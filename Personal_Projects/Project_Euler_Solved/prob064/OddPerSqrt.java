package prob064;

public class OddPerSqrt {

	public static void main(String[] args) {
		int sq = 2, count = 0;
		
		for (int N = 2; N <= 10000; N++) {
			if (N == sq*sq) {
				sq++;
				continue;
			}
			
			int per = 0; //Dont think a is needed for finding repeat BUT int a0 = sq-1; //a0 = floor of sqrt of N, thus sq-1
			boolean repeat = false;
			
			int subtra = sq-1, denom = 1;
			
			while(!repeat) {
				denom = (N - (subtra * subtra)) / denom;
				
				while ((subtra - denom) >= (1 - sq)) {
					subtra -= denom;
				}
				
				subtra *= -1;
				
				if (denom == 1 && subtra == (sq - 1)) {
					repeat = true;
				}
				
				per++;
			}
			
			if (per%2 == 1) {
				count++;
			}
			
			//System.out.println(N);
		}
		
		System.out.println("\n"+count);
	}
}