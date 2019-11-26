package sort;

import java.util.Arrays;

public class Heap {
	public static void main(String[] args) {
		int[] test = {0,3,2,-1,17,45,32,6,-180};
		test = sort(test);
		for (int t : test) {
			System.out.print(t+" ");
		}
	}
	
	private static int[] sort(int[] arr) {
		/*Binary Tree:             4(0)
		 *                          /\
		 *                      3(1)  1(2)*/
		
		//Elements in level: 0.5*e^(.6931*level)
		//Start index of level: 2^(level-1)-1
		//Left/right child: 2*i+1/2
		if (arr.length <= 1) {
			return arr;
		}
		
		//Make it so all parents are not less than their children
		int t;
		for (int i=arr.length-1; i>=0; i--) {
			int par = arr[i];
			int left = (2*i+1<arr.length)?arr[2*i+1]:Integer.MIN_VALUE;
			int right = (2*i+2<arr.length)?arr[2*i+2]:Integer.MIN_VALUE;
			
			while(par < left || par < right) {
				if (par < left) {
					t = par;
					par = left;
					left = t;
					arr[i] = par;
					arr[2*i+1] = left;
				}
				
				if (par < right) {
					t = par;
					par = right;
					right = t;
					arr[i] = par;
					arr[2*i+2] = right;
				}
			}
		}
		
		//Swap last and first
		t = arr[arr.length-1];
		arr[arr.length-1] = arr[0];
		arr[0] = t;
		
		//Eliminate last (greatest) and repeat, but come back and add on at end
		t = arr[arr.length-1];
		arr = Arrays.copyOf(sort(Arrays.copyOf(arr, arr.length-1)),arr.length);
		arr[arr.length-1] = t;
		return arr;
	}
}