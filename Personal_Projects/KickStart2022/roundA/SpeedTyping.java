package roundA;

import java.util.*;
import java.io.*;

public class SpeedTyping {
	
	public static void main(String[] args) {
		Scanner inputLines = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
		int numCases = inputLines.nextInt(); inputLines.nextLine();
		caseLoop: for (int caseNum = 1; caseNum <= numCases; caseNum++) {
			
			// Getting words I and P + their char arrays and lengths
			String wordI = inputLines.nextLine(), wordP = inputLines.nextLine();
			char[] charrI = wordI.toCharArray(), charrP = wordP.toCharArray();
			int lenI = charrI.length, lenP = charrP.length;
			
			// Get letter counts for each word (number of 'a', 'b', 'A', etc)
			// Return IMPOSSIBLE if P doesn't have the letters I has
			// Also count the number of extra letters
			int[] lcI = new int[52], lcP = new int[52];
			String lettMap = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
			
			for (int i = 0; i < lenI; i++) {lcI[lettMap.indexOf(charrI[i])]++;}
			for (int p = 0; p < lenP; p++) {lcP[lettMap.indexOf(charrP[p])]++;}
			
			int extraCount = 0; int[] lcExtra = new int[52];
			for (int lc = 0; lc < 52; lc++) {
				int diff = lcP[lc] - lcI[lc];
				if (diff < 0) {System.out.println("Case #" + caseNum + ": IMPOSSIBLE"); continue caseLoop;}
				extraCount += diff; // lcI <= lcP given above
				lcExtra[lc] = diff;
			}
			
			// From here, we know P is I with extra letters but I letters not necessarily in correct order
			
			// We do know how many extras there are of each letter, however
			// So next, delete extra letters
			String iPrime = "";
			for (int p = 0; p < lenP; p++) {
				char chP = charrP[p];
				if (lcExtra[lettMap.indexOf(chP)] == 0) {iPrime += chP;}
				else {lcExtra[lettMap.indexOf(chP)]--;}
			}
			
			// Finally, check if I and I' are the same
			if (wordI.equals(iPrime)) {System.out.println("Case #" + caseNum + ": " + extraCount);}
			else {System.out.println("Case #" + caseNum + ": IMPOSSIBLE");}
		}
		
		inputLines.close();
	}
}