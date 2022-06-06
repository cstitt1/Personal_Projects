package prob066;

public class DioEq {
	public static void main(String[] args) {
		// x^2 - D*y^2 = 1
		// THUS: x^2 = D*y^2 + 1
		//       x^2 - 1 / D = y^2
		//       x^2-1/D MUST BE A PERFECT SQUARE
		// HOWEVER: x^2 is also known to be perfect squares
		// THUS: PS - 1 / D MUST BE PS
		// HOWEVER: x^2 and y^2 must be integers, thus D must divide x^2-1
		// THUS: D MUST DIVIDE PS - 1 AND QUOTIENT MUST BE PS
		
		long maxX2 = -1, corrD = -1; 
		
		for (long D = 2; D <= 1000; D++) {
			if (isSqFAST(D)) {continue;}
			
			System.out.print("D: " + D + " -- ");
			
			long sqUP = 1;
			for (long mulD = 1; mulD > 0; mulD+=sqUP) {
				sqUP+=2;
				long prd = D * mulD;
				if (isSqFAST(prd + 1)) {
					if (maxX2 < prd + 1) {
						maxX2 = prd + 1; corrD = D;
					}
					System.out.println("x2 is "+(prd+1));
					break;
				}
			}
		}
		
		System.out.println(corrD);
	}
	
	/*
	 * static long goodMask; { for (int i = 0; i < 64; ++i) goodMask |=
	 * Long.MIN_VALUE >>> (i*i); }
	 */
	
	/*private static boolean isSquare(long num) {
		if (num < 0) {return false;}
		if (num == 0 || num == 1) {return true;}
		
		for (long i = 2; i < num/2; i++) {
			long sq = i * i;
			if (sq > num) {
				return false;
			} else if (sq == num) {
				return true;
			}
		}
		
		return false;
	}*/
	
	private static boolean isSqFAST(long x) {
		long goodMask = 0;
		for (int i = 0; i < 64; ++i) goodMask |= Long.MIN_VALUE >>> (i*i);
		long helpme = goodMask << x;
		if (helpme >= 0) return false;
		final int numberOfTrailingZeros = Long.numberOfTrailingZeros(x);
		if ((numberOfTrailingZeros & 1) != 0) return false;
		x >>= numberOfTrailingZeros;
		if ((x&7) != 1 | x <= 0) return x == 0;
		final long sqrt = (long) Math.sqrt(x);
		return sqrt*sqrt == x;
	}
}