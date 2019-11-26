package prob153;

public class GausInt {
	public static void main(String[] args) {
		double total = 0;
		
		for (int n=1; n<=Math.pow(10, 8); n++) {
			
			for (int x=1; x<=n; x++) {
				if (n%x==0) {
					total = total + x;
				}
			}
			
			for (int a = 1; a <= n/2; a++) {
				for (int b = 1; b <= n/2; b++) {
					int c2 = a*a+b*b;
					if (c2%n==0 || n%c2==0) {
						total = total + a*2;
					}
				}
			}
			
			System.out.println(n);
		}
		
		System.out.println(total);
	}
}