package jgogears;

import java.io.*;
import java.util.*;

/**
 * External representation of a game of go. This is currently very heavily
 * influenced by SGF, it needs to be generalised.
 * 
 * @author syeates
 */
public final class Game {

	/**
	 * Load an SGF file into memory.
	 * 
	 * @param file
	 *            the file to load
	 * @return the game
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */

	public static Game loadFromFile(File file) throws IOException {
		try {

			return SGFGameTree.loadFromFile(file);
		} catch (jgogears.SGF.TokenMgrError e) {
			throw new java.io.IOException("Error reading \"" + file + "\" "
					+ e.getLocalizedMessage());
		}
	}

	/**
	 * Load an SGF file into memory.
	 * 
	 * @param filename
	 *            the filename to load
	 * @return the game
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */

	public static Game loadFromFilename(String filename) throws IOException {
		return loadFromFile(new File(filename));
	}

	/** The size. */
	protected short size = 0;

	/** The black player. */
	private String blackPlayer = "";

	/** The white player. */
	private String whitePlayer = "";

	/** The komi. */
	private short komi = 0;

	/** The handicap. */
	private short handicap = 0;

	/** The score. */
	private GTPScore score = null;

	/** The comment count. */
	private int commentCount = 0;

	/** The branched. */
	private boolean branched = false;

	/** The black rank. */
	private Rank blackRank = new Rank();

	/** The white rank. */
	private Rank whiteRank = new Rank();

	/** The ruleset. */
	private String ruleset = "";

	/** The date. */
	private String date = "";

	/** The maintime. */
	private String maintime = "";

	/** The extra time. */
	private String extraTime = "";

	/** The PC. */
	private String PC = "";

	/** The movelist. */
	private LinkedList<Move> movelist = new LinkedList<Move>();

	/** The boardlist. */
	private LinkedList<BoardI> boardlist = null;

	/**
	 * default constractor.
	 */
	public Game() {
		// nothing
	}

	/**
	 * default constractor.
	 * 
	 * @param size
	 *            the size
	 */
	public Game(int size) {
		this.size = (short) size;
	}

	/**
	 * build a game from a gametree.
	 * 
	 * @param gameTree
	 *            the game tree
	 */
	public Game(SGFGameTree gameTree) {
		gameTree.init(this);
		// System.err.println("\"" + this.blackPlayer + "\" (" + this.blackRank
		// + ") vs" + "\"" +
		// this.blackPlayer + "\" (" + this.blackRank + ") " + this.size + "x" +
		// this.size + " h=" +
		// this.handicap + " s=\"" + this.score+ "\" b=" + this.branched + " r="
		// + this.ruleset + " date=\"" + this.date +"\" t=\"" + this.maintime +
		// "\" e=\"" + this.extraTime +"\" PC=\"" + this.PC +"\"");
	}

	public boolean getBlackWin() {
		if (score != null && score.getBlackWin())
			return true;
		return false;
	}

	public boolean getWhiteWin() {
		if (score != null && score.getWhiteWin())
			return true;
		return false;
	}

	public boolean getNeitherWin() {
		if (score == null || score.getDraw() || score.getVoid())
			return true;
		return false;
	}

	/**
	 * get the blackPlayer.
	 * 
	 * @return the blackPlayer
	 */
	public final String getBlackPlayer() {
		return this.blackPlayer;
	}

	/**
	 * get the blackRank.
	 * 
	 * @return the blackRank
	 */
	public final Rank getBlackRank() {
		return this.blackRank;
	}

	/**
	 * get the boardlist.
	 * 
	 * @return the boardlist
	 */
	public final LinkedList<BoardI> getBoardlist() {
		if (this.boardlist == null)
			getBoards();
		return this.boardlist;
	}

	/**
	 * Get an iterator of all the boards in the game.
	 * 
	 * @return an iterator
	 */
	public Iterator<BoardI> getBoards() {
		if (this.boardlist == null) {

			Iterator<Move> moves = this.getMoves();
			this.boardlist = new LinkedList<BoardI>();
			BoardI board = BoardI.newBoard(this.size);
			this.boardlist.add(board);
			while (moves.hasNext()) {
				Move move = moves.next();
				board = board.newBoard(move);
				this.boardlist.add(board);
			}
		}
		return this.boardlist.iterator();
	}

	/**
	 * get the commentCount.
	 * 
	 * @return the commentCount
	 */
	public final int getCommentCount() {
		return this.commentCount;
	}

	/**
	 * get the date.
	 * 
	 * @return the date
	 */
	public final String getDate() {
		return this.date;
	}

	/**
	 * get the extraTime.
	 * 
	 * @return the extraTime
	 */
	public final String getExtraTime() {
		return this.extraTime;
	}

	/**
	 * get the handicap.
	 * 
	 * @return the handicap
	 */
	public final short getHandicap() {
		return this.handicap;
	}

