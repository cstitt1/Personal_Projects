package ddlc;

import java.io.File;
import java.util.Scanner;

public class DDUI {
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter the name of the image file or text file that is on the desktop screen that you wish to work\nwith and .jpg or .txt, separated by a comma.");
		System.out.println("For example, the file \"test.ext\" is typed in as test,ext");
		String[] name = keyboard.nextLine().split(",");
		boolean encoding = name[1].equals("txt");
		System.out.println("Enter the name you want the new file to be");
		String end = keyboard.nextLine();
		keyboard.close();
		
		File file = new File("C:\\Users\\cstit\\Desktop\\"+name[0]+"."+name[1]);
		Base64 convert64 = new Base64();
		BinarySquare binSq = new BinarySquare();
		
		if (encoding) {
			binSq.toSquare(convert64.encode(file), end);
		} else {
			convert64.decode(binSq.fromSquare(file), end);
		}
	}
}