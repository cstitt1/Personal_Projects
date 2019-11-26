package prob069;

public class TotMax {
	public static void main(String[] args) {//510510
		int[] max = {Integer.MIN_VALUE,Integer.MIN_VALUE,0}; //q,r,n
		for (int n = 2; n <= 1000000; n+=3) {
			int count = phi(n);
			if (n/count > max[0] || (n/count == max[0] && n%count > max[1])) {
				max[0] = n/count;
				max[1] = n%count;
				max[2] = n;
				System.out.println("Max: "+max[2]);
			}
			
			if (n%3 != 0) {
				n = 3;
			}
			
			if (max[2] % 10 == 0) {
				n += 27;
			}
		}
	}
	
	private static int phi(int n) {
		int count = n-1;
		for (int i = 2; i < n; i++) {
			if (gcd(i,n) != 1) {
				count--;
			}
		}
		return count;
	}
	
	private static int gcd(int a, int b) {
		if (b == 0) {
			return a;
		} else {
			return gcd(b, a%b);
		}
	}
}