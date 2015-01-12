package jgogears;

import java.io.*;
import java.util.*;

import junit.framework.TestCase;

// TODO: Auto-generated Javadoc
/**
 * The Class SmallerBoardTest.
 */
public class SmallerBoardTest extends TestCase {
	/** Are we using verbose debugging?. */
	public static final boolean DEBUG = false;

	/**
	 * Test all sizes.
	 */
	public void testAllSizes() {
		this.testAllVertexesN(3);
		this.testAllVertexesN(6);
		this.testAllVertexesN(7);
		this.testAllVertexesN(8);
		this.testAllVertexesN(9);
		this.testAllVertexesN(10);
		this.testAllVertexesN(11);
		this.testAllVertexesN(12);
		this.testAllVertexesN(13);
		this.testAllVertexesN(14);
		this.testAllVertexesN(15);
		this.testAllVertexesN(16);
		this.testAllVertexesN(17);
		this.testAllVertexesN(18);
		this.testAllVertexesN(19);
		this.testAllVertexesN(20);
		this.testAllVertexesN(21);
		this.testAllVertexesN(22);
	}

	/**
	 * Test all vertexes n.
	 * 
	 * @param size
	 *            the size
	 */
	public void testAllVertexesN(int size) {
		BoardI board = new SmallerBoard(size);

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				Move move = new Move(i, j, BoardI.VERTEX_BLACK);
				board = board.newBoard(move);
				assertTrue(
						"" + size + "," + i + "," + j + ","
								+ board.getColour(i, j) + ","
								+ BoardI.VERTEX_BLACK,
						board.getColour(i, j) == BoardI.VERTEX_BLACK);

				for (int l = 0; l < size; l++) {
					for (int m = 0; m < size; m++) {
						if (l != i && m != j) {

							assertTrue(
									"" + size + "," + i + "," + j + "," + l
											+ "," + m + ","
											+ board.getColour(l, m) + ","
											+ BoardI.VERTEX_EMPTY,
									board.getColour(l, m) == BoardI.VERTEX_EMPTY);
							assertTrue(board.getColour(l, m) == BoardI.VERTEX_EMPTY);
							assertTrue(BoardI.VERTEX_EMPTY == BoardI.VERTEX_EMPTY);
							assertTrue(board.getColour(l, m) == board
									.getColour(l, m));
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
							assertTrue(
									"" + size + "," + i + "," + j + "," + l
											+ "," + m + ","
											+ board.getColour(l, m) + ",",
									board.getColour(i, j) == BoardI.VERTEX_EMPTY);
						}
					}
				}
				move = new Move(i, j, BoardI.VERTEX_WHITE);
				board = board.newBoard(move);
				assertTrue(board.getColour(i, j) == BoardI.VERTEX_WHITE);

				for (int l = 0; l < size; l++) {
					for (int m = 0; m < size; m++) {
						if (l != i && m != j) {
							assertTrue(
									"" + size + "," + i + "," + j + "," + l
											+ "," + m + ","
											+ board.getColour(l, m) + ",",
									board.getColour(l, m) == BoardI.VERTEX_EMPTY);
						}
					}
				}
				move = new Move(i, j, BoardI.VERTEX_KO);
				board = board.newBoard(move);
				assertTrue(board.getColour(i, j) == BoardI.VERTEX_KO);

				for (int l = 0; l < size; l++) {
					for (int m = 0; m < size; m++) {
						if (l != i && m != j) {
							assertTrue(
									"" + size + "," + i + "," + j + "," + l
											+ "," + m + ","
											+ board.getColour(l, m) + ",",
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
							assertTrue(
									"" + size + "," + i + "," + j + "," + l
											+ "," + m + ","
											+ board.getColour(l, m) + ",",
									board.getColour(l, m) == BoardI.VERTEX_EMPTY);
						}
					}
				}
			}
		}
	}

	/**
	 * Test load all sg ffiles.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void testLoadAllSGFfiles() throws IOException {
		Stack<String> files = new Stack<String>();
		files.push("sgf/2004-12");
		int count = 0;

		while (files.size() > 0 && count <= 10) {
			String filename = files.pop();
			File file = new File(filename);
			count++;
			if (DEBUG)
				System.err.println("examining \"" + filename + "\"");
			if (file.exists()) {
				if (!file.isDirectory()) {
					// System.err.println("\"" + filename + "\" is not a
					// directory, parsing as an SGF file");

					Game game = Game.loadFromFile(file);
					if (game.getSize() == 19) {
						Iterator<Move> i = game.getMoves();
						Move move = null;
						BoardI board = new SmallerBoard(game.getSize());
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
					if (DEBUG)
						System.err.println("\"" + filename
								+ "\" is a directory");
					if (!file.getName().contains(".svn")) {
						String[] children = file.list();
						for (int i = 0; i < children.length; i++) {
							// System.err.println("pushing \"" + children[i] +
							// "\"");
							files.push(filename + "/" + children[i]);
						}
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
		BoardI board = new SmallerBoard(game.getSize());
		while (i.hasNext()) {
			move = i.next();
			assertNotNull(move);
			board = board.newBoard(move);
		}
		// System.err.println(g);
	}

	/**
	 * Test to string.
	 */
	public void testToString() {
		BoardI working = new SmallerBoard((short) 19);
		assertNotNull(working);
		working = working.newBoard(new Move("B q10"));
		if (DEBUG)
			System.out.println(working);
	}

	/**
	 * 
	 */
	public void testEachUndoableI() {
		for (int i = 0; i < Zobrist.MAX_BOARD_SIZE; i++)
			for (int j = 0; j < Zobrist.MAX_BOARD_SIZE; j++)
				for (int k = 0; k < Zobrist.MAX_COLOUR; k++) {

					BoardI board1 = new SmallerBoard();
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

					BoardI board1 = new SmallerBoard(true);
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

					BoardI board1 = new SmallerBoard(false);
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
