package kattis;

import java.util.Scanner;

public class Tak2Stns {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		long stns = scan.nextInt();
		
		System.out.println((stns%2==0)?"Bob":"Alice");
		scan.close();
	}
}