package kattis;

import java.util.Scanner;

public class SvnWdr {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		char[] line = scan.nextLine().toCharArray();
		int cC = 0, cT = 0, cG = 0, cMin;
		for (char chr : line) {
			if (chr == 'C') {
				cC++;
			} else if (chr == 'G') {
				cG++;
			} else if (chr == 'T') {
				cT++;
			}
		}
		
		cMin = cC;
		if (cT < cMin) {
			cMin = cT;
		}
		if (cG < cMin) {
			cMin = cG;
		}
		
		int res = cC*cC + cG*cG + cT*cT + cMin*7;
		System.out.println(res);
		scan.close();
	}
}