
public class draft {

	public static void main(String[] args) {
		int x = 3;
		// 1000
		for (int i = 0; i < 8; i++) {
			int y = (x >>> i & 1);
			System.out.print(y);			
		}
		System.out.println();
		int mask = 128;
		for (int i = 0; i < 8; i++) {
			System.out.print(x & mask);
			mask = mask >> 1;
		}
		
		System.out.println();
		
		System.out.println(24 >> 0110);
		
		System.out.println(Integer.toBinaryString(255));
		System.out.println(Integer.toBinaryString(0>>1));
	}

}
