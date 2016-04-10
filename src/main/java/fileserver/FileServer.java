package fileserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(1337);
        try {
            while (true) {
                Socket incoming = serverSocket.accept();
                new Thread(() -> {
                    try (
                            DataOutputStream output = new DataOutputStream(incoming.getOutputStream());
                            DataInputStream input = new DataInputStream(incoming.getInputStream())) {
                        String resourceName = input.readUTF();
                        //Object file = getClass().getClassLoader().getResourceAsStream(resourceName);

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } finally {
                        try {
                            incoming.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }).start();
            }
        }catch(EOFException e){
            serverSocket.close();
        }
    }
}
