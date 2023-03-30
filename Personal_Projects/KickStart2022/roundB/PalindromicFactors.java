package roundB;

import java.util.*;
import java.io.*;

public class PalindromicFactors {
	public static void main(String[] args) {
		Scanner inputLines = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
		int numCases = inputLines.nextInt(); inputLines.nextLine();
		long[] inNums = new long[numCases]; long maxNum = 0;
		for (int caseNum = 1; caseNum <= numCases; caseNum++) { // gets all test cases #s and stores in array and their max
			
			long nextVal = inputLines.nextLong(); inputLines.nextLine();
			inNums[caseNum - 1] = nextVal;
			if (nextVal > maxNum) {maxNum = nextVal;}
		} inputLines.close();
		
		long[] primes = primesUpThru(maxNum);
		
		List<List<Long>> primeFactLists = new ArrayList<>();
		for (int c = 0; c < numCases; c++) {
			
			long num = inNums[c];
			List<Long> primeFacts = new ArrayList<>();
			int pInd = 0;
			while (num != 1) {
				if (num % primes[pInd] == 0) {
					primeFacts.add(primes[pInd]); num /= primes[pInd];
				} else {
					pInd++;
				}
			}
			primeFactLists.add(primeFacts);
		}
		
		for (int c = 0; c < numCases; c++) {
			long[] primeFacts = primeFactLists.get(c).stream().mapToLong(i -> i).toArray();
			int pfLen = primeFacts.length;
			String facts = ":1:" + inNums[c] + ":";
			
			for (int i1 = 0; i1 < pfLen; i1++) {
				for (int i2 = i1 + 1; i2 < pfLen; i2++) {
					long fact = primeFacts[i1] * primeFacts[i2];
					if (!facts.contains(":" + fact + ":")) {facts = facts + fact + ":";}
				}
			}
			
			String[] fArr = facts.split(":");
		}
	}
	
	// Uses Sieve of E. to compute
	private static long[] primesUpThru(long limit) {
		List<String> mults = new ArrayList<String>();
		List<Long> primes = new ArrayList<Long>(); primes.add(2l); // 2 is only even prime
		
		primeLoop: for (long i = 3; i <= limit; i += 2) { // 3, 5, 7, 9, 11, etc.
			
			// If one of the multiple lines contains the number, not prime
			for (String multLine : mults) {if (multLine.contains(":" + i + ":")) {continue primeLoop;}}
			
			// At this point, i is prime
			// 2 next steps:
			
			// 1. Add i to the list of primes
			primes.add(i);
			
			// 2. Add i and its multiples to mults using colon-boxing
			// :i:2i:3i:4i:5i:...:
			String multLine = ":";
			for (long j = i; j <= limit; j += i) {multLine = multLine + j + ":";}
			mults.add(multLine);
		}
		
		return primes.stream().mapToLong(i -> i).toArray();
	}
}