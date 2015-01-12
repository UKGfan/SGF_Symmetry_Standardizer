/**
 * 
 */
package jgogears.engine;

import java.util.Iterator;

import jgogears.BoardI;

/**
 * Class to hold a single node in the tree.
 * 
 * @author stuart
 */
public final class Node implements Comparable<Node> {

	/** are we doing expensive checking? */
	private final static boolean CHECKING = true;

	/** are we being verbose? */
	public static boolean DEBUG = false;

	/**
	 * The count. Should be equal to or higher than the sum of the childrens
	 * counts
	 */
	private long played = 0;

	private long notPlayed = 0;
	/** The white child */
	private Node white = null;

	/** The off child */
	private Node off = null;;

	/** The black child */
	private Node black = null;

	/** The empty child */
	private Node empty = null;

	/**
	 * Instantiates a new node.
	 */
	Node() {
	}

	public int compareTo(Node node) {
		if (node == this)
			return 0;
		return node.hashCode() > this.hashCode() ? 1 : -1;
	}

	/**
	 * get the black
	 * 
	 * @return the black
	 */
	public final Node getBlack() {
		return this.black;
	}

	/**
	 * return an iterator over the children of this Node;
	 * 
	 * @return the iterator
	 */
	public Iterator<Node> getChildren() {
		return new NodeIterator(this);
	}

	/**
	 * return an iterator over the decendents of this Node;
	 * 
	 * @return the iterator
	 */
	public Iterator<Node> getDecendents() {
		return new TreeIterator(this);
	}

	/**
	 * get the empty
	 * 
	 * @return the empty
	 */
	public final Node getEmpty() {
		return this.empty;
	}

	// /**
	// * find a particular leaf in this tree
	// * @param board
	// * @param colour
	// * @param row
	// * @param column
	// * @param sym
	// * @param node
	// * @return the leaf node
	// */
	// static public Node getLeaf(BoardI board, short colour, short row, short
	// column, short sym, Node node) {
	// if (board == null)
	// throw new Error();
	// VertexLineariser linear = null;
	// boolean invert = colour == BoardI.VERTEX_WHITE;
	//
	// linear = new VertexLineariser(board, row, column, sym, invert);
	// if (!linear.hasNext())
	// throw new Error();
	// return getLeaf(linear, node);
	//
	// }
	// /**
	// * find a particular leaf in this tree
	// * @param linear the linearisation to use
	// * @param node the root node to use
	// * @return the leaf node
	// */
	//
	// static public Node getLeaf(VertexLineariser linear, Node node) {
	// if (!linear.hasNext())
	// throw new Error();
	//
	// while (linear.hasNext()) {
	// Node child = null;
	// Short colour = linear.next();
	// switch (colour) {
	// case BoardI.VERTEX_BLACK:
	// child = node.black;
	// break;
	// case BoardI.VERTEX_WHITE:
	// child = node.white;
	// break;
	// case BoardI.VERTEX_OFF_BOARD:
	// child = node.off;
	// break;
	// case BoardI.VERTEX_KO:
	// case BoardI.VERTEX_EMPTY:
	// child = node.empty;
	// break;
	// default:
	// throw new Error("Unknown vertex colour: " + colour);
	// }
	// if (child == null)
	// return node;
	// node = child;
	// }
	// return node;
	// }

	/**
	 * how many times has this node not been played?
	 * 
	 * @return the number of times has this node not been played?
	 */
	public final long getNotPlayed() {
		return this.notPlayed;
	}

	/**
	 * get the off
	 * 
	 * @return the off
	 */
	public final Node getOff() {
		return this.off;
	}

	/**
	 * how many times has this node been played?
	 * 
	 * @return the number of times has this node been played?
	 */
	public final long getPlayed() {
		return this.played;
	}

	/**
	 * get the white
	 * 
	 * @return the white
	 */
	public final Node getWhite() {
		return this.white;
	}

	/**
	 * return an iterator over the children of this Node;
	 * 
	 * @return the iterator
	 */
	public Iterator<Node> iterator() {
		return new NodeIterator(this);
	}

	/**
	 * set the black
	 * 
	 * @param black
	 *            the black to set
	 */
	public final void setBlack(Node black) {
		if (CHECKING)
			if (this.white == this)
				throw new Error();
		this.black = black;
	}

	/**
	 * set the empty
	 * 
	 * @param empty
	 *            the empty to set
	 */
	public final void setEmpty(Node empty) {
		if (CHECKING)
			if (this.white == this)
				throw new Error();
		this.empty = empty;
	}

	/**
	 * set the notplayed
	 * 
	 * @param notplayed
	 *            the notplayed to set
	 */
	public final void setNotPlayed(long notplayed) {
		this.notPlayed = notplayed;
	}

	/**
	 * set the off
	 * 
	 * @param off
	 *            the off to set
	 */
	public final void setOff(Node off) {
		if (CHECKING)
			if (this.white == this)
				throw new Error();
		this.off = off;
	}

	/**
	 * set the played
	 * 
	 * @param played
	 *            the played to set
	 */
	public final void setPlayed(long played) {
		this.played = played;
	}

	/**
	 * set the white
	 * 
	 * @param white
	 *            the white to set
	 */
	public final void setWhite(Node white) {
		if (CHECKING)
			if (white == this)
				throw new Error();
		this.white = white;
	}

	/**
	 * get the size of this (sub)tree
	 * 
	 * @return the size
	 */
	public int size() {
		int result = 1;
		if (this.black != null)
			result = result + this.black.size();
		if (this.white != null)
			result = result + this.white.size();
		if (this.empty != null)
			result = result + this.empty.size();
		if (this.off != null)
			result = result + this.off.size();
		return result;
	}

}
