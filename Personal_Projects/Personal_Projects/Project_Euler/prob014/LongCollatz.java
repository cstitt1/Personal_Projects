package prob014;

import java.util.ArrayList;

public class LongCollatz {

	public static void main(String[] args) {
		int max=0;
		ArrayList<Integer> starts = new ArrayList<>();
		for (int i=2; i<1000000; i++) {
			int count=1;
			double n=i;
			while (n>1) {
				if (n%2==0) {
					n = n/2;
				} else {
					n = 3*n + 1;
				}
				count++;
			}
			
			if (count > max) {
				max = count;
				starts.clear();
				starts.add(i);
			} else if (count==max) {
				starts.add(i);
			}
			
			System.out.println("Count for "+i+" is "+count);
		}
		
		for (int start : starts) {
			System.out.print(start+", ");
		}
	}

}
