package jgogears;

import jgogears.engine.*;
import junit.framework.*;

// TODO: Auto-generated Javadoc
/**
 * Manually created test suite of all useful tests in the project, across all
 * packages. The default test suite for the project.
 * 
 * @author Stuart
 */

public class GlobalTests {

	/**
	 * test collection.
	 * 
	 * @return the test
	 * @author syeates
	 */

	public static Test suite() {
		TestSuite suite = new TestSuite("all tests in project");
		suite.addTestSuite(jgogears.AllTests.class);
		suite.addTestSuite(jgogears.engine.AllTests.class);
		return suite;
	}

}
