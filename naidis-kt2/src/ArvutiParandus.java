import java.io.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;

class ArvutiParandus {
    private static String ootelArvutiteFailinimi;
    private static Map<String, Double> parandajateTunnitasud;
    private static List<Arvuti> ootelArvutid, kiirtööd = new ArrayList<>(), tavatööd = new ArrayList<>(), tehtudTööd = new ArrayList<>();
    private static List<String> faultyDescriptions = new ArrayList<>();

    public static void main(String[] args) {
        if(args.length < 1){
            System.out.println("System arguments missing.");
            System.exit(-1);
        }

        //ootel arvutite failinimi/URL salvestatakse
        ootelArvutiteFailinimi = args[0];

        //luuakse listid, mida meetod hakkab kasutama, samuti loetakse ootel arvutite listi ootel arvutid
        initiateWaitingComputers();

        //töötajate tunnitasud loetakse sisse lihtsaks ligipääsuks
        initiateRepairersFees();

        //pannakse tööle suhtlus arvuti ja töötaja vahel
        runDialog();

    }

    private static void runDialog() {
        List<String> töötüübid = new ArrayList<>(3);
        töötüübid.add("L");
        töötüübid.add("P");
        töötüübid.add("R");
        double teenitudRaha = 0.0;
        boolean tööLäbi = false;
        String töötüüp;

        //algab töötsükkel
        try(Scanner scanner = new Scanner(System.in)){
            while(!tööLäbi){
                while(true) {
                    System.out.print("Kas soovid parandada (P), uut tööd registreerida (R) või lõpetada (L) ? ");
                    töötüüp = scanner.nextLine();
                    if (kiirtööd.isEmpty() && tavatööd.isEmpty() && töötüüp.equals("P")) {
                        System.out.println("Ühtegi tööd pole alles, saab ainult registreerida või lõpetada!");
                        continue;
                    }
                    if (töötüübid.contains(töötüüp)) {
                        break;
                    } else {
                        System.out.println("Vigane sisestus! Vali 'P', 'R' ja 'L' vahel!\n");
                    }
                }
                Arvuti käsilOlevArvuti;
                switch (töötüüp){
                    case "P":
                        System.out.print("Arvuti info: ");

                        //võtab ette järgmise töö
                        if(!kiirtööd.isEmpty()){
                            käsilOlevArvuti = kiirtööd.remove(0);
                        }else{
                            käsilOlevArvuti = tavatööd.remove(0);
                        }
                        ootelArvutid.remove(käsilOlevArvuti);

                        //prindib töö kirjelduse
                        System.out.println(käsilOlevArvuti.toString());

                        //küsib aega, mis kulus
                        int parandamisaeg;
                        while(true) {
                            System.out.print("Sisesta parandamiseks kulunud aeg (täisminutites): ");
                            try {
                                parandamisaeg = Integer.parseInt(scanner.nextLine());
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Pole arv!");
                            }
                        }

                        //küsib, kes parandas
                        String nimi;
                        Double tunnitasu;
                        while (true){
                            System.out.print("Sisesta enda nimi: ");
                            nimi = scanner.nextLine();
                            if(!(parandajateTunnitasud.containsKey(nimi))){
                                System.out.println("Pole sellist töötajat!");
                            }else{
                                tunnitasu = parandajateTunnitasud.get(nimi);
                                break;
                            }
                        }

                        //lõpetab töö
                        käsilOlevArvuti.lõpetaTöö((parandamisaeg/60.0)*tunnitasu);
                        teenitudRaha += käsilOlevArvuti.getArveSumma();
                        tehtudTööd.add(käsilOlevArvuti);
                        System.out.println("Töö tehtud, arve summa on " + käsilOlevArvuti.getArveSumma() + " €!\n");
                        break;
                    case "R":

                        //küsib töö kirjeldust
                        while(true){
                            System.out.print("Sisesta töö kirjeldus: ");
                            if((käsilOlevArvuti = loeArvuti(scanner.nextLine())) != null){
                                break;
                            }
                        }

                        //lisab töö nimekirja
                        if(käsilOlevArvuti.onKiirtöö()){
                            kiirtööd.add(käsilOlevArvuti);
                        }else{
                            tavatööd.add(käsilOlevArvuti);
                        }
                        ootelArvutid.add(käsilOlevArvuti);
                        System.out.println("Töö on registreeritud!\n");
                        break;
                    case "L":
                        //kirjutab kokkuvõtte
                        System.out.println("Sessiooni kokkuvõte: ");
                        System.out.println("Teenitud raha: " + teenitudRaha);
                        Map<String, Integer> parandatudArvutiteTootjad = new HashMap<>();
                        //tootjate arvestus
                        for (Arvuti tehtudTöö : tehtudTööd) {
                            if(!parandatudArvutiteTootjad.containsKey(tehtudTöö.getTootja())) {
                                parandatudArvutiteTootjad.put(tehtudTöö.getTootja(), 1);
                            }else{
                                parandatudArvutiteTootjad.put(tehtudTöö.getTootja(), parandatudArvutiteTootjad.get(tehtudTöö.getTootja()) + 1);
                            }
                        }
                        for (String tootja : parandatudArvutiteTootjad.keySet()) {
                            System.out.println("\t" + tootja + ": " + parandatudArvutiteTootjad.get(tootja) + "tk");
                        }
                        System.out.print("Ootele jäi " + ootelArvutid.size() + " arvuti(t).");
                        //tööläbi, seega tsükkel enam tööle ei lähe
                        tööLäbi = true;

                        //kirjutab tehtud tööd dat faili
                        try(DataOutputStream dos = new DataOutputStream(new FileOutputStream(new File("tehtud.dat")))){
                            for (Arvuti tehtudTöö : tehtudTööd) {
                                dos.writeUTF(tehtudTöö.toString());
                            }
                        } catch (IOException e) {
                            System.out.println("Couldn't access tehtud.dat.");
                            System.exit(-1);
                        }

                        //kirjutab ootele jäänud tööd txt faili
                        try(BufferedWriter bw = new BufferedWriter(new FileWriter(new File("ootel.txt")))){
                            for (Arvuti ootelArvuti : ootelArvutid) {
                                bw.write(ootelArvuti.toString() + "\n");
                            }
                        } catch (IOException e) {
                            System.out.println("Couldn't access ootel.txt!");
                            System.exit(-1);
                        }

                        //kirjutab vigased kirjeldused txt faili
                        try(BufferedWriter bw = new BufferedWriter(new FileWriter(new File("vigased_kirjeldused.txt")))) {
                            for (String faultyDescription : faultyDescriptions) {
                                bw.write(faultyDescription + "\n");
                            }
                        } catch (IOException e) {
                            System.out.println("Couldn't access vigased_kirjeldused.txt");
                            System.exit(-1);
                        }
                        break;
                }
            }
        }
    }

