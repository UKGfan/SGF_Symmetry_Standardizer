package jgogears.engine;

import java.util.*;

/**
 * A probability model of Go
 */
public final class Model {

	/** are we being verbose? */
	public static boolean DEBUG = false;

	/** */
	public static int MAX_LENGTH = 21 * 21;

	/** The random number generator */
	private static Random random = new Random();

	/**
	 * Max of two doubles, biased towards the first in the case of equality
	 * 
	 * @param a
	 *            the first double
	 * @param b
	 *            the second double
	 * @return the float
	 */
	public final static double max(double a, double b) {
		if (a >= b)
			return a;
		else
			return b;
	}

	/**
	 * Max of two floats
	 * 
	 * @param a
	 *            the first double
	 * @param b
	 *            the second double
	 * @return the float
	 */
	public final static float max(float a, float b) {
		if (a >= b)
			return a;
		else
			return b;
	}

	private int boardsTrained = 0;

	private int gamesTrained = 0;

	/** The root of the model tree */
	private final Node root = new Node();

	/**
	 * Instantiates a new model.
	 */
	public Model() {
	}

	/**
	 * Gets the root of the model
	 * 
	 * @return the root
	 */
	public Node getRoot() {
		return this.root;
	}

	/**
	 * get the boardsTrained
	 * 
	 * @return the boardsTrained
	 */
	public final int getBoardsTrained() {
		if (DEBUG)
			System.err.print(":");
		return boardsTrained;
	}

	/**
	 * get the gamesTrained
	 * 
	 * @return the gamesTrained
	 */
	public final int getGamesTrained() {
		return gamesTrained;
	}

	/**
	 * set the boardsTrained
	 * 
	 * @param boardsTrained
	 *            the boardsTrained to set
	 */
	public final void setBoardsTrained(int boardsTrained) {
		if (DEBUG)
			System.err.print(";");
		this.boardsTrained = boardsTrained;
	}

	/**
	 * set the gamesTrained
	 * 
	 * @param gamesTrained
	 *            the gamesTrained to set
	 */
	public final void setGamesTrained(int gamesTrained) {
		this.gamesTrained = gamesTrained;
	}

	/**
	 * get the size of the model
	 * 
	 * @return the size
	 */
	public int size() {
		return root.size();
	}
}