	/**
	 * get the komi.
	 * 
	 * @return the komi
	 */
	public final short getKomi() {
		return this.komi;
	}

	/**
	 * get the maintime.
	 * 
	 * @return the maintime
	 */
	public final String getMaintime() {
		return this.maintime;
	}

	/**
	 * get the movelist
	 * 
	 * @return the movelist
	 */
	public final LinkedList<Move> getMovelist() {
		return this.movelist;
	}

	/**
	 * get the movelist.
	 * 
	 * @return the movelist
	 */
	public final Iterator<Move> getMoves() {
		return this.movelist.iterator();
	}

	/**
	 * get the pC.
	 * 
	 * @return the pC
	 */
	public final String getPC() {
		return this.PC;
	}

	/**
	 * get the ruleset.
	 * 
	 * @return the ruleset
	 */
	public final String getRuleset() {
		return this.ruleset;
	}

	/**
	 * get the score.
	 * 
	 * @return the score
	 */
	public final GTPScore getScore() {
		return this.score;
	}

	/**
	 * get the size.
	 * 
	 * @return the size
	 */
	public final short getSize() {
		return this.size;
	}

	/**
	 * get the whitePlayer.
	 * 
	 * @return the whitePlayer
	 */
	public final String getWhitePlayer() {
		return this.whitePlayer;
	}

	/**
	 * get the whiteRank.
	 * 
	 * @return the whiteRank
	 */
	public final Rank getWhiteRank() {
		return this.whiteRank;
	}

	/**
	 * get the branched.
	 * 
	 * @return the branched
	 */
	public final boolean isBranched() {
		return this.branched;
	}

	/**
	 * set the blackPlayer.
	 * 
	 * @param blackPlayer
	 *            the blackPlayer to set
	 */
	public final void setBlackPlayer(String blackPlayer) {
		this.blackPlayer = blackPlayer;
	}

	/**
	 * set the blackRank.
	 * 
	 * @param blackRank
	 *            the blackRank to set
	 */
	public final void setBlackRank(Rank blackRank) {
		this.blackRank = blackRank;
	}

	/**
	 * set the boardlist.
	 * 
	 * @param boardlist
	 *            the boardlist to set
	 */
	public final void setBoardlist(LinkedList<BoardI> boardlist) {
		this.boardlist = boardlist;
	}

	/**
	 * set the branched.
	 * 
	 * @param branched
	 *            the branched to set
	 */
	public final void setBranched(boolean branched) {
		this.branched = branched;
	}

	/**
	 * set the commentCount.
	 * 
	 * @param commentCount
	 *            the commentCount to set
	 */
	public final void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	/**
	 * set the date.
	 * 
	 * @param date
	 *            the date to set
	 */
	public final void setDate(String date) {
		this.date = date;
	}

	/**
	 * set the extraTime.
	 * 
	 * @param extraTime
	 *            the extraTime to set
	 */
	public final void setExtraTime(String extraTime) {
		this.extraTime = extraTime;
	}

	/**
	 * set the handicap.
	 * 
	 * @param handicap
	 *            the handicap to set
	 */
	public final void setHandicap(short handicap) {
		this.handicap = handicap;
	}

	/**
	 * set the komi.
	 * 
	 * @param komi
	 *            the komi to set
	 */
	public final void setKomi(short komi) {
		this.komi = komi;
	}

	/**
	 * set the maintime.
	 * 
	 * @param maintime
	 *            the maintime to set
	 */
	public final void setMaintime(String maintime) {
		this.maintime = maintime;
	}

	/**
	 * set the movelist.
	 * 
	 * @param movelist
	 *            the movelist to set
	 */
	public final void setMovelist(LinkedList<Move> movelist) {
		this.movelist = movelist;
	}

	/**
	 * set the pC.
	 * 
	 * @param pc
	 *            the pC to set
	 */
	public final void setPC(String pc) {
		this.PC = pc;
	}

	/**
	 * set the ruleset.
	 * 
	 * @param ruleset
	 *            the ruleset to set
	 */
	public final void setRuleset(String ruleset) {
		this.ruleset = ruleset;
	}

	/**
	 * set the score.
	 * 
	 * @param score
	 *            the score to set
	 */
	public final void setScore(GTPScore score) {
		this.score = score;
	}

	/**
	 * set the size.
	 * 
	 * @param size
	 *            the size to set
	 */
	public final void setSize(short size) {
		this.size = size;
	}

	/**
	 * set the whitePlayer.
	 * 
	 * @param whitePlayer
	 *            the whitePlayer to set
	 */
	public final void setWhitePlayer(String whitePlayer) {
		this.whitePlayer = whitePlayer;
	}

	/**
	 * set the whiteRank.
	 * 
	 * @param whiteRank
	 *            the whiteRank to set
	 */
	public final void setWhiteRank(Rank whiteRank) {
		this.whiteRank = whiteRank;
	}

}
