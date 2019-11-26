package prob067;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MaxPathSum2 {
	public static void main(String[] args) {
		InputStream f = null;
		try {
			f = new FileInputStream("C:\\Users\\cstit\\Documents\\PE067.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(f)));
		
		ArrayList<int[]> rows = new ArrayList<>();
		rows.add(new int[] {Integer.parseInt(in.nextLine())});
		while (in.hasNextLine()) {
			String[] line = in.nextLine().split(" ");
			int[] linI = new int[line.length];
			for (int i=0; i<line.length; i++) {
				linI[i] = Integer.parseInt(line[i]);
			}
			rows.add(linI);
		}
		in.close();
		
		int max = rows.get(rows.size()-1).length;
		for (int i=0; i<rows.size()-1; i++) {
			rows.set(i, Arrays.copyOf(rows.get(i), max));
		}
		
		System.out.println(sum(rows, rows.size()));
	}
	
	private static int sum(ArrayList<int[]> rows, int elems) {
		if (elems == 1) {
			return rows.get(0)[0];
		}
		
		int[] max = new int[elems], row = rows.get(elems-1), above = rows.get(elems-2);
		
		for (int i=0; i<row.length-1; i++) {
			max[i] = (row[i]>row[i+1]?row[i]:row[i+1]) + above[i];
		}
		
		rows.set(elems-2, max);
		rows.remove(elems-1);
		elems--;
		
		return sum(rows, elems);
	}
}