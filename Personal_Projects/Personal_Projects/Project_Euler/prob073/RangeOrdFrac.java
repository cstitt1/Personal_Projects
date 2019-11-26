package prob073;

public class RangeOrdFrac {
	public static void main(String[] args) {
		long count = 0;
		
		for (int d = 4; d <= 12000; d++) {
			int low = d/3 + 1, high = d/2;
			for (int n = low; n <= high; n++) {
				if (gcd(d,n) == 1) {
					count++;
					System.out.println(n+"/"+d+" -- "+count);
				}
			}
		}
	}
	
	private static int gcd(int a, int b) {
		if (b == 0) {
			return a;
		} else {
			return gcd(b, a%b);
		}
	}
}