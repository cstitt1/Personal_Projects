package round1C;

import java.util.*;
import java.io.*;

public class LetterBlock {
	
	public static void main(String[] args) {
		Scanner inputLines = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
		int numCases = inputLines.nextInt(); inputLines.nextLine();
		caseLoop: for (int caseNum = 1; caseNum <= numCases; caseNum++) {
			inputLines.nextLine(); String word_line = inputLines.nextLine();
			
			if (isThereDupLetterInWords(word_line) == true) {System.out.println("Case #" + caseNum + ": IMPOSSIBLE"); continue caseLoop;}
			
			if (!areThereStuckLetters(word_line)) {System.out.println("Case #" + caseNum + ": IMPOSSIBLE"); continue caseLoop;}
			
			String match = matchFrontsToEnds(word_line);
			if (match == null) {System.out.println("Case #" + caseNum + ": IMPOSSIBLE"); continue caseLoop;}
			
			System.out.println("Case #" + caseNum + ": " + match);
		}
		
		inputLines.close();
	}
	
	/**
	 * For each word, check if the same letter appears twice but not together -- hash, wow, ukulele
	 * 
	 * @param word_line: a String variable of space-separated words
	 * 
	 * @return True if there is a word with a duplicate, non-consecutive letter (examples above), false otherwise
	 */
	private static boolean isThereDupLetterInWords(String word_line) {
		String[] words = word_line.split(" ");
		
		wordLoop: for (String word : words) { //for each word, check if same letter twice not together (hash, wow, ukulele)
			char[] charr = word.toCharArray(); int[] lettCount = new int[26];
			char last_lett = charr[0]; lettCount[last_lett - 'A']++;
			
			if (charr.length < 3) {continue wordLoop;}
			
			for (int cInd = 1; cInd < charr.length; cInd++) {
				char chAt = charr[cInd];
				if (chAt != last_lett) {
					if (lettCount[chAt - 'A'] != 0) {
						return true;
					} else {
						lettCount[chAt - 'A']++; last_lett = chAt;
					}
				}
			}
		}
		
		return false;
	}
	
	/**
	 * For words like hash and wow, 'a' and 's' and 'o' are stuck in the middles of their respective words
	 * If stuck letters appear anywhere else, the line is not tower-able
	 * 
	 * @param word_line: a String variable of space-separated words
	 * @return True if there is a letter stuck that appears elsewhere, false otherwise
	 */
	private static boolean areThereStuckLetters(String word_line) {
		String[] words = word_line.split(" ");
		String midLetters = "";
		for (String word : words) {
			char[] charArr = word.toCharArray(); int char_len = charArr.length;
			char first = charArr[0], last = charArr[char_len - 1];
			
			firstLoop: for (int cInd = 0; cInd < char_len; cInd++) {
				if (charArr[cInd] == first) {charArr[cInd] = ' ';}
				else {break firstLoop;}
			}
			
			lastLoop: for (int cInd = char_len - 1; cInd > -1; cInd--) {
				if (charArr[cInd] == last) {charArr[cInd] = ' ';}
				else {break lastLoop;}
			}
			
			midLetters += String.valueOf(charArr).strip();
		}
		
		char[] midChars = midLetters.toCharArray();
		int[] lettCount = new int[26];
		for (int cInd = 0; cInd < midChars.length; cInd++) {
			char chAt = midChars[cInd];
			if (lettCount[chAt - 'A'] != 0) {return false;}
			else {lettCount[chAt - 'A']++;}
		}
		
		return true;
	}
	
	private static String matchFrontsToEnds(String word_line) {
		//TODO: Not working right
		
		String[] words = word_line.split(" "); int words_len = words.length;
		ArrayList<String> wordList = new ArrayList<String>(Arrays.asList(words));
		
		for (int w1 = 0; w1 < words_len; w1++) {
			for (int w2 = w1 + 1; w2 < words_len; w2++) {
				String word1 = words[w1], word2 = words[w2];
				char[] w1Arr = word1.toCharArray(), w2Arr = word2.toCharArray();
				int w1_len = w1Arr.length, w2_len = w2Arr.length;
				
				if (w1Arr[0] == w2Arr[w2_len - 1]) {
					ArrayList<String> newList = new ArrayList<String>(Arrays.asList(words));
					newList.remove(word1); newList.remove(word2); newList.add(word2 + word1);
					String matched = matchFrontsToEnds(String.join(" ", newList));
					if (matched != null) {return matched;}
				}
				
				if (w1Arr[w1_len - 1] == w2Arr[0]) {
					ArrayList<String> newList = new ArrayList<String>(Arrays.asList(words));
					newList.remove(word1); newList.remove(word2); newList.add(word1 + word2);
					String matched = matchFrontsToEnds(String.join(" ", newList));
					if (matched != null) {return matched;}
				}
			}
		}
		
		/* Non-recursive
		List<ArrayList<String>> superList = new ArrayList<ArrayList<String>>();
		superList.add(new ArrayList<String>(Arrays.asList(word_line.split(" "))));
		
		boolean canCombine = false;
		do {
			canCombine = false;
			ArrayList<String> wordsList = superList.get(0);
			String[] wordsArr = wordsList.toArray(new String[0]); int words_len = wordsArr.length;
			
			for (int w1 = 0; w1 < words_len; w1++) {
				for (int w2 = w1 + 1; w2 < words_len; w2++) {
					String word1 = wordsArr[w1], word2 = wordsArr[w2];
					char[] w1Arr = word1.toCharArray(), w2Arr = word2.toCharArray();
					int w1_len = w1Arr.length, w2_len = w2Arr.length;
					
					if (w1Arr[0] == w2Arr[w2_len - 1]) {
						canCombine = true;
						ArrayList<String> newList = new ArrayList<String>(Arrays.asList(wordsArr));
						newList.remove(word1); newList.remove(word2); newList.add(word2 + word1);
						if (!superList.contains(newList)) {superList.add(newList);}
					}
					
					if (w1Arr[w1_len - 1] == w2Arr[0]) {
						canCombine = true;
						ArrayList<String> newList = new ArrayList<String>(Arrays.asList(wordsArr));
						newList.remove(word1); newList.remove(word2); newList.add(word1 + word2);
						if (!superList.contains(newList)) {superList.add(newList);}
					}
				}
			}
			
			if (canCombine) {superList.remove(0);}
		} while (canCombine == true);
		
		for (int i = 0; i < superList.size(); i++) {
			String[] listArr = superList.get(i).toArray(new String[0]);
			String listStr = String.join("", listArr);
			if (checkDupLetterInWords(listStr)) {return listStr;}
		}*/
		
		return null;
	}
}