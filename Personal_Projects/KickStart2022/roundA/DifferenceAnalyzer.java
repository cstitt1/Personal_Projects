package roundA;

import java.io.*;
import java.util.*;

// Input part 1 is their test input
//       part 2 is their test output
//       part 3 is our output for their test input
// Input ends with :end:

// Output is line-by-line the inputs with the different outputs

public class DifferenceAnalyzer {
	
	public static void main(String[] args) throws Exception {
		Scanner inputLines = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
		String[] in = new String[100], out1 = new String[100], out2 = new String[100];
		String[][] data = new String[][] {in, out1, out2};
		
		for (int dt = 0; dt < 3; dt++) {
			for (int line = 0; line < 100; line++) {
				data[dt][line] = inputLines.nextLine();
			}
		}
		
		for (int i = 0; i < 100; i++) {
			if (!data[1][i].equals(data[2][i])) {
				System.out.println(data[0][i] + " -- " + data[1][i] + " vs " + data[2][i]);
			}
		}
	}
}