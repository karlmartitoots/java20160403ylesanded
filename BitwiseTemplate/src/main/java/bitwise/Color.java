package bitwise;

/**
 * Holds a color value with 8-bit red, green, blue and alpha components.
 * The components are packed into a single 32-bit integer, where the
 * component order from left to right is: red, green, blue, alpha.
 */
public class Color {

  private int rgba;

  public int getPacked() {
    return rgba;
  }

  public void setPacked(int rgba) {
    this.rgba = rgba;
  }

  public int getRed() {
    return (rgba & (0xFF << 24)) >>> 24;
  }

  public int getGreen() {
    return (rgba & (0xFF << 16)) >>> 16;
  }

  public int getBlue() {
    return (rgba & (0xFF << 8)) >>> 8;
  }

  public int getAlpha() {
    return rgba & 0xFF;
  }

  public void setRed(int value) {
    assertBounds(value);
    rgba = (value << 24) | (rgba & 0x00FFFFFF);
  }

  public void setGreen(int value) {
    assertBounds(value);
    rgba = (value << 16) | (rgba & 0xFF00FFFF);
  }

  public void setBlue(int value) {
    assertBounds(value);
    rgba = (value << 8) | (rgba & 0xFFFF00FF);
  }

  public void setAlpha(int value) {
    assertBounds(value);
    rgba = value | (rgba & 0xFFFFFF00);
  }

  private void assertBounds(int value) {
    if (value < 0 || value > 255)
      throw new IllegalArgumentException(String.valueOf(value));
  }
}
