package jgogears;

import java.util.TreeSet;

/**
 * A RuleSet that caches the results to speed things up. TODO this class is
 * still a skeleton, it doesn't actually do anything yet.
 * 
 * @author syeates
 */
public class CachingKoRule extends RuleSet {

	/** the inner ruleset. */
	private final RuleSet rule;

	/**
	 * The constructor, passing in the inner rule whose results are cached.
	 * 
	 * @param rule
	 *            the inner rule whose results are cached
	 */
	public CachingKoRule(RuleSet rule) {
		this.rule = rule;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.RuleSet#captures(jgogears.Game, jgogears.BoardI,
	 * jgogears.Move)
	 */
	@Override
	public TreeSet<Vertex> captures(Game game, BoardI Board, Move move) {
		return this.rule.captures(game, Board, move);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.RuleSet#getDescription()
	 */
	@Override
	public String getDescription() {
		return this.rule.getDescription() + "+ caching";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.RuleSet#getLiberties(short, short, jgogears.BoardI)
	 */
	@Override
	public TreeSet<Vertex> getLiberties(short rowb, short columnb, BoardI board) {
		return this.rule.getLiberties(rowb, columnb, board);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.RuleSet#getName()
	 */
	@Override
	public String getName() {
		return this.rule.getName() + "+ caching";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.RuleSet#getString(short, short, jgogears.BoardI)
	 */
	@Override
	public TreeSet<Vertex> getString(short row, short column, BoardI board) {
		return this.rule.getString(row, column, board);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.RuleSet#leavesKo(jgogears.Game, jgogears.BoardI,
	 * jgogears.Move)
	 */
	@Override
	public TreeSet<Vertex> leavesKo(Game game, BoardI Board, Move move) {
		return this.rule.leavesKo(game, Board, move);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jgogears.RuleSet#moveIsLegal(jgogears.Game, jgogears.BoardI,
	 * jgogears.Move)
	 */
	@Override
	public boolean moveIsLegal(Game game, BoardI Board, Move move) {
		return this.rule.moveIsLegal(game, Board, move);
	}

}
