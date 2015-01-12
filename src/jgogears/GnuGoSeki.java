package jgogears;

import java.io.*;
import java.util.*;

// TODO: Auto-generated Javadoc
/**
 * The Class GnuGoSeki.
 */
public class GnuGoSeki {

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the args
	 * @throws Exception
	 *             the exception
	 */
	public static void main(String[] args) throws Exception {
		Stack<String> files = new Stack<String>();
		files.push("sgf/");

		while (files.size() > 0) {
			String filename = files.pop();
			File file = new File(filename);
			// System.err.println("examining \"" + filename + "\"");
			if (file.exists()) {
				if (!file.isDirectory()) {
					// System.err.println("\"" + filename + "\" is not a
					// directory");
					if (testForSeki(file)) {
						System.err.println("\"" + filename
								+ "\" contains a seki");
					}
				} else {
					System.err.println("\"" + filename + "\" is a directory");
					String[] children = file.list();
					for (int i = 0; i < children.length; i++) {
						// System.err.println("pushing \"" + children[i] +
						// "\"");
						files.push(filename + "/" + children[i]);
					}
				}
			}
		}

		// testForSeki("sgf/testing/seki.sgf");
	}

	/**
	 * Test for seki.
	 * 
	 * @param file
	 *            the file
	 * @return true, if successful
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	static public boolean testForSeki(File file) throws IOException {

		Game game = Game.loadFromFile(file);
		if (game == null) {
			System.err.println("failed to load \"" + file + "\"");
			return false;
		}
		if (!game.getScore().getScored() || game.getSize() != 19)
			return false;
		if (game.isBranched())
			return false;
		Iterator<Move> moves = game.getMoves();

		GnuGoEngine engine = new GnuGoEngine();
		while (moves.hasNext()) {
			Move move = moves.next();
			// System.err.println(move);
			engine.play(move);
		}
		// engine.showBoard();
		// engine.getFinalScore();
		Date before = new Date();
		TreeSet<Vertex> stonesInSeki = engine.finalStatusList("seki");
		Date after = new Date();
		System.err.println("\"" + file + "\" time = \""
				+ (after.getTime() - before.getTime()) + "\" result = \""
				+ game.getScore() + "\"");
		if (stonesInSeki != null && stonesInSeki.size() != 0) {
			System.err.println("\"" + file + "\" stonesInSeki = \""
					+ stonesInSeki + "\"");
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Test for seki.
	 * 
	 * @param filename
	 *            the filename
	 * @return true, if successful
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	static public boolean testForSeki(String filename) throws IOException {
		return testForSeki(new File(filename));
	}

}
