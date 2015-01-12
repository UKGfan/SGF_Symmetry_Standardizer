package jgogears;

// TODO: Auto-generated Javadoc

/**
 * A GTP interface for which a computer-go player need not keep any state. All
 * necessary state is passed to the player each time the player is called.
 * 
 * @author syeates
 */
public interface GTPInterface {

	/**
	 * Clear board.
	 * 
	 * @param state
	 *            the state
	 */
	void clearBoard(GTPState state);

	/**
	 * Final status list.
	 * 
	 * @param status
	 *            the status
	 * @param state
	 *            the state
	 * @return the move[]
	 */
	Move[] finalStatusList(String status, GTPState state);

	/**
	 * Fixed handicap.
	 * 
	 * @param handicap
	 *            the handicap
	 * @param state
	 *            the state
	 * @return the move[]
	 */
	Move[] fixedHandicap(int handicap, GTPState state);

	/**
	 * Gen move.
	 * 
	 * @param colour
	 *            the colour
	 * @param state
	 *            the state
	 * @return the move
	 */
	Move genMove(short colour, GTPState state);

	/**
	 * get the name of the engine.
	 * 
	 * @return the engine name
	 */
	String getEngineName();

	/**
	 * get the version of the engine.
	 * 
	 * @return the engine version
	 */
	String getEngineVersion();

	/**
	 * Gets the final score.
	 * 
	 * @param state
	 *            the state
	 * @return the final score
	 */
	GTPScore getFinalScore(GTPState state);

	/**
	 * Gets the known command.
	 * 
	 * @param command
	 *            the command
	 * @return the known command
	 */
	boolean getKnownCommand(String command);

	/**
	 * Gets the list commands.
	 * 
	 * @return the list commands
	 */
	String[] getListCommands();

	/**
	 * get the protocol version.
	 * 
	 * @return the protocol version
	 */
	int getProtocolVersion();

	/**
	 * Loadsgf.
	 * 
	 * @param filename
	 *            the filename
	 * @param moveNumber
	 *            the move number
	 * @param state
	 *            the state
	 */
	void loadsgf(String filename, int moveNumber, GTPState state);

	/**
	 * Place free handicap.
	 * 
	 * @param handicap
	 *            the handicap
	 * @param state
	 *            the state
	 * @return the move[]
	 */
	Move[] placeFreeHandicap(int handicap, GTPState state);

	/**
	 * Place free handicap.
	 * 
	 * @param stones
	 *            the stones
	 * @param state
	 *            the state
	 */
	void placeFreeHandicap(jgogears.Move[] stones, GTPState state);

	/**
	 * Play.
	 * 
	 * @param move
	 *            the move
	 * @param state
	 *            the state
	 */
	void play(Move move, GTPState state);

	/**
	 * Quit.
	 * 
	 * @return true, if successful
	 */
	boolean quit();

	/**
	 * Reg gen move.
	 * 
	 * @param colour
	 *            the colour
	 * @param state
	 *            the state
	 * @return the move
	 */
	Move regGenMove(int colour, GTPState state);

	/**
	 * Sets the board size.
	 * 
	 * @param size
	 *            the size
	 * @param state
	 *            the state
	 */
	void setBoardSize(short size, GTPState state);

	/**
	 * Sets the komi.
	 * 
	 * @param komi
	 *            the komi
	 * @param state
	 *            the state
	 */
	void setKomi(double komi, GTPState state);

	/**
	 * Sets the time left.
	 * 
	 * @param colour
	 *            the colour
	 * @param byoYomiTime
	 *            the byo yomi time
	 * @param byoYomiStones
	 *            the byo yomi stones
	 * @param state
	 *            the state
	 */
	void setTimeLeft(int colour, double byoYomiTime, double byoYomiStones,
			GTPState state);

	/**
	 * Sets the time settings.
	 * 
	 * @param mainTime
	 *            the main time
	 * @param byoYomiTime
	 *            the byo yomi time
	 * @param byoYomiStones
	 *            the byo yomi stones
	 * @param state
	 *            the state
	 */
	void setTimeSettings(double mainTime, double byoYomiTime,
			double byoYomiStones, GTPState state);

	/**
	 * Show board.
	 * 
	 * @param state
	 *            the state
	 * @return the board i
	 */
	BoardI showBoard(GTPState state);

	/**
	 * Undo.
	 * 
	 * @param state
	 *            the state
	 * @return true, if successful
	 */
	boolean undo(GTPState state);

}
