package prob045;

public class TriPentHex {

	public static void main(String[] args) {
		long val, test;
		String equal = "";
		
		for (long t = 284; t < Long.MAX_VALUE; t++) {
			val = t%2==0?(t/2)*(t+1):t*((t+1)/2);
			for (long p = 164; p < Long.MAX_VALUE; p++) {
				test = p%2==0?(p/2)*(3*p-1):p*((3*p-1)/2);
				if (test > val) {
					break;
				}
				
				if (test == val) {
					for (long h = 142; h < Long.MAX_VALUE; h++) {
						test = h*(2*h-1);
						if (test > val) {
							break;
						} else if (test == val) {
							equal = "("+t+","+p+","+h+") = "+val;
						}
					} 
				}
			}
			
			System.out.println("Equal: "+equal);
		}
	}
}