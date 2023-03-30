package prob077;

import java.util.ArrayList;

public class PriSum {
	public static void main(String[] args) {
		ArrayList<Integer> nums = new ArrayList<>();
		
		for (int p = 2; p <= 10000; p+=2) {
			if (isP(p)) {
				nums.add(p);
			}
			
			if (p%2 == 0) {
				p--;
			}
		}
		
		for (int num = 10; num < Integer.MAX_VALUE; num++) {
			int count = f2(num, indNxt(num, nums), nums);
			System.out.println(num+": "+count);
			if (count >= 5000) {
				break;
			}
		}
	}
	
	private static int indNxt(int num, ArrayList<Integer> p) {
		for (int i = num+1; i <= 10000; i++) {
			if (p.indexOf(i) != -1) {
				return p.indexOf(i);
			}
		}
		
		return p.size();
	}
	
	private static int f2(int n, int k, ArrayList<Integer> p) {
		if (n<0 || k<1) {
			return 0;
		} else if (n == 0) {
			return 1;
		} else {
			int c1 = f2(n,k-1, p);
			int c2 = f2(n-p.get(k-1),k, p);
			return c1+c2;
		}
	}
	
	private static boolean isP(int pc) {
		for (int d = 2; d <= pc/2; d++) {
			if (pc%d == 0) {
				return false;
			}
		}
		
		return true;
	}
}