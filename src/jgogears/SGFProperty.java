package jgogears;

import java.util.*;

/**
 * An SGFProperty is a string identifier and a sequence of string values. Common
 * the sequence contains exactly one item.
 */
public class SGFProperty {

	/**
	 * String all square brackets from a string. DOES NOT LEAVE escaped squre
	 * bracekets!
	 * 
	 * @param s
	 *            the s
	 * @return the new string
	 */
	protected static String stripSquareBrackets(String s) {
		if (s == null)
			return "";
		return s.replaceAll("\\[", "").replaceAll("\\]", "");
	}

	/** The identifier. */
	private String identifier = null;

	/** The values. */
	private Vector<String> values = new Vector<String>();

	/**
	 * get the first value
	 * 
	 * @return the first value
	 */
	public String first() {
		return this.getValues().firstElement();
	}

	/**
	 * get the first value (stripped)
	 * 
	 * @return the first value (stripped)
	 */
	public String firstStripped() {
		return stripSquareBrackets(this.first());
	}

	/**
	 * Gets the identifier.
	 * 
	 * @return the identifier
	 */
	public String getIdentifier() {
		return this.identifier;
	}

	/**
	 * Gets the values.
	 * 
	 * @return the values
	 */
	public Vector<String> getValues() {
		return this.values;
	}

	/**
	 * Sets the identifier.
	 * 
	 * @param propIdent
	 *            the identifier
	 */
	public void setIdentifier(String propIdent) {
		if (this.identifier != null)
			throw new Error();
		this.identifier = propIdent;
	}

	/**
	 * Sets the values.
	 * 
	 * @param propvalues
	 *            the values to set
	 */
	public void setValues(Vector<String> propvalues) {
		if (this.values != null)
			throw new Error();
		this.values = propvalues;
	}

	/**
	 * The value of this property, stripped of brackets
	 * 
	 * @return the value, stripped
	 */
	public String stripped() {
		return stripSquareBrackets(this.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String result = this.getIdentifier();
		Iterator<String> i = this.getValues().iterator();
		while (i.hasNext()) {
			result = result + i.next();
		}
		return result;
	}
}
