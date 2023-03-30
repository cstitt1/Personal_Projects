package prob027;

import java.math.BigInteger;

public class QuadPri {

	public static void main(String[] args) {
		int maxN = 0, maxProd = 0, maxA = 0, maxB = 0;
		
		for (int b = 2; b <= 1000; b++) {
			for (int a = 2 - (b+1); a < 1000; a++) {
				int n = nTest(a,b);
				
				if (n > maxN) {
					maxN = n;
					maxProd = a*b;
					maxA = a;
					maxB = b;
				}
			}
			
			if (b%2 == 1) {b++;}
		}
		
		System.out.println(maxN + ": "+maxA+" * "+maxB+" = "+maxProd);
	}
	
	private static int nTest(int a, int b) {
		//returns max n for which 0 through n of n^2+an+b is prime
		BigInteger quad;
		for (int n = 0; true; n++) {
			//n^2+an+b = n(n+a)+b
			quad = new BigInteger(""+n);
			quad = quad.add(new BigInteger(""+a));
			quad = quad.multiply(new BigInteger(""+n));
			quad = quad.add(new BigInteger(""+b));
			
			if (!quad.isProbablePrime(5)) {
				return n-1;
			}
		}
	}
}