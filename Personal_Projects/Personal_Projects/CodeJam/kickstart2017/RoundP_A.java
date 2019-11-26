package kickstart2017;

import java.util.*;
import java.io.*;

public class RoundP_A {
	public static void main(String[] args) {
		InputStream f = null;
		BufferedWriter out = null;
		try {
			f = new FileInputStream("C:\\Users\\cstit\\Desktop\\A-large-practice.in.txt");
			out = new BufferedWriter(new FileWriter(new File("C:\\Users\\cstit\\Desktop\\large_output.txt")));
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException i) {
			System.out.println("File cannot be written to");
		}
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(f)));
		
		int t = in.nextInt();
		in.nextLine();
		for (int i=1; i<=t; i++) {
			int n = in.nextInt();
			in.nextLine();
			int max = -1;
			String lead = "";
			for (int j=1; j<=n; j++) {
				ArrayList<Character> let = new ArrayList<Character>();
				String test = in.nextLine();
				for (char c : test.toCharArray())
					if (!let.contains(c) && c != ' ')
						let.add(c);
				if (let.size() > max) {
					max = let.size();
					lead = test;
				} else if (let.size() == max) {
					if (test.compareToIgnoreCase(lead) < 0)
						lead = test;
				}
			}
			try {
				out.write("Case #" + i + ": "+lead + '\n');
				System.out.println("case " + i + " is done");
			} catch (IOException e) {
				System.out.println("Writing failed");
			}
		}
		
		in.close();
		try {
			out.close();
		} catch (IOException e) {
			System.out.println("Closing failed");
		}
	}
}