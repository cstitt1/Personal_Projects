package roundQualif;

import java.util.*;
import java.io.*;

public class D1Mil {
	
	public static void main(String[] args) {
		Scanner inputLines = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
		int numCases = inputLines.nextInt(); inputLines.nextLine();
		for (int caseNum = 1; caseNum <= numCases; caseNum++) {
			int[] sideNums = new int[inputLines.nextInt()]; inputLines.nextLine();
			for (int sideNum = 0; sideNum < sideNums.length; sideNum++) {
				sideNums[sideNum] = inputLines.nextInt();
			} inputLines.nextLine();
			
			sideNums = mergeSort(sideNums);
			
			int straight = 0;
			for (int sideNum = 0; sideNum < sideNums.length; sideNum++) {
				if (straight + 1 <= sideNums[sideNum]) {straight++;}
			}
			
			System.out.println("Case #" + caseNum+ ": " + straight);
		}
		
		inputLines.close();
	}
	
	private static int[] mergeSort(int[] arr) {
		int arr_size = arr.length;
		if (arr_size <= 1) {return arr;}
		
		int ind_mid = arr_size/2; // [0, 1, 2] --> mid = 3/2 = 1; [0, 1, 2, 3] --> mid = 4/2 = 2; [0, 1, 2, 3, 4] --> mid = 5/2 = 2; [0, 1, 2, 3, 4, 5] --> mid = 6/2 = 3
		
		int[] temp_left = new int[ind_mid]; //ind_mid = temp_left.length;
		int[] temp_right = new int[arr_size - ind_mid];
		
		for (int i = 0; i < arr_size; i++) {
			if (i < ind_mid) {temp_left[i] = arr[i];}
			else {temp_right[i - ind_mid] = arr[i];}
		}
		
		temp_left = mergeSort(temp_left); temp_right = mergeSort(temp_right);
		
		int[] merged = new int[arr_size];
		int ind_left = 0, ind_right = 0;
		for (int i = 0; i < arr_size; i++) {
			if (ind_left < ind_mid && ind_right < arr_size - ind_mid) {
				if (temp_left[ind_left] < temp_right[ind_right]) {
					merged[i] = temp_left[ind_left++];
				} else {
					merged[i] = temp_right[ind_right++];
				}
			} else if (ind_left < ind_mid) {
				merged[i] = temp_left[ind_left++];
			} else {
				merged[i] = temp_right[ind_right++];
			}
		}
		
		return merged;
	}
}