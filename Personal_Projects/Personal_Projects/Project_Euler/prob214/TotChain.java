package prob214;

public class TotChain {
	public static void main(String[] args) {
		long sum = 0;
		for (int p = 5; p < 40000000; p+=2) {
			if (!isPri(p)) {
				continue;
			}
			
			int count = chain(p);
			if (count == 25) {
				sum += p;
			}
			
			System.out.println(p + ": " + count + " -- " + sum);
		}
	}
	
	private static int chain(int p) {
		int ans = phi(p-1), count = 1;
		while (ans != 1) {
			ans = phi(ans);
			count++;
		}
		
		return count;
	}
	
	private static int phi(int n) {
		int count = n-1;
		for (int i = 2; i < n; i++) {
			if (gcd(i,n) != 1) {
				count--;
			}
		}
		return count;
	}
	
	private static int gcd(int a, int b) {
		if (b == 0) {
			return a;
		} else {
			return gcd(b, a%b);
		}
	}
	
	private static boolean isPri(long pc) {
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