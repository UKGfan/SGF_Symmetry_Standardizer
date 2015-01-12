package jgogears.engine;

import java.io.StringReader;
import java.util.Iterator;

import jgogears.*;
import junit.framework.TestCase;

// TODO: Auto-generated Javadoc
/**
 * The Class VertexLineariserTest.
 */
public class StraightVertexLineariserTest extends TestCase {

	/** Are we using verbose debugging?. */
	public static final boolean DEBUG = false;

	/**
	 * Identical linearisation.
	 * 
	 * @param board
	 *            the board
	 * @param rowa
	 *            the rowa
	 * @param columna
	 *            the columna
	 * @param rowb
	 *            the rowb
	 * @param columnb
	 *            the columnb
	 * @return true, if successful
	 */
	boolean identicalLinearisation(BoardI board, short rowa, short columna,
			short rowb, short columnb) {
		for (short j = 0; j < 8; j++) {
			boolean matchFound = false;
			for (short i = 0; i < 8; i++) {
				if (i != j) {
					boolean differenceFound = false;
					Iterator<Short> lineara = new StraightVertexLineariser(
							board, rowa, columna, j, false);
					Iterator<Short> linearb = new StraightVertexLineariser(
							board, rowb, columnb, i, false);
					assertTrue(lineara != null);
					assertTrue(linearb != null);
					while (lineara.hasNext() && linearb.hasNext()
							&& !differenceFound) {
						Short a = lineara.next();
						Short b = linearb.next();
						assertTrue(a != null);
						assertTrue(b != null);
						if (a == null || b == null || !a.equals(b))
							differenceFound = true;
					}
					if (!differenceFound)
						matchFound = true;
				}
			}
			if (!matchFound) {
				if (DEBUG)
					System.err.println(rowa + " " + columna + " " + rowb + " "
							+ columnb + " " + j);
				return false;
			}
		}
		return true;
	}

	/**
	 * make sure that all the linearisations are different.
	 */
	public void testAllDifferent() {
		BoardI board = BoardI.newBoard();
		assertNotNull(board);
		board = board.newBoard(new Move("white b2"));
		board = board.newBoard(new Move("black k4"));
		board = board.newBoard(new Move("white c3"));
		board = board.newBoard(new Move("black g4"));
		board = board.newBoard(new Move("white d4"));
		board = board.newBoard(new Move("black h4"));
		board = board.newBoard(new Move("white n4"));
		board = board.newBoard(new Move("black b4"));
		board = board.newBoard(new Move("white c4"));
		board = board.newBoard(new Move("white n5"));
		board = board.newBoard(new Move("black b5"));
		board = board.newBoard(new Move("white c5"));

		assertNotNull(board);
		for (short row = 2; row < board.getSize() - 2; row++)
			for (short column = 2; column < board.getSize() - 2; column++)
				for (short j = 0; j < 8; j++)
					for (short i = 0; i < 8; i++) {
						if (i != j) {
							Iterator<Short> lineara = new StraightVertexLineariser(
									board, row, column, j, false);
							Iterator<Short> linearb = new StraightVertexLineariser(
									board, row, column, i, false);
							assertTrue(lineara != null);
							assertTrue(linearb != null);
							boolean differenceFound = false;
							while (lineara.hasNext() && linearb.hasNext()) {
								Short a = lineara.next();
								Short b = linearb.next();
								assertTrue(a != null);
								assertTrue(b != null);
								if (a == null || b == null)
									throw new Error();
								if (!a.equals(b))
									differenceFound = true;
							}
							assertTrue(i
									+ " "
									+ j
									+ " "
									+ row
									+ " "
									+ column
									+ "\n"
									+ new StraightVertexLineariser(board, row,
											column, j, false)
									+ "\n"
									+ new StraightVertexLineariser(board, row,
											column, i, false), differenceFound);
						}
					}

	}

