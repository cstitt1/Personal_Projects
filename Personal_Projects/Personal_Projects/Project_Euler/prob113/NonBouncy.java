package prob113;

import java.math.BigInteger;

public class NonBouncy {

	public static void main(String[] args) {
		BigInteger b = new BigInteger(""+10);
		b = b.pow(100);
		BigInteger ctr = new BigInteger(""+1);
		
		long count = 0; boolean inc = true, dec = true;
		while (ctr.compareTo(b) < 0) {
			inc = true; dec = true;
			char[] str = ctr.toString().toCharArray();
			for (int i = 0; i < str.length-1 && (inc || dec); i++) {
				if (inc && str[i] < str[i+1]) {
					inc = false;
				}
				
				if (dec && str[i] > str[i+1]) {
					dec = false;
				}
			}
			
			if (inc || dec) {
				count++;
			}
		}
		
		System.out.println(count);
	}
}