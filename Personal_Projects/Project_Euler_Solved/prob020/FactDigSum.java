package prob020;

import java.util.ArrayList;

public class FactDigSum {

	public static void main(String[] args) {
		ArrayList<Integer> digits = new ArrayList<>();
		digits.add(1);
		int exp = 1;
		
		while (exp <= 100) {
			int sum=0;
			for (int i=0; i<digits.size(); i++) {
				digits.set(i, digits.get(i)*exp);
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
			
			for (int digit : digits) {
				sum = sum + digit;
			}
			System.out.println(exp + ": " + sum);
			exp++;
		}
	}

}
