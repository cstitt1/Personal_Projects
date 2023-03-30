package prob044;

public class PentNum {
	public static void main(String[] args) {
		long min = Integer.MAX_VALUE;
		
		for (long k = 2; k*k > 0 && k*k < Integer.MAX_VALUE; k++) {
			for (long j = 1; j < k; j++) {
				long sum = 3*(k*k + j*j) - (k+j);
				long diff = 3*(k*k - j*j) - (k-j);
				
				long nS = Math.round((Math.sqrt(12*sum+1)+1)/6.0);
				long nD = Math.round((Math.sqrt(12*diff+1)+1)/6.0);
				
				if (sum == (3*nS*nS - nS) && diff == (3*nD*nD - nD) && diff/2 < min) {
					min = diff/2;
					System.out.println("P"+j+" and P"+k+" -- min = "+min);
				}
			}
		}
	}
}