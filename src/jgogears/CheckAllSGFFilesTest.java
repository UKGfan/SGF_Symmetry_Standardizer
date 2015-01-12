/**
 * 
 */
package jgogears;

import java.io.*;
import java.util.*;

import junit.framework.TestCase;

/**
 * Class to check that all the sgf files are valid and well behaving.
 * 
 * @author syeates
 */
public class CheckAllSGFFilesTest extends TestCase {

	/** are we being verbose debugging. */
	public static final boolean DEBUG = false;

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
							if (DEBUG)
								System.err.print(".");
						}
						if (DEBUG)
							System.err.println();
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

}
