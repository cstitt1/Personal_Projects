package prob131;

public class PriCuPart {

	public static void main(String[] args) {
		for (int p = 2; p < 100; p += 2) {
			if (!isPrime(p)) {
				continue;
			}
			
			//n^3 + n^2p = c^3 --> (c^3 - n^3)/n^2 = p = ((c-n)(c^2+cn+n^2))/n^2
		}
	}
	
	private static boolean isPrime(int pc) {
		for (int i=2; i<= pc/2; i++) {
			if (pc%i == 0) {
				return false;
			}
		}
		
		return true;
	}
}