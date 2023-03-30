package prob099;

import java.io.*;
import java.util.*;

public class LorgExp {

	public static void main(String[] args) throws IOException {
		String file = "C:\\Users\\cstit\\Desktop\\p099_base_exp.txt";
		Scanner inp = new Scanner(new FileReader(file));
		
		int mLine = -1, mBase = 1, mExp = 0;
		for (int i = 0; i < 1000; i++) {
			String[] line = inp.nextLine().split(",");
			int[] vals = {Integer.parseInt(line[0]),Integer.parseInt(line[1])};
			
			if (mExp*(Math.log(mBase)/Math.log(vals[0])) < vals[1]) {
				mLine = i+1;
				mBase = vals[0];
				mExp = vals[1];
			}
		}
		
		inp.close();
		
		System.out.println(mLine + " " + mBase + " " + mExp);
	}
}