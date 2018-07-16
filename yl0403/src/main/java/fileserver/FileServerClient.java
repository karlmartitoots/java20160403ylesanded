package fileserver;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class FileServerClient {

    public static void main(String[] args) throws IOException {

        try (Socket socket = new Socket(InetAddress.getLocalHost(), 1337);
             DataInputStream inputStream = new DataInputStream(socket.getInputStream());
             DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream())) {

            if(args[0] == null){
                System.out.println("1st commandline argument is null.");
                System.exit(-1);
            }
            outputStream.writeUTF(args[0]);

            if (args[1] == null) {
                System.out.println("2nd commandline argument is null.");
                System.exit(-1);
            }
            File outputDirectory = new File(args[1]);

            inputStream.readInt();

            saveFile(inputStream, outputDirectory);
        }
    }

    /**
     * Saves all bytes from inputStream into outputDirectory
     * @param inputStream Input stream of bytes
     * @param outputDirectory Output directory for the file
     * @throws IOException
     */
    private static void saveFile(DataInputStream inputStream, File outputDirectory) throws IOException {
        int currentBufferSize;
        byte[] byteChunk = new byte[1024];
        try (BufferedOutputStream fileOutputStream = new BufferedOutputStream(new FileOutputStream(outputDirectory))) {

            while ((currentBufferSize = inputStream.read(byteChunk, 0, byteChunk.length)) != -1) {
                fileOutputStream.write(byteChunk, 0, currentBufferSize);
            }

        }
    }

}
