package prob028;

public class NumSpiDiag {
	public static void main(String[] args) {
		int by = 1001, sum = 0, pow;
		
		for (int i = 1; i <= by; i++) {
			pow = i*i;
			sum += pow;
			if ((pow)%2 == 0) {
				sum++;
				sum += (pow+1)-i;
				sum += (pow+1)+i;
			}
		}
		
		System.out.println(sum);
	}
}