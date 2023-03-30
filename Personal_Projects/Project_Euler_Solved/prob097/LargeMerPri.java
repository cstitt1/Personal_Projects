package prob097;

public class LargeMerPri {
	public static void main(String[] args) {
		int[] digits = new int[10];
		digits[0] = 1;
		for (int i=1; i<=7830457; i++) {
			digits = digitMult(digits, 2);
		}
		
		digits = digitMult(digits, 28433);
		digits[0] = digits[0] + 1;
		
		String res = "";
		for (int i=digits.length-1; i>=0; i--) {
			res = res + digits[i];
		}
		
		System.out.println(res);
	}
	
	private static int[] digitMult(int[] digits, int mult) {
		for (int i=0; i<digits.length; i++) {
			digits[i]=digits[i]*mult;
		}
		
		for (int i=0; i<digits.length-1; i++) {
			while (digits[i] >= 10) {
				digits[i] = digits[i] - 10;
				digits[i+1] = digits[i+1] + 1;
			}
		}
		
		while (digits[digits.length-1] >= 10) {
			digits[digits.length-1] = digits[digits.length-1] - 10;
		}
		
		return digits;
	}
}