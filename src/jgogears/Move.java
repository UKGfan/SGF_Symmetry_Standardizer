package jgogears;

// TODO: Auto-generated Javadoc
/**
 * A move. A colour and either a vertex, a pass or a resign. Also used for
 * handicap stones.
 * 
 * @author syeates
 */

public final class Move {

	/**
	 * Create a handicap stone from a string.
	 * 
	 * @param s
	 *            the s
	 * @return the stone as a move
	 */
	public static Move createHandicapStone(String s) {
		Vertex v = new Vertex(s);
		Move result = new Move(v.getRow(), v.getColumn(), BoardI.VERTEX_BLACK);
		return result;
	}

	/** The row. */
	private short row = Short.MIN_VALUE;

	/** The column. */
	private short column = Short.MIN_VALUE;

	/** The pass. */
	private boolean pass = false;

	/** The resign. */
	private boolean resign = false;

	/** The colour. */
	private short colour = BoardI.VERTEX_KO;

	/**
	 * create an empty GoMove.
	 */
	public Move() {
		// nothing
	}

	/**
	 * create a GoMove.
	 * 
	 * @param row
	 *            the row of the move
	 * @param column
	 *            the column of the move
	 * @param colour
	 *            the colour of the move
	 */
	public Move(int row, int column, int colour) {
		this.row = (short) row;
		this.column = (short) column;
		this.colour = (short) colour;
	}

	/**
	 * create a GoMove.
	 * 
	 * @param row
	 *            the row of the move
	 * @param column
	 *            the column of the move
	 * @param colour
	 *            the colour of the move
	 */
	public Move(short row, short column, int colour) {
		this.row = row;
		this.column = column;
		this.colour = (short) colour;
	}

	/**
	 * create a GoMove.
	 * 
	 * @param row
	 *            the row of the move
	 * @param column
	 *            the column of the move
	 * @param colour
	 *            the colour of the move
	 */
	public Move(short row, short column, short colour) {
		this.row = row;
		this.column = column;
		this.colour = colour;
	}

	/**
	 * create a GoMove from a GTP move string.
	 * 
	 * @param move
	 *            the string to parse
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 */
	public Move(String move) throws IllegalArgumentException {
		// System.err.println("GoMove::GoMove(\"" + move + "\"");

		move = move.toLowerCase();

		int space = move.indexOf(" ");
		if (space < 0)
			throw new IllegalArgumentException("Bad argument to GoMove(" + move
					+ ")");
		String colourString = move.substring(0, space);
		this.colour = BoardI.parseColour(colourString);
		String vertexString = move.substring(space + 1, move.length());
		if (vertexString.contains("resign")) {
			this.resign = true;
		} else if (vertexString.contains("pass")) {
			this.pass = true;
		} else {
			Vertex v = new Vertex(vertexString);
			this.setRow(v.getRow());
			this.setColumn(v.getColumn());
		}
	}

	/**
	 * create a GoMove from a move string minus the mvoe colour
	 * 
	 * @param move
	 *            the string to parse
	 * @param colour
	 *            the colour of the move
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 */
	public Move(String move, short colour) throws IllegalArgumentException {
		// System.err.println("GoMove::GoMove(\"" + move + "\"");

		move = move.toLowerCase();

		this.colour = colour;
		if (move.contains("resign")) {
			this.resign = true;
		} else if (move.contains("pass")) {
			this.pass = true;
		} else {
			Vertex v = new Vertex(move);
			this.setRow(v.getRow());
			this.setColumn(v.getColumn());
		}
	}

	/**
	 * return the colour of this move.
	 * 
	 * @return the colour
	 */
	public short getColour() {
		return this.colour;
	}

	/**
	 * get the row of this column.
	 * 
	 * @return the column
	 * @throws Error
	 *             if this is a pass move
	 */
	public short getColumn() {
		if (this.resign)
			throw new Error();
		if (this.pass)
			throw new Error();
		return this.column;
	}

