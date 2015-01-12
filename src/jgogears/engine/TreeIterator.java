/**
 * 
 */
package jgogears.engine;

import java.util.*;

/**
 * An iterator for all decendents of a node
 * 
 * @author syeates
 */

public class TreeIterator implements Iterator<Node> {

	/** The node we're iterating over */
	private final Stack<Node> nodes = new Stack<Node>();

	/**
	 * public constructor.
	 * 
	 * @param node
	 *            the Node to iterator over
	 */
	public TreeIterator(Node node) {
		Iterator<Node> iter = node.getChildren();
		while (iter.hasNext()) {
			this.nodes.push(iter.next());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#hasNext()
	 */
	public boolean hasNext() {
		return this.nodes.size() > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#next()
	 */
	public Node next() {
		Node result = this.nodes.pop();
		Iterator<Node> iter = result.getChildren();
		while (iter.hasNext()) {
			this.nodes.push(iter.next());
		}
		return result;
	}

	/**
	 * Not implemented. Throws an Error if called
	 */
	public void remove() {
		throw new Error("Not implemented");
	}

}
