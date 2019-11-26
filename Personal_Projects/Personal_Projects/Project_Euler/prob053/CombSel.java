package prob053;

public class CombSel {
	public static void main(String[] args) {
		double[] last = {1,1};
		double count = 0;
		
		for (int n=3; n<=100; n++) {
			double[] next = new double[n];
			next[0] = 1;
			next[n-1] = 1;
			
			for (int i=1; i<n-1; i++) {
				next[i] = last[i-1] + last[i];
				if (next[i] > 1000000) {
					count++;
					System.out.println(n+"C"+i+": "+next[i]);
				}
			}
			
			last = next;
		}
		
		System.out.println(count);
		
		System.out.println("------------------------------");
		
		count=0;
		for (int n = 1; n <= 100; n++) {
			for (int r = 0; r <= n; r++) {
				if (nCr(n,r) > 1000000) {
					count++;
					System.out.println(n+"C"+r);
				}
			}
		}
		System.out.println(count);
	}
	
	private static double nCr(int n, int r) {
		if (n==r || r==0) {
			return 1;
		}
		
		return (fact(n))/((fact(r))*(fact(n-r)));
	}
	
	private static double fact(double n) {
		if (n==1 || n==0) {
			return 1;
		}
		
		return n*fact(n-1);
	}
}