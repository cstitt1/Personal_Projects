package prob050;

import java.util.ArrayList;

public class ConPriSum {
	public static void main(String[] args) {
		int max = 0;
		ArrayList<Integer> primes = new ArrayList<>();
		primes.add(2);
		
		for (int i=3; i<1000000; i+=2) {
			if (isPrime(i)) {
				primes.add(i);
				System.out.println("Added: "+i);
			}
		}
		
		for (int s=0; s<primes.size(); s++) {
			int sum = 0;
			int count=0;
			for (int i=s; i<primes.size(); i++) {
				sum = sum + primes.get(i);
				count++;
				if (sum < 1000000) {
					if (count > max && primes.contains(sum)) {
						max = count;
						System.out.println(sum + ": " + max);
					}
				} else {
					break;
				}
			}
		}
	}
	
	private static boolean isPrime(int i) {
		if (i==1) {
			return false;
		}
		
		for (int x=2; x<i; x++)
			if (i%x==0)
				return false;
		return true;
	}
}