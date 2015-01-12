package jgogears;

import java.util.Vector;

/**
 * A class representing a vertex on the board. There is no representation of
 * colour or the occupancy of the vertex. TODO write some tests for this
 * 
 * @author Stuart
 */
public final class Vertex extends Vector<Short> implements Comparable<Vertex> {
	/** are we spewing out lots of debugging info? */
	static final boolean DEBUG = false;

	/**
	 * Short cut constructor with ints rather than shorts.
	 * 
	 * @param row
	 *            the row
	 * @param column
	 *            the column
	 */
	public Vertex(int row, int column) {
		this.add((short) row);
		this.add((short) column);
	}

	/**
	 * Preferred constuctor.
	 * 
	 * @param row
	 *            the row
	 * @param column
	 *            the column
	 */
	public Vertex(short row, short column) {
		this.add(row);
		this.add(column);
	}

	/**
	 * Constuctor based on a string.
	 * 
	 * @param vertexString
	 *            the vertex string
	 */
	public Vertex(String vertexString) {
		if (DEBUG)
			System.err.println("parseVertex(\"" + vertexString + "\")");
		vertexString = vertexString.toLowerCase();
		switch (vertexString.charAt(0)) {
		case 'a':
			this.setRow(0);
			break;
		case 'b':
			this.setRow(1);
			break;
		case 'c':
			this.setRow(2);
			break;
		case 'd':
			this.setRow(3);
			break;
		case 'e':
			this.setRow(4);
			break;
		case 'f':
			this.setRow(5);
			break;
		case 'g':
			this.setRow(6);
			break;
		case 'h':
			this.setRow(7);
			break;
		case 'j':
			this.setRow(8);
			break;
		case 'k':
			this.setRow(9);
			break;
		case 'l':
			this.setRow(10);
			break;
		case 'm':
			this.setRow(11);
			break;
		case 'n':
			this.setRow(12);
			break;
		case 'o':
			this.setRow(13);
			break;
		case 'p':
			this.setRow(14);
			break;
		case 'q':
			this.setRow(15);
			break;
		case 'r':
			this.setRow(16);
			break;
		case 's':
			this.setRow(17);
			break;
		case 't':
			this.setRow(18);
			break;
		case 'u':
			this.setRow(19);
			break;
		case 'v':
			this.setRow(20);
			break;
		case 'w':
			this.setRow(21);
			break;
		case 'x':
			this.setRow(22);
			break;
		case 'y':
			this.setRow(23);
			break;
		case 'z':
			this.setRow(24);
			break;
		default:
			throw new IllegalArgumentException("trying to parse (3) \""
					+ vertexString + "\"");

		}
		if (vertexString.length() == 2) {
			this.setColumn((vertexString.charAt(1) - '1'));
		} else if (vertexString.length() == 3) {
			this.setColumn((vertexString.charAt(1) - '0') * 10
					+ vertexString.charAt(2) - '1');
		} else
			throw new IllegalArgumentException("trying to parse (4) \""
					+ vertexString + "\", \"" + vertexString + "\"");
		if (DEBUG)
			System.err.println(this);
	}

	/**
	 * Comparison operator to ensure that (in)equality operators work as
	 * expected.
	 * 
	 * @param v
	 *            the v
	 * @return the int
	 */
	public int compareTo(Vertex v) {

		if (v.getRow() > this.getRow())
			return 1;
		if (v.getRow() < this.getRow())
			return -1;
		if (v.getColumn() > this.getColumn())
			return 1;
		if (v.getColumn() < this.getColumn())
			return -1;
		return 0;
	}

	/**
	 * equality operator to ensure that two different vertex objects
	 * representing the same vertex are recognised as being equal.
	 * 
	 * @param o
	 *            the o
	 * @return true, if equals
	 */
	@Override
	public boolean equals(Object o) {
		if (this == null && o == null)
			return true;
		if (o == null || this == null)
			return false;
		if (o.getClass() != this.getClass())
			return super.equals(o);
		try {
			Vertex v = (Vertex) o;
			if (v.getRow() == this.getRow()
					&& v.getColumn() == this.getColumn())
				return true;
			else
				return false;
		} catch (Throwable t) {
			return false;
		}
	}

	/**
	 * Get the column of this vertex.
	 * 
	 * @return the column of this vertex
	 */
	public short getColumn() {
		if (this.size() > 1)
			return this.elementAt(1);
		else
			return Short.MIN_VALUE;
	}

	/**
	 * Get the row of this vertex.
	 * 
	 * @return the row of this vertex
	 */
	public short getRow() {
		if (this.size() > 0)
			return this.elementAt(0);
		else
			return Short.MIN_VALUE;
	}

	/**
	 * set the column of this vertex.
	 * 
	 * @param column
	 *            the column
	 */
	private void setColumn(int column) {
		short row = this.getRow();
		this.clear();
		this.add(row);
		this.add((short) column);
	}

	/**
	 * Set the row of this vertex.
	 * 
	 * @param row
	 *            the row
	 */
	private void setRow(int row) {
		short column = this.getColumn();
		this.clear();
		this.add((short) row);
		this.add(column);
	}

}
