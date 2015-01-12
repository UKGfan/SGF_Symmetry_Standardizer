package jgogears.engine;

import jgogears.*;

/**
 * @author syeates
 */
public class SufgoEngine extends SkeletonEngine {

	/** The model. */
	private Model model = null;

	/**
	 * Create an engine with an empty model
	 */
	public SufgoEngine() {
		// do nothing
	}

	/**
	 * TODO.
	 * 
	 * @param status
	 *            the status
	 * @param state
	 *            the state
	 * @return the move[]
	 */
	public Move[] finalStatusList(String status, GTPState state) {
		// TODO Auto-generated method stub
		throw new Error("not implemented");
	}

	/**
	 * TODO.
	 * 
	 * @return the engine name
	 */
	public String getEngineName() {
		return "SufgoEngine";
	}

	/**
	 * TODO.
	 * 
	 * @return the engine version
	 */
	public String getEngineVersion() {
		return "0.0.0.1";
	}

	/**
	 * TODO.
	 * 
	 * @param state
	 *            the state
	 * @return the final score
	 */
	public GTPScore getFinalScore(GTPState state) {
		// TODO Auto-generated method stub
		throw new Error("not implemented");
	}

	/**
	 * get the model
	 * 
	 * @return the model
	 */
	public final Model getModel() {
		return this.model;
	}

	/**
	 * Get the best move for this state and colour
	 * 
	 * @param colour
	 *            the colour
	 * @param state
	 *            the state
	 * @return the move
	 */
	public Move regGenMove(int colour, GTPState state) {
		BoardI board = state.getBoard();
		Scorer scorer = new Scorer();
		Vertex vertex = scorer.getBestScore(this.model, board,
				colour == BoardI.VERTEX_WHITE);
		Move move = new Move(vertex.getRow(), vertex.getColumn(), colour);
		return move;
	}

	/**
	 * set the model
	 * 
	 * @param model
	 *            the model to set
	 */
	public final void setModel(Model model) {
		this.model = model;
	}

}
