package jgogears;
import java.io.*;
import java.util.Iterator;
import jgogears.SGF.ParseException;
import junit.framework.TestCase;
// TODO: Auto-generated Javadoc
/**
* The Class SGFGameTest.
*/
public class SGFGameTest extends TestCase {
/*
* Test method for 'jgogears.Sufgo.isTrue(boolean)'
*/
/**
* Test is verbose.
*
* @throws ParseException
* the parse exception
*/
public void testIsVerbose() throws ParseException {
String example = SGFParser.EXAMPLEB;
StringReader reader = new StringReader(example);
jgogears.SGF.SGF parser = new jgogears.SGF.SGF(reader);
Game game = new Game(parser.gameTree());
Iterator<Move> iterator = game.getMoves();
while (iterator.hasNext()) {
iterator.next();
// System.out.println(iterator.next());
}
Iterator<BoardI> iterator2 = game.getBoards();
while (iterator.hasNext()) {
iterator2.next();
// System.out.println(iterator.next());
}
}
/**
* Test load.
*
* @throws IOException
* Signals that an I/O exception has occurred.
*/
public void testLoad() throws IOException {
try {
FileReader reader = new FileReader(
"C:\\sgfstart\\superkuni-WeirdOpen.sgf");
jgogears.SGF.SGF parser = new jgogears.SGF.SGF(reader);
assertNotNull(parser);
SGFGameTree tree = parser.gameTree();
assertNotNull(tree);
Game game = new Game(tree);
assertNotNull(game);
Iterator<Move> moves = game.getMoves();
assertNotNull(moves);
assertTrue(moves.hasNext());
while (moves.hasNext()) {
Move move = moves.next();
assertNotNull(move);
System.err.print(move);
}
System.err.println();
} catch (IOException e) {
System.err.println(e);
e.printStackTrace();
fail("throw an error");
} catch (ParseException e) {
System.err.println(e);
e.printStackTrace();
fail("throw an error");
}
}
}