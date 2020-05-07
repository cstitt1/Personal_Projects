package game2;

import java.util.ArrayList;
import java.util.HashMap;

//BEEP BOOP. I AM CHESS BOT. NICE TO MEET YOU!

public class ChessBot {
	private boolean color;
	private HashMap<String, Integer> pvals;
	
	/**
	 * Activates Chess Bot
	 * @param col the color it will control (true for white)
	 */
	public ChessBot(boolean col) {
		color = col;
		pvals = new HashMap<String, Integer>();
		pvals.put("pawn",1);
		pvals.put("knight",3);
		pvals.put("bishop",3);
		pvals.put("rook",5);
		pvals.put("queen",9);
		pvals.put("king",50);
	}
	
	/**
	 * Returns Chess Bot's piece color
	 * @return Chess Bot's piece color
	 */
	public boolean getColor() {
		return color;
	}
	
	/**
	 * Returns Chess Bot's move based on the game state
	 * @param board the current board layout
	 * @return Chess Bot's next move
	 */
	public String chooseMove(ArrayList<ChessPiece> board) {
		ArrayList<String> poss = new ArrayList<String>();
		ArrayList<Integer> vals = new ArrayList<Integer>();
		
		for (ChessPiece p : board) {
			if (color == p.getIsWhite()) {
				String[] locs = p.getMoves().split(",");
				for (String loc : locs) {
					if (loc.length() < 2) {continue;}
					poss.add(p.getLocation() + " " + loc);
					
					int val = 0;
					for (ChessPiece pv : board) {
						if (pv.getLocation().equals(loc)) {val = pvals.get(pv.getType());}
					}
					vals.add(val);
				}
			}
		}
		
		ArrayList<String> maxes = new ArrayList<String>();
		int max = -1;
		for (int i = 0; i < vals.size(); i++) {
			if (vals.get(i) > max) {max = vals.get(i); maxes.clear();}
			if (vals.get(i) == max) {maxes.add(poss.get(i));}
		}
		
		int choice = (int) (Math.random()*maxes.size()); //random number b/t 0 and size-1
		return maxes.get(choice);
	}
}