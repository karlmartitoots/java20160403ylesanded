package bitwise;

import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static bitwise.TestUtil.assertEquals;

public class ColorTest {

  // just some distinct bit patterns
  private static final int R = 0b10101000;
  private static final int G = 0b01101001;
  private static final int B = 0b11101010;
  private static final int A = 0b01001011;
  private static final int X = 0b11110101;

  @Test
  public void testZero() throws Exception {
    Color c = new Color();
    assertEquals(0, c.getPacked());
  }

  @Test
  public void testSetRed() throws Exception {
    Color c = new Color();
    c.setPacked(pack(R, G, B, A));
    c.setRed(X);
    assertEquals(pack(X, G, B, A), c.getPacked());
  }

  @Test
  public void testSetGreen() throws Exception {
    Color c = new Color();
    c.setPacked(pack(R, G, B, A));
    c.setGreen(X);
    assertEquals(pack(R, X, B, A), c.getPacked());
  }

  @Test
  public void testSetBlue() throws Exception {
    Color c = new Color();
    c.setPacked(pack(R, G, B, A));
    c.setBlue(X);
    assertEquals(pack(R, G, X, A), c.getPacked());
  }

  @Test
  public void testSetAlpha() throws Exception {
    Color c = new Color();
    c.setPacked(pack(R, G, B, A));
    c.setAlpha(X);
    assertEquals(pack(R, G, B, X), c.getPacked());
  }

  @Test
  public void testGetRed() throws Exception {
    Color c = new Color();
    c.setPacked(pack(R, G, B, A));
    assertEquals(R, c.getRed());
  }

  @Test
  public void testGetGreen() throws Exception {
    Color c = new Color();
    c.setPacked(pack(R, G, B, A));
    assertEquals(G, c.getGreen());
  }

  @Test
  public void testGetBlue() throws Exception {
    Color c = new Color();
    c.setPacked(pack(R, G, B, A));
    assertEquals(B, c.getBlue());
  }

  @Test
  public void testGetAlpha() throws Exception {
    Color c = new Color();
    c.setPacked(pack(R, G, B, A));
    assertEquals(A, c.getAlpha());
  }

  private static int pack(int ... bytes) {
    ByteBuffer buf = ByteBuffer.allocate(bytes.length).order(ByteOrder.BIG_ENDIAN);
    for (int b : bytes) {
      buf.put((byte) b);
    }
    buf.flip();
    return buf.getInt();
  }
}
