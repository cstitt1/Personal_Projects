package prob101;

import java.util.Arrays;

public class OptPoly {
	public static void main(String[] args) {
		//u(n) = 1 - n + n^2 - n^3 + n^4 - n^5 + n^6 - n^7 + n^8 - n^9 + n^10
		//            0 1   2     3      4       5        6         7         8           9           10           11
		long[] seq = {1,683,44287,838861,8138021,51828151,247165843,954437177,3138105961l,9090909091l,23775972551l,57154490053l};
		long sum = 1;
		String res = "1 + ";
		for (int k = 2; k < 12; k++) {
			long[][] coeff = new long[k][2];
			coeff = diff(Arrays.copyOf(seq, k),coeff);
		}
	}
	
	private static long[][] diff(long[] arr, long[][] res) {
		return res; //IDK WE NEED GAUSS-JORDAN I THINK. AHHHH
	}
	
	private static int fact(int n) {
		if (n <= 1) {
			return 1;
		} else {
			return fact(n-1) * n;
		}
	}
}