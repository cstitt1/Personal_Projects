package prob023;

import java.util.ArrayList;

public class NonabunSum {

	public static void main(String[] args) {
		ArrayList<Integer> abundant = new ArrayList<>();
		ArrayList<Integer> abunSums = new ArrayList<>();
		double sumN = 0;
		
		for (int a = 12; a<=28123; a++) {
			if (d(a) > a) {
				abundant.add(a);
				System.out.println("New num: "+a);
			}
		}
		
		for (int k = 1; k<=28123; k++) {
			sumN = sumN + k;
		}
		
		double init = sumN;
		
		for (int i = 0; i<abundant.size(); i++) {
			for (int j = i; j<abundant.size(); j++) {
				int sum = abundant.get(i) + abundant.get(j);
				if (sum > 28123) {
					continue;
				}
				
				if (abunSums.contains(sum)==false) {
					abunSums.add(sum);
					sumN = sumN - sum;
					System.out.println("New sum: ("+i+","+j+") of "+abundant.size()+"-- from "+init+" to "+sumN);
				}
			}
		}
		
		System.out.println(sumN);
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
