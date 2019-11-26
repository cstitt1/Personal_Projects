package sort;

public class Merge {
	public static void main(String[] args) {
		int[] test = {0,3,2,-1,17,45,32,6,-180};
		for (int t : sort(test)) {
			System.out.print(t+" ");
		}
	}
	
	private static int[] sort(int[] arr) {
		if (arr.length<=1) {
			return arr;
		}
		
		//Split in half (right gets extra iff odd number of elements)
		int rL = arr.length%2==0?arr.length/2:arr.length/2+1;
		int[] left = new int[arr.length/2], right = new int[rL];
		for (int i=0; i<arr.length; i++) {
			if (i<left.length) {
				left[i] = arr[i];
			} else {
				right[i-left.length] = arr[i];
			}
		}
		
		//Sort halves
		left = sort(left);
		right = sort(right);
		
		//Merging from two so that combined in order
		int li = 0, ri = 0;
		int[] both = new int[left.length+right.length];
		for (int i=0; i<both.length; i++) {
			if (li>=left.length) {
				both[i] = right[ri++];
			} else if (ri>=right.length) {
				both[i] = left[li++];
			} else {
				both[i] = left[li]>right[ri]?right[ri++]:left[li++];
			}
		}
		
		return both;
	}
}