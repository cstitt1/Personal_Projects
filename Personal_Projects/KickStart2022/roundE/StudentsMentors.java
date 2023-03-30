package roundE;

import java.io.*;
import java.util.*;

public class StudentsMentors {
	
	public static void main(String[] args) {
		Scanner inputLines = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
		int numCases = inputLines.nextInt(); inputLines.nextLine();
		for (int caseNum = 1; caseNum <= numCases; caseNum++) {
			
			int numStu = inputLines.nextInt(); inputLines.nextLine();
			int[] stuVals = new int[numStu];//, sortVals = new int[numStu];
			
			for (int s = 0; s < numStu; s++) {
				int stuVal = inputLines.nextInt();
				stuVals[s] = stuVal;// sortVals[s] = stuVal;
			} inputLines.nextLine();
			
			//sortVals = mergeSort(sortVals);
			
			String menList = "";
			for (int i = 0; i < numStu; i++) {
				
				int bestMen = -1;
				int dubVal = stuVals[i] * 2;
				
				for (int j = 0; j < numStu; j++) {
					
					if (i == j) {continue;}
					int jVal = stuVals[j];
					
					if (jVal > bestMen && jVal <= dubVal) {bestMen = jVal;}
				}
				
				menList = menList + bestMen + (i + 1 == numStu ? "" : " ");
			}
			
			System.out.println("Case #" + caseNum + ": " + menList);
		}
		inputLines.close();
	}
}