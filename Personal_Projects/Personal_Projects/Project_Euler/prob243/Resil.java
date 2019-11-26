package prob243;

public class Resil {
	public static void main(String[] args) {
		double less = 15499.0/94744.0;
		for (int d = 2; d < Integer.MAX_VALUE; d++) {
			int cnt = 1;
			for (int n = 2; n < d; n++) {
				if (gcd1(n,d)) {
					cnt++;
				}
			}
			
			double q = ((double) cnt)/(d-1);
			System.out.println(d + ": "+cnt+"/"+(d-1));
			if (q < less) {
				break;
			}
		}
	}
	
	private static boolean gcd1(int n, int d) {
		for (int i = 2; i <= n; i++) {
			if (n%i == 0 && d%i == 0) {
				return false;
			}
		}
		
		return true;
	}
}