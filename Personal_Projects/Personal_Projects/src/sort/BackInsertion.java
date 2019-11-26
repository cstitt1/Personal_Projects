package sort;

public class BackInsertion {
	public static void main(String[] args) {
		int[] test = {0,3,2,-1,17,45,32,6,-180};
		test = sort(test);
		for (int t : test) {
			System.out.print(t+" ");
		}
	}
	
	private static int[] sort(int[] arr) {
		int t, max, ind;
		for (int i=0; i<arr.length-1; i++) {
			max = Integer.MIN_VALUE;
			ind = -1;
			for (int j=arr.length-1; j>=i; j--) {
				if (arr[j] > max) {
					max = arr[j];
					ind = j;
				}
			}
			
			t = arr[i];
			arr[i] = arr[ind];
			arr[ind] = t;
		}
		
		for (int i=0; i<arr.length/2; i++) {
			t = arr[i];
			arr[i] = arr[arr.length-1-i];
			arr[arr.length-1-i] = t;
		}
		
		return arr;
	}
}