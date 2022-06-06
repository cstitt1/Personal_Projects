package prob098;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AnagramicSquares {

	public static void main(String[] args) throws FileNotFoundException {
		String file = "C:\\Users\\cstit\\OneDrive\\Desktop\\p098_words.txt";
		Scanner inp = new Scanner(new FileReader(file));
		String csv = inp.nextLine(); inp.close();
		
		String[] wordsArr = csv.substring(1, csv.length()-1).split("\",\""); // each element is word (had to remove " at front and end)
		Map<String, String> letterCountMap = new HashMap<>();
		for (String word : wordsArr) {
			int[] letterCountArr = new int[26];
			for (char letter : word.toCharArray()) {
				int index = ((int) letter) - ((int) 'A');
				letterCountArr[index] = letterCountArr[index] + 1;
			}
			
			String letterCountStr = "";
			for (int letterCount : letterCountArr) {
				letterCountStr = letterCountStr + letterCount + ",";
			}
			letterCountStr = letterCountStr.substring(0, letterCountStr.length()-1);
			
			if (letterCountMap.containsKey(letterCountStr)) {
				letterCountMap.put(letterCountStr, letterCountMap.get(letterCountStr) + "," + word);
			} else {
				letterCountMap.put(letterCountStr, word);
			}
		}
		
		String anagramStr = "";
		for (String wordSet : letterCountMap.values()) {
			if (wordSet.contains(",")) {
				anagramStr = anagramStr + (anagramStr.isEmpty()?"":";") + wordSet;
			}
		}
		
		String[] anagramArr = anagramStr.split(";");
		int digitMax = 0;
		for (String anagram : anagramArr) {
			int wordLen = anagram.indexOf(',');
			if (digitMax < wordLen) {
				digitMax = wordLen;
			}
		}
		
		Map<String, String> sqDigitMap = new HashMap<>();
		int sqUP = -1;
		for (int sq = 0; sq < Integer.MAX_VALUE; sq += sqUP) {
			sqUP += 2;
			
			String sqStr = "" + sq;
			int numDigits = sqStr.length();
			
			if (numDigits > digitMax) {
				break;
			}
			
			int[] digitCountArr = new int[10];
			for (char sqDigit : sqStr.toCharArray()) {
				int index = ((int) sqDigit) - ((int) '0');
				digitCountArr[index] = digitCountArr[index] + 1;
			}
			
			String digitCountStr = "";
			for (int digitCount : digitCountArr) {
				digitCountStr = digitCountStr + digitCount + ",";
			}
			digitCountStr = digitCountStr.substring(0, digitCountStr.length()-1);
			
			if (sqDigitMap.containsKey(digitCountStr)) {
				sqDigitMap.put(digitCountStr, sqDigitMap.get(digitCountStr) + "," + sqStr);
			} else {
				sqDigitMap.put(digitCountStr, sqStr);
			}
		}
		
		String sqAnagramStr = "";
		for (String numSet : sqDigitMap.values()) {
			if (numSet.contains(",")) {
				sqAnagramStr = sqAnagramStr + (sqAnagramStr.isEmpty()?"":";") + numSet;
			}
		}
		String[] sqAnagramArr = sqAnagramStr.split(";");
		
		for (String wordGramStr : anagramArr) {
			String[] wordGramArr = wordGramStr.split(",");
			for (int wg1 = 0; wg1 < wordGramArr.length - 1; wg1++) {
				for (int wg2 = wg1 + 1; wg2 < wordGramArr.length; wg2++) {
					String wordGram1 = wordGramArr[wg1], wordGram2 = wordGramArr[wg2];
					
				}
			}
		}
	}
}

















