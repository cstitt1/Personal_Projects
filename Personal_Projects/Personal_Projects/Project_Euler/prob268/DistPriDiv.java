package prob268;

import java.util.ArrayList;

public class DistPriDiv {

	public static void main(String[] args) {
		long count = 0;
		ArrayList<Long> primes = new ArrayList<>();
		primes.add(2l);
		for (long i=3; i<100; i+=2) {
			if (isPri(i)) {
				primes.add(i);
				System.out.println("Added prime number "+i);
			}
		}
		
		System.out.println("\n-----------------------------\n");
		
		ArrayList<Long> mult = new ArrayList<>();
		for (int i=0; i<primes.size(); i++) {
			for (int j=i+1; j<primes.size(); j++) {
				for (int k=j+1; k<primes.size(); k++) {
					for (int l=k+1; l<primes.size(); l++) {
						long m = primes.get(i)*primes.get(j)*primes.get(k)*primes.get(l);
						mult.add(m);
						System.out.println("Added multiple "+m);
					}
				}
			}
		}
		
		System.out.println("\n-----------------------------\n");
		
		ArrayList<Long> lcm = new ArrayList<>();
		for (int i=0; i<mult.size(); i++) {
			for (int j=i+1; j<mult.size(); j++) {
				long pi = mult.get(i), pj = mult.get(j);
				lcm.add((pi/gcd(pi,pj))*pj);
				System.out.println("Added lcm of "+pi+" and "+pj);
			}
		}
		
		System.out.println("\n-----------------------------\n");
		
		long limit = pow(10,16);
		for (long m : mult) {
			count += (limit/m);
		}
		for (long l : lcm) {
			count -= (limit/l);
		}
		
		System.out.println(count);
	}
	
	private static long pow(int a, int b) {
		if (b == 0) {
			return 1;
		} else if (b == 1) {
			return a;
		} else {
			return a*pow(a,b-1);
		}
	}
	
	private static long gcd(long i, long j) {
		for (long x = (i<j?i:j); x>1; x--) {
			if (i%x == 0 && j%x == 0) {
				return x;
			}
		}
		
		return 1;
	}
	
	private static boolean isPri(long i) {
		for (long d = 2; d<i; d++) {
			if (i%d==0) {
				return false;
			}
		}
		
		return true;
	}
}