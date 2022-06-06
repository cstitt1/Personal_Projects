package prob059;

import java.io.*;
import java.util.*;

public class XorDecTesting {

	public static void main(String[] args) throws IOException {
		String file = "C:\\Users\\cstit\\OneDrive\\Desktop\\testing.txt";
		Scanner inp = new Scanner(new FileReader(file));
		String csv = inp.nextLine();
		inp.close();
		
		System.out.println(csv);
		String[] charsArr = csv.split(",");
		int[] charArr = new int[charsArr.length];
		
		int cntr = 0;
		for (String charS : charsArr) {
			charArr[cntr] = (int) charS.charAt(0);
			System.out.print(charArr[cntr] + ",");
			cntr++;
		}
		System.out.println("");
		
		for (int chr : charArr) {
			int xor_key = 111;
			int xor_result = chr ^ xor_key;
			System.out.print(xor_result + ",");
		}
		System.out.println("");
	}
}