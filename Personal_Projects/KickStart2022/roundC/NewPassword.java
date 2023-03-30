package roundC;

import java.util.*;
import java.io.*;

public class NewPassword {
	
	public static void main(String[] args) {
		Scanner inputLines = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
		int numCases = inputLines.nextInt(); inputLines.nextLine();
		for (int caseNum = 1; caseNum <= numCases; caseNum++) {
			inputLines.nextLine(); String pswd = inputLines.nextLine();
			
			char[] lwr = ("abcdefghijklmnopqrstuvwxyz").toCharArray();
			char[] uppr = ("ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
			char[] nums = ("0123456789").toCharArray();
			char[] spcl = ("#@*&").toCharArray();
			
			for (char c : lwr) {
				if (pswd.contains("" + c)) {break;}
				if (c == 'z') {pswd += "a";}
			}
			
			for (char c : uppr) {
				if (pswd.contains("" + c)) {break;}
				if (c == 'Z') {pswd += "A";}
			}
			
			for (char c : nums) {
				if (pswd.contains("" + c)) {break;}
				if (c == '9') {pswd += "0";}
			}
			
			for (char c : spcl) {
				if (pswd.contains("" + c)) {break;}
				if (c == '&') {pswd += "#";}
			}
			
			while (pswd.length() < 7) {
				pswd += "r";
			}
			
			System.out.println("Case #" + caseNum + ": " + pswd);
		}
		
		inputLines.close();
	}
}