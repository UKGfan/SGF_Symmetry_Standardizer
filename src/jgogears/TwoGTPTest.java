/**
 * 
 */
package jgogears;

import junit.framework.TestCase;

// TODO: Auto-generated Javadoc
/**
 * test TwoGTP with a pair of GnuGo Players.
 * 
 * @author syeates
 */
public class TwoGTPTest extends TestCase {

	/**
	 * Test raw.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public void testRaw() throws Exception {
		TwoGTPRaw two = new TwoGTPRaw();
		assertNotNull(two);
		two.setBlack(new GnuGoEngine(9));
		two.setWhite(new GnuGoEngine(9));
		two.playOutGame();
		GTPScore scoreb = two.getBlack().getFinalScore();
		GTPScore scorew = two.getWhite().getFinalScore();
		assertTrue(scoreb + " " + scorew, scoreb.equals(scorew));
	}

}
