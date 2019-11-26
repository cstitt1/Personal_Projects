package codejam2017;

import java.util.*;
import java.io.*;

public class RoundQ_B {
	public static void main(String[] args) throws IOException{
		InputStream f = null;
		BufferedWriter out = null;
		try {
			f = new FileInputStream("C:\\Users\\cstit\\Desktop\\B-large-practice.in.txt");
			out = new BufferedWriter(new FileWriter(new File("C:\\Users\\cstit\\Desktop\\large_output.txt")));
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(f)));
		
		int t = in.nextInt();
		in.nextLine();
		for (int i=1; i<=t; i++) {
			double n = Double.parseDouble(in.nextLine());
			double res = -1;
			
			if (isTidy(n)) {
				if (Double.toString(n).indexOf("E") == -1)
					out.write("Case #" + i + ": " + Double.toString(n).substring(0, Double.toString(n).indexOf(".")) +'\n');
				else
					out.write("Case #" + i + ": " + n +'\n');
				System.out.println("case " + i + " is done");
				continue;
			}
			
			for (double j=n; j>1; j--)
				if (isTidy(j)) {
					res = j;
					break;
				}
			
			if (Double.toString(res).indexOf("E") == -1)
				out.write("Case #" + i + ": " + Double.toString(res).substring(0, Double.toString(res).indexOf(".")) +'\n');
			else
				out.write("Case #" + i + ": " + res +'\n');
			System.out.println("case " + i + " is done");
		}
		
		in.close();
		out.close();
	}
	
	public static boolean isTidy(double n) {
		char[] m = Double.toString(n).substring(0,Double.toString(n).indexOf(".")).toCharArray();
		if (Double.toString(n).indexOf("E") != -1)
			m = (Double.toString(n).substring(0,Double.toString(n).indexOf(".")) + Double.toString(n).substring(Double.toString(n).indexOf(".")+1,Double.toString(n).indexOf("E"))).toCharArray();
		
		for (int i=0; i<m.length-1; i++)
			if (m[i+1] < m[i] || (m[i] != 0 && m[i+1] == 0))
				return false;
		
		return true;
	}
}