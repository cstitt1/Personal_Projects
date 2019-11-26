package prob179;

public class ConsPosInt {

	public static void main(String[] args) {
		int count = 0;
		
		for (int n = 2; n < Math.round(Math.pow(10, 7)); n++) {
			if (div(n) == div(n+1)) {
				count++;
				System.out.println("("+n+","+(n+1)+") -- count = "+count);
			}
		}
	}
	
	private static int div(int num) {
		int count = 2;
		for (int i = 2; i <= num/2; i++) {
			if (num%i == 0) {
				count++;
			}
		}
		return count;
	}
}