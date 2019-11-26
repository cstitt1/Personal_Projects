package prob003;

public class MaxPrimeFactor {
	public static void main(String[] args) {
		double limit = Double.valueOf("600851475143");
		for (double i=2; i<limit; i++)
			if (limit%i==0 && isPrime(i)) {
				System.out.println(i);
				limit /= i;
			}
		
		System.out.println(limit);
	}
	
	public static boolean isPrime(double i) {
		for (double x=2; x<i; x++)
			if (i%x==0)
				return false;
		return true;
	}
}