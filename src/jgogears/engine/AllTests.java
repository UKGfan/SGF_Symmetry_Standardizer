/**
 * 
 */
package jgogears.engine;

import junit.framework.*;

/**
 * TODO
 * 
 * @author syeates
 * 
 */
public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for jgogears.engine");
		// $JUnit-BEGIN$
		suite.addTestSuite(TrainerTest.class);
		suite.addTestSuite(VertexLineariserTest.class);
		suite.addTestSuite(NodeTest.class);
		suite.addTestSuite(NodeIteratorTest.class);
		suite.addTestSuite(SufogoEngineTest.class);
		suite.addTestSuite(TreeIteratorTest.class);
		suite.addTestSuite(ModelTest.class);
		suite.addTestSuite(StraightVertexLineariserTest.class);
		// $JUnit-END$
		return suite;
	}

}
