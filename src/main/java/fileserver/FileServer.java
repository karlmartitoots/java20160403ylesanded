package fileserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class FileServer {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(1337);
        try {
            while (true) {
                Socket incoming = serverSocket.accept();
                new Thread(() -> {
                    try (
                            DataInputStream input = new DataInputStream(incoming.getInputStream())) {
                        String resourceName = input.readUTF();

                        try(
                                DataOutputStream output = new DataOutputStream(incoming.getOutputStream());
                                InputStream resourceInputStream = FileServer.class.getClassLoader().getResourceAsStream(resourceName)){

                            if(resourceInputStream == null){
                                incoming.close();
                                Thread.currentThread().interrupt();
                                return;
                            }

                            int read;
                            int fileSize = 0;
                            byte[] byteChunk = new byte[1024];

                            ByteArrayOutputStream deezBytes = new ByteArrayOutputStream();
                            while((read = resourceInputStream.read(byteChunk, 0, 1024)) != -1){
                                fileSize += read;
                                deezBytes.write(byteChunk, 0, read);
                            }

                            output.writeInt(fileSize);
                            deezBytes.writeTo(output);
                        }
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
