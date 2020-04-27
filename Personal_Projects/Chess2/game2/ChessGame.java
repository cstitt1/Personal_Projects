package game2;

import java.awt.Frame;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ChessGame{
	private ArrayList<ChessPiece> board;
	private ArrayList<String> moves;
	private Frame frame;
	public boolean disp = false;
	private boolean isEP = false;
	private boolean isCastle = false;
	private ChessPiece kingMe = null;
	
	//---------------------------------------------------------------------
	// Basic Functions
	//---------------------------------------------------------------------
	
	/**
	 * Constructs a new chess game.
	 */
	public ChessGame(Frame frame) {
		this.frame = frame;
		board = new ArrayList<ChessPiece>(32);
		moves = new ArrayList<String>();
		
		String[] type = {"rook","knight","bishop","queen","king","bishop","knight","rook"};
		String[] let = {"a","b","c","d","e","f","g","h"};
		for (int i=0; i<8; i++) {
			board.add(new ChessPiece(type[i], true, let[i]+1));
			board.add(new ChessPiece("pawn", true, let[i]+2));
			board.add(new ChessPiece("pawn", false, let[i]+7));
			board.add(new ChessPiece(type[i], false, let[i]+8));
		}
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
	 * Returns true if the location loc is valid, false otherwise
	 * @param loc the location to be checked
	 * @return true if the location loc is valid, false otherwise
	 */
	private boolean isValidLoc(String loc) {
		if (loc.charAt(0) < 'a' || loc.charAt(0) > 'h') {return false;}
		else if (loc.charAt(1) > '8' || loc.charAt(1) < '1') {return false;}
		else {return true;}
	}
	
	//---------------------------------------------------------------------
	// Movement Functions
	//---------------------------------------------------------------------
	
	/**
	 * Makes one move of a piece, if valid
	 * @param move A string in the format location + space + other location
	 */
	public void makeMove(String move) {
		String loc = move.substring(0, 2);
		String other = move.substring(3);
		ChessPiece p = getPiece(loc);
		isEP = false; isCastle = false;
		boolean scheck = selfCheck(p, other);
		
		if (!(isValidMove(p, other) && scheck)) {
			JOptionPane.showMessageDialog(frame, "Move "+loc+" to "+other+" is not valid","",JOptionPane.ERROR_MESSAGE);
			disp = true;
		}
		
		if (isEP) {isEP = false; board.remove(getPiece(new String(new char[] {loc.charAt(0),p.getIsWhite()?'5':'4'})));}
		if (isCastle) {isCastle = false; castle(p.getIsWhite(), other.charAt(0)=='g'); return;}
		if(p.getType().equals("pawn") && other.charAt(0)==(p.getIsWhite()?'8':'1')) {kingMe = p;}
		
		if (getPiece(other) != null) {board.remove(getPiece(other));}
		p.changeLocation(other);
		moves.add(move);
	}
	
	/**
	 * Returns true if the move will not put self into check or will remove self from check
	 * @param piece the piece to be moved
	 * @param loc the new location of the piece
	 * @return true if the move will not put self into check or will remove self from check, false otherwise
	 */
	private boolean selfCheck(ChessPiece piece, String loc) {
		boolean tcol = piece.getIsWhite();
		
		ChessPiece rem = getPiece(loc);
		if (rem != null) {board.remove(rem);}
		
		String old = piece.getLocation();
		piece.changeLocation(loc);
		
		ChessPiece king = null;
		for(ChessPiece p : board) {
			if (p.getType().equals("king") && p.getIsWhite() == tcol) {king = p;}
		}
		
		for(ChessPiece p : board) {
			if (p.getIsWhite() != tcol && isValidMove(p, king.getLocation())) {
				piece.changeLocation(old);
				if (rem != null) {board.add(rem);}
				return false;
			}
		}
		
		piece.changeLocation(old);
		if (rem != null) {board.add(rem);}
		return true;
	}
	
	/**
	 * Returns true if moving piece to loc is valid, false otherwise 
	 * @param piece the piece to be moved
	 * @param loc the new location of the piece
	 * @return true if moving piece to loc is valid, false otherwise
	 */
	private boolean isValidMove(ChessPiece piece, String loc) {
		try {
			if (!isValidLoc(loc)) {return false;} //new location not valid
			else if (piece.getLocation().equals(loc)) {return false;} //moving to same location
			else if (getPiece(loc) != null && getPiece(loc).getIsWhite() == piece.getIsWhite()) {return false;} //piece of same color there
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, "There is no piece at this location","",JOptionPane.ERROR_MESSAGE);
			disp = true;
			return false;
		}
		
		char[] src = piece.getLocation().toCharArray();
		char[] dst = loc.toCharArray();
		
		if (piece.getType().equals("rook") && (src[0] == dst[0] || src[1] == dst[1])) {
			int cor = src[0] == dst[0]?1:0; // 1 if moving through column, 0 if row
			boolean dir = src[cor] < dst[cor]; // for 1->8/a->h, true is piece before new loc and false otherwise
			
			char limL = dir?src[cor]:dst[cor];
			char limH = dir?dst[cor]:src[cor];
			
			for (int i = limL+1; i <= limH-1; i++) { //collision on path detection
				char[] coll = new char[2];
				coll[1-cor] = src[1-cor];
				coll[cor] = (char) i;
				
				if (getPiece(new String(coll)) != null) {return false;}
			}
			
			return true;
		}
		
		if (piece.getType().equals("knight")) {
			boolean turnL1 = Math.abs(dst[0] - src[0]) == 2 && Math.abs(dst[1] - src[1]) == 1;
			boolean turnL2 = Math.abs(dst[1] - src[1]) == 2 && Math.abs(dst[0] - src[0]) == 1;
			return turnL1 || turnL2;
		}
		
		if (piece.getType().equals("bishop") && Math.abs(src[0]-dst[0]) == Math.abs(src[1]-dst[1])) {
			int rl = src[0] < dst[0]?1:-1; // 1 for moving right, -1 for moving left
			int ud = src[1] < dst[1]?1:-1; // 1 for moving up, -1 for moving down
			
			char[] coll = new char[] {(char) (src[0] + rl), (char) (src[1] + ud)};
			while (coll[0] != dst[0] && coll[1] != dst[1]) { //collision detection
				if (getPiece(new String(coll)) != null) {return false;}
				coll[0] = (char) (coll[0]+rl);
				coll[1] = (char) (coll[1]+ud);
			}
			
			return true;
		}
		
		if (piece.getType().equals("queen")) {
			boolean rStyle = isValidMove(new ChessPiece("rook", piece.getIsWhite(), piece.getLocation()), loc);
			boolean bStyle = isValidMove(new ChessPiece("bishop", piece.getIsWhite(), piece.getLocation()), loc);
			return rStyle || bStyle;
		}
		
		if (piece.getType().equals("pawn")) {
			return validatePawn(piece.getIsWhite(), piece.getLocation(), loc) || validEnPassant(piece, loc);
		}
		
		if (piece.getType().equals("king")) {
			return (Math.abs(src[0] - dst[0]) <= 1 && Math.abs(src[1] - dst[1]) <= 1) || validCastle(piece, loc);
		}
		
		return false;
	}
	
	/**
	 * Returns true if the pawn's move is valid, false otherwise
	 * @param isWhite true if the pawn is white, false otherwise
	 * @param loc the old location of the pawn
	 * @param other the new position of the pawn
	 * @return true if the pawn's move is valid, false otherwise
	 */
	private boolean validatePawn(boolean isWhite, String loc, String other) {
		if (loc.equals("") || other.equals("")) {return false;}
		
		boolean capture = false;
		if (getPiece(other) != null) {capture = true;}
		
		if (Math.abs(other.charAt(0) - loc.charAt(0)) == 1) { //diagonal capture
			if (isWhite) {return capture && other.charAt(1) - loc.charAt(1) == 1;}
			else {return capture && loc.charAt(1) - other.charAt(1) == 1;}
		}
		
		if (Math.abs(other.charAt(1)-loc.charAt(1))==2 && Math.abs(other.charAt(0)-loc.charAt(0))==0) {//start double jump
			if (isWhite) {return loc.charAt(1)=='2';}
			else {return loc.charAt(1)=='7';}
		}
		
		if (capture) {return false;}
		
		if (isWhite) {return other.charAt(0)==loc.charAt(0) && other.charAt(1) - loc.charAt(1) == 1;}
		else {return other.charAt(0)==loc.charAt(0) && other.charAt(1) - loc.charAt(1) == -1;}
	}
	
	/**
	 * Returns true if the proposed en passant is valid & sets the isEP flag
	 * @param pawn the pawn to en passant
	 * @param loc the location it would move to
	 * @return true if the proposed en passant is valid; false otherwise
	 */
	private boolean validEnPassant(ChessPiece pawn, String loc) {
		int col = pawn.getIsWhite()?0:1;
		char[][] locs = {{'5','4'},{'7','2'},{'6','3'}};
		isEP = false;
		
		ChessPiece cap = getPiece(new String(new char[] {loc.charAt(0),locs[0][col]}));
		if (cap == null) {return false;}
		else {if (!cap.getType().equals("pawn")) {return false;}}
		
		if (locs[0][col] != pawn.getLocation().charAt(1)) {return false;}
		if (locs[0][col] != moves.get(moves.size()-1).charAt(1)) {return false;}
		if (locs[1][col] != moves.get(moves.size()-1).charAt(4)) {return false;}
		if (cap.getLocation().charAt(0) != loc.charAt(0)) {return false;}
		if (locs[2][col] != loc.charAt(1)) {return false;}
		
		isEP = true;
		return true;
	}
	
	/**
	 * Returns true if the proposed castle is valid & sets the isCastle flag
	 * @param king the king to castle
	 * @param loc the location it would move to
	 * @return true if the proposed castle is valid; false otherwise
	 */
	private boolean validCastle(ChessPiece king, String loc) {
		//king & rook haven't moved & can't castle into or through or out of check
		if (Math.abs((int) (loc.charAt(0)-'e')) == 2) {return false;}//c or g
		char rcol = loc.charAt(0)=='c'?'a':'h';
		char color = king.getIsWhite()?'1':'8';
		isCastle = false;
		
		String rLoc = new String(new char[] {rcol,color});
		ChessPiece rook = getPiece(rLoc);
		if (rook==null || !rook.getType().equals("rook")) {return false;}
		for (String move : moves) {
			if (move.substring(0,2).equals(rLoc)) {return false;}
			if (move.substring(3).equals(rLoc)) {return false;}
			if (move.substring(0,2).equals(king.getLocation())) {return false;}
			if (move.substring(3).equals(king.getLocation())) {return false;}
		}
		

		if (selfCheck(king,new String(new char[] {rcol=='a'?'d':'f', color}))) {return false;}
		for (char i = (rcol=='a'?'b':'f');i <= (rcol=='a'?'d':'g');i++) {
			if (getPiece(new String(new char[] {i, color})) != null) {return false;}
		}
		
		isCastle = !check(king);
		return isCastle;
	}
	
	/**
	 * Makes a castle, if valid
	 * @param kingIsWhite if the king trying to castle if white, true; otherwise, false
	 * @param kingSide if the castle is on the king side, true; otherwise, false
	 */
	private void castle(boolean kingIsWhite, boolean kingSide) {
		String rookMove = "";
		String kingMove = "";
		
		if (kingIsWhite) {
			if (kingSide) {
				rookMove = "h1 f1";
				kingMove = "e1 g1";
			} else {
				rookMove = "a1 d1";
				kingMove = "e1 c1";
			}
		} else {
			if (kingSide) {
				rookMove = "h8 f8";
				kingMove = "e8 g8";
			} else {
				rookMove = "a8 d8";
				kingMove = "e8 c8";
			}
		}
		
		ChessPiece king = getPiece(kingMove.substring(0,2));
		king.changeLocation(kingMove.substring(3));
		moves.add(kingMove);
		
		ChessPiece rook = getPiece(rookMove.substring(0,2));
		rook.changeLocation(rookMove.substring(3));
		moves.add(rookMove);
	}
	
	//---------------------------------------------------------------------
	// Game State Functions
	//---------------------------------------------------------------------
	
	/**
	 * Returns value based on state of game: running, check, checkmate, stalemate & applies any pawn changes
	 * @return -2 for white in checkmate, -1 for white in check, 0 for stalemate, 1 for black in check, 2 for black in checkmate
	 */
	public int endCond() {
		changePawn();
		
		ChessPiece[] kings = {null, null}; //white king, black king
		boolean[] lock = {true, true}; //white locked, black locked
		for(ChessPiece p : board) {
			if (p.getType().equals("king")) {kings[p.getIsWhite()?0:1] = p;}
			if (hasValidMove(p)) {lock[p.getIsWhite()?0:1] = false;}
		}
		boolean[] check = {check(kings[0]), check(kings[1])}; //white check, black check
		
		if (board.size() == 2) {return 0;} //stalemate by only kings
		if (check[0] && lock[0]) {return -2;}
		if (check[0] && !lock[0]) {return -1;}
		if ((lock[0] && !check[0]) || (lock[1] && !check[1])) {return 0;}
		if (check[1] && !lock[1]) {return 1;}
		if (check[1] && lock[1]) {return 2;}
		
		return 3;
	}
	
	/**
	 * Changes a pawn to another piece if it fulfills the conditions
	 */
	private void changePawn() {
		if (kingMe == null) {return;}
		
		String[] options = {"QUEEN","BISHOP","ROOK","KNIGHT","PAWN"};
		int p = JOptionPane.showOptionDialog(frame,"Change Your Pawn","Pawn Exchange",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE,null,options,null);
		kingMe.changeType(options[p].toLowerCase());
		
		kingMe = null;
	}
	
	/**
	 * Returns true if the king is in check
	 * @param king the king piece in question
	 * @return true if the king is in check
	 */
	private boolean check(ChessPiece king) {
		boolean kingIsWhite = king.getIsWhite();
		
		for(ChessPiece p : board) {
			if (p.getIsWhite() != kingIsWhite && isValidMove(p, king.getLocation())) {return true;}
		}
		
		return false;
	}
	
	/**
	 * Returns true if piece has any valid moves
	 * @param piece the chess piece in question
	 * @return true if the piece has any valid moves
	 */
	public boolean hasValidMove(ChessPiece piece) {
		char[] loc = piece.getLocation().toCharArray();
		String type = piece.getType();
		
		if (type.equals("rook")) {return vmRook(piece, loc);}
		if (type.equals("bishop")) {return vmBishop(piece, loc);}
		if (type.equals("queen")) {return vmRook(piece, loc) || vmBishop(piece, loc);}
		
		int[][] mvs = {};
		if (type.equals("kinght")) {mvs =  new int[][] {{1,2},{1,-2},{-1,2},{-1,-2},{2,1},{2,-1},{-2,1},{-2,-1}};}		
		if (type.equals("king")) {mvs = new int[][] {{1,0},{1,-1},{0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{2,0},{-2,0}};}
		if (type.equals("pawn")) {
			int dir = piece.getIsWhite()?1:-1;//white goes up, black comes down
			mvs = new int[][] {{0,dir},{0,2*dir},{1,dir},{-1,dir}};
		}
		
		for (int i = 0; i < mvs.length; i++) {
			String mv = new String(new char[] {(char)(loc[0]+mvs[i][0]),(char)(loc[1]+mvs[i][1])});
			if (isValidMove(piece, mv) && selfCheck(piece, mv)) {return true;}
		}
		return false;
	}
	
	/**
	 * Helper function for hasValidMove
	 * @param rook the rook in question
	 * @param loc the rook's location
	 * @return true if the rook has any valid moves
	 */
	private boolean vmRook(ChessPiece rook, char[] loc) {
		char[] mv = loc;
		for (int i = 1; isValidLoc(new String(mv)); i++) {
			mv = new char[] {(char)(loc[0]+i), loc[1]};
			if (isValidMove(rook, new String(mv)) && selfCheck(rook, new String(mv))) {return true;}
		}
		
		mv = loc;
		for (int i = 1; isValidLoc(new String(mv)); i++) {
			mv = new char[] {(char)(loc[0]-i), loc[1]};
			if (isValidMove(rook, new String(mv)) && selfCheck(rook, new String(mv))) {return true;}
		}
		
		mv = loc;
		for (int i = 1; isValidLoc(new String(mv)); i++) {
			mv = new char[] {loc[0], (char)(loc[1]+i)};
			if (isValidMove(rook, new String(mv)) && selfCheck(rook, new String(mv))) {return true;}
		}
		
		mv = loc;
		for (int i = 1; isValidLoc(new String(mv)); i++) {
			mv = new char[] {loc[0], (char)(loc[1]-i)};
			if (isValidMove(rook, new String(mv)) && selfCheck(rook, new String(mv))) {return true;}
		}
		
		return false;
	}
	
	/**
	 * Helper function for hasValidMove
	 * @param bishop the bishop in question
	 * @param loc the bishop's location
	 * @return true if the bishop has any valid moves
	 */
	private boolean vmBishop(ChessPiece bishop, char[] loc) {
		char[] mv = loc;
		for (int i = 1; isValidLoc(new String(mv)); i++) {
			mv = new char[] {(char)(loc[0]+i), (char)(loc[1]+i)};
			if (isValidMove(bishop, new String(mv)) && selfCheck(bishop, new String(mv))) {return true;}
		}
		
		mv = loc;
		for (int i = 1; isValidLoc(new String(mv)); i++) {
			mv = new char[] {(char)(loc[0]+i), (char)(loc[1]-i)};
			if (isValidMove(bishop, new String(mv)) && selfCheck(bishop, new String(mv))) {return true;}
		}
		
		mv = loc;
		for (int i = 1; isValidLoc(new String(mv)); i++) {
			mv = new char[] {(char)(loc[0]-i), (char)(loc[1]+i)};
			if (isValidMove(bishop, new String(mv)) && selfCheck(bishop, new String(mv))) {return true;}
		}
		
		mv = loc;
		for (int i = 1; isValidLoc(new String(mv)); i++) {
			mv = new char[] {(char)(loc[0]-i), (char)(loc[1]-i)};
			if (isValidMove(bishop, new String(mv)) && selfCheck(bishop, new String(mv))) {return true;}
		}
		
		return false;
	}
}