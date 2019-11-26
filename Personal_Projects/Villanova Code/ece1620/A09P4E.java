package ece1620;

public class A09P4E {
	public static void main(String[] args) {
		char a[] = "Computer Engineering rrrr".toCharArray();
		char c[] = "in".toCharArray();
		int count = pattern(a,c);
		System.out.println(count);
	}
	
	private static int pattern(char[] a, char[] c) {
		int count = 0, len1 = 0, len2 = 0, i, j, s;
		
		for (i = 0; i < a.length; i++) {
			if (i < c.length) {
				len2++;
			}
			len1++;
		}
		
		if (len2 == 0) {
			return count;
		}
		
		for (i = 0; i <= len1-len2; i++) {
			count++;
			s = 0;
			for (j = i; j < len2+i; j++) {
				if (a[j] == c[j]) {
					s++;
				}
			}
			
			if (s != len2) {
				count--;
			}
		}
		
		return count;
	}
}