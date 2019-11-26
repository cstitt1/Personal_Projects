package prob051;

public class PriDigRep {
	// Length > 2 -- length = n -- digits 0 to n-1
	// Can't replace 0 or n-1
	// n-1 can't be 0, 2, 4, 5, 6, 8 -- 1, 3, 7, 9
	public static void main(String[] args) {
		int count = 0, fin = -1;
		for (int len = 3; count < 8; len++) {
			int exp = len - 2;
			
			for (int bin = 1; bin < (int) Math.round(Math.pow(2,exp)); bin++) {
				String sb = Integer.toBinaryString(bin).replaceAll("1", "*").replaceAll("0", "-");
				while (sb.length() < exp) {
					sb = "-" + sb;
				}
				
				
			}
		}
	}
}