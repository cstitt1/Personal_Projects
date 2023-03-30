package prob024;

import java.util.ArrayList;

public class LexPerm {
	public static void main(String[] args) {
		double count = 0;
		
		for (double i=0123456789d; i<=9876543210d; i++) {
			String str = ""+i;
			str = str.substring(0, str.indexOf(".")) + str.substring(str.indexOf(".")+1,str.indexOf("E"));
			if (str.length()==9) {
				str = "0"+str;
			}
			
			if (containsOnce(str,"0") &&
				containsOnce(str,"1") &&
				containsOnce(str,"2") &&
				containsOnce(str,"3") &&
				containsOnce(str,"4") &&
				containsOnce(str,"5") &&
				containsOnce(str,"6") &&
				containsOnce(str,"7") &&
				containsOnce(str,"8") &&
				containsOnce(str,"9")) {
				count++;
				System.out.println("Added: "+i+" -- Size: "+count);
				
				if (count==1000000)
					break;
			}
		}
	}
	
	private static boolean containsOnce(String str, String regex) {
		return str.indexOf(regex) == str.lastIndexOf(regex) && str.contains(regex);
	}
}
