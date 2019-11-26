package wordgame;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
public class BumbleBee {
	public static void main(String[] args) {
		File WL = new File("C:\\Users\\cstit\\Desktop\\My Stuff\\My Programs\\WordList.txt");
        if (!(WL.exists())) {
            System.out.println("This file does not exist.");
            return;
        }
        
        ArrayList<ArrayList<String>> alpha = new ArrayList<ArrayList<String>>(26);
        for (int i=0; i<26; i++)
        	alpha.add(new ArrayList<String>());
        Scanner wds;
        try {
        	wds = new Scanner(WL);
        } catch (Exception e) {
        	System.out.println("This file does not exist.");
            return;
        }
        
        String word = wds.nextLine();
        while (wds.hasNextLine()) {
        	int ind = (int) (Character.toLowerCase(word.charAt(0))) - 97;
        	alpha.get(ind).add(word);
        	word = wds.nextLine();
        }
        wds.close();
        
        for (int i=0; i<26; i++) {
        	int getter = (int) (Math.random()*(alpha.get(i).size()-1));
        	System.out.println(alpha.get(i).get(getter));
        }
	}
}