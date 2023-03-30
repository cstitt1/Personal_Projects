package roundB;

import java.io.*;
import java.util.*;

public class PalinFacts2 {
	
	public static void main(String[] args) {
		Scanner inputLines = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
		int numCases = inputLines.nextInt(); inputLines.nextLine();
		for (int caseNum = 1; caseNum <= numCases; caseNum++) {
			
			long num = inputLines.nextLong();
			long sqrt = Math.round(Math.sqrt(num) + 0.5);
			
			int pCount = 0;
			for (long i = 1; i < sqrt; i++) {
				long f1, f2;
				if (num % i == 0) {f1 = i; f2 = num / i;}
				else {continue;}
				if ( isPalin( (""+f1).toCharArray() ) ) {pCount++;}
				if ( isPalin( (""+f2).toCharArray() ) ) {pCount++;}
			}
			
			System.out.println("Case #" + caseNum + ": " + pCount);
		}
		
		inputLines.close();
	}
	
	private static boolean isPalin(char[] word) {
		//  0    0 1    0 1 2    0 1 2 3    0 1 2 3 4    0 1 2 3 4 5
		// [a], [a,b], [a,b,c], [a,b,c,d], [a,b,c,d,e], [a,b,c,d,e,f]
		//  1     2       3         4           5             6
		int len = word.length;
		int lim = len / 2;
		int end = len - 1;
		
		for (int c = 0; c < lim; c++) {
			if (word[c] != word[end - c]) {return false;}
		}
		
		return true;
	}
}