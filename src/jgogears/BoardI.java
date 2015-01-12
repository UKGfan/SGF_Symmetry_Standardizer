package jgogears;

import java.util.*;

/**
 * Abstract interface to a board. Knows about the size of the board, which
 * stones are where, and about colours. Knows nothing of the history of the
 * board or whose turn it is to play.
 * 
 * @author Stuart
 */
public abstract class BoardI {

	/** The default board size. */
	public static final short DEFAULT_BOARD_SIZE = 19;

	/** The MAXIMUM value for a vertex colour. */
	public static final short VERTEX_MAX = 4;

	/** A vertex in ko. */
	public static final short VERTEX_OFF_BOARD = 4;

	/** A vertex in ko. */
	public static final short VERTEX_KO = 3;

	/** A vertex with a black stone. */
	public static final short VERTEX_BLACK = 2;

	/** A vertex with a white stone. */
	public static final short VERTEX_WHITE = 1;

	/** A vertex without a stone (and not in KO. */
	public static final short VERTEX_EMPTY = 0;

	/** The MINIMUM value for a vertex colour. */
	public static final short VERTEX_MIN = 0;

	/** The Constant DEFAULT_ZOBRIST. */
	public final static boolean DEFAULT_ZOBRIST = true;

	/** Are we sanity checking moves? */
	public final static boolean SANITY_CHECK_MOVES = true;

	/**
	 * Colour string.
	 * 
	 * @param colour
	 *            the colour
	 * @return the colour as a string
	 */
	public static String colourString(int colour) {
		// find the colour of the move
		String colourS = "";
		switch (colour) {
		case BoardI.VERTEX_WHITE:
			colourS = "white";
			break;
		case BoardI.VERTEX_BLACK:
			colourS = "black";
			break;
		case BoardI.VERTEX_KO:
			colourS = "ko";
			break;
		case BoardI.VERTEX_EMPTY:
			colourS = "empty";
			break;
		case BoardI.VERTEX_OFF_BOARD:
			colourS = "off board";
			break;
		default:
			throw new java.lang.InternalError();
		}
		return colourS;
	}

	/**
	 * create an empty board of the default class
	 * 
	 * @return the new empty board
	 */
	public static BoardI newBoard() {
		return new Board(DEFAULT_BOARD_SIZE);
	}

	/**
	 * create an empty board of the default class
	 * 
	 * @param zobrist
	 *            are we using a zobrist hash?
	 * @return the new empty board
	 */
	public static BoardI newBoard(boolean zobrist) {
		return new Board(zobrist);
	}

	/**
	 * create an empty board of the default class
	 * 
	 * @param size
	 *            the size of the board
	 * @return the new empty board
	 */
	public static BoardI newBoard(int size) {
		return new Board((short) size);
	}

	/**
	 * create an empty board of the default class
	 * 
	 * @param size
	 *            the size of the board
	 * @param zobrist
	 *            are we using a zobrist hash?
	 * @return the new empty board
	 */
	public static BoardI newBoard(int size, boolean zobrist) {
		return new Board((short) size, zobrist);
	}

	/**
	 * create an empty board of the default class
	 * 
	 * @param size
	 *            the size of the board
	 * @param rule
	 *            the ruleset to use
	 * @return the new empty board
	 */
	public static BoardI newBoard(int size, RuleSet rule) {
		return new Board((short) size, rule);
	}

	/**
	 * create an empty board of the default class
	 * 
	 * @param size
	 *            the size of the board
	 * @param rule
	 *            the ruleset to use
	 * @param zobrist
	 *            are we using a zobrist hash?
	 * @return the new empty board
	 */
	public static BoardI newBoard(int size, RuleSet rule, boolean zobrist) {
		return new Board((short) size, rule, zobrist);
	}

