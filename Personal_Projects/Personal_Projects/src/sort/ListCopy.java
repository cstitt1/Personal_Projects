package sort;
import java.util.*;

public class ListCopy {
	public static void main(String[] args) {
		int[] test = {0,3,2,-1,17,45,32,6,-180};
		test = sort(test);
		for (int t : test) {
			System.out.print(t+" ");
		}
	}
	
	private static int[] sort (int[] arr) {
		if (arr.length <= 1) {
			return arr;
		}
		
		ArrayList<Integer> list = new ArrayList<>(0);
		
		for (int elem : arr) {
			for (int j=0; j<list.size(); j++) {
				if (elem < list.get(j)) {
					list.add(j, elem);
				}
			}
			list.add(elem);
		}
		
		for (int i = 0; i<list.size(); i++) {
			arr[i] = list.get(i);
		}
		
		return arr;
	}
}