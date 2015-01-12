package jgogears.engine;

import java.util.*;

import jgogears.BoardI;

/**
 * The Class Straight Vertex Lineariser.
 */
final public class StraightVertexLineariser implements Iterator<Short> {

	/** the SIZE of the board we've cached */
	static final short BOARD_SIZE = 19;

	/** the SIZE of the board plus the surrounding edge */
	static final short SIZE = BOARD_SIZE + 2;

	/** the maximum length of the sequence */
	static final short SEQUENCE_SIZE = SIZE * SIZE * 2 * 2;

	/** the static master sequence */
	static short[][][] master = null;

	/** The offset. */
	protected int offset = 0;

	/** The board. */
	protected BoardI board = null;

	/** The row. */
	short row = -2;

	/** The column. */
	short column = -2;

	/** The sym. */
	short sym = -2;

	/** Have the colours been inverted? */
	boolean invert = false;

	/** are we being verbose */
	final static boolean PROGRESS = true;

	/**
	 * static initialiser. inits the master array.
	 * 
	 * @return true, if successful
	 */
	static boolean init() {
		if (master == null) {
			master = new short[8][2][SEQUENCE_SIZE];
			fillMasterL((short) 0, 0, true);
			fillMasterL((short) 1, 1, true);
			fillMasterL((short) 2, 2, true);
			fillMasterL((short) 3, 3, true);
			fillMasterR((short) 4, 0, false);
			fillMasterR((short) 5, 1, false);
			fillMasterR((short) 6, 2, false);
			fillMasterR((short) 7, 3, false);
		}
		return true;
	}

	static final boolean isAreadyThere(int cnt, int row, int column) {
		for (int l = 0; l < SEQUENCE_SIZE; l++)
			if (master[cnt][0][l] == row && master[cnt][1][l] == column)
				return true;
		return false;
	}

	static final void fillMasterL(short cnt, int state, boolean reverse) {
		for (int n = 0; n < 2; n++)
			for (int l = 0; l < SEQUENCE_SIZE; l++)
				master[cnt][n][l] = -5;

		short row = 0;
		short column = 0;
		short counter = 0;
		while (counter < SEQUENCE_SIZE) {
			master[cnt][0][counter] = row;
			master[cnt][1][counter] = column;
			switch (state) {
			case 0:
				if (isAreadyThere(cnt, row, column - 1)) {
					row++;
				} else {
					column--;
					state = 1;
				}
				break;
			case 1:
				if (isAreadyThere(cnt, row - 1, column)) {
					column--;
				} else {
					row--;
					state = 2;
				}
				break;
			case 2:
				if (isAreadyThere(cnt, row, column + 1)) {
					row--;
				} else {
					column++;
					state = 3;
				}
				break;
			case 3:
				if (isAreadyThere(cnt, row + 1, column)) {
					column++;
				} else {
					row++;
					state = 0;
				}
				break;
			default:
				throw new Error("bad state " + state);
			}
			counter++;

		}
	}

	static final void fillMasterR(short cnt, int state, boolean reverse) {
		for (int n = 0; n < 2; n++)
			for (int l = 0; l < SEQUENCE_SIZE; l++)
				master[cnt][n][l] = -5;

		short row = 0;
		short column = 0;
		short counter = 0;
		while (counter < SEQUENCE_SIZE) {
			master[cnt][0][counter] = row;
			master[cnt][1][counter] = column;
			switch (state) {
			case 0:
				if (isAreadyThere(cnt, row, column - 1)) {
					row--;
				} else {
					column--;
					state = 1;
				}
				break;
			case 1:
				if (isAreadyThere(cnt, row + 1, column)) {
					column--;
				} else {
					row++;
					state = 2;
				}
				break;
			case 2:
				if (isAreadyThere(cnt, row, column + 1)) {
					row++;
				} else {
					column++;
					state = 3;
				}
				break;
			case 3:
				if (isAreadyThere(cnt, row - 1, column)) {
					column++;
				} else {
					row--;
					state = 0;
				}
				break;
			default:
				throw new Error("bad state " + state);
			}
			counter++;

		}
	}

	/**
	 * Instantiates a new vertex lineariser.
	 * 
	 * @param board
	 *            the board
	 * @param row
	 *            the row
	 * @param column
	 *            the column
	 * @param sym
	 *            the sym
	 * @param invert
	 *            are we inverting the colour?
	 */
	public StraightVertexLineariser(BoardI board, short row, short column,
			short sym, boolean invert) {
		this.board = board;
		this.row = row;
		this.column = column;
		this.sym = sym;
		this.invert = invert;

		init();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#hasNext()
	 */
	public boolean hasNext() {
		return this.offset < SEQUENCE_SIZE - 1;
	}

	/**
	 * Invert a colour. Used when white is to play
	 * 
	 * @param colour
	 * @return the inverted colour
	 */
	public Short invert(Short colour) {
		switch (colour.shortValue()) {
		case BoardI.VERTEX_BLACK:
			return BoardI.VERTEX_WHITE;
		case BoardI.VERTEX_WHITE:
			return BoardI.VERTEX_BLACK;
		default:
			return colour;

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#next()
	 */
	public Short next() {
		if (!this.hasNext())
			throw new NoSuchElementException();
		// System.err.println("next() " + sym + " " + row + " " + column + " "
		// + offset);
		while (board.isWayOffBoard(master[this.sym][0][this.offset] + this.row,
				master[this.sym][1][this.offset] + this.column) && hasNext())
			offset++;

		short c = this.board.getColour(master[this.sym][0][this.offset]
				+ this.row, master[this.sym][1][this.offset] + this.column);
		this.offset++;
		if (this.invert)
			return c;
		else
			return this.invert(c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#remove()
	 */
	public void remove() {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		while (this.hasNext())
			buf.append(this.next()).append(' ');
		;
		return buf.toString();
	}

}
