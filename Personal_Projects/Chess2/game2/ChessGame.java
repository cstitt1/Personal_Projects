package game2;

import java.awt.Frame;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import game2.ChessBoard;
import game2.ChessPiece;

public class ChessGame{
	private ChessBoard board;
	private ArrayList<String> moves;
	private Frame frame;
	
	/**
	 * Constructs a new chess game.
	 */
	public ChessGame(Frame frame) {
		this.frame = frame;
		board = new ChessBoard(frame);
		moves = new ArrayList<String>();
	}
	
	/**
	 * Returns the chess board
	 * @return the chess board
	 */
	public ChessBoard getChessBoard() {
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
	 * Returns true if the king is in check
	 * @param kingIsWhite true if the king is white, false if it is black
	 * @return true if the king is in check
	 */
	public boolean check(boolean kingIsWhite) {
		ChessPiece king = null;
		for(ChessPiece p : board.getBoard()) {
			if (p.getType().equals("king") && p.getIsWhite() == kingIsWhite) {king = p;}
		}
		
		for(ChessPiece p : board.getBoard()) {
			if (p.getIsWhite() != kingIsWhite && !p.getType().equals("king")) {
				if (board.isValidMove(p, king.getLocation())) {return true;}
			}
		}
		
		return false;
	}
	
	/**
	 * Returns true if the king is in checkmate, false otherwise
	 * @param kingIsWhite true if the king is white, false otherwise
	 * @return true if the king is in checkmate, false otherwise
	 */
	public boolean checkmate(boolean kingIsWhite) {
		if (!check(kingIsWhite)) {return false;}
		
		ChessPiece king = null;
		for(ChessPiece p : board.getBoard()) {
			if (p.getType().equals("king") && p.getIsWhite() == kingIsWhite) {king = p;}
		}
		
		String kingLoc = king.getLocation();
		
		for (int i = kingLoc.charAt(0)-1; i <= kingLoc.charAt(0)+1; i++) {
			for (int j = kingLoc.charAt(1)-1; j <= kingLoc.charAt(1)+1; j++) {
				if (board.isValidMove(king, ""+((char) i) + ((char) j))) {
					board.movePiece(king.getLocation(), ""+((char) i) + ((char) j), false);
					if (check(kingIsWhite))
						board.movePiece(""+((char) i) + ((char) j), kingLoc, false);
					else
						return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Makes one move of a piece, if valid
	 * @param move A string in the format location + space + other location
	 */
	public void makeMove(String move, boolean castleOrEnPassant) {
		moves.add(move);
		String loc = move.substring(0, 2);
		String other = move.substring(3);
		board.movePiece(loc, other, castleOrEnPassant);
	}
	
	/**
	 * Returns true if player can castle, false otherwise
	 * @param kingIsWhite if the king trying to castle if white, true; otherwise, false
	 * @param kingSide if the castle is on the king side, true; otherwise, false
	 * @return true if player can castle, false otherwise
	 */
	public boolean canCastle(boolean kingIsWhite, boolean kingSide) {
		for (String move : moves) {
			if (kingIsWhite) {
				if (kingSide) {
					if (move.substring(0, 2).equals("h1") || move.substring(0, 2).equals("e1")) {return false;}
				} else {
					if (move.substring(0, 2).equals("a1") || move.substring(0, 2).equals("e1")) {return false;}
				}
			} else {
				if (kingSide) {
					if (move.substring(0, 2).equals("h8") || move.substring(0, 2).equals("e8")) {return false;}
				} else {
					if (move.substring(0, 2).equals("a8") || move.substring(0, 2).equals("e8")) {return false;}
				}
			}
		}
		
		for(ChessPiece p : board.getBoard()) {
			if (kingIsWhite) {
				if (kingSide) {
					if (p.getLocation().equals("f1") || p.getLocation().equals("g1")) {return false;}
				} else {
					if (p.getLocation().equals("b1") || p.getLocation().equals("c1") || p.getLocation().equals("d1")) {return false;}
				}
			} else {
				if (kingSide) {
					if (p.getLocation().equals("f8") || p.getLocation().equals("g8")) {return false;}
				} else {
					if (p.getLocation().equals("b8") || p.getLocation().equals("c8") || p.getLocation().equals("d8")) {return false;}
				}
			}
		}
		
		return !check(kingIsWhite);
	}
	
	/**
	 * Makes a castle, if valid
	 * @param kingIsWhite if the king trying to castle if white, true; otherwise, false
	 * @param kingSide if the castle is on the king side, true; otherwise, false
	 */
	public void castle(boolean kingIsWhite, boolean kingSide) {
		if (!canCastle(kingIsWhite, kingSide)) {
			JOptionPane.showMessageDialog(frame, "Castle is not valid","",JOptionPane.ERROR_MESSAGE);
			board.disp = true;
			return;
		}
			
		
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
		
		makeMove(rookMove, true);
		makeMove(kingMove, true);
	}
	
	/**
	 * Returns true if player can en passant, false otherwise
	 * @param yourLoc The location of your pawn
	 * @param otherLoc The position of the other pawn
	 * @return true if player can en passant, false otherwise
	 */
	public boolean canEnPassant(String yourLoc, String otherLoc) {
		if (yourLoc.equals(otherLoc) || board.getPiece(yourLoc) == null || board.getPiece(otherLoc) == null || !board.getPiece(yourLoc).getType().equals("pawn") || !board.getPiece(otherLoc).getType().equals("pawn"))
			return false;
		
		if (board.getPiece(yourLoc).getIsWhite() == board.getPiece(otherLoc).getIsWhite()) {return false;}
		
		if (yourLoc.charAt(1) == '5' && moves.contains(otherLoc.substring(0,1) + "7 " + otherLoc)) {return board.getPiece(otherLoc.substring(0, 1) + "6") == null;}
		else if (yourLoc.charAt(1) == '4' && moves.contains(otherLoc.substring(0,1) + "2 " + otherLoc)) {return board.getPiece(otherLoc.substring(0, 1) + "3") == null;}
		else {return false;}
	}
	
	/**
	 * Makes an En Passant if valid
	 * @param yourLoc the location of your piece
	 * @param otherLoc the location of the other piece
	 */
	public void enPassant(String yourLoc, String otherLoc) {
		if (!canEnPassant(yourLoc, otherLoc)) {
			JOptionPane.showMessageDialog(frame, "En Passant is not valid","",JOptionPane.ERROR_MESSAGE);
			board.disp = true;
			return;
		}
		
		board.movePiece(yourLoc, otherLoc, true);
		
		if (yourLoc.charAt(1) == '5') {makeMove(otherLoc.substring(0, 1) + "5 "+ otherLoc.substring(0, 1) + "6", true);}
		else if (yourLoc.charAt(1) == '4') {makeMove(otherLoc.substring(0, 1) + "4 "+ otherLoc.substring(0, 1) + "3", true);}
	}
}