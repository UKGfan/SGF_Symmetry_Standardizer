package jgogears;

import java.util.*;

// TODO: Auto-generated Javadoc
/**
 * The Class RandomEngine.
 */
public class RandomEngine extends SkeletonEngine implements GTPInterface {

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.GTPInterface#finalStatusList(java.lang.String,
	 * jgogears.GTPState)
	 */
	public Move[] finalStatusList(String status, GTPState state) {

		// TODO
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.GTPInterface#getEngineName()
	 */
	public String getEngineName() {
		return "Mii / Barrotts Reef";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.GTPInterface#getEngineVersion()
	 */
	public String getEngineVersion() {
		return "00.001" + new Date();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.GTPInterface#getFinalScore(jgogears.GTPState)
	 */
	public GTPScore getFinalScore(GTPState state) {
		// TODO
		return new GTPScore("?");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.GTPInterface#regGenMove(int, jgogears.GTPState)
	 */
	public Move regGenMove(int colour, GTPState state) {
		// TODO make sure this is a _legal_ move
		short row;
		short column;
		do {
			row = (short) Random.nextInt(state.boardsize);
			column = (short) Random.nextInt(state.boardsize);
		} while (state.board.getColour(row, column) == BoardI.VERTEX_EMPTY);
		return new Move(row, column, colour);
	}

	/**
	 * Score.
	 * 
	 * @param state
	 *            the state
	 * @return the gTP score
	 */
	public GTPScore score(GTPState state) {
		if (state == null)
			throw new Error();
		// TODO Auto-generated method stub
		return new GTPScore("?");
	}

}
