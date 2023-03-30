package prob056;

import java.util.ArrayList;

public class PowDigSum {
	public static void main(String[] args) {
		int max = 0;
		for (int a = 2; a<100; a++) {
			for (int b=1; b<100; b++) {
				ArrayList<Integer> digits = new ArrayList<>();
				digits.add(a);
				for (int e = 1; e<b; e++) {
					for (int i=0; i<digits.size(); i++) {
						digits.set(i, digits.get(i)*a);
					}
					
					for (int i=0; i<digits.size(); i++) {
						while (digits.get(i) >= 10) {
							digits.set(i, digits.get(i)-10);
							if (i==digits.size()-1) {
								digits.add(1);
							} else {
								digits.set(i+1, digits.get(i+1)+1);
							}
						}
					}
				}
				
				int sum = 0;
				for (int digit : digits) {
					sum = sum + digit;
				}
				
				if (sum > max) {
					max = sum;
					System.out.println(a+"^"+b + ": " + sum);
				}
			}
		}
		
		System.out.println(max);
	}
}