package game2;

import javax.swing.JOptionPane;
import processing.core.*;

@SuppressWarnings("serial")
//TODO: Fix en passant
public class ChessGUI extends PApplet{
	private ChessGame game;
	private String move;
	private boolean isWhiteTurn;
	
	public void setup() {
		size(632,632); //632 = 8*79
		game = new ChessGame(frame);
		isWhiteTurn = true;
		move = "";
	}
	
	public void draw() {
		int dim = 79;
		
		PImage bg = createImage(dim*8,dim*8,RGB);
		bg.loadPixels();
		int col = 0;
		//              green       beige
		int[][] cols = {{34,139,34},{215,192,149}};
		for(int i = 0; i < bg.pixels.length; ++i) {
			if (i%dim == 0) {col = col == 0?1:0;}
			if (i%(dim*dim*8) == 0) {col = col==0?1:0;}
			bg.pixels[i] = color(cols[col][0],cols[col][1],cols[col][2]);
		}
		
		PieceDraw drawer = new PieceDraw(bg.pixels);
		for (ChessPiece piece : game.getChessBoard().getBoard()) {drawPieces(drawer,piece);}
		
		bg.updatePixels();
		image(bg,0,0);
		
		textSize(15);
		fill(0,0,255);
		String[] let = {"a","b","c","d","e","f","g","h"};
		for (int i=0; i<8; i++) {
			for (int j=1; j <= 8; j++) {
				text(let[i]+j,i*79,(8-j)*79+15);
			}
		}
	}
	
	private void drawPieces(PieceDraw d, ChessPiece p) {
		int color = p.getIsWhite()?255:0;
		//1=49, a=97
		int coor = (p.getLocation().charAt(0)-96)*10 + p.getLocation().charAt(1)-48;
		
		if(p.getType().equals("king")) {d.king(color, coor);}
		else if(p.getType().equals("queen")) {d.queen(color, coor);}
		else if(p.getType().equals("knight")) {d.knight(color, coor);}
		else if(p.getType().equals("bishop")) {d.bishop(color, coor);}
		else if(p.getType().equals("rook")) {d.rook(color, coor);}
		else {d.pawn(color, coor);}
	}
	
	/**
	 * Interprets the key pressed any time a key is pressed
	 */
	public void keyPressed() {
		move += (char) keyCode;
		
		if (move.length() == 5) {
			performMove(move.toLowerCase());
			move = "";
			
			if (game.check(true)) {
				if (game.checkmate(true)) {System.out.println("White is in checkmate!");}
				else {System.out.println("White is in check!");}
			} else if (game.check(false)) {
				if (game.checkmate(false)) {System.out.println("Black is in checkmate!");}
				else {System.out.println("Black is in check!");}
			}
		}
	}
	
	/**
	 * Performs the move coded by moveToMake
	 * @param moveToMake the move coded by oldLocation + space + newLocation
	 */
	private void performMove(String moveToMake) {
		if (game.getChessBoard().getPiece(moveToMake.substring(0, 2)) != null && game.getChessBoard().getPiece(moveToMake.substring(0, 2)).getIsWhite() != isWhiteTurn) {
			JOptionPane.showMessageDialog(frame, "It is not your turn to move","",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (isWhiteTurn) {
			if (moveToMake.charAt(3) == 'g' && game.canCastle(isWhiteTurn, moveToMake.charAt(3) == 'g')) {
				if (moveToMake.substring(3).equals("g1") && game.getChessBoard().getPiece(moveToMake.substring(0, 2)).getType().equals("king")) {
					game.castle(true, true);
					if (!game.getChessBoard().disp) {isWhiteTurn = !isWhiteTurn;}
					game.getChessBoard().disp = false;
					return;
				}
			} else if (moveToMake.charAt(3) == 'c' && game.canCastle(isWhiteTurn, moveToMake.charAt(3) != 'c')) {
				if (moveToMake.substring(3).equals("c1") && game.getChessBoard().getPiece(moveToMake.substring(0, 2)).getType().equals("king")) {
					game.castle(true, false);
					if (!game.getChessBoard().disp) {isWhiteTurn = !isWhiteTurn;}
					game.getChessBoard().disp = false;
					return;
				}
			}
		} else {
			if (moveToMake.charAt(3) == 'g' && game.canCastle(isWhiteTurn, moveToMake.charAt(3) == 'g')) {
				if (moveToMake.substring(3).equals("g8") && game.getChessBoard().getPiece(moveToMake.substring(0, 2)).getType().equals("king")) {
					game.castle(false, true);
					if (!game.getChessBoard().disp) {isWhiteTurn = !isWhiteTurn;}
					game.getChessBoard().disp = false;
					return;
				}
			} else if (moveToMake.charAt(3) == 'c' && game.canCastle(isWhiteTurn, moveToMake.charAt(3) != 'c')) {
				if (moveToMake.substring(3).equals("c8") && game.getChessBoard().getPiece(moveToMake.substring(0, 2)).getType().equals("king")) {
					game.castle(false, false);
					if (!game.getChessBoard().disp) {isWhiteTurn = !isWhiteTurn;}
					game.getChessBoard().disp = false;
					return;
				}
			}
		}
		
		int offset=1;
		if (isWhiteTurn) {offset*=-1;}
		
		if (game.canEnPassant(moveToMake.substring(0,2), moveToMake.substring(3,4)+(char)(moveToMake.charAt(4)+offset))) {
			game.enPassant(moveToMake.substring(0,2), moveToMake.substring(3,4)+(char)(moveToMake.charAt(4)+offset));
		} else {game.makeMove(moveToMake, false);}
		
		if (!game.getChessBoard().disp) {isWhiteTurn = !isWhiteTurn;}
		game.getChessBoard().disp = false;
	}
}