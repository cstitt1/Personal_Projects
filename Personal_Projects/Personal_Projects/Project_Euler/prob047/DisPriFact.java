package prob047;

public class DisPriFact {
	public static void main(String[] args) {
		int[] nums = new int[4];
		int ind = 0;
		
		for (int i=2; i<=Integer.MAX_VALUE; i++) {
			if (primeFactCount(i)==4) {
				nums[ind] = i;
				System.out.println(ind+": "+i);
				ind++;
			} else {
				if (ind != 0) {
					nums = new int[4];
					ind=0;
					System.out.println("-------------------------------------");
				}
			}
			
			if (ind==4) {
				break;
			}
		}
		
		System.out.println(nums[0]);
	}
	
	private static int primeFactCount(int i) {
		int count = 0;
		
		for (int x=2; x<i; x++)
			if (i%x==0 && isPrime(x))
				count++;
		return count;
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
}