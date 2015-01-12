package jgogears;

import java.io.*;
import java.util.*;

import jgogears.SGF.ParseException;

/**
 * The Class SGFGameTree. An SGF Game Tree represents zero or more SGF encoded
 * games or game problems
 */
public class SGFGameTree {

	/**
	 * Load an SGF tree from a file
	 * 
	 * @param file
	 *            the file
	 * @return the game
	 */
	public static Game loadFromFile(File file) {
		Reader reader = null;
		try {
			// speed up file reading. BufferedReader doesn't help
			reader = new FileReader(file);
			jgogears.SGF.SGF parser = new jgogears.SGF.SGF(reader);
			SGFGameTree tree = parser.gameTree();
			Game result = new Game(tree);
			reader.close();
			return result;
		} catch (IOException e) {
			System.err.println(e);
			e.printStackTrace();
		} catch (ParseException e) {
			System.err.println(e);
			e.printStackTrace();
		} catch (Throwable e) {
			System.err.println(e);
			e.printStackTrace();
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException e) {
				System.err.println(e);
				e.printStackTrace();
			}
		}
		return null;
	}

	/** The sequence. */
	public SGFSequence sequence = null;

	/** The game trees. */
	public Vector<SGFGameTree> gameTrees = new Vector<SGFGameTree>();

	/**
	 * Inits the.
	 * 
	 * @param game
	 *            the game
	 */
	protected void init(Game game) {
		if (game == null)
			throw new Error("null GoGame in SGFGameTree.init");
		// get the moves on the main branch
		Iterator<SGFNode> i = this.sequence.iterator();
		if (i.hasNext())
			i.next();
		while (i.hasNext()) {
			SGFNode node = i.next();
			Move move = node.getMove();
			if (move != null)
				game.getMovelist().add(move);
		}

		i = this.sequence.iterator();
		if (i.hasNext())
			i.next();
		while (i.hasNext()) {
			int movec = 0;
			SGFNode node = i.next();
			Iterator<SGFProperty> iterator = node.properties.iterator();
			while (iterator.hasNext()) {
				SGFProperty prop = iterator.next();
				if (prop.getIdentifier().compareToIgnoreCase("B") == 0
						|| prop.getIdentifier().compareToIgnoreCase("W") == 0)
					movec++;
				if (prop.getIdentifier().compareToIgnoreCase("C") == 0)
					game.setCommentCount(game.getCommentCount() + 1);
			}
			if (movec > 1)
				game.setBranched(true);
		}

		// get the game level properties
		SGFNode node = this.sequence.firstElement();
		Iterator<SGFProperty> iterator = node.properties.iterator();
		while (iterator.hasNext()) {
			SGFProperty prop = iterator.next();
			if (prop.getIdentifier().compareToIgnoreCase("FF") == 0) {
				String s = prop.firstStripped();
				int value = Integer.parseInt(s);
				if (value != 4 && value != 3)
					throw new Error(
							"wrong version of SGF, we only handle versions 3 and 4");
				continue;
			}
			if (prop.getIdentifier().compareToIgnoreCase("GM") == 0) {
				String s = prop.firstStripped();
				int value = Integer.parseInt(s);
				if (value != 1)
					throw new Error(
							"This is not a Go GoGame, it is some other kind of game");
				continue;
			}
			if (prop.getIdentifier().compareToIgnoreCase("SZ") == 0) {
				String s = prop.firstStripped();
				int value = Integer.parseInt(s);
				game.setSize((short) value);
				continue;
			}
			if (prop.getIdentifier().compareToIgnoreCase("RE") == 0) {
				String s = prop.firstStripped();
				game.setScore(new GTPScore(s));
				continue;
			}
			if (prop.getIdentifier().compareToIgnoreCase("PW") == 0) {
				String s = prop.firstStripped();
				game.setWhitePlayer(s);
				continue;
			}
			if (prop.getIdentifier().compareToIgnoreCase("PB") == 0) {
				String s = prop.firstStripped();
				game.setBlackPlayer(s);
				continue;
			}
			if (prop.getIdentifier().compareToIgnoreCase("BR") == 0) {
				String s = prop.firstStripped();
				// TODO
				// game.getBlackRank(s)
				continue;
			}
			if (prop.getIdentifier().compareToIgnoreCase("WR") == 0) {
				String s = prop.firstStripped();
				// TODO
				// game.setWhiteRank(s);
				continue;
			}
			if (prop.getIdentifier().compareToIgnoreCase("DT") == 0) {
				String s = prop.firstStripped();
				game.setDate(s);
				continue;
			}
			if (prop.getIdentifier().compareToIgnoreCase("RU") == 0) {
				String s = prop.firstStripped();
				game.setRuleset(s);
				continue;
			}
			if (prop.getIdentifier().compareToIgnoreCase("TM") == 0) {
				String s = prop.firstStripped();
				game.setMaintime(s);
				continue;
			}
			if (prop.getIdentifier().compareToIgnoreCase("OT") == 0) {
				String s = prop.firstStripped();
				game.setExtraTime(s);
				continue;
			}
			if (prop.getIdentifier().compareToIgnoreCase("PC") == 0) {
				String s = prop.firstStripped();
				game.setPC(s);
				continue;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String result = "(";
		result = result + this.sequence;
		Iterator<SGFGameTree> i = this.gameTrees.iterator();
		while (i.hasNext()) {
			result = result + i.next().toString();
		}
		result = result + ")";
		return result;
	}

}
