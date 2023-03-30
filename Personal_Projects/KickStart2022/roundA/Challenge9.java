package roundA;

import java.io.*;
import java.util.*;

public class Challenge9 {
	public static void main(String[] args) {
		Scanner inputLines = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
		int numCases = inputLines.nextInt(); inputLines.nextLine();
		caseLoop: for (int caseNum = 1; caseNum <= numCases; caseNum++) {
			
			String numStr = inputLines.nextLine();
			char[] numCharr = numStr.toCharArray();
			
			/*
			 * Math basis:
			 * 			a number is divisible by 9 if the digits sum to 9 (or 0 mod 9)
			 * 			Thus, if you know the sum if x, you can add a digit 9-x to make it divisible
			 */
			int modSum = 0; int addDigit;
			for (char numCh : numCharr) {
				modSum += (numCh - '0'); //'0' = 0, '1' = 1, etc
				if (modSum > 8) {modSum -= 9;}
			} addDigit = (9 - modSum)%9;
			
			/*
			 *  To add the new digit into a number to minimize the new number:
			 *  		-- If it is 1, put it in the very front
			 *  		-- If it is 0, put it just after the first digit (no leading 0's)
			 *  		-- Otherwise, put before 1st digit bigger than it
			 */
			
			String z = "Case #" + caseNum + ": ";
			if (addDigit == 1) {System.out.println(z + addDigit + numStr); continue caseLoop;}
			if (addDigit == 0) {System.out.println(z + numCharr[0] + addDigit + numStr.substring(1)); continue caseLoop;}
			
			int placeInd = 0;
			for (char numCh : numCharr) {
				if (numCh - '0' > addDigit) {
					System.out.println(z + numStr.substring(0, placeInd) + addDigit + numStr.substring(placeInd)); continue caseLoop;
				} else {
					placeInd++;
				}
			}
			
			System.out.println(z + numStr + addDigit);
		}
		
		inputLines.close();
	}
}