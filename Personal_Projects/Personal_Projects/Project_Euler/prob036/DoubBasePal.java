package prob036;

import java.util.ArrayList;

public class DoubBasePal {
	public static void main(String[] args) {
		int sum = 0;
		ArrayList<Integer> palin = new ArrayList<>();
		for (int i=1; i<1000000; i++) {
			String str = ""+i;
			if (isPalindrome(str)) {
				palin.add(i);
				System.out.println(i);
			}
		}
		
		for (int pal : palin) {
			int ori = pal;
			String bin = "";
			while (pal!=0) {
				bin = pal%2 + bin;
				pal = pal/2;
			}
			
			if (isPalindrome(bin)) {
				System.out.println(ori + ": " + bin);
				sum = sum + ori;
			}
		}
		
		System.out.println(sum);
	}
	
	private static boolean isPalindrome(String str) {
		String[] sides;
		if (str.length()==1) {
			return true;
		} else if (str.length()%2==0) {
			sides = new String[] {str.substring(0, str.length()/2),str.substring(str.length()/2)};
		} else {
			sides = new String[] {str.substring(0, str.length()/2),str.substring(str.length()/2+1)};
		}
		
		boolean palindrome = true;
		for (int s=0; s<sides[0].length(); s++) {
			if (sides[0].charAt(s) != sides[1].charAt(sides[1].length()-(1+s)))
				palindrome = false;
		}
		
		return palindrome;
	}
}