package kattis;

import java.util.Scanner;

public class Bijele {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String[] nums = scan.nextLine().split(" ");
		int[] pcs = {1,1,2,2,2,8};
		String str = "";
		
		for (int i = 0; i < nums.length; i++) {
			str += (pcs[i] - Integer.parseInt(nums[i]));
			if (i != nums.length-1) {
				str += " ";
			}
		}
		
		System.out.println(str);
		
		scan.close();
	}
}