package prob022;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.ArrayList;

public class NameScore {
	public static void main(String[] args) {
		InputStream f = null;
		try {
			f = new FileInputStream("C:\\Users\\cstit\\Documents\\PE022.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(f)));
		String[] names = (in.nextLine()).split(",");
		in.close();
		
		names = Nsort(names);
		
		ArrayList<Double> nums = new ArrayList<>();
		nums.add(0d);
		for (int i=0; i<names.length; i++) {
			double val = val(names[i])*(i+1);
			
			if (nums.get(0)+val < 0 || nums.get(0)+val < nums.get(0)) {
				nums.add(0, val);
			} else {
				nums.set(0, nums.get(0)+val);
			}
		}
		
		for (double n : nums) {
			System.out.println(n);
		}
	}
	
	private static double val(String str) {
		double sum = 0;
		for (char c : str.toLowerCase().toCharArray()) {
			if (c != '"') {
				sum += (c-96);
			}
		}
		return sum;
	}
	
	private static String[] Nsort(String[] arr) {
		if (arr.length<=1) {
			return arr;
		}
		
		//Split in half (right gets extra iff odd number of elements)
		int rL = arr.length%2==0?arr.length/2:arr.length/2+1;
		String[] left = new String[arr.length/2], right = new String[rL];
		for (int i=0; i<arr.length; i++) {
			if (i<left.length) {
				left[i] = arr[i];
			} else {
				right[i-left.length] = arr[i];
			}
		}
		
		//Sort halves
		left = Nsort(left);
		right = Nsort(right);
		
		//Merging from two so that combined in order
		int li = 0, ri = 0;
		String[] both = new String[left.length+right.length];
		for (int i=0; i<both.length; i++) {
			if (li>=left.length) {
				both[i] = right[ri++];
			} else if (ri>=right.length) {
				both[i] = left[li++];
			} else {
				both[i] = left[li].compareToIgnoreCase(right[ri])>0?right[ri++]:left[li++];
			}
		}
		
		return both;
	}

}