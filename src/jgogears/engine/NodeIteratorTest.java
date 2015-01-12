/**
 * 
 */
package jgogears.engine;

import junit.framework.TestCase;

// TODO: Auto-generated Javadoc
/**
 * Tests for NodeIterator.
 * 
 * @author syeates
 */
public class NodeIteratorTest extends TestCase {

	/**
	 * Test basic.
	 */
	public void testBasic() {
		Node node = new Node();
		assertNotNull(node);
		NodeIterator iter = new NodeIterator(node);
		assertNotNull(iter);
	}

	/**
	 * Test basic10.
	 */
	public void testBasic10() {
		Node node = new Node();
		node.setBlack(new Node());
		node.setWhite(null);
		node.setEmpty(null);
		node.setOff(null);
		assertNotNull(node);
		NodeIterator iter = new NodeIterator(node);
		assertNotNull(iter);
		int count = 0;
		while (iter.hasNext()) {
			Node child = iter.next();
			assertNotNull(child);
			count++;
		}
		assertTrue(count == 1);
	}

	/**
	 * Test basic11.
	 */
	public void testBasic11() {
		Node node = new Node();
		node.setBlack(null);
		node.setWhite(new Node());
		node.setEmpty(null);
		node.setOff(null);
		assertNotNull(node);
		NodeIterator iter = new NodeIterator(node);
		assertNotNull(iter);
		int count = 0;
		while (iter.hasNext()) {
			Node child = iter.next();
			assertNotNull(child);
			count++;
		}
		assertTrue(count == 1);
	}

	/**
	 * Test basic12.
	 */
	public void testBasic12() {
		Node node = new Node();
		node.setBlack(null);
		node.setWhite(null);
		node.setEmpty(new Node());
		node.setOff(null);
		assertNotNull(node);
		NodeIterator iter = new NodeIterator(node);
		assertNotNull(iter);
		int count = 0;
		while (iter.hasNext()) {
			Node child = iter.next();
			assertNotNull(child);
			count++;
		}
		assertTrue(count == 1);
	}

	/**
	 * Test basic13.
	 */
	public void testBasic13() {
		Node node = new Node();
		node.setBlack(null);
		node.setWhite(null);
		node.setEmpty(null);
		node.setOff(new Node());
		assertNotNull(node);
		NodeIterator iter = new NodeIterator(node);
		assertNotNull(iter);
		int count = 0;
		while (iter.hasNext()) {
			Node child = iter.next();
			assertNotNull(child);
			count++;
		}
		assertTrue(count == 1);
	}

	/**
	 * Test basic3.
	 */
	public void testBasic3() {
		Node node = new Node();
		node.setBlack(null);
		node.setWhite(new Node());
		node.setEmpty(new Node());
		node.setOff(new Node());
		assertNotNull(node);
		NodeIterator iter = new NodeIterator(node);
		assertNotNull(iter);
		int count = 0;
		while (iter.hasNext()) {
			Node child = iter.next();
			assertNotNull(child);
			count++;
		}
		assertTrue(count == 3);
	}

	/**
	 * Test basic4.
	 */
	public void testBasic4() {
		Node node = new Node();
		node.setBlack(new Node());
		node.setWhite(null);
		node.setEmpty(new Node());
		node.setOff(new Node());
		assertNotNull(node);
		NodeIterator iter = new NodeIterator(node);
		assertNotNull(iter);
		int count = 0;
		while (iter.hasNext()) {
			Node child = iter.next();
			assertNotNull(child);
			count++;
		}
		assertTrue(count == 3);
	}

	/**
	 * Test basic5.
	 */
	public void testBasic5() {
		Node node = new Node();
		node.setBlack(new Node());
		node.setWhite(new Node());
		node.setEmpty(null);
		node.setOff(new Node());
		assertNotNull(node);
		NodeIterator iter = new NodeIterator(node);
		assertNotNull(iter);
		int count = 0;
		while (iter.hasNext()) {
			Node child = iter.next();
			assertNotNull(child);
			count++;
		}
		assertTrue(count == 3);
	}

	/**
	 * Test basic6.
	 */
	public void testBasic6() {
		Node node = new Node();
		node.setBlack(new Node());
		node.setWhite(new Node());
		node.setEmpty(new Node());
		node.setOff(null);
		assertNotNull(node);
		NodeIterator iter = new NodeIterator(node);
		assertNotNull(iter);
		int count = 0;
		while (iter.hasNext()) {
			Node child = iter.next();
			assertNotNull(child);
			count++;
		}
		assertTrue(count == 3);
	}

	/**
	 * Test basic7.
	 */
	public void testBasic7() {
		Node node = new Node();
		node.setBlack(new Node());
		node.setWhite(new Node());
		node.setEmpty(null);
		node.setOff(null);
		assertNotNull(node);
		NodeIterator iter = new NodeIterator(node);
		assertNotNull(iter);
		int count = 0;
		while (iter.hasNext()) {
			Node child = iter.next();
			assertNotNull(child);
			count++;
		}
		assertTrue(count == 2);
	}

	/**
	 * Test basic8.
	 */
	public void testBasic8() {
		Node node = new Node();
		node.setBlack(null);
		node.setWhite(null);
		node.setEmpty(new Node());
		node.setOff(new Node());
		assertNotNull(node);
		NodeIterator iter = new NodeIterator(node);
		assertNotNull(iter);
		int count = 0;
		while (iter.hasNext()) {
			Node child = iter.next();
			assertNotNull(child);
			count++;
		}
		assertTrue(count == 2);
	}

	/**
	 * Test basic9.
	 */
	public void testBasic9() {
		Node node = new Node();
		node.setBlack(new Node());
		node.setWhite(null);
		node.setEmpty(null);
		node.setOff(new Node());
		assertNotNull(node);
		NodeIterator iter = new NodeIterator(node);
		assertNotNull(iter);
		int count = 0;
		while (iter.hasNext()) {
			Node child = iter.next();
			assertNotNull(child);
			count++;
		}
		assertTrue(count == 2);
	}

	/**
	 * Test basic i.
	 */
	public void testBasicI() {
		Node node = new Node();
		assertNotNull(node);
		NodeIterator iter = new NodeIterator(node);
		assertNotNull(iter);
		assertFalse(iter.hasNext());
	}

	/**
	 * Test remove.
	 */
	public void testRemove() {
		Node node = new Node();
		node.setBlack(null);
		node.setWhite(null);
		node.setEmpty(null);
		node.setOff(new Node());
		assertNotNull(node);
		NodeIterator iter = new NodeIterator(node);
		assertNotNull(iter);
		try {
			iter.remove();
			fail();
		} catch (Throwable t) {

		}
	}

}
