package prob65;

public class ConverOfE {

	public static void main(String[] args) {
		//Sequence for e is [2;1,2,1,1,4,1,1,6,1,...] = [2;(1,2k,1)]
		long k = 1;
		long[] eSeq = new long[100];
		
		for (long i = 1; i <= 100; i++) {
			if (i < 10) {
				System.out.print("  " + i + " ");
			} else if (i < 100) {
				System.out.print(" " + i + " ");
			} else {
				System.out.println(i);
			}
		}
		
		System.out.print("  2 "); eSeq[0] = 2;
		for (long i = 2; i <= 100; i++) {
			if ((i-2)%3 == 0 || (i-1)%3 == 0) {
				System.out.print("  1 "); eSeq[(int) (i-1)] = 1;
				continue;
			}
			
			//2k
			long seqNum = 2 * k; k++; eSeq[(int) (i-1)] = seqNum;
			
			if (seqNum < 10) {
				System.out.print("  " + seqNum + " ");
			} else if (seqNum < 100) {
				System.out.print(" " + seqNum + " ");
			} else {
				System.out.println("ERR");
			}
		}
		System.out.println("\n");
		
		long start = 99; // 99
		long numer = 1, denom = eSeq[(int) start];
		for (long i = start-1; i >= 0; i--) {
			numer = (eSeq[(int) i] * denom) + numer; // Add
			long[] simpli = simplify(numer, denom);
			numer = simpli[1]; denom = simpli[0]; // Simplify and Flip
		}
		
		System.out.println("\n" + denom + " / " + numer);
	}
	
	private static long[] simplify(long numer, long denom) {
		if (numer == 1 || denom == 1) {
			return new long[] {numer, denom};
		}
		
		long min = numer<denom?numer:denom;
		if (numer%min == 0 && denom%min == 0) {numer /= min; denom /= min;}
		
		for (long i = (min+1)/2; i > 0; i--) {
			if (numer%i == 0 && denom%i == 0) {numer /= i; denom /= i;}
		}
		
		return new long[] {numer, denom};
	}
}