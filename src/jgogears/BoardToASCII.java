package jgogears;

// TODO: Auto-generated Javadoc
/**
 * Transform a board to ASCII, in the style of GnuGo.
 * 
 * @author syeates
 */
public final class BoardToASCII {

	/**
	 * Generate a header (or footer) for a board of a particular size
	 * 
	 * @param size
	 *            the size
	 * @return the string
	 */
	static String headerRow(short size) {
		StringBuffer buf = new StringBuffer();
		// do the header
		buf.append("    ");
		for (int i = 0; i < size; i++) {
			char c = (char) (i + 'A');
			if (c >= 'I')
				c++;
			buf.append(c).append(" ");
		}
		return buf.toString();
	}

	/**
	 * Checks if is eye vertex
	 * 
	 * @param row
	 *            the row
	 * @param column
	 *            the column
	 * @param size
	 *            the size
	 * @return true, if is eye vertex
	 */
	static boolean isEyeVertex(short row, short column, short size) {
		if (size == 19) {
			if (row == 4 || row == 9 || row == 16)
				if (column == 4 || column == 9 || column == 16)
					return true;
			return false;
		}
		if (size == 7) {
			if (row == 2 || row == 4)
				if (column == 2 || column == 4)
					return true;
			return false;
		}
		if (size == 9) {
			if (row == 3 || row == 5)
				if (column == 3 || column == 5)
					return true;
			return false;
		}
		throw new Error("not implemented");
	}

	/**
	 * Transform a board to an ASCII representation.
	 * 
	 * @param board
	 *            the board
	 * @return the ASCII representation
	 */
	static String Transform(BoardI board) {
		StringBuffer buf = new StringBuffer();
		int size = board.getSize();
		String head = headerRow(board.getSize());
		buf.append(head);
		buf.append("\n");
		for (short i = 0; i < size; i++) {
			if (19 - i < 10)
				buf.append(" ");
			buf.append(" ").append(size - i).append(" ");
			for (short j = 0; j < size; j++) {
				switch (board.getColour(j, size - 1 - i)) {
				case BoardI.VERTEX_KO:
					buf.append("!");
					break;
				case BoardI.VERTEX_BLACK:
					buf.append("X");
					break;
				case BoardI.VERTEX_WHITE:
					buf.append("O");
					break;
				case BoardI.VERTEX_EMPTY:
					if (isEyeVertex(i, j, (short) size))
						buf.append("+");
					else
						buf.append(".");
					break;
				default:
					throw new Error();
				}
				buf.append(" ");
			}
			if (19 - i < 10)
				buf.append(" ");
			buf.append(19 - i).append(" ");

			buf.append("\n");
		}
		buf.append(head);
		buf.append("\n");
		return buf.toString();
	}
}
