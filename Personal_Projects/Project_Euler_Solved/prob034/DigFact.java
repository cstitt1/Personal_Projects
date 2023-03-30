package prob034;

public class DigFact {

	public static void main(String[] args) {
		long sum, big = 0;
		for (long i = 3; i < Long.MAX_VALUE; i++) {
			sum = 0;
			long j = i, x;
			while (j > 0) {
				x = j%10;
				j/=10;
				sum += fact(x);
				if (sum > i || sum < 0) {
					break;
				}
			}
			
			if (sum == i) {
				big += i;
			}
			System.out.println(i+"-->"+sum+" -- big = "+big);
		}
	}
	
	private static long fact(long num) {
		if (num <= 1) {
			return 1;
		} else {
			return num*fact(num-1);
		}
	}
}