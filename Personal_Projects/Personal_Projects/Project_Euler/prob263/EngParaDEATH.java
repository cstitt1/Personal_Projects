package prob263;

import java.util.ArrayList;

public class EngParaDEATH {
	public static void main(String[] args) {
		long sum = 0; int count = 0;
		for (long n = 12; count < 4; n = n + 2) {
			
		}
	}
	
	private boolean p1(long pc) {//prime
		if (pc == 2) {
			return true;
		}
		
		long sqrt = (long) Math.ceil(Math.sqrt(pc));
		for (long d = 2; d <= sqrt; d++) {
			if (pc % d == 0) {
				return false;
			}
		}
		
		return true;
	}
	
	private boolean p2(long p) { //practical
		ArrayList<Long> f = new ArrayList<>();
		long sqrt = (long) Math.round(Math.sqrt(p));
		if (sqrt * sqrt == p) {
			f.add(sqrt);
		}
		
		f.add(1l);
		f.add(p);
		for (long i = 2; i < sqrt; i++) {
			if (p%i == 0) {
				f.add(i);
				f.add(p%i);
			}
		}
		
		for (long k = 3; k < p; k++) {
			if (f.contains(k)) {
				continue;
			}
			
			boolean sum = false;
			for (long fact : f) {
				if (f.contains(k-fact)) {
					sum = true;
					break;
				}
			}
			
			if (!sum) {
				return false;
			}
		}
		return true;
	}
}