package jgogears;

import java.util.*;

/**
 * Class representing a Zobrist hash, a binary hash of the current board state.
 * TODO add proper references
 * 
 * @author Stuart
 */

public class Zobrist extends BitSet {

	/** The Constant MAX_BOARD_SIZE. */
	public static final short MAX_BOARD_SIZE = 25;

	/** The Constant MAX_COLOUR. */
	public static final short MAX_COLOUR = 4;

	/** The Constant ZOBRIST_SIZE. */
	public static final short ZOBRIST_SIZE = 96;

	// initialisers ...
	/** The static grid of all random values */
	static BitSet[][][] grid = null;

	/**
	 * Initialises the static grid of random values
	 */
	static private void init() {
		if (grid == null) {
			grid = new BitSet[MAX_BOARD_SIZE][MAX_BOARD_SIZE][MAX_COLOUR];

			for (int i = 0; i < MAX_BOARD_SIZE; i++)
				for (int j = 0; j < MAX_BOARD_SIZE; j++)
					for (int k = 0; k < MAX_COLOUR; k++) {
						grid[i][j][k] = new BitSet();
						// System.err.println("" + i + " " + j + " " + k);
						for (int l = 0; l < ZOBRIST_SIZE; l++) {
							grid[i][j][k].set(l, Random.nextBoolean());
						}
					}
		}
	}

	/**
	 * Default constructor, represents an empty board.
	 */
	public Zobrist() {
		init();
	}

	/**
	 * Constructor building a Zobrist from an existing Zobrist and a move.
	 * 
	 * @param old
	 *            the old
	 * @param row
	 *            the row
	 * @param column
	 *            the column
	 * @param colour
	 *            the colour
	 */
	public Zobrist(Zobrist old, int row, int column, int colour) {
		init();
		if (row >= MAX_BOARD_SIZE || row < 0)
			throw new Error("" + row);
		if (column >= MAX_BOARD_SIZE || column < 0)
			throw new Error("" + column);
		if (colour >= BoardI.VERTEX_MAX || colour < BoardI.VERTEX_MIN)
			throw new Error("" + colour);
		this.clear();
		this.xor(old);
		this.xor(grid[row][column][colour]);
	}

	/**
	 * compare this zobrist to another object, potentially another zobrist
	 * 
	 * @param o
	 *            the other Object
	 * @return 0 if equal, otherwise 1/-1
	 */
	public int compareTo(Object o) {
		if (o.getClass() != this.getClass())
			return this.hashCode() > o.hashCode() ? 1 : -1;
		Zobrist other = (Zobrist) o;
		if (other == null)
			throw new Error();
		for (int i = 0; i < this.size(); i++)
			if (this.get(i) != other.get(i))
				return this.get(i) == true ? 1 : -1;
		return 0;
	}

}
