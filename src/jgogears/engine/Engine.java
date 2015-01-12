/**
 * 
 */
package jgogears.engine;

import java.io.IOException;

import jgogears.*;

/**
 * TODO
 * 
 * @author syeates
 */
public class Engine {

	/**
	 * TODO
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		Model model = new Model();
		System.out.println("about to train model");

		Trainer trainer = new Trainer();
		trainer.setModel(model);

		// trainer.train(200);
		trainer.train();
		System.out.println("model trained");
		System.out.println(model.getRoot());
		System.out.println(model.getRoot().size());

		SufgoEngine black = new SufgoEngine();
		black.setModel(model);

		SufgoEngine white = new SufgoEngine();
		white.setModel(model);

		for (int j = 0; j < 200; j++) {
			TwoGTP two = new TwoGTP();
			two.setBlack(black);
			two.setWhite(white);

			for (int i = 0; i < 200; i++) {
				GTPState state = new GTPState();
				state = two.move();
				System.out.println(state.getBoard());
			}
		}

	}

}
