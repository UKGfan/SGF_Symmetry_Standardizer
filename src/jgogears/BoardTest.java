package jgogears;

import java.io.*;
import java.util.*;

import junit.framework.TestCase;

/**
 * The Class BoardTest.
 * 
 * @author Stuart
 */
public class BoardTest extends TestCase {
	/** are we being verbose ? */
	static final private boolean DEBUG = false;

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
		BoardI board = BoardI.newBoard(size);

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				Move move = new Move(i, j, BoardI.VERTEX_BLACK);
				board = board.newBoard(move);
				assertTrue(board.getColour(i, j) == BoardI.VERTEX_BLACK);

				for (int l = 0; l < size; l++) {
					for (int m = 0; m < size; m++) {
						if (l != i && m != j)
							assertTrue(board.getColour(l, m) == BoardI.VERTEX_EMPTY);
					}
				}
				move = new Move(i, j, BoardI.VERTEX_EMPTY);
				board = board.newBoard(move);
				assertTrue(board.getColour(i, j) == BoardI.VERTEX_EMPTY);

				for (int l = 0; l < size; l++) {
					for (int m = 0; m < size; m++) {
						if (l != i && m != j)
							assertTrue(board.getColour(i, j) == BoardI.VERTEX_EMPTY);
					}
				}
				move = new Move(i, j, BoardI.VERTEX_WHITE);
				board = board.newBoard(move);
				assertTrue(board.getColour(i, j) == BoardI.VERTEX_WHITE);

				for (int l = 0; l < size; l++) {
					for (int m = 0; m < size; m++) {
						if (l != i && m != j)
							assertTrue(board.getColour(l, m) == BoardI.VERTEX_EMPTY);
					}
				}
				move = new Move(i, j, BoardI.VERTEX_KO);
				board = board.newBoard(move);
				assertTrue(board.getColour(i, j) == BoardI.VERTEX_KO);

				for (int l = 0; l < size; l++) {
					for (int m = 0; m < size; m++) {
						if (l != i && m != j)
							assertTrue(board.getColour(l, m) == BoardI.VERTEX_EMPTY);
					}
				}
				move = new Move(i, j, BoardI.VERTEX_EMPTY);
				board = board.newBoard(move);
				assertTrue(board.getColour(i, j) == BoardI.VERTEX_EMPTY);

				for (int l = 0; l < size; l++) {
					for (int m = 0; m < size; m++) {
						if (l != i && m != j)
							assertTrue(board.getColour(l, m) == BoardI.VERTEX_EMPTY);
					}
				}
			}
		}
	}

	/**
	 * Test colours.
	 */
	public void testColours() {
//		assertTrue(BoardI.VERTEX_BLACK == BoardI.VERTEX_BLACK);
		assertTrue(BoardI.VERTEX_BLACK != BoardI.VERTEX_WHITE);
		assertTrue(BoardI.VERTEX_BLACK != BoardI.VERTEX_EMPTY);
		assertTrue(BoardI.VERTEX_BLACK != BoardI.VERTEX_KO);
		assertTrue(BoardI.VERTEX_BLACK != BoardI.VERTEX_OFF_BOARD);

		assertTrue(BoardI.VERTEX_WHITE != BoardI.VERTEX_BLACK);
//		assertTrue(BoardI.VERTEX_WHITE == BoardI.VERTEX_WHITE);
		assertTrue(BoardI.VERTEX_WHITE != BoardI.VERTEX_EMPTY);
		assertTrue(BoardI.VERTEX_WHITE != BoardI.VERTEX_KO);
		assertTrue(BoardI.VERTEX_WHITE != BoardI.VERTEX_OFF_BOARD);

		assertTrue(BoardI.VERTEX_EMPTY != BoardI.VERTEX_BLACK);
		assertTrue(BoardI.VERTEX_EMPTY != BoardI.VERTEX_WHITE);
//		assertTrue(BoardI.VERTEX_EMPTY == BoardI.VERTEX_EMPTY);
		assertTrue(BoardI.VERTEX_EMPTY != BoardI.VERTEX_OFF_BOARD);

		assertTrue(BoardI.VERTEX_KO != BoardI.VERTEX_BLACK);
		assertTrue(BoardI.VERTEX_KO != BoardI.VERTEX_WHITE);
		assertTrue(BoardI.VERTEX_KO != BoardI.VERTEX_EMPTY);
//		assertTrue(BoardI.VERTEX_KO == BoardI.VERTEX_KO);
		assertTrue(BoardI.VERTEX_KO != BoardI.VERTEX_OFF_BOARD);

		assertTrue(BoardI.VERTEX_OFF_BOARD != BoardI.VERTEX_BLACK);
		assertTrue(BoardI.VERTEX_OFF_BOARD != BoardI.VERTEX_WHITE);
		assertTrue(BoardI.VERTEX_OFF_BOARD != BoardI.VERTEX_EMPTY);
		assertTrue(BoardI.VERTEX_OFF_BOARD != BoardI.VERTEX_KO);
//		assertTrue(BoardI.VERTEX_OFF_BOARD == BoardI.VERTEX_OFF_BOARD);

	}

	/**
	 * 
	 */
	public void testEachUndoableI() {
		for (int i = 0; i < Zobrist.MAX_BOARD_SIZE; i++)
			for (int j = 0; j < Zobrist.MAX_BOARD_SIZE; j++)
				for (int k = 0; k < Zobrist.MAX_COLOUR; k++) {

					BoardI board1 = new Board();
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

					BoardI board1 = new Board(true);
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

					BoardI board1 = new Board(false);
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
					assertTrue(board2.equals(board3));
					assertTrue(board3.equals(board2));

				}
	}

	/**
	 * Loads a ton of SGF files and tests the resulting boards
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void testLoadAllSGFfiles() throws IOException {
		Stack<String> files = new Stack<String>();
		files.push("sgf/2004-12");

		while (files.size() > 0) {
			String filename = files.pop();
			File file = new File(filename);
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
						BoardI board = new Board(game.getSize());
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
		BoardI board = new Board(game.getSize());
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
		BoardI working = new Board((short) 19);
		assertNotNull(working);
		working = working.newBoard(new Move("B q10"));
		// System.out.println(working);
	}

}
