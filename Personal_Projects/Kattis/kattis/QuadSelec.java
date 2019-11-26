package kattis;

import java.util.Scanner;

public class QuadSelec {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int x = Integer.parseInt(scan.nextLine());
		int y = Integer.parseInt(scan.nextLine());;
		
		int q = 1;
		int s = ((x<0)?0:1)*10 + ((y<0)?0:1);
		if (s == 0) {
			q += 2;
		} else if (s == 1) {
			q += 1;
		} else if (s == 10) {
			q += 3;
		}
		System.out.println(q);
		scan.close();
	}
}