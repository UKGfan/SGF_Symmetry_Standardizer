package jgogears.engine;

import java.io.*;
import java.util.*;

import jgogears.*;
import junit.framework.TestCase;

// TODO: Auto-generated Javadoc
/**
 * The Class ModelTest.
 */
public class ModelTest extends TestCase {

	/** are we being verbose debugging. */
	public static final boolean DEBUG = false;

	/** The model. */
	static Model model = null;

	boolean compare(double first, double second) {
		return ((float) first) == ((float) second);
	}

	/**
	 * Instantiates a new model test.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public ModelTest() throws IOException {
		if (model == null) {
			model = new Model();
			Trainer trainer = new Trainer();
			trainer.setModel(model);
			trainer.train(2);
		}
	}

	/**
	 * Load test game.
	 * 
	 * @return the game
	 */
	public Game loadTestGame() {
		try {
			FileReader reader = new FileReader(
					"sgf/testing/2007-01-12- BamboField- tokabe.sgf");
			jgogears.SGF.SGF parser = new jgogears.SGF.SGF(reader);
			SGFGameTree tree = parser.gameTree();
			Game game = new Game(tree);
			return game;
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}

	}

	/**
	 * Show tree helper.
	 * 
	 * @param indent
	 *            the indent
	 * @param root
	 *            the root
	 */
	private void showTreeHelper(int indent, Node root) {
		if (root.getBlack() != null) {
			Node child = root.getBlack();
			StringBuffer buf = new StringBuffer("ModelTest::showTree ");
			for (int i = 0; i < indent; i++)
				buf.append(" ");
			if (DEBUG)
				System.err.println(buf + " " + child.getPlayed() + "/"
						+ child.getNotPlayed() + "/"
						+ BoardI.colourString(BoardI.VERTEX_BLACK));
			this.showTreeHelper(indent + 1, child);

		}
		if (root.getWhite() != null) {
			Node child = root.getWhite();
			StringBuffer buf = new StringBuffer("ModelTest::showTree ");
			for (int i = 0; i < indent; i++)
				buf.append(" ");
			if (DEBUG)
				System.err.println(buf + " " + child.getPlayed() + "/"
						+ child.getNotPlayed() + "/"
						+ BoardI.colourString(BoardI.VERTEX_WHITE));
			this.showTreeHelper(indent + 1, child);

		}
		if (root.getEmpty() != null) {
			Node child = root.getEmpty();
			StringBuffer buf = new StringBuffer("ModelTest::showTree ");
			for (int i = 0; i < indent; i++)
				buf.append(" ");
			if (DEBUG)
				System.err.println(buf + " " + child.getPlayed() + "/"
						+ child.getNotPlayed() + "/"
						+ BoardI.colourString(BoardI.VERTEX_EMPTY));
			this.showTreeHelper(indent + 1, child);

		}
		if (root.getOff() != null) {
			Node child = root.getOff();
			StringBuffer buf = new StringBuffer("ModelTest::showTree ");
			for (int i = 0; i < indent; i++)
				buf.append(" ");
			if (DEBUG)
				System.err.println(buf + " " + child.getPlayed() + "/"
						+ child.getNotPlayed() + "/"
						+ BoardI.colourString(BoardI.VERTEX_OFF_BOARD));
			this.showTreeHelper(indent + 1, child);

		}

	}

