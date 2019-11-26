package codejam2017;

import java.util.*;
import java.io.*;

public class Round1C_C {
	public static void main(String[] args) throws IOException{
		InputStream f = null;
		BufferedWriter out = null;
		try {
			f = new FileInputStream("C:\\Users\\cstit\\Desktop\\C-small-practice-1.in.txt");
			out = new BufferedWriter(new FileWriter(new File("C:\\Users\\cstit\\Desktop\\small_output.txt")));
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(f)));
		
		int t = in.nextInt();
		in.nextLine();
		for (int i=1; i<=t; i++) {
			String str = in.nextLine();
			int n = Integer.parseInt(str.substring(0,str.indexOf(" ")));
			int k = Integer.parseInt(str.substring(str.indexOf(" ")+1));
			double u = Double.parseDouble(in.nextLine());
			ArrayList<Double> ps = new ArrayList<Double>();
			str = in.nextLine();
			for (int j=0; j<n-1; j++) {
				ps.add(Double.parseDouble(str.substring(0,str.indexOf(" "))));
				str = str.substring(str.indexOf(" ")+1);
			}
			ps.add(Double.parseDouble(str));
			ps.sort(null);
			
			out.write("Case #" + i + ":" +'\n');
			System.out.println("case " + i + " is done");
		}
		
		in.close();
		out.close();
	}
}