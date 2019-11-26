package prob071;

public class OrderFrac {
	public static void main(String[] args) {
		int[] c = {2,5};
		int nt = 1;
		for (int d = 9; d <= 1000000; d++) {
			for (int n = nt; (3*d - 7*n) > 0; n++) {
				if (gcd(d,n) == 1 && ((3*d - 7*n)/((double) n*d) < (3*c[1]-7*c[0])/((double) c[0]*c[1]))) {
					c[0] = n;
					c[1] = d;
					System.out.println("Closest "+c[0]+","+c[1]);
					nt = n;
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