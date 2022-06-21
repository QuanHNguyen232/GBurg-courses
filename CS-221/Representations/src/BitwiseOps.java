
public class BitwiseOps {

	public static void main(String[] args) {
		String name = null;
		// replace with '&' to force both conditions
		if (name != null && name.length() > 1) {
			System.out.println(name.length());
		}
		System.out.println("Done");

//		~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Section~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		int n = 6;	// 0110
		int m = 5;	// 0101
//		n & m		// 0100 -> 4 bitwise AND
//		n | m		// 0111 -> 7 bitwise OR
//		n ^ m		// 0011 -> 3 bitwise XOR (different but not same)
//		~n			1111001 -> -7 bitwise NOT
		
		System.out.println(n & m);
		System.out.println(n | m);
		System.out.println(n ^ m);
		System.out.println(~n);
		
		
		// BITMAPPED Flags
		final int FLAG_NONE = 0;
		final int FLAG_ACTIVE = 1;	// 0001
		final int FLAG_VISIBLE = 2;	// 0010
		final int FLAG_REVERSE = 4;	// 0100
		final int FLAG_GREEN = 8;	// 1000
		
		int flags = FLAG_ACTIVE | FLAG_GREEN | FLAG_VISIBLE;	// bitwise OR works at a '+'
		// 0001
		// 1000
		// 0010
		// 1011	result
		
		System.out.println(flags);

		// 1011
		// 0001
		// 0001	result
		if ((flags & FLAG_ACTIVE) != 0) {
			System.out.println("Active");
		}
		

		// 1011
		// 0100
		// 0000	result
		if ((flags & FLAG_REVERSE) != 0) {
			System.out.println("REVERSE");
		}
		
//		~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Section~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// shift operators
		int t = 1;
		
		System.out.println(t);
		// left shift operator (shift bits left) (fill in with 0)
		// 0001
		// 0010	result (shift by one)
		System.out.println(t << 1);	// = multiply by 2
		
		
		// right shift (fill in with 1)
		System.out.println(t >> 1);	// = integer division
		
		// ...00000010
		// ...11111101 + 1
		// ...11111110 -> -2
		t = -2;
		// ...11111111 -> -1
		System.out.println(t >> 1);
		
		// >> arithmetic shift right (asr)
		// fill in the left side with the sign
		
		// >>> logical shift right: fill with 0
		System.out.println(t >>> 1);
		
		
		int x = -5;
		
		// use shift and bitwise operator to print binary of x, one bit at a time
		/*
		 * 		xxxx
		 * 	&	0001
		 * 
		 * 		xxxx
		 *  &	0010
		 * 
		 */
		for (int i = 31; i >= 0; i--) {
			int mask = 1 << i;	// move a one to the bit I'm working on
			int bit = (x & mask) >>> i;
			System.out.print(bit);
		}
		System.out.println();
		
		// way 2
		int mask = 1 << 31;
		for (int i = 31; i >= 0; i--) {
			// move a one to the bit I'm working on
			int bit = (x & mask) >>> i;
			mask = mask >>> 1;
			System.out.print(bit);
		}
		System.out.println();
		
		
		int pixel = 0x00FF00AA;	// alpha, red, green, blue (each 1 one byte)
		int blue = (pixel & 0xFF);	// 0xFF == 0x000000FF ????
		int green = (pixel & 0xFF00) >>> 8;	// (pixel >>> 8) & 0xFF
		int red = (pixel & 0xFF0000) >>> 16;
		int alpha = (pixel & 0xFF000000) >>> 24;	// alpha has problem with (-) value => use logical
		
		// for blue
		// 0x00FF00AA	...01 1010 1010	1AA
		// 0x000000FF	...00 1111 1111 0FF
		// &			...00 1010 1010 0AA
		
		// for green
		// 0x00FF00AA >> 8 -> 0x0000FF00
		
		// & 0xFF to keep to last 2 bytes (16 bits)
		
		System.out.printf("a=%H, r=%H, g=%H, b=%H\n", alpha, red, green, blue);
		
		
//		~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Section~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// double precision floating point values
		double y = 2;
		double y2 = Math.sqrt(y);
		
		System.out.println(y2*y2);
		System.out.println(Math.sqrt(-2));
		
		double z = Double.POSITIVE_INFINITY;
		System.out.println(z);
		
		
		
		
	}
}
