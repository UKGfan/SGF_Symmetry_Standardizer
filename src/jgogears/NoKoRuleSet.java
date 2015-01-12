/**
 * 
 */
package jgogears;

import java.util.*;

/**
 * Really simple and fast ruleset
 * 
 * @author Stuart
 */
public class NoKoRuleSet extends RuleSet {

	/** Are we being verbose? */
	final static boolean DEBUG = false;

	/** an empty treeset for filling from */
	final static TreeSet<Vertex> EMPTY = new TreeSet<Vertex>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.tsume.KoRule#captures(jgogears.GoGame, jgogears.GoBoard,
	 * jgogears.GoMove)
	 */
	@Override
	public TreeSet<Vertex> captures(Game game, BoardI board, Move move) {
		if (board == null)
			throw new Error();
		if (move == null)
			throw new Error();
		if (!move.getPlay())
			return new TreeSet<Vertex>();

		short row = move.getRow();
		short column = move.getColumn();
		short colour = move.getColour();

		TreeSet<Vertex> captures = new TreeSet<Vertex>();

		// there can only be captures when placing a stone
		if (colour != BoardI.VERTEX_BLACK || colour != BoardI.VERTEX_WHITE) {
			captures.addAll(this.captureshelper(board, move, row + 1, column));
			captures.addAll(this.captureshelper(board, move, row - 1, column));
			captures.addAll(this.captureshelper(board, move, row, column + 1));
			captures.addAll(this.captureshelper(board, move, row, column - 1));
		}
		return captures;
	}

	/**
	 * Captureshelper.
	 * 
	 * @param board
	 *            the board
	 * @param move
	 *            the move
	 * @param row
	 *            the row
	 * @param column
	 *            the column
	 * @return the tree set< vertex>
	 */
	public TreeSet<Vertex> captureshelper(BoardI board, Move move, int row,
			int column) {
		if (DEBUG && EMPTY.size() != 0)
			throw new Error("EMPTY not empty");
		short colour = move.getColour();
		short acolour = board.getColour(row, column);
		if (acolour == BoardI.VERTEX_EMPTY || acolour == BoardI.VERTEX_KO) {
			if (DEBUG)
				System.err.println("captures == empty");
			return EMPTY;
		}
		if (colour == BoardI.VERTEX_BLACK && acolour == BoardI.VERTEX_WHITE
				|| colour == BoardI.VERTEX_WHITE
				&& acolour == BoardI.VERTEX_BLACK) {
			int libs = this.countLiberties(row, column, board);
			if (libs == 1) {
				TreeSet<Vertex> string = this.getString(row, column, board);
				if (DEBUG)
					System.err.println("captures == single liberty! capture! "
							+ libs + " " + string);
				return string;
			} else {
				if (DEBUG)
					System.err.println("captures == multiple liberties");
			}
		}
		if (DEBUG)
			System.err.println("captures == ? " + colour + " " + acolour);
		return EMPTY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.tsume.KoRule#getDescription()
	 */
	@Override
	public String getDescription() {
		return "A ko rule which doesn't recognise any form of Ko whatsoever and allows unbounded loops and repetition.";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.RuleSet#getLiberties(short, short, jgogears.BoardI)
	 */
	@Override
	public TreeSet<Vertex> getLiberties(short rowb, short columnb, BoardI board) {
		if (board.getColour(rowb, columnb) == BoardI.VERTEX_EMPTY
				|| board.getColour(rowb, columnb) == BoardI.VERTEX_KO
				|| board.getColour(rowb, columnb) == BoardI.VERTEX_OFF_BOARD) {
			throw new Error("empty sqaures don't have liberties");
		}

		TreeSet<Vertex> string = this.getString(rowb, columnb, board);
		TreeSet<Vertex> liberties = new TreeSet<Vertex>();

		Iterator<Vertex> i = string.iterator();
		while (i.hasNext()) {
			Vertex current = i.next();
			short row = current.getRow();
			short column = current.getColumn();
			if (board.getColour(row, column + 1) == BoardI.VERTEX_EMPTY
					|| board.getColour(row, column + 1) == BoardI.VERTEX_KO) {
				Vertex adjacent = new Vertex(row, column + 1);
				liberties.add(adjacent);
			}
			if (board.getColour(row, column - 1) == BoardI.VERTEX_EMPTY
					|| board.getColour(row, column - 1) == BoardI.VERTEX_KO) {
				Vertex adjacent = new Vertex(row, column - 1);
				liberties.add(adjacent);
			}
			if (board.getColour(row + 1, column) == BoardI.VERTEX_EMPTY
					|| board.getColour(row + 1, column) == BoardI.VERTEX_KO) {
				Vertex adjacent = new Vertex(row + 1, column);
				liberties.add(adjacent);
			}
			if (board.getColour(row - 1, column) == BoardI.VERTEX_EMPTY
					|| board.getColour(row - 1, column) == BoardI.VERTEX_KO) {
				Vertex adjacent = new Vertex(row - 1, column);
				liberties.add(adjacent);
			}
		}
		return liberties;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.tsume.KoRule#getName()
	 */
	@Override
	public String getName() {
		return "No Ko";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.RuleSet#getString(int, int, jgogears.BoardI)
	 */
	@Override
	TreeSet<Vertex> getString(int rowb, int columnb, BoardI board) {
		return this.getString((short) rowb, (short) columnb, board);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.RuleSet#getString(short, short, jgogears.BoardI)
	 */
	@Override
	public TreeSet<Vertex> getString(short rowb, short columnb, BoardI board) {
		TreeSet<Vertex> string = new TreeSet<Vertex>();
		short colour = board.getColour(rowb, columnb);
		if (colour == BoardI.VERTEX_OFF_BOARD)
			return string;

		Vertex seed = new Vertex(rowb, columnb);
		string.add(seed);
		TreeSet<Vertex> newstring = new TreeSet<Vertex>(string);
		newstring.add(seed);

		boolean changed = false;
		do {
			changed = false;
			Iterator<Vertex> i = string.iterator();
			while (i.hasNext()) {
				Vertex current = i.next();
				short row = current.getRow();
				short column = current.getColumn();
				if (board.getColour(row, column + 1) == colour) {
					Vertex adjacent = new Vertex(row, column + 1);
					newstring.add(adjacent);
				}
				if (board.getColour(row, column - 1) == colour) {
					Vertex adjacent = new Vertex(row, column - 1);
					newstring.add(adjacent);
				}
				if (board.getColour(row + 1, column) == colour) {
					Vertex adjacent = new Vertex(row + 1, column);
					newstring.add(adjacent);
				}
				if (board.getColour(row - 1, column) == colour) {
					Vertex adjacent = new Vertex(row - 1, column);
					newstring.add(adjacent);
				}

			}
			changed = string.size() != newstring.size();
			if (DEBUG)
				System.err.print(" " + string.size() + " " + newstring.size());
			if (string.size() > newstring.size())
				throw new Error("string has got smaller! " + string + " / "
						+ newstring);
			string.addAll(newstring);
		} while (changed);
		if (DEBUG)
			System.err.println();

		return string;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.tsume.KoRule#leavesKo(jgogears.GoGame, jgogears.GoBoard,
	 * jgogears.GoMove)
	 */
	@Override
	public TreeSet<Vertex> leavesKo(Game game, BoardI board, Move move) {
		// TODO write test cases for this method
		return new TreeSet<Vertex>();
	}

	/**
	 * Helper for moveIsLegal.
	 * 
	 * @param row
	 *            the row of the position that is a potential liberty
	 * @param column
	 *            the column of the position that is a potential liberty
	 * @param colour
	 *            the colour of the stone we want to use these liberties for
	 * @param board
	 *            the board
	 * @return the number of liberties through this position
	 */
	TreeSet<Vertex> legelsfrompos(int row, int column, short colour,
			BoardI board) {
		TreeSet<Vertex> liberties = new TreeSet<Vertex>();
		short acolour = board.getColour(row, column);
		if (acolour == colour) {
			liberties.addAll(this.getLiberties(row, column, board));
			if (DEBUG)
				System.err.println("position == same");
		}
		if (acolour == BoardI.VERTEX_EMPTY || acolour == BoardI.VERTEX_EMPTY)
			liberties.add(new Vertex(row, column));
		return liberties;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.tsume.KoRule#moveIsLegal(jgogears.GoGame, jgogears.GoBoard,
	 * jgogears.GoMove)
	 */
	@Override
	public boolean moveIsLegal(Game game, BoardI board, Move move) {
		if (board == null)
			throw new Error();
		if (move == null)
			throw new Error();
		if (move.getPass())
			return true;
		if (move.getResign())
			return true;
		if (!move.getPlay())
			throw new Error("Internal error, bad move");

		short row = move.getRow();
		short column = move.getColumn();
		short colour = move.getColour();

		if (board.getColour(row, column) != BoardI.VERTEX_EMPTY) {
			if (DEBUG)
				System.err.println("illegal move, not empty");
			return false;
		}

		if (colour != BoardI.VERTEX_BLACK && colour != BoardI.VERTEX_WHITE)
			throw new Error("Internal error, bad colour");
		TreeSet<Vertex> liberties = new TreeSet<Vertex>();

		liberties.addAll(this.legelsfrompos(row + 1, column, colour, board));
		liberties.addAll(this.legelsfrompos(row - 1, column, colour, board));
		liberties.addAll(this.legelsfrompos(row, column + 1, colour, board));
		liberties.addAll(this.legelsfrompos(row, column - 1, colour, board));

		liberties.addAll(this.captures(game, board, move));

		liberties.remove(new Vertex(row, column));

		if (liberties.size() > 0)
			return true;

		return false;

	}
}
