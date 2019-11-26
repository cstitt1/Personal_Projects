package prob043;

import java.util.Arrays;

public class SubDiv {
	public static void main(String[] args) {
		long sum = 0;
		
		long mS = 1023456789l/9, mF = 9876543210l/9;
		for (long m = mS; m <= mF; m++) {
			int[] d = new int[10];
			String num = ""+m*9;
			for (int i=d.length-1; i >= 0; i--) {
				d[i] = Integer.parseInt(num.substring(i,i+1));
			}
			
			if (d[3]%2==0 && (d[2]+d[3]+d[4])%3==0 && d[5]%5==0 && (d[4]*100+d[5]*10+d[6])%7==0 && (d[5]*100+d[6]*10+d[7])%11==0 && (d[6]*100+d[7]*10+d[8])%13==0) {
				if ((d[7]*100+d[8]*10+d[9])%17==0 && is09P(d)) {
					sum += m*9;
					System.out.println("Added "+m*9+" -- sum = "+sum);
				}
			}
		}
	}
	
	private static boolean is09P(int[] d) {
		boolean[] cont = new boolean[10];
		for (int dig : d) {
			cont[dig] = true;
		}
		
		return !Arrays.toString(cont).contains("false");
	}
}