# Bitwise operations

*In digital computer programming, a bitwise operation 
operates on one or more bit patterns or binary numerals 
at the level of their individual bits. It is a fast, 
primitive action directly supported by the processor, 
and is used to manipulate values for comparisons and 
calculations.* - Wikipedia

## Tasks you'll be doing

- convert byte\[4\] -\> int
- convert int -\> byte\[4\]
- pack 4x 1-byte values into a single 4-byte int

## DIY

To really learn bitwise operations, you must use them. 
For this practice session, try to solve the tasks on 
your own, without using StackOverflow, Google or reading 
the sources of Java's built in classes. 

## Types

- byte is 8 bits
- short is 16 bits
- int is 32 bits
- long is 64 bits

All these types are **signed**, which means the value 
can be either negative or positive. Here are some values 
and their binary representations for byte: 

    10000000 = -128 (Byte.MIN_VALUE)
    10000001 = -127
    10000010 = -126
    11111101 = -3
    11111110 = -2
    11111111 = -1
    00000000 = 0
    00000001 = 1
    00000010 = 2
    00000011 = 3
    01111101 = 125
    01111110 = 126
    01111111 = 127 (Byte.MAX_VALUE)

Note that char is a **unsigned** type in java. We won't be using char today.  

### Sign extension

Assigning a negative number to a wider type extends the 
sign, i.e. in the case of negative numbers, the left-most 
bits are set to 1.  

    byte b = -128; // 10000000
    int i = b; // 11111111 11111111 11111111 10000000

### Hex and binary literals

Sometimes it's handy to write down some bit patterns using hex or a binary literal: 

    byte b = 0xFF; 
    byte b = 0b11111111;
    
    byte b = 0x7F; 
    byte b = 0b01111111;
    
    byte b = 0x80; 
    byte b = 0b10000000;
    
    int i = 0xFFFFFFFF;
    int i = 0b11111111111111111111111111111111;

### Byte order (endianness)

To send out an 32-bit integer over the network, you first convert it 
to 4 bytes. In which order should you send out the bytes: starting 
from left/high/"most significant" or starting from the 
right/low/"least significant"? To make things more complicated, 
each protocol and file format uses a different convention. 

- Big-endian: send high bytes first
- Little-endian: send low bytes first

## Bitwise operations

### Bitwise AND

If both bits are 1, the resulting bit is 1. Otherwise, the result is 0.

        0011 1010
    AND 0010 0011
      = 0010 0010

or in java

    int a = 0b00111010; 
    int b = 0b00100011;
    int result = a & b; // 0b00100010

Useful for isolating a single byte from an int: 

    int i = 0xAABBCCDD;
    int lastByte = i & 0xFF; // 0x000000DD

### Bitwise OR

If one of the bits is 1, the resulting bit is 1. Otherwise, the result is 0.

       0011 1010
    OR 0010 0011
     = 0011 1011

or in java

    int a = 0b00111010; 
    int b = 0b00100011;
    int result = a | b; // 0b00111011

Useful for combining values: 

    int i = 0x00AA0000;
    int combined = i | 0xFF; // 0x00AA00FF

### Signed left shift

Shifts all bits by given positions. Right-most bits are set to 0.  

    byte b = 0b00001001;
    byte x = b << 3; // 0b01001000

Usually you'll be shifting bits by a multiple of 8. Useful in combination with OR: 

    int i = 0x00AA0000;
    int combined = i | (0xFF << 8); // 0x00AAFF00

### Signed right shift

Shifts all bits by given positions. Left-most bits are **sign extended**.

    byte b = 0b01001000;
    byte x = b >> 3; // 0b00001001
    
    byte b = 0b10010000;    
    byte x = b >> 3; // 0b11110010 - sign extension

Useful for extracting a byte from wider values: 

    int i = 0xDDCCBBAA;
    int x = i >> 24; 
    // you would expect x to be 0xDD, but due to sign extension it is 0xFFFFFFDD
    int dd = x & 0xFF; // after dropping sign bits it is 0xDD

There also exists a unsigned right shift \>\>\> that doesn't do sign extension.  

# Tasks

Make the tests pass: 

- bitwise.ColorTest
- bitwise.InputTest
- bitwise.OutputTest
