package jgogears;

import java.io.*;

// TODO: Auto-generated Javadoc
/**
 * The engine side of a GTP connection. Uses a GTPInterface to talk to the
 * engine that actually does the work.
 * 
 * @author syeates
 */
public class GTPEngine implements Runnable {

	/** The reader. */
	BufferedReader reader = null;

	/** The writer. */
	Writer writer = null;

	/** The state. */
	GTPState state = new GTPState();

	/** The engine. */
	GTPInterface engine = null;

	/**
	 * compares two strings and returns true is the shorter is the first
	 * substring of the second.
	 * 
	 * @param a
	 *            the first string
	 * @param b
	 *            the second string
	 * @return true if they match
	 */
	boolean compare(String a, String b) {
		// ensure b is longer that a
		if (a.length() > b.length()) {
			String c = a;
			a = b;
			b = c;
		}
		b = b.substring(0, a.length());
		return b.compareTo(a) == 0;

	}

	/**
	 * Processes single command.
	 * 
	 * @param command
	 *            the command to process
	 * @return true on success
	 * @throws Exception
	 *             the exception
	 */
	boolean processCommand(String command) throws Exception {
		command = command.toLowerCase();
		if (this.compare(command, GTPConstants.BOARDSIZE)) {
			short size = Short.parseShort(command
					.substring(GTPConstants.BOARDSIZE.length() + 1));
			this.engine.setBoardSize(size, this.state);
			this.engine.clearBoard(this.state);
			return true;
		} else if (this.compare(command, GTPConstants.CLEARBAORD)) {
			this.engine.clearBoard(this.state);
			return true;
		} else if (this.compare(command, GTPConstants.QUIT)) {
			this.engine.quit();
			this.writer.flush();
			this.writer.close();
			this.writer = null;
			this.reader.close();
			this.reader = null;
			this.state = null;
			System.err.println("quiting");
			return true;
		} else if (this.compare(command, GTPConstants.KNOWNCOMMAND)) {
			String commandS = command.substring(GTPConstants.KNOWNCOMMAND
					.length() + 1);
			boolean result = this.engine.getKnownCommand(commandS);
			this.writer.write("" + result);
			return true;
		} else if (this.compare(command, GTPConstants.LISTCOMMANDS)) {
			System.err.println("don't know how to handle the command:"
					+ command);
			return false;
		} else if (this.compare(command, GTPConstants.NAME)) {
			this.writer.write(this.engine.getEngineName());
			return true;
		} else if (this.compare(command, GTPConstants.VERSION)) {
			this.writer.write(this.engine.getEngineVersion());
			return true;
		} else if (this.compare(command, GTPConstants.PROTOCOLVERSION)) {
			int version = this.engine.getProtocolVersion();
			if (version != 2)
				throw new Error();
			this.writer.write("" + version);
			return true;
		} else if (this.compare(command, GTPConstants.PLAY)) {
			Move move = new Move(
					command.substring(GTPConstants.PLAY.length() + 1));
			this.engine.play(move, this.state);
			return true;
		} else if (this.compare(command, GTPConstants.GENMOVE)) {
			short colour = BoardI.parseColour(command
					.substring(GTPConstants.PLAY.length() + 1));
			Move move = this.engine.genMove(colour, this.state);
			this.writer.write(move.toVertexString());
			return true;
		} else if (this.compare(command, GTPConstants.UNDO)) {
			if (!this.engine.undo(this.state)) {
				this.writer.write(GTPConstants.CANNOTUNDO);
				return false;
			}
			return true;
		} else if (this.compare(command, GTPConstants.FINALSCORE)) {
			this.writer.write(this.engine.getFinalScore(this.state).toString());
			return true;
		} else if (this.compare(command, GTPConstants.LOADSGF)) {
			System.err.println("clearing board");
			throw new Error("don't know how to handle the command:" + command);
		} else if (this.compare(command, GTPConstants.REGGENMOVE)) {
			short colour = BoardI.parseColour(command
					.substring(GTPConstants.PLAY.length() + 1));
			Move move = this.engine.regGenMove(colour, this.state);
			this.writer.write(move.toVertexString());
			return true;
		} else
			return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		try {
			this.stuff();
		} catch (Throwable t) {
			t.printStackTrace();
			System.err.println(t);
		}
	}

	/**
	 * Stuff.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	void stuff() throws Exception {
		boolean quit = false;
		while (!quit) {
			String s = this.reader.readLine();
			System.err.println("GTPEngine: \"" + s + "\"");
			if (s.length() == 0)
				continue;
			if (s.charAt(0) == '#')
				continue;
			System.err.println("looks like a real command...");
			quit = this.processCommand(s);
			Thread.yield();
		}
	}
}
