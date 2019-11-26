package prob066;

import java.math.BigInteger;

public class DioEq { //x^2 - D*y^2 = 1
	public static void main(String[] args) {
		BigInteger maxD = new BigInteger(""+-1), maxX = new BigInteger(""+-1);
		for (long d = 2; d <= 1000; d++) {
			if (Math.round(Math.sqrt(d))*Math.round(Math.sqrt(d)) == d) {
				continue;
			}
			BigInteger dBI = new BigInteger(""+d);
			
			boolean fnd = false;
			for (long y = 1; !fnd; y++) {
				BigInteger yBI = new BigInteger(""+y);
				BigInteger x2 = (dBI.multiply(yBI.pow(2))).add(BigInteger.ONE);
				/*if (Math.round(Math.sqrt(x2))*Math.round(Math.sqrt(x2)) == x2) {
					fnd = true;
					if (maxX.compareTo(val) < 0) {
						maxD = dBI;
						maxX = Math.round(Math.sqrt(x2));
					}
					System.out.println(d+": "+Math.round(Math.sqrt(x2))+"^2 - "+d+"*"+y+"^2 = 1");
				}*/
			}
		}
		
		System.out.println(maxD);
	}
}