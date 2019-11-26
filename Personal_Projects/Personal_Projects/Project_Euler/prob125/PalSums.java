package prob125;

import java.util.ArrayList;

public class PalSums {
	public static void main(String[] args) {
		ArrayList<Long> vals = new ArrayList<>();
		long sum = 0, limit = Math.round(Math.pow(10, 8));
		for (long s = 1; s <= limit; s++) {
			long sq = (s*s);
			for (long add = 1; sq <= limit; add++) {
				sq += ((s+add)*(s+add));
				if (isPalindrome(sq) && sq <= limit && !vals.contains(sq)) {
					sum += sq;
					vals.add(sq);
					System.out.println(sq+" -- "+s+"..."+(s+add)+" sum = "+sum);
				}
			}
		}
	}
	
	private static boolean isPalindrome(long n) {
		String str = ""+n;
		
		for (int i=0; i<str.length()/2; i++) {
			if (str.charAt(i) != str.charAt(str.length()-(i+1))) {
				return false;
			}
		}
		
		return true;
	}
}