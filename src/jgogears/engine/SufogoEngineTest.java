/**
 * 
 */
package jgogears.engine;

import jgogears.*;
import junit.framework.TestCase;

// TODO: Auto-generated Javadoc
/**
 * test TwoGTP with a pair of GnuGo Players.
 * 
 * @author syeates
 */
public class SufogoEngineTest extends TestCase {

	/** The Constant DEBUG. */
	static final boolean DEBUG = false;

	/**
	 * Test simple.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public void testSimple() throws Exception {

		GTPState state = new GTPState();
		Model model = new Model();
		Trainer trainer = new Trainer();
		trainer.setModel(model);
		trainer.train(10);

		SufgoEngine black = new SufgoEngine();
		black.setModel(model);

		SufgoEngine white = new SufgoEngine();
		white.setModel(model);

		TwoGTP two = new TwoGTP();
		assertNotNull(two);
		two.setBlack(black);
		two.setWhite(white);
		for (int i = 0; i < 20; i++) {
			state = two.move();
			assertNotNull(state);
			assertNotNull(state.getBoard());
			if (DEBUG)
				System.err.println(state.getBoard());
		}
	}
}
