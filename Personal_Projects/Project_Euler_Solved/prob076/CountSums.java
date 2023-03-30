package prob076;

public class CountSums {
	private static int[] nums;
	
	public static void main(String[] args) {
		for (int n = 2; n <= 100; n++) {
			nums = new int[n-1];
			for (int x = 1; x < n; x++) {
				nums[x-1] = x;
			}
			System.out.println(n+": "+f2(n,nums.length));
		}
	}
	
	private static int f2(int n, int k) {
		if (n<0 || k<1) {
			return 0;
		} else if (n == 0) {
			return 1;
		} else {
			return f2(n, k-1) + f2(n-nums[k-1],k);
		}
	}
}