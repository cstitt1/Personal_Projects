package roundE;

import java.io.*;
import java.util.*;

public class ColoringGame {
	
	public static void main(String[] args) {
		Scanner inputLines = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
		int numCases = inputLines.nextInt(); inputLines.nextLine();
		for (int caseNum = 1; caseNum <= numCases; caseNum++) {
			int valN = inputLines.nextInt(); inputLines.nextLine(); valN--;
			
			int num5 = valN / 5; // Integer division
			
			System.out.println("Case #" + caseNum + ": " + (num5 + 1));
		} inputLines.close();
	}
}