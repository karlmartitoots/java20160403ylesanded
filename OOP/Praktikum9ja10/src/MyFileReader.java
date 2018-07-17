import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;

public class MyFileReader implements Runnable{

    private final String fileName;
    private List<BigInteger> numbers = new ArrayList<>();

    MyFileReader(String file){
        this.fileName = file;
    }

    public List<BigInteger> readFile() throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader(this.fileName))){

            String line = bufferedReader.readLine();

            while(line != null){
                String[] parts = line.split(" ");
                for (String part : parts) {
                    try {
                        this.numbers.add(new BigInteger(part));
                    } catch(RuntimeException e){
                        throw new RuntimeException("RuntimeException " + e + "! From file \"" + this.fileName + "\" found an element: \"" + part + "\", that could not be converted to a number. :(");
                    }
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new IOException("Exception " + e + "! Something went wrong, when trying to reading file " + this.fileName);
        }
    }

    @Override
    public void run() {
        try {
            readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
