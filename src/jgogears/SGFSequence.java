package jgogears;

import java.util.*;

/**
 * The Class SGFSequence.
 */
public class SGFSequence extends Vector<SGFNode> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Vector#toString()
	 */
	@Override
	
	// adding comment here test
	
	public String toString() {
		Iterator<SGFNode> i = this.iterator();
		String result = "";
		while (i.hasNext()) {
			result = result + i.next().toString();
		}
		return result;
	}

}