	/**
	 * make sure that all the linearisations are different.
	 */
	public void testAllDifferentII() {
		BoardI board = BoardI.newBoard();
		assertNotNull(board);
		board = board.newBoard(new Move("white b1"));

		assertNotNull(board);
		for (short j = 0; j < 8; j++)
			for (short i = 0; i < 8; i++) {
				if (i != j) {
					Iterator<Short> lineara = new StraightVertexLineariser(
							board, (short) 2, (short) 2, j, false);
					Iterator<Short> linearb = new StraightVertexLineariser(
							board, (short) 2, (short) 2, i, false);
					assertTrue(lineara != null);
					assertTrue(linearb != null);
					boolean differenceFound = false;
					while (lineara.hasNext() && linearb.hasNext()) {
						Short a = lineara.next();
						Short b = linearb.next();
						assertTrue(a != null);
						assertTrue(b != null);
						if (a == null || b == null)
							throw new Error();
						if (!a.equals(b))
							differenceFound = true;
					}
					assertTrue(i + " " + j, differenceFound);
				}
			}

	}

	/**
	 * make sure that all the linearisations are different.
	 */
	public void testBoardFalseSymmetryI() {
		BoardI board = BoardI.newBoard();
		assertNotNull(board);
		board = board.newBoard(new Move("white l10"));
		board = board.newBoard(new Move("black a1"));
		board = board.newBoard(new Move("white k10"));
		board = board.newBoard(new Move("black g18"));
		if (DEBUG)
			System.err.println(board);

		assertNotNull(board);
		for (short row = 0; row < board.getSize(); row++)
			for (short column = 0; column < board.getSize(); column++) {
				if (DEBUG)
					for (short j = 0; j < 8; j++) {
						Iterator<Short> linear = new StraightVertexLineariser(
								board, row, column, j, false);
						assertTrue(linear != null);
						while (linear.hasNext()) {
							Short s = linear.next();
							assertTrue(s != null);
							System.err.print(" " + s + ", ");
						}
						System.err.println();
					}
				if (DEBUG)
					for (short j = 0; j < 8; j++) {
						Iterator<Short> linear = new StraightVertexLineariser(
								board, column, row, j, false);
						assertTrue(linear != null);
						while (linear.hasNext()) {
							Short s = linear.next();
							assertTrue(s != null);
							System.err.print(" " + s + ", ");
						}
						System.err.println();
					}

				boolean result;
				result = this.identicalLinearisation(board, row, column,
						column, row);
				assertFalse(result);
			}
	}

	/**
	 * make sure that all the linearisations are different.
	 */
	public void testBoardFalseSymmetryII() {
		BoardI board = BoardI.newBoard();
		assertNotNull(board);
		board = board.newBoard(new Move("white l10"));
		board = board.newBoard(new Move("black a1"));
		board = board.newBoard(new Move("white k10"));
		board = board.newBoard(new Move("black g18"));
		if (DEBUG)
			System.err.println(board);

		assertNotNull(board);
		for (short row = 0; row < board.getSize(); row++)
			for (short column = 0; column < board.getSize(); column++) {

				boolean result;
				result = this.identicalLinearisation(board, row, column,
						(short) (board.getSize() - row - 1),
						(short) (board.getSize() - column - 1));
				assertFalse(result);
			}
	}

	/**
	 * make sure that all the linearisations are different.
	 */
	public void testBoardSymmetryI() {
		BoardI board = BoardI.newBoard();
		assertNotNull(board);
		board = board.newBoard(new Move("white k10"));
		if (DEBUG)
			System.err.println(board);

		assertNotNull(board);
		for (short row = 0; row < board.getSize(); row++)
			for (short column = 0; column < board.getSize(); column++) {
				boolean result;
				result = this.identicalLinearisation(board, row, column,
						column, row);
				assertTrue(result);
			}
	}

	/**
	 * make sure that all the linearisations are different.
	 */
	public void testBoardSymmetryII() {
		BoardI board = BoardI.newBoard();
		assertNotNull(board);
		board = board.newBoard(new Move("white k10"));
		if (DEBUG)
			System.err.println(board);

		assertNotNull(board);
		for (short row = 0; row < board.getSize(); row++)
			for (short column = 0; column < board.getSize(); column++) {
				boolean result;
				result = this.identicalLinearisation(board, row, column,
						(short) (board.getSize() - row - 1),
						(short) (board.getSize() - column - 1));
				assertTrue(result);
			}
	}

