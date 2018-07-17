package main.bureauActivation;

import java.util.Random;

public class RandomHashFactory {

  private static final char[] symbols = new char[36];
  private final char[] buffer;
  static {
    for (int idx = 0; idx < 10; ++idx)
      symbols[idx] = (char) ('0' + idx);
    for (int idx = 10; idx < 36; ++idx)
      symbols[idx] = (char) ('a' + idx - 10);
  }

  private final Random random = new Random();

  public RandomHashFactory(int length) {
    if (length < 1)
      throw new IllegalArgumentException("length < 1: " + length);
    buffer = new char[length];
  }

  public String nextString() {
    for (int idx = 0; idx < buffer.length; ++idx)
      buffer[idx] = symbols[random.nextInt(symbols.length)];
    return new String(buffer);
  }

}