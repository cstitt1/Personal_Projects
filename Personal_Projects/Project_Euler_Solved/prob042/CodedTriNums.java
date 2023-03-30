package prob042;

import java.io.*;
import java.util.*;

public class CodedTriNums {

	public static void main(String[] args) throws IOException {
		String file = "C:\\Users\\cstit\\Desktop\\p042_words.txt";
		FileReader fin = new FileReader(file);
	    Scanner charsIn = new Scanner(fin);
	    charsIn.useDelimiter("[,]+");
	    
	    int count = 0;
	    
	    while(charsIn.hasNext()) {
	    	String word = charsIn.next();
	    	int sum = 0;
	    	for (int i = 1; i < word.length()-1; i++) {
	    		sum += (word.charAt(i)-64);
	    	}
	    	
	    	int tri = 0;
	    	for (int i = 1; tri < sum; i++) {
	    		tri += i;
	    	}
	    	
	    	if (tri == sum) {
	    		count++;
	    	}
	    }
	    
	    System.out.println(count);
	    
	    charsIn.close();
	}
}