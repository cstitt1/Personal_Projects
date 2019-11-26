package kattis;

import java.util.Scanner;

public class DiceCup {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String[] nums = scan.nextLine().split(" ");
		int a = Integer.parseInt(nums[0]), b = Integer.parseInt(nums[1]);
		int[] sums = new int[a+b+1];
		int maxSum = 0;
		
		for (int i = 1; i <= a; i++) {
			for (int j = 1; j <= b; j++) {
				sums[i+j]++;
				if (sums[i+j] > maxSum) {
					maxSum = sums[i+j];
				}
			}
		}
		
		for (int i = 0; i < sums.length; i++) {
			if (sums[i] == maxSum) {
				System.out.println(i);
			}
		}
		
		scan.close();
	}
}