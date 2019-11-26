package prob341;

import java.util.HashMap;

public class GolSeq {
	private static HashMap<Long,Long> vals = new HashMap<>();
	
	public static void main(String[] args) {
		long sum = 0, cube = 1;
		
		for (long i = 1; i < Math.round(Math.pow(10, 18)); i++) {
			long g = a(i);
			if (i == cube*cube*cube) {
				sum += g;
				System.out.println(cube+": "+i+" -- sum = "+sum);
				cube++;
			}
		}
	}
	
	private static long a(long n) {
		if (n == 1) {
			vals.put(n,n);
			return 1;
		} else {
			long val = 1 + vals.get(n - vals.get(vals.get(n-1)));
			vals.put(n, val);
			return val;
		}
	}
}