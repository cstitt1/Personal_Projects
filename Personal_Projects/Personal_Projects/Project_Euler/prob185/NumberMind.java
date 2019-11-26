package prob185;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class NumberMind {

	public static void main(String[] args) {
		InputStream f = null;
		try {
			f = new FileInputStream("C:\\Users\\cstit\\Documents\\PE185.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(f)));
		HashMap<Long, Integer> guess = new HashMap<>();
		ArrayList<ArrayList<Integer>> nums = new ArrayList<>();
		int length = -1;
		
		while (in.hasNextLine()) {
			String[] line = in.nextLine().split(" ");
			if (line[0].length() > length) {
				length = line[0].length();
			}
			guess.put(Long.parseLong(line[0]), Integer.parseInt(line[1].substring(1)));
		}
		in.close();
		
		for (int i=1; i<=length; i++) {
			if (i==1 && i!=length) {
				nums.add((ArrayList<Integer>) Arrays.asList(new Integer[] {1,2,3,4,5,6,7,8,9}));
				continue;
			}
			
			nums.add((ArrayList<Integer>) Arrays.asList(new Integer[] {0,1,2,3,4,5,6,7,8,9}));
		}
	}
}