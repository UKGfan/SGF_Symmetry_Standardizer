/**
 * 
 */
package jgogears;

import java.util.*;

// TODO: Auto-generated Javadoc
/**
 * The Class RuleSet.
 * 
 * @author Stuart
 */
public abstract class RuleSet {
	/**
	 * The default ruleset
	 */
	static public final RuleSet DEFAULT = new NoKoRuleSet();

	/**
	 * Captures.
	 * 
	 * @param game
	 *            the game
	 * @param move
	 *            the move
	 * @param board
	 *            the board
	 * @return the tree set< vertex>
	 */
	public abstract TreeSet<Vertex> captures(Game game, BoardI board, Move move);

	/**
	 * Count liberties.
	 * 
	 * @param rowb
	 *            the rowb
	 * @param columnb
	 *            the columnb
	 * @param board
	 *            the board
	 * @return the short
	 */
	public short countLiberties(int rowb, int columnb, BoardI board) {
		return this.countLiberties((short) rowb, (short) columnb, board);
	}

	/**
	 * Count liberties.
	 * 
	 * @param rowb
	 *            the rowb
	 * @param columnb
	 *            the columnb
	 * @param board
	 *            the board
	 * @return the short
	 */
	public short countLiberties(short rowb, short columnb, BoardI board) {
		return (short) this.getLiberties(rowb, columnb, board).size();
	}

	/**
	 * Get all the legal moves
	 * 
	 * @param game
	 *            the game being played
	 * @param board
	 *            the current state of the baord
	 * @param colour
	 *            the colour being played
	 * @return the collection of moves
	 */

	public Collection<Move> getAllLegalMoves(Game game, BoardI board,
			short colour) {
		Stack<Move> moves = new Stack<Move>();
		for (int i = 0; i < board.getSize(); i++)
			for (int j = 0; j < board.getSize(); j++) {
				Move move = new Move(i, j, colour);
				if (this.moveIsLegal(game, board, move))
					moves.push(move);
			}
		return moves;
	}

	/**
	 * Get all the legal moves
	 * 
	 * @param game
	 *            the game being played
	 * @param board
	 *            the current state of the baord
	 * @param colour
	 *            the colour being played
	 * @return the collection of vertexes
	 */
	public Collection<Vertex> getAllLegalVertexes(Game game, BoardI board,
			short colour) {
		Stack<Vertex> moves = new Stack<Vertex>();
		for (int i = 0; i < board.getSize(); i++)
			for (int j = 0; j < board.getSize(); j++)
				if (this.moveIsLegal(game, board, new Move(i, j, colour)))
					moves.push(new Vertex(i, j));
		return moves;
	}

	/**
	 * Get a description of this Ko rule.
	 * 
	 * @return the description
	 */
	public abstract String getDescription();

	/**
	 * Gets the liberties.
	 * 
	 * @param rowb
	 *            the rowb
	 * @param columnb
	 *            the columnb
	 * @param board
	 *            the board
	 * @return the liberties
	 */
	public TreeSet<Vertex> getLiberties(int rowb, int columnb, BoardI board) {
		return this.getLiberties((short) rowb, (short) columnb, board);
	}

	/**
	 * Gets the liberties.
	 * 
	 * @param rowb
	 *            the rowb
	 * @param columnb
	 *            the columnb
	 * @param board
	 *            the board
	 * @return the liberties
	 */
	abstract public TreeSet<Vertex> getLiberties(short rowb, short columnb,
			BoardI board);

	/**
	 * Get the name of this Ko rule.
	 * 
	 * @return the name
	 */
	public abstract String getName();

	/**
	 * Gets the string.
	 * 
	 * @param row
	 *            the row
	 * @param column
	 *            the column
	 * @param board
	 *            the board
	 * @return the string
	 */
	TreeSet<Vertex> getString(int row, int column, BoardI board) {
		return this.getString((short) row, (short) column, board);
	}

	/**
	 * Helper function to get a string containing this position.
	 * 
	 * @param row
	 *            the row
	 * @param column
	 *            the column
	 * @param board
	 *            the board
	 * @return the string
	 */
	abstract public TreeSet<Vertex> getString(short row, short column,
			BoardI board);

	/**
	 * Leaves ko.
	 * 
	 * @param game
	 *            the game
	 * @param move
	 *            the move
	 * @param board
	 *            the board
	 * @return the tree set< vertex>
	 */
	public abstract TreeSet<Vertex> leavesKo(Game game, BoardI board, Move move);

	/**
	 * Is this move legal, given this board in this game?.
	 * 
	 * @param game
	 *            the game
	 * @param move
	 *            the move
	 * @param board
	 *            the board
	 * @return true, if move is legal
	 */
	public abstract boolean moveIsLegal(Game game, BoardI board, Move move);

}
