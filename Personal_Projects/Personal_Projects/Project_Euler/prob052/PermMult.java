package prob052;

public class PermMult {

	public static void main(String[] args) {
		String res="";
		for (int i=1; i<=Integer.MAX_VALUE; i++) {
			res = ""+i;
			if (sameDigits(i*2, i)) {
				res = res + " -- " + i*2;
				if (sameDigits(i*3,i)) {
					res = res + " -- " + i*3;
					if (sameDigits(i*4,i)) {
						res = res + " -- " + i*4;
						if (sameDigits(i*5,i)) {
							res = res + " -- " + i*5;
							if (sameDigits(i*6,i)) {
								res = res + " -- " + i*6;
								break;
							}
						}
					}
				}
				
				System.out.println(res);
			}
		}
		
		System.out.println(res);
	}
	
	private static boolean sameDigits(int num, int base) {
		String[] nums = {""+num, ""+base};
		
		if (nums[0].length() != nums[1].length()) {
			return false;
		}
		
		for (int i=0; i<nums[1].length(); i++) {
			String sub = nums[1].substring(i, i+1);
			if (!(nums[0].contains(sub) && nums[0].indexOf(sub) == nums[0].lastIndexOf(sub))) {
				return false;
			}
		}
		
		return true;
	}
}