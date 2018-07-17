package bitwise;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DataOutputStream extends FilterOutputStream {

  public DataOutputStream(OutputStream out) {
    super(out);
  }

  /**
   * Writes an int to the underlying output stream as four bytes, high byte first.
   */
  public void writeInt(int v) throws IOException {
    // TODO: implement
  }

  /**
   * Writes a string to the underlying output stream using UTF-8 encoding.
   * <p>
   * First, four bytes are written to the output stream as if by the writeInt
   * method giving the number of bytes to follow. This value is the number of
   * bytes actually written out, not the length of the string. Following the
   * length, each character of the string is output, in sequence, using the UTF-8
   * encoding for the character.
   */
  public void writeUTF(String str) throws IOException {
    // TODO: implement
  }
}
