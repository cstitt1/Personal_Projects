package prob134;

public class PriPairConn {
	public static void main(String[] args) {
		long sum = 0;
		
		for (long p1 = 5; p1 <= 1000000; p1+=2) {
			if (!isPrime(p1)) {
				continue;
			}
			
			long p2;
			for (p2 = p1+2; p2 < Long.MAX_VALUE; p2 += 2) {
				if (isPrime(p2)) {
					break;
				}
			}
			
			for (long a = 1; a < Long.MAX_VALUE; a++) {
				long p = (a*pow(10,(""+p1).length())) + p1;
				if (p%p2 == 0) {
					sum += p;
					System.out.println(p+" for "+p1+" and "+p2+" -- sum = "+sum);
					break;
				}
			}
		}
	}
	
	private static boolean isPrime(long pc) {
		for (long i=2; i<= pc/2; i++) {
			if (pc%i == 0) {
				return false;
			}
		}
		
		return true;
	}
	
	private static long pow(int a, int b) {
		if (b == 0) {
			return 1;
		} else {
			return a*pow(a,b-1);
		}
	}
}