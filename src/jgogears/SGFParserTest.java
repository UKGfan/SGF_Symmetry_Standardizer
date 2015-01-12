package jgogears;

import java.io.StringReader;

import jgogears.SGF.ParseException;
import junit.framework.TestCase;

// TODO: Auto-generated Javadoc
/**
 * The Class SGFParserTest.
 */
public class SGFParserTest extends TestCase {

	/** The BADEXAMPLES. */
	public static String BADEXAMPLES[] = { "(;", "();", ";)", "()", "$$", "" };

	/*
	 * Test method
	 */
	/**
	 * Test a.
	 * 
	 * @throws ParseException
	 *             the parse exception
	 */
	public void testA() throws ParseException {
		String example = SGFParser.EXAMPLEA;
		StringReader reader = new StringReader(example);
		jgogears.SGF.SGF parser = new jgogears.SGF.SGF(reader);
		parser.gameTree();
	}

	/**
	 * Test bad repeat.
	 * 
	 * @throws ParseException
	 *             the parse exception
	 */
	public void testBADRepeat() throws ParseException {
		String examples[] = BADEXAMPLES;
		for (int i = 0; i < examples.length; i++) {
			try {
				String example = examples[i];
				StringReader reader = new StringReader(example);
				jgogears.SGF.SGF parser = new jgogears.SGF.SGF(reader);

				assertTrue(parser != null);
				assertTrue(parser.toString() != null);
				assertTrue(parser.toString().length() > 0);
				assertTrue(parser.toString().length() > 0);
				assertTrue(parser.toString().compareTo(parser.toString()) == 0);

				String result = parser.gameTree().toString();
				reader = new StringReader(result);
				parser = new jgogears.SGF.SGF(reader);
				String result2 = parser.gameTree().toString();

				assertTrue(parser != null);
				assertTrue(parser.toString() != null);
				assertTrue(parser.toString().length() > 0);

				assertTrue(result.compareTo(result2) == 0);
			} catch (ParseException p) {
				assertTrue(true);
				continue;
			} catch (jgogears.SGF.TokenMgrError e) {
				assertTrue(true);
				continue;
			}
			// we chould never reach here
			assertTrue("Bad SGF file accepted \"" + BADEXAMPLES[i] + "\"",
					false);
		}
	}

	/*
	 * Test method
	 */
	/**
	 * Test five.
	 * 
	 * @throws ParseException
	 *             the parse exception
	 */
	public void testFive() throws ParseException {
		String example = SGFParser.EXAMPLEFIVE;
		StringReader reader = new StringReader(example);
		jgogears.SGF.SGF parser = new jgogears.SGF.SGF(reader);
		parser.gameTree();

		assertTrue(parser != null);
		assertTrue(parser.toString() != null);
		assertTrue(parser.toString().length() > 0);
	}

	/*
	 * Test method
	 */
	/**
	 * Test four.
	 * 
	 * @throws ParseException
	 *             the parse exception
	 */
	public void testFour() throws ParseException {
		String example = SGFParser.EXAMPLEFOUR;
		StringReader reader = new StringReader(example);
		jgogears.SGF.SGF parser = new jgogears.SGF.SGF(reader);
		parser.gameTree();

		assertTrue(parser != null);
		assertTrue(parser.toString() != null);
		assertTrue(parser.toString().length() > 0);
	}

	/*
	 * Test method
	 */
	/**
	 * Test one.
	 * 
	 * @throws ParseException
	 *             the parse exception
	 */
	public void testOne() throws ParseException {
		String example = SGFParser.EXAMPLEONE;
		StringReader reader = new StringReader(example);
		jgogears.SGF.SGF parser = new jgogears.SGF.SGF(reader);
		SGFGameTree tree = parser.gameTree();

		assertTrue(parser != null);
		assertTrue(parser.toString() != null);
		assertTrue(parser.toString().length() > 0);

		assertTrue(tree != null);

	}

	/**
	 * Test repeat.
	 * 
	 * @throws ParseException
	 *             the parse exception
	 */
	public void testRepeat() throws ParseException {
		String examples[] = { SGFParser.EXAMPLEA, SGFParser.EXAMPLEONE,
				SGFParser.EXAMPLETWO, SGFParser.EXAMPLETHREE,
				SGFParser.EXAMPLEFOUR, SGFParser.EXAMPLEFIVE };
		for (int i = 0; i < examples.length; i++) {
			String example = examples[i];
			StringReader reader = new StringReader(example);
			jgogears.SGF.SGF parser = new jgogears.SGF.SGF(reader);

			assertTrue(parser != null);
			assertTrue(parser.toString() != null);
			assertTrue(parser.toString().length() > 0);
			assertTrue(parser.toString().length() > 0);
			assertTrue(parser.toString().compareTo(parser.toString()) == 0);

			String result = parser.gameTree().toString();
			reader = new StringReader(result);
			parser = new jgogears.SGF.SGF(reader);
			String result2 = parser.gameTree().toString();

			assertTrue(parser != null);
			assertTrue(parser.toString() != null);
			assertTrue(parser.toString().length() > 0);

			assertTrue(result.compareTo(result2) == 0);

		}
	}

	/*
	 * Test method
	 */
	/**
	 * Test three.
	 * 
	 * @throws ParseException
	 *             the parse exception
	 */
	public void testThree() throws ParseException {
		String example = SGFParser.EXAMPLETHREE;
		StringReader reader = new StringReader(example);
		jgogears.SGF.SGF parser = new jgogears.SGF.SGF(reader);
		parser.gameTree();

		assertTrue(parser != null);
		assertTrue(parser.toString() != null);
		assertTrue(parser.toString().length() > 0);
	}

	/*
	 * Test method
	 */
	/**
	 * Test two.
	 * 
	 * @throws ParseException
	 *             the parse exception
	 */
	public void testTwo() throws ParseException {
		String example = SGFParser.EXAMPLETWO;
		StringReader reader = new StringReader(example);
		jgogears.SGF.SGF parser = new jgogears.SGF.SGF(reader);
		parser.gameTree();

		assertTrue(parser != null);
		assertTrue(parser.toString() != null);
		assertTrue(parser.toString().length() > 0);
	}

}
