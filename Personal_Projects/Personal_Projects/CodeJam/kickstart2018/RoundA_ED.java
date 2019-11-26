package kickstart2018;

import java.io.*;
import java.util.*;

public class RoundA_ED {

	public static void main(String[] args) throws IOException {
		String file = "C:\\Users\\cstit\\Desktop\\p107_network.txt";
		Scanner inp = new Scanner(new FileReader(file));
		int t = Integer.parseInt(inp.nextLine());
		
		for (int i = 1; i <= t; i++) {
			char[] line = inp.nextLine().toCharArray();
			for (int j = 0; j < line.length; j++) {
				int num = line[j] - 48;
				if (num % 2 == 0) {
					
				}
			}
		}
		
		inp.close();
	}
}