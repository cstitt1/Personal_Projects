package prob026;

import java.util.HashMap;

public class RecipCyc {
	
	public static void main(String[] args) {
		HashMap<Integer, Integer> primes = new HashMap<>();
		int lM = -1, dM = 1;
		primes.put(2, 0);
		for (int d = 3; d < 1000; d++) {
			int max = -1;
			for (int p : primes.keySet()) {
				if (d%p == 0 && primes.get(p) > max) {
					max = primes.get(p);
				}
			}
			
			if (max > -1) {
				System.out.println(d + ": max = " + max + " len = " + lM);
				continue;
			}
			
			int f = 1, p = 0;
			do {
				int res = 1;
				if ((d-1)%f != 0) {
					f++;
					continue;
				}
				String fb = bin(f);
				for (int i = 0; i < fb.length(); i++) {
					int val = (int) Math.round(Math.pow(10, Integer.parseInt(fb.substring(i,i+1))));
					res = (res*val)%d;
					if (i != fb.length()-1) {
						res = (res * res) % d;
					}
				}
				if (res == 1) {
					p = f;
					break;
				}
				
				f++;
			} while (f < d);
			
			if (lM < p) {
				lM = p;
				dM = d;
			}
			primes.put(d, p);
			System.out.println(d + ": p = " + p + " len = " + lM);
		}
		
		System.out.println("\n" + "dM = "+dM);
	}
	
	private static String bin(int n) {
		String res = "";
		while (n > 0) {
			res = (n % 2) + res;
			n /= 2;
		}
		return res;
	}
}