package prob254;

public class SumDigFact {

	public static void main(String[] args) {
		long sum = 0;
		
		for (int i=1; i<=150; i++) {
			sum += sg(i);
			System.out.println(i+": sum = "+sum);
		}
	}
	
	private static long sg(int i) {
		long sum = 0, val = g(i);
		while (val > 0) {
			sum += val%10;
			val/=10;
		}
		return sum;
	}
	
	private static long g(int i) {
		long val;
		for (long n = 1; n<Long.MAX_VALUE; n++) {
			val = sf(n);
			if (val == i) {
				return n;
			}
		}
		return -1;
	}
	
	private static long sf(long n) {
		long sum = 0, val = f(n);
		while (val > 0) {
			sum += val%10;
			val/=10;
		}
		return sum;
	}
	
	private static long f(long n) {
		long sum = 0;
		while (n>0) {
			sum += fact(n%10);
			n/=10;
		}
		return sum;
	}
	
	private static long fact(long n) {
		if (n <= 1) {
			return 1;
		} else {
			return n*fact(n-1);
		}
	}
}