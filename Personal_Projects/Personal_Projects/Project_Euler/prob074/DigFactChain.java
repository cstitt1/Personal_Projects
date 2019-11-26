package prob074;

import java.util.ArrayList;

public class DigFactChain {
	public static void main(String[] args) {
		int count = 0;
		
		for (int start = 0; start < 1000000; start++) {
			ArrayList<Integer> terms = new ArrayList<>();
			terms.add(start);
			int num = start;
			
			while (true) {
				num = dfs(num);
				if (terms.contains(num)) {
					break;
				} else {
					terms.add(num);
				}
			}
			
			if (terms.size()==60) {
				count++;
			}
			System.out.println(start + ": " + terms.size());
		}
		
		System.out.println(count);
	}
	
	private static int dfs(int num) {
//      0 1 2 3 4  5   6   7    8     9
		int[] fact = {1,1,2,6,24,120,720,5040,40320,362880};
		String str = ""+num;
		int sum = 0;
		
		for (int i=0; i<str.length(); i++) {
			sum = sum + fact[Integer.parseInt(str.substring(i, i+1))];
		}
		
		return sum;
	}
}