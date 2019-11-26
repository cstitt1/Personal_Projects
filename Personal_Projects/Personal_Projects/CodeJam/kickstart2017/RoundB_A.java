package kickstart2017;

import java.util.*;
import java.io.*;

public class RoundB_A {
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
			double sum = 0;
			int n = in.nextInt();
			ArrayList<Integer> nums = new ArrayList<Integer>(n);
			in.nextLine();
			String lst = in.nextLine();
			while (lst.indexOf(" ") != -1) {
				nums.add(Integer.decode(lst.substring(0, lst.indexOf(" "))));
				lst = lst.substring(lst.indexOf(" ")+1);
			}
			nums.add(Integer.decode(lst));
			
			for (int s = 2; s<= nums.size(); s++) {
				int[] inds = new int[s];
				for (int j=0; j<inds.length; j++)
					inds[j] = j;
				
//				TODO:for (int k = inds.length-1; k>=0; k--)
			}
			
			out.write("Case #" + i + ": " + sum % (Math.pow(10, 9)+7) + '\n');
			System.out.println("case " + i + " is done");
		}
		
		in.close();
		out.close();
	}
}