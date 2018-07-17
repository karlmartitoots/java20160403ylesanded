import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class P11Ü2 {

    public static void main(String[] args) throws IOException {

        String file = args[0];
        Map<String, String> guestPairs = new HashMap<>();

        try(DataInputStream dis = new DataInputStream(new FileInputStream(file));){

            String line = "";

            while((line = dis.readUTF()) != null){

                String[] parts = line.split(" ");
                guestPairs.put(parts[1], parts[0]);

            }

        }

        String guest = args[1];

        while(guestPairs.containsKey(guest)){
            guest = guestPairs.get(guest);
        }

        System.out.println(guest);

    }

}
