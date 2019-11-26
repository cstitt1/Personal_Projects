package primeTest;

public class PriTest {

	public static void main(String[] args) {
		for (int i = 2; i < 1000000000; i++) {
			boolean o = prime(i), p = newP(i);
			System.out.println(i+": "+o+" vs "+p);
			if (o!=p) {
				break;
			}
		}
	}
	
	private static boolean prime(long pc) {
		for (long i=2; i<=pc/2; i++) {
			if (pc%i==0) {
				return false;
			}
		}
		
		return true;
	}
	
	private static boolean newP(long pc) {
		if (pc == 2) {
			return true;
		}
		
		for (long i = 2; i <= Math.round(Math.ceil(Math.sqrt(pc))); i+=2) {
			if (pc%i == 0) {
				return false;
			}
			
			if (i == 2) {
				i--;
			}
		}
		
		return true;
	}
}