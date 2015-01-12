package jgogears;

import junit.framework.TestCase;

// TODO: Auto-generated Javadoc
/**
 * The Class SGFNodeTest.
 */
public class SGFNodeTest extends TestCase {

	/*
	 * Test method for 'jgogears.SGFNode.columnFromMoveString(String)'
	 */
	/**
	 * Test from move string.
	 */
	public void testFromMoveString() {
		String example = "[aa]";
		assertTrue(SGFNode.columnFromMoveString(example) == 0);
		assertTrue(SGFNode.rowFromMoveString(example) == 0);

		example = "[aa]";
		assertTrue(SGFNode.columnFromMoveString(example) == 0);
		assertTrue(SGFNode.rowFromMoveString(example) == 0);

		example = "[bb]";
		assertTrue(SGFNode.columnFromMoveString(example) == 1);
		assertTrue(SGFNode.rowFromMoveString(example) == 1);

		example = "[ag]";
		assertTrue(SGFNode.columnFromMoveString(example) == 0);
		assertTrue(SGFNode.rowFromMoveString(example) == 6);

		example = "[ss]";
		assertTrue(SGFNode.columnFromMoveString(example) == 18);
		assertTrue(SGFNode.rowFromMoveString(example) == 18);

	}

}
