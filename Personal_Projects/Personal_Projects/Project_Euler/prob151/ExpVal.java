package prob151;

import java.util.ArrayList;
import java.util.HashMap;

public class ExpVal {
	public static void main(String[] args) {
		HashMap<int[],String> last = new HashMap<>();
		ArrayList<String> prob = new ArrayList<>();
		last.put(new int[] {1,1,1,1},"1"); //smallest to largest -- A5 to A2
		
		for (int b = 2; b <= 15; b++) {
			HashMap<int[],String> next = new HashMap<>();
			for (int k=0; k<last.keySet().size(); k++) {
				int[] combo = (int[]) last.keySet().toArray()[k];
				for (int i=0; i<combo.length; i++) {
					if (combo[i]==0) {
						continue;
					}
					
					int[] pick = new int[4];
					for (int j=0; j<combo.length; j++) {
						if (j<i) {
							pick[j] = combo[j]+1;
						} else if (j==i) {
							pick[j] = combo[j]-1;
						} else {
							pick[j] = combo[j];
						}
					}
					
					if (next.containsKey(pick)) {
						next.put(pick, next.get(pick)+k);
					} else {
						next.put(pick, ""+k);
					}
				}
			}
		}
	}
	
	private static int sumArray(int[] nums) {
		int sum = 0;
		
		for (int num : nums) {
			sum = sum + num;
		}
		
		return sum;
	}
}