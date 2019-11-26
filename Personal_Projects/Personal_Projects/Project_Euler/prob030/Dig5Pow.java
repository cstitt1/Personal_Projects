package prob030;

public class Dig5Pow {
	public static void main(String[] args) {
		long sum, big = 0;
		for (long i = 2; i < Long.MAX_VALUE; i++) {
			sum = 0;
			long j = i, x;
			while (j > 0) {
				x = j%10;
				j/=10;
				sum += (x*x*x*x*x);
				if (sum > i) {
					break;
				}
			}
			
			if (sum == i) {
				big += i;
			}
			System.out.println(i+"-->"+sum+" -- big = "+big);
		}
	}
}