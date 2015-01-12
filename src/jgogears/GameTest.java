/**
 * 
 */
package jgogears;

import java.io.*;
import java.util.*;

import junit.framework.TestCase;

/**
 * TODO
 * 
 * @author syeates
 */
public class GameTest extends TestCase {
	/** are we being verbose ? */
	static final private boolean DEBUG = false;

	public void testOneSGFFile() throws Exception {

		Collection<String> filenames = jgogears.engine.Trainer.loadAllSGFfiles();
		assertNotNull(filenames);
		String filename = filenames.iterator().next();
		assertNotNull(filename);
		Game game = Game.loadFromFilename(filename);
		GTPScore score = game.getScore();
		if (score.getBlackWin()) {
			if (DEBUG) {
			System.err.println(score + "  black win");
			}
		} else if (score.getWhiteWin()) {
			if (DEBUG) {
			System.err.println(score + "  black win");
			}
		} else
			if (DEBUG) {
			System.err.println(score + "  neither win");
			}
	}

	public void testManySGFFile() throws Exception {

		Collection<String> filenames = jgogears.engine.Trainer.loadAllSGFfiles();
		assertNotNull(filenames);
		Iterator<String> iterator = filenames.iterator();
		while (iterator.hasNext()) {
			String filename = iterator.next();
			assertNotNull(filename);
			Game game = Game.loadFromFilename(filename);
			assertNotNull(game);
			GTPScore score = game.getScore();
			if (score == null) {
				if (DEBUG) {
					System.err.println("null  unknown score");
				}
			} else if (score.getBlackWin()) {
				if (DEBUG) {
					System.err.println(score + "  black win");
				}
			} else if (score.getWhiteWin()) {
				if (DEBUG) {
					System.err.println(score + "  white win");
				}
			} else if (DEBUG) {
				System.err.println(score + "  neither win");
			}
		}
	}
}	