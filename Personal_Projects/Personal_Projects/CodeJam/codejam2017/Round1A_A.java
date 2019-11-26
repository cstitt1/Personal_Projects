package codejam2017;

import java.util.*;
import java.io.*;

public class Round1A_A {
	public static void main(String[] args) throws IOException{
		InputStream f = null;
		BufferedWriter out = null;
		try {
			f = new FileInputStream("C:\\Users\\cstit\\Desktop\\A-large-practice.in.txt");
			out = new BufferedWriter(new FileWriter(new File("C:\\Users\\cstit\\Desktop\\large_output.txt")));
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(f)));
		
		int t = in.nextInt();
		in.nextLine();
		for (int i=1; i<=t; i++) {
			int r = in.nextInt();
			int c = in.nextInt();
			in.nextLine();
			char[][] init = new char[r][c];
			for (int j=0; j<r; j++)
				init[j] = in.nextLine().toCharArray();
			String str="";
			for (char[] cA : init)
				for (char ch : cA)
					str = str + ch;
			
			int lps = 0;
			while(str.contains("?")) {
				if (lps > 4 && lps < 9) {
					for (c=0; c<init[0].length; c++)
						for (r=0; r<init.length; r++) {
							if (init[r][c] == '?')
								if (c==0)
									init[r][c] = init[r][c+1];
								else
									if (init[r][c-1] == '?' && c != init[0].length-1)
										init[r][c] = init[r][c+1];
									else
										init[r][c] = init[r][c-1];
						}
				} else if (lps < 4 || lps > 9) {
					for (r=0; r<init.length; r++)
						for (c=0; c<init[r].length; c++) {
							if (r != 0 && init[r-1][c] == '?')
								init[r-1][c] = init[r][c];
							
							if (r != init.length-1 && init[r+1][c] == '?')
								init[r+1][c] = init[r][c];
						}
				}
				str="";
				for (char[] cA : init)
					for (char ch : cA)
						str = str + ch;
				
				lps++;
			}
			
			out.write("Case #" + i + ":" +'\n');
			for (char[] cA : init) {
				for (char ch : cA)
					out.write(""+ch);
				out.write('\n');
			}
			System.out.println("case " + i + " is done");
		}
		
		in.close();
		out.close();
	}
}