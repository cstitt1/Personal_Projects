package prob063;

import java.math.BigInteger;

public class PowDigCount {
	public static void main(String[] args) {
		int count = 0, numOf = -1;
		for (int i = 1; numOf != 0; i++) {
			numOf = 0; boolean over = false;
			for (int n = 1; !over; n++) {
				BigInteger num = new BigInteger(""+n);
				num = num.pow(i);
				if (num.toString().length() == i) {
					numOf++;
				} else if (num.toString().length() > i) {
					over = true;
				}
			}
			
			count += numOf;
		}
		System.out.println(count);
	}	
}