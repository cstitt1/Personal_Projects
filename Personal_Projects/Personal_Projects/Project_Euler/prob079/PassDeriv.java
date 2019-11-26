package prob079;

import java.io.*;
import java.util.*;

public class PassDeriv {

	public static void main(String[] args) throws IOException{
		//i,j is true means i is in front of j
		boolean[][] behind = new boolean[10][10];
		int[] code = new int[8];
		
		for (int i = 0; i < behind.length; i++) {
			for (int j = 0; j < behind[i].length; j++) {
				behind[i][j] = false;
			}
		}
		
		String file = "C:\\Users\\cstit\\Desktop\\p079_keylog.txt";
		Scanner inp = new Scanner(new FileReader(file));
		
		for (int i = 0; i < 50; i++) {
			int val = inp.nextInt();
			inp.nextLine();
			int[] dig = {val/100,(val/10)%10,val%10};
			behind[dig[0]][dig[1]] = true;
			behind[dig[0]][dig[2]] = true;
			behind[dig[1]][dig[2]] = true;
		}
		
		System.out.println("  0 1 2 3 4 5 6 7 8 9 ");
		for (int i = 0; i < behind.length; i++) {
			System.out.print(i+" ");
			for (int j = 0; j < behind[i].length; j++) {
				System.out.print((behind[i][j]?"T":"F") + " ");
			}
			System.out.println();
		} //4 & 5 not in passcode
		System.out.println();
		
		String[] bhnd = new String[8]; // what's behind 0,1,2,3,6,7,8,9
		for (int i = 0; i < behind.length; i++) {
			bhnd[((i<5)?i:i-2)] = "";
			if (i == 4 || i == 5) {
				continue;
			}
			for (int j = 0; j < behind[i].length; j++) {
				if (j == 4 || j == 5) {
					continue;
				}
				if (behind[i][j]) {
					bhnd[((i<5)?i:i-2)] += j;
				}
			}
			System.out.println(i+": "+ bhnd[((i<5)?i:i-2)]);
		}
		
		inp.close();
	}
}