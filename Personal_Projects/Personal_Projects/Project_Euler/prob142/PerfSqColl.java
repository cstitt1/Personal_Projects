package prob142;

public class PerfSqColl {

	public static void main(String[] args) {
		long limit = 10000;
		
		for (long z = 1; z < limit; z++) {
			for (long y = z+1; y < limit; y++) {
				for (long x = y+1; x < limit; x++) {
					if (pS(x+y) && pS(x-y) && pS(x+z) && pS(x-z) && pS(y+z) && pS(y-z)) {
						System.out.println(x+"+"+y+"+"+z);
					}
				}
			}
		}
	}
	
	private static boolean pS(long num) {
		long rt = Math.round(Math.sqrt(num));
		return (rt*rt) == num;
	}
}