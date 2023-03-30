package prob032;

import java.util.ArrayList;

public class PandProd {

	public static void main(String[] args) {
		ArrayList<Long> prods = new ArrayList<>();
		long sum = 0;
		
		for (long a = 2; a < 987654321; a++) {
			for (long b = 2; b <= a; b++) {
				long prod = a*b;
				String str = a+" "+b+" "+prod;
				if (prod > 0 && p19(str) && !prods.contains(prod)) {
					sum += prod;
					System.out.println(str + " -- sum = "+sum);
					prods.add(prod);
				}
			}
		}
	}
	
	private static boolean p19(String str) {
		if (str.contains("0")) {
			return false;
		}
		
		for (int i = 1; i <= 9; i++) {
			if (str.indexOf(""+i)!=str.lastIndexOf(""+i) || !str.contains(""+i)) {
				return false;
			}
		}
		
		return true;
	}
}