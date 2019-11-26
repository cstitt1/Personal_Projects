package kattis;

import java.util.Scanner;

public class R2 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		String[] nums = scan.nextLine().split(" ");
		System.out.println(2*Integer.parseInt(nums[1]) - Integer.parseInt(nums[0]));
		
		scan.close();
	}
}