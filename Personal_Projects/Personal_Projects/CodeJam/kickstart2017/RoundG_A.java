package kickstart2017;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class RoundG_A {
	public static void main(String[] args) throws IOException {
		InputStream f = null;
		try {
			f = new FileInputStream("C:\\Users\\cstit\\Downloads\\A-small-practice.in");
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(f)));
		
		int t = in.nextInt();
		in.nextLine();
		
		for (int i=0; i<t; i++) {
			String[] nums = in.nextLine().split(" ");
			int a = Integer.parseInt(nums[0]), p = Integer.parseInt(nums[2]);
			System.out.print("Case #" + (i+1) + ": " + (a%p) + '\n');
		}
		
		in.close();
	}
}