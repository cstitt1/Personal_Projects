package prob108;

public class DioRecip1 {
	public static void main(String[] args) {
		int num = 1, max = -1;
		for (int n = 4; num < 1000; n++) {
			num = 1;
			for (long x = 2*n-1; x>n; x--) {
				long m = x*n, d = x-n;
				if ((m/d)*d == m) {
					num++;
				}
			}
			
			if (num > max) {
				max = num;
			}
			System.out.println(n+": "+num+" -- "+max);
		}
	}
}