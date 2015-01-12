/**
 * 
 */
package jgogears.engine;

import junit.framework.TestCase;

/**
 * Test class Node
 * 
 * @author syeates
 */
public class NodeTest extends TestCase {

	/**
	 * Test method for {@link jgogears.engine.Node#getBlack()}.
	 */
	public void testGetBlack() {
		Node parent = new Node();
		assertNotNull(parent);
		Node child = new Node();
		assertNotNull(child);
		parent.setBlack(child);
		assertTrue(parent.getBlack() == child);
		parent.setBlack(null);
		assertNull(parent.getBlack());
	}

	/**
	 * 
	 */
	public void testGetCount() {
		Node node = new Node();
		Node node2 = new Node();
		assertNotNull(node);
		assertNotNull(node2);
		assertTrue(node.getPlayed() == 0);
		assertTrue(node2.getPlayed() == 0);
		node.setPlayed(10);
		node2.setPlayed(10);
		assertTrue(node.getPlayed() == 10);
		assertTrue(node2.getPlayed() == 10);
	}

	/**
	 * Test method for {@link jgogears.engine.Node#getEmpty()}.
	 */
	public void testGetEmpty() {
		Node parent = new Node();
		assertNotNull(parent);
		Node child = new Node();
		assertNotNull(child);
		parent.setEmpty(child);
		assertTrue(parent.getEmpty() == child);
		parent.setEmpty(null);
		assertNull(parent.getEmpty());
	}

	/**
	 * Test method for {@link jgogears.engine.Node#getOff()}.
	 */
	public void testGetOff() {
		Node parent = new Node();
		assertNotNull(parent);
		Node child = new Node();
		assertNotNull(child);
		parent.setOff(child);
		assertTrue(parent.getOff() == child);
		parent.setOff(null);
		assertNull(parent.getOff());

	}

	/**
	 * Test method for {@link jgogears.engine.Node#getWhite()}.
	 */
	public void testGetWhite() {
		Node parent = new Node();
		assertNotNull(parent);
		Node child = new Node();
		assertNotNull(child);
		parent.setWhite(child);
		assertTrue(parent.getWhite() == child);
		parent.setWhite(null);
		assertNull(parent.getWhite());

	}

	/**
	 * Test method for {@link jgogears.engine.Node#Node()}.
	 */
	public void testNode() {
		Node node = new Node();
		assertNotNull(node);
	}

	/**
	 * Test method for
	 * {@link jgogears.engine.Node#setBlack(jgogears.engine.Node)}.
	 */
	public void testSetBlack() {
		Node parent = new Node();
		assertNotNull(parent);
		Node child = new Node();
		assertNotNull(child);
		parent.setBlack(child);
		assertTrue(parent.getBlack() == child);
		parent.setBlack(null);
		assertNull(parent.getBlack());
	}

	/**
	 * 
	 */
	public void testSetCount() {
		Node node = new Node();
		Node node2 = new Node();
		assertNotNull(node);
		assertNotNull(node2);
		assertTrue(node.getPlayed() == 0);
		assertTrue(node2.getPlayed() == 0);
		node.setPlayed(10);
		node2.setPlayed(10);
		assertTrue(node.getPlayed() == 10);
		assertTrue(node2.getPlayed() == 10);
	}

	/**
	 * Test method for
	 * {@link jgogears.engine.Node#setEmpty(jgogears.engine.Node)}.
	 */
	public void testSetEmpty() {
		Node parent = new Node();
		assertNotNull(parent);
		Node child = new Node();
		assertNotNull(child);
		parent.setEmpty(child);
		assertTrue(parent.getEmpty() == child);
		parent.setEmpty(null);
		assertNull(parent.getEmpty());
	}

	/**
	 * Test method for {@link jgogears.engine.Node#setOff(jgogears.engine.Node)}
	 * .
	 */
	public void testSetOff() {
		Node parent = new Node();
		assertNotNull(parent);
		Node child = new Node();
		assertNotNull(child);
		parent.setOff(child);
		assertTrue(parent.getOff() == child);
		parent.setOff(null);
		assertNull(parent.getOff());
	}

	/**
	 * Test method for
	 * {@link jgogears.engine.Node#setWhite(jgogears.engine.Node)}.
	 */
	public void testSetWhite() {
		Node parent = new Node();
		assertNotNull(parent);
		Node child = new Node();
		assertNotNull(child);
		parent.setWhite(child);
		assertTrue(parent.getWhite() == child);
		parent.setWhite(null);
		assertNull(parent.getWhite());

	}

}
