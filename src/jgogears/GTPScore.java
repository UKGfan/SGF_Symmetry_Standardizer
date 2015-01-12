package jgogears;

// TODO: Auto-generated Javadoc
/**
 * A class representing a go score, as represented by SGF and GTP (GTP defines
 * scores in terms of the SGF standard). A score is either (a) a win to
 * {black|white} with a margin, by time, by resignation or by forfiet (b) a win
 * to niether (a draw or void) or (c) unknown. Unknown scores are also used
 * returned by go engines and systems which cannot score games, even if the
 * score is obvious. The margin of a sore should not be taken to represent the
 * gap in skill between the two players, since a player who is loosing even
 * slightly may make increasingly risky moves in an attempt to force an error.
 * 
 * @author syeates
 */

public final class GTPScore {

	/** The white win. */
	private boolean whiteWin;

	/** The neither win. */
	private boolean neitherWin = false;

	/** The scored. */
	private boolean scored = false;

	/** The margin. */
	private double margin = Double.MIN_VALUE;

	/** The resign. */
	private boolean resign = false;

	/** The time. */
	private boolean time = false;

	/** The forfeit. */
	private boolean forfeit = false;

	/** The _void. */
	private boolean _void = false;

	/** The unknown. */
	private boolean unknown = false;

	/** The value. */
	private String value;

	/** The DEBUG. */
	public boolean DEBUG = false;

	/**
	 * Instantiates a new gTP score.
	 * 
	 * @param s
	 *            the s
	 */
	public GTPScore(String s) {
		this.init(s);
	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object arg0) {
		return this.toString().equalsIgnoreCase(arg0.toString());
	};

	/**
	 * Gets the black win.
	 * 
	 * @return the black win
	 */
	public boolean getBlackWin() {
		return !this.neitherWin && !this.whiteWin;
	};

	/**
	 * Gets the draw.
	 * 
	 * @return the draw
	 */
	public boolean getDraw() {
		return this.margin == 0.0;
	};

	/**
	 * Gets the forfeit.
	 * 
	 * @return the forfeit
	 */
	public boolean getForfeit() {
		return this.forfeit;
	};

	/**
	 * Gets the margin.
	 * 
	 * @return the margin
	 */
	public double getMargin() {
		return this.margin;
	};

	/**
	 * Gets the resign.
	 * 
	 * @return the resign
	 */
	public boolean getResign() {
		return this.resign;
	};

	/**
	 * Gets the scored.
	 * 
	 * @return the scored
	 */
	public boolean getScored() {
		return this.scored;
	};

	/**
	 * Gets the time.
	 * 
	 * @return the time
	 */
	public boolean getTime() {
		return this.time;
	};

	/**
	 * Gets the unknown.
	 * 
	 * @return the unknown
	 */
	public boolean getUnknown() {
		return this.unknown;
	}

	/**
	 * Gets the void.
	 * 
	 * @return the void
	 */
	public boolean getVoid() {
		return this._void;
	}

	/**
	 * Gets the white win.
	 * 
	 * @return the white win
	 */
	public boolean getWhiteWin() {
		return !this.neitherWin && this.whiteWin;
	}

	/**
	 * Inits the.
	 * 
	 * @param s
	 *            the s
	 * @return true, if successful
	 */
	public boolean init(String s) {
		String original = s;
		if (s.length() == 0) {
			System.err
					.println("cannot initialise a score from a zero length string");
			throw new Error();
			// return false;
		}
		try {
			this.value = s;
			s = s.toLowerCase();
			if (s.contains("w+r")) {
				this.whiteWin = true;
				this.resign = true;
			} else if (s.contains("b+r")) {
				this.whiteWin = false;
				this.resign = true;
			} else if (s.compareTo("0") == 0 || s.compareTo("draw") == 0) {
				this.whiteWin = false;
				this.neitherWin = true;
				this.margin = 0.0;
			} else if (s.contains("w+t")) {
				this.whiteWin = true;
				this.time = true;
			} else if (s.contains("b+t")) {
				this.whiteWin = false;
				this.time = true;
			} else if (s.contains("void")) {
				this.neitherWin = true;
				this._void = true;
			} else if (s.contains("w+f")) {
				this.whiteWin = true;
				this.forfeit = true;
			} else if (s.contains("b+f")) {
				this.whiteWin = false;
				this.forfeit = true;
			} else if (s.contains("w+?")) {
				this.whiteWin = true;
				this.margin = Double.MAX_VALUE;
			} else if (s.contains("b+?")) {
				this.whiteWin = false;
				this.margin = Double.MAX_VALUE;
			} else if (s.contains("?")) {
				this.unknown = true;
				this.neitherWin = true;
			} else {
				switch (s.charAt(0)) {
				case 'w':
					this.whiteWin = true;
					break;
				case 'b':
					this.whiteWin = false;
					break;
				default:
					throw new IllegalArgumentException(
							"Badly formed GTP Score \"" + this.value + "\"");
				}
				if (s.length() == 2)
					this.margin = Double.MAX_VALUE;
				else {
					String number = s.substring(2);
					this.scored = true;
					this.margin = Double.parseDouble(number);
				}
			}
			if (this.DEBUG)
				System.err.println("Generated a GTPScore of \"" + this
						+ "\" from  \"" + this.value + "\"");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			if (this.DEBUG)
				System.err.println("Problem with \"" + original + "\"");
			if (original.length() > 2) {
				try {
					this.init(s.substring(2, original.length()));
				} catch (IllegalArgumentException e2) {
					e2.printStackTrace();
					throw e;
				}
			}
			return true;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (this.neitherWin)
			if (this.margin == 0.0)
				return "0";
			else if (this._void)
				return "void";
			else
				return "?";
		else // there's a winner
		if (this.whiteWin) { // white wins
			if (this.resign)
				return "w+r";
			else if (this.time)
				return "w+t";
			else if (this.forfeit)
				return "w+f";
			else if (this.margin == Double.MAX_VALUE)
				return "w+";
			else
				return "w+" + this.margin;
		} else { // black wins
			if (this.resign)
				return "b+r";
			else if (this.time)
				return "b+t";
			else if (this.forfeit)
				return "b+f";
			else if (this.margin == Double.MAX_VALUE)
				return "b+";
			else
				return "b+" + this.margin;
		}
	}

}
