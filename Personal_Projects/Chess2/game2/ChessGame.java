package game2;

import java.awt.Frame;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ChessGame {
	private ArrayList<ChessPiece> board, rem;
	private ArrayList<String> moves;
	private Frame frame;
	private boolean whiteTurn = true;
	private boolean gameOver = false;
	
	/**
	 * Constructs a new chess game
	 * @param frame the frame that the GUI exists on
	 */
	public ChessGame(Frame frame) {
		this.frame = frame;
		board = new ArrayList<ChessPiece>(32);
		rem = new ArrayList<ChessPiece>(32);
		moves = new ArrayList<String>(32);
		
		String[] type = {"rook","knight","bishop","queen","king","bishop","knight","rook"};
		String[] let = {"a","b","c","d","e","f","g","h"};
		for (int i=0; i<8; i++) {
			board.add(new ChessPiece(type[i], true, let[i]+1));
			board.add(new ChessPiece("pawn", true, let[i]+2));
			board.add(new ChessPiece("pawn", false, let[i]+7));
			board.add(new ChessPiece(type[i], false, let[i]+8));
		}
		
		setValidMoves();
	}
	
	/**
	 * Returns the piece at a certain location
	 * @param loc the location of the piece to be returned
	 * @return the piece at a certain location
	 */
	public ChessPiece getPiece(String loc) {
		for(ChessPiece piece : board) {if (piece.getLocation().equals(loc)) {return piece;}}
		return null;
	}
	
	/**
	 * Returns the chess board
	 * @return the chess board
	 */
	public ArrayList<ChessPiece> getChessBoard() {
		return board;
	}
	
	/**
	 * Returns an array list containing all moves made during the game
	 * @return an array list containing all moves made during the game
	 */
	public ArrayList<String> getMoves() {
		return moves;
	}
	
	/**
	 * Returns which color the turn color is
	 * @return the turn color
	 */
	public boolean getTurn() {
		return whiteTurn;
	}
	
	/**
	 * Returns true if the location loc is valid, false otherwise
	 * @param loc the location to be checked
	 * @return true if the location loc is valid, false otherwise
	 */
	private boolean isValidLoc(String loc) {
		if (loc.charAt(0) < 'a' || loc.charAt(0) > 'h') {return false;}
		else if (loc.charAt(1) > '8' || loc.charAt(1) < '1') {return false;}
		else {return true;}
	}
	
	/**
	 * Handles the error messages for the entire class
	 * @param msg the message to be sent with the error
	 */
	private void gErr(String msg) {
		JOptionPane.showMessageDialog(frame, msg,"",JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Moves a piece based on the coordinates given
	 * @param move two space-separated coordinates
	 */
	public void makeMove(String move) {
		String loc = move.substring(0,2);
		String other = move.substring(3);
		ChessPiece me = getPiece(loc), nxt = getPiece(other);
		
		if (gameOver) {gErr("The game is over!"); return;}
		if (me == null) {gErr("There is no piece at "+loc); return;}
		if (me.getIsWhite() != whiteTurn) {gErr("Not your turn!"); return;}
		if (!me.getMoves().contains(other)) {gErr("Move "+loc+" to "+other+" is not valid"); return;}
		
		if (me.getType().equals("pawn") && nxt == null) {
			if (Math.abs(loc.charAt(0)-other.charAt(0)) == 1 && Math.abs(loc.charAt(1)-other.charAt(1)) == 1) {
				nxt = getPiece(new String(new char[] {other.charAt(0),me.getIsWhite()?'5':'4'})); //En Passant
			}
		}
		
		if (me.getType().equals("king") && Math.abs(loc.charAt(0)-other.charAt(0)) == 2) {
			String rL1 = (loc.charAt(0)<other.charAt(0)?"h":"a") + loc.substring(1);
			String rL2 = (loc.charAt(0)<other.charAt(0)?"f":"d") + loc.substring(1);
			getPiece(rL1).changeLocation(rL2); //Castle
		}
		
		if (nxt != null) {board.remove(nxt); rem.add(nxt);}
		me.changeLocation(other);
		moves.add(move);
		if(me.getType().equals("pawn") && other.charAt(1)==(me.getIsWhite()?'8':'1')) {kingMe(me);}
		
		int cond = endCond();
		boolean end = Math.abs(cond) == 2;
		String state = cond<0?"White":"Black";
		state += " is in check";
		state += end?"mate!":"!";
		
		if (cond == 0) {gErr("It's a stalemate!"); gameOver = true;}
		else if (cond < 3) {gErr(state); gameOver = end;}
	}
	
	/**
	 * Sets the valid moveset for each piece on the board
	 */
	private void setValidMoves() {
		String valids = "";
		for (int i = 0; i < board.size(); i++) {
			valids = "";
			ChessPiece piece = board.get(i);
			char[] loc = piece.getLocation().toCharArray();
			String type = piece.getType();
			
			if (type.equals("rook")) {valids = svmRook(piece);}
			if (type.equals("bishop")) {valids = svmBishop(piece);}
			if (type.equals("queen")) {valids = svmRook(piece) + "," + svmBishop(piece);}
			
			int[][] mvs = {};
			if (type.equals("knight")) {mvs =  new int[][] {{1,2},{1,-2},{-1,2},{-1,-2},{2,1},{2,-1},{-2,1},{-2,-1}};}
			if (type.equals("king")) {mvs = new int[][] {{1,0},{1,-1},{0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{2,0},{-2,0}};}
			if (type.equals("pawn")) {
				int dir = piece.getIsWhite()?1:-1;//white goes up, black comes down
				mvs = new int[][] {{0,dir},{0,2*dir},{1,dir},{-1,dir}};
			}
			
			for (int j = 0; j < mvs.length; j++) {
				String mv = new String(new char[] {(char)(loc[0]+mvs[j][0]),(char)(loc[1]+mvs[j][1])});
				if (isValidMove(piece, mv)) {valids += (j>0?",":"") + mv;}
			}
			
			piece.setMoves(valids);
		}
	}
	
	/**
	 * Helper for setValidMoves for rooks (& queens)
	 * @param rook the rook to get the moveset for
	 * @return the set of moves for the piece
	 */
	private String svmRook(ChessPiece rook) {
		String valids = "";
		char[] loc = rook.getLocation().toCharArray();
		
		char[] mv = loc;
		boolean vmove = true;
		for (int i = 1; vmove; i++) {
			mv = new char[] {(char)(loc[0]+i), loc[1]};
			vmove = isValidMove(rook, new String(mv));
			if (vmove) {valids += (valids.length()==0?"":",") + (new String(mv));}
		}
		
		mv = loc;
		vmove = true;
		for (int i = 1; vmove; i++) {
			mv = new char[] {(char)(loc[0]-i), loc[1]};
			vmove = isValidMove(rook, new String(mv));
			if (vmove) {valids += (valids.length()==0?"":",") + (new String(mv));}
		}
		
		mv = loc;
		vmove = true;
		for (int i = 1; vmove; i++) {
			mv = new char[] {loc[0], (char)(loc[1]+i)};
			vmove = isValidMove(rook, new String(mv));
			if (vmove) {valids += (valids.length()==0?"":",") + (new String(mv));}
		}
		
		mv = loc;
		vmove = true;
		for (int i = 1; vmove; i++) {
			mv = new char[] {loc[0], (char)(loc[1]-i)};
			vmove = isValidMove(rook, new String(mv));
			if (vmove) {valids += (valids.length()==0?"":",") + (new String(mv));}
		}
		
		return valids;
	}
	
	/**
	 * Helper for setValidMoves for bishops (& queens)
	 * @param bishop the bishop to get the moveset for
	 * @return the set of moves for the piece
	 */
	private String svmBishop(ChessPiece bishop) {
		String valids = "";
		char[] loc = bishop.getLocation().toCharArray();
		
		char[] mv = loc;
		boolean vmove = true;
		for (int i = 1; vmove; i++) {
			mv = new char[] {(char)(loc[0]+i), (char)(loc[1]+i)};
			vmove = isValidMove(bishop, new String(mv));
			if (vmove) {valids += (valids.length()==0?"":",") + (new String(mv));}
		}
		
		mv = loc;
		vmove = true;
		for (int i = 1; vmove; i++) {
			mv = new char[] {(char)(loc[0]+i), (char)(loc[1]-i)};
			vmove = isValidMove(bishop, new String(mv));
			if (vmove) {valids += (valids.length()==0?"":",") + (new String(mv));}
		}
		
		mv = loc;
		vmove = true;
		for (int i = 1; vmove; i++) {
			mv = new char[] {(char)(loc[0]-i), (char)(loc[1]+i)};
			vmove = isValidMove(bishop, new String(mv));
			if (vmove) {valids += (valids.length()==0?"":",") + (new String(mv));}
		}
		
		mv = loc;
		vmove = true;
		for (int i = 1; vmove; i++) {
			mv = new char[] {(char)(loc[0]-i), (char)(loc[1]-i)};
			vmove = isValidMove(bishop, new String(mv));
			if (vmove) {valids += (valids.length()==0?"":",") + (new String(mv));}
		}
		
		return valids;
	}
	
	/**
	 * Returns true if the move is valid
	 * @param piece the piece to be moved
	 * @param loc the location to move it to
	 * @return true if the move is valid
	 */
	private boolean isValidMove(ChessPiece piece, String loc) {
		if (!isValidLoc(loc)) {return false;} //new location not valid
		else if (piece.getLocation().equals(loc)) {return false;} //moving to same location
		else if (getPiece(loc) != null && getPiece(loc).getIsWhite() == piece.getIsWhite()) {return false;} //piece of same color there
		
		String type = piece.getType();
		if (type.equals("rook") || type.equals("bishop") || type.equals("queen")) {
			char[] src = piece.getLocation().toCharArray(), dst = loc.toCharArray(); //from dst to src --> changes from domain of {-inf to inf} to {-1,0,1}
			int[] dir = new int[] {(2*src[0]-2*dst[0])/(Math.abs(src[0]-dst[0])+1),(2*src[1]-2*dst[1])/(Math.abs(src[1]-dst[1])+1)};
			ChessPiece chk = getPiece(new String(new char[] {(char) (dst[0]+dir[0]),(char) (dst[1]+dir[1])}));
			if (chk != null && chk.getIsWhite() != piece.getIsWhite()) {return false;}
		}
		
		if (type.equals("king") && Math.abs(loc.charAt(0)-piece.getLocation().charAt(0)) == 2) { //Castle
			String c = piece.getIsWhite()?"1":"8";
			String[] empty = loc.charAt(0)=='c'?new String[] {"d"+c,"c"+c,"b"+c}: new String[] {"f"+c,"g"+c};
			for (String em : empty) {if (getPiece(em) != null) {return false;}}
			
			String[] pos = loc.charAt(0)=='c'?new String[] {"e"+c,"d"+c,"a"+c}: new String[] {"e"+c,"f"+c,"h"+c};
			if (selfCheck(piece, pos[0]) || selfCheck(piece, pos[1])) {return false;}
			
			for (String move : moves) {
				if (move.substring(0,2).equals(pos[0]) || move.substring(3).equals(pos[0])) {return false;}
				if (move.substring(0,2).equals(pos[2]) || move.substring(3).equals(pos[2])) {return false;}
			}
		}
		
		if (type.equals("pawn")) {
			int ud = piece.getIsWhite()?1:-1;
			boolean capture = (getPiece(loc) != null);
			String src = piece.getLocation(), dst = loc;
			
			if (Math.abs(dst.charAt(0) - src.charAt(0)) == 1 && capture) {return dst.charAt(1) - src.charAt(1) == ud && !selfCheck(piece, loc);}
			else if (capture) {return false;} //diagonal capture
			
			if (dst.charAt(1)-src.charAt(1) == 2*ud && dst.charAt(0) == src.charAt(0)) { //double jump
				String coll = new String(new char[] {dst.charAt(0),piece.getIsWhite()?'3':'6'});
				if (src.charAt(1) != (piece.getIsWhite()?'2':'7') || getPiece(coll) != null) {return false;}
				else {return !selfCheck(piece, loc);}
			}
			
			if (dst.charAt(0) == src.charAt(0)) {return dst.charAt(1) - src.charAt(1) == ud && !selfCheck(piece, loc);} //regular forward
			
			//Below here must be En Passant
			int col = piece.getIsWhite()?0:1; // = |ud-1|/2
			char[][] locs = {{'5','4'},{'7','2'},{'6','3'}};
			ChessPiece cap = getPiece(new String(new char[] {dst.charAt(0),locs[0][col]}));
			if (cap == null || !cap.getType().equals("pawn")) {return false;}
			else if (locs[0][col] != src.charAt(1)) {return false;}
			else if (locs[0][col] != moves.get(moves.size()-1).charAt(4)) {return false;}
			else if (locs[1][col] != moves.get(moves.size()-1).charAt(1)) {return false;}
			else if (locs[2][col] != dst.charAt(1)) {return false;}
		}
		
		return !selfCheck(piece, loc); //only reach if knight or not false for type
	}
	
	/**
	 * Returns true if the proposed move will put the color's king in check
	 * @param piece the piece whose king would be put in check
	 * @param dst the destination of the piece
	 * @return true if the proposed move will put the color's king in check
	 */
	private boolean selfCheck(ChessPiece piece, String dst) { //true if WILL put self into check
		int index = -1;
		ChessPiece repl = null;
		for (int i = 0; i < board.size() && !piece.getLocation().equals(dst); i++) {
			if (board.get(i).getLocation().equals(dst)) {index = i; break;} //index of removed
		}
		if (index != -1) {repl = board.remove(index);}
		
		String old = piece.getLocation();
		piece.changeLocation(dst);
		
		char[] king = null;
		boolean color = piece.getIsWhite();
		for(ChessPiece p : board) {//king of piece's color
			if (p.getType().equals("king") && p.getIsWhite() == color) {king = p.getLocation().toCharArray();}
		}
		
		boolean chk = false;
		for (int i = 0; i < board.size(); i++) {
			ChessPiece p = board.get(i);
			if (p.getIsWhite() == color) {continue;} //don't check if piece is color of king
			char[] loc = p.getLocation().toCharArray();
			
			if (p.getType().equals("king")) {
				if (Math.abs(king[0]-loc[0]) < 1 && Math.abs(king[1]-loc[1]) < 1) {chk = true; break;}
			}
			
			if (p.getType().equals("knight")) {
				if (Math.abs(king[0]-loc[0]) == 2 && Math.abs(king[1]-loc[1]) == 1) {chk = true; break;}
				if (Math.abs(king[0]-loc[0]) == 1 && Math.abs(king[1]-loc[1]) == 2) {chk = true; break;}
			}
			
			if (p.getType().equals("pawn") && Math.abs(king[0]-loc[0]) == 1) {
				if (king[1] - loc[1] == (!color?1:-1)) {chk = true; break;}
			}
			
			if (p.getType().equals("rook") || p.getType().equals("queen")) {
				int[][] dirs = new int[][] {{1,0},{-1,0},{0,1},{0,-1}};
				for (int[] dir : dirs) {
					for (int d = 1; !chk; d++) {
						char[] mv = new char[] {(char) (loc[0]+d*dir[0]),(char) (loc[1]+d*dir[1])};
						if (!isValidLoc(new String(mv))) {break;}
						if (getPiece(new String(mv)) != null) {
							if (king[0] == mv[0] && king[1] == mv[1]) {chk = true;}
							break;
						}
					}
				}
			}
			
			if (p.getType().equals("bishop") || p.getType().equals("queen")) {
				int[][] dirs = new int[][] {{1,1},{-1,1},{1,-1},{-1,-1}};
				for (int[] dir : dirs) {
					for (int d = 1; !chk; d++) {
						char[] mv = new char[] {(char) (loc[0]+d*dir[0]),(char) (loc[1]+d*dir[1])};
						if (!isValidLoc(new String(mv))) {break;}
						if (getPiece(new String(mv)) != null) {
							if (king[0] == mv[0] && king[1] == mv[1]) {chk = true;}
							break;
						}
					}
				}
			}
		}
		
		piece.changeLocation(old);
		if (index != -1) {board.add(index, repl);}
		return chk;
	}
	
	/**
	 * Changes a pawn to another piece if it fulfills the conditions
	 * @param pawn the pawn to be changed
	 */
	private void kingMe(ChessPiece pawn) {
		String[] options = {"QUEEN","BISHOP","ROOK","KNIGHT","PAWN"};
		int p = JOptionPane.showOptionDialog(frame,"Change Your Pawn","Pawn Exchange",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE,null,options,null);
		pawn.changeType(options[p].toLowerCase());
	}
	
	/**
	 * Returns value based on state of game: running, check, checkmate, stalemate & applies any pawn changes
	 * @return -2 for white in checkmate, -1 for white in check, 0 for stalemate, 1 for black in check, 2 for black in checkmate
	 */
	private int endCond() {
		whiteTurn = !whiteTurn;
		setValidMoves();
		
		ChessPiece[] kings = {null, null}; //white king, black king
		boolean[] lock = {true, true}; //white locked, black locked
		for(ChessPiece p : board) {
			if (p.getType().equals("king")) {kings[p.getIsWhite()?0:1] = p;}
			if (!p.getMoves().equals("")) {lock[p.getIsWhite()?0:1] = false;}
		}
		boolean[] check = {selfCheck(kings[0],kings[0].getLocation()), selfCheck(kings[1],kings[1].getLocation())};
		
		if (board.size() == 2) {return 0;} //stalemate by only kings
		if (check[0] && lock[0]) {return -2;}
		if (check[0] && !lock[0]) {return -1;}
		if ((lock[0] && !check[0]) || (lock[1] && !check[1])) {return 0;}
		if (check[1] && !lock[1]) {return 1;}
		if (check[1] && lock[1]) {return 2;}
		
		return 3;
	}
}