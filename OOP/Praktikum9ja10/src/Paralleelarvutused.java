import sun.security.mscapi.KeyStore;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Paralleelarvutused {

    public static void main(String[] args) {

        if(!(args.length > 0)){
            System.out.println("Missing command line argument. ");
            System.exit(-1);
        }

        String path = args[0];
        List<List<BigInteger>> listsOfNumbers = new ArrayList<>();
        List<String> fileNames = findTxtFiles(path);

        Runnable r = () -> {

        };

        ExecutorService threadPool = Executors.newFixedThreadPool(fileNames.size());
        for (int i = 0; i < fileNames.size(); i++) {
            MyFileReader myFileReader = new MyFileReader(path + "\\" + fileNames.get(i));
            threadPool.submit(r);
        }
    }

    static List<String> findTxtFiles(String path){

        List<String> txtFileNames = new ArrayList<String>();
        File dir = new File(path);

        for (File file : dir.listFiles()) {
            if(file.getName().endsWith(".txt")){
                txtFileNames.add(file.getName());
            }
        }
        return txtFileNames;
    }

}
