package jgogears;

/**
 * An incomplete clone of the TwoGTP program included in the GnuGo distribution.
 * It runs a go game between a pair of GTP-capiable players TODO finish this
 * implementation
 * 
 * @author syeates
 */

public class TwoGTP {
	/** are we spewing debugging information? */
	public static final boolean DEBUG = false;

	/** The black player. */
	private GTPInterface black = null;

	/** The white player. */
	private GTPInterface white = null;

	private final GTPState state = new GTPState();

	private int passes = 0;

	private boolean blackNext = true;

	/**
	 * get the black
	 * 
	 * @return the black
	 */
	public final GTPInterface getBlack() {
		return this.black;
	}

	/**
	 * get the currentBoard
	 * 
	 * @return the currentBoard
	 */
	public final BoardI getCurrentBoard() {
		return this.state.getBoard();
	}

	/**
	 * get the white
	 * 
	 * @return the white
	 */
	public final GTPInterface getWhite() {
		return this.white;
	}

	/**
	 * play a single move in the game
	 * 
	 * @return the state after the move
	 */
	public GTPState move() {
		if (this.black == null)
			throw new Error();
		if (this.white == null)
			throw new Error();
		Move move = null;
		BoardI oldBoard = this.state.getBoard();
		if (this.blackNext) {
			move = this.black.genMove(BoardI.VERTEX_BLACK, this.state);
		} else {
			move = this.white.genMove(BoardI.VERTEX_WHITE, this.state);
		}
		if (!RuleSet.DEFAULT.moveIsLegal(null, oldBoard, move))
			throw new Error(move + "\n" + oldBoard);
		if (move.getPass())
			this.passes++;
		else
			this.passes = 0;
		this.blackNext = !this.blackNext;

		if (DEBUG)
			System.err.println("TwoGTP: played " + move);
		if (DEBUG)
			System.err.println(this.state.getBoard());

		return this.state;

	}

	/**
	 * Run the game. Assumes that the black and white players have already been
	 * set up.
	 * 
	 * @return true, if play out game
	 */

	public GTPState playOutGame() {

		if (this.black == null)
			throw new Error();
		if (this.white == null)
			throw new Error();

		while (this.passes < 4)
			this.move();

		if (DEBUG)
			System.err.println(this.black.getFinalScore(this.state));
		if (DEBUG)
			System.err.println(this.white.getFinalScore(this.state));

		return this.state;
	}

	/**
	 * set the black
	 * 
	 * @param black
	 *            the black to set
	 */
	public final void setBlack(GTPInterface black) {
		this.black = black;
	}

	/**
	 * set the white
	 * 
	 * @param white
	 *            the white to set
	 */
	public final void setWhite(GTPInterface white) {
		this.white = white;
	}

}
