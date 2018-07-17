import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class P11Ü1 {

    public static void main(String[] args) throws IOException {

        String filename = args[0];
        Map<String, Integer> sõnaraamat = new HashMap<>();
        try (
                DataInputStream br = new DataInputStream(new FileInputStream(filename));) {

            String line = "";

            while ((line = br.readUTF()) != null) {
                String[] parts = line.split(" ");
                for (String part : parts) {
                    if (!sõnaraamat.containsKey(part)) {
                        sõnaraamat.put(part, 1);
                    } else {
                        Integer currentCount = sõnaraamat.get(part);
                        sõnaraamat.replace(part, currentCount, currentCount + 1);
                    }
                }
            }
        }

        System.out.println(sõnaraamat);

    }

}
