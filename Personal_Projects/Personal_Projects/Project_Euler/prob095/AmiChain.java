package prob095;

import java.util.Arrays;
import java.util.HashMap;

public class AmiChain {

	public static void main(String[] args) {
		HashMap<Long, Integer> chains = new HashMap<>();
		long maxC = Integer.MIN_VALUE, maxI = -1;
		for (long i = 6; i <= 1000000; i++) {
			if (chains.containsKey(i)) {
				continue;
			}
			
			long[] arr = {i};
			int count = 0;
			long nxt = i;
			System.out.print("[");
			do {
				System.out.print(nxt+",");
				nxt = spd(nxt);
				if (chains.containsKey(nxt) || nxt > i) {
					count = -1;
					break;
				}
				count++;
				arr = Arrays.copyOf(arr, arr.length+1);
				arr[arr.length-1] = nxt;
				
				if (nxt == spd(nxt) || nxt > 1000000) {
					count = -1;
					break;
				}
			} while (nxt != i);
			System.out.print("] ");
			
			for (long a : arr) {
				chains.put(a, count);
			}
			
			if (count > maxC) {
				maxC = count;
				Arrays.sort(arr);
				maxI = arr[0];
			}
			
			System.out.println("Max: "+maxI+" for chain of length "+maxC);
		}
	}
	
	private static long spd(long num) {
		long sum = 1;
		
		for (int n = 2; n <= Math.round(Math.floor(Math.sqrt(num))); n++) {
			if (n*n == num) {
				sum += n;
			} else if (num%n == 0) {
				sum += (n + num/n);
			}
		}
		
		return sum;
	}
}