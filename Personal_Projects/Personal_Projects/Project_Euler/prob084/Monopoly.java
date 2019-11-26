package prob084;

import java.util.ArrayList;

public class Monopoly {
	private static String[] map = {"GO","A1","CC1","A2","T1","R1","B1","CH1",
								   "B2","B3","JAIL","C1","U1","C2","C3","R2",
								   "D1","CC2","D2","D3","FP","E1","CH2","E2",
								   "E3","R3","F1","F2","U2","F3","G2J","G1",
								   "G2","CC3","G3","R4","CH3","H1","T2","H2"};
	
	public static void main(String[] args) {
		int d1, d2, pos=0;
		int[] sq = new int[40], past = new int[4];
		ArrayList<Integer> cc = new ArrayList<>(), ch = new ArrayList<>();
		
		while (cc.size()<16) {
			int r = ((int) (Math.random()*16))+1;
			if (!cc.contains(r)) {
				cc.add(r);
				ch.add(r);
			}
		}
		
		for (int i=0; i<400000; i++) {
			d1 = ((int) (Math.random()*4))+1;
			d2 = ((int) (Math.random()*4))+1;
			
			if (d1 == past[0] && d1 == past[2] && d2 == past[1] && d2 == past[3]) { //three consecutive doubles
				pos = 10;
			} else {
				pos += d1+d2;
				pos %= 40;
				
				if (pos == 30) {//G2J
					pos = 10;
				} else if (map[pos].contains("CC")){
					int card = cc.get(0);
					cc.add(cc.remove(0));
					if (card == 1) {
						pos = 0;
					} else if (card == 2) {
						pos = 10;
					}
				} else if (map[pos].contains("CH")) {
					int card = ch.get(0);
					ch.add(ch.remove(0));
					pos = chance(pos, card);
				}
			}
			
			sq[pos] += 1;
			past[2] = past[0];
			past[3] = past[1];
			past[0] = d1;
			past[1] = d2;
			
			System.out.println(max3P(sq, i+1));
		}
	}
	
	private static int chance(int current, int card) {
		switch (card) {
		case 1:
			return 0;
		case 2:
			return 10;
		case 3:
			return next("C1",current);
		case 4:
			return next("E3",current);
		case 5:
			return next("H2",current);
		case 6:
			return next("R1",current);
		case 7:
			return next("R",current);
		case 8:
			return next("R",current);
		case 9:
			return next("U",current);
		case 10:
			return current-3;
		}
		
		return current;
	}
	
	private static int next(String cont, int from) {
		while (!map[from].contains(cont)) {
			from++;
			from%=40;
		}
		
		return from;
	}
	
	private static String max3P(int[] counts, double total) {
		String str = "";
		int[] ind = {0,1,2};
		
		for (int i=1; i<counts.length; i++) {
			if (counts[i] >= counts[ind[0]]) {
				ind[2] = ind[1];
				ind[1] = ind[0];
				ind[0] = i;
			} else if (counts[i] >= counts[ind[1]]) {
				ind[2] = ind[1];
				ind[1] = i;
			} else if (counts[i] >= counts[ind[2]]) {
				ind[2] = i;
			}
		}
		
		str += map[ind[0]] + " = " + (counts[ind[0]]/total*100) + "%\n";
		str += map[ind[1]] + " = " + (counts[ind[1]]/total*100) + "%\n";
		str += map[ind[2]] + " = " + (counts[ind[2]]/total*100) + "%\n\n";
		
		return str;
	}
}