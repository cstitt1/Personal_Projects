package prob127;

import java.util.ArrayList;

public class ABCHits {
	public static void main(String[] args) {
		int sum = 0;
		
		for (int a = 1; a < 120000/2; a++) {
			for (int b = a+1; b <= 120000-a; b++) {
				int c = a+b;
				if (rad(a*b*c)<c && gcd1(new int[] {a,b}) && gcd1(new int[] {c,b}) && gcd1(new int[] {a,c})) {
					sum = sum + c;
					System.out.println("("+a+","+b+","+c+")");
				}
			}
		}
		
		System.out.println(sum);
	}
	
	private static boolean gcd1(int[] terms) {
		ArrayList<ArrayList<Integer>> facs = new ArrayList<>();
		
		for (int i=0; i<terms.length; i++) {
			facs.add(new ArrayList<>());
		}
		
		for (int i=0; i<facs.size(); i++) {
			for (int j=2; j<=terms[i]; j++) {
				if (isPrime(j) && terms[i]%j==0) {
					facs.get(i).add(j);
				}
			}
		}
		
		for (int i=0; i<facs.size(); i++) {
			for (int fac : facs.get(i)) {
				if (facs.get(1-i).contains(fac)) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	private static int rad(int n) {
		int product = 1;
		
		for (int i = 2; i <= n; i++) {
			if (n%i==0 && isPrime(i)) {
				product = product * i;
			}
		}
		
		return product;
	}
	
	private static boolean isPrime(int i) {
		if (i==1) {
			return false;
		}
		
		for (int x=2; x<i; x++)
			if (i%x==0)
				return false;
		return true;
	}
}