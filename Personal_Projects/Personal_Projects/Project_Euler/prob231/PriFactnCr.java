package prob231;

import java.util.ArrayList;

public class PriFactnCr {
	public static void main(String[] args) {
		int n = 20000000; // 20 million
		int r = 15000000; // 15 million
		
		int c = nCr(n,r);
		System.out.println(c);
		
		int sum = 0;
		for (int i=2; i<c; i+=0) {
			if (isPrime(i) && c%i==0) {
				c = c/i;
				sum = sum + i;
				System.out.println(sum);
			} else {
				i++;
			}
		}
	}
	
	private static int nCr(int n, int r) {
		int prod = 1;
		int diff = n-r;
		ArrayList<Integer> nums = new ArrayList<>();
		
		for (int i=diff; i>=2; i--) {
			nums.add(i);
		}
		
		for (int m = n; m>r; m--) {
			int u=m;
			for (int i=0; i<nums.size(); i+=0) {
				if (u%nums.get(i)==0) {
					u = u/nums.get(i);
					nums.remove(i);
				} else {
					i++;
				}
			}
			prod = prod*u;
		}
		
		for (int num : nums) {
			prod = prod/num;
		}
		
		return prod;
	}
	
	private static boolean isPrime(int n) {
		for (int i=2; i<=n/2; i++) {
			if (n%i==0) {
				return false;
			}
		}
		
		return true;
	}
}