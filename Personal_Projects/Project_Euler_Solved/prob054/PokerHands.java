package prob054;

import java.io.*;
import java.util.*;

public class PokerHands {

	public static void main(String[] args) throws IOException {
		String file = "C:\\Users\\cstit\\Desktop\\p054_poker.txt";
		Scanner inp = new Scanner(new FileReader(file));
		
		HashMap<Character, Long> cardV = new HashMap<>();
		char[] cardVs = {'2','3','4','5','6','7','8','9','T','J','Q','K','A'};
		for (int i = 0; i < 13; i++) {
			cardV.put(cardVs[i], Math.round(Math.pow(10, i)));
		}
		
		int count = 0;
		for (int i = 0; i < 1000; i++) {
			int[] val = {-1,-1};
			int[][] rank = {{-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1}};
			String[] cards = inp.nextLine().split(" ");
			
			String[] cvals = {"",""}, suits = {"",""};
			for (int c = 0; c < 10; c++) {
				cvals[c<5?0:1] += cards[c].substring(0, 1);
				suits[c<5?0:1] += cards[c].substring(1, 2);
			}
			
			for (int h = 0; h < 2; h++) {
				long sum = 0;
				for (int c = 0; c < 5; c++) {
					sum += cardV.get(cvals[h].toCharArray()[c]);
				}
				
				String sm = ""+sum; //indexing -- 0 = A, 1 = K, 2 = Q, 3 = J, etc
				while (sm.length() < 13) {
					sm = "0" + sm;
				}
				
				if (sm.startsWith("11111") && sameSuit(suits[h])) {
					val[h] = 10;
				} else if (sm.contains("11111") && sameSuit(suits[h])) {
					val[h] = 9;
					rank[h][0] = sm.indexOf("1");
				} else if (sm.contains("4")) {
					val[h] = 8;
					rank[h][0] = sm.indexOf("4");
					rank[h][1] = sm.indexOf("1");
				} else if (sm.contains("3") && sm.contains("2")) {
					val[h] = 7;
					rank[h][0] = sm.indexOf("3");
					rank[h][1] = sm.indexOf("2");
				} else if (sameSuit(suits[h])) {
					val[h] = 6;
					int ind = 0;
					for (int s = 0; s < sm.toCharArray().length; s++) {
						if (sm.toCharArray()[s] != '0') {
							rank[h][ind] = s;
							ind++;
						}
					}
				} else if (sm.contains("11111")) {
					val[h] = 5;
					rank[h][0] = sm.indexOf("1");
				} else if (sm.contains("3")) {
					val[h] = 4;
					rank[h][0] = sm.indexOf("3");
					rank[h][1] = sm.indexOf("1");
					rank[h][2] = sm.lastIndexOf("1");
				} else if (sm.contains("2") && sm.indexOf("2") != sm.lastIndexOf("2")) {
					val[h] = 3;
					rank[h][0] = sm.indexOf("2");
					rank[h][1] = sm.lastIndexOf("2");
					rank[h][2] = sm.indexOf("1");
				} else if (sm.contains("2")) {
					val[h] = 2;
					rank[h][0] = sm.indexOf("2");
					int ind = 1;
					for (int s = 0; s < sm.toCharArray().length; s++) {
						if (sm.toCharArray()[s] != '0' && sm.toCharArray()[s] != '2') {
							rank[h][ind] = s;
							ind++;
						}
					}
				} else {
					val[h] = 1;
					int ind = 0;
					for (int s = 0; s < sm.toCharArray().length; s++) {
						if (sm.toCharArray()[s] == '1') {
							rank[h][ind] = s;
							ind++;
						}
					}
				}
			}
			
			if (val[0] > val[1]) {
				count++;
			} else if (val[0] == val[1]) {
				for (int ind = 0; ind < 5; ind++) {
					if (rank[0][ind] < rank[1][ind]) {
						count++;
						break;
					} else if (rank[0][ind] > rank[1][ind]) {
						break;
					}
				}
			}
		}
		
		inp.close();
		System.out.println(count);
	}
	
	private static boolean sameSuit(String suits) {
		char first = suits.charAt(0);
		for (int i = 1; i < suits.length(); i++) {
			if (suits.charAt(i) != first) {
				return false;
			}
		}
		
		return true;
	}
}