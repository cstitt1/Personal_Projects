package codejam2017;

import java.util.*;
import java.io.*;

public class Round1B_A {
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
			String str = in.nextLine();
			int target = Integer.parseInt(str.substring(0,str.indexOf(" ")));
			ArrayList<Integer> locs = new ArrayList<Integer>();
			ArrayList<Integer> spds = new ArrayList<Integer>();
			for (int j=0; j<Integer.parseInt(str.substring(str.indexOf(" ")+1)); j++) {
				String horse = in.nextLine();
				locs.add(Integer.parseInt(horse.substring(0,horse.indexOf(" "))));
				spds.add(Integer.parseInt(horse.substring(horse.indexOf(" ")+1)));
			}
			ArrayList<Integer> updt = new ArrayList<Integer>(locs.size());
			ArrayList<Integer> inds = new ArrayList<Integer>(locs.size());
			int loc = 0;
			int spd = 0;
			int hrs = 0;
			int tst = target;
			while (spd == 0) {
				loc = 0;
				hrs = 0;
				for (int j=0; j<locs.size(); j++) {
					updt.set(j, 0);
					inds.set(j, j);
				}
				
				while (!inds.isEmpty()) {
					for (int ind : inds) {
						updt.set(ind, updt.get(ind)+locs.get(ind)+spds.get(ind));
						hrs++;
					}
					
					for (int j=0; j<inds.size(); j++)
						for (int k=0; k<inds.size(); k++)
							if (updt.get(inds.get(k)) >= updt.get(inds.get(j)) && locs.get(inds.get(k)) < locs.get(inds.get(j))) {
								inds.remove(k);
								k--;
							}
					
					for (int j=0; j<inds.size(); j++) {
						if (updt.get(inds.get(j)) > target) {
							inds.remove(j);
							j--;
						}
					}
				}
			}
			
			out.write("Case #" + i + ":" + spd +'\n');
			System.out.println("case " + i + " is done");
		}
		
		in.close();
		out.close();
	}
}