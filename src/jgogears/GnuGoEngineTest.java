package jgogears;

import junit.framework.TestCase;

// TODO: Auto-generated Javadoc
/**
 * The Class GnuGoEngineTest.
 * 
 * @author syeates
 * @see GnuGoEngine
 * @see junit.framework.TestCase
 */
public class GnuGoEngineTest extends TestCase {

	/**
	 * Test a.
	 */
	public void testA() {
		if (true)
			return;
		try {
			GnuGoEngine engine = new GnuGoEngine();
			engine.initialise();
			engine.write(GTPConstants.BOARDSIZE + " 19\n\n");
			engine.read();
			engine.write(GTPConstants.CLEARBAORD + "\n\n");
			engine.read();
			for (int i = 0; i < 20; i++) {
				engine.write(GTPConstants.GENMOVE + " BLACK \n\n");
				engine.read();
			}
			engine.write(GTPConstants.SHOWBOARD + "\n\n");

			Thread.sleep(10);
			while (engine.getReader().ready()) {
				engine.readAll();
			}

			engine.getProcess().destroy();
		} catch (Throwable t) {
			t.printStackTrace();
			System.err.println(t);
		}

	}

}