	/**
	 * test what happens when linearising boards of different sizes.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public void testDifferenetSizes13() throws Exception {
		BoardI board = BoardI.newBoard();
		StraightVertexLineariser linear = new StraightVertexLineariser(board,
				(short) 2, (short) 2, (short) 0, false);
		assertNotNull(linear);
		board = BoardI.newBoard(13);
		linear = new StraightVertexLineariser(board, (short) 2, (short) 2,
				(short) 0, false);
		int count = 0;
		while (linear.hasNext()) {
			Short s = linear.next();
			assertNotNull(s);
			count++;
		}
		int plussize = (board.getSize() + 2) * (board.getSize() + 2);

		assertTrue(count + "/" + plussize + 1, count == plussize + 1);
	}

	/**
	 * test what happens when linearising boards of different sizes.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public void testDifferenetSizes9() throws Exception {
		BoardI board = BoardI.newBoard();
		StraightVertexLineariser linear = new StraightVertexLineariser(board,
				(short) 2, (short) 2, (short) 0, false);
		assertNotNull(linear);
		board = BoardI.newBoard(9);
		linear = new StraightVertexLineariser(board, (short) 2, (short) 2,
				(short) 0, false);
		int count = 0;
		while (linear.hasNext()) {
			Short s = linear.next();
			assertNotNull(s);
			count++;
		}
		int plussize = (board.getSize() + 2) * (board.getSize() + 2);

		assertTrue(count + "/" + plussize + 1, count == plussize + 1);
	}

	/**
	 * test what happens when linearising boards of different sizes.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public void testDifferenetSizes11() throws Exception {
		BoardI board = BoardI.newBoard();
		StraightVertexLineariser linear = new StraightVertexLineariser(board,
				(short) 2, (short) 2, (short) 0, false);
		assertNotNull(linear);
		board = BoardI.newBoard(11);
		linear = new StraightVertexLineariser(board, (short) 2, (short) 2,
				(short) 0, false);
		int count = 0;
		while (linear.hasNext()) {
			Short s = linear.next();
			assertNotNull(s);
			count++;
		}
		int plussize = (board.getSize() + 2) * (board.getSize() + 2);

		assertTrue(count + "/" + plussize + 1, count == plussize + 1);
	}

	/**
	 * test what happens when linearising boards of different sizes.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public void testDifferenetSizes10() throws Exception {
		BoardI board = BoardI.newBoard();
		StraightVertexLineariser linear = new StraightVertexLineariser(board,
				(short) 2, (short) 2, (short) 0, false);
		assertNotNull(linear);
		board = BoardI.newBoard(10);
		linear = new StraightVertexLineariser(board, (short) 2, (short) 2,
				(short) 0, false);
		int count = 0;
		while (linear.hasNext()) {
			Short s = linear.next();
			assertNotNull(s);
			count++;
		}
		int plussize = (board.getSize() + 2) * (board.getSize() + 2);

		assertTrue(count + "/" + plussize + 1, count == plussize + 1);
	}

	/**
	 * test what happens when linearising boards of different sizes.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public void testDifferenetSizes21() throws Exception {
		BoardI board = BoardI.newBoard();
		StraightVertexLineariser linear = new StraightVertexLineariser(board,
				(short) 2, (short) 2, (short) 0, false);
		assertNotNull(linear);
		board = BoardI.newBoard(21);
		linear = new StraightVertexLineariser(board, (short) 2, (short) 2,
				(short) 0, false);
		int count = 0;
		while (linear.hasNext()) {
			Short s = linear.next();
			assertNotNull(s);
			count++;
		}
		int plussize = (board.getSize() + 2) * (board.getSize() + 2);

		assertTrue(count + "/" + plussize + 1, count == plussize + 1);
	}

	/**
	 * test what happens when linearising boards of different sizes.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public void testDifferenetSizes17() throws Exception {
		BoardI board = BoardI.newBoard();
		StraightVertexLineariser linear = new StraightVertexLineariser(board,
				(short) 2, (short) 2, (short) 0, false);
		assertNotNull(linear);
		board = BoardI.newBoard(17);
		linear = new StraightVertexLineariser(board, (short) 2, (short) 2,
				(short) 0, false);
		int count = 0;
		while (linear.hasNext()) {
			Short s = linear.next();
			assertNotNull(s);
			count++;
		}
		int plussize = (board.getSize() + 2) * (board.getSize() + 2);

		assertTrue(count + "/" + plussize + 1, count == plussize + 1);
	}

	/**
	 * make sure that all the linearisations are different.
	 */
	public void testEmptyBoardSymmetryI() {
		BoardI board = BoardI.newBoard();
		assertNotNull(board);

		for (short row = 0; row < board.getSize(); row++)
			for (short column = 0; column < board.getSize(); column++) {
				for (short j = 0; j < 8; j++) {
					Iterator<Short> linear = new StraightVertexLineariser(
							board, row, column, j, false);
					while (linear.hasNext()) {
						Short s = linear.next();
						if (DEBUG)
							System.err.print(" " + s + ", ");
					}
					if (DEBUG)
						System.err.println();
				}
				if (DEBUG)
					System.err.println();
				for (short j = 0; j < 8; j++) {
					Iterator<Short> linear = new StraightVertexLineariser(
							board, (short) (board.getSize() - row - 1),
							(short) (board.getSize() - column - 1), j, false);
					while (linear.hasNext()) {
						Short s = linear.next();
						if (DEBUG)
							System.err.print(" " + s + ", ");
					}
					if (DEBUG)
						System.err.println();
				}
				if (DEBUG)
					System.err.println();
				if (DEBUG)
					System.err.println();

				boolean result;
				result = this.identicalLinearisation(board, row, column,
						(short) (board.getSize() - row - 1),
						(short) (board.getSize() - column - 1));
				assertTrue(result);
			}
	}

