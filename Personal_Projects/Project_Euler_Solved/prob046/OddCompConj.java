package prob046;

public class OddCompConj {

	public static void main(String[] args) {
		boolean found;
		for (int c = 3; c <= Integer.MAX_VALUE; c+=2) {
			for (int m = 3; m <= c && (m*c) > 0; m+=2) {
				found = false;
				for (int p = 3; p < (m*c); p+=2) {
					if (!isP(p)) {
						continue;
					}
					
					int sq = (m*c - p)/2, odd = 1;
					while (sq > 0) {
						sq -= odd;
						odd += 2;
					}
					
					if (sq == 0) {
						found = true;
						System.out.println((m*c) + " = " + p + " + 2x" + Math.round(Math.sqrt((m*c - p)/2)) + "^2");
						break;
					}
				}
				
				if (!found) {
					System.out.println("Answer: "+(m*c));
					return;
				}
			}
		}
	}
	
	private static boolean isP(int pc) {
		for (int d = 2; d<=pc/2+1; d++) {
			if (pc%d == 0) {
				return false;
			}
		}
		
		return true;
	}
}