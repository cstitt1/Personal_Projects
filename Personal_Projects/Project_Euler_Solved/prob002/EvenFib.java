package prob002;

public class EvenFib {

	public static void main(String[] args) {
		int sum = 0;
		int preFib = 0;
		int fib = 1;
		while(fib <= 4000000) {
			if (fib%2==0) {
				sum += fib;
			}
			int temp = fib;
			fib += preFib;
			preFib = temp;
		}
		System.out.println(sum);
	}

}
