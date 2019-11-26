package kickstart2017;

import java.util.*;
import java.io.*;

public class RoundP_B {
	public static void main(String[] args) throws IOException {
		InputStream f = null;
		BufferedWriter out = null;
		try {
			f = new FileInputStream("C:\\Users\\cstit\\Desktop\\B-small-practice.in.txt");
			out = new BufferedWriter(new FileWriter(new File("C:\\Users\\cstit\\Desktop\\small_output.txt")));
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(f)));
		
		int t = in.nextInt();
		in.nextLine();
		for (int i=1; i<=t; i++) {
			String str = in.nextLine();
			double a = Integer.parseInt(str.substring(0, str.indexOf(" ")));
			double b = Integer.parseInt(str.substring(str.indexOf(" ")+1));
			double x = a/(a+b);
			double y = 1;
			if (a != 1)
				y = (a-1)/(a+b-1);
			double z = 1;
			if (b != 1)
				z = split(a-2,b,2*b-2,b-1);
			double prob = x * y * z;
			
			out.write("Case #" + i + ": " +round(prob) + '\n');
			System.out.println("case " + i + " is done");
		}
		
		in.close();
		out.close();
	}
	
	public static double split(double a, double b, double limA, double limB) {
		if (limA==0)
			return 1;
		
		if (limB == 0)
			return a/(a+b);
		
		if (a==0 || b==0)
			return 1;
		
		double x = (a/(a+b))*split(a-1,b,limA-1,limB);
		double y = (b/(a+b))*split(a,b-1,limA-1,limB-1);
		
		return x + y;
	}
	
	public static double round(double prob) {
		prob = prob * Math.pow(10, 10);
		prob = Math.round(prob);
		return prob / Math.pow(10, 10);
	}
}