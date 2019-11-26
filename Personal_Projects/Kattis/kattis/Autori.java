package kattis;

import java.util.Scanner;

public class Autori {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String[] line = scan.nextLine().split("-");
		String res = "";
		
		for (String name : line) {
			res += name.charAt(0);
		}
		
		System.out.println(res);
		scan.close();
	}
}