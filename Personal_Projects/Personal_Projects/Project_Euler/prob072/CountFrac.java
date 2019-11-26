package prob072;

import java.math.BigInteger;

public class CountFrac {
	public static void main(String[] args) {
		long count = 1; // 1/2
		
		for (int d = 3; d <= 1000000; d++) {
			BigInteger den = new BigInteger(""+d);
			for (int n = 1; n < d; n++) {
				boolean nah = false;
				
				for (int div = 2; div <= n && !nah; div++) {
					if (n % div == 0 && d % div == 0) {
						nah = true;
					}
				}
				
				if (!nah) {
					count++;
				}
			}
		}
		
		System.out.println(count);
	}
}