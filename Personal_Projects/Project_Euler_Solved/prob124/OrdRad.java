package prob124;

import java.util.*;

public class OrdRad {
	public static void main(String[] args) {
		HashMap<Integer, Integer> unsorted = new HashMap<>();
		ArrayList<Integer> sorted = new ArrayList<>();
		sorted.add(0);
		
		for (int n = 1; n <= 100000; n++) {
			int rad = rad(n);
			unsorted.put(n, rad);
			System.out.println(n + ": " + rad);
		}
		
		sorted.addAll(unsorted.values());
		sorted.sort(null);
		
		int e = sorted.get(10000);
		int posE = 1;
		
		for (int i = 9999; i >= 0; i--) {
			if (sorted.get(i) == e) {
				posE++;
			} else {
				break;
			}
		}
		
		int count = 0;
		for (int key = 1; key <= unsorted.size(); key++) {
			if (unsorted.get(key) == e) {
				count++;
				
				if (count==posE) {
					System.out.println(key);
				}
			}
		}
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