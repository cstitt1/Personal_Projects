package prob039;

public class IntRightTri {
	public static void main(String[] args) {
		int[] pVals = new int[1001]; //p = 0 through and including p = 1000
		
		for (int a = 1; a <= 1000; a++) {
			for (int b = 1; a + b <= 1000; b++) {
				int c2 = a*a + b*b;
				int c = (int) Math.round(Math.sqrt(c2));
				
				if (c*c == c2 && a+b+c <= 1000) {
					pVals[a+b+c]++;
				}
			}
		}
		
		int maxC = 0, maxP = 0;
		for (int i = 0; i < pVals.length; i++) {
			if (maxC < pVals[i]) {
				maxP = i;
				maxC = pVals[i];
			}
		}
		
		System.out.println(maxP);
	}
}