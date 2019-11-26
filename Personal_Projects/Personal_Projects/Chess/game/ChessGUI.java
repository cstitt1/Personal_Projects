package game;

import processing.core.PApplet;
import processing.core.PImage;
//1=49, a=97
//TODO: Fix Queen-side castle (both colors)
public class ChessGUI extends PApplet{
	private ChessGame game;
	private String move;
	private boolean isWhiteTurn;
	
	/**
	 * Sets up GUI; run once
	 */
	public void setup() {
		size(730,730); //728 = 8 * 91
		PImage background = loadImage("../../images/b4.png");
		image(background, 0, 0);
		
		game = new ChessGame();
		isWhiteTurn = true;
		move = "";
	}
	
	/**
	 * Runs infinitely until end of program to draw GUI
	 */
	public void draw() {
		PImage background = loadImage("../../images/b4.png");
		image(background, 0, 0);
		
		String pieceTitle = "";
		
		for (ChessPiece piece : game.getChessBoard().getBoard()) {
			if(piece.getLocation().equals(""))
				piece.changeLocation(game.getMoves().get(game.getMoves().size()-1).substring(3));
			
			pieceTitle = "";
			
			if (piece.getIsWhite())
				pieceTitle += "white";
			else
				pieceTitle += "black";
			
			pieceTitle += piece.getType();
			
			int back = (((int) piece.getLocation().charAt(0))-96) + (((int) piece.getLocation().charAt(1))-48);
			if (back % 2 == 0)
				pieceTitle += "black";
			else
				pieceTitle += "white";
			
			image(loadImage("../../images/"+pieceTitle+".PNG"),(((int) piece.getLocation().charAt(0))-97)*91,(56-((int) piece.getLocation().charAt(1)))*91);
		}
		
		textSize(20);
		String[] let = {"a","b","c","d","e","f","g","h"};
		for (int i=0; i<8; i++) {
			for (int j=1; j <= 8; j++) {
				text(let[i]+j,i*91,(8-j)*91+20);
			}
		}
	}
	
	/**
	 * Interprets the key pressed any time a key is pressed
	 */
	public void keyPressed() {
		move += (char) keyCode;
		
		if (move.length() == 5) {
			performMove(move.toLowerCase());
			move = "";
			
			if (game.check(true))
				if (game.checkmate(true))
					System.out.println("White is in checkmate!");
				else
					System.out.println("White is in check!");
			else if (game.check(false))
				if (game.checkmate(false))
					System.out.println("Black is in checkmate!");
				else
					System.out.println("Black is in check!");
		}
	}
	
	/**
	 * Performs the move coded by moveToMake
	 * @param moveToMake the move coded by oldLocation + space + newLocation
	 */
	private void performMove(String moveToMake) {
//		if (game.getChessBoard().getPiece(moveToMake.substring(0, 2)) != null && game.getChessBoard().getPiece(moveToMake.substring(0, 2)).getIsWhite() != isWhiteTurn)
//			throw new IllegalStateException("It is not your turn to move.");
		
		if (isWhiteTurn)
			if (moveToMake.charAt(3) == 'g' && game.canCastle(isWhiteTurn, moveToMake.charAt(3) == 'g')) {
				if (moveToMake.substring(3).equals("g1") && game.getChessBoard().getPiece(moveToMake.substring(0, 2)).getType().equals("king")) {
					game.castle(true, true);
					isWhiteTurn = !isWhiteTurn;
					return;
				}
			} else if (moveToMake.charAt(3) == 'c' && game.canCastle(isWhiteTurn, moveToMake.charAt(3) != 'c')) {
				if (moveToMake.substring(3).equals("c1") && game.getChessBoard().getPiece(moveToMake.substring(0, 2)).getType().equals("king")) {
					game.castle(true, false);
					isWhiteTurn = !isWhiteTurn;
					return;
				}
			}
		else
			if (moveToMake.charAt(3) == 'g' && game.canCastle(isWhiteTurn, moveToMake.charAt(3) == 'g')) {
				if (moveToMake.substring(3).equals("g8") && game.getChessBoard().getPiece(moveToMake.substring(0, 2)).getType().equals("king")) {
					game.castle(false, true);
					isWhiteTurn = !isWhiteTurn;
					return;
				}
			} else if (moveToMake.charAt(3) == 'c' && game.canCastle(isWhiteTurn, moveToMake.charAt(3) != 'c')) {
				if (moveToMake.substring(3).equals("c8") && game.getChessBoard().getPiece(moveToMake.substring(0, 2)).getType().equals("king")) {
					game.castle(false, false);
					isWhiteTurn = !isWhiteTurn;
					return;
				}
			}
		
		int offset=1;
		if (isWhiteTurn)
			offset*=-1;
		if (game.canEnPassant(moveToMake.substring(0,2), moveToMake.substring(3,4)+(char)(moveToMake.charAt(4)+offset)))
			game.enPassant(moveToMake.substring(0,2), moveToMake.substring(3,4)+(char)(moveToMake.charAt(4)+offset));
		else
			game.makeMove(moveToMake, false);
		
//		isWhiteTurn = !isWhiteTurn;
	}
}