    private static void initiateWaitingComputers() {
        ootelArvutid = loeOotelArvutid(ootelArvutiteFailinimi);
        //tööd liigitatakse, et neid lihtsalt pärast tegema hakata
        for (Arvuti ootelArvuti : ootelArvutid) {
            if(ootelArvuti.onKiirtöö()){
                kiirtööd.add(ootelArvuti);
            }else{
                tavatööd.add(ootelArvuti);
            }
        }
    }

    private static void initiateRepairersFees() {
        try(DataInputStream dis = new DataInputStream(new FileInputStream(new File("tunnitasud.dat")))){
            Map<String,Double> repairersFees = new HashMap<>();
            int amountOfRepairers = dis.readInt();
            String repairerName;
            double repairerFee;
            for (int i = 0; i < amountOfRepairers; i++) {
                repairerName = dis.readUTF();
                repairerFee = dis.readDouble();
                repairersFees.put(repairerName, repairerFee);
            }
            parandajateTunnitasud = repairersFees;
        } catch (IOException e) {
            System.out.println("Couldn't access tunnitasud.dat.");
            System.exit(-1);
        }
    }

    private static Arvuti loeArvuti(String tööKirjeldus){
        try {
            if(!tööKirjeldus.contains(";")) {
                throw new FormaadiErind(tööKirjeldus);
            }
            String[] tööKirjeldusJaRegistreerimisAeg = getTööKirjeldusJaRegistreerimisAeg(tööKirjeldus);
            String[] tööKirjelduseOsad = getTööKirjelduseOsad(tööKirjeldusJaRegistreerimisAeg[0]);

            if(tööKirjeldusJaRegistreerimisAeg.length < 1 || tööKirjeldusJaRegistreerimisAeg.length > 2){
                throw new FormaadiErind(tööKirjeldus);
            }
            if(tööKirjelduseOsad.length < 2 || tööKirjelduseOsad.length > 3){
                throw new FormaadiErind(tööKirjeldus);
            }
            boolean onKiirtöö = checkForKiirtöö(tööKirjeldus, tööKirjelduseOsad[1]);

            if(tööKirjeldusJaRegistreerimisAeg.length == 2) {
                return createComputerWithDateTime(tööKirjeldus, tööKirjeldusJaRegistreerimisAeg, tööKirjelduseOsad, onKiirtöö);
            }else if(tööKirjeldusJaRegistreerimisAeg.length == 1){
                return createComputer(tööKirjeldus, tööKirjelduseOsad, onKiirtöö);
            }
        }catch(FormaadiErind e){
            System.out.println("Vigane sisestus! Viga " + e.getVeaPositsioon() + ". väljas!");
            faultyDescriptions.add(e.getViganeRida());
        }
        return null;
    }

