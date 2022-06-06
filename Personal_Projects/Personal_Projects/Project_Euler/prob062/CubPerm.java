package prob062;

import java.util.HashMap;
import java.util.Map;

public class CubPerm {

	public static void main(String[] args) {
		// number permutation = same number of each digit
		// track number of 0s, 1s, 2s, etc in each cube
		String[] numTrack = new String[2097152];
		
		for (int i = 0; i < numTrack.length; i++) {
			//                 0s 1s 2s 3s 4s 5s 6s 7s 8s 9s
			int[] currTrack = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			long cube = i; cube = cube * i; cube = cube * i;
			
			while (cube > 0) {
				currTrack[(int) (cube%10)]++;
				cube /= 10;
			}
			
			numTrack[i] = "";
			for (int digit : currTrack) {
				numTrack[i] = numTrack[i] + digit + ",";
			}
		}
		
		Map<String, Integer> uniqueMap = new HashMap<String, Integer>();
		for (int dCount = 0; dCount < numTrack.length; dCount++) {
			String digitStr = numTrack[dCount];
			if (uniqueMap.keySet().contains(digitStr)) {
				int old_val = uniqueMap.get(digitStr);
				uniqueMap.put(digitStr, old_val + 1);
			} else {
				uniqueMap.put(digitStr, 1);
			}
		}
		
		int min = 0;
		String valKeys = "";
		for (String key : uniqueMap.keySet()) {
			String[] digitCounts = new String[10];
			int sum = 0;
			
			if (uniqueMap.get(key) == 5) {
				digitCounts = key.split(",");
				for (String digit : digitCounts) {
					sum = sum + Integer.decode(digit);
				}
				
				if (min == 0 || min >= sum) {
					min = sum;
					System.out.println(key + ": " + sum);
					valKeys = valKeys + key + ";";
				}
			}
		}
		
		System.out.println("\n");
		for (long i = 1; i > 0; i++) {
			long cube = i * i * i; long cubeHold = cube;
			String lenCheck = ""+cube;
			
			if (lenCheck.length() == 12) {
				int[] currTrack = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
				while (cube > 0) {
					currTrack[(int) (cube%10)]++;
					cube /= 10;
				}
				
				String currStr = "";
				for (int digit : currTrack) {
					currStr = currStr + digit + ",";
				}
				
				if (valKeys.contains(currStr)) {System.out.println(cubeHold);}
			} else if (lenCheck.length() > 12) {
				return;
			}
		}
	}
}