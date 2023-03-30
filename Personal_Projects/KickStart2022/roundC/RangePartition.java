package roundC;

import java.io.*;
import java.util.*;

public class RangePartition {
	
	public static void main(String[] args) {
		Scanner inputLines = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
		int numCases = inputLines.nextInt(); inputLines.nextLine();
		for (int caseNum = 1; caseNum <= numCases; caseNum++) {
			
			int valN = inputLines.nextInt(), valX = inputLines.nextInt(), valY = inputLines.nextInt();
			
			int summN = 0; // summ( i, 1 to N) = (N)(N + 1)/2
			if (valN % 2 == 0) {summN = (valN / 2) * (valN + 1);}
			else {summN = (valN + 1) / 2; summN *= valN;}
			
			if (summN % (valX + valY) != 0) {System.out.println("Case #" + caseNum + ": IMPOSSIBLE"); continue;}
			
			int summA = (summN / (valX + valY)) * valX;
			
			String numsA = ""; int countA = 0;
			for (int i = valN; i > -1; i--) {
				summA -= i;
				if (summA < 0) {summA += i; continue;}
				
				numsA = numsA + ":" + i; countA++;
				if (summA == 0) {break;}
			}
			
			System.out.println("Case #" + caseNum + ": POSSIBLE");
			System.out.println(countA);
			System.out.println(numsA.replaceAll(":", " ").substring(1));
		}
		
		inputLines.close();
	}
}