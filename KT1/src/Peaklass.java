import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.util.Collections.sort;

public class Peaklass {

    public static void main(String[] args) throws FileNotFoundException {

        List<Treening> treeninguNimekiri = loeTreeningud("treeningud.txt");

        List<Klient> kliendiNimekiri = new ArrayList<>();
        kliendiNimekiri.addAll(Arrays.asList(
                new Püsiklient("Taavi", 50),
                new Püsiklient("Märt", 25),
                new Klient("Foo"),
                new Klient("Bar"),
                new Klient("Baz"),
                new Klient("Quz")));



        for (Treening treening : treeninguNimekiri) {
            for (Klient klient : kliendiNimekiri) {
                treening.registreeri(klient);
            }
        }

        for (Treening treening : treeninguNimekiri) {
            System.out.println(treening.toString());
        }

        sort(kliendiNimekiri);

        for (Klient klient : kliendiNimekiri) {
            System.out.println(klient.toString());
        }

    }

    public static List<Treening> loeTreeningud(String fileName) throws FileNotFoundException {

        List<Treening> exerciseProgramList = new ArrayList<>();
        File file = new File(fileName);

        try (Scanner sc = new Scanner(file, "utf-8")){
            String line = "";
            while(sc.hasNextLine()){
                line = sc.nextLine();
                if(line.contains(",")){
                    String[] parts = line.split(",");
                    exerciseProgramList.add(new Rühmatreening(parts[0], Integer.parseInt(parts[1]), Double.parseDouble(parts[2])));
                }else{
                    exerciseProgramList.add(new Individuaaltreening(line));
                }
            }
        } finally{
            return exerciseProgramList;
        }
    }

}
