package roundB;

import java.io.*;
import java.util.*;

public class InfinityArea {
	
	public static void main(String[] args) {
		
		Scanner inputLines = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
		int numCases = inputLines.nextInt(); inputLines.nextLine();
		for (int caseNum = 1; caseNum <= numCases; caseNum++) {
			
			long valR = inputLines.nextLong();
			long valA = inputLines.nextLong();
			long valB = inputLines.nextLong();
			inputLines.nextLine();
			
			long radSum = valR * valR;
			long currR = valR;
			while (true) {
				
				currR = currR * valA;
				radSum += (currR * currR);
				
				currR = currR / valB;
				if (currR == 0) {break;}
				radSum += (currR * currR);
			}
			
			System.out.println("Case #" + caseNum + ": " + (radSum * Math.PI));
		}
		
		inputLines.close();
	}
}