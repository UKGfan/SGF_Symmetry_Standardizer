package jgogears;

import java.io.IOException;

/**
 * An incomplete clone of the TwoGTP program included in the GnuGo distribution.
 * It runs a go game between a pair of GTP-capiable players TODO finish this
 * implementation
 * 
 * @author syeates
 */

public class TwoGTPRaw {

	/**
	 * Play two GTP-compatible players against each other.
	 * 
	 * @param args
	 *            (ignored)
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */

	public static void main(String[] args) throws IOException {
		TwoGTPRaw twoGTP = new TwoGTPRaw();
		twoGTP.black = new GnuGoEngine();
		twoGTP.white = new GnuGoEngine();
		twoGTP.playOutGame();

	}

	/** The black player. */
	private GTPInterfaceRaw black = null;

	/** The white player. */
	private GTPInterfaceRaw white = null;

	private BoardI currentBoard = BoardI.newBoard();

	/**
	 * get the black
	 * 
	 * @return the black
	 */
	public final GTPInterfaceRaw getBlack() {
		return this.black;
	}

	/**
	 * get the currentBoard
	 * 
	 * @return the currentBoard
	 */
	public final BoardI getCurrentBoard() {
		return this.currentBoard;
	}

	/**
	 * get the white
	 * 
	 * @return the white
	 */
	public final GTPInterfaceRaw getWhite() {
		return this.white;
	}

	/**
	 * Run the game. Assumes that the black and white players have already been
	 * set up.
	 * 
	 * @return true, if play out game
	 */

	public boolean playOutGame() {
		int passes = 0;
		boolean blackNext = true;
		// TODO check the black and white players have been set up

		while (passes < 4) {
			Move move = null;
			if (blackNext) {
				move = this.black.genMove(BoardI.VERTEX_BLACK);
				assert move != null;
				this.white.play(move);
				if (move.getPass())
					passes++;
				else
					passes = 0;
				blackNext = false;
			} else {
				move = this.white.genMove(BoardI.VERTEX_WHITE);
				assert move != null;
				this.black.play(move);
				if (move.getPass())
					passes++;
				else
					passes = 0;
				blackNext = true;
			}
			this.currentBoard = this.currentBoard.newBoard(move);
		}
		System.err.println(this.black.getFinalScore());
		System.err.println(this.white.getFinalScore());
		System.err.println(this.black.showBoard());
		System.err.println(this.white.showBoard());

		return true;
	}

	/**
	 * set the black
	 * 
	 * @param black
	 *            the black to set
	 */
	public final void setBlack(GTPInterfaceRaw black) {
		this.black = black;
	}

	/**
	 * set the white
	 * 
	 * @param white
	 *            the white to set
	 */
	public final void setWhite(GTPInterfaceRaw white) {
		this.white = white;
	}

}
