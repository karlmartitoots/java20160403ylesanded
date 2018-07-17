package bitwise;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DataInputStream extends FilterInputStream {

  public DataInputStream(InputStream in) {
    super(in);
  }

  /**
   * Reads four input bytes and returns an int value.
   */
  public int readInt() throws IOException {
    int nextbyte;
    int returnable = 0;
    for (int i = 0; i < 4; i++) {
      if((nextbyte = read()) != -1)
      {
        returnable = returnable | ((nextbyte & 0x000000FF) << 8*(3 - i));
      }
    }
    return returnable;
  }

  /**
   * Reads in a string that has been encoded using the UTF-8 format.
   * <p>
   * First, four bytes are read and used to construct an
   * integer in exactly the manner of the readInt method.
   * This integer value specifies the number of additional
   * bytes to be read. These bytes are then converted to a
   * String using UTF-8 encoding.
   */
  public String readUTF() throws IOException {
    int size = readInt();
    byte string[] = new byte[size];
      for (int i = 0; i < size; i++) {

      }
    return null;
  }
}
