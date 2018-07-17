package bitwise;

import org.junit.Test;

import java.io.OutputStream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class OutputTest {

  @Test
  public void testPositiveInt() throws Exception {
    int value = 0x0F1F2F3F;
    OutputStream out = TestUtil.outputVerifiedOnClose(in -> {
      TestUtil.assertEquals(value, in.readInt());
      assertEquals("stream reports end", -1, in.read());
    });
    try (DataOutputStream dos = new DataOutputStream(out)) {
      dos.writeInt(value);
    }
  }

  @Test
  public void testNegativeInt() throws Exception {
    int value = 0xF0F1F2F3;
    OutputStream out = TestUtil.outputVerifiedOnClose(in -> {
      TestUtil.assertEquals(value, in.readInt());
      assertEquals("stream reports end", -1, in.read());
    });
    try (DataOutputStream dos = new DataOutputStream(out)) {
      dos.writeInt(value);
    }
  }

  @Test
  public void testUTFNonEmpty() throws Exception {
    OutputStream out = TestUtil.outputVerifiedOnClose(in -> {
      assertEquals("string length in bytes", 7, in.readInt());
      assertArrayEquals("string contents",
          new byte[]{97, 49, -61, -74, -30, -126, -84},
          TestUtil.readBytes(in, 7));
      assertEquals("stream reports end", -1, in.read());
    });
    try (DataOutputStream dos = new DataOutputStream(out)) {
      dos.writeUTF("a1ö€");
    }
  }

  @Test
  public void testUTFEmpty() throws Exception {
    OutputStream out = TestUtil.outputVerifiedOnClose(in -> {
      assertEquals("string length in bytes", 0, in.readInt());
      assertEquals("stream reports end", -1, in.read());
    });
    try (DataOutputStream dos = new DataOutputStream(out)) {
      dos.writeUTF("");
    }
  }
}
