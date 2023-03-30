package helper;

import java.io.*;
import java.util.*;

// Input part 1 is their test input
//       part 2 is their test output
//       part 3 is our output for their test input
// Input ends with :end:

// Output is line-by-line the inputs with the different outputs

public class DifferenceAnalyzer {
	
	public static void main(String[] args) throws Exception {
		File dataFile = new File("C:\\Users\\cstit\\OneDrive\\Desktop\\testData.txt");
		Scanner inputLines = new Scanner(dataFile);
		
		ArrayList<ArrayList<String>> data = new ArrayList<>();
		data.add(new ArrayList<String>()); data.add(new ArrayList<String>()); data.add(new ArrayList<String>());
		
		int ind = 0; String inputLine = "";
		while (!inputLine.equalsIgnoreCase(":end:")) {
			inputLine = inputLines.nextLine();
			if (inputLine.charAt(0) - '0' < 10) {continue;}
			if (inputLine.startsWith("Case #1:")) {ind++;}
			data.get(ind).add(inputLine);
		}
		inputLines.close();
		
		listLoop: for (int i = 0; i < 100; i++) {
			String testOut = data.get(1).get(i), ourOut = data.get(2).get(i);
			char[] testArr = testOut.toCharArray(), ourArr = ourOut.toCharArray();
			int[] diffLC = new int[26];
			
			for (int j = 0; j < testArr.length; j++) {
				char testChar = testArr[j];
				if (testChar < 'A' || testChar > 'Z') {continue;}
				diffLC[testChar - 'A']++;
			}
			
			for (int j = 0; j < ourArr.length; j++) {
				char ourChar = ourArr[j];
				if (ourChar < 'A' || ourChar > 'Z') {continue;}
				diffLC[ourChar - 'A']--;
			}
			
			//                          [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
			//                           A  B  C  D  E  F  G  H  I  J  K  L  M  N  O  P  Q  R  S  T  U  V  W  X  Y  Z
			//if (diffLC.equals(new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0})) {continue;}
			for (int j = 0; j < 26; j++) {
				if (diffLC[j] != 0) {break;}
				if (j == 25) {continue listLoop;}
			}
			
			//String prnt = data.get(0).get(i);
			//int firstLen = prnt.length();
			String prnt = "  -- ";
			prnt += data.get(1).get(i);
			System.out.println(prnt);
			
			prnt = "";
			//for (int j = 0; j < firstLen; j++) {prnt += " ";}
			prnt += "  -- ";
			prnt += data.get(2).get(i);
			System.out.println(prnt + "\n");
		}
	}
}