	/**
	 * make sure that all the linearisations are different.
	 */
	public void testEmptyBoardSymmetryII() {
		BoardI board = BoardI.newBoard();
		assertNotNull(board);

		for (short row = 0; row < board.getSize(); row++)
			for (short column = 0; column < board.getSize(); column++) {
				boolean result;
				result = this.identicalLinearisation(board, row, column,
						column, row);
				assertTrue(result);
			}
	}

	/**
	 * make sure that all the linearisations are different.
	 */
	public void testEmptyBoardSymmetryReimplematation() {
		BoardI board = BoardI.newBoard();
		short size = board.getSize();
		boolean matches[][][] = new boolean[3][8][8];

		for (short sym1 = 0; sym1 < 8; sym1++) {

			for (short sym2 = 0; sym2 < 8; sym2++) {
				matches[0][sym1][sym2] = true;
				Iterator<Short> lineara = new StraightVertexLineariser(board,
						(short) 0, (short) 0, sym1, false);
				Iterator<Short> linearb = new StraightVertexLineariser(board,
						(short) (size - 1), (short) (size - 1), sym2, false);
				while (lineara.hasNext()) {
					assertTrue(lineara.hasNext());
					assertTrue(linearb.hasNext());
					Short a = lineara.next();
					Short b = linearb.next();
					if (!a.equals(b))
						matches[0][sym1][sym2] = false;
				}
				assertFalse(linearb.hasNext());
			}

			for (short sym2 = 0; sym2 < 8; sym2++) {
				matches[1][sym1][sym2] = true;
				Iterator<Short> lineara = new StraightVertexLineariser(board,
						(short) 0, (short) 0, sym1, false);
				Iterator<Short> linearb = new StraightVertexLineariser(board,
						(short) 0, (short) (size - 1), sym2, false);
				while (lineara.hasNext()) {
					assertTrue(lineara.hasNext());
					assertTrue(linearb.hasNext());
					Short a = lineara.next();
					Short b = linearb.next();
					if (!a.equals(b))
						matches[1][sym1][sym2] = false;
				}
				assertFalse(linearb.hasNext());
			}

			for (short sym2 = 0; sym2 < 8; sym2++) {
				matches[2][sym1][sym2] = true;
				Iterator<Short> lineara = new StraightVertexLineariser(board,
						(short) 0, (short) 0, sym1, false);
				Iterator<Short> linearb = new StraightVertexLineariser(board,
						(short) (size - 1), (short) 0, sym2, false);
				while (lineara.hasNext()) {
					assertTrue(lineara.hasNext());
					assertTrue(linearb.hasNext());
					Short a = lineara.next();
					Short b = linearb.next();
					if (!a.equals(b))
						matches[2][sym1][sym2] = false;
				}
				assertFalse(linearb.hasNext());
			}
		}
		for (short corn = 0; corn < 3; corn++) {
			for (short sym1 = 0; sym1 < 8; sym1++) {
				boolean tfound = false;
				boolean ffound = false;
				for (short sym2 = 0; sym2 < 8; sym2++) {
					if (DEBUG)
						System.err.print(matches[corn][sym1][sym2] + " ");
					if (matches[corn][sym1][sym2])
						tfound = true;
					else
						ffound = true;
				}
				assertTrue(tfound);
				assertTrue(ffound);
				if (DEBUG)
					System.err.println();
			}
			if (DEBUG)
				System.err.println();
		}
	}

