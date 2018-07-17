package bitwise;

import org.junit.Assert;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class TestUtil {

  public static void assertEquals(int expected, int actual) {
    Assert.assertEquals(bin(expected), bin(actual));
  }

  private static String bin(int value) {
    return leftPad(Integer.toBinaryString(value), '0', 32);
  }

  private static String leftPad(String str, char padding, int len) {
    StringBuilder sb = new StringBuilder(len);
    for (int i = 0; i < len - str.length(); i++)
      sb.append(padding);
    return sb.append(str).toString();
  }

  public static InputStream prepare(InputBuilder builder) throws IOException {
    PipedOutputStream pos = new PipedOutputStream();
    PipedInputStream pis = new PipedInputStream(pos);
    try (DataOutputStream out = new DataOutputStream(pos)) {
      builder.build(out);
    }
    return pis;
  }

  public static OutputStream outputVerifiedOnClose(Verifier verifier) throws IOException {
    PipedOutputStream pos = new PipedOutputStream();
    PipedInputStream pis = new PipedInputStream(pos);
    return new FilterOutputStream(pos) {
      @Override
      public void close() throws IOException {
        super.close();
        try (java.io.DataInputStream dis = new DataInputStream(pis)) {
          verifier.verify(dis);
        }
      }
    };
  }

  public static byte[] readBytes(InputStream in, int count) throws IOException {
    byte[] received = new byte[count];
    for (int i = 0; i < received.length; i++) {
      int b = in.read();
      if (b == -1)
        throw new IOException("premature end of stream at " + i);
      received[i] = (byte) b;
    }
    return received;
  }

  @FunctionalInterface
  public interface Verifier {
    void verify(DataInputStream s) throws IOException;
  }

  @FunctionalInterface
  public interface InputBuilder {
    void build(DataOutputStream s) throws IOException;
  }
}
