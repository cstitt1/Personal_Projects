package prob207;

public class IntPartEqs {
	public static void main(String[] args) {
		int perf = 1, total = 1, pow = 2;
		
		for (long u = 3; ((double)perf)/total >= 1.0/12345; u++) {
			while (u > pow) {
				pow *= 2;
			}
			
			total++;
			long k = u*u - u;
			if (pow == u) {
				perf++;
			}
			
			System.out.println(k + ": " + perf + "/" + total);
		}
	}
}