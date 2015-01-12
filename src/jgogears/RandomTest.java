/**
 * 
 */
package jgogears;

import junit.framework.TestCase;

/**
 * TODO
 * 
 * @author syeates
 */
public class RandomTest extends TestCase {
	public void testisLarger() {
		assertTrue(Random.isLarger(9.0, 1.0));
		assertTrue(Random.isLarger(1.1, 1.0));
		assertTrue(Random.isLarger(1.2, 1.0));
		assertTrue(Random.isLarger(0.2, 0.0));
		assertTrue(Random.isLarger(0.2, 0.1));

		assertFalse(Random.isLarger(1.0, 9.0));
		assertFalse(Random.isLarger(1.0, 1.1));
		assertFalse(Random.isLarger(1.0, 1.2));
		assertFalse(Random.isLarger(0.0, 0.2));
		assertFalse(Random.isLarger(0.1, 0.2));

		while (Random.isLarger(0.1, 0.1))
			;
		while (!Random.isLarger(0.1, 0.1))
			;

	}

}