	/**
	 * Test everything.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public void testEverything() throws Exception {

		String examples[] = {
				// SGFParser.EXAMPLEONE,
				SGFParser.EXAMPLETWO, SGFParser.EXAMPLETHREE,
				SGFParser.EXAMPLEFOUR, SGFParser.EXAMPLEFIVE };

		for (int i = 0; i < examples.length; i++) {
			String example = examples[i];

			StringReader reader = new StringReader(example);
			jgogears.SGF.SGF parser = new jgogears.SGF.SGF(reader);
			SGFGameTree tree = parser.gameTree();

			assertTrue(parser != null);
			assertTrue(parser.toString() != null);
			assertTrue(parser.toString().length() > 0);

			assertTrue(tree != null);
			if (tree == null)
				throw new Error();
			assertTrue(tree.toString() != null);
			assertTrue(tree.toString().length() > 0);

			Game game = new Game(tree);
			assertTrue(game != null);

			Iterator<BoardI> iterator = game.getBoards();
			assertTrue(iterator != null);
			if (iterator == null)
				throw new Error();
			BoardI board = null;
			while (iterator.hasNext()) {
				board = iterator.next();
				assertTrue(board != null);
				for (short j = 0; j < 8; j++) {
					Iterator<Short> linear = new StraightVertexLineariser(
							board, (short) 2, (short) 2, j, false);
					assertTrue(linear != null);
					while (linear.hasNext()) {
						Short s = linear.next();
						assertTrue(s != null);
						// System.out.print(" " + s + ", ");
					}
				}
				// System.out.println();
			}

		}

	}

	/**
	 * make sure that all the linearisations are different.
	 */
	public void testFirst() {
		BoardI board = BoardI.newBoard();
		assertNotNull(board);
		board = board.newBoard(new Move("white b2"));

		assertNotNull(board);

		if (DEBUG)
			System.err.print("VertexLineariserTest::testFirst() Black = "
					+ BoardI.parseColour("black"));
		if (DEBUG)
			System.err.print(" White = " + BoardI.parseColour("white"));
		if (DEBUG)
			System.err.print(" Empty = " + BoardI.VERTEX_EMPTY);
		if (DEBUG)
			System.err.print(" Off = " + BoardI.VERTEX_OFF_BOARD);
		if (DEBUG)
			System.err.println("");
		for (short j = 0; j < 8; j++) {
			Iterator<Short> linear = new StraightVertexLineariser(board,
					(short) 1, (short) 1, j, false);
			assertTrue(linear != null);
			while (linear.hasNext()) {
				Short s = linear.next();
				assertTrue(s != null);
				if (DEBUG)
					System.err.print(" " + s + ", ");
			}
			if (DEBUG)
				System.err.println();
		}

		for (short j = 0; j < 8; j++) {
			if (DEBUG)
				System.err
						.println("StraightVertexLineariserTest::testFirst sym="
								+ j);
			Iterator<Short> lineara = new StraightVertexLineariser(board,
					(short) 1, (short) 1, j, true);
			assertTrue(lineara != null);
			Short a = lineara.next();
			assertTrue(a != null);
			if (a == null)
				throw new Error();
			assertTrue(a + " " + BoardI.VERTEX_WHITE + "\n" + board,
					a.equals(new Short(BoardI.VERTEX_WHITE)));
		}
	}

