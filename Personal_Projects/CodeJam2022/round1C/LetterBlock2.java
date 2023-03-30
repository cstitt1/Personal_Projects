package round1C;

import java.util.*;
import java.io.*;

public class LetterBlock2 {
	
	public static void main(String[] args) {
		Scanner inputLines = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
		int numCases = inputLines.nextInt(); inputLines.nextLine();
		caseLoop: for (int caseNum = 1; caseNum <= numCases; caseNum++) {
			inputLines.nextLine(); String wordLine = inputLines.nextLine();
			
			ArrayList<String> matches = matchFrontsToEnds(wordLine);
			for (String match : matches) {
				if (isValidTower(match)) {
					System.out.println("Case #" + caseNum + ": " + match);
					continue caseLoop;
				}
			}
			System.out.println("Case #" + caseNum + ": IMPOSSIBLE");
		}
		
		inputLines.close();
	}
	
	/**
	 * From a string of space-separated words, gives all combinations where the fronts
	 * of words can be matched to the ends to the maximum degree
	 * @param wordLine: a String variable of space
	 * @return ArrayList<String>: a list of strings where each is the most combined that possibility can be
	 */
	private static ArrayList<String> matchFrontsToEnds(String wordLine) {
		ArrayList<String> matches = new ArrayList<>();
		
		ArrayList<String> wordLines = new ArrayList<>(); wordLines.add(wordLine);
		while (!wordLines.isEmpty()) {
			String line = wordLines.remove(0);
			String[] words = line.split(" "); int wordsLen = words.length;
			boolean canCombine = false;
			
			for (int w1Ind = 0; w1Ind < wordsLen; w1Ind++) {
				for (int w2Ind = w1Ind + 1; w2Ind < wordsLen; w2Ind++) {
					String word1 = words[w1Ind], word2 = words[w2Ind];
					char[] w1Arr = word1.toCharArray(), w2Arr = word2.toCharArray();
					int w1Len = w1Arr.length, w2Len = w2Arr.length;
					
					if (w1Arr[0] == w2Arr[w2Len - 1]) {
						canCombine = true;
						ArrayList<String> newWords = new ArrayList<String>(Arrays.asList(words));
						newWords.remove(word2); newWords.remove(word1); newWords.add(word2 + word1);
						String newWordLine = String.join(" ", newWords.toArray(new String[0]));
						if (!wordLines.contains(newWordLine)) {wordLines.add(newWordLine);}
					}
					
					if (w1Arr[w1Len - 1] == w2Arr[0]) {
						canCombine = true;
						ArrayList<String> newWords = new ArrayList<String>(Arrays.asList(words));
						newWords.remove(word1); newWords.remove(word2); newWords.add(word1 + word2);
						String newWordLine = String.join(" ", newWords.toArray(new String[0]));
						if (!wordLines.contains(newWordLine)) {wordLines.add(newWordLine);}
					}
				}
			}
			
			if (!canCombine) {matches.add(line.replaceAll(" ", ""));}
		}
		
		return matches;
	}
	
	/**
	 * For a string of A-Z characters, check if it is a valid tower by the rules
	 * Thus, no letter must appear twice or more unless it is consecutively
	 * @param charLine: The string of characters 
	 * @return True if it is a valid tower, false otherwise
	 */
	private static boolean isValidTower(String charLine) {
		char[] charr = charLine.toCharArray(); int chLen = charr.length;
		int[] lettCount = new int[26]; char lastLett = charr[0];
		
		for (int cInd = 0; cInd < chLen; cInd++) {
			char chAt = charr[cInd];
			
			if (lettCount[chAt - 'A'] != 0 && lastLett != chAt) {
				return false;
			}
			
			lettCount[chAt - 'A']++;
			lastLett = chAt;
		}
		
		return true;
	}
}