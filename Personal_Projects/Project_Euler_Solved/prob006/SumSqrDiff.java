package prob006;
public class SumSqrDiff {
	public static void main(String[] args) {
		int sqSum = 0;
		int sumSq = 0;
		for (int i=1; i<=100; i++) {
			sqSum = sqSum + (int) Math.pow(i, 2);
			sumSq = sumSq + i;
		}
		
		System.out.println(Math.abs(sqSum-(int)(Math.pow(sumSq, 2))));
	}
}