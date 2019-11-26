package prob055;

import java.util.ArrayList;

public class LycNums {
	public static void main(String[] args) {
		int count = 0;
		
		for (int n=1; n<10000; n++) {
			ArrayList<Integer> digits = new ArrayList<>();
			for (int j=0; j<(""+n).length(); j++) {
				digits.add(Integer.parseInt((""+n).substring(j, j+1)));
			}
			
			int i = 0;
			do {
				digits = addReverse(digits);
				i++;
			} while (isPal(digits)==false && i <= 50);
			
			if (i==51) {
				count++;
				System.out.println("Added: "+n);
			}
		}
		
		System.out.println(count);
	}
	
	private static ArrayList<Integer> addReverse(ArrayList<Integer> digits) {
		ArrayList<Integer> rev = new ArrayList<>();
		
		for (int i=0; i<digits.size(); i++) {
			rev.add(0,digits.get(i));
		}
		
		for (int j=0; j<rev.size(); j++) {
			digits.set(j, rev.get(j)+digits.get(j));
		}
		
		for (int k=0; k<digits.size(); k++) {
			while (digits.get(k)>=10) {
				if (k==digits.size()-1) {
					digits.add(1);
				} else {
					digits.set(k+1, digits.get(k+1)+1);
				}
				digits.set(k, digits.get(k)-10);
			}
		}
		
		return digits;
	}
	
	private static boolean isPal(ArrayList<Integer> digits) {
		for (int i=0; i<digits.size()/2; i++) {
			if (digits.get(i)!=digits.get(digits.size()-(i+1))) {
				return false;
			}
		}
		
		return true;
	}
}