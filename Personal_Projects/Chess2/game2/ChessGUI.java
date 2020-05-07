package game2;

import javax.swing.JOptionPane;

import processing.core.*;

@SuppressWarnings("serial")
//TODO: Create Chess (AI?) robot
public class ChessGUI extends PApplet{
	private ChessGame game;
	private String move;
	private boolean mouselr = true;
	private boolean tips = false;
	private ChessBot aibot;
	
	public void setup() {
		size(632,632); //632 = 8*79
		game = new ChessGame(frame);
		move = "";
		aibot = null;
		String[] options = {"1-Player","2-Player"};
		int p = JOptionPane.showOptionDialog(frame,"","Let's Play A Game",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE,null,options,null);
		if (p == 0) {
			String[] cols = {"White","Black"};
			int c = JOptionPane.showOptionDialog(frame,"","Pick a Color",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE,null,cols,null);
			aibot = new ChessBot(c == 1);
			JOptionPane.showMessageDialog(frame, "Beep Boop. I'm Chess Bot! Let's have some fun!","",JOptionPane.PLAIN_MESSAGE);
		}
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
		
		if (tips) {
			ChessPiece piece = game.getPiece(move);
			if (piece != null && game.getTurn() == piece.getIsWhite()) {
				String[] locs = piece.getMoves().split(",");
				for (String loc : locs) {
					if (loc.length()<2) {continue;}
					int x = (loc.charAt(0)-97)*79+79/2;//a --> 0, b --> 1, etc.
					int y = (56-loc.charAt(1))*79+79/2;//8 --> 0, 7 --> 1, etc.
					fill(color(255,0,0));
					ellipse(x,y,79/4,79/4);
				}
				
				int c = piece.getIsWhite()?0:255;
				int x = (piece.getLocation().charAt(0)-97)*79+79/2;
				int y = (56-piece.getLocation().charAt(1))*79+79/2;
				fill(color(c,c,c));
				ellipse(x,y,79/4,79/4);
			}
		}
		
		if (aibot != null && aibot.getColor() == game.getTurn() && !game.isOver()) {
			game.makeMove(aibot.chooseMove(game.getChessBoard()), false);
		}
		
		/*textSize(15); -- lettering of squares
		fill(0,0,255);
		String[] let = {"a","b","c","d","e","f","g","h"};
		for (int i=0; i<8; i++) {
			for (int j=1; j <= 8; j++) {
				text(let[i]+j,i*79,(8-j)*79+15);
			}
		}*/
	}
	
	/**
	 * Helper function for the drawing of the pieces
	 * @param d the PieceDraw object in use
	 * @param p the piece to be drawn
	 */
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
	
	public void mousePressed() {
		tips = false;
		mouselr = mouseButton == LEFT;
		String[] let = {"a","b","c","d","e","f","g","h"};
		move = let[mouseX/79] + (8 - mouseY/79);
	}
	
	public void mouseReleased() {
		String[] let = {"a","b","c","d","e","f","g","h"};
		String pos = let[mouseX/79] + (8 - mouseY/79);
		
		if (aibot != null && aibot.getColor() != game.getTurn()) {
			if (mouselr) {game.makeMove(move + " " + pos, true);}
			else {tips = true;}
		}
	}
}