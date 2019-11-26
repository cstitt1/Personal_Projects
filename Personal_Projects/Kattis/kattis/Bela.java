package kattis;

import java.util.Scanner;

public class Bela {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String[] imp = scan.nextLine().split(" ");
		int lines = Integer.parseInt(imp[0]);
		char suit = imp[1].charAt(0);
		int sum = 0;
		
		for (int i = 0; i < 4*lines; i++) {
			String str = scan.nextLine();
			char num = str.charAt(0), st = str.charAt(1);
			
			if (num == 'A') {
				sum += 11;
			} else if (num == 'K') {
				sum += 4;
			} else if (num == 'Q') {
				sum += 3;
			} else if (num == 'J') {
				sum += 2;
				if (suit == st) {
					sum += 18;
				}
			} else if (num == 'T') {
				sum += 10;
			} else if (num == '9' && suit == st) {
				sum += 14;
			}
		}
		
		System.out.println(sum);
		scan.close();
	}
}