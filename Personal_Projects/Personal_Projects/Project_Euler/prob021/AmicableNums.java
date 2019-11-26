package prob021;

import java.util.ArrayList;

public class AmicableNums {

	public static void main(String[] args) {
		double sum = 0;
		ArrayList<Integer> nums = new ArrayList<>();
		for (int a=220; a<10000; a++) {
			if (nums.contains(a)) {
				continue;
			}
			
			int b = d(a);
			if (a==b) {
				continue;
			}
			
			int tst = d(b);
			
			if (tst==a) {
				sum = sum + a;
				
				if (b<10000) {
					sum = sum + b;
				}
				
				nums.add(a);
				nums.add(b);
				System.out.println(a+" & "+b);
			}
		}
		
		System.out.println("Sum: "+sum);
	}
	
	private static int d(int n) {
		int sum = 1;
		
		for (int div=2; div<(n/2) + 1; div++) {
			if (n%div==0) {
				sum = sum + div;
			}
		}
		
		return sum;
	}

}