	/**
	 * parse the colour of a move.
	 * 
	 * @param colourString
	 *            the colour string
	 * @return the colour as a short
	 */
	public static short parseColour(String colourString) {

		if (colourString.compareTo("w") == 0) {
			return BoardI.VERTEX_WHITE;
		} else if (colourString.compareTo("white") == 0) {
			return BoardI.VERTEX_WHITE;
		} else if (colourString.compareTo("b") == 0) {
			return BoardI.VERTEX_BLACK;
		} else if (colourString.compareTo("black") == 0) {
			return BoardI.VERTEX_BLACK;
		} else {
			throw new IllegalArgumentException("trying to parse (1) \""
					+ colourString + "\" as a colour");
		}
	}

	/** The zobrist. */
	protected Zobrist zobrist = null;

	/** the size of the board. */
	protected short size = 19;

	/** the ruleset. */
	protected RuleSet ruleSet = new NoKoRuleSet();

	/**
	 * Instantiates a new board
	 */
	public BoardI() {
		if (DEFAULT_ZOBRIST)
			this.zobrist = new Zobrist();
	}

	/**
	 * The Constructor.
	 * 
	 * @param zobrist
	 *            the zobrist
	 */
	public BoardI(boolean zobrist) {
		if (zobrist)
			this.zobrist = new Zobrist();
	}

