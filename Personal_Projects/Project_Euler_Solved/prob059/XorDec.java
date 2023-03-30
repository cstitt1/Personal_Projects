package prob059;

import java.io.*;
import java.util.*;

public class XorDec {
	public static void main(String[] args) throws IOException {
		String file = "C:\\Users\\cstit\\OneDrive\\Desktop\\p059_cipher.txt";
		Scanner inp = new Scanner(new FileReader(file));
		//??? cipher sequence abc
		//??? 88 XOR b = 32 because space --> b = 120
		
		//File is one line of comma-separated integers
		String csv = inp.nextLine();
		inp.close();
		
		//break into integers
		String[] numsArr = csv.split(",");
		int[] numArr = new int[numsArr.length];
		for (int i = 0; i < numsArr.length; i++) {
			numArr[i] = Integer.valueOf(numsArr[i]);
		}
		
		// if encrypted is 0, then key is same as character (111 ^ 111 = 0)
		// key range is a to z: 97 to 122
		int keyRangeStart = 97, keyRangeEnd = 122; //[97, 122]
		int[] keyArr = {keyRangeStart, keyRangeStart, keyRangeStart};
		int decStart = 32, decEnd = 126;
		String[] keyOpts = {"","",""};
		
		//System.out.println("---------------------------\n");
		for (int key = 0; key < keyArr.length; key++) {
			while (keyArr[key] <= keyRangeEnd) {
				for (int i = key; i < numArr.length; i+=3) {
					int xor_res = numArr[i] ^ keyArr[key];
					
					if (!(decStart <= xor_res && xor_res <= decEnd)) {
						//System.out.println("Key " + key + " is not " + keyArr[0] + " because xor_res for " + i + " is " + xor_res + ", character \"" + ((char) xor_res) + "\"");
						break;
					}
					
					if (i+3 >= numArr.length) {
						//System.out.println("Key " + key + ": " + keyArr[key]);
						if (!keyOpts[key].isEmpty()) {keyOpts[key] += ",";}
						keyOpts[key] += keyArr[key];
					}
				}
				
				keyArr[key]++;
			}
			
			//System.out.println("\n---------------------------\n");
		}
		
		String[] commWords = {"the","be","to","of","Euler"};
		String[][] keysS = {keyOpts[0].split(","),keyOpts[1].split(","),keyOpts[2].split(",")};
		int[] lp = {0,0,0};
		
		for (lp[0] = 0; lp[0] < keysS[0].length; lp[0]++) {
			for (lp[1] = 0; lp[1] < keysS[1].length; lp[1]++) {
				for (lp[2] = 0; lp[2] < keysS[2].length; lp[2]++) {
					String[] encArr = csv.split(",");
					char[] decArr = new char[encArr.length];
					int count = 0;
					for (int ed = 0; ed < encArr.length; ed++) {
						decArr[ed] = (char)(Integer.valueOf(encArr[ed]) ^ Integer.valueOf(keysS[ed%3][lp[ed%3]]));
						count += (int) decArr[ed];
					}
					
					String dec = String.valueOf(decArr);
					
					boolean comm = true;
					for (String word : commWords) {
						if (!dec.contains(word)) {
							comm = false; break;
						}
					}
					
					if (comm) {System.out.println(dec); System.out.println("\n\n\n"); System.out.println(count);}
				}
			}
		}
	}
}




