package csvlugeja;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CSVLugeja implements AutoCloseable{
    private BufferedReader br;

    public CSVLugeja(String fileName) throws IOException {
        if(!fileName.toLowerCase().endsWith(".csv")){
            throw new IOException("File doesn't end with .csv!");
        }
        this.br = new BufferedReader(new FileReader(new File(fileName)));

    }

    public String[] loeRida() throws IOException {
        String line;
        if((line = br.readLine()) == null){
            return null;
        }else{
            return line.split(",");
        }
    }

    @Override
    public void close() throws Exception {
        br.close();
    }
}
