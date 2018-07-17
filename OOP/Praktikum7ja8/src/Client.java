import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9000);
        OutputStream outputStream = socket.getOutputStream();

        DataOutputStream dos = new DataOutputStream(outputStream);
        dos.writeUTF("hi");
        dos.close();
        socket.close();
    }

}
