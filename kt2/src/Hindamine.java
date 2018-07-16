import java.io.*;
import java.util.*;

public class Hindamine {
    public static void main(String[] args) {
        Queue<Kontrolltöö> kontrolltööd = loeKontrolltööd("kontrolltööd.dat");
        List<Hinnang> hinnangud = new ArrayList<>();
        //Kahjuks ei leia viisi, kuidas seada kodeering "utf-8"

        try(Scanner systemInScanner = new Scanner(System.in)){
            boolean lõpp = false;
            while(!lõpp) {
                System.out.print("Kas soovid tööd (H)innata, hinnangut (V)aadata või hinnangut (S)alvestada? ");
                String soov = systemInScanner.nextLine();
                if(kontrolltööd.isEmpty() && soov.equals("H")){
                    System.out.println("Pole kontrolltöid, mida hinnata!");
                    continue;
                }
                if(hinnangud.isEmpty() && soov.equals("V")){
                    System.out.println("Pole hinnatud kontrolltöid, mida vaadata!");
                    continue;
                }
                switch (soov) {
                    case "H":
                        prooviKontrolltöödHinnata(kontrolltööd, hinnangud, systemInScanner);
                        break;
                    case "V":
                        prooviHinnangutVaadata(hinnangud, systemInScanner);
                        break;
                    case "S":
                        salvestaHinnangud(hinnangud);
                        lõpp = true;
                        break;
                    default:
                }
            }
        }
    }

    private static void salvestaHinnangud(List<Hinnang> hinnangud) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(new File("hinnangud.txt")))){
            for (Hinnang hinnang : hinnangud) {
                bw.write("Hinnang kontrolltööle:\n" + hinnang.toString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("IOException, kui proovisin hinnanguid kirjutada.");
        }
        System.out.println("Hinnangud salvestatud!");
    }

    private static void prooviHinnangutVaadata(List<Hinnang> hinnangud, Scanner systemInScanner) {
        Map<String, Hinnang> nimiHinnanguks = new HashMap<>();
        //dialoog
        System.out.print("Hinnatud on järgmiste tudengite tööd: ");
        String nimedToString = initsieeriNimiHinnanguksJaLooNimedeToString(hinnangud, nimiHinnanguks);
        System.out.println(nimedToString);
        System.out.println("Kelle töö hinnangut soovid vaadata?");
        String nimi = prooviSaadaHinnatudTöögaAutoriNimi(systemInScanner, nimiHinnanguks, nimedToString);
        System.out.println("Hinnang kontrolltööle: ");
        System.out.println(nimiHinnanguks.get(nimi).toString());
    }

    private static String prooviSaadaHinnatudTöögaAutoriNimi(Scanner systemInScanner, Map<String, Hinnang> nimiHinnanguks, String nimedToString) {
        String nimi = null;
        boolean nimiEksisteerib = false;
        while(!nimiEksisteerib){
            if(!nimiHinnanguks.containsKey(nimi = systemInScanner.nextLine())){
                System.out.println("Sellist nime pole. Vali nimi uuesti: " + nimedToString);
            }else{
                nimiEksisteerib = true;
            }
        }
        return nimi;
    }

    private static String initsieeriNimiHinnanguksJaLooNimedeToString(List<Hinnang> hinnangud, Map<String, Hinnang> nimiHinnanguks) {
        boolean first = true;
        String nimedToString = "[";
        for (Hinnang hinnang : hinnangud) {
            if(first){
                nimedToString += hinnang.getKontrolltöö().getAutoriNimi();
                first = false;
            }else{
                nimedToString += ", " + hinnang.getKontrolltöö().getAutoriNimi();
            }
            nimiHinnanguks.put(hinnang.getKontrolltöö().getAutoriNimi(), hinnang);
        }
        nimedToString += "]";
        return nimedToString;
    }

    private static void prooviKontrolltöödHinnata(Queue<Kontrolltöö> kontrolltööd, List<Hinnang> hinnangud, Scanner systemInScanner) {
        Kontrolltöö hinnatavTöö = kontrolltööd.peek();
        System.out.println("Hindad tööd: ");
        System.out.println(hinnatavTöö.toString());
        System.out.println("Sisesta hinne (0-14): ");
        double kontrolltööHinne = Double.parseDouble(systemInScanner.nextLine());
        System.out.println("Sisesta kommentaar: ");
        String kontrolltööKommentaar = systemInScanner.nextLine();
        //kontrolli
        prooviLuuaHinnang(kontrolltööd, hinnangud, hinnatavTöö, kontrolltööHinne, kontrolltööKommentaar);
    }

    private static void prooviLuuaHinnang(Queue<Kontrolltöö> kontrolltööd, List<Hinnang> hinnangud, Kontrolltöö hinnatavTöö, double kontrolltööHinne, String kontrolltööKommentaar) {
        Hinnang kontrolltööHinnang = null;
        try {
            kontrolltööHinnang = new Hinnang(hinnatavTöö, kontrolltööHinne, kontrolltööKommentaar);
        } catch (EbasobivHinneErind e) {
            System.out.println("Peab olema mittenegatiivne arv, 14 on maksimum");
        }
        if (kontrolltööHinnang != null) {
            kontrolltööd.remove();
            hinnangud.add(kontrolltööHinnang);
        } else {
            System.out.println("Töö hindamine ei õnnestunud");
        }
    }

    private static Queue<Kontrolltöö> loeKontrolltööd(String failiNimi){
        Queue<Kontrolltöö> kontrolltööd = new LinkedList<>();
        try(DataInputStream dis = new DataInputStream(new FileInputStream(new File(failiNimi)))){
            int kontrolltöödeArv = dis.readInt();
            String autoriNimi, ktSisu;
            for (int i = 0; i < kontrolltöödeArv; i++) {
                autoriNimi = dis.readUTF();
                ktSisu = dis.readUTF();
                kontrolltööd.add(new Kontrolltöö(autoriNimi, ktSisu));
            }
        } catch (IOException e) {
            System.out.println("IOException in loeKontrolltööd!");
        }
        return kontrolltööd;
    }
}
