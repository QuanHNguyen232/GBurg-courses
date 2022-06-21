import java.util.Arrays;

public class DPFun {

	public static long fib(int n) {
		if (n == 0) {
			return 0L;
		}
		else if (n == 1) {
			return 1L;
		}
		else {
			return fib(n - 1) + fib(n - 2);
		}
	}

	public static long fibApprox(int n) {
		final double ROOT5 = Math.sqrt(5), PHI = (ROOT5 + 1) / 2; 
		return (long) Math.round((Math.pow(PHI, n) - Math.pow(-PHI, -n)) / ROOT5);
	}

	public static final int SIZE = 93;
	
	public static Long[] fib = new Long[SIZE];
	
	public static long fibDP(int n) {
		if (fib[n] == null) {
			if (n == 0) {
				fib[0] = 0L;
			}
			else if (n == 1) {
				fib[1] = 1L;
			}
			else {
				fib[n] = fibDP(n - 1) + fibDP(n - 2);
			}
		}
		return fib[n];
	}
	
	
	public static final int WIN = 1, LOSS = -1, MAX = 5; 
	
	public static int nimSubtractionGame(int n) {
		if (n == 0) {
			return LOSS;
		}
		else {
			int maxPieces = Math.min(n, MAX);
			boolean canWin = false;
			for (int pieces = 1; pieces <= maxPieces && !canWin; pieces++) {
				if (nimSubtractionGame(n - pieces) ==  LOSS) {
					canWin = true;
				}
			}
			return canWin ? WIN : LOSS;
		}
	}
	
	public static int[] nim = new int[SIZE];
	
	public static int nimSubtractionGameDP(int n) {
		if (nim[n] == 0) {
			if (n == 0) {
				nim[0] = LOSS;
			}
			else {
				int maxPieces = Math.min(n, MAX);
				boolean canWin = false;
				for (int pieces = 1; pieces <= maxPieces && !canWin; pieces++) {
					if (nimSubtractionGameDP(n - pieces) ==  LOSS) {
						canWin = true;
					}
				}
				nim[n] = canWin ? WIN : LOSS;
			}
		}
		return nim[n];
	}
	
	public static void main(String[] args) {
//		for (int i = 0; i < SIZE; i++) {
//			System.out.printf("fib(%d) = %d\n", i, fib(i));
//		}
//		System.out.println();
		
//		for (int i = 0; i < SIZE; i++) {
//			System.out.printf("fibApprox(%d) = %d\n", i, fibApprox(i));
//		}
//		System.out.println();
		
//		for (int i = 0; i < SIZE; i++) {
//			System.out.printf("fibDP(%d) = %d\n", i, fibDP(i));
//		}
//		System.out.println();
		
//		for (int i = 0; i < SIZE; i++) {
//			System.out.printf("%d pieces --> %s\n", i, nimSubtractionGame(i) == WIN ? "win" : "loss");
//		}
//		System.out.println();
		
		for (int i = 0; i < SIZE; i++) {
			System.out.printf("%d pieces --> %s\n", i, nimSubtractionGameDP(i) == WIN ? "win" : "loss");
		}
		System.out.println();
		
	}

}
