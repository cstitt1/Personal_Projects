package roundD;

import java.util.*;
import java.io.*;

public class ImageLabeler {
	
	public static void main(String[] args) {
		Scanner inputLines = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
		int numCases = inputLines.nextInt(); inputLines.nextLine();
		for (int caseNum = 1; caseNum <= numCases; caseNum++) {
			int valN = inputLines.nextInt(), valM = inputLines.nextInt(); inputLines.nextLine();
			
			int[] valsA = new int[valN];
			for (int a = 0; a < valN; a++) {valsA[a] = inputLines.nextInt();} inputLines.nextLine();
			
			valsA = mergeSort(valsA);
			
			int sum = 0; int end = valN - 1;
			for (int a = valN - 1; valM-- > 1; a--) {
				sum += valsA[a];
				end--;
			}
			
			// a,b,c,d,e,f
			// 0 1 2 3 4 5
			// end = 5 --> mid = 2 & 3
			// end = 4 --> mid = 2
			// end = 3 --> mid = 1 & 2
			
			int mid1 = valsA[end / 2]; int mid2 = mid1;
			if (end % 2 == 1) {mid2 = valsA[(end + 1) / 2];}
			
			int avgSum = mid1 + mid2;
			String printSum = "" + (sum + (avgSum / 2));
			if (avgSum % 2 == 1) {printSum += ".5";}
			
			System.out.println("Case #" + caseNum + ": " + printSum);
		}
		
		inputLines.close();
	}
	
	private static int[] mergeSort(int[] arr) {
		int arr_size = arr.length;
		if (arr_size <= 1) {return arr;}
		
		int ind_mid = arr_size/2;
		
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