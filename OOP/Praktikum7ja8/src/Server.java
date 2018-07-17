import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9000);
        Socket incoming = serverSocket.accept();
        InputStream inputStream = incoming.getInputStream();

        DataInputStream dis = new DataInputStream(inputStream);
        System.out.println(dis.readUTF());
    }


}
