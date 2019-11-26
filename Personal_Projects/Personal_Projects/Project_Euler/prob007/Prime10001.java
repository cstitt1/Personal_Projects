package prob007;
import prob003.MaxPrimeFactor;

public class Prime10001 extends MaxPrimeFactor {
	public static void main(String[] args) {
		int cnt = 0;
		double prm = 1;
		
		while (cnt != 10001) {
			prm++;
			if (isPrime(prm))
				cnt++;
		}
		
		System.out.println(prm);
	}
}