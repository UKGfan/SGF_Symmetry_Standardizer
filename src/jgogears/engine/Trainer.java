/**
 * 
 */
package jgogears.engine;

import java.io.*;
import java.util.*;

import jgogears.*;

/**
 * @author syeates
 */
public class Trainer {
	/** arbitrarily large integer for use in counting variables */
	static final private int BIG_INT = Integer.MAX_VALUE;
	/** are we being verbose ? */
	static final private boolean DEBUG = false;
	/**
	 * are we showing progress ? Useful in when training on large numbers of
	 * games
	 */
	static public boolean PROGRESS = false;
	/** are we giving a free ride to EMPTY and OFF_BOARD ? */
	static final private boolean freeRideForEmpty = true;

	// these are all set side open to expand the tree as much as possible
	static final private boolean onlyOneNewNodePerSymmetry = true;
	static final private int trainPlaysToDepth = BIG_INT;
	static final private int trainNoPlaysToDepth = BIG_INT;
	static final private int trainPassToDepth = BIG_INT;
	static final private boolean expandNoPlay = true;
	static final private boolean expandPass = true;

	/** how many games are we going to train on? */
	public final int DEFAULT_NUMBER_OF_FILES = Integer.MAX_VALUE;
	/** the model we're going to train */
	private Model model = null;
	/** location of the small training library in svn */
	final public static String LIBRARY = "sgf/2004-12";

	/**
	 * Returns the minimum number of times we have to have visited a node to
	 * expand it. Doesn't apply to nodes expanded with a free ride. This is the
	 * prime method of limiting the growth of the tree size.
	 * 
	 * @return the minimum size, including both played and not-played visits.
	 */
	public final double getMinBranchSize() {
		return model.getGamesTrained() * 2.0 + 20.0;
	}

	/**
	 * Loads all the default SGF files
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @return a Collection of Strings
	 */
	public static Collection<String> loadAllSGFfiles() throws IOException {
		return loadAllSGFfiles(LIBRARY);
	}

	/**
	 * Loads all SGF files in a directory
	 * 
	 * @param directory
	 *            the directory to load
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @return a Collection of Strings
	 */
	public static Collection<String> loadAllSGFfiles(String directory)
			throws IOException {
		Stack<String> files = new Stack<String>();
		files.push(directory);
		Stack<String> result = new Stack<String>();

		while (files.size() > 0) {
			String filename = files.pop();
			File file = new File(filename);
			if (DEBUG)
				System.err.println("examining \"" + filename + "\"");
			if (file.exists()) {
				if (!file.isDirectory()) {
					// System.err.println("\"" + filename + "\" is not a
					// directory, parsing as an SGF file");
					if (file.getName().endsWith(".sgf"))
						result.push(file.getCanonicalPath());

				} else {
					if (DEBUG)
						System.err.println("\"" + filename
								+ "\" is a directory");
					String[] children = file.list();
					if (!file.getName().contains(".svn"))
						for (int i = 0; i < children.length; i++) {
							// System.err.println("pushing \"" + children[i] +
							// "\"");
							files.push(filename + "/" + children[i]);
						}
				}
			}
		}
		return result;

	}

	/**
	 * Train on files.
	 * 
	 * @return the model
	 */
	public Model train() {
		return train(DEFAULT_NUMBER_OF_FILES);
	}

	/**
	 * Train on files.
	 * 
	 * @param count
	 *            the count
	 * @return the model
	 */
	public Model train(int count) {
		try {
			Collection<String> files = loadAllSGFfiles();
			int filecount = 0;
			int examined = 0;
			Iterator<String> iterator = files.iterator();
			while (iterator.hasNext() && filecount < count) {
				String filename = iterator.next();

				Game game = Game.loadFromFile(new File(filename));
				examined++;
				if (game.getSize() == 19) {
					filecount++;
					train(game);
				} else {
					if (DEBUG)
						System.err.print("!");

				}
				// if (PROGRESS || DEBUG)
				// System.err.print(".");
				if (PROGRESS)
					System.err
							.println(filecount
									+ "/"
									+ examined
									+ "/"
									+ files.size()
									+ "/"
									+ count
									+ " "
									+ model.getBoardsTrained()
									+ "b "
									+ model.size()
									+ "n "
									+ ((Runtime.getRuntime().totalMemory() / 1000) - (Runtime
											.getRuntime().freeMemory() / 1000))
									+ "K " + Runtime.getRuntime().totalMemory()
									/ 1000 + "K "
									+ Runtime.getRuntime().maxMemory() / 1000
									+ "K");

			}

			if (DEBUG)
				System.err.println("\nTrainer::trainNFiles loaded " + filecount
						+ " files ");
		} catch (IOException e) {
			System.err.println(e);
			return null;
		}
		return model;
	}

