package jgogears;

import java.io.*;


import java.util.Iterator;
import java.util.LinkedList;

import jgogears.SGF.ParseException;
import junit.framework.TestCase;

// TODO: Auto-generated Javadoc
/**
 * The Class SGFGameTest.
 */
public class SGFWriter2 {
	
	
	public String inpath;
	
	public String name;
	
	public String outpath;
	
	public SGFWriter2(String inpath, String name, String outpath){
	
		this.inpath = inpath;
		
		this.name = name;
		
		this.outpath = outpath;
	
	}
	
	/*
	 * Test method for 'jgogears.Sufgo.isTrue(boolean)'
	 */
	/**
	 * Test is verbose.
	 * 
	 * @throws ParseException
	 *             the parse exception
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
	
	
	// these functions will do the rotations along the different lines of symmetry
	
	public Move northSouth(Move preswap){
		
		int row = preswap.getRow();
		
		int distfrom9 = 0;
		
		if(row > 9){
			
			distfrom9 = row - 9;
			distfrom9 = distfrom9 + distfrom9;  // multiply by 2
			row = row - distfrom9;
		}
		else if ( row < 9){
			
			distfrom9 = 9 - row;
			distfrom9 = distfrom9 + distfrom9;
			row = row + distfrom9;
		}
		
		Move postSwap = new Move (row, preswap.getColumn(), preswap.getColour() ); 
		
		return postSwap;
	}
	
	public Move eastWest(Move preswap){
		
		int column = preswap.getColumn();
		
		int distfrom9 = 0;
		
		if(column > 9){
			
			distfrom9 = column - 9;
			distfrom9 = distfrom9 + distfrom9;  // multiply by 2
			column = column - distfrom9;
		}
		else if ( column < 9){
			
			distfrom9 = 9 - column;
			distfrom9 = distfrom9 + distfrom9;
			column = column + distfrom9;
		}
		
		Move postSwap = new Move (preswap.getRow(), column, preswap.getColour() ); 
		
		return postSwap;
	}
	
	public Move swNe(Move preswap){
		
		return northSouth(eastWest(nwSe(preswap)));
	}
	
	public Move nwSe(Move preswap){
		
	
		Move postSwap = new Move (preswap.getColumn(), preswap.getRow(), preswap.getColour());
		return postSwap;
	}
	
	// functions to detect if move is on a line of symmetry
	
	public boolean NScheck(Move checkmove){
		
		if (checkmove.getRow() == 9) return true;
		return false;
	}
	
	public boolean EWcheck(Move checkmove){
		
		if (checkmove.getColumn() == 9) return true;
		return false;
	}
	
	public boolean NwSecheck(Move checkmove){
		
		if(checkmove.getColumn() == checkmove.getRow()) return true;
		return false;
	}
	
	public boolean SwNecheck(Move checkmove){
		
		Move temp = eastWest(checkmove);
		if(temp.getColumn() == temp.getRow()) return true;
		return false;
	}
	
	// i want something to print the sector binaries of each move to help testing
	public void printMove(Move checkmove){
		
		String out = ""; 
		
		if(NScheck(checkmove)) out += "1";
		else out += "0";
		
		if(EWcheck(checkmove)) out += "1";
		else out += "0";
		
		if(NwSecheck(checkmove)) out += "1";
		else out += "0";
		
		if(SwNecheck(checkmove)) out += "1";
		else out += "0";
		
		System.out.println(out);
		
	}
	
	// function to determine if move is on the tenger
	
	public boolean tengenCheck(Move checkmove){
		
		if(checkmove.getColumn() == 9 && checkmove.getRow() == 9) return true;
		return false;
	}
	
	//return mathematical quadrant of move
	//if it's on an axis it doesn't really matter because it won't get rotated according to the axis it's on anyway
	public int getQuad(Move checkmove){
		
		if(checkmove.getRow() <= 9){
			
			if(checkmove.getColumn() >= 9){
				
				return 1;
			}
			else return 4;
		}
		else {
			if(checkmove.getColumn() >=9){
				
				return 2;
			}
			else return 3;
		}
		
	}

	// Return true-false, is the stone on the north side?
	public boolean northSideCheck(Move checkmove){
		
		if(checkmove.getRow() <= 9) return true;
		
		return false;
	}
	
	
	// true false, is the stone on the east side?
	public boolean eastSideCheck(Move checkmove){
		
		if(checkmove.getColumn() >= 9) return true;
		
		return false;
	}
	
	// checks to see what diagonal slice we are on for the easy one (for the NW SE axis)
	// true is already towards the side black want to play first (NE side)
	// false is the other side (SW side)
	
 	public boolean bostonSliceCheck(Move checkmove){
		
 		if (checkmove.getRow() <= checkmove.getColumn()){
 			return true;
 		}
	
 		return false;
	}
 	
 	
 	// move in NW corner True, SE false
 	public boolean seattleSliceCheck(Move checkmove){
 		
 		Move temp = eastWest(checkmove);
	
 		if (temp.getRow() <= temp.getColumn()){
 			return true;
 		}
	
 		return false;
 		
 	}
 	
 	public boolean oceanSliceCheck(Move checkmove){
 		
 		
 		// if we are in both slices good, if not bad
 		
 		return (bostonSliceCheck(checkmove) == seattleSliceCheck(checkmove));
 		
 	}

	// this function will detect if we are in our desired sector for the starting move;
	
	// W NW = 1, N NW = 2, N NE = 3, E NE = 4, E SE = 5, S SE = 6, S SW = 7, W SW = 8;
	
	// we want to get to sector 5 (E NE);
	
	public boolean detectSector(Move move){
		
		if(move.getRow() <= 10){
			
			if(move.getColumn() >= 10){ 
				Move checkMove = eastWest(move);
				
			// see if our move is in the top right quadrant of the board
				
			// then rotate so it's easy to check which half of the quadrant it's in
				
				if(checkMove.getRow() >= checkMove.getColumn()){
					
					// if the Rows down from the top is greater than the
			        // columns across from the side, we are in our goal sector
					
					return true;
				}
			}
		}
		
		return false;
	}
	

	/**
	 * Test load.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void testLoad() throws IOException {
		
		
		try {
			FileReader reader = new FileReader( inpath + name);
			
			//System.out.println("Test1");
			
			jgogears.SGF.SGF parser = new jgogears.SGF.SGF(reader);
			assertNotNull(parser);
			SGFGameTree tree = parser.gameTree();
			assertNotNull(tree);
			Game game = new Game(tree);
			assertNotNull(game);
			Iterator<Move> moves = game.getMoves();
			assertNotNull(moves);
			assertTrue(moves.hasNext());
			
			int count = 0;
			
			Game fix = new Game(tree);
			Iterator<Move> fixMoves = fix.getMoves();
			
			boolean resolved = false;
			
			// refrence cities for the diagonal boolean checks
			
			// if Seattle is true the stone is in the NW slice
			// false then it is in the SE slice

			boolean n;  // North side?
			boolean e;  // East side?			
			boolean s;  // Seattle side?
			boolean b;  // Boston side?			
			// if Boston is true the stone is in the NE slice
			// false then its in the SW slice

			
			boolean diagonal = false; // this is needed to remember if our first move is on a diagonal symmetry, to resolve post move 1 symmetries
			
			boolean perpendicular = false; // we need this one to
			

			
			boolean nflip = false; // flip north?
			boolean eflip = false; // flip east?
			boolean sflip = false; // flip seattle?
			boolean bflip = false; // flip boston?
			
			// check what rotations we need to do
			//while(!resolved){
					
				Move checkmove = moves.next();
				
				if(tengenCheck(checkmove)) checkmove = moves.next();  // if our first move is tengen it won't help us resolve symmetries, get another move
				
				
				if(NwSecheck(checkmove) || SwNecheck(checkmove)) diagonal = true;
				
				if(NScheck(checkmove) || EWcheck(checkmove)) perpendicular = true;
				
				
				n = northSideCheck(checkmove);
				
				System.out.println("N      " + n);
				
				e = eastSideCheck(checkmove);
				
				System.out.println("E      " + e);
				
				s = seattleSliceCheck(checkmove);
				
				System.out.println("S      " + s);
				
				b = bostonSliceCheck(checkmove);
				
				System.out.println("B      " + b);
				
				
				// North, East, Seattle, Boston
			
			// set flips
				
			// this is just a sum of products expansion of a truth table
				
			// hopefully it works
				

			
			
			if((!n && !e && s && !b) || (!n && e && !s && b) || (n && !e && s && b)){
			
				
				
				nflip = true;
			}
			
				
			if((!n && !e && s && !b) || (!n && e && !s && !b) || (n && !e && s && !b))
				eflip = true;
			
			if(n && e && s && b)
				sflip = true;
			
			if((!n && !e && !s && !b) || (!n && e && !s && !b) || (n && !e && s && b))
				bflip = true;
				
			
			
			if(checkmove.getColour() == 1){ // if black's first move was at tengen, that means we are fixing whites move
				
				sflip = !sflip;				// Lets make this move polite for white and not black i.e octant 1011 instead of 1101
				
				System.out.println(checkmove.getRow());
				
				System.out.println(checkmove.getColumn());
				
				System.out.println(checkmove.getColour());
				
			}
			
			System.out.println("Correction given first move: ");

			System.out.println("Nflip   " +  nflip);
			System.out.println("Eflip   " +  eflip);
			System.out.println("Sflip   " +  sflip);
			System.out.println("Bflip   " +  bflip);
			
			System.out.println("Diagonal :    " + diagonal + "   Perpendicular:    " + perpendicular);
			
			
			if(diagonal || perpendicular){
			
				System.out.println("                 Current move: " + checkmove.getRow() + "  " + checkmove.getColumn());
				
				checkmove = moves.next();
			
				System.out.println("                 Current move: " + checkmove.getRow() + "  " + checkmove.getColumn());
				
			
				// code after here standardizes symmetries that might be left over after first move
				
				if(tengenCheck(checkmove)) checkmove = moves.next();  // if our next move is tengen it won't help us resolve symmetries, get the next one another
			
				
				
				// fix seattle symmetry, (swne)
				
				System.out.println("~~~~~~~~~~~~");
			
				if(diagonal){
					
					int snewCount = 0;
				
					while(SwNecheck(checkmove = moves.next())){
				
						System.out.print("                 Current move: " + checkmove.getRow() + "  " + checkmove.getColumn());
						
						System.out.println(SwNecheck(checkmove));
						
						System.out.println("Stones on swne axis: " + ++snewCount);
					
				};  // just keep going through the game until somebody plays off this (seattle) axis
				
				System.out.println("                 Current move: " + checkmove.getRow() + "  " + checkmove.getColumn());
				
				
				// ok good, no more symmetry forks left, now standardize
				
				s = seattleSliceCheck(checkmove);
				
				if(s == !sflip && checkmove.getColour() == 2){
					
					System.out.println(checkmove.getColour());
					
					System.out.println("flipping sflip. sflip: " + sflip);
					
					sflip = !sflip; // if black broke symmetry rudely, make it polite, and the rest of the game polite too
					
					System.out.println("we think we are fixing blacks first move off the axis here");
				}
				
				
				if(!s == !sflip && checkmove.getColour() == 1){
					
					System.out.println(checkmove.getColour());
					
					System.out.println("flipping sflip. sflip: " + sflip);
					
					sflip = !sflip; // ditto for white
					
					System.out.println("we think we are fixing whites first move off the axis here");
				}
				
			}
			
			// fix N symmetry, (ns)
			
			if(perpendicular){
				
				while(NScheck(checkmove = moves.next()));  // just keep going through the game until somebody plays off this (seattle) axis
				
				System.out.println("                 Current move: " + checkmove.getRow() + "  " + checkmove.getColumn());
				
				
				// ok good, no more symmetry forks left, now standardize
				
				n = NScheck(checkmove);
				
				if(n == !nflip && checkmove.getColour() == 2) nflip = !nflip; // if black broke symmetry rudely, make it polite, and the rest of the game polite too
				
				if(!n == !nflip && checkmove.getColour() == 1) nflip = !nflip; // ditto for white
				
			}
			
			
			System.out.println("Correction given first unique move: ");

			System.out.println("Nflip   " +  nflip);
			System.out.println("Eflip   " +  eflip);
			System.out.println("Sflip   " +  sflip);
			System.out.println("Bflip   " +  bflip);
			
			
			}
			
			// if the first move is not in the correct quadrants 
			 
			int sector = 0;
			
			
			// Symmetry Axis Color Prefs
			
			// EW
			// Black: E
			// White: W
			
			// NS
			
			
			// find out what set of rotations we need to do to standardize record
			switch(sector){
			case 0:
				
				// EW
				
				break;
			case 1:
				
				// NwSe, EW
				
				break;
			case 2:
				
				// SwNe
				break;
			case 3:
				
				// nope, already correct
				break;
			case 4:
				
				// NS
				break;
			case 5:
				
				// NwSe, NS
				break;
			case 6:
				
				// NsSe
				break;
			case 7:
				
				// EW, NS
				break;				
			}
			
			
			while (fixMoves.hasNext()) {
				Move move = fixMoves.next();
				//Move rotate = swNe(move); // test the swNe rotation
				assertNotNull(move);
				//System.out.println("Original: "); // + move.getColour() + " Move Row: " + move.getRow() + " Move Column: " + move.getColumn());
				
				//System.out.format("%+8d%n", n);
				
				System.out.format("Move: %d Row: %2d Col: %2d   ",  move.getColour() , move.getRow() , move.getColumn()); printMove(move);
				//System.out.println("Rotated: " + rotate.getColour() + " Move Row: " + rotate.getRow() + " Move Column: " + rotate.getColumn());
				//System.out.println(" Move #: " + ++count);

			}
			
			System.out.println();
			
			fixMoves = fix.getMoves();
			
			count = 0;
			
			Game writeGame = new Game();
			
			LinkedList<Move> writeList = writeGame.getMovelist();
			
			while (fixMoves.hasNext()) {
				
				
				Move move = fixMoves.next();
	
				if(nflip)move = northSouth(move);
				
				if(eflip)move = eastWest(move);
				
				if(sflip)move = swNe(move);
				
				if(bflip)move = nwSe(move);
				
				writeList.add(move);
				
				//if()
				//Move rotate = swNe(move); // test the swNe rotation
				assertNotNull(move);
				//System.out.println("Fixed?: "); // + move.getColour() + " Move Row: " + move.getRow() + " Move Column: " + move.getColumn());
				System.out.format("Move: %d Row: %2d Col: %2d   ",  move.getColour() , move.getRow() , move.getColumn()); printMove(move);
				//System.out.println("Rotated: " + rotate.getColour() + " Move Row: " + rotate.getRow() + " Move Column: " + rotate.getColumn());
				//System.out.println(" Move #: " + ++count);

			}
			
			
			
			// Writing new SGF File
			
			
			
		
			
			
			
			String outString = "";
			
			
			fixMoves = writeGame.getMoves();
			count = 0;
			
			// testing to see if we actually changed the sgf
			
			System.out.println("print check between sgf dumps");
			
			while(fixMoves.hasNext()){
			
				Move move = fixMoves.next();
				
				assertNotNull(move);
				//System.out.println("Fixed?: "); // + move.getColour() + " Move Row: " + move.getRow() + " Move Column: " + move.getColumn());
				System.out.format("Move: %d Row: %2d Col: %2d   ",  move.getColour() , move.getRow() , move.getColumn()); printMove(move);
				//System.out.println(" Move #: " + ++count);
				
				
				if(move.getColour() == 2){
					
					outString += ";B[";
				}
				else {
					
					outString += ";W[";
					
				}
				
				// cast co-ordinates to char to save to file
				
				char rowsChar = (char)(move.getRow()+97);
				char colsChar = (char)(move.getColumn()+97);
				
				outString += colsChar;
				outString += rowsChar;
				
				outString += "]";
			
			}
			
			
			FileReader reader2 = new FileReader( inpath + name);
			
			String prefix = "";
			
			char writeChar = (char)reader2.read();
			
			while(writeChar != ';'){
				
				prefix += writeChar;
				
				writeChar = (char)reader2.read();
				
			}
			
			do {
				
				prefix += writeChar;
				
				writeChar = (char)reader2.read();
				
			} while (writeChar != ';');
			
			
			prefix += outString;
			
			prefix += ')';
			
			System.out.println(outString);
			
			System.out.println(prefix);
			
			PrintWriter out = new PrintWriter(outpath + name);
			
			out.print(prefix);
			
			out.close();
			
			reader2.close();
			
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
