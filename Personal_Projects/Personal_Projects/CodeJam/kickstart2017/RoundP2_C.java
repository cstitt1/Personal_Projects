package kickstart2017;

import java.util.*;
import java.io.*;

public class RoundP2_C {
	public static void main(String[] args) {
		InputStream f = null;
		try {
			f = new FileInputStream("C:\\Users\\cstit\\Desktop\\A-large.in.txt");
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(f)));
		
		int t = in.nextInt();
		in.nextLine();
		for (int i=1; i<=t; i++) {
			int b = 0;
			String s = (in.nextLine());
			double n = Double.parseDouble(s);
			for (double k = n-1; k > 2; k--)
				for (int j = 0; j<s.length(); j++)
			System.out.println("Case #"+i+": "+b);
		}
		
		in.close();
	}
}