package prob092;

public class SqDigChain {
	public static void main(String[] args) {
		int count = 0;
		
		for (int s=1; s<10000000; s++) {
			int num = s;
			
			while (true) {
				if (is10Mult(new int[] {1,44,32,13}, num)) {
					System.out.println(s + ": 1");
					break;
				} else if (is10Mult(new int[] {89,145,42,2,4,16,37,58}, num)) {
					System.out.println(s + ": 89");
					count++;
					break;
				} else {
					String str = ""+num;
					int sum = 0;
					for (int i=0; i<str.length(); i++) {
						int n = Integer.parseInt(str.substring(i, i+1));
						sum = sum + n*n;
					}
					num = sum;
				}
			}
		}
		
		System.out.println(count);
	}
	
	private static boolean is10Mult(int[] nums, int num) {
		String str = ""+num;
		while (str.charAt(str.length()-1) == '0') {
			str = str.substring(0, str.length()-1);
		}
		num = Integer.parseInt(str);
		
		for (int n : nums) {
			if (n==num) {
				return true;
			}
		}
		return false;
	}
}