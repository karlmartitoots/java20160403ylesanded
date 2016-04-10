package fileserver;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class FileServerClient {

    public static void main(String[] args) throws IOException {

        try (
                Socket socket = new Socket(InetAddress.getLocalHost(), 1337);
                DataInputStream inputStream = new DataInputStream(socket.getInputStream());//input socketist(puhverdatud)
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream())) {//output socketile
            outputStream.writeUTF(args[0]); //saadab failinime
            File resource = new File(args[1]);
            inputStream.readInt();//saab failisuuruse

            int read;
            byte[] byteChunk = new byte[1024];
            try(
                    BufferedOutputStream fileOutputStream = new BufferedOutputStream(new FileOutputStream(resource))) {
                while ((read = inputStream.read(byteChunk, 0, byteChunk.length)) != -1) {
                    fileOutputStream.write(byteChunk, 0, read);
                }
            }
        }
    }

}
