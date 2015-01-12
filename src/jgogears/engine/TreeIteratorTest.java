/**
 * 
 */
package jgogears.engine;

import java.util.*;

import junit.framework.TestCase;

// TODO: Auto-generated Javadoc
/**
 * Tests for NodeIterator.
 * 
 * @author syeates
 */
public class TreeIteratorTest extends TestCase {

	/**
	 * Count size.
	 * 
	 * @param nodes
	 *            the nodes
	 * @return the int
	 */
	int countSize(Iterator<Node> nodes) {
		int count = 0;
		while (nodes.hasNext()) {
			nodes.next();
			count++;
		}
		return count;
	}

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
	public void testRecursive() {
		Node node = new Node();
		node.setBlack(null);
		node.setWhite(null);
		node.setEmpty(null);
		node.setOff(null);
		assertNotNull(node);

		Node b = new Node();
		node.setBlack(b);
		assertTrue(this.countSize(node.getDecendents()) == 1);
		assertTrue(this.unique(node.getDecendents()));
		Node w = new Node();
		node.setWhite(w);
		assertTrue(this.countSize(node.getDecendents()) == 2);
		assertTrue(this.unique(node.getDecendents()));
		Node e = new Node();
		node.setEmpty(e);
		assertTrue(this.countSize(node.getDecendents()) == 3);
		assertTrue(this.unique(node.getDecendents()));
		Node o = new Node();
		node.setOff(o);
		assertTrue(this.countSize(node.getDecendents()) == 4);
		assertTrue(this.unique(node.getDecendents()));

		Node bb = new Node();
		b.setBlack(bb);
		assertTrue(this.countSize(node.getDecendents()) == 5);
		assertTrue(this.unique(node.getDecendents()));
		Node bw = new Node();
		b.setWhite(bw);
		assertTrue(this.countSize(node.getDecendents()) == 6);
		assertTrue(this.unique(node.getDecendents()));
		Node be = new Node();
		b.setEmpty(be);
		assertTrue(this.countSize(node.getDecendents()) == 7);
		assertTrue(this.unique(node.getDecendents()));
		Node bo = new Node();
		b.setOff(bo);
		assertTrue(this.countSize(node.getDecendents()) == 8);
		assertTrue(this.unique(node.getDecendents()));

		Node wb = new Node();
		w.setBlack(wb);
		assertTrue(this.countSize(node.getDecendents()) == 9);
		assertTrue(this.unique(node.getDecendents()));
		Node ww = new Node();
		w.setWhite(ww);
		assertTrue(this.countSize(node.getDecendents()) == 10);
		assertTrue(this.unique(node.getDecendents()));
		Node we = new Node();
		w.setEmpty(we);
		assertTrue(this.countSize(node.getDecendents()) == 11);
		assertTrue(this.unique(node.getDecendents()));
		Node wo = new Node();
		w.setOff(wo);
		assertTrue(this.countSize(node.getDecendents()) == 12);
		assertTrue(this.unique(node.getDecendents()));

		Node eb = new Node();
		e.setBlack(eb);
		assertTrue(this.countSize(node.getDecendents()) == 13);
		assertTrue(this.unique(node.getDecendents()));
		Node ew = new Node();
		e.setWhite(ew);
		assertTrue(this.countSize(node.getDecendents()) == 14);
		assertTrue(this.unique(node.getDecendents()));
		Node ee = new Node();
		e.setEmpty(ee);
		assertTrue(this.countSize(node.getDecendents()) == 15);
		assertTrue(this.unique(node.getDecendents()));
		Node eo = new Node();
		e.setOff(eo);
		assertTrue(this.countSize(node.getDecendents()) == 16);
		assertTrue(this.unique(node.getDecendents()));

		Node ob = new Node();
		o.setBlack(ob);
		assertTrue(this.countSize(node.getDecendents()) == 17);
		Node ow = new Node();
		o.setWhite(ow);
		assertTrue(this.countSize(node.getDecendents()) == 18);
		Node oe = new Node();
		o.setEmpty(oe);
		assertTrue(this.countSize(node.getDecendents()) == 19);
		Node oo = new Node();
		o.setOff(oo);
		assertTrue(this.countSize(node.getDecendents()) == 20);

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

	/**
	 * Unique.
	 * 
	 * @param nodes
	 *            the nodes
	 * @return true, if successful
	 */
	boolean unique(Iterator<Node> nodes) {
		TreeSet<Node> set = new TreeSet<Node>();
		while (nodes.hasNext()) {
			Node current = nodes.next();
			if (set.contains(current))
				return false;
			set.add(current);
		}
		return true;

	}

}
