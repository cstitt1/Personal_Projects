package prob035;

import java.util.ArrayList;

public class CirPri {
	public static void main(String[] args) {
		ArrayList<Integer> primes = new ArrayList<>();
		ArrayList<Integer> bad = new ArrayList<>();
		primes.add(2);
		System.out.println("[2]");
		
		for (int i=3; i<1000000; i+=2) {
			if (primes.contains(i) || bad.contains(i)) {
				continue;
			}
			
			String str = ""+i;
			ArrayList<Integer> rotations = new ArrayList<>();
			rotations.add(i);
			for (int r=1; r<str.length(); r++) {
				int rotation = Integer.parseInt(str.substring(r)+str.substring(0, r));
				if (!rotations.contains(rotation)) {
					rotations.add(rotation);
				}
			}
			
			boolean circular = true;
			for (int rotation : rotations) {
				if (!isPrime(rotation)) {
					circular = false;
					break;
				}
			}
			
			String res = "[";
			for (int rotation : rotations) {
				res = res + rotation + ",";
				if (circular) {
					primes.add(rotation);
				} else {
					bad.add(rotation);
				}
			}
			res = res.substring(0,res.length()-1) + "]";
			
			if (circular) {
				System.out.println(res + ": circular");
			} else {
				System.out.println(res + ": non-circular");
			}
		}
		
		System.out.println(primes.size());
	}
	
	private static boolean isPrime(int i) {
		for (int x=2; x<i; x++)
			if (i%x==0)
				return false;
		return true;
	}
}