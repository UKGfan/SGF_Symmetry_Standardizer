/**
 * 
 */
package jgogears.engine;

import java.util.Iterator;

/**
 * An iterator for nodes.
 * 
 * @author syeates
 */

public class NodeIterator implements Iterator<Node> {

	/** The node we're iterating over */
	private Node node = null;

	/** indicator of where we are */
	private short next = 0;

	/**
	 * public constructor.
	 * 
	 * @param node
	 *            the Node to iterator over
	 */
	public NodeIterator(Node node) {
		this.node = node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#hasNext()
	 */
	public boolean hasNext() {
		switch (this.next) {
		case 0:
			if (this.node.getBlack() != null)
				return true;
		case 1:
			if (this.node.getWhite() != null)
				return true;
		case 2:
			if (this.node.getEmpty() != null)
				return true;
		case 3:
			if (this.node.getOff() != null)
				return true;
		default:
			return false;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#next()
	 */
	public Node next() {
		switch (this.next) {
		case 0:
			this.next++;
			if (this.node.getBlack() != null)
				return this.node.getBlack();
		case 1:
			this.next++;
			if (this.node.getWhite() != null)
				return this.node.getWhite();
		case 2:
			this.next++;
			if (this.node.getEmpty() != null)
				return this.node.getEmpty();
		case 3:
			this.next++;
			if (this.node.getOff() != null)
				return this.node.getOff();
		default:
			throw new Error("nothing left");

		}

	}

	/**
	 * Not implemented. Throws an Error if called
	 */
	public void remove() {
		throw new Error("Not implemented");
	}

}
