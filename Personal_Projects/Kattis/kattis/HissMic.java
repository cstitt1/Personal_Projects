package kattis;

import java.util.Scanner;

public class HissMic {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		char[] word = scan.nextLine().toCharArray();
		String out = "no hiss";
		boolean dble = false;
		
		for (char let : word) {
			if (dble && let == 's') {
				out = "hiss";
			} else {
				dble = (let == 's');
			}
		}
		
		System.out.println(out);
		scan.close();
	}
}