	/**
	 * Test trained model.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void testEmptyModelEmptyBoard() throws IOException {
		Model model = new Model(); // yes, this overrides an instance variable
		assertNotNull(model);
		BoardI board = BoardI.newBoard();
		double[][] r = new Scorer().getScores(model, board, false);
		assertNotNull(r);
		assertTrue(r.length == r[0].length);
		if (DEBUG) {
			for (int i = 0; i < r.length; i++) {
				for (int j = 0; j < r[i].length; j++)
					System.err.print(" " + r[i][j]);
				System.err.println();
			}
		}
		for (int i = 0; i < r.length; i++)
			for (int j = 0; j < r[i].length; j++) {
				assertTrue(i + " " + j + " " + r[i][j] + " " + r[j][i],
						r[i][j] == r[j][i]);
				assertTrue(
						i + " " + j + " " + r[i][j] + " "
								+ r[r.length - i - 1][r.length - j - 1],
						this.compare(r[i][j], r[r.length - i - 1][r.length - j
								- 1]));
				assertTrue(i + " " + j + " " + r[i][j] + " "
						+ r[r.length - i - 1][j],
						this.compare(r[i][j], r[r.length - i - 1][j]));
				assertTrue(i + " " + j + " " + r[i][j] + " "
						+ r[i][r.length - j - 1],
						this.compare(r[i][j], r[i][r.length - j - 1]));
			}
	}

	/**
	 * Test load all sgf files. TODO make this quciker. it takes far too long
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void testLoadAllSGFFiles() throws IOException {

		BoardI board = BoardI.newBoard(19);
		board = board.newBoard(new Move("black a1"));
		board = board.newBoard(new Move("white b2"));
		board = board.newBoard(new Move("black b4"));
		board = board.newBoard(new Move("white c3"));
		board = board.newBoard(new Move("black c4"));
		board = board.newBoard(new Move("white d4"));
		board = board.newBoard(new Move("black a4"));
		board = board.newBoard(new Move("white d2"));
		double[][] r = new Scorer().getScores(model, board, false);
		assertNotNull(r);
		if (DEBUG)
			System.err.println("ModelTest::testLoadAllSGFFiles()  ");
		for (int i = 0; i < r.length; i++) {
			for (int j = 0; j < r[i].length; j++)
				if (DEBUG)
					System.err.print(" " + r[i][j]);
			if (DEBUG)
				System.err.println();
		}

		double maxScore = Double.MIN_NORMAL;
		Move maxMove = null;
		double minScore = Double.MAX_VALUE;
		Move minMove = null;
		for (int i = 0; i < r.length; i++)
			for (int j = 0; j < r[i].length; j++) {
				if (maxScore <= r[i][j]) {
					maxScore = r[i][j];
					maxMove = new Move(i, j, BoardI.VERTEX_BLACK);
				}
				if (minScore >= r[i][j]) {
					minScore = r[i][j];
					minMove = new Move(i, j, BoardI.VERTEX_BLACK);
				}
			}
		if (DEBUG) {
			System.err.println("MaxScore = " + maxScore + ", " + maxMove);
			System.err.println("MinScore = " + minScore + ", " + minMove);
		}
	}

	/**
	 * check some stats on some files.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void testShowTree() throws IOException {
		Node root = model.getRoot();
		assertNotNull(root);
		if (DEBUG)
			System.err.println("ModelTest tree:");
		// this.showTreeHelper(0, root);

	}

	/**
	 * Test trained model.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void testTrainedModel() throws IOException {
		assertNotNull(model);
		if (DEBUG)
			System.err.println("ModelTest::testTrainedModel() model size =  "
					+ model.getRoot().size());
		Trainer trainer = new Trainer();
		trainer.setModel(model);
		trainer.train(this.loadTestGame());

		if (DEBUG)
			System.err.println("ModelTest::testTrainedModel() model size =  "
					+ model.getRoot().size());
		BoardI board = BoardI.newBoard(19);
		board = board.newBoard(new Move("white b2"));
		board = board.newBoard(new Move("black k4"));
		board = board.newBoard(new Move("white c3"));
		board = board.newBoard(new Move("black g4"));
		board = board.newBoard(new Move("white d4"));
		board = board.newBoard(new Move("black h4"));
		board = board.newBoard(new Move("white n4"));
		double[][] r = new Scorer().getScores(model, board, false);
		assertNotNull(r);
		if (DEBUG) {
			System.err.println("ModeTest::testTrainedModel() Double.MIN_VALUE "
					+ Double.MIN_VALUE);
			for (int i = 0; i < r.length; i++) {
				for (int j = 0; j < r[i].length; j++)
					System.err.print(" " + r[i][j]);
				System.err.println();
			}
		}
	}

	/**
	 * This is currently failing because of lack of symmetry.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void testTrainedModelEmptyBoard() throws IOException {
		assertNotNull(model);

		if (DEBUG)
			System.err
					.println("ModeTest::testTrainedModelEmptyBoard() model size =  "
							+ model.getRoot().size());
		Trainer trainer = new Trainer();
		trainer.setModel(model);
		trainer.train(this.loadTestGame());

		if (DEBUG)
			System.err
					.println("ModeTest::testTrainedModelEmptyBoard() model size =  "
							+ model.getRoot().size());
		BoardI board = BoardI.newBoard(19);
		double[][] r = new Scorer().getScores(model, board, false);
		assertNotNull(r);
		assertTrue(r.length == r[0].length);
		if (DEBUG) {
			System.err
					.println("ModeTest::testTrainedModelEmptyBoard() Double.MIN_VALUE "
							+ Double.MIN_VALUE);
			for (int i = 0; i < r.length; i++) {
				for (int j = 0; j < r[i].length; j++)
					System.err.print(" " + r[i][j]);
				System.err.println();
			}
		}
		for (int i = 0; i < r.length; i++)
			for (int j = 0; j < r[i].length; j++) {
				// assertTrue(i + " " + j + " " + r[i][j] + " " + r[j][i],
				// r[i][j] == r[j][i]);
				assertTrue(
						i + " " + j + " " + r[i][j] + " "
								+ r[r.length - i - 1][r.length - j - 1],
						this.compare(r[i][j], r[r.length - i - 1][r.length - j
								- 1]));
				assertTrue(i + " " + j + " " + r[i][j] + " "
						+ r[r.length - i - 1][j],
						this.compare(r[i][j], r[r.length - i - 1][j]));
				assertTrue(i + " " + j + " " + r[i][j] + " "
						+ r[i][r.length - j - 1],
						this.compare(r[i][j], r[i][r.length - j - 1]));
			}
	}

	/**
	 * check some stats on some files.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void testTreeWellFormedness() throws IOException {
		// generate
		int nodeCount = 0;
		int leafCount = 0;
		int fullCount = 0;
		int countSum = 0;
		Stack<Node> nodes = new Stack<Node>();
		nodes.push(model.getRoot());
		while (nodes.size() > 0) {
			Node node = nodes.pop();
			if (node != null) {
				nodeCount++;
				assertTrue(node.getBlack() != node);
				assertTrue(node.getWhite() != node);
				assertTrue(node.getEmpty() != node);
				assertTrue(node.getOff() != node);
				if (node.getBlack() != null && node.getWhite() != null
						&& node.getEmpty() != null && node.getOff() != null)
					fullCount++;
				if (node.getBlack() == null && node.getWhite() == null
						&& node.getEmpty() == null && node.getOff() == null)
					leafCount++;
				countSum += node.getPlayed();
			}
		}
		if (DEBUG)
			System.err.println("ModelTest::testTreeWellFormedness()  nodes="
					+ nodeCount + " leaves=" + leafCount + " full=" + fullCount
					+ " countSum=" + countSum + " countAvg =" + countSum
					/ nodeCount + " ");

	}

	/**
	 * check that there are no loops in the tree.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void testTreeWellFormednessII() throws IOException {
		Stack<Node> nodes = new Stack<Node>();
		Set<Node> nodeSet = new TreeSet<Node>();
		nodes.push(model.getRoot());
		while (nodes.size() > 0) {
			Node node = nodes.pop();
			if (node != null) {
				assertFalse(nodeSet.contains(node));
				nodeSet.add(node);
				if (node.getBlack() != null) {
					nodes.push(node.getBlack());
				}
				if (node.getWhite() != null) {
					nodes.push(node.getWhite());
				}
				if (node.getEmpty() != null) {
					nodes.push(node.getEmpty());
				}
				if (node.getOff() != null) {
					nodes.push(node.getOff());
				}
			}
		}
	}

	/**
	 * Test trivial.
	 */
	public void testTrivial() {
		model = new Model();
		assertNotNull(model);
	}

}
