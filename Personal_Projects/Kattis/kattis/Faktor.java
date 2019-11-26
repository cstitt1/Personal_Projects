package kattis;

import java.util.Scanner;

public class Faktor {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String[] nums = scan.nextLine().split(" ");
		int a = Integer.parseInt(nums[0]), i = Integer.parseInt(nums[1]);
		
		System.out.println((a*(i-1))+1);
		scan.close();
	}
}