	/**
	 * Test first all.
	 */
	public void testFirstAll() {
		BoardI board = BoardI.newBoard();
		assertNotNull(board);
		board = board.newBoard(new Move("white b2"));

		assertNotNull(board);

		if (DEBUG)
			System.err.print("VertexLineariserTest::testFirst() Black = "
					+ BoardI.parseColour("black"));
		if (DEBUG)
			System.err.print(" White = " + BoardI.parseColour("white"));
		if (DEBUG)
			System.err.print(" Empty = " + BoardI.VERTEX_EMPTY);
		if (DEBUG)
			System.err.print(" Off = " + BoardI.VERTEX_OFF_BOARD);
		if (DEBUG)
			System.err.println("");
		for (short row = 0; row < board.getSize(); row++)
			for (short column = 0; column < board.getSize(); column++)
				for (short j = 0; j < 8; j++) {
					if (DEBUG)
						System.err
								.println("StraightVertexLineariserTest::testFirst sym="
										+ j);
					Iterator<Short> lineara = new StraightVertexLineariser(
							board, row, column, j, true);
					assertTrue(lineara != null);
					Short a = lineara.next();
					assertTrue(a != null);
					if (a == null)
						throw new Error();
					assertTrue(a + " " + board.getColour(row, column) + "\n"
							+ board,
							a.equals(new Short(board.getColour(row, column))));
				}
	}

	/**
	 * Test trained model.
	 */
	public void testSize() {
		BoardI board = BoardI.newBoard();
		assertNotNull(board);
		int count = 0;
		Iterator<Short> linear = new StraightVertexLineariser(board, (short) 2,
				(short) 2, (short) 0, false);
		while (linear.hasNext()) {
			Short s = linear.next();
			assertNotNull(s);
			count++;
		}
		int plussize = StraightVertexLineariser.SIZE
				* StraightVertexLineariser.SIZE;

		assertTrue(count + "/" + plussize + 1, count == plussize + 1);
	}

	/**
	 * Test trained model.
	 */
	public void testVerbose() {
		BoardI board = BoardI.newBoard();
		assertNotNull(board);
		board = board.newBoard(new Move("white b2"));
		board = board.newBoard(new Move("black k4"));
		board = board.newBoard(new Move("white c3"));
		board = board.newBoard(new Move("black g4"));
		board = board.newBoard(new Move("white d4"));
		board = board.newBoard(new Move("black h4"));
		board = board.newBoard(new Move("white n4"));

		assertNotNull(board);
		if (DEBUG)
			System.err
					.print("StraightVertexLineariserTest::testVertexLineariser() Black = "
							+ BoardI.parseColour("black"));
		if (DEBUG)
			System.err.print(" White = " + BoardI.parseColour("white"));
		if (DEBUG)
			System.err.print(" Empty = " + BoardI.VERTEX_EMPTY);
		if (DEBUG)
			System.err.print(" Off = " + BoardI.VERTEX_OFF_BOARD);
		if (DEBUG)
			System.err.println("");
		for (short j = 0; j < 8; j++) {
			Iterator<Short> linear = new StraightVertexLineariser(board,
					(short) 2, (short) 2, j, false);
			assertTrue(linear != null);
			while (linear.hasNext()) {
				Short s = linear.next();
				assertTrue(s != null);
				if (DEBUG)
					System.err.print(" " + s + ", ");
			}
			if (DEBUG)
				System.err.println();
		}

	}

	/**
	 * Test trained model.
	 */
	public void testVerboseII() {
		BoardI board = BoardI.newBoard();
		assertNotNull(board);
		board = board.newBoard(new Move("white b2"));
		board = board.newBoard(new Move("black c3"));
		board = board.newBoard(new Move("white a3"));
		board = board.newBoard(new Move("black g4"));
		board = board.newBoard(new Move("white d4"));
		board = board.newBoard(new Move("black h4"));
		board = board.newBoard(new Move("white n4"));

		for (short j = 0; j < 8; j++) {
			Iterator<Short> linear = new StraightVertexLineariser(board,
					(short) 2, (short) 2, j, false);
			assertTrue(linear != null);
			if (DEBUG)
				System.err.println(linear);
			linear = new StraightVertexLineariser(board, (short) 2, (short) 2,
					j, false);
		}

	}

