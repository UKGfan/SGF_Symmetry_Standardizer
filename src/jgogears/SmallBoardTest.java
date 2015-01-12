package jgogears;

import java.io.*;
import java.util.*;

import junit.framework.TestCase;

// TODO: Auto-generated Javadoc
/**
 * The Class SmallBoardTest.
 */
public class SmallBoardTest extends TestCase {

	/**
	 * Test all vertexes on a board of size N.
	 * 
	 * @param size
	 *            the size
	 */
	public void testAllVertexesN(int size) {
		BoardI board = new SmallBoard(size);

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				Move move = new Move(i, j, BoardI.VERTEX_BLACK);
				board = board.newBoard(move);
				assertTrue(board.getColour(i, j) == BoardI.VERTEX_BLACK);

				for (int l = 0; l < size; l++) {
					for (int m = 0; m < size; m++) {
						if (l != i && m != j) {
							assertTrue(board.getColour(l, m) == BoardI.VERTEX_EMPTY);
							assertTrue(BoardI.VERTEX_EMPTY == BoardI.VERTEX_EMPTY);
							assertTrue(board.getColour(l, m) == board
									.getColour(l, m));
							assertTrue(board.getColour(l, m) == BoardI.VERTEX_EMPTY);
							// assertTrue(board.getColour(l, m) ==
							// SmallBoard.VERTEX_EMPTY);
							assertTrue(
									"" + size + "," + i + "," + j + "," + l
											+ "," + m + ","
											+ board.getColour(l, m) + ","
											+ BoardI.VERTEX_EMPTY,
									board.getColour(l, m) == BoardI.VERTEX_EMPTY);
						}
					}
				}
				move = new Move(i, j, BoardI.VERTEX_EMPTY);
				board = board.newBoard(move);
				assertTrue(board.getColour(i, j) == BoardI.VERTEX_EMPTY);

				for (int l = 0; l < size; l++) {
					for (int m = 0; m < size; m++) {
						if (l != i && m != j) {
							assertTrue(board.getColour(i, j) == BoardI.VERTEX_EMPTY);
						}
					}
				}
				move = new Move(i, j, BoardI.VERTEX_WHITE);
				board = board.newBoard(move);
				assertTrue(board.getColour(i, j) == BoardI.VERTEX_WHITE);

				for (int l = 0; l < size; l++) {
					for (int m = 0; m < size; m++) {
						if (l != i && m != j) {
							assertTrue(board.getColour(l, m) == BoardI.VERTEX_EMPTY);
						}
					}
				}
				move = new Move(i, j, BoardI.VERTEX_KO);
				board = board.newBoard(move);
				assertTrue(board.getColour(i, j) == BoardI.VERTEX_KO);

				for (int l = 0; l < size; l++) {
					for (int m = 0; m < size; m++) {
						if (l != i && m != j) {
							assertTrue(board.getColour(l, m) == BoardI.VERTEX_EMPTY);
						}
					}
				}
				move = new Move(i, j, BoardI.VERTEX_EMPTY);
				board = board.newBoard(move);
				assertTrue(board.getColour(i, j) == BoardI.VERTEX_EMPTY);

				for (int l = 0; l < size; l++) {
					for (int m = 0; m < size; m++) {
						if (l != i && m != j) {
							assertTrue(board.getColour(l, m) == BoardI.VERTEX_EMPTY);
						}
					}
				}
			}
		}
	}

	/**
	 * Test load all SGF files.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void testLoadAllSGFFiles() throws IOException {
		Stack<String> files = new Stack<String>();
		files.push("sgf/2004-12");

		while (files.size() > 0) {
			String filename = files.pop();
			File file = new File(filename);
			// System.err.println("examining \"" + filename + "\"");
			if (file.exists()) {
				if (!file.isDirectory()) {
					// System.err.println("\"" + filename + "\" is not a
					// directory, parsing as an SGF file");

					Game game = Game.loadFromFile(file);
					if (game.getSize() == 19) {
						Iterator<Move> i = game.getMoves();
						Move move = null;
						BoardI board = new SmallBoard(game.getSize());
						// System.err.println("board size is: \"" +
						// goGame.getSize()
						// + "\"");
						while (i.hasNext()) {
							move = i.next();
							assertNotNull(move);
							// System.err.print("move: \"" + move + "\"");
							// assertTrue("" + board + "\n" +
							// move.toString(),board.isLegalMove(move));
							board = board.newBoard(move);
							// System.err.println(" board size is: \"" +
							// board.getSize() + "\"");
						}
						// System.err.println();

					}
				} else {
					// System.err.println("\"" + filename +
					// "\" is a directory");
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

	}

	/**
	 * Test load simple gnugo.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void testLoadSimpleGnugo() throws IOException {

		Game game = Game.loadFromFile(new File("sgf/testing/simpleGnuGo.sgf"));
		Iterator<Move> i = game.getMoves();
		Move move = null;
		BoardI board = new SmallBoard(game.getSize());
		while (i.hasNext()) {
			move = i.next();
			assertNotNull(move);
			board = board.newBoard(move);
		}
		// System.err.println(g);
	}

	/**
	 * Test size10.
	 */
	public void testSize10() {
		this.testAllVertexesN(10);
	}

	/**
	 * Test size11.
	 */
	public void testSize11() {
		this.testAllVertexesN(11);
	}

	/**
	 * Test size12.
	 */
	public void testSize12() {
		this.testAllVertexesN(12);
	}

	/**
	 * Test size13.
	 */
	public void testSize13() {
		this.testAllVertexesN(13);
	}

	/**
	 * Test size14.
	 */
	public void testSize14() {
		this.testAllVertexesN(14);
	}

	/**
	 * Test size16.
	 */
	public void testSize16() {
		this.testAllVertexesN(16);
	}

	/**
	 * Test size17.
	 */
	public void testSize17() {
		this.testAllVertexesN(17);
	}

	/**
	 * Test size18.
	 */
	public void testSize18() {
		this.testAllVertexesN(18);
	}

	/**
	 * Test size19.
	 */
	public void testSize19() {
		this.testAllVertexesN(19);
	}

	/**
	 * Test size20.
	 */
	public void testSize20() {
		this.testAllVertexesN(20);
	}

	/**
	 * Test size21.
	 */
	public void testSize21() {
		this.testAllVertexesN(21);
	}

	/**
	 * Test size22.
	 */
	public void testSize22() {
		this.testAllVertexesN(22);
	}

	/**
	 * Test size23.
	 */
	public void testSize23() {
		this.testAllVertexesN(23);
	}

	/**
	 * Test size24.
	 */
	public void testSize24() {
		this.testAllVertexesN(24);
	}

	/**
	 * Test size25.
	 */
	public void testSize25() {
		this.testAllVertexesN(25);
	}

	/**
	 * Test size3.
	 */
	public void testSize3() {
		this.testAllVertexesN(3);
	}

	/**
	 * Test size4.
	 */
	public void testSize4() {
		this.testAllVertexesN(4);
	}

	/**
	 * Test size5.
	 */
	public void testSize5() {
		this.testAllVertexesN(5);
	}

	/**
	 * Test size6.
	 */
	public void testSize6() {
		this.testAllVertexesN(6);
	}

	/**
	 * Test size7.
	 */
	public void testSize7() {
		this.testAllVertexesN(7);
	}

	/**
	 * Test size8.
	 */
	public void testSize8() {
		this.testAllVertexesN(8);
	}

	/**
	 * Test size9.
	 */
	public void testSize9() {
		this.testAllVertexesN(9);
	}

	/**
	 * Test to string.
	 */
	public void testToString() {
		BoardI working = new SmallBoard((short) 19);
		assertNotNull(working);
		working = working.newBoard(new Move("B q10"));
		// System.out.println(working);
	}

	/**
	 * 
	 */
	public void testEachUndoableI() {
		for (int i = 0; i < Zobrist.MAX_BOARD_SIZE; i++)
			for (int j = 0; j < Zobrist.MAX_BOARD_SIZE; j++)
				for (int k = 0; k < Zobrist.MAX_COLOUR; k++) {

					BoardI board1 = new SmallBoard();
					BoardI board2 = board1.newBoard(new Move((short) 1,
							(short) 1, BoardI.VERTEX_BLACK));
					BoardI board3 = board1.newBoard(new Move((short) 1,
							(short) 1, BoardI.VERTEX_BLACK));
					// BoardI board4 = board2.newBoard(new Move((short) 1,
					// (short) 1, BoardI.VERTEX_BLACK));

					assertTrue(board1.equals(board1));
					assertTrue(board2.equals(board2));
					assertTrue(board3.equals(board3));
					// assertTrue(board4.equals(board4));

					assertFalse(board1.equals(board2));
					assertFalse(board2.equals(board1));
					assertFalse(board1.equals(board3));
					assertFalse(board3.equals(board1));
					// assertTrue(board4.equals(board1));
					// assertTrue(board1.equals(board4));
					assertTrue(board2.equals(board3));
					assertTrue(board3.equals(board2));

				}
	}

	/**
	 * 
	 */
	public void testEachUndoableII() {
		for (int i = 0; i < Zobrist.MAX_BOARD_SIZE; i++)
			for (int j = 0; j < Zobrist.MAX_BOARD_SIZE; j++)
				for (int k = 0; k < Zobrist.MAX_COLOUR; k++) {

					BoardI board1 = new SmallBoard(true);
					BoardI board2 = board1.newBoard(new Move((short) 1,
							(short) 1, BoardI.VERTEX_BLACK));
					BoardI board3 = board1.newBoard(new Move((short) 1,
							(short) 1, BoardI.VERTEX_BLACK));
					// BoardI board4 = board2.newBoard(new Move((short) 1,
					// (short) 1, BoardI.VERTEX_BLACK));

					assertTrue(board1.equals(board1));
					assertTrue(board2.equals(board2));
					assertTrue(board3.equals(board3));
					// assertTrue(board4.equals(board4));

					assertFalse(board1.equals(board2));
					assertFalse(board2.equals(board1));
					assertFalse(board1.equals(board3));
					assertFalse(board3.equals(board1));
					// assertTrue(board4.equals(board1));
					// assertTrue(board1.equals(board4));
					assertTrue(board2.equals(board3));
					assertTrue(board3.equals(board2));

				}
	}

	/**
	 * 
	 */
	public void testEachUndoableIII() {
		for (int i = 0; i < Zobrist.MAX_BOARD_SIZE; i++)
			for (int j = 0; j < Zobrist.MAX_BOARD_SIZE; j++)
				for (int k = 0; k < Zobrist.MAX_COLOUR; k++) {

					BoardI board1 = new SmallBoard(false);
					BoardI board2 = board1.newBoard(new Move((short) 1,
							(short) 1, BoardI.VERTEX_BLACK));
					BoardI board3 = board1.newBoard(new Move((short) 1,
							(short) 1, BoardI.VERTEX_BLACK));
					// BoardI board4 = board2.newBoard(new Move((short) 1,
					// (short) 1, BoardI.VERTEX_BLACK));

					assertTrue(board1.equals(board1));
					assertTrue(board2.equals(board2));
					assertTrue(board3.equals(board3));
					// assertTrue(board4.equals(board4));

					assertFalse(board1.equals(board2));
					assertFalse(board2.equals(board1));
					assertFalse(board1.equals(board3));
					assertFalse(board3.equals(board1));
					// assertTrue(board4.equals(board1));
					// assertTrue(board1.equals(board4));
					assertTrue(board2.equals(board3));
					assertTrue(board3.equals(board2));

				}
	}

}
