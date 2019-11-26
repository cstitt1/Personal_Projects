package kattis;

import java.util.Scanner;

public class HumanCnnball {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int lins = Integer.parseInt(scan.nextLine());
		
		for (int i = 0; i < lins; i++) {
			String[] nums = scan.nextLine().split(" ");
			double v0 = Double.parseDouble(nums[0]), theta = Double.parseDouble(nums[1])*Math.PI/180;
			double x1 = Double.parseDouble(nums[2]), h1 = Double.parseDouble(nums[3]), h2 = Double.parseDouble(nums[4]);
			
			double t = x1/(v0*Math.cos(theta));
			double yt = (v0*Math.sin(theta) - 4.905*t)*t;
			
			if (yt < h2 - 1 && yt > h1 + 1) {
				System.out.println("Safe");
			} else {
				System.out.println("Not Safe");
			}
		}
		
		scan.close();
	}
}