package roundQualif;

import java.util.Scanner;

public class PunchedCards {
	
	public static void main(String[] args) {
		Scanner inputLines = new Scanner(System.in);
		
		int numLines = inputLines.nextInt(); inputLines.nextLine();
		for(int line = 0; line < numLines; line++) {
			int varR = inputLines.nextInt(), varC = inputLines.nextInt(); inputLines.nextLine();
			System.out.println("Case #" + (line+1) + ":");
			
			System.out.print("..+");
			for (int col = 1; col < varC; col++) {
				System.out.print("-+");
			}
			
			System.out.print("\n..|");
			for (int col = 1; col < varC; col++) {
				System.out.print(".|");
			}
			
			System.out.print("\n+");
			for (int col = 0; col < varC; col++) {
				System.out.print("-+");
			}
			
			for (int row = 1; row < varR; row++) {
				System.out.print("\n|");
				for (int col = 0; col < varC; col++) {System.out.print(".|");}
				
				System.out.print("\n+");
				for (int col = 0; col < varC; col++) {System.out.print("-+");}
			}
			
			if (line+1 < numLines) {System.out.print("\n");}
		}
		
		inputLines.close();
	}
}