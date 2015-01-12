package jgogears;

import java.io.StringReader;

import jgogears.SGF.ParseException;

/**
 * Front end to the SGF parser. Also holds the SGF examples from the SGF
 * standard
 * 
 * @author stuart
 */
public class SGFParser {

	/** Trivial example SGF file. */
	public static String EXAMPLEA = "(;)";

	// //////////// static examples NOT from the standard

	/**
	 * Example from the SGF standard. Note that examples do not conserve white
	 * space
	 */
	public static String EXAMPLEB = "(;FF[4]GM[1]SZ[7];B[aa];W[bb];B[cc];W[dd];B[ad];W[bd])";

	/**
	 * Example from the SGF standard. Note that examples do not conserve white
	 * space
	 */
	public static String EXAMPLEONE = "(;FF[4]GM[1]SZ[19];B[aa];W[bb];B[cc];W[dd];B[ad];W[bd])";

	// //////////// static examples from the standard

	/**
	 * Example from the SGF standard. Note that examples do not conserve white
	 * space
	 */
	public static String EXAMPLETWO = "(;FF[4]GM[1]SZ[19];B[aa];W[bb](;B[cc];W[dd];B[ad];W[bd]) (;B[hh];W[hg]))";

	/**
	 * Example from the SGF standard. Note that examples do not conserve white
	 * space
	 */
	public static String EXAMPLETHREE = "(;FF[4]GM[1]SZ[19];B[aa];W[bb](;B[cc]N[Var A];W[dd];B[ad];W[bd]) (;B[hh]N[Var B];W[hg]) (;B[gg]N[Var C];W[gh];B[hh];W[hg];B[kk]))";

	/**
	 * Example from the SGF standard. Note that examples do not conserve white
	 * space
	 */
	public static String EXAMPLEFOUR = "(;FF[4]GM[1]SZ[19];B[aa];W[bb](;B[cc];W[dd](;B[ad];W[bd]) (;B[ee];W[ff])) (;B[hh];W[hg]))";

	/**
	 * Example from the SGF standard. Note that examples do not conserve white
	 * space
	 */
	public static String EXAMPLEFIVE = "(;FF[4]GM[1]SZ[19];B[aa];W[bb](;B[cc]N[Var A];W[dd];B[ad];W[bd])(;B[hh]N[Var B];W[hg]) (;B[gg]N[Var C];W[gh];B[hh]  (;W[hg]N[Var A];B[kk])  (;W[kl]N[Var B])))";

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the args
	 */
	public static void main(String[] args) {
		String example = SGFParser.EXAMPLEFIVE;
		StringReader reader = new StringReader(example);
		jgogears.SGF.SGF parser = new jgogears.SGF.SGF(reader);
		SGFGameTree tree = null;
		try {
			tree = parser.gameTree();
		} catch (ParseException e) {
			System.err.println(e);
		}
		System.out.println(tree);

	}

}
