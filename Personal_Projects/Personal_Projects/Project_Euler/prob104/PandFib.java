package prob104;

import java.math.BigInteger;

public class PandFib {

	public static void main(String[] args) {
		BigInteger[] fibs = {new BigInteger(""+1),new BigInteger(""+1)};
		BigInteger fib = fibs[0].add(fibs[1]);
		int k = 3;
		
		while (fib.toString().length() < 9 || !dualP(fib)) {
			fibs[0] = fibs[1];
			fibs[1] = fib;
			fib = fibs[0].add(fibs[1]);
			k++;
			System.out.println(k + " " + fib.toString());
		}
		
		System.out.println(k);
	}
	
	private static boolean dualP(BigInteger num) {
		String str = num.toString();
		String[] ends = {str.substring(0, 9), str.substring(str.length()-9)};
		for (String end : ends) {
			for (int i = 1; i <= 9; i++) {
				if (!end.contains(""+i)) {
					return false;
				}
			}
		}
		
		return true;
	}
}