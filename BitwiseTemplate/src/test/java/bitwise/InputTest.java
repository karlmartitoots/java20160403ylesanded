package bitwise;

import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.assertEquals;


public class InputTest {

  @Test
  public void testPositiveInt() throws Exception {
    int value = 0x0F1F2F3F;
    InputStream stream = TestUtil.prepare(out -> out.writeInt(value));
    try (DataInputStream dis = new DataInputStream(stream)) {
      TestUtil.assertEquals(value, dis.readInt());
    }
  }

  @Test
  public void testNegativeInt() throws Exception {
    int value = 0xF0F1F2F3;
    InputStream stream = TestUtil.prepare(out -> out.writeInt(value));
    try (DataInputStream dis = new DataInputStream(stream)) {
      TestUtil.assertEquals(value, dis.readInt());
    }
  }

  @Test
  public void testUTFNonEmpty() throws Exception {
    InputStream stream = TestUtil.prepare(out -> {
      out.writeInt(7);
      out.write(new byte[]{97, 49, -61, -74, -30, -126, -84});
      out.write(new byte[]{'w', 't', 'f'}); // trailing garbage
    });
    try (DataInputStream dis = new DataInputStream(stream)) {
      assertEquals("a1ö€", dis.readUTF());
    }
  }

  @Test
  public void testUTFEmpty() throws Exception {
    InputStream stream = TestUtil.prepare(out -> out.writeInt(0));
    try (DataInputStream dis = new DataInputStream(stream)) {
      assertEquals("<empty string>", "", dis.readUTF());
    }
  }
}
