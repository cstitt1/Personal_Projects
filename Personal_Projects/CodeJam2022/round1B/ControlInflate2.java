package round1B;

import java.util.*;
import java.io.*;

public class ControlInflate2 {
	
	public static void main(String[] args) {
		Scanner inputLines = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
		int numCases = inputLines.nextInt(); inputLines.nextLine();
		for (int caseNum = 1; caseNum <= numCases; caseNum++) {
			
			int numCust = inputLines.nextInt(); int prodPerC = inputLines.nextInt(); inputLines.nextLine();
			//long[][] pump_line = new long[numCust][prodPerC];
			long[] mins = new long[numCust], maxs = new long[numCust];
			long pressSum = 0;
			
			for (int cust = 0; cust < numCust; cust++) {
				
				long custMin = -1, custMax = -1;
				for (int prod = 0; prod < prodPerC; prod++) {
					
					long newVal = inputLines.nextLong();
					//pump_line[cust][prod] = newVal;
					if (custMin == -1 || newVal < custMin) {custMin = newVal;}
					if (custMax == -1 || newVal > custMax) {custMax = newVal;}
				} inputLines.nextLine();
				
				mins[cust] = custMin; maxs[cust] = custMax;
				pressSum += (custMax - custMin);
			}
			
			long dPInc = 0, dPDec = 0;
			long lvInc = 0, lvDec = 0;
			for (int cInd = 0; cInd < numCust; cInd++) {
				
				long[] dPIncNext = new long[2];
				dPIncNext[0] = dPInc + Math.abs(lvInc - mins[cInd]);
				dPIncNext[1] = dPDec + Math.abs(lvDec - mins[cInd]);
				
				long[] dPDecNext = new long[2];
				dPDecNext[0] = dPInc + Math.abs(lvInc - maxs[cInd]);
				dPDecNext[1] = dPDec + Math.abs(lvDec - maxs[cInd]);
				
				dPInc = dPIncNext[0] < dPIncNext[1] ? dPIncNext[0] : dPIncNext[1];
				dPDec = dPDecNext[0] < dPDecNext[1] ? dPDecNext[0] : dPDecNext[1];
				lvInc = maxs[cInd]; lvDec = mins[cInd];
			}
			
			pressSum += (dPInc < dPDec ? dPInc : dPDec);
			
			System.out.println("Case #" + caseNum + ": " + pressSum);
		}
		
		inputLines.close();
	}
}