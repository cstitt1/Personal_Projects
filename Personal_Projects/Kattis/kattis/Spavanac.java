package kattis;

import java.util.Scanner;

public class Spavanac {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String[] nums = scan.nextLine().split(" ");
		int[] nms = {Integer.parseInt(nums[0]),Integer.parseInt(nums[1])};
		
		nms[1] -= 45;
		if (nms[1] < 0) {
			nms[1] += 60;
			nms[0]--;
		}
		
		if (nms[0] < 0) {
			nms[0] = 23;
		}
		
		System.out.println(nms[0] + " " + nms[1]);
		
		scan.close();
	}
}