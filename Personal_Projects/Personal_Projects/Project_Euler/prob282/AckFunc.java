package prob282;

public class AckFunc {
	public static void main(String[] args) {
		long sum = 0;
		long mod = 1475789056;
		
		for (int n = 0; n <= 6; n++) {
//			long res = A(n,n);
			long res;
			
			if (n == 0) {
				res = n+1;
			} else if (n == 1) {
				res = n+2;
			} else if (n == 2) {
				res = 2*n+3;
			} else {
				res = kua(2,n+3,n-2) - 3;
			}
			
			sum += (res%mod);
			System.out.println(n+": "+res+" --> "+res+"%"+mod+" = "+(res%mod)+" -- sum = "+sum);
		}
	}
	
	private static long kua(long a, long b, long n) {
		if (n == 1) {
			return pow(a,b);
		} else if (n >= 1 && b == 0) {
			return 1;
		} else {
			return kua(a,kua(a,b-1,n),n-1);
		}
	}
	
	private static long pow(long a, long b) {
		long res = 1;
		for (long i = 1; i <= b; i++) {
			res *= a;
		}
		return res;
	}
	
	/*private static long A(long m, long n) {
		if (m == 0) {
			return n+1;
		} else if (n == 0) {
			return A(m-1,1);
		} else {
			return A(m-1,A(m,n-1));
		}
	}*/
}