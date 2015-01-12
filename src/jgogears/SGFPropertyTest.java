package jgogears;

import junit.framework.TestCase;

// TODO: Auto-generated Javadoc
/**
 * The Class SGFPropertyTest.
 */
public class SGFPropertyTest extends TestCase {

	/**
	 * Test strip.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public void testStrip() throws Exception {
		String a1 = "[19]";
		String a2 = "19";
		assertTrue(SGFProperty.stripSquareBrackets(a1).compareTo(a2) == 0);
		assertFalse(SGFProperty.stripSquareBrackets(a2).compareTo(a1) == 0);
		assertTrue(SGFProperty.stripSquareBrackets(a2).compareTo(a2) == 0);
		assertFalse(SGFProperty.stripSquareBrackets(a1).compareTo(a1) == 0);

	}

}
