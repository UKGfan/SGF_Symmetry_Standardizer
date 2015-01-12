package jgogears;

import java.io.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import jgogears.SGF.ParseException;
import junit.framework.TestCase;

// TODO: Auto-generated Javadoc
/**
 * The Class SGFGameTest.
 */
public class SGFRotator {
	
	
	public void createTestCases(String directory, String outdirectory){
		
		
		Collection<String> filenames = null;
		try {
			filenames = jgogears.engine.Trainer.loadAllSGFfiles(directory);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<String> iterator = filenames.iterator();
		
		while(iterator.hasNext()){
		
		try {
			makeTestCases(iterator.next(), outdirectory);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
		
		}
	
	
	public void convertSGFs(String directory, String outdirectory){
	
	Collection<String> filenames = null;
	try {
		filenames = jgogears.engine.Trainer.loadAllSGFfiles(directory);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	Iterator<String> iterator = filenames.iterator();
	
	while(iterator.hasNext()){
	
	try {
		convertFile(iterator.next(), outdirectory);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
	
	}
	
	public SGFRotator(){
	
	
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
	
	/*
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
	
	*/
	
	
	// these functions will do the rotations along the different lines of symmetry
	
	
	public Game flipGame(char flip, Game preflip){
		
		Game postflip = new Game();
		
		Iterator<Move> flipMoves = preflip.getMoves();
		
		LinkedList<Move> flippedMoves = postflip.getMovelist();
		
		Move move = null;
		
		while (flipMoves.hasNext()) {
			
			
			move = flipMoves.next();

			if(!move.getPass()){
			
				if(flip == 'n')move = northSouth(move);
			
				if(flip == 'e')move = eastWest(move);
			
				if(flip == 's')move = swNe(move);
			
				if(flip == 'b')move = nwSe(move);
			
			}
			
			flippedMoves.add(move);
		
		}
		
		
		return postflip;
		
	}
	
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
		
		
		if(checkmove.getPass()){
			System.out.println("pass");
			return;
		}
		
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
	
	// function to determine if move is on the tengen
	
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
	
	
	public void makeTestCases(String fileName, String outDirectory) throws IOException{
		
		String localName = "";

		//extract the local name from the entire file name
		
		for(int i = 0, n = fileName.length() ; i < n ; i++) { 
		    char c = fileName.charAt(i);
		    
		    localName += c;
		    
		    if(c == '\\')localName = "";
		}
		
		try {
			FileReader reader = new FileReader( fileName);
			
			System.out.println("Test1");
			
			jgogears.SGF.SGF parser = new jgogears.SGF.SGF(reader);
			//assertNotNull(parser);
			SGFGameTree tree = parser.gameTree();
			//assertNotNull(tree);
			Game game = new Game(tree);
			//assertNotNull(game);
			
			//assertNotNull(moves);
			//assertTrue(moves.hasNext());
			
	

			
		
		
			Move move = null;
		
			for(int rotations = 0; rotations < 8; rotations++){
		
				String outString = "";
				
				
				if(rotations % 2 == 0){
					
					game = flipGame('s', game);
				}
				else {
					
					game = flipGame('e', game);
				}
				
				
				Iterator<Move> fixMoves = game.getMoves();
				
				while(fixMoves.hasNext()){
		
					move = fixMoves.next();
					
					if(move.getColour() == 2){
				
					outString += ";B[";
					}
					else {
				
						outString += ";W[";
				
					}
			
					// cast co-ordinates to char to save to file
			
			
					if(!move.getPass()){
						char rowsChar = (char)(move.getRow()+97);
						char colsChar = (char)(move.getColumn()+97);
			
						outString += colsChar;
						outString += rowsChar;
			
					}
			
					outString += "]";
		
				}	// end of loop that writes one string to write to a file
		
				FileReader reader2 = new FileReader( fileName);
				
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
				
				String version = "_";
				
				Integer rotationsOb = rotations;
				
				version +=  rotationsOb.toString();
				
				version += "_";
				
				PrintWriter out = new PrintWriter(outDirectory + version + localName);
			
				out.print(prefix);   // this actually prints out the whole file out because we just added the rest in after the prefix
				
				out.close();
				
				reader2.close();
				
				
			} // end of loop that rotates 8 times
		
		}
		catch (IOException e) {
			System.err.println(e);
			e.printStackTrace();
			//fail("throw an error");
		} catch (ParseException e) {
			System.err.println(e);
			e.printStackTrace();
			//fail("throw an error");
		}
		
		
	}

	/**
	 * Test load.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void convertFile(String fileName, String outDirectory) throws IOException {
		
		
		String localName = "";

		//extract the local name from the entire file name
		
		for(int i = 0, n = fileName.length() ; i < n ; i++) { 
		    char c = fileName.charAt(i);
		    
		    localName += c;
		    
		    if(c == '\\')localName = "";
		}
		
		
		
		try {
			FileReader reader = new FileReader( fileName);
			
			System.out.println("Test1");
			
			jgogears.SGF.SGF parser = new jgogears.SGF.SGF(reader);
			//assertNotNull(parser);
			SGFGameTree tree = parser.gameTree();
			//assertNotNull(tree);
			Game game = new Game(tree);
			//assertNotNull(game);
			Iterator<Move> moves = game.getMoves();
			//assertNotNull(moves);
			//assertTrue(moves.hasNext());
			
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
			
			boolean perpendicular = false; // we need this one too
			
			boolean tengenfirst = false; // probably don't need this one but i am just trying to hack this together at this point god damn
			
			
			boolean nflip = false; // flip north?
			boolean eflip = false; // flip east?
			boolean sflip = false; // flip seattle?
			boolean bflip = false; // flip boston?
			
			// check what rotations we need to do
			//while(!resolved){
					
				Move checkmove = moves.next();
				
				if(tengenCheck(checkmove)){
					
					checkmove = moves.next();  // if our first move is tengen it won't help us resolve symmetries, get another move
			
					tengenfirst = true;
				}
				
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
				

			
			if(checkmove.getColour() == 2){
				
			
				if((!n && !e && s && !b) || (!n && e && !s && b) || (n && !e && s && b)){
			
					nflip = true;
				}
			
				
				if((!n && !e && s && !b) || (!n && e && !s && !b) || (n && !e && s && !b))
					eflip = true;
			
				if(n && e && s && b)
					sflip = true;
			
				if((!n && !e && !s && !b) || (!n && e && !s && !b) || (n && !e && s && b))
					bflip = true;
				
				}
			
			if(checkmove.getColour() == 1){ // if black's first move was at tengen, that means we are fixing whites move
				
				System.out.println("Fixing first move by white because black played tengen");
				
				// nflip for white
				
				if(!b && !n){
					
					nflip = true;
				}
				
				// eflip for white
				
				if( (n && e) || (e && !b)){
					eflip = true;
				}
				
				// sflip for white
				
				// messed up the truth table, this gruesome hack saves me some work
				
				if((b && !s) && !(n && e && !s & b) ){
					sflip = true;
				}
				
				// bflip for white
				
				if((s && !b) || (n && e && !s & b)){
					
					bflip = true;
				}
				
				
				System.out.println(checkmove.getRow());
				
				System.out.println(checkmove.getColumn());
				
				System.out.println(checkmove.getColour());
				
			}
			
			System.out.println("Correction given first non-tengen move: ");

			System.out.println("Nflip   " +  nflip);
			System.out.println("Eflip   " +  eflip);
			System.out.println("Sflip   " +  sflip);
			System.out.println("Bflip   " +  bflip);
			
			System.out.println("Diagonal :    " + diagonal + "   Perpendicular:    " + perpendicular);
			
			System.out.println("color (1 is white 2 is black):  " + checkmove.getColour());
			
			
			// we have already worked out most of what we need from the first one or two moves.
			
			// it's still possible that there are symmetries
			
			// we need to apply what we already have to our game, and then continue to fix from there
			
			Iterator<Move> oldGame = fix.getMoves();
			Game stepOne = new Game();
			
			Move move = null;
			
			LinkedList<Move> stepOneList = stepOne.getMovelist();
			
			while (oldGame.hasNext()) {
				
				
				move = oldGame.next();
	
				if(!move.getPass()){
				
				if(nflip)move = northSouth(move);
				
				if(eflip)move = eastWest(move);
				
				if(sflip)move = swNe(move);
				
				if(bflip)move = nwSe(move);
				
				}
				
				stepOneList.add(move);
			
			}
				
			moves = stepOne.getMoves();
			
			move = moves.next();
			
			if(tengenCheck(move)){
				
				move = moves.next();
			}
			
			// lets make a new round of flips for the second round of fixing so this is less confusing
			
			boolean nflip2 = false;
			
			boolean eflip2 = false;
			
			boolean sflip2 = false;
			
			boolean bflip2 = false;
			
			if((diagonal || perpendicular) && moves.hasNext() ){
			
				System.out.println("                 Current move: " + checkmove.getRow() + "  " + checkmove.getColumn());
				
				checkmove = moves.next();
			
				System.out.println("                 Current move: " + checkmove.getRow() + "  " + checkmove.getColumn());
				
			
				// code after here standardizes symmetries that might be left over after first move
				
				if(tengenCheck(checkmove)) checkmove = moves.next();  // if our next move is tengen it won't help us resolve symmetries, get the next one another
			
				
				
				// fix seattle symmetry, (swne)
				
				System.out.println("~~~~~~~~~~~~");
				
				System.out.println("SwNecheck: " + SwNecheck(checkmove));
			
				System.out.println("NwSecheck: " + NwSecheck(checkmove));
				
				System.out.println("Moves hasNext: " + moves.hasNext());
				
				if(diagonal){
					
					int snewCount = 0;
				
					if(moves.hasNext() && SwNecheck(checkmove)){
					
						while(SwNecheck(checkmove = moves.next())){
				
						
							System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~ is this happening? ~~~~~~~~~~~~~~~~~~~~~~~`");
						
							System.out.print("                 Current move: " + checkmove.getRow() + "  " + checkmove.getColumn());
						
							System.out.println(SwNecheck(checkmove));
						
							System.out.println("Stones on swne axis: " + ++snewCount);
					
						};  // just keep going through the game until somebody plays off this (seattle) axis
				
					}
					
					if(moves.hasNext() && NwSecheck(checkmove)){
				
							while(NwSecheck(checkmove = moves.next())){
					
					
									System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~ or is this happening? ~~~~~~~~~~~~~~~~~~~~~~`");
					
									System.out.print("                 Current move: " + checkmove.getRow() + "  " + checkmove.getColumn());
					
									System.out.println(NwSecheck(checkmove));
					
									System.out.println("Stones on NwSe axis: " + ++snewCount);
				
							};  // just keep going through the game until somebody plays off this (seattle) axis
				
			
					}
				
					System.out.println("                 Current move: " + checkmove.getRow() + "  " + checkmove.getColumn());
				
				
					// ok good, no more symmetry forks left for diagonal, now standardize
				
					s = seattleSliceCheck(checkmove);
					
					// checking for tengen here because if black played tengen first then the first non-tengen move was white
				
					if(!tengenfirst){
						
					// black didn't play on tengen so we know first move is in 1101
					
						s = seattleSliceCheck(checkmove);
						
						if(s == !sflip2 && checkmove.getColour() == 2){
					
							System.out.println(checkmove.getColour());
					
							System.out.println("flipping sflip2. sflip2: " + sflip2);
					
							sflip2 = !sflip2; // if black broke symmetry rudely, make it polite, and the rest of the game polite too
					
							System.out.println("we think we are fixing blacks first move off the s axis here");
						}
				
				
						if(!s == !sflip2 && checkmove.getColour() == 1){
					
							System.out.println("s value we are using here: " + s );
					
							System.out.println("sflip:                     " + sflip);
					
							System.out.println(checkmove.getColour());
						
							System.out.println("flipping sflip2. sflip2: " + sflip2);
					
							sflip2 = !sflip2; // ditto for white
					
							System.out.println("we think we are fixing whites first move off the  s axis here");
						}
					}
					
					if(tengenfirst){
						
					// black played tengen first so the first white move is in 1011	
					
						b = bostonSliceCheck(checkmove);
						
						
						if(b == bflip2 && checkmove.getColour() == 2){
							
							System.out.println(checkmove.getColour());
					
							System.out.println("flipping bflip2. bflip2: " + bflip2);
					
							bflip2 = !bflip2; // if black broke symmetry rudely, make it polite, and the rest of the game polite too
					
							System.out.println("we think we are fixing blacks first move off the b axis here");
						}
				
				
						if(b == !bflip2 && checkmove.getColour() == 1){
					
							System.out.println(checkmove.getColour());
						
							System.out.println("flipping bflip. bflip: " + bflip);
					
							bflip2 = !bflip2; // ditto for white
					
							System.out.println("we think we are fixing whites first move off the b axis here");
						}
						
					}
					
				}
			
			// fix perpendicular symmetries
			
				
				
			if(perpendicular){
				
				// this will crash if you never leave this axis in the sgf. fixable but a pain. doing that later
				
				if(moves.hasNext() && NScheck(checkmove)){
					while(NScheck(checkmove = moves.next()));  // just keep going through the game until somebody plays off this (seattle) axis
				}
				
				System.out.println("                 Current move: " + checkmove.getRow() + "  " + checkmove.getColumn());
				
				
				if(moves.hasNext() && EWcheck(checkmove)){
					while(EWcheck(checkmove = moves.next()));  // just keep going through the game until somebody plays off this (seattle) axis
				}
				
				System.out.println("                 Current move: " + checkmove.getRow() + "  " + checkmove.getColumn());
				
				
				
				// ok good, no more symmetry forks left, now standardize
				
				
				
				if(!tengenfirst){
					
					n = northSideCheck(checkmove);
				
					System.out.println("Perpandicular Check if black not tengenfirst");
					
					System.out.println("N:   " + n );
				
					System.out.println(checkmove.getColour() + "                 Current move: " + checkmove.getRow() + "  " + checkmove.getColumn());
					
					
				// first move in 1101
					
					if(n == true && checkmove.getColour() == 2) nflip2 = true; // if black broke symmetry rudely, make it polite, and the rest of the game polite too
				
					
					// this means is black played on the north when it could have been south make it south

					
					if(n == false && checkmove.getColour() == 1) nflip2 = true; // ditto for white
				
					// this means if white played in the south when it could have been north make it north
					
					
				}
				
				if(tengenfirst){
					
				// first move tengen, second move in 1011
					
					e = eastSideCheck(checkmove);
					
					if(e == false && checkmove.getColour() == 2) eflip2 = true; // if black broke symmetry rudely, make it polite, and the rest of the game polite too
					
					// ok, so if black played west when it could have been east, make it east
					
					if(e == true && checkmove.getColour() == 1) eflip2 = true; // ditto for white
					
					// if white played east when it could have been west make it west
					
				}
			}
			
			
			System.out.println("Second round corrections given first unique move: ");

			System.out.println("Nflip2   " +  nflip2);
			System.out.println("Eflip2   " +  eflip2);
			System.out.println("Sflip2   " +  sflip2);
			System.out.println("Bflip2   " +  bflip2);
			
			
			}
			
			// if the first move is not in the correct quadrants 
			 
			int sector = 0;
			
			
			// Symmetry Axis Color Prefs
			
			// EW
			// Black: E
			// White: W
			
			// NS
			
					
			
			while (fixMoves.hasNext()) {
			
				move = fixMoves.next();
			
				boolean pass = move.getPass();
				
				//Move rotate = swNe(move); // test the swNe rotation
				//assertNotNull(move);
				//System.out.println("Original: "); // + move.getColour() + " Move Row: " + move.getRow() + " Move Column: " + move.getColumn());
				
				//System.out.format("%+8d%n", n);
				
				if(!pass)
				System.out.format("Move: %d Row: %2d Col: %2d   ",  move.getColour() , move.getRow() , move.getColumn()); printMove(move);
				if(pass)
				System.out.println("Pass");
				//System.out.println("Rotated: " + rotate.getColour() + " Move Row: " + rotate.getRow() + " Move Column: " + rotate.getColumn());
				//System.out.println(" Move #: " + ++count);

			}
			
			System.out.println();
			
			fixMoves = fix.getMoves();
			
			count = 0;
			
			Game writeGame1 = new Game();
			
			Game writeGame2 = new Game();
			
			LinkedList<Move> writeList1 = writeGame1.getMovelist();
			
			// rotations step one
			
			while (fixMoves.hasNext()) {
				
				
				move = fixMoves.next();
	
				if(!move.getPass()){
				
				if(nflip)move = northSouth(move);
				
				if(eflip)move = eastWest(move);
				
				if(sflip)move = swNe(move);
				
				if(bflip)move = nwSe(move);
				
				}
				
				writeList1.add(move);
				
				//if()
				//Move rotate = swNe(move); // test the swNe rotation
				//assertNotNull(move);
				//System.out.println("Fixed?: "); // + move.getColour() + " Move Row: " + move.getRow() + " Move Column: " + move.getColumn());
				
				if(!move.getPass())
				System.out.format("Move: %d Row: %2d Col: %2d   ",  move.getColour() , move.getRow() , move.getColumn()); printMove(move);
				if(move.getPass()){System.out.println("Pass"); }
				
				//System.out.println("Rotated: " + rotate.getColour() + " Move Row: " + rotate.getRow() + " Move Column: " + rotate.getColumn());
				//System.out.println(" Move #: " + ++count);

			}
			
			fixMoves = writeGame1.getMoves();
			
			LinkedList<Move> writeList2 = writeGame2.getMovelist();
			
			
			// doing the rotations in two steps probably isn't computationally necessary
			// but it makes writing it slightly less confusing, so that is nice
			
			// rotations step 2
			
			while (fixMoves.hasNext()) {
				
				
				move = fixMoves.next();
	
				if(!move.getPass()){
				
					if(nflip2)move = northSouth(move);
				
					if(eflip2)move = eastWest(move);
				
					if(sflip2)move = swNe(move);
				
					if(bflip2)move = nwSe(move);
				
				}
				
				writeList2.add(move);
			
			}
			
			// Writing new SGF File
			
			
			
		
			
			
			
			String outString = "";
			
			
			fixMoves = writeGame2.getMoves();
			count = 0;
			
			// testing to see if we actually changed the sgf
			
			System.out.println("print check between sgf dumps");
			
			while(fixMoves.hasNext()){
			
				move = fixMoves.next();
				
				//assertNotNull(move);
				//System.out.println("Fixed?: "); // + move.getColour() + " Move Row: " + move.getRow() + " Move Column: " + move.getColumn());
				if(!move.getPass())
				System.out.format("Move: %d Row: %2d Col: %2d   ",  move.getColour() , move.getRow() , move.getColumn()); printMove(move);
				if(move.getPass()) {
					System.out.println("Pass");
				}
				//System.out.println(" Move #: " + ++count);
				
				if(move.getColour() == 2){
					
					outString += ";B[";
				}
				else {
					
					outString += ";W[";
					
				}
				
				// cast co-ordinates to char to save to file
				
				
				if(!move.getPass()){
					char rowsChar = (char)(move.getRow()+97);
					char colsChar = (char)(move.getColumn()+97);
				
					outString += colsChar;
					outString += rowsChar;
				
				}
				
				outString += "]";
			
			}
			
			
			FileReader reader2 = new FileReader( fileName);
			
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
			
			PrintWriter out = new PrintWriter(outDirectory + localName);
			
			out.print(prefix);
			
			out.close();
			
			reader2.close();
			
			System.err.println();

		} catch (IOException e) {
			System.err.println(e);
			e.printStackTrace();
			//fail("throw an error");
		} catch (ParseException e) {
			System.err.println(e);
			e.printStackTrace();
			//fail("throw an error");
		}

	}
}
 