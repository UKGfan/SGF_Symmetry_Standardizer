package jgogears;

import java.util.BitSet;

// TODO: Auto-generated Javadoc
/**
 * A better bitset-based board implementation.
 * 
 * @author syeates
 */
public class SmallerBoard extends BoardI {

	/** the bit number for empty. */
	final static short OFFSET_EMPTY = 0;

	/** the bit number for colour / ko. */
	final static short OFFSET_COLOUR = 1;

	/** verbose debugging info. */
	private final boolean DEBUG = false;

	/** the underlying bitset holding the data. */
	private final BitSet bits = new BitSet();

	/**
	 * Create a new board.
	 */
	public SmallerBoard() {
		// nothing
	}

	/**
	 * Create a new board.
	 * 
	 * @param zobrist
	 *            true if using zorbist hashing
	 */
	public SmallerBoard(boolean zobrist) {
		super(zobrist);
	}

	/**
	 * create a new board based on the current board plus a move.
	 * 
	 * @param board
	 *            the move
	 * @param move
	 *            the move
	 */
	public SmallerBoard(SmallerBoard board, Move move) {
		this.size = board.getSize();
		this.copydata(board, move);
	}

	/**
	 * Create a new board.
	 * 
	 * @param size
	 *            the size of the board
	 */
	public SmallerBoard(int size) {
		this.size = (short) size;
	}

	/**
	 * Create a new board.
	 * 
	 * @param size
	 *            the size of the board
	 * @param rule
	 *            the ruleset to use
	 */
	public SmallerBoard(int size, RuleSet rule) {
		this.size = (short) size;
		this.ruleSet = rule;
	}

	/**
	 * Create a new board.
	 * 
	 * @param rule
	 *            the ruleset to use
	 */
	public SmallerBoard(RuleSet rule) {
		this.ruleSet = rule;
	}

	/**
	 * Create a new board.
	 * 
	 * @param size
	 *            the size of the board
	 */

	public SmallerBoard(short size) {
		this.size = size;
	}

	/**
	 * Create a new board.
	 * 
	 * @param zobrist
	 *            are we using zobrist hashes?
	 * @param size
	 *            the board size
	 */
	public SmallerBoard(short size, boolean zobrist) {
		super(zobrist);
		this.size = size;
	}

	/**
	 * Create a new board.
	 * 
	 * @param size
	 *            the size of the board
	 * @param rule
	 *            the ruleset to use
	 */
	public SmallerBoard(short size, RuleSet rule) {
		this.size = size;
		this.ruleSet = rule;
	}

	/**
	 * Create a new board.
	 * 
	 * @param size
	 *            the size of the board
	 * @param zobrist
	 *            are we using zobrist hashes?
	 * @param rule
	 *            the ruleset in use
	 */
	public SmallerBoard(short size, RuleSet rule, boolean zobrist) {
		super(zobrist);
		this.size = size;
		this.ruleSet = rule;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.BoardI#getColour(int, int)
	 */
	@Override
	public short getColour(int row, int column) {
		if (row < 0 || row >= this.size)
			return VERTEX_OFF_BOARD;
		if (column < 0 || column >= this.size)
			return VERTEX_OFF_BOARD;

		boolean empty = this.bits.get(this.getEmptyOffSet(row, column));
		boolean colour = this.bits.get(this.getColourOffSet(row, column));

		if (empty)
			if (colour)
				return VERTEX_WHITE;
			else
				return VERTEX_BLACK;
		else if (colour)
			return VERTEX_KO;
		else
			return VERTEX_EMPTY;
	}

	/**
	 * Gets the colour off set.
	 * 
	 * @param row
	 *            the row
	 * @param column
	 *            the column
	 * @return the colour offset
	 */
	int getColourOffSet(int row, int column) {
		return OFFSET_COLOUR * this.size * this.size + row * this.size + column;
	}

	/**
	 * Gets the empty off set.
	 * 
	 * @param row
	 *            the row
	 * @param column
	 *            the column
	 * @return the empty offset
	 */
	int getEmptyOffSet(int row, int column) {
		return OFFSET_EMPTY * this.size * this.size + row * this.size + column;
	}

	/**
	 * create a new board based on the current board plus a move.
	 * 
	 * @param move
	 *            the move
	 * @return the new board
	 */
	@Override
	public final SmallerBoard newBoard(Move move) {
		return new SmallerBoard(this, move);
	}

	/**
	 * Sets the colour.
	 * 
	 * @param row
	 *            the row
	 * @param column
	 *            the column
	 * @param colour
	 *            the colour
	 */
	@Override
	protected void setColour(int row, int column, int colour) {
		if (row < 0 || column < 0 || row >= this.size || column >= this.size) {
			if (this.DEBUG)
				System.err.println("attempt to set a colour off-board");
			throw new Error();
		}
		int emptyB = this.getEmptyOffSet(row, column);
		int colourB = this.getColourOffSet(row, column);

		switch (colour) {
		case VERTEX_EMPTY:
			this.bits.set(emptyB, false);
			this.bits.set(colourB, false);
			break;
		case VERTEX_KO:
			this.bits.set(emptyB, false);
			this.bits.set(colourB, true);
			break;
		case VERTEX_BLACK:
			this.bits.set(emptyB, true);
			this.bits.set(colourB, false);
			break;
		case VERTEX_WHITE:
			this.bits.set(emptyB, true);
			this.bits.set(colourB, true);
			break;
		default:
			throw new Error();
		}

	}

}
