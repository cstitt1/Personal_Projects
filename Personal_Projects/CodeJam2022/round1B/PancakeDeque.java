package round1B;

import java.util.*;
import java.io.*;

public class PancakeDeque {
	
	public static void main(String[] args) {
		Scanner inputLines = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
		int numCases = inputLines.nextInt(); inputLines.nextLine();
		for (int caseNum = 1; caseNum <= numCases; caseNum++) {
			int[] deliLevels = new int[inputLines.nextInt()]; inputLines.nextLine();
			for (int delind = 0; delind < deliLevels.length; delind++) {
				deliLevels[delind] = inputLines.nextInt();
			} inputLines.nextLine();
			
			int deli_min = -1; int pay_count = 0;
			int left_ind = 0, right_ind = deliLevels.length - 1;
			while (left_ind <= right_ind) {
				int deli_left = deliLevels[left_ind], deli_right = deliLevels[right_ind];
				int edge_min = -2;
				
				if (deli_left < deli_right) {
					edge_min = deli_left; left_ind++;
				} else {
					edge_min = deli_right; right_ind--;
				}
				
				if (edge_min >= deli_min) {
					deli_min = edge_min;
					pay_count++;
				}
			}
			
			System.out.println("Case #" + caseNum + ": " + pay_count);
		}
		
		inputLines.close();
	}
}