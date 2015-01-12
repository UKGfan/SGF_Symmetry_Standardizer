package jgogears;

import junit.framework.TestCase;

// TODO: Auto-generated Javadoc
/**
 * The Class GTPScoreTest.
 */
public class GTPScoreTest extends TestCase {

	/**
	 * Test constructor with standard examples.
	 */
	public void testConstructorWithStandardExamples() {
		{
			GTPScore score = new GTPScore("0");
			assertNotNull(score);
			assertTrue(!score.getBlackWin());
			assertTrue(!score.getWhiteWin());
			assertTrue(score.getDraw());
			assertTrue(!score.getTime());
			assertTrue((score.getMargin() == 0.0));
			assertTrue(!score.getVoid());
		}
		{
			GTPScore score = new GTPScore("draw");
			assertNotNull(score);
			assertTrue(!score.getBlackWin());
			assertTrue(!score.getWhiteWin());
			assertTrue(score.getDraw());
			assertTrue(!score.getTime());
			assertTrue((score.getMargin() == 0.0));
			assertTrue(!score.getVoid());
		}
		{
			GTPScore score = new GTPScore("B+");
			assertNotNull(score);
			assertTrue(score.getBlackWin());
			assertTrue(!score.getWhiteWin());
			assertTrue(!score.getDraw());
			assertTrue(!score.getTime());
			assertTrue(score.getMargin() == Double.MAX_VALUE);
			assertTrue(!score.getVoid());
		}
		{
			GTPScore score = new GTPScore("W+");
			assertNotNull(score);
			assertTrue(!score.getBlackWin());
			assertTrue(score.getWhiteWin());
			assertTrue(!score.getDraw());
			assertTrue(!score.getTime());
			assertTrue(score.getMargin() == Double.MAX_VALUE);
			assertTrue(!score.getVoid());
		}
		{
			GTPScore score = new GTPScore("void");
			assertNotNull(score);
			assertTrue(!score.getBlackWin());
			assertTrue(!score.getWhiteWin());
			assertTrue(!score.getDraw());
			assertTrue(!score.getTime());
			assertTrue(score.getVoid());
		}
		{
			GTPScore score = new GTPScore("?");
			assertNotNull(score);
			assertTrue(!score.getBlackWin());
			assertTrue(!score.getWhiteWin());
			assertTrue(!score.getDraw());
			assertTrue(!score.getTime());
			assertTrue(!score.getVoid());
			assertTrue(score.getUnknown());
		}
		{
			GTPScore score = new GTPScore("b+t");
			assertNotNull(score);
			assertTrue(score.getBlackWin());
			assertTrue(!score.getWhiteWin());
			assertTrue(!score.getDraw());
			assertTrue(score.getTime());
		}
		{
			GTPScore score = new GTPScore("w+f");
			assertNotNull(score);
			assertTrue(!score.getBlackWin());
			assertTrue(score.getWhiteWin());
			assertTrue(!score.getDraw());
			assertTrue(!score.getTime());
			assertTrue(score.getForfeit());
		}
		{
			GTPScore score = new GTPScore("b+r");
			assertNotNull(score);
			assertTrue(score.getBlackWin());
			assertTrue(!score.getWhiteWin());
			assertTrue(!score.getDraw());
			assertTrue(!score.getTime());
			assertTrue(!score.getVoid());
			assertTrue(score.getResign());
		}
		{
			GTPScore score = new GTPScore("b+0.5");
			assertNotNull(score);
			assertTrue(score.getBlackWin());
			assertTrue(!score.getWhiteWin());
			assertTrue(!score.getDraw());
			assertTrue(!score.getTime());
			assertTrue(score.getMargin() == 0.5);
			assertTrue(!score.getVoid());
		}
		{
			GTPScore score = new GTPScore("b+457");
			assertNotNull(score);
			assertTrue(score.getBlackWin());
			assertTrue(!score.getWhiteWin());
			assertTrue(!score.getDraw());
			assertTrue(!score.getTime());
			assertTrue(score.getMargin() == 457);
			assertTrue(!score.getVoid());
		}
		{
			GTPScore score = new GTPScore("b+1");
			assertNotNull(score);
			assertTrue(score.getBlackWin());
			assertTrue(!score.getWhiteWin());
			assertTrue(!score.getDraw());
			assertTrue(!score.getTime());
			assertTrue(score.getMargin() == 1.0);
			assertTrue(!score.getVoid());
		}
		{
			GTPScore score = new GTPScore("w+t");
			assertNotNull(score);
			assertTrue(!score.getBlackWin());
			assertTrue(score.getWhiteWin());
			assertTrue(!score.getDraw());
			assertTrue(score.getTime());
			assertTrue(!score.getVoid());
		}
		{
			GTPScore score = new GTPScore("w+0.3");
			assertNotNull(score);
			assertTrue(!score.getBlackWin());
			assertTrue(score.getWhiteWin());
			assertTrue(!score.getDraw());
			assertTrue(!score.getTime());
			assertTrue(score.getMargin() == 0.3);
			assertTrue(!score.getVoid());
		}
		{
			GTPScore score = new GTPScore("w+1.0");
			assertNotNull(score);
			assertTrue(!score.getBlackWin());
			assertTrue(score.getWhiteWin());
			assertTrue(!score.getDraw());
			assertTrue(!score.getTime());
			assertTrue(score.getMargin() == 1.0);
			assertTrue(!score.getVoid());
		}
		{
			GTPScore score = new GTPScore("w+0");
			assertNotNull(score);
			assertTrue(!score.getBlackWin());
			assertTrue(score.getWhiteWin());
			assertTrue(score.getDraw());
			assertTrue(!score.getTime());
			assertTrue(score.getMargin() == 0.0);
			assertTrue(!score.getVoid());
		}
	}
}
