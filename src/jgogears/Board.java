package jgogears;

/**
 * GoBoard represents the state of a Go board at a particular point in time. It
 * does NOT represent the number of prisoners, the number (or order) of previous
 * moves or whose turn it is too play.
 * 
 * @author stuart
 */
public class Board extends BoardI {

	/** verbose debugging */
	static boolean DEBUG = false;

	/** bounds checking */
	static boolean CHECK = true;

	/** The actual board, of size size. */
	private short[][] board = null;

	/**
	 * Default constructor.
	 */
	public Board() {
		this.init();
	}

	/**
	 * create a new board based on the current board plus a move.
	 * 
	 * @param board
	 *            the move
	 * @param move
	 *            the move
	 */
	public Board(Board board, Move move) {
		this.size = board.getSize();
		this.init();
		this.copydata(board, move);
	}

	/**
	 * Default constructor.
	 * 
	 * @param zobrist
	 *            the zobrist
	 */
	public Board(boolean zobrist) {
		super(zobrist);
		this.init();
	}

	/**
	 * constructor of specially sized boards.
	 * 
	 * @param size
	 *            the size
	 */
	public Board(int size) {
		this.size = (short) size;
		this.init();
	}

	/**
	 * constructor of specially sized boards.
	 * 
	 * @param size
	 *            the size
	 * @param zobrist
	 *            true if we're using zobrist hashes
	 */
	public Board(int size, boolean zobrist) {
		super(zobrist);
		this.size = (short) size;
		this.init();
	}

	/**
	 * constructor of specially sized boards.
	 * 
	 * @param size
	 *            the size
	 * @param rule
	 *            the rule
	 */
	public Board(int size, RuleSet rule) {
		this.size = (short) size;
		this.ruleSet = rule;
		this.init();
	}

	/**
	 * constructor of specially sized boards.
	 * 
	 * @param size
	 *            the size
	 * @param rule
	 *            the rule
	 * @param zobrist
	 *            true if we're using zobrist hashes
	 */
	public Board(int size, RuleSet rule, boolean zobrist) {
		super(zobrist);
		this.size = (short) size;
		this.ruleSet = rule;
		this.init();
	}

	/**
	 * Default constructor.
	 * 
	 * @param rule
	 *            the rule
	 */
	public Board(RuleSet rule) {
		this.ruleSet = rule;
		this.init();
	}

	/**
	 * Instantiates a new board.
	 * 
	 * @param size
	 *            the size
	 */
	public Board(short size) {
		this.size = size;
		this.init();
	}

	/**
	 * constructor of specially sized boards.
	 * 
	 * @param size
	 *            the size
	 * @param rule
	 *            the rule
	 */
	public Board(short size, RuleSet rule) {
		this.size = size;
		this.ruleSet = rule;
		this.init();
	}

	@Override
	public short getColour(int row, int column) {
		// System.err.println("getColour() " + " " + row + " " + column + " " +
		// size);
		if (row < 0 || column < 0 || row >= this.size || column >= this.size)
			return VERTEX_OFF_BOARD;
		return this.board[row][column];
	}

	/**
	 * initialise the board, creating it and setting it empty.
	 */
	protected void init() {
		this.board = new short[this.size][this.size];
		for (int i = 0; i < this.size; i++)
			for (int j = 0; j < this.size; j++)
				this.board[i][j] = VERTEX_EMPTY;
	}

	/**
	 * create a new board based on the current board plus a move.
	 * 
	 * @param move
	 *            the move
	 * @return the new board
	 */
	@Override
	public final Board newBoard(Move move) {
		return new Board(this, move);
	}

	/**
	 * Sets the colour of a vertex
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

		// if (CHECK)
		// if ((row >= this.getSize()) || (row < 0))
		// throw new Error("Bad board size " + row + "/" + this.getSize() +
		// " ");
		// if (CHECK)
		// if ((column >= this.getSize()) || (column < 0))
		// throw new
		// Error("Bad board size or play off the edge of the board (remember we're zero indexed) "
		// + column + "/" + this.getSize() + " ");

		this.board[row][column] = (short) colour;
	}

}
