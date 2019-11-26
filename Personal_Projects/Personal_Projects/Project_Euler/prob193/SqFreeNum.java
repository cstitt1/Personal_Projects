package prob193;

import java.util.ArrayList;

public class SqFreeNum {

	public static void main(String[] args) {
		long limit = Math.round(Math.pow(2, 50))-1, count = limit, div = -1;
		ArrayList<Long> nums = new ArrayList<>();
		
		for (long p = 2; div != 0; p+=2) {
			if (!prime(p)) {
				continue;
			}
			
			div = limit/(p*p);
			
			for (long n : nums) {
				div -= limit/(p*p*n*n);
			}
			
			count -= div;
			nums.add(p);
			System.out.println(p+" -- count = "+count);
			if (p == 2) {
				p--;
			}
		}
	}
	
	private static boolean prime(long pc) {
		if (pc == 2) {
			return true;
		}
		
		for (long i = 2; i <= Math.round(Math.ceil(Math.sqrt(pc))); i+=2) {
			if (pc%i == 0) {
				return false;
			}
			
			if (i == 2) {
				i--;
			}
		}
		
		return true;
	}
}