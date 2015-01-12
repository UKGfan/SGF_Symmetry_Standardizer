package jgogears;

import java.io.*;

// TODO: Auto-generated Javadoc
/**
 * The Class GTPController.
 */
public class GTPController {

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the args
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws IOException {
		new GTPController().run();

	}

	/** The reader. */
	java.io.BufferedReader reader = null;

	/** The writer. */
	java.io.Writer writer = null;

	/**
	 * Consume result.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	void consumeResult() throws java.io.IOException {
		while (this.reader.ready()) {
			String s = this.reader.readLine();
			System.err.println("GTPController: \"" + s + "\"");
		}
	}

	/**
	 * Run.
	 * 
	 * @return true, if successful
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	boolean run() throws java.io.IOException {
		PipedWriter farwriter = new PipedWriter();
		this.reader = new BufferedReader(new PipedReader(farwriter));
		PipedReader farreader = new PipedReader();
		this.writer = new PipedWriter(farreader);

		GTPEngine engine = new GTPEngine();
		engine.engine = new RandomEngine();
		engine.reader = new BufferedReader(farreader);
		engine.writer = farwriter;

		Thread thread = new Thread(engine);
		thread.start();

		this.setup();
		while (true) {
			this.consumeResult();
			Thread.yield();
		}
	}

	/**
	 * Setup.
	 * 
	 * @return true, if successful
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	boolean setup() throws java.io.IOException {
		this.writer.write("boardsize 19\n\n");
		this.writer.flush();
		this.consumeResult();
		this.writer.write("clear_board\n\n");
		this.writer.flush();
		this.consumeResult();
		return true;
	}
}