	/**
	 * create a new board based on the current board plus a move.
	 * 
	 * @param move
	 *            the move
	 * @param old
	 *            the old board we're coping data from
	 */
	protected void copydata(BoardI old, Move move) {
		this.size = old.getSize();
		if (this.size < 3 || this.size > 25)
			throw new Error();

		this.zobrist = old.getZobrist();
		this.ruleSet = old.getRuleSet();

		for (short i = 0; i < this.size; i++)
			for (short j = 0; j < this.size; j++) {
				short colour = old.getColour(i, j);
				if (colour == VERTEX_KO)
					this.setColour(i, j, VERTEX_EMPTY);
				else
					this.setColour(i, j, colour);
			}
		if (move == null)
			return;
		if (move.getResign()) {
			return;
		} else if (move.getPass()) {
			// do nothing, since GoBoard doesn't know whose turn it is
		} else {
			// check the sanity of moves
			if (SANITY_CHECK_MOVES) {
				short oldColour = old
						.getColour(move.getRow(), move.getColumn());
				switch (oldColour) {
				case VERTEX_KO:
				case VERTEX_EMPTY:
					break;
				case VERTEX_WHITE:
				case VERTEX_BLACK:
					if (move.getColour() == VERTEX_KO
							|| move.getColour() == VERTEX_EMPTY) {
						break;
					} else {
						throw new Error(move + "\n " + old);
					}
				case VERTEX_OFF_BOARD:
					if (move.getColour() != VERTEX_OFF_BOARD) {
						throw new Error(move + "");
					} else {
						break;
					}
				default:
				}
			}
			// place the stone
			if (move.getColumn() >= this.size || move.getRow() >= this.size)
				throw new Error(move + "");
			this.setColour(move.getRow(), move.getColumn(), move.getColour());
			if (this.zobrist != null)
				this.setZobrist(new Zobrist(this.zobrist, move.getRow(), move
						.getColumn(), BoardI.VERTEX_EMPTY));

			// take the captures
			TreeSet<Vertex> captures = old.getRuleSet().captures(null, old,
					move);
			if (captures.size() > 0) {
				// System.err.println("captured" + captures);
				Iterator<Vertex> i = captures.iterator();
				while (i.hasNext()) {
					Vertex v = i.next();
					// System.err.println("captured" + v);
					this.setColour(v.getRow(), v.getColumn(),
							BoardI.VERTEX_EMPTY);

					if (this.zobrist != null)
						this.setZobrist(new Zobrist(this.getZobrist(), v
								.getRow(), v.getColumn(), BoardI.VERTEX_EMPTY));
				}
			}
			// mark the kos
			TreeSet<Vertex> kos = old.getRuleSet().leavesKo(null, old, move);
			if (kos.size() > 0) {
				// System.err.println("captured" + captures);
				Iterator<Vertex> i = kos.iterator();
				while (i.hasNext()) {
					Vertex v = i.next();
					// System.err.println("captured" + v);
					this.setColour(v.getRow(), v.getColumn(), BoardI.VERTEX_KO);

				}
			}
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		BoardI other = null;
		try {
			other = (BoardI) obj;
		} catch (Throwable t) {
			return super.equals(obj);
		}
		if (other == null)
			return false;
		if (this.size != other.size)
			return false;
		if (this.getZobrist() != null && other.getZobrist() != null)
			return this.getZobrist().equals(other.getZobrist());
		for (int i = 0; i < this.size; i++)
			for (int j = 0; j < this.size; j++)
				if (this.getColour(i, j) != other.getColour(i, j))
					return false;
		return true;
	}

	/**
	 * Get a collection of all the legal moves
	 * 
	 * @param rules
	 *            the ruleset in use
	 * @param colour
	 *            the colour of the move we want to play
	 * @return a collection of the moves
	 */
	public Collection<Move> getAllLegalMoves(RuleSet rules, short colour) {
		return rules.getAllLegalMoves(null, this, colour);
	}

	/**
	 * Get a collection of all the legal moves
	 * 
	 * @param rules
	 *            the ruleset in use
	 * @param colour
	 *            the colour of the move we want to play
	 * @return the moves
	 */

	public Collection<Vertex> getAllLegalVertexes(RuleSet rules, short colour) {
		return rules.getAllLegalVertexes(null, this, colour);
	}

	/**
	 * get the colour of a vertex.
	 * 
	 * @param row
	 *            the row
	 * @param column
	 *            the column
	 * @return the colour
	 */
	public abstract short getColour(int row, int column);

	/**
	 * get the colour of a vertex.
	 * 
	 * @param vertex
	 *            the vertex we're getting the colour of
	 * @return the colour
	 */
	public short getColour(Vertex vertex) {
		return this.getColour(vertex.getRow(), vertex.getColumn());
	}

	/**
	 * get the ruleSet
	 * 
	 * @return the ruleSet
	 */
	public final RuleSet getRuleSet() {
		return this.ruleSet;
	}

	/**
	 * get the size of this board.
	 * 
	 * @return the size
	 */
	public short getSize() {
		return this.size;
	}

	/**
	 * Gets the zobrist.
	 * 
	 * @return the zobrist
	 */
	public Zobrist getZobrist() {
		return this.zobrist;
	}

	/**
	 * create a new board based on the current board plus a move.
	 * 
	 * @param move
	 *            the move
	 * @return the new board
	 */
	abstract public BoardI newBoard(Move move);

	/**
	 * set the colour of a vertex.
	 * 
	 * @param row
	 *            the row
	 * @param column
	 *            the column
	 * @param colour
	 *            the colour to set this to
	 */
	protected void setColour(int row, int column, int colour) {
		throw new Error("concrete classes need to override this method " + row
				+ column + colour);
	}

	/**
	 * Sets the zobrist.
	 * 
	 * @param zobrist
	 *            the zobrist
	 */
	protected void setZobrist(Zobrist zobrist) {
		this.zobrist = zobrist;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.BoardInterface#toString()
	 */
	@Override
	public String toString() {
		return BoardToASCII.Transform(this);
	}

	public boolean isOffBoard(int row, int column) {
		if (row < 0)
			return true;
		if (column < 0)
			return true;
		if (row >= this.getSize())
			return true;
		if (column < this.getSize())
			return true;
		return false;
	}

	public boolean isWayOffBoard(int row, int column) {
		if (row < -1)
			return true;
		if (column < -1)
			return true;
		if (row >= this.getSize() + 1)
			return true;
		if (column >= this.getSize() + 1)
			return true;
		return false;
	}

}
