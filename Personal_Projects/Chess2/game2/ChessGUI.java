package game2;

import javax.swing.JOptionPane;
import processing.core.*;

@SuppressWarnings("serial")
//TODO: New click-based GUI
//TODO: Chess (AI?) robot
public class ChessGUI extends PApplet{
	private ChessGame game;
	private String move;
	private boolean isWhiteTurn;
	private boolean gameOver;
	
	public void setup() {
		size(632,632); //632 = 8*79
		game = new ChessGame(frame);
		isWhiteTurn = true;
		gameOver = false;
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
		for (ChessPiece piece : game.getChessBoard()) {drawPieces(drawer,piece);}
		
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
		if (gameOver) {return;}
		
		move += (char) keyCode;
		
		if (move.length() == 5) {
			String moveToMake = move.toLowerCase();
			
			ChessPiece piece = game.getPiece(moveToMake.substring(0, 2));
			if (piece != null && piece.getIsWhite() != isWhiteTurn) {
				JOptionPane.showMessageDialog(frame, "It is not your turn to move","",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			game.makeMove(moveToMake);
			if (!game.disp) {isWhiteTurn = !isWhiteTurn;}
			game.disp = false;
			
			move = "";
			
			int cond = game.endCond();
			boolean end = Math.abs(cond) == 2;
			String state = cond<0?"White":"Black";
			state += " is in check";
			state += end?"mate!":"!";
			
			if (cond == 0) {
				JOptionPane.showMessageDialog(frame, "It's a stalemate!","",JOptionPane.ERROR_MESSAGE);
				gameOver = true;
			} else if (cond < 3) {
				JOptionPane.showMessageDialog(frame, state,"",JOptionPane.ERROR_MESSAGE);
				gameOver = end;
			}
		}
	}
}