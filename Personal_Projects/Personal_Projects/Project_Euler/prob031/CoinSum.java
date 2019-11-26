package prob031;

import java.util.Arrays;

public class CoinSum {
	private static int[] val = {2, 5, 10, 20, 50, 100};
	private static int[] coins = {1, 2, 5, 10, 20, 50, 100, 200};
	
	public static void main(String[] args) {
		int count = 2;
		
		System.out.println("0×£1 + 0×50p + 0×20p + 0x10p + 0×5p + 0×2p + 200×1p -- count = 1");
		count += comb(200, val.length);
		System.out.println("1x£2 -- count = "+count+"\n");
		
		System.out.println("Count = "+f(200, new int[] {1,2,5,10,20,50,100,200}));
		System.out.println("Count = "+f2(200, coins.length));
	}
	
	private static int comb(int total, int end) {
		if (total == 0) {
			return 1;
		}
		int count = 0;
		
		for (int i=0; i<end; i++) {
			if (i == 0) {
				count += total/val[i];
				continue;
			}
			
			for (int add = (total==200?1:0); total-(val[i]*add)>=0; add++) {
				count += comb(total-(val[i]*add), i);
				
				if (total == 200) {
					System.out.println(add+"x"+val[i]+"p -- count = "+count);
				}
			}
		}
		
		return count;
	}
	
	private static int f(int x, int[] vals) {
		int len = vals.length;
		if (x == 0 || len == 1) {
			return 1;
		}
		
		int val = vals[len-1], count = 1;
		for (int n=1; n <= x/val; n++) {
			count = count + f(n*val, Arrays.copyOf(vals, len-1));
		}
		
		return count;
	}
	
	private static int f2(int n, int k) {
		if (n<0 || k<1) {
			return 0;
		} else if (n == 0) {
			return 1;
		} else {
			return f2(n, k-1) + f2(n-coins[k-1],k);
		}
	}
}