	/**
	 * Gets the pass.
	 * 
	 * @return true if this move is a pass
	 */
	public boolean getPass() {
		return this.pass;
	}

	/**
	 * return true if this is a plying move (not a pass or resign).
	 * 
	 * @return whether this is a play
	 */
	public boolean getPlay() {
		if (this.pass)
			return false;
		if (this.resign)
			return false;
		return true;
	}

	/**
	 * Gets the resign.
	 * 
	 * @return true if this is a resignation
	 */
	public boolean getResign() {
		return this.resign;
	}

	/**
	 * get the row of this column.
	 * 
	 * @return the column
	 * @throws Error
	 *             if this is a pass move
	 */
	public short getRow() {
		if (this.resign)
			throw new Error();
		if (this.pass)
			throw new Error();
		return this.row;
	}

	/**
	 * Number to character.
	 * 
	 * @param s
	 *            an alphabetic label from a traditional goban
	 * @return the corresponding (zero-indexed) integer
	 */
	protected String numberToCharacter(short s) {
		String rowS = null;
		switch (s) {
		case 0:
			rowS = "a";
			break;
		case 1:
			rowS = "b";
			break;
		case 2:
			rowS = "c";
			break;
		case 3:
			rowS = "d";
			break;
		case 4:
			rowS = "e";
			break;
		case 5:
			rowS = "f";
			break;
		case 6:
			rowS = "g";
			break;
		case 7:
			rowS = "h";
			break;// no i
		case 8:
			rowS = "j";
			break;
		case 9:
			rowS = "k";
			break;
		case 10:
			rowS = "l";
			break;
		case 11:
			rowS = "m";
			break;
		case 12:
			rowS = "n";
			break;
		case 13:
			rowS = "o";
			break;
		case 14:
			rowS = "p";
			break;
		case 15:
			rowS = "q";
			break;
		case 16:
			rowS = "r";
			break;
		case 17:
			rowS = "s";
			break;
		case 18:
			rowS = "t";
			break;
		case 19:
			rowS = "u";
			break;
		case 20:
			rowS = "v";
			break;
		case 21:
			rowS = "w";
			break;
		case 22:
			rowS = "x";
			break;
		case 23:
			rowS = "y";
			break;
		case 24:
			rowS = "z";
			break;
		default:
			throw new java.lang.InternalError("bad row=" + s);
		}
		return rowS;
	}

	/**
	 * Sets the colour.
	 * 
	 * @param colour
	 *            the colour to set
	 * @return the move
	 */
	public Move setColour(short colour) {
		this.colour = colour;
		return this;
	}

	/**
	 * Sets the column.
	 * 
	 * @param column
	 *            the column to set
	 * @return the move
	 */
	public Move setColumn(short column) {
		this.column = column;
		return this;
	}

	/**
	 * Sets the pass.
	 * 
	 * @param pass
	 *            the pass to set
	 * @return the move
	 */
	public Move setPass(boolean pass) {
		this.pass = pass;
		return this;
	}

	/**
	 * Sets the resign.
	 * 
	 * @param resign
	 *            the resign to set
	 * @return the move
	 */
	public Move setResign(boolean resign) {
		this.resign = resign;
		return this;
	}

	/**
	 * Sets the row.
	 * 
	 * @param row
	 *            the row to set
	 * @return the move
	 */
	public Move setRow(short row) {
		this.row = row;
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.toStringGTP();
	}

	/**
	 * To string gtp.
	 * 
	 * @return the string
	 */
	public String toStringGTP() {

		// find the colour of the move
		String colourS = BoardI.colourString(this.colour);

		// calculate the position
		String vertexS = this.toVertexString();

		return colourS + " " + vertexS;
	}

	/**
	 * To vertex string.
	 * 
	 * @return this vertex as a string
	 */
	public String toVertexString() {
		// catch the booleans
		if (this.pass) {
			return "pass";
		}
		if (this.resign) {
			return "resign";
		}
		return this.numberToCharacter(this.row) + ("" + (this.column + 1));
	}

}
