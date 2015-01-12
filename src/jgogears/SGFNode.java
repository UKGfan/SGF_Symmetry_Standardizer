package jgogears;

import java.util.*;

/**
 * The Class SGFNode.
 */
public class SGFNode {

	/**
	 * Column from move string.
	 * 
	 * @param move
	 *            the move
	 * @return the column of the move
	 */
	public static short columnFromMoveString(String move) {
		switch (move.charAt(1)) {
		case 'a':
			return 0;
		case 'b':
			return 1;
		case 'c':
			return 2;
		case 'd':
			return 3;
		case 'e':
			return 4;
		case 'f':
			return 5;
		case 'g':
			return 6;
		case 'h':
			return 7;
		case 'i':
			return 8;
		case 'j':
			return 9;
		case 'k':
			return 10;
		case 'l':
			return 11;
		case 'm':
			return 12;
		case 'n':
			return 13;
		case 'o':
			return 14;
		case 'p':
			return 15;
		case 'q':
			return 16;
		case 'r':
			return 17;
		case 's':
			return 18;
		case 't':
			return 19;
		case 'u':
			return 20;
		case 'v':
			return 21;
		case 'w':
			return 22;
		case 'x':
			return 23;
		case 'y':
			return 24;
		case 'z':
			return 25;
		default:
			throw new Error(move + " " + move.charAt(1));
		}
	}

	/**
	 * Extract the row (second value) from tokens in the range of: [a1]:[t19]
	 * missing the i.
	 * 
	 * @param move
	 *            the move
	 * @return the column
	 */
	public static short columnFromMoveStringWRONG(String move) {
		if (true)
			throw new Error();
		switch (move.charAt(2)) {
		case '1': {
			switch (move.charAt(3)) {
			case ']':
				return 0;
			case '0':
				return 9;
			case '1':
				return 10;
			case '2':
				return 11;
			case '3':
				return 12;
			case '4':
				return 13;
			case '5':
				return 14;
			case '6':
				return 15;
			case '7':
				return 16;
			case '8':
				return 17;
			case '9':
				return 18;
			default:
				throw new Error(move + " " + move.charAt(3));
			}
		}
		case '2': {
			switch (move.charAt(3)) {
			case ']':
				return 1;
			case '0':
				return 19;
			case '1':
				return 20;
			case '2':
				return 21;
			case '3':
				return 22;
			case '4':
				return 23;
			case '5':
				return 24;
			case '6':
				return 25;
			case '7':
				return 26;
			case '8':
				return 27;
			case '9':
				return 28;
			default:
				throw new Error(move + " " + move.charAt(3));
			}
		}
		case '3':
			return 2;
		case '4':
			return 3;
		case '5':
			return 4;
		case '6':
			return 5;
		case '7':
			return 6;
		case '8':
			return 7;
		case '9':
			return 8;
		default:
			throw new Error(move + " " + move.charAt(2));
		}
	}

	/**
	 * Row from move string.
	 * 
	 * @param move
	 *            the move
	 * @return the row of the move
	 */
	public static short rowFromMoveString(String move) {
		switch (move.charAt(2)) {
		case 'a':
			return 0;
		case 'b':
			return 1;
		case 'c':
			return 2;
		case 'd':
			return 3;
		case 'e':
			return 4;
		case 'f':
			return 5;
		case 'g':
			return 6;
		case 'h':
			return 7;
		case 'i':
			return 8;
		case 'j':
			return 9;
		case 'k':
			return 10;
		case 'l':
			return 11;
		case 'm':
			return 12;
		case 'n':
			return 13;
		case 'o':
			return 14;
		case 'p':
			return 15;
		case 'q':
			return 16;
		case 'r':
			return 17;
		case 's':
			return 18;
		case 't':
			return 19;
		case 'u':
			return 20;
		case 'v':
			return 21;
		case 'w':
			return 22;
		case 'x':
			return 23;
		case 'y':
			return 24;
		case 'z':
			return 25;
		default:
			throw new Error(move + " " + move.charAt(2));
		}
	}

	/** The DEBUG. */
	boolean DEBUG = false;

	/** The properties. */
	public Vector<SGFProperty> properties = new Vector<SGFProperty>();

	/**
	 * First property.
	 * 
	 * @return the sGF property
	 */
	public SGFProperty firstProperty() {
		Iterator<SGFProperty> props = this.properties.iterator();
		if (props.hasNext())
			return props.next();
		else
			return null;
	}

	/**
	 * Gets the move.
	 * 
	 * @return the move
	 */
	public Move getMove() {
		Iterator<SGFProperty> iterator = this.properties.iterator();
		while (iterator.hasNext()) {
			Move move = this.getMove(iterator.next());
			if (move != null)
				return move;
		}
		return null;
	}

	/**
	 * Gets the move.
	 * 
	 * @param prop
	 *            the prop
	 * @return the move
	 */
	public Move getMove(SGFProperty prop) {
		if (prop == null)
			return null;
		Move move = new Move();

		if (prop.getIdentifier().compareToIgnoreCase("B") == 0) {
			move.setColour(BoardI.VERTEX_BLACK);
		} else if (prop.getIdentifier().compareToIgnoreCase("W") == 0) {
			move.setColour(BoardI.VERTEX_WHITE);
		} else {
			return null;
		}
		String value = prop.getValues().firstElement();
		if (value.length() <= 2) {
			move.setPass(true);
			return move;
		}
		move.setRow(rowFromMoveString(value.toLowerCase()));
		move.setColumn(columnFromMoveString(value.toLowerCase()));
		// check for [tt] pass
		if (move.getRow() == 19 && move.getColumn() == 19) {
			Move newmove = new Move();
			newmove.setPass(true);
			newmove.setColour(move.getColour());
			if (this.DEBUG)
				System.err.println("converted \"" + this + "\"  to pass");
			move = newmove;
		}
		return move;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String result = ";";
		Iterator<SGFProperty> i = this.properties.iterator();
		while (i.hasNext()) {
			result = result + i.next();
		}
		return result;
	}

}
