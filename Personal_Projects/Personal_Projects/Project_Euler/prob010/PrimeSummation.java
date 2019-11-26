package prob010;

import java.util.ArrayList;

public class PrimeSummation {
	private static ArrayList<Double> primes = new ArrayList<>();
	
	public static void main(String[] args) {
		double sum = 2;
		primes.add(sum);
		for (double i=3; i<2000000; i+=2) {
			if (isPrime(i)) {
				sum = sum+i;
				System.out.println("Sum + "+i+" = "+sum);
				primes.add(i);
			}
		}
		
		System.out.println("Sum:" + sum);
	}
	
	public static boolean isPrime(double i) {
		for (double prime : primes)
			if (i%prime==0)
				return false;
		return true;
	}
}
