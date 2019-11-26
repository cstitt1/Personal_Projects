package prob160;

public class FacTrailDig {
	public static void main(String[] args) {
		long res = 1;
		for (long f = 2; f <= 1000000000000l; f++) {
			res = modMult(res, f);
			
			while (res % 10 == 0) {
				res /= 10;
			}
			
			res %= 100000;
			
			System.out.println(f + ": "+res);
		}
	}
	
	private static long modMult(long a, long b) {
		long res = 0, q = b / 10, r = b % 10;
		res += a*r;
		res %= 100000;
		for (long i = 1; i <= q; i++) {
			res *= 10;
			res %= 100000;
		}
		
		return res;
	}
}