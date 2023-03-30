package prob049;

public class PriPerm {
	public static void main(String[] args) {
		int a=-1, b=-1, c=-1;
		
		for (int i=1001; i<=9999; i++) {
			if (isPrime(i)) {
				a=i;
			} else {
				continue;
			}
			
			for (int j=a+1; j<=9999; j++) {
				if (j>a && isPrime(j) && isPerm(""+j,""+a)) {
					b=j;
				} else {
					continue;
				}
				
				for (int k=b+1; k<=9999; k++) {
					if (k>b && isPrime(k) && isPerm(""+k,""+a) && isPerm(""+b,""+k) && (b-a==k-b)) {
						c=k;
						System.out.println(a+","+b+","+c);
					}
				}
			}
		}
	}
	
	private static boolean isPrime(int i) {
		if (i==1) {
			return false;
		}
		
		for (int x=2; x<i; x++)
			if (i%x==0)
				return false;
		return true;
	}
	
	private static boolean isPerm(String num1, String num2) {
		String[] nums = {num1, "", num2};
		int[] counts = new int[10];
		
		for (int i=0; i<nums.length; i+=2) {
			for (int j=0; j<nums[i].length(); j++) {
				int k = Integer.parseInt(nums[i].substring(j, j+1));
				counts[k] = counts[k] + (1-i);
			}
		}
		
		for (int count : counts) {
			if (count!=0) {
				return false;
			}
		}
		return true;
	}
}