    private static Arvuti createComputer(String tööKirjeldus, String[] tööKirjelduseOsad, boolean onKiirtöö) {
        if (tööKirjelduseOsad.length == 2) {
            return new Arvuti(tööKirjelduseOsad[0], onKiirtöö);
        } else if (tööKirjelduseOsad.length == 3 && tööKirjelduseOsad[2].equals("monitoriga")) {
            return new VäliseMonitorigaArvuti(tööKirjelduseOsad[0], onKiirtöö);
        } else throw new FormaadiErind(tööKirjeldus);
    }

    private static Arvuti createComputerWithDateTime(String tööKirjeldus, String[] tööKirjeldusJaRegistreerimisAeg, String[] tööKirjelduseOsad, boolean onKiirtöö) {
        if (tööKirjelduseOsad.length == 2) {
            return new Arvuti(tööKirjelduseOsad[0], onKiirtöö, LocalDateTime.parse(tööKirjeldusJaRegistreerimisAeg[1]));
        } else if (tööKirjelduseOsad.length == 3 && tööKirjelduseOsad[2].equals("monitoriga")) {
            return new VäliseMonitorigaArvuti(tööKirjelduseOsad[0], onKiirtöö, LocalDateTime.parse(tööKirjeldusJaRegistreerimisAeg[1]));
        } else throw new FormaadiErind(tööKirjeldus);
    }

    private static boolean checkForKiirtöö(String tööKirjeldus, String s) {
        boolean onKiirtöö;
        switch (s) {
            case "kiirtöö":
                onKiirtöö = true;
                break;
            case "tavatöö":
                onKiirtöö = false;
                break;
            default:
                throw new FormaadiErind(tööKirjeldus);
        }
        return onKiirtöö;
    }

    private static String[] getTööKirjeldusJaRegistreerimisAeg(String tööKirjeldus) throws FormaadiErind{
        return tööKirjeldus.split("\\|");
    }

    private static String[] getTööKirjelduseOsad(String tööKirjelduseOsa) throws FormaadiErind{
        return tööKirjelduseOsa.split(";");
    }

    private static List<Arvuti> loeOotelArvutid(String failiNimi){
        List<Arvuti> tulevasedOotelArvutid = new ArrayList<>();
        if(failiNimi.contains("http://") || failiNimi.contains("https://")){
            try(InputStream sisse =
                        new URL(failiNimi).openConnection().getInputStream()){
                Scanner scanner = new Scanner(sisse);
                readWaitingComputers(tulevasedOotelArvutid, scanner);
            } catch (IOException e) {
                System.out.println("Couldn't access " + failiNimi);
                System.exit(-1);
            }

        }else{
            try(Scanner ootelScanner = new Scanner(new File(failiNimi))){
                readWaitingComputers(tulevasedOotelArvutid, ootelScanner);
            } catch (FileNotFoundException e) {
                System.out.println("Couldn't access " + failiNimi);
                System.exit(-1);
            }
        }
        return tulevasedOotelArvutid;
    }

    private static void readWaitingComputers(List<Arvuti> tulevasedOotelArvutid, Scanner scanner) {
        while(scanner.hasNextLine()){
            Arvuti ootelTöö;
            if((ootelTöö = loeArvuti(scanner.nextLine())) != null){
                tulevasedOotelArvutid.add(ootelTöö);
            }
        }
    }

}
