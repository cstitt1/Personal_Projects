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
		
		//						 0  1  2  3  4  5  6  7  8  9
		String[] sqAnagramStr = {"","","","","","","","","",""};
		for (String numSet : sqDigitMap.values()) {
			if (numSet.contains(",")) {
				int index = numSet.indexOf(",");
				sqAnagramStr[index] = sqAnagramStr[index] + (sqAnagramStr[index].isEmpty()?"":",") + numSet;
			}
		}
		
		for (String str : sqAnagramStr) {if (str.indexOf(',') <= 4) {System.out.println(str.indexOf(',') + ": " + str);}} /*TODO: EXTRA*/
		
		for (String wordGramStr : anagramArr) {
			System.out.println(wordGramStr);/*TODO: EXTRA*/
			String[] wordGramArr = wordGramStr.split(",");
			for (int wg1 = 0; wg1 < wordGramArr.length - 1; wg1++) {
				for (int wg2 = wg1 + 1; wg2 < wordGramArr.length; wg2++) {
					String wordGram1 = wordGramArr[wg1], wordGram2 = wordGramArr[wg2];
					System.out.println("\t -- "+wordGram1+","+wordGram2);/*TODO: EXTRA*/
					
					//wg1 'indexes' array, where vals are where moved to in wg2
					int len = wordGram1.length(); char[] wG2Arr = wordGram2.toCharArray();
					int[] transposeArr = new int[len];
					
					for (int cInd = 0; cInd < len; cInd++) {
						char charAt = wordGram1.charAt(cInd);
						for (int tInd = 0; tInd < len; tInd++) {
							if (charAt == wG2Arr[tInd]) {
								transposeArr[cInd] = tInd;
								wG2Arr[tInd] = 0;
								break;
							}
						}
					}
					
					System.out.print("\t\t -- ["); for (int i : transposeArr) {System.out.print(i + ",");} System.out.println("]");/*TODO: EXTRA*/
					
					//TODO: then use moves to see if squares' anagrams are in collection
					String sqNumsStr = sqAnagramStr[len]; wG2Arr = wordGram2.toCharArray();
					if (sqNumsStr.isBlank()) {break;}
					
					String[] sqNums = sqNumsStr.split(",");
					for (String sqNum : sqNums) {
						char[] origSq = sqNum.toCharArray(), tSq = new char[len];
						for (int sqInd = 0; sqInd < len; sqInd++) {tSq[transposeArr[sqInd]] = origSq[sqInd];}
						
						String transposeSq = String.copyValueOf(tSq);
						if (sqNumsStr.contains(transposeSq)) {
							int[] checkArr = new int[10]; boolean chk = true;
							for (int chkInd = 0; chkInd < len; chkInd++) {
								int calcInd = tSq[chkInd] - '0';
								char compLetter = wG2Arr[chkInd];
								
								if (checkArr[calcInd] == 0 || checkArr[calcInd] == ((int) compLetter)) {checkArr[calcInd] = (int) compLetter;}
								else {chk = false; break;}
							}
							
							if (chk) {System.out.println("\t\t\t -- " + sqNum + " to " + transposeSq);/*TODO: EXTRA*/}
						}
					}
				}
			}
		}
	}
}

















