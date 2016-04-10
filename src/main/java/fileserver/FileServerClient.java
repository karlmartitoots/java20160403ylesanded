package fileserver;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class FileServerClient {

    public static void main(String[] args) throws IOException {

            try (
                    Socket socket = new Socket(InetAddress.getLocalHost(), 1337);
                    DataInputStream inputStream = new DataInputStream(socket.getInputStream());//input socketist(puhverdatud)
                    DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());) {//output socketile
                outputStream.writeUTF(args[0]); //saadab failinime
                System.out.println(inputStream.readUTF());
            }
    }

}
