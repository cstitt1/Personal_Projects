package roundQualif;

import java.util.*;
import java.io.*;

public class D3Printing {
	
	public static void main(String[] args) {
		Scanner inputLines = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
		int numCases = inputLines.nextInt(); inputLines.nextLine();
		for (int caseNum = 0; caseNum < numCases; caseNum++) {
			int[] PrinterA = new int[] {inputLines.nextInt(),inputLines.nextInt(),inputLines.nextInt(),inputLines.nextInt()};
			inputLines.nextLine();
			
			int[] PrinterB = new int[] {inputLines.nextInt(),inputLines.nextInt(),inputLines.nextInt(),inputLines.nextInt()};
			inputLines.nextLine();
			
			int[] PrinterC = new int[] {inputLines.nextInt(),inputLines.nextInt(),inputLines.nextInt(),inputLines.nextInt()};
			inputLines.nextLine();
			
			int inkTotal = 0; int[] DPrint = new int[4];
			for (int color = 0; color < PrinterA.length; color++) {
				int min = PrinterA[color];
				if (PrinterB[color] < min) {min = PrinterB[color];}
				if (PrinterC[color] < min) {min = PrinterC[color];}
				
				if (inkTotal + min > 1000000) {min = 1000000 - inkTotal;}
				inkTotal += min; DPrint[color] = min;
			}
			
			if (inkTotal == 1000000) {
				System.out.print("Case #" + (caseNum+1) + ":");
				System.out.print(" " + DPrint[0]);
				System.out.print(" " + DPrint[1]);
				System.out.print(" " + DPrint[2]);
				System.out.println(" " + DPrint[3]);
			} else {
				System.out.println("Case #" + (caseNum+1) + ": IMPOSSIBLE");
			}
		}
		
		inputLines.close();
	}
}