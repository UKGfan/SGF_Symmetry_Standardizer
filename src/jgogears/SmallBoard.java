package jgogears;

import java.util.BitSet;

/**
 * A bit-wise board representation using a naive bit ordering.
 * 
 * @author syeates
 */
public class SmallBoard extends BoardI {

	/** How many bits we're allocating per square on the board. */
	final static short BITS_PER_VERTEX = 4;

	/** The underlying BitSet. */
	private final BitSet bits = new BitSet();

	/**
	 * Constructor for default board size.
	 */

	public SmallBoard() {
		// nothing
	}

	/**
	 * Constructor for default board size.
	 * 
	 * @param zobrist
	 *            true if we're using zobrist hashing
	 */

	public SmallBoard(boolean zobrist) {
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
	public SmallBoard(SmallBoard board, Move move) {
		this.size = board.getSize();
		this.copydata(board, move);
	}

	/**
	 * Constructor for a particular size board.
	 * 
	 * @param size
	 *            size of the board
	 */

	public SmallBoard(int size) {
		this.size = (short) size;
	}

	/**
	 * constructor of specially sized boards.
	 * 
	 * @param size
	 *            the size of the board
	 * @param rule
	 *            the ruleset to use
	 */
	public SmallBoard(int size, RuleSet rule) {
		this.size = (short) size;
		this.ruleSet = rule;
	}

	/**
	 * Default constructor.
	 * 
	 * @param rule
	 *            the rule
	 */
	public SmallBoard(RuleSet rule) {
		this.ruleSet = rule;
	}

	/**
	 * Constructor for a particular size board.
	 * 
	 * @param size
	 *            size of the board
	 */

	public SmallBoard(short size) {
		this.size = size;
	}

	/**
	 * constructor of specially sized boards.
	 * 
	 * @param size
	 *            the size of the board
	 * @param rule
	 *            the ruleset to use
	 */
	public SmallBoard(short size, RuleSet rule) {
		this.size = size;
		this.ruleSet = rule;
	}

	/*
	 * @see jgogears.BoardInterface#
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.BoardI#getColour(int, int)
	 */
	@Override
	public short getColour(int row, int column) {
		if (row < 0 || column < 0 || row >= this.size || column >= this.size)
			return VERTEX_OFF_BOARD;

		int offset = row * BITS_PER_VERTEX * this.size + column
				* BITS_PER_VERTEX;
		if (this.bits.get(offset))
			if (this.bits.get(offset + 1))
				return VERTEX_WHITE;
			else
				return VERTEX_BLACK;
		else if (this.bits.get(offset + 1))
			return VERTEX_KO;
		else
			return VERTEX_EMPTY;

	}

	/**
	 * create a new board based on the current board plus a move.
	 * 
	 * @param move
	 *            the move
	 * @return the new board
	 */
	@Override
	public final SmallBoard newBoard(Move move) {
		return new SmallBoard(this, move);
	}

	/*
	 * @see jgogears.BoardInterface#
	 */
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
		int offset = row * BITS_PER_VERTEX * this.size + column
				* BITS_PER_VERTEX;

		switch (colour) {
		case VERTEX_EMPTY:
			this.bits.set(offset, false);
			this.bits.set(offset + 1, false);
			break;
		case VERTEX_KO:
			this.bits.set(offset, false);
			this.bits.set(offset + 1, true);
			break;
		case VERTEX_BLACK:
			this.bits.set(offset, true);
			this.bits.set(offset + 1, false);
			break;
		case VERTEX_WHITE:
			this.bits.set(offset, true);
			this.bits.set(offset + 1, true);
			break;
		default:
			throw new Error();
		}

	}

}
