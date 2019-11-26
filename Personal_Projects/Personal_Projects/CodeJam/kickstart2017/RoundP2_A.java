package kickstart2017;

import java.util.*;
import java.io.*;

public class RoundP2_A {
	public static void main(String[] args) {
		InputStream f = null;
		try {
			f = new FileInputStream("C:\\Users\\cstit\\Desktop\\A-small-attempt6.in.txt");
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(f)));
		
		int t = in.nextInt();
		in.nextLine();
		for (int i=1; i<=t; i++) {
			int cnt = 0;
			String s = in.nextLine();
			String str = in.nextLine();
			int j = Integer.parseInt(str.substring(0, str.indexOf(" ")));
			int k = Integer.parseInt(str.substring(str.indexOf(" ")+1));
			while (s.length() < k)
				s+=s;
			for (int z=j; z<k; z++)
				if (s.charAt(z)=='B')
					cnt++;
			System.out.println("Case #"+i+": "+cnt);
		}
		
		in.close();
	}
}