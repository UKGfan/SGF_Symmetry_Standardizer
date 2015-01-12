/**
 * 
 */
package jgogears;

/**
 * A random number generator
 * 
 * @author syeates
 */
public class Random {
	/** The random number generator */
	private static final java.util.Random random = new java.util.Random(
			new java.util.Date().getTime());
	/** A small value used as a small increment to probabilities */
	public static final double DELTA = (double) 0.01;

	/**
	 * get the next double
	 * 
	 * @return the next double
	 */
	static public final double nextDouble() {
		double r = random.nextDouble();
		return r;
	}

	/**
	 * get the next boolean
	 * 
	 * @return the next boolean
	 */
	static public final boolean nextBoolean() {
		boolean r = random.nextBoolean();
		return r;
	}

	/**
	 * get the next integer
	 * 
	 * @param max
	 *            the maximum integer
	 * @return the next integer
	 */
	static public final int nextInt(int max) {
		int r = random.nextInt(max);
		return r;
	}

	/**
	 * Gets the random delta.
	 * 
	 * @return the random delta
	 */
	static public final double getRandomDelta() {
		double r = random.nextDouble();
		return r * DELTA;
	}

	/**
	 * a randomised max function
	 * 
	 * @param first
	 *            the first double
	 * @param second
	 *            the second double
	 * @return the largest or a random value if equal
	 */
	static public final double getRandomBest(double first, double second) {
		if (first > second)
			return first;
		if (second > first)
			return second;
		return first;
	}

	/**
	 * a randomised max function
	 * 
	 * @param first
	 *            the first double
	 * @param second
	 *            the second double
	 * @return the largest or a random value if equal
	 */
	static public final boolean isLarger(double first, double second) {
		if (first > second)
			return true;
		if (second > first)
			return false;
		return nextBoolean();
	}

}
