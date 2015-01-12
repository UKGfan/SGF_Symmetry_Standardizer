package jgogears;

/**
 * A skeleton GTP player TODO complete this implementation TODO add tests
 * 
 * @author syeates
 */
public abstract class SkeletonEngine implements GTPInterface {

	/**
	 * Instantiates a new skeleton engine.
	 */
	public SkeletonEngine() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.GTPInterface#clearBoard(jgogears.GTPState)
	 */
	public void clearBoard(GTPState state) {
		System.err.println("clearing board");
		state.clearBoard();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.GTPInterface#fixedHandicap(int, jgogears.GTPState)
	 */
	public Move[] fixedHandicap(int handicap, GTPState state) {
		Move[] stones = GTPHandicaps.fixedHandicap(handicap);
		for (int i = 0; i < stones.length; i++) {
			state.playMove(stones[i]);
		}
		return stones;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.GTPInterface#genMove(short, jgogears.GTPState)
	 */
	public Move genMove(short colour, GTPState state) {
		Move result = this.regGenMove(colour, state);
		state.playMove(result);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.GTPInterface#getKnownCommand(java.lang.String)
	 */
	public boolean getKnownCommand(String command) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.GTPInterface#getListCommands()
	 */
	public String[] getListCommands() {
		// TODO
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.GTPInterface#getProtocolVersion()
	 */
	public int getProtocolVersion() {
		return 2;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.GTPInterface#loadsgf(java.lang.String, int,
	 * jgogears.GTPState)
	 */
	public void loadsgf(String filename, int moveNumber, GTPState state) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.GTPInterface#placeFreeHandicap(int, jgogears.GTPState)
	 */
	public Move[] placeFreeHandicap(int handicap, GTPState state) {
		Move[] stones = GTPHandicaps.freeHandicap(handicap);
		for (int i = 0; i < stones.length; i++) {
			state.playMove(stones[i]);
		}
		return stones;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.GTPInterface#placeFreeHandicap(jgogears.Move[],
	 * jgogears.GTPState)
	 */
	public void placeFreeHandicap(Move[] stones, GTPState state) {
		for (int i = 0; i < stones.length; i++) {
			state.playMove(stones[i]);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.GTPInterface#play(jgogears.Move, jgogears.GTPState)
	 */
	public void play(Move move, GTPState state) {
		state.playMove(move);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.GTPInterface#quit()
	 */
	public boolean quit() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.GTPInterface#setBoardSize(short, jgogears.GTPState)
	 */
	public void setBoardSize(short size, GTPState state) {
		System.err.println("setting boardsize to: \"" + size + "\"");
		state.setBoardsize(size);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.GTPInterface#setKomi(double, jgogears.GTPState)
	 */
	public void setKomi(double komi, GTPState state) {
		state.setKomi(komi);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.GTPInterface#setTimeLeft(int, double, double,
	 * jgogears.GTPState)
	 */
	public void setTimeLeft(int colour, double byoYomiTime,
			double byoYomiStones, GTPState state) {
		state.setByoYomiStones(byoYomiStones);
		state.setByoYomiTime(byoYomiTime);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.GTPInterface#setTimeSettings(double, double, double,
	 * jgogears.GTPState)
	 */
	public void setTimeSettings(double mainTime, double byoYomiTime,
			double byoYomiStones, GTPState state) {
		state.setByoYomiStones(byoYomiStones);
		state.setByoYomiTime(byoYomiTime);
		state.setMainTime(mainTime);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.GTPInterface#showBoard(jgogears.GTPState)
	 */
	public BoardI showBoard(GTPState state) {
		return state.getBoard();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.GTPInterface#undo(jgogears.GTPState)
	 */
	public boolean undo(GTPState state) {
		// TODO Auto-generated method stub
		GTPState newState = new GTPState();
		newState.byoYomiStones = state.byoYomiStones;
		newState.byoYomiTime = state.byoYomiTime;
		newState.komi = state.komi;
		newState.boardsize = state.boardsize;
		newState.mainTime = state.mainTime;
		newState.board = BoardI.newBoard(newState.boardsize);

		// TODO generate new board

		return false;
	}

}
