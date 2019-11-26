package game2;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.awt.Frame;

import game2.ChessPiece;
//A1 is white rook on black square
//A8 is black rook on white square
public class ChessBoard {
	private ArrayList<ChessPiece> board;
	Frame frame;
	public boolean disp = false;
	
	/**
	 * Constructs a new chess board
	 */
	public ChessBoard(Frame frame) {
		board = new ArrayList<ChessPiece>(32);
		this.frame = frame;
		
		String[] type = {"rook","knight","bishop","queen","king","bishop","knight","rook"};
		String[] let = {"a","b","c","d","e","f","g","h"};
		for (int i=0; i<8; i++) {
			board.add(new ChessPiece(type[i], false, let[i]+1));
			board.add(new ChessPiece("pawn", false, let[i]+2));
			board.add(new ChessPiece("pawn", true, let[i]+7));
			board.add(new ChessPiece(type[i], true, let[i]+8));
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
	 * Returns the list of the pieces on the board
	 * @return the list of the pieces on the board
	 */
	public ArrayList<ChessPiece> getBoard() {
		return board;
	}
	
	/**
	 * Changes the type of the piece at the location loc to other
	 * @param loc the location of the piece
	 * @param other the new type of the piece
	 */
	public void changePiece(String loc, String other) {
		getPiece(loc).changeType(other);
	}
	
	/**
	 * Moves the piece from loc to other
	 * @param loc the old location of the piece
	 * @param other the new location of the piece
	 * @param castleOrEP true if the move is a castle or En Passant, false otherwise
	 */
	public void movePiece(String loc, String other, boolean castleOrEP) {
		if(castleOrEP || isValidMove(getPiece(loc), other)) {
			if (getPiece(other) != null) {board.remove(getPiece(other));}
			getPiece(loc).changeLocation(other);
		} else {
			JOptionPane.showMessageDialog(frame, "Move "+loc+" to "+other+" is not valid","",JOptionPane.ERROR_MESSAGE);
			disp = true;
		}	
	}
	
	/**
	 * Returns true if moving piece to loc is valid, false otherwise 
	 * @param piece the piece to be moved
	 * @param loc the new location of the piece
	 * @return true if moving piece to loc is valid, false otherwise
	 */
	public boolean isValidMove(ChessPiece piece, String loc) {
		try {
			if (!isValidLoc(loc)) {return false;}
			else if (piece.getLocation().equals(loc)) {return false;}
			else if (getPiece(loc) != null && getPiece(loc).getIsWhite() == piece.getIsWhite()) {return false;}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, "There is no piece at this location","",JOptionPane.ERROR_MESSAGE);
			disp = true;
			return false;
		}
		
		if (piece.getType().equals("rook") && piece.getLocation().charAt(0) != loc.charAt(0) && piece.getLocation().charAt(1) != loc.charAt(1)) {
			return isValidPath(piece, loc);
		}
		if (piece.getType().equals("knight")) {
			return (Math.abs(loc.charAt(0) - piece.getLocation().charAt(0)) == 2 && Math.abs(loc.charAt(1) - piece.getLocation().charAt(1)) == 1) || (Math.abs(loc.charAt(1) - piece.getLocation().charAt(1)) == 2 && Math.abs(loc.charAt(0) - piece.getLocation().charAt(0)) == 1);
		}
		if (piece.getType().equals("bishop")) {
			if (Math.abs(piece.getLocation().charAt(0)-loc.charAt(0)) != Math.abs(piece.getLocation().charAt(1)-loc.charAt(1))) {return isValidPath(piece, loc);}
		}
		
		if (piece.getType().equals("queen")) {
			return isValidMove(new ChessPiece("rook", !piece.getIsWhite(), piece.getLocation()), loc) || isValidMove(new ChessPiece("bishop", !piece.getIsWhite(), piece.getLocation()), loc);
		}
		if (piece.getType().equals("pawn")) {
			return validatePawn(piece.getIsWhite(), piece.getLocation(), loc);
		}
		if (piece.getType().equals("king")) {
			return Math.abs(piece.getLocation().charAt(0) - loc.charAt(0)) <= 1 && Math.abs(piece.getLocation().charAt(1) - loc.charAt(1)) <= 1;
		}
		
		return true;
	}
	
	/**
	 * Returns if the path from piece to loc is valid
	 * @param piece the piece that is to be moved
	 * @param loc the new location
	 * @return if the path from piece to loc is valid
	 */
	private boolean isValidPath(ChessPiece piece, String loc) {
		int dir=8;
		if (piece.getLocation().charAt(0) < loc.charAt(0)) {
			if (piece.getLocation().charAt(1) < loc.charAt(1)) {dir = 1;}
			else if (piece.getLocation().charAt(1) > loc.charAt(1)) {dir = 3;}
			else {dir = 2;}
		} else if (piece.getLocation().charAt(0) > loc.charAt(0)) {
			if (piece.getLocation().charAt(1) < loc.charAt(1)) {dir = 7;}
			else if (piece.getLocation().charAt(1) > loc.charAt(1)) {dir = 5;}
			else {dir = 6;}
		} else {
			if (piece.getLocation().charAt(1) < loc.charAt(1)) {dir = 0;}
			else if (piece.getLocation().charAt(1) > loc.charAt(1)) {dir = 4;}
		}
		
		String other = piece.getLocation();
		while (!other.equals(loc)) {
			if (piece.getType().equals("rook")) {
				if (dir==0) {
					if (this.getPiece(other.substring(0, 1)+(char)(other.charAt(1)+1)) != null && !(other.substring(0, 1)+(char)(other.charAt(1)+1)).equals(loc))
						return false;
					else
						other = (other.substring(0, 1)+(char)(other.charAt(1)+1));
				} else if (dir==2) {
					if (this.getPiece((char)(other.charAt(0)+1)+other.substring(1)) != null && !((char)(other.charAt(0)+1)+other.substring(1)).equals(loc))
						return false;
					else
						other = ((char)(other.charAt(0)+1)+other.substring(1));
				} else if (dir==4) {
					if (this.getPiece(other.substring(0, 1)+(char)(other.charAt(1)-1)) != null && !(other.substring(0, 1)+(char)(other.charAt(1)-1)).equals(loc))
						return false;
					else
						other = (other.substring(0, 1)+(char)(other.charAt(1)-1));
				} else {
					if (this.getPiece((char)(other.charAt(0)-1)+other.substring(1)) != null && !((char)(other.charAt(0)-1)+other.substring(1)).equals(loc))
						return false;
					else
						other = ((char)(other.charAt(0)-1)+other.substring(1));
				}
			} else if (piece.getType().equals("bishop")) {
				if (dir==1) {
					if (this.getPiece(""+(char)(other.charAt(0)+1)+(char)(other.charAt(1)+1)) != null && !(""+(char)(other.charAt(0)+1)+(char)(other.charAt(1)+1)).equals(loc))
						return false;
					else
						other = (""+(char)(other.charAt(0)+1)+(char)(other.charAt(1)+1));
				} else if (dir==3) {
					if (this.getPiece(""+(char)(other.charAt(0)+1)+(char)(other.charAt(1)-1)) != null && !(""+(char)(other.charAt(0)+1)+(char)(other.charAt(1)-1)).equals(loc))
						return false;
					else
						other = (""+(char)(other.charAt(0)+1)+(char)(other.charAt(1)-1));
				} else if (dir==5) {
					if (this.getPiece(""+(char)(other.charAt(0)-1)+(char)(other.charAt(1)+1)) != null && !(""+(char)(other.charAt(0)-1)+(char)(other.charAt(1)+1)).equals(loc))
						return false;
					else
						other = (""+(char)(other.charAt(0)-1)+(char)(other.charAt(1)+1));
				} else {
					if (this.getPiece(""+(char)(other.charAt(0)-1)+(char)(other.charAt(1)-1)) != null && !(""+(char)(other.charAt(0)-1)+(char)(other.charAt(1)-1)).equals(loc))
						return false;
					else
						other = (""+(char)(other.charAt(0)-1)+(char)(other.charAt(1)-1));
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Returns true if the location loc is valid, false otherwise
	 * @param loc the location to be checked
	 * @return true if the location loc is valid, false otherwise
	 */
	private boolean isValidLoc(String loc) {
		if ((int) loc.charAt(0) < 97 || (int) loc.charAt(0) > 104) {return false;}
		else if (Integer.decode(loc.substring(1)) > 8 || Integer.decode(loc.substring(1)) < 1) {return false;}
		else {return true;}
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
		
		if (Math.abs(other.charAt(0) - loc.charAt(0)) == 1) {
			if (isWhite) {return capture && other.charAt(1) - loc.charAt(1) == 1;}
			else {return capture && loc.charAt(1) - other.charAt(1) == 1;}
		}
		
		if (Math.abs(other.charAt(1)-loc.charAt(1))==2 && Math.abs(other.charAt(0)-loc.charAt(0))==0) {
			if (isWhite) {return loc.charAt(1)=='2';}
			else {return loc.charAt(1)=='7';}
		}
		
		if (capture) {return false;}
		
		if (isWhite) {return other.charAt(0)==loc.charAt(0) && other.charAt(1) - loc.charAt(1) == 1;}
		else {return other.charAt(0)==loc.charAt(0) && other.charAt(1) - loc.charAt(1) == -1;}
	}
}