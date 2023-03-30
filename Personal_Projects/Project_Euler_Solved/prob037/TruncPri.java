package prob037;

import java.util.ArrayList;

public class TruncPri {
	public static void main(String[] args) {
		ArrayList<Integer> primes = new ArrayList<>();
		String eq = "";
		int sum = 0;
		
		for (int i=11; primes.size()<11; i+=2) {
			if (fromL(""+i) && fromR(""+i)) {
				primes.add(i);
				System.out.println("Added: "+i);
				eq = eq + i + "+";
				sum = sum + i;
			}
		}
		
		System.out.println(eq.substring(0,eq.length()-1)+"="+sum);
	}
	
	private static boolean fromL(String str) {
		if (str.length()==0) {
			return true;
		}
		
		if (!isPrime(Integer.parseInt(str))) {
			return false;
		} else {
			return fromL(str.substring(1));
		}
	}
	
	private static boolean fromR(String str) {
		if (str.length()==0) {
			return true;
		}
		
		if (!isPrime(Integer.parseInt(str))) {
			return false;
		} else {
			return fromR(str.substring(0,str.length()-1));
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