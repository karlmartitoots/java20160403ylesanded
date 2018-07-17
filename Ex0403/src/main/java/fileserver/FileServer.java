package fileserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {

    public static void main(String[] args) throws IOException {

        try (ServerSocket serverSocket = new ServerSocket(1337)) {
            while (true) {
                Socket openSocket = serverSocket.accept();

                new Thread(() -> {
                    try (DataInputStream dataInputStream = new DataInputStream(openSocket.getInputStream())) {
                        String resourceName = dataInputStream.readUTF();
                        sendByteArray(resourceName, openSocket);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } finally {
                        try {
                            openSocket.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }).start();
            }
        }
    }

    /**
     * Sends a file to the client as a byte array using an open socket
     * @param resourceName Name of the resource to be sent
     * @param openSocket An open socket connected to the client
     * @throws IOException
     */
    private static void sendByteArray(String resourceName, Socket openSocket) throws IOException {
        try(
                DataOutputStream dataOutputStream = new DataOutputStream(openSocket.getOutputStream());
                InputStream resourceInputStream = FileServer.class.getClassLoader().getResourceAsStream(resourceName)){

            if(resourceInputStream == null){
                openSocket.close();
                Thread.currentThread().interrupt();
                return;
            }

            ByteArrayOutputStream byteArrayOutputStream = getResourceAsByteArrayOutputStream(resourceInputStream);

            dataOutputStream.writeInt(byteArrayOutputStream.size());
            byteArrayOutputStream.writeTo(dataOutputStream);
        }
    }

    /**
     * Turns a resourceInputStream into a ByteArrayOutputStream
     * @param resourceInputStream Input stream for converting into byte array output stream
     * @return Returns a stream that contains the resourceInputStream as a byte array
     * @throws IOException
     */
    private static ByteArrayOutputStream getResourceAsByteArrayOutputStream(InputStream resourceInputStream) throws IOException {
        int currentBufferSize;
        byte[] byteChunk = new byte[1024];

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while((currentBufferSize = resourceInputStream.read(byteChunk, 0, byteChunk.length)) != -1){
            byteArrayOutputStream.write(byteChunk, 0, currentBufferSize);
        }
        return byteArrayOutputStream;
    }
}
