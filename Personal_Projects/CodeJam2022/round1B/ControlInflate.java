package round1B;
// NOT WORKING
import java.util.*;
import java.io.*;

public class ControlInflate {
	
	public static void main(String[] args) {
		Scanner inputLines = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
		int numCases = inputLines.nextInt(); inputLines.nextLine();
		for (int caseNum = 1; caseNum <= numCases; caseNum++) {
			int numCust = inputLines.nextInt(); int prodPerC = inputLines.nextInt(); inputLines.nextLine();
			long[][] pump_line = new long[numCust][prodPerC];
			long[] mins = new long[numCust], maxs = new long[numCust];
			long pressSum = 0;
			
			for (int cust = 0; cust < numCust; cust++) {
				for (int prod = 0; prod < prodPerC; prod++) {
					pump_line[cust][prod] = inputLines.nextLong();
				} inputLines.nextLine();
				
				pump_line[cust] = mergeSort(pump_line[cust]);
				
				pressSum += sumPerCustomer(pump_line[cust]);
				mins[cust] = pump_line[cust][0]; maxs[cust] = pump_line[cust][prodPerC - 1];
			}
			
			for (int cust = 0; cust < numCust - 1; cust++) {
				long min_prev = mins[cust], max_prev = maxs[cust];
				long min_new = mins[cust+1], max_new = maxs[cust+1];
				
				long diff_min = Math.abs(min_prev - min_new);
				long diff_new = Math.abs(min_prev - max_new); if (diff_new < diff_min) {diff_min = diff_new;}
				diff_new = Math.abs(max_prev - min_new); if (diff_new < diff_min) {diff_min = diff_new;}
				diff_new = Math.abs(max_prev - max_new); if (diff_new < diff_min) {diff_min = diff_new;}
				
				pressSum += diff_min;
			}
			
			System.out.println("Case #" + caseNum + ": " + pressSum);
		}
		
		inputLines.close();
	}
	
	private static long[] mergeSort(long[] arr) {
		int arr_size = arr.length;
		if (arr_size <= 1) {return arr;}
		
		int ind_mid = arr_size/2;
		
		long[] temp_left = new long[ind_mid]; //ind_mid = temp_left.length;
		long[] temp_right = new long[arr_size - ind_mid];
		
		for (int i = 0; i < arr_size; i++) {
			if (i < ind_mid) {temp_left[i] = arr[i];}
			else {temp_right[i - ind_mid] = arr[i];}
		}
		
		temp_left = mergeSort(temp_left); temp_right = mergeSort(temp_right);
		
		long[] merged = new long[arr_size];
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
	
	private static long sumPerCustomer(long[] prods) {
		long sum = 0;
		for (int p = 0; p < prods.length - 1; p++) {
			sum += (prods[p + 1] - prods[p]);
		}
		return sum;
	}
}