package kattis;

import java.util.Scanner;

public class Coldputer {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		scan.nextLine(); char[] str = scan.nextLine().toCharArray();
		
		int count = 0;
		for (char c : str) {
			if (c == '-') {
				count++;
			}
		}
		
		System.out.println(count);
		scan.close();
	}
}