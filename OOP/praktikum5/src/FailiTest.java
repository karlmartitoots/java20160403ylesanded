import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FailiTest {

    public static void main(String[] args) throws IOException {
        
        Path folderName = Paths.get(args[0]);
        FailiVaatleja fileObserver = new FailiVaatleja();
        List<String> fileNames = new ArrayList<String>();

        if(!Files.isDirectory(folderName)){
            System.out.println("paha! (vigane kaustanimi)");
            System.exit(1);
        }

        Files.walkFileTree(folderName, fileObserver);
        fileNames = fileObserver.getFileNames();
        Collections.sort(fileNames, new NimeSorteerija());
        System.out.println(fileNames);
    }


}