	/**
	 * Test initialised master
	 */
	public void testMaster() {
		BoardI board = BoardI.newBoard();
		assertNotNull(board);

		Iterator<Short> linear = new StraightVertexLineariser(board, (short) 2,
				(short) 2, (short) 0, false);
		assertTrue(linear != null);
		for (short j = 0; j < 8; j++) {
			int totalboardsizea = board.getSize() * board.getSize();
			int totalboardsizeb = board.getSize() * board.getSize();
			int totalboardsizec = board.getSize() * board.getSize();
			int totalboardsized = board.getSize() * board.getSize();
			assertTrue(StraightVertexLineariser.master[j][0].length == StraightVertexLineariser.SEQUENCE_SIZE);
			for (short seq = 0; seq < StraightVertexLineariser.master[j][0].length; seq++) {
				if (DEBUG && seq < 9)
					System.err
							.print("{"
									+ StraightVertexLineariser.master[j][0][seq]
									+ ","
									+ StraightVertexLineariser.master[j][1][seq]
									+ "},");
				if (board.getColour(StraightVertexLineariser.master[j][0][seq],
						StraightVertexLineariser.master[j][1][seq]) == Board.VERTEX_EMPTY)
					totalboardsizea--;
				if (board.getColour(
						StraightVertexLineariser.master[j][0][seq] + 18,
						StraightVertexLineariser.master[j][1][seq]) == Board.VERTEX_EMPTY)
					totalboardsizeb--;
				if (board.getColour(StraightVertexLineariser.master[j][0][seq],
						StraightVertexLineariser.master[j][1][seq] + 18) == Board.VERTEX_EMPTY)
					totalboardsizec--;
				if (board.getColour(
						StraightVertexLineariser.master[j][0][seq] + 18,
						StraightVertexLineariser.master[j][1][seq] + 18) == Board.VERTEX_EMPTY)
					totalboardsized--;
			}
			if (DEBUG)
				System.err.println();
			assertTrue(totalboardsizea == 0);
			assertTrue(totalboardsizeb == 0);
			assertTrue(totalboardsizec == 0);
			assertTrue(totalboardsized == 0);
		}
	}

	/**
	 * Test initialised master
	 */
	public void testMasterII() {
		BoardI board = BoardI.newBoard();
		assertNotNull(board);
		// this call makes sure that the master is initialised
		Iterator<Short> linear = new StraightVertexLineariser(board, (short) 2,
				(short) 2, (short) 0, false);
		assertTrue(linear != null);

		for (short row = 0; row < board.getSize(); row++)
			for (short column = 0; column < board.getSize(); column++)
				for (short j = 0; j < 8; j++) {
					boolean found = false;
					for (short seq = 0; seq < StraightVertexLineariser.master[j][0].length
							&& found == false; seq++)
						if (StraightVertexLineariser.master[j][0][seq] == row
								&& StraightVertexLineariser.master[j][1][seq] == column
								&& found == false)
							found = true;
					assertTrue("" + row + " " + column + " " + j, found);
				}
	}

	/**
	 * Test initialised master
	 */
	public void testContants() {
		BoardI board = BoardI.newBoard();
		assertNotNull(board);
		// this call makes sure that the master is initialised
		Iterator<Short> linear = new StraightVertexLineariser(board, (short) 2,
				(short) 2, (short) 0, false);
		assertTrue(linear != null);
		if (DEBUG)
			System.err.println("BOARD_SIZE="
					+ StraightVertexLineariser.BOARD_SIZE + " SIZE="
					+ StraightVertexLineariser.SIZE + " SEQUENCE_SIZE="
					+ StraightVertexLineariser.SEQUENCE_SIZE);
		if (DEBUG)
			System.err.println("master[this.sym][0].length="
					+ StraightVertexLineariser.master[0][0].length);
	}
}
