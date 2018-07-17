package csvlugeja;

import java.util.Arrays;

public class CSVLugejaTest {
    public static void main(String[] args) {
        try(CSVLugeja CSVLugeja = new CSVLugeja("SampleCSVFile1.csv")){
            String[] rida;
            while((rida = CSVLugeja.loeRida()) != null){
                System.out.println(Arrays.toString(rida));
            }
        } catch (Exception e) {
            System.out.println("Something went wrong when reading the file.");
            System.exit(-1);
        }
    }
}
