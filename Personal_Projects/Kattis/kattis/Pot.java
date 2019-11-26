package kattis;

import java.util.Scanner;

public class Pot {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int lins = Integer.parseInt(scan.nextLine());
		
		long sum = 0;
		for (int i = 0; i < lins; i++) {
			int num = Integer.parseInt(scan.nextLine());
			int base = num / 10, exp = num % 10;
			sum += Math.round(Math.pow(base, exp));
		}
		
		System.out.println(sum);
		scan.close();
	}
}