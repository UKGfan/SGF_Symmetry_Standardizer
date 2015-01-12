package jgogears;

import java.io.*;
import java.util.TreeSet;

// TODO: Auto-generated Javadoc
/**
 * An engine wrapping an instance of the GnuGo computer-go player.
 * 
 * @author syeates
 */
public final class GnuGoEngine implements GTPInterfaceRaw {

	/** The Constant SMALL_PAUSE. */
	public static final int SMALL_PAUSE = 3;

	/** The Constant LARGE_PAUSE. */
	public static final int LARGE_PAUSE = 33;

	/** The executablea. */
	private final String executablea = "/usr/games/gnugo";

	/** The executableb. */
	private final String executableb = "H:/gnugo-mingw-36.exe";

	/** The args. */
	private String args = "--mode gtp --level 1";

	/** The initialised. */
	protected boolean initialised = false;

	/** The writer. */
	private Writer writer = null;

	/** The reader. */
	private BufferedReader reader = null;

	/** The errreader. */
	private BufferedReader errReader = null;

	/** The process. */
	private Process process = null;

	/** The DEBUG. */
	public boolean DEBUG = false;

	private short size = BoardI.DEFAULT_BOARD_SIZE;

	/**
	 * Instantiates a new gnu go engine.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public GnuGoEngine() throws IOException {
		this.initialise();
	}

	/**
	 * Instantiates a new gnu go engine.
	 * 
	 * @param size
	 *            the board size we're playing on
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public GnuGoEngine(int size) throws IOException {
		this.size = (short) size;
		this.initialise();
	}

	/**
	 * Instantiates a new gnu go engine.
	 * 
	 * @param size
	 *            the board size we're playing on
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public GnuGoEngine(short size) throws IOException {
		this.size = size;
		this.initialise();
	}

	/**
	 * Check.
	 */
	protected synchronized void check() {
		try {
			while (this.errReader.ready()) {
				System.err.print("GnuGo Process Error:\""
						+ this.errReader.readLine() + "\"");
			}
		} catch (Throwable t) {
			t.printStackTrace();
			System.err.println(t);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.GTPInterfaceRaw#clearBoard()
	 */
	public boolean clearBoard() {
		this.write(GTPConstants.CLEARBAORD + "\n\n");
		String s = this.read();
		Error e = GTPParserUtils.getError(s);
		if (null == e)
			return true;
		if (this.DEBUG)
			System.err.println("clearBoard:" + s);
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.GTPInterfaceRaw#finalStatusList(java.lang.String)
	 */
	public TreeSet<Vertex> finalStatusList(String status) {
		this.write(GTPConstants.FINALSTATUSLIST + " " + status + "\n\n");
		String s = this.read();
		TreeSet<Vertex> v = GTPParserUtils.parseVertexList(s);
		if (this.DEBUG) {
			System.err.println("finalStatusList" + status);
			System.err.println("finalStatusList" + s);
			System.err.println("finalStatusList" + v);
		}
		return v;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.GTPInterfaceRaw#fixedHandicap(short)
	 */
	public TreeSet<Vertex> fixedHandicap(short handicap) {
		this.write(GTPConstants.FIXEDHANDICAP + " " + handicap + "\n\n");
		String s = this.read();
		TreeSet<Vertex> v = GTPParserUtils.parseVertexList(s);
		if (this.DEBUG) {
			System.err.println("fixedHandicap" + handicap);
			System.err.println("fixedHandicap" + s);
			System.err.println("fixedHandicap" + v);
		}
		return v;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.GTPInterfaceRaw#genMove(short)
	 */
	public Move genMove(short colour) {
		this.write(GTPConstants.GENMOVE + " " + BoardI.colourString(colour)
				+ "\n\n");
		String s = this.read();
		// GoMove move = GoMove.createVertex(s.substring(2));
		Move move = new Move(s, colour);
		if (this.DEBUG)
			System.err.println("genMove:" + colour + " " + move);
		return move;
	}

	/**
	 * Gets the args.
	 * 
	 * @return the args
	 */
	public String getArgs() {
		return this.args;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.GTPInterfaceRaw#getEngineName()
	 */
	public String getEngineName() {
		this.write(GTPConstants.NAME + "\n\n");
		return this.read();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.GTPInterfaceRaw#getEngineVersion()
	 */
	public String getEngineVersion() {
		this.write(GTPConstants.VERSION + "\n\n");
		return this.read();
	}

	/**
	 * Gets the Reader for the error stream
	 * 
	 * @return the errReader
	 */
	public BufferedReader getErrReader() {
		return this.errReader;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.GTPInterfaceRaw#getFinalScore()
	 */
	public GTPScore getFinalScore() {
		this.write(GTPConstants.FINALSCORE + "\n\n");
		String result = this.read();
		result = GTPParserUtils.stripIntro(result);
		return new GTPScore(result);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.GTPInterfaceRaw#getKnownCommand(java.lang.String)
	 */
	public boolean getKnownCommand(String command) {
		// TODO Auto-generated method stub
		throw new Error();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.GTPInterfaceRaw#getListCommands()
	 */
	public TreeSet<String> getListCommands() {
		// TODO Auto-generated method stub
		throw new Error();
	}

	/**
	 * Gets the process.
	 * 
	 * @return the process
	 */
	public Process getProcess() {
		return this.process;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.GTPInterfaceRaw#getProtocolVersion()
	 */
	public int getProtocolVersion() {
		this.write(GTPConstants.PROTOCOLVERSION + " \n\n");
		String result = this.read();
		return Integer.parseInt(result);
	}

	/**
	 * Gets the reader.
	 * 
	 * @return the reader
	 */
	public BufferedReader getReader() {
		return this.reader;
	}

	/**
	 * get the size
	 * 
	 * @return the size
	 */
	public final short getSize() {
		return this.size;
	}

	/**
	 * Gets the writer.
	 * 
	 * @return the writer
	 */
	public Writer getWriter() {
		return this.writer;
	}

	/**
	 * Initialise.
	 * 
	 * @return true, if successful
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public synchronized boolean initialise() throws IOException {
		if (this.initialised)
			return true;
		String executable = this.executablea;
		File exec = new File(this.executablea);
		if (!exec.exists() || !exec.canExecute()) {
			if (this.DEBUG)
				System.err.println(exec.toString() + " exists: "
						+ exec.exists() + " exec: " + exec.canExecute());
			exec = new File(this.executableb);
			executable = this.executableb;
			if (!exec.exists() || !exec.canExecute()) {
				if (this.DEBUG)
					System.err.println(exec.toString() + " exists: "
							+ exec.exists() + " exec: " + exec.canExecute());
				throw new java.io.IOException(
						"Files don't exist or cannot be executed: \""
								+ this.executablea + "\", \""
								+ this.executableb);
			}
		}

		try {
			String command = executable + " " + this.args;
			this.process = java.lang.Runtime.getRuntime().exec(command);
			this.reader = new java.io.BufferedReader(new InputStreamReader(
					this.process.getInputStream()));
			this.errReader = new java.io.BufferedReader(new InputStreamReader(
					this.process.getErrorStream()));
			this.writer = new OutputStreamWriter(this.process.getOutputStream());
			this.initialised = true;

			this.setBoardSize(this.getSize());

			this.check();
			Thread.sleep(LARGE_PAUSE);
			return true;
		} catch (Throwable t) {
			t.printStackTrace();
			System.err.println(t);
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.GTPInterfaceRaw#loadsgf(java.lang.String, int)
	 */
	public boolean loadsgf(String filename, int moveNumber) {
		if (moveNumber > 0)
			this.write(GTPConstants.LOADSGF + " " + filename + " " + moveNumber
					+ "\n\n");
		else
			this.write(GTPConstants.LOADSGF + " " + filename + "\n\n");
		String s = this.read();
		Error e = GTPParserUtils.getError(s);
		if (null == e)
			return true;
		if (this.DEBUG)
			System.err.println("loadsgf:" + s);
		return false;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.GTPInterfaceRaw#placeFreeHandicap(short)
	 */
	public TreeSet<Vertex> placeFreeHandicap(short handicap) {
		// TODO write tests for this
		this.write(GTPConstants.PLACEFREEHANDHANDICAP + " " + handicap + "\n\n");
		String s = this.read();
		TreeSet<Vertex> v = GTPParserUtils.parseVertexList(s);
		if (this.DEBUG)
			System.err.println(s);
		if (this.DEBUG)
			System.err.println(v);
		return v;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.GTPInterfaceRaw#placeFreeHandicap(java.util.TreeSet)
	 */
	public boolean placeFreeHandicap(TreeSet<Vertex> stones) {
		this.write(GTPConstants.PLACEFREEHANDHANDICAP + " " + stones.toString()
				+ "\n\n");
		String s = this.read();
		Error e = GTPParserUtils.getError(s);
		if (null == e)
			return true;
		if (this.DEBUG)
			System.err.println("clearBoard:" + s);
		return false;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.GTPInterfaceRaw#play(jgogears.Move)
	 */
	public boolean play(Move move) {
		this.write(GTPConstants.PLAY + " " + move + "\n\n");
		String s = this.read();
		Error e = GTPParserUtils.getError(s);
		if (null == e)
			return true;
		return false;
	}

	/**
	 * quit notifies the remote engine, flushes all buffers and sets variables
	 * to null
	 */
	public synchronized boolean quit() {
		if (this.initialised) {
			try {
				// handle the remote end
				this.write(GTPConstants.QUIT + "\n\n");
				this.writer.flush();

				// handle the local end
				this.check();
				this.initialised = false;
				this.reader.close();
				this.reader = null;
				this.errReader.close();
				this.errReader = null;
				this.writer.close();
				this.writer = null;
				this.process.destroy();
				this.process = null;

			} catch (Throwable t) {
				t.printStackTrace();
				System.err.println(t);
				return false;
			}
		}
		return true;

	}

	/**
	 * Read.
	 * 
	 * @return the string
	 */
	protected synchronized String read() {
		String s = "";
		String result = "";
		try {
			try {
				Thread.sleep(SMALL_PAUSE);
			} catch (Throwable t) {
				// do nothing
			}
			while (s != null && s.compareTo("") == 0) {
				s = this.reader.readLine();
			}
			if (s == null)
				s = "";
			result = GTPParserUtils.stripIntro(s);

			if (this.DEBUG)
				System.err.println("GnuGo Process Output:\"" + s + "\" ==> \""
						+ result + "\"");

			this.check();
		} catch (GTPError t) {
			t.printStackTrace();
			System.err.println(t);
			throw t;
		} catch (IOException t) {
			t.printStackTrace();
			System.err.println(t);
		}
		return result;
	}

	/**
	 * Read all.
	 * 
	 * @return the string
	 */
	protected synchronized String readAll() {
		String s = "";
		try {
			Thread.sleep(SMALL_PAUSE);
			s = this.reader.readLine();
			while (this.reader.ready()) {
				Thread.yield();
				s = s + "\n" + this.reader.readLine();
			}
			if (this.DEBUG)
				System.err.println("GnuGo Process Output:\"" + s + "\"");

			this.check();
		} catch (Throwable t) {
			t.printStackTrace();
			System.err.println(t);
		}
		return s;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.GTPInterfaceRaw#regGenMove(short)
	 */
	public Move regGenMove(short colour) {
		return this.genMove(colour);
	}

	/**
	 * Sets the args.
	 * 
	 * @param args
	 *            the new args
	 */
	public void setArgs(String args) {
		this.args = args;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.GTPInterfaceRaw#setBoardSize(short)
	 */
	public boolean setBoardSize(short size) {
		this.write(GTPConstants.BOARDSIZE + " " + size + "\n\n");
		String s = this.read();
		Error e = GTPParserUtils.getError(s);
		if (this.DEBUG)
			System.err.println("clearBoard:" + s);
		if (null == e)
			return true;
		return false;
	}

	/**
	 * Sets the errReader.
	 * 
	 * @param errReader
	 *            the new errReader
	 */
	public void setErrReader(BufferedReader errReader) {
		this.errReader = errReader;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.GTPInterfaceRaw#setKomi(double)
	 */
	public boolean setKomi(double komi) {
		this.write(GTPConstants.KOMI + " " + komi + "\n\n");
		String s = this.read();
		Error e = GTPParserUtils.getError(s);
		if (null == e)
			return true;
		if (this.DEBUG)
			System.err.println("clearBoard:" + s);
		return false;

	}

	/**
	 * Sets the process.
	 * 
	 * @param process
	 *            the new process
	 */
	public void setProcess(Process process) {
		this.process = process;
	}

	/**
	 * Sets the reader.
	 * 
	 * @param reader
	 *            the new reader
	 */
	public void setReader(BufferedReader reader) {
		this.reader = reader;
	}

	/**
	 * set the size
	 * 
	 * @param size
	 *            the size to set
	 */
	public final void setSize(short size) {
		this.size = size;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.GTPInterfaceRaw#setTimeLeft(short, double, double)
	 */
	public boolean setTimeLeft(short colour, double byoYomiTime,
			double byoYomiStones) {
		this.write(GTPConstants.TIMELEFT + " " + BoardI.colourString(colour)
				+ " " + (int) byoYomiTime + " " + (int) byoYomiStones + "\n\n");
		String s = this.read();
		Error e = GTPParserUtils.getError(s);
		if (null == e)
			return true;
		if (this.DEBUG)
			System.err.println("clearBoard:" + s);
		return false;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.GTPInterfaceRaw#setTimeSettings(double, double, double)
	 */
	public boolean setTimeSettings(double mainTime, double byoYomiTime,
			double byoYomiStones) {
		this.write(GTPConstants.TIMESETTINGS + " " + (int) mainTime + " "
				+ (int) byoYomiTime + " " + (int) byoYomiStones + "\n\n");
		String s = this.read();
		Error e = GTPParserUtils.getError(s);
		if (null == e)
			return true;
		if (this.DEBUG)
			System.err.println("clearBoard:" + s);
		return false;

	}

	/**
	 * Sets the writer.
	 * 
	 * @param writer
	 *            the new writer
	 */
	public void setWriter(Writer writer) {
		this.writer = writer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.GTPInterfaceRaw#showBoard()
	 */
	public BoardI showBoard() {
		this.write(GTPConstants.SHOWBOARD + "\n\n");
		String result = this.readAll();
		if (this.DEBUG)
			System.err.println(result);
		// TODO
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.GTPInterfaceRaw#undo()
	 */
	public boolean undo() {
		try {
			this.write(GTPConstants.UNDO + "\n\n");
			String s = this.read();
			return true;
		} catch (GTPError e) {
			if (e.getMessage().contains(GTPConstants.CANNOTUNDO)) {
				// TODO find a stack of moves to replay here
				return false;
			} else {
				return true;
			}
		}
	}

	/**
	 * Write.
	 * 
	 * @param s
	 *            the s
	 */
	protected synchronized void write(String s) {
		try {
			this.writer.write(s);
			this.writer.flush();
			if (this.DEBUG)
				System.err.println("GnuGo Process Input:\"" + s + "\"");

			this.check();
		} catch (Throwable t) {
			t.printStackTrace();
			System.err.println(t);
		}
	}

}
