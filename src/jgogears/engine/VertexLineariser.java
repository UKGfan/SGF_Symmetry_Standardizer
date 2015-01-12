package jgogears.engine;

import java.util.*;

import jgogears.BoardI;

// TODO: Auto-generated Javadoc
/**
 * The Class VertexLineariser.
 */
final public class VertexLineariser implements Iterator<Short> {
	// Table of sequences
	/** The cache. */
	static private short[][][][][] cache = null;

	// the SIZE of the board we've cached
	/** The Constant BOARD_SIZE. */
	static private final short BOARD_SIZE = 19;

	// the SIZE of the board we've cached
	/** The Constant SIZE. */
	static private final short SIZE = 21;

	/** OFFSETs need to be very small and prime relative to each other and 19 */
	static double SMALL_OFFSET = 0.000000100;
	/** OFFSETs need to be very small and prime relative to each other and 19 */
	static double LARGE_OFFSET = 0.000000230;

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
	final static boolean PROGRESS = false;

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
	public VertexLineariser(BoardI board, short row, short column, short sym,
			boolean invert) {
		this.board = board;
		this.row = (short) (row + 1);
		this.column = (short) (column + 1);
		this.sym = sym;
		this.invert = invert;

		if (this.board.getSize() != BOARD_SIZE)
			throw new IllegalArgumentException("only boards of BOARD_SIZE "
					+ BOARD_SIZE + " please");
		if (cache == null)
			init();
		this.check();
	}

	/**
	 * Check.
	 * 
	 * @return true, if successful
	 */
	private boolean check() {
		if (this.board == null)
			throw new Error();
		if (this.row == -2)
			throw new Error();
		if (this.column == -2)
			throw new Error();
		if (this.offset == -2)
			throw new Error();

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#hasNext()
	 */
	public boolean hasNext() {
		return this.offset < SIZE * SIZE;
	}

	/**
	 * Inits the.
	 * 
	 * @return true, if successful
	 */
	static boolean init() {
		if (cache == null) {
			cache = new short[2][8][SIZE][SIZE][SIZE * SIZE];
			for (int n = 0; n < 2; n++)
				for (int m = 0; m < 8; m++)
					for (int i = 0; i < SIZE; i++)
						for (int j = 0; j < SIZE; j++)
							for (int l = 0; l < SIZE * SIZE; l++)
								cache[n][m][i][j][l] = -5;
		} else if (cache.length != SIZE) {
			throw new Error(
					"VertexLineariser Error! only one boardsize allowed");
		}

		for (int row = 0; row < SIZE; row++)
			for (int column = 0; column < SIZE; column++) {
				for (int sym = 0; sym < 8; sym++) {
					TreeMap<Double, ArrayList<Short>> values = new TreeMap<Double, ArrayList<Short>>();
					for (short i = 0; i < SIZE; i++)
						for (short j = 0; j < SIZE; j++) {
							double row_offset, column_offset;
							// transform
							switch (sym) {
							case 0:
								row_offset = SMALL_OFFSET;
								column_offset = LARGE_OFFSET;
								break;
							case 1:
								row_offset = -SMALL_OFFSET;
								column_offset = LARGE_OFFSET;
								break;
							case 2:
								row_offset = SMALL_OFFSET;
								column_offset = -LARGE_OFFSET;
								break;
							case 3:
								row_offset = -SMALL_OFFSET;
								column_offset = -LARGE_OFFSET;
								break;
							case 4:
								row_offset = LARGE_OFFSET;
								column_offset = SMALL_OFFSET;
								break;
							case 5:
								row_offset = -LARGE_OFFSET;
								column_offset = SMALL_OFFSET;
								break;
							case 6:
								row_offset = LARGE_OFFSET;
								column_offset = -SMALL_OFFSET;
								break;
							case 7:
								row_offset = -LARGE_OFFSET;
								column_offset = -SMALL_OFFSET;
								break;
							default:
								throw new Error(
										"VertexLineariser::init  bad symmetry: "
												+ sym);
							}
							double d = Math.pow(row - i + row_offset, 2)
									+ Math.pow(column - j + column_offset, 2);

							ArrayList<Short> array = new ArrayList<Short>(2);
							array.add(new Short(i));
							array.add(new Short(j));

							// System.out.println(" (" + i + "," + j + "," + a +
							// "," + b + "), ");
							values.put(new Double(d), array);
						}
					// System.out.println();
					Short[] array = new Short[] { null, null };
					// System.out.println(values.size() + ", " +SIZE*SIZE+ ", "
					// +19*19);
					for (int i = 0; i < SIZE * SIZE; i++) {
						if (values.isEmpty())
							throw new Error();
						Double key = values.firstKey();
						Short[] thisone = values.get(key).toArray(array);
						values.remove(key);
						// rowsequence[i] = thisone[0];
						// columnsequence[i] = thisone[1];
						cache[0][sym][row][column][i] = thisone[0];
						cache[1][sym][row][column][i] = thisone[1];
						// System.out.println(" [" +
						// cache[0][sym][row][column][i] + "," +
						// cache[1][sym][row][column][i] + "], ");
					}
					if (values.size() > 0)
						throw new Error("values should be empty but was "
								+ values.size());
					if (PROGRESS)
						System.err.print(":");
				}
			}
		// System.out.println();
		if (PROGRESS)
			System.err.println();

		return true;
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
		short c = this.board.getColour(
				cache[0][this.sym][this.row][this.column][this.offset] - 1,
				cache[1][this.sym][this.row][this.column][this.offset] - 1);
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
