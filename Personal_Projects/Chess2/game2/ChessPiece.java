package game2;

public class ChessPiece {
	private String type;
	private boolean isWhite;
	private String loc;
	
	/**
	 * Constructs a new Chess Piece based on color, type, and location
	 * @param kind the type of the piece
	 * @param isW true if the piece is white, false otherwise
	 * @param location the location on the board of the piece
	 */
	public ChessPiece (String kind, boolean isW, String location) {
		type = kind;
		isWhite = isW;
		loc = location;
	}
	
	/**
	 * Changes the type of the piece to type other
	 * @param other the new type of the piece
	 */
	public void changeType(String other) {
		type = other;
	}
	
	/**
	 * Returns the type of the piece
	 * @return the type of the piece
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Returns true if the piece is white, false otherwise
	 * @return true if the piece is white, false otherwise
	 */
	public boolean getIsWhite() {
		return isWhite;
	}
	
	/**
	 * Changes the location of the piece to other
	 * @param other the new location of the piece
	 */
	public void changeLocation(String other) {
		loc = other;
	}
	
	/**
	 * Returns the location of the piece
	 * @return the location of the piece
	 */
	public String getLocation() {
		return loc;
	}
	
	public String toString() {
		return type + "," + isWhite + "," + loc;
	}
}