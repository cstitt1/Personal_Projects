package kickstart2017;

import java.util.*;
import java.io.*;

public class RoundA_A {
	public static void main(String[] args) throws IOException{
		InputStream f = null;
		BufferedWriter out = null;
		try {
			f = new FileInputStream("C:\\Users\\cstit\\Desktop\\A-small-practice.in.txt");
			out = new BufferedWriter(new FileWriter(new File("C:\\Users\\cstit\\Desktop\\small_output.txt")));
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(f)));
		
		int t = in.nextInt();
		in.nextLine();
		for (int i=1; i<=t; i++) {
			String str = in.nextLine();
			double r = Integer.parseInt(str.substring(0, str.indexOf(" ")));
			double c = Integer.parseInt(str.substring(str.indexOf(" ")+1));
			double cnt = 0;
			
			for (double s=1; s<=Math.min(r, c)-1; s++)
				cnt = cnt + square(r,c,s);
			
			out.write("Case #" + i + ": " + cnt + '\n');
			System.out.println("case " + i + " is done");
		}
		
		in.close();
		out.close();
	}
	
	public static double square(double row, double col, double size) {
		if (size==1)
			return (row-1)*(col-1);
		
		if (row%2==1)
			row--;
		if (col%2==1)
			col--;
		
		return row*col/Math.pow(size, 2);
	}
}