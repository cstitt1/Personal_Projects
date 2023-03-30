package round1A;

import java.io.*;
import java.util.*;

public class DoubleOr1Thing {
	
	public static void main(String[] args) {
		Scanner inputLines = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
		int numCases = inputLines.nextInt(); inputLines.nextLine();
		for (int caseNum = 1; caseNum <= numCases; caseNum++) {
			
			String word = inputLines.nextLine();
			
			char[] wordArr = word.toCharArray(); int wordLen = wordArr.length;
			String uniOnly = ""; char lastUni = 0;
			
			for (int wInd = 0; wInd < wordLen; wInd++) {
				char chAt = wordArr[wInd];
				if (chAt != lastUni) {
					uniOnly += ("" + chAt);
					lastUni = chAt;
				}
			}
			
			char[] uniArr = uniOnly.toCharArray(); int uniLen = uniArr.length;
			int[] repArr = new int[uniLen];
			
			int uInd = 0;
			for (int wInd = 0; wInd < wordLen; wInd++) {
				char wCh = wordArr[wInd], uCh = uniArr[uInd];
				if (wCh != uCh) {uInd++;}
				repArr[uInd]++;
			}
			
			for (uInd = 0; uInd < uniLen - 1; uInd++) {
				char curr = uniArr[uInd], next = uniArr[uInd + 1];
				if (curr < next) {repArr[uInd] *= 2;}
			}
			
			String alpha = "";
			for (int rInd = 0; rInd < repArr.length; rInd++) {
				int repNum = repArr[rInd];
				for (uInd = 0; uInd < repNum; uInd++) {
					alpha += uniArr[rInd];
				}
			}
			
			System.out.println("Case #" + caseNum + ": " + alpha);
		}
		
		inputLines.close();
	}
}