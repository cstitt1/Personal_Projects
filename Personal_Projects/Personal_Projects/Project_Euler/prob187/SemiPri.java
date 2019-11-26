package prob187;

public class SemiPri {
	public static void main(String[] args) {
		int count = 0;
		
		for (int n = 4; n <= Math.pow(10, 8); n++) {
			if (isPrime(n)) {
				continue;
			}
			
			int m = n;
			if (m%2!=0) {
				for (int f = 3; f <= m / 2; f+=2) {
					if (m % f == 0 && isPrime(f)) {
						m = m / f;
						break;
					}
				} 
			} else {
				m = m/2;
			}
			if (isPrime(m)) {
				count++;
				System.out.println(n+" -- count = "+count);
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