	/**
	 * Train.
	 * 
	 * @param game
	 *            the game
	 */
	public void train(Game game) {
		if (game == null)
			throw new Error("Internal error, null Game");
		if (game.isBranched()) {
			// if (DEBUG)
			System.err
					.println("game branched, assuming it's a teaching game and not training on it");
			return;
		}
		if (game.getNeitherWin()) {
			// if (DEBUG)
			System.err
					.println("no obvious winner to the game, not using it as training");
			return;
		}
		boolean playingBlack = false;
		if (game.getBlackWin())
			playingBlack = true;
		else if (game.getWhiteWin())
			playingBlack = false;
		else
			throw new Error("Bad score" + game.getScore());

		short size = game.getSize();
		Iterator<BoardI> boards = game.getBoards();
		if (boards == null)
			throw new Error();
		Iterator<Move> moves = game.getMoves();
		if (moves == null)
			throw new Error();
		model.setGamesTrained(model.getGamesTrained() + 1);

		while (boards.hasNext() && moves.hasNext()) {
			model.setBoardsTrained(model.getBoardsTrained() + 1);
			BoardI board = boards.next();
			if (board == null)
				throw new Error();
			Move move = moves.next();
			if (move == null)
				throw new Error();
			if (DEBUG)
				System.err.println("Model::train next board is: \n" + board);
			if (DEBUG)
				System.err.println("Model::train about to train on: " + move);
			int colour = move.getColour();
			boolean isBlack = colour == BoardI.VERTEX_BLACK;
			// float str = (float) (isBlack ? strengthB : strengthW);

			if (isBlack == playingBlack) {
				if (move.getResign())
					return;
				else
					for (short i = 0; i < size; i++)
						for (short j = 0; j < size; j++)
							for (short sym = 0; sym < 8; sym++) {
								StraightVertexLineariser linear = new StraightVertexLineariser(
										board, i, j, sym, !isBlack);
								if (move.getPlay())
									if (move.getRow() != i
											&& move.getColumn() != j)
										train(linear, true, true,
												trainPlaysToDepth);
									else
										train(linear, false, expandNoPlay,
												trainNoPlaysToDepth);
								else if (move.getPass())
									train(linear, false, expandPass,
											trainPassToDepth);
								else
									throw new Error(
											"internal error, unknown move type");
							}
			}
		}
	}

	/**
	 * Train.
	 * 
	 * @param linear
	 *            the linear
	 * @param expand
	 *            are we expanding?
	 * @param depth
	 *            the depth to expand to
	 * @param playeda
	 *            the played
	 */
	public void train(StraightVertexLineariser linear, boolean playeda,
			boolean expand, int depth) {
		Node root = model.getRoot();
		boolean freeRideUsed = false;
		while (root != null && linear.hasNext()) {
			if (depth <= 0)
				expand = false;
			if (root.getNotPlayed() + root.getPlayed() < this
					.getMinBranchSize())
				expand = false;
			depth--;
			if (playeda)
				root.setPlayed(root.getPlayed() + 1);
			else
				root.setNotPlayed(root.getNotPlayed() + 1);
			Short colour = linear.next();
			boolean expandMore = !onlyOneNewNodePerSymmetry;

			switch (colour) {
			case BoardI.VERTEX_BLACK:
				freeRideUsed = true;
				if (root.getBlack() == null)
					if (expand) {
						root.setBlack(new Node());
						root = root.getBlack();
						expand = expandMore;
					} else
						return;
				else
					root = root.getBlack();
				break;
			case BoardI.VERTEX_WHITE:
				freeRideUsed = true;
				if (root.getWhite() == null)
					if (expand) {
						root.setWhite(new Node());
						root = root.getWhite();
						expand = expandMore;
					} else
						return;
				else
					root = root.getWhite();
				break;
			case BoardI.VERTEX_KO:
			case BoardI.VERTEX_EMPTY:
				if (root.getEmpty() == null)
					if (expand || (!freeRideUsed && freeRideForEmpty)) {
						root.setEmpty(new Node());
						expand = expandMore;
						root = root.getEmpty();
					} else
						return;
				else
					root = root.getEmpty();
				break;
			case BoardI.VERTEX_OFF_BOARD:
				if (root.getOff() == null)
					if (expand || (!freeRideUsed && freeRideForEmpty)) {
						root.setOff(new Node());
						expand = expandMore;
						root = root.getOff();
					} else
						return;
				else
					root = root.getOff();
				break;
			default:
				throw new Error();
			}
		}
	}

	/**
	 * get the model
	 * 
	 * @return the model
	 */
	public final Model getModel() {
		return model;
	}

	/**
	 * set the model
	 * 
	 * @param model
	 *            the model to set
	 */
	public final void setModel(Model model) {
		this.model = model;
	}

}
