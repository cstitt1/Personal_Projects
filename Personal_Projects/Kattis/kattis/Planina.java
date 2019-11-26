package kattis;

import java.util.Scanner;

public class Planina {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		
		long[] lins = {2,3,5};
		while (n > 2) {
			lins[0] = lins[1];
			lins[1] = lins[2];
			lins[2] = lins[0] + lins[1];
			n--;
		}
		
		System.out.println(lins[n]*lins[n]);
		scan.close();
	}
}