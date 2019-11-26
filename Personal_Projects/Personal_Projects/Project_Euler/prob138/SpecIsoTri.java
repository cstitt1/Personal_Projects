package prob138;

public class SpecIsoTri {
	public static void main(String[] args) {
		double sum = 0;
		int count = 0;
		for (double b=2; b<Double.MAX_VALUE && count < 12; b+=2) {
			double pL2 = (b/2)*(b/2) + (b+1)*(b+1);
			double nL2 = (b/2)*(b/2) + (b-1)*(b-1);
			
			if (nL2 < 0) {
				break;
			}
			
			double nL = Math.round(Math.sqrt(nL2));
			if (Math.abs(nL * nL - nL2) < 0.0005) {
				count++;
				sum += nL;
				System.out.println("Added ("+b+","+(b-1)+","+nL+") -- sum = "+sum);
			}
			
			if (pL2 > 0) {
				double pL = Math.round(Math.sqrt(pL2));
				if (Math.abs(pL * pL - pL2) < 0.0005) {
					count++;
					sum += pL;
					System.out.println("Added ("+b+","+(b+1)+","+pL+") -- sum = "+sum);
				}
			}
		}
	}
}