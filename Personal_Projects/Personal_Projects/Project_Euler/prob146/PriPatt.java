package prob146;

import java.math.BigInteger;

public class PriPatt {

	public static void main(String[] args) {
		long sum = 0/*1242490*/, start = 7/*1000000*/, lim = 150000000;
		BigInteger limBI = new BigInteger(""+lim).pow(2);
		BigInteger[] pris = new BigInteger[6]; pris[0] = new BigInteger(""+start).pow(2).nextProbablePrime();
		
		for (int i = 1; i < 6; i++) {
			pris[i] = pris[i-1].nextProbablePrime();
		}
		
		while (pris[0].compareTo(limBI) < 0) {
			long rt = Math.round(Math.sqrt(pris[0].doubleValue()-1));
			BigInteger sq = new BigInteger(""+rt).pow(2);
			BigInteger p1 = new BigInteger(""+1), p3 = new BigInteger(""+3), p7 = new BigInteger(""+7);
			BigInteger p9 = new BigInteger(""+9), p13 = new BigInteger(""+13), p27 = new BigInteger(""+27);
			if (sq.add(p1).equals(pris[0]) && sq.add(p3).equals(pris[1]) && sq.add(p7).equals(pris[2])) {
				if (sq.add(p9).equals(pris[3]) && sq.add(p13).equals(pris[4]) && sq.add(p27).equals(pris[5])) {
					sum += rt;
					System.out.println(pris[0].toString() + ": " + sum);
				}
			}
			
			for (int i = 0; i < 5; i++) {
				pris[i] = pris[i+1];
			}
			pris[5] = pris[4].nextProbablePrime();
		}
		
		System.out.println(sum);
	}
}