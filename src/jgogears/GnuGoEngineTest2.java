package jgogears;

import java.io.*;
import java.util.*;

import junit.framework.TestCase;

// TODO: Auto-generated Javadoc
/**
 * The Class GnuGoEngineTest2.
 * 
 * @author syeates
 */
public class GnuGoEngineTest2 extends TestCase {

	/** The DEBUG. */
	final static private boolean DEBUG = false;

	/** The engine. */
	GnuGoEngine engine = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		this.engine = new GnuGoEngine();
		this.engine.initialise();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		if (this.engine != null)
			this.engine.quit();
	}

	/**
	 * Test clear board.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public final void testClearBoard() throws IOException {
		this.engine.initialise();
		boolean r = this.engine.clearBoard();
		this.engine.quit();
		assertTrue(r);
	}

	/**
	 * Test final status list.
	 */
	public final void testFinalStatusList() {
		try {

			Game game = Game.loadFromFile(new File("sgf/testing/seki.sgf"));
			assertNotNull(game);

			Iterator<Move> moves = game.getMoves();
			assertNotNull(moves);
			engine = new GnuGoEngine();
			while (moves.hasNext()) {
				Move move = moves.next();
				// System.err.println(move);
				engine.play(move);
			}
			engine.initialise();
			TreeSet<Vertex> alive = engine.finalStatusList("alive");
			assertNotNull(alive);
			TreeSet<Vertex> dead = engine.finalStatusList("dead");
			assertNotNull(dead);
			TreeSet<Vertex> seki = engine.finalStatusList("seki");
			assertNotNull(seki);
			engine.quit();
		} catch (Throwable t) {
			if (DEBUG)
				System.err.println(t);
			if (DEBUG)
				t.printStackTrace();
			fail();
		}
	}

	/**
	 * Test fixed handicap.
	 */
	public final void testFixedHandicap() {
		try {
			this.engine.initialise();
			for (short i = 2; i < 10; i++) {
				this.engine.setBoardSize((short) 19);
				TreeSet<Vertex> moves = this.engine.fixedHandicap(i);
				assertNotNull(moves);
				assertTrue(moves.size() == i);
			}
			this.engine.quit();
		} catch (Throwable t) {
			if (DEBUG)
				System.err.println(t);
			if (DEBUG)
				t.printStackTrace();
			fail();
		}
	}

	/**
	 * Test gen move.
	 */
	public final void testGenMove() {
		try {
			this.engine.initialise();
			this.engine.setBoardSize((short) 19);
			this.engine.clearBoard();
			Move move = this.engine.genMove(BoardI.VERTEX_BLACK);
			if (this.DEBUG)
				System.err.println(move);
			this.engine.quit();
		} catch (Throwable t) {
			if (DEBUG)
				System.err.println(t);
			if (DEBUG)
				t.printStackTrace();
			fail();
		}

	}

	/**
	 * Test get engine name.
	 */
	public final void testGetEngineName() {
		try {
			this.engine.initialise();
			String s = this.engine.getEngineName();
			if (this.DEBUG)
				System.err.println(s);
			this.engine.quit();
		} catch (Throwable t) {
			if (DEBUG) {
				System.err.println(t);
				t.printStackTrace();
			}
			fail();
		}
	}

	/**
	 * Test get engine version.
	 */
	public final void testGetEngineVersion() {
		try {
			this.engine.initialise();
			String s = this.engine.getEngineVersion();
			if (DEBUG)
				System.err.println(s);
			this.engine.quit();
		} catch (Throwable t) {
			if (DEBUG)
				System.err.println(t);
			if (DEBUG)
				t.printStackTrace();
			fail();
		}
	}

	/**
	 * Test get final score.
	 */
	public final void testGetFinalScore() {
		try {
			this.engine.initialise();
			Boolean b = this.engine.loadsgf("sgf/testing/seki.sgf", 0);
			assertTrue(b);

			GTPScore score = this.engine.getFinalScore();
			assertNotNull(score);
			// TODO
			this.engine.quit();
			// TODO
		} catch (Throwable t) {
			if (DEBUG)
				System.err.println(t);
			if (DEBUG)
				t.printStackTrace();
			fail();
		}
	}

	/**
	 * Test get known command.
	 */
	public final void testGetKnownCommand() {
		try {
			this.engine.initialise();
			this.engine.clearBoard();
			this.engine.quit();
			// TODO
		} catch (Throwable t) {
			if (DEBUG)
				System.err.println(t);
			if (DEBUG)
				t.printStackTrace();
			fail();
		}
	}

	/**
	 * Test get list commands.
	 */
	public final void testGetListCommands() {
		try {
			this.engine.initialise();
			this.engine.clearBoard();
			this.engine.quit();
			// TODO
		} catch (Throwable t) {
			if (DEBUG)
				System.err.println(t);
			if (DEBUG)
				t.printStackTrace();
			fail();
		}

	}

	/**
	 * Test get protocol version.
	 */
	public final void testGetProtocolVersion() {
		try {
			this.engine.initialise();
			int v = this.engine.getProtocolVersion();
			assertTrue(v > 0);
			assertTrue(v < 3);
			this.engine.quit();

		} catch (Throwable t) {
			if (DEBUG)
				System.err.println(t);
			if (DEBUG)
				t.printStackTrace();
			fail();
		}
	}

	/**
	 * Test loadsgf.
	 */
	public final void testLoadsgf() {
		try {
			this.engine.initialise();
			Boolean b = this.engine.loadsgf("sgf/testing/seki.sgf", 20);
			assertTrue(b);
			Move move = this.engine.genMove(BoardI.VERTEX_BLACK);
			assertNotNull(move);
			BoardI board = this.engine.showBoard();
			if (DEBUG)
				System.err
						.println("testLoadsgf:: the following board should have moves on it:");
			if (DEBUG)
				System.err.println(board);
			this.engine.quit();
			// TODO
		} catch (Throwable t) {
			if (DEBUG)
				System.err.println(t);
			if (DEBUG)
				t.printStackTrace();
			fail();
		}
	}

	/**
	 * Test place free handicap go move array.
	 */
	public final void testPlaceFreeHandicapGoMoveArray() {
		try {
			this.engine.initialise();
			this.engine.clearBoard();
			this.engine.quit();
			// TODO
		} catch (Throwable t) {
			if (DEBUG)
				System.err.println(t);
			if (DEBUG)
				t.printStackTrace();
			fail();
		}
	}

	/**
	 * Test place free handicap int.
	 */
	public final void testPlaceFreeHandicapInt() {

		try {
			this.engine.initialise();
			this.engine.setBoardSize((short) 19);
			this.engine.clearBoard();
			TreeSet<Vertex> moves = this.engine.placeFreeHandicap((short) 9);
			assertTrue(moves.size() == 9);
			this.engine.quit();
		} catch (Throwable t) {
			if (DEBUG)
				System.err.println(t);
			if (DEBUG)
				t.printStackTrace();
			fail();
		}
	}

	/**
	 * Test place free handicap int multi.
	 */
	public final void testPlaceFreeHandicapIntMulti() {

		try {
			for (short i = 2; i < 50; i++) {
				this.engine.initialise();
				this.engine.setBoardSize((short) 19);
				this.engine.clearBoard();
				TreeSet<Vertex> moves = this.engine.placeFreeHandicap(i);
				assertTrue(moves.size() == i);
				this.engine.quit();
			}
			for (short i = 2; i < 20; i++) {
				this.engine.initialise();
				this.engine.setBoardSize((short) 13);
				this.engine.clearBoard();
				TreeSet<Vertex> moves = this.engine.placeFreeHandicap(i);
				assertTrue(moves.size() == i);
				this.engine.quit();
			}
			for (short i = 2; i < 10; i++) {
				this.engine.initialise();
				this.engine.setBoardSize((short) 9);
				this.engine.clearBoard();
				TreeSet<Vertex> moves = this.engine.placeFreeHandicap(i);
				assertTrue(moves.size() == i);
				this.engine.quit();
			}
		} catch (Throwable t) {
			if (DEBUG)
				System.err.println(t);
			if (DEBUG)
				t.printStackTrace();
			fail();
		}
	}

	/**
	 * Test play.
	 */
	public final void testPlay() {
		try {
			this.engine.initialise();
			this.engine.setBoardSize((short) 19);
			this.engine.clearBoard();
			this.engine.play(new Move("white c3"));
			this.engine.quit();
		} catch (Throwable t) {
			if (DEBUG)
				System.err.println(t);
			if (DEBUG)
				t.printStackTrace();
			fail();
		}
	}

	/**
	 * Test quit.
	 */
	public final void testQuit() {
		try {
			this.engine.initialise();
			this.engine.quit();
		} catch (Throwable t) {
			if (DEBUG)
				System.err.println(t);
			if (DEBUG)
				t.printStackTrace();
			fail();
		}
	}

	/**
	 * Test reg gen move.
	 */
	public final void testRegGenMove() {
		try {
			this.engine.initialise();
			Move move = this.engine.regGenMove(BoardI.VERTEX_BLACK);
			if (DEBUG)
				System.err.println(move);
			this.engine.quit();
		} catch (Throwable t) {
			if (DEBUG)
				System.err.println(t);
			if (DEBUG)
				t.printStackTrace();
			fail();
		}
	}

	/**
	 * Test set board size.
	 */
	public final void testSetBoardSize() {
		try {
			this.engine.initialise();
			boolean result = this.engine.setBoardSize((short) 19);
			assertTrue(result);
			result = this.engine.setBoardSize((short) 9);
			assertTrue(result);
			this.engine.quit();
		} catch (Throwable t) {
			if (DEBUG)
				System.err.println(t);
			if (DEBUG)
				t.printStackTrace();
			fail();
		}
	}

	/**
	 * Test set komi.
	 */
	public final void testSetKomi() {
		try {
			this.engine.initialise();
			this.engine.setKomi(7.0);
			this.engine.quit();
		} catch (Throwable t) {
			if (DEBUG)
				System.err.println(t);
			if (DEBUG)
				t.printStackTrace();
			fail();
		}
	}

	/**
	 * Test set time left.
	 */
	public final void testSetTimeLeft() {
		try {
			this.engine.initialise();
			this.engine.setTimeLeft(BoardI.VERTEX_BLACK, 1.0, 1.0);
			this.engine.quit();
		} catch (Throwable t) {
			if (DEBUG)
				System.err.println(t);
			if (DEBUG)
				t.printStackTrace();
			fail();
		}
	}

	/**
	 * Test set time settings.
	 */
	public final void testSetTimeSettings() {
		try {
			this.engine.initialise();
			this.engine.setTimeSettings(1.0, 1.0, 1.0);
			this.engine.quit();
		} catch (Throwable t) {
			if (DEBUG)
				System.err.println(t);
			if (DEBUG)
				t.printStackTrace();
			fail();
		}
	}

	/**
	 * This is currently failing because I don't have good code to parse the
	 * ASCII board back into a BoardI.
	 */
	public final void testShowBoard() {
		try {
			this.engine.initialise();
			BoardI board = this.engine.showBoard();
			assertNotNull(board);
			// TODO
			this.engine.quit();
		} catch (Throwable t) {
			if (DEBUG)
				System.err.println(t);
			if (DEBUG)
				t.printStackTrace();
			fail();
		}
	}

	/**
	 * Test undo.
	 */
	public final void testUndo() {
		try {
			this.engine.initialise();
			boolean result = this.engine.undo();
			assertFalse(result);
			this.engine.quit();
		} catch (Throwable t) {
			if (DEBUG)
				System.err.println(t);
			if (DEBUG)
				t.printStackTrace();
		}

	}

}
