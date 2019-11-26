package sort;

public class FrontInsertion {
	public static void main(String[] args) {
		int[] test = {0,3,2,-1,17,45,32,6,-180};
		test = sort(test);
		for (int t : test) {
			System.out.print(t+" ");
		}
	}
	
	private static int[] sort(int[] arr) {
		int t, min, ind;
		for (int i=0; i<arr.length-1; i++) {
			min = Integer.MAX_VALUE;
			ind = -1;
			for (int j=i; j<arr.length; j++) {
				if (arr[j] < min) {
					min = arr[j];
					ind = j;
				}
			}
			
			t = arr[i];
			arr[i] = arr[ind];
			arr[ind] = t;
		}
		
		return arr;
	}
}