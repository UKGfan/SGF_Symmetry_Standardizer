package jgogears;

import java.util.TreeSet;

// TODO: Auto-generated Javadoc
/**
 * The Interface GTPInterfaceRaw.
 */
public interface GTPInterfaceRaw {

	/**
	 * Clear board.
	 * 
	 * @return true, if successful
	 */
	boolean clearBoard();

	/**
	 * Final status list.
	 * 
	 * @param status
	 *            the status
	 * @return the tree set< vertex>
	 */
	TreeSet<Vertex> finalStatusList(String status);

	/**
	 * Fixed handicap.
	 * 
	 * @param handicap
	 *            the handicap
	 * @return the tree set< vertex>
	 */
	TreeSet<Vertex> fixedHandicap(short handicap);

	/**
	 * Gen move.
	 * 
	 * @param colour
	 *            the colour
	 * @return the move
	 */
	Move genMove(short colour);

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
	 * @return the final score
	 */
	GTPScore getFinalScore();

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
	TreeSet<String> getListCommands();

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
	 * @return true, if successful
	 */
	boolean loadsgf(String filename, int moveNumber);

	/**
	 * Place free handicap.
	 * 
	 * @param handicap
	 *            the handicap
	 * @return the tree set< vertex>
	 */
	TreeSet<Vertex> placeFreeHandicap(short handicap);

	/**
	 * Place free handicap.
	 * 
	 * @param stones
	 *            the stones
	 * @return true, if successful
	 */
	boolean placeFreeHandicap(TreeSet<Vertex> stones);

	/**
	 * Play.
	 * 
	 * @param move
	 *            the move
	 * @return true, if successful
	 */
	boolean play(Move move);

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
	 * @return the move
	 */
	Move regGenMove(short colour);

	/**
	 * Sets the board size.
	 * 
	 * @param size
	 *            the size
	 * @return true, if successful
	 */
	boolean setBoardSize(short size);

	/**
	 * Sets the komi.
	 * 
	 * @param komi
	 *            the komi
	 * @return true, if successful
	 */
	boolean setKomi(double komi);

	/**
	 * Sets the time left.
	 * 
	 * @param colour
	 *            the colour
	 * @param byoYomiTime
	 *            the byo yomi time
	 * @param byoYomiStones
	 *            the byo yomi stones
	 * @return true, if successful
	 */
	boolean setTimeLeft(short colour, double byoYomiTime, double byoYomiStones);

	/**
	 * Sets the time settings.
	 * 
	 * @param mainTime
	 *            the main time
	 * @param byoYomiTime
	 *            the byo yomi time
	 * @param byoYomiStones
	 *            the byo yomi stones
	 * @return true, if successful
	 */
	boolean setTimeSettings(double mainTime, double byoYomiTime,
			double byoYomiStones);

	/**
	 * Show board.
	 * 
	 * @return the board i
	 */
	BoardI showBoard();

	/**
	 * Undo.
	 * 
	 * @return true, if successful
	 */
	boolean undo();
}
