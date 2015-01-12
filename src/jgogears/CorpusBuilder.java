package jgogears;

import java.io.*;
import java.util.*;
import java.util.zip.*;

/**
 * The Class CorpusBuilder. troll through a directory hierarchy containing SGF
 * files and archives of scoff files. Each file is parsed as an SGF file and
 * copied to a directory if it meets the tests
 */
public class CorpusBuilder {

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 * @throws Exception
	 *             the exception
	 */
	public static void main(String[] args) throws Exception {
		if (args.length > 0) {
			System.err.println("arguments are not examined ");
		}

		CorpusBuilder builder = new CorpusBuilder();
		builder.files.push(new File(builder.from));

	}

	/**
	 * Mainold.
	 * 
	 * @param args
	 *            the args
	 * @throws Exception
	 *             the exception
	 */
	public static void mainold(String[] args) throws Exception {
		if (args.length > 0) {
			System.err.println("arguments are not examined ");
		}
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

	/** The from. */
	public String from = "sgf/zipped";

	/** The to. */
	public String to = "sgf/archive";

	/** The files. */
	public Stack<File> files = new Stack<File>();

	/** The BUFFER. */
	final int BUFFER = 2048;

	/**
	 * Examinefiles.
	 */
	public void examinefiles() {
		while (this.files.size() > 0) {
			File file = this.files.pop();
			if (file.isDirectory()) {
				File[] fs = file.listFiles();
				for (int i = 0; i < fs.length; i++)
					this.files.add(fs[i]);
			} else if (file.getName().endsWith(".zip")) {
				this.process(file);
			}
		}
	}

	/**
	 * Process.
	 * 
	 * @param file
	 *            the file
	 */
	void process(File file) {

		try {
			if (!file.getName().endsWith(".zip"))
				throw new Error();

			BufferedOutputStream dest = null;
			FileInputStream fis = new FileInputStream(file);
			ZipInputStream zis = new ZipInputStream(
					new BufferedInputStream(fis));
			ZipEntry entry;
			while ((entry = zis.getNextEntry()) != null) {
				System.out.println("Extracting: " + entry);
				int count;
				byte data[] = new byte[this.BUFFER];
				// write the files to the disk
				FileOutputStream fos = new FileOutputStream(entry.getName());
				dest = new BufferedOutputStream(fos, this.BUFFER);
				while ((count = zis.read(data, 0, this.BUFFER)) != -1) {
					dest.write(data, 0, count);
				}
				dest.flush();
				dest.close();
			}
			zis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * The Class SGFFilter.
	 */
	class SGFFilter implements FilenameFilter {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.io.FilenameFilter#accept(java.io.File, java.lang.String)
		 */
		public boolean accept(File dir, String name) {
			return name.endsWith(".sgf");
		}
	}

	/**
	 * The Class ZIPFilter.
	 */
	class ZIPFilter implements FilenameFilter {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.io.FilenameFilter#accept(java.io.File, java.lang.String)
		 */
		public boolean accept(File dir, String name) {
			return name.endsWith(".zip");
		}
	}
}
