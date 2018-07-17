import javax.xml.crypto.Data;
import java.io.*;

public class TunniplaaniTest {

    public static void main(String[] args) throws IOException {

        File andmed = new File("andmed.bin");

        Tunniplaan tunniplaan = new Tunniplaan(new String[]{"KMII", "OOP"}, new int[]{615, 735});
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(andmed));
        tunniplaan.salvestaAndmed(dos);
        dos.close();

        DataInputStream dis = new DataInputStream(new FileInputStream(andmed));
        Tunniplaan tunniplaan2 = tunniplaan.laadiAndmed(dis);
        dis.close();

        System.out.println(tunniplaan2.ained);
        System.out.println(tunniplaan2.algused);

    }

}
