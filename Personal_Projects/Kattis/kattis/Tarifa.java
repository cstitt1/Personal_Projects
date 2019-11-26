package kattis;

import java.util.Scanner;

public class Tarifa {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int max = Integer.parseInt(scan.nextLine());
		int lins = Integer.parseInt(scan.nextLine());
		int xSum = 0;
		
		for (int i = 0; i < lins; i++) {
			xSum += (max - Integer.parseInt(scan.nextLine()));
		}
		
		System.out.println(xSum + max);
		scan.close();
	}
}