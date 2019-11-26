package kattis;

import java.util.Scanner;

public class Sibice {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String[] numS = scan.nextLine().split(" ");
		int[] nums = {Integer.parseInt(numS[0]),Integer.parseInt(numS[1]),Integer.parseInt(numS[2])};
		int maxLen = (int) Math.sqrt(nums[1]*nums[1] + nums[2]*nums[2]);
		
		for (int i = 0; i < nums[0]; i++) {
			if (Integer.parseInt(scan.nextLine()) <= maxLen) {
				System.out.println("DA");
			} else {
				System.out.println("NE");
			}
		}
		
		scan.close();
	}
}