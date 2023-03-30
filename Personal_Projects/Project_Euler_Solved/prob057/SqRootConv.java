package prob057;

import java.math.BigInteger;

public class SqRootConv {
	public static void main(String[] args) {
		BigInteger num = new BigInteger(""+1), den = new BigInteger(""+1), t = new BigInteger(""+1);
		int count = 0;
		
		for (int i = 1; i <= 1000; i++) {
			den = num.add(den);
			num = num.add(t.multiply(new BigInteger(""+2)));
			t = den.add(new BigInteger(""+0));
			
			if (num.toString().length() > den.toString().length()) {
				count++;
			}
		}
		
		System.out.println(count);
	}
}