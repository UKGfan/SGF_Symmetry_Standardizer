/**
 * 
 */
package jgogears.engine;

import junit.framework.TestCase;
import java.util.*;
import java.io.*;

/**
 * Test the trainer
 * 
 * @author syeates
 */
public class TrainerTest extends TestCase {

	public void testLoadAllSGFfiles() throws IOException {
		Collection<String> plain = Trainer.loadAllSGFfiles();
		assertNotNull(plain);
		Iterator<String> iterator = plain.iterator();
		assertNotNull(iterator);
		int count = 0;
		TreeSet<String> tree = new TreeSet();

		while (iterator.hasNext()) {
			String s = iterator.next();
			assertNotNull(s);
			count++;
			assertFalse(tree.contains(s));
			tree.add(s);
		}
	}

	public void testLoadAllSGFfilesGeneric() throws IOException {
		Collection plain = Trainer.loadAllSGFfiles();
		assertNotNull(plain);
		Iterator iterator = plain.iterator();
		assertNotNull(iterator);
		int count = 0;
		TreeSet tree = new TreeSet();

		while (iterator.hasNext()) {
			String s = (String) iterator.next();
			assertNotNull(s);
			count++;
			assertFalse(tree.contains(s));
			tree.add(s);
		}
	}

	public void testLoadAllSGFfilesString() throws IOException {
		Collection<String> plain = Trainer.loadAllSGFfiles(Trainer.LIBRARY);
		assertNotNull(plain);
		Iterator<String> iterator = plain.iterator();
		assertNotNull(iterator);
		int count = 0;
		TreeSet<String> tree = new TreeSet();

		while (iterator.hasNext()) {
			String s = iterator.next();
			assertNotNull(s);
			count++;
			assertFalse(tree.contains(s));
			tree.add(s);
		}

	}

	public void testTrainSingle() throws IOException {
		try {
			Trainer trainer = new Trainer();
			assertNotNull(trainer);
			Model model = new Model();
			trainer.setModel(model);
			assertNotNull(model);
			trainer.train(1);
			assertNotNull(trainer);
			assertTrue(model.getGamesTrained() == 1);
			assertTrue("" + model.getBoardsTrained(),
					model.getBoardsTrained() > 1);
		} catch (Throwable e) {
			fail("" + e);
		}
	}

	public void testTrainTwo() throws IOException {
		try {
			Trainer trainer = new Trainer();
			assertNotNull(trainer);
			Model model = new Model();
			trainer.setModel(model);
			assertNotNull(model);
			trainer.train(2);
			assertNotNull(trainer);
			assertTrue(model.getGamesTrained() == 2);
			assertTrue("" + model.getBoardsTrained(),
					model.getBoardsTrained() > 1);
		} catch (Throwable e) {
			fail("" + e);
		}
	}

	public void testTrainDouble() throws IOException {
		try {
			Trainer trainer = new Trainer();
			assertNotNull(trainer);
			Model model = new Model();
			trainer.setModel(model);
			assertNotNull(model);
			trainer.train(2);
			assertNotNull(model);
			assertTrue(model.getGamesTrained() == 2);
			assertTrue("" + model.getBoardsTrained(),
					model.getBoardsTrained() > 20);
			trainer.train(2);
			assertNotNull(trainer);
			assertNotNull(model);
			assertTrue(model.getGamesTrained() == 4);
			assertTrue("" + model.getBoardsTrained(),
					model.getBoardsTrained() > 100);
		} catch (Throwable e) {
			fail("" + e);
		}
	}

}
