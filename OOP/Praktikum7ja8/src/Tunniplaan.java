import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Karl on 3.03.2016.
 */
public class Tunniplaan {

    String[] ained;
    int[] algused;


    public Tunniplaan(String[] initAined, int[] initAlgused) {
        this.ained = initAined;
        this.algused = initAlgused;
    }

    public void salvestaAndmed(DataOutputStream dos) throws IOException {
        dos.write(ained.length);
        for (int i = 0; i < ained.length; i++) {
            dos.writeUTF(ained[i]);
            dos.write(algused[i]);
        }
    }

    public Tunniplaan laadiAndmed(DataInputStream dis) throws IOException {
        int aineid = dis.readInt();
        String[] laetudAined = new String[aineid];
        int[] laetudAlgused = new int[aineid];

        for (int i = 0; i < aineid; i++) {
            laetudAined[i] = String.valueOf(dis.read());
            laetudAlgused[i] = dis.read();
        }

        return new Tunniplaan(laetudAined, laetudAlgused);
    }

}
