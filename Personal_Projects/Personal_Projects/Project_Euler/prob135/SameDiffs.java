package prob135;

public class SameDiffs {

	public static void main(String[] args) {
		int tenCount = 0;
		
		//                  1,000,000
		for (long n = 1; n <= 1000000; n++) {
			int zCount = 0; String zHelper = "";
			
			for (long lmbd = 1; true; lmbd++) {
				long sqrtNum = lmbd * lmbd - 4 * n;
				if (sqrtNum < 0) {continue;}
				long sqrtRes = Math.round(Math.sqrt(sqrtNum));
				if (1 + sqrtRes > n && (1 - sqrtRes > n || 1 - sqrtRes < 0)) {break;}
				if (sqrtRes * sqrtRes != sqrtNum) {continue;}
				
				long[] d0Arr = {1 + sqrtRes, 1 - sqrtRes};
				if (d0Arr[0] > n && d0Arr[1] > n) {break;}
				for (long d0 : d0Arr) {
					if (d0 < 0 || d0 % 2 == 0 || d0 > n) {continue;}
					long mStart = ((d0 - 1) * (d0 - 1)) + (4 * n);
					long m1 = Math.round(Math.sqrt(mStart));
					if (m1 * m1 != mStart) {continue;}
					long m2 = (m1 + 1) - d0;
					long m = Math.round(m2 / 2.0);
					if (m * 2 != m2) {continue;}
				}
			}
			
			//for (long oddStart = 1; oddStart <= n; oddStart+=2) {
				//System.out.println(oddStart + "," + m);
				
				/*int oddSum = 0;
				for (int i = oddStart; i > 0; i+=2) {
					oddSum += i;
					
					if (oddSum == n) {
						int theta = (oddStart - 1) / 2;
						int alpha = (int) Math.round(Math.sqrt(n + theta * theta)/2);
						int z1 = alpha + theta, z2 = alpha - theta;
						String combo1 = "(" + z1 + "," + alpha + ")", combo2 = "(" + z2 + "," + alpha + ")";
						
						if (z1 >= 0 && !zHelper.contains(combo1)) {zCount++; zHelper += combo1;}
						if (z2 >= 0 && !zHelper.contains(combo2)) {zCount++; zHelper += combo2;}
						
						break;
					}
					
					if (oddSum > n) {break;}
				}*/
			//}
			
			if (zCount == 10) {tenCount++; System.out.println(n + ": " + zCount + "\n ---------------------------------------------- \n");}
		}
		
		System.out.println(tenCount);
	}
}