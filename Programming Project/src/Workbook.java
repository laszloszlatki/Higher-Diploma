
/* 
 * @author	Laszlo Szlatki
 * @date	22/11/2019
 * @subject	CS4141 assignment
 */

/*
 * Assume user inputs indexes from 1 to sheet length
 * If a sheet hasn't been moved due to unique settings, all moving methods return invalid (i.e. last sheet moved to the end etc.)
 */

public class Workbook {

	private String[] workbook;
	private int nextSheetNum;
	private int nextSheetIndex;			// also represents number of active sheets
	String allowedChars = "ABCDEFGHIJKLNMOPQRSTUVWXYZ"
		    + "abcdefghijklmnopqrstuvwxyz"
		    + "0123456789" + " ";
	
	/*
	 * constructor
	 * 3 sheets created, inserted in index 0-2 and called: "Sheet1", "Sheet2", "Sheet3"
	 * @param		capacity		user can determine max capacity
	 */
	public Workbook(int capacity){
		
		// if requested capacity is not between 3 - 256, set it to 256
		if(capacity < 3 || capacity > 256) {
			capacity = 256;
		}
		workbook = new String[capacity];
		
		for(int i = 0; i < 3; i++) {
			workbook[i] = "Sheet" + (i+1);
		}
		nextSheetNum = 4;
		nextSheetIndex = 3;
	}
	
	/*
	 * constructor
	 * 3 sheets created, inserted in index 0-2 and called: "Sheet1", "Sheet2", "Sheet3"
	 */
	public Workbook() {
		
		this(256);
	}
	
	/*
	 * index of the requested sheet
	 * 
	 * @param 		sheetName		name of sheet, where index is required
	 * @return		index			index if sheet exists; -1 if nonexistent
	 */
	public int index(String sheetName) {
				
		for (int i = 0; i < nextSheetIndex; i++) {
			if(workbook[i].compareToIgnoreCase(sheetName) == 0) {
			return i + 1;		// increment from index to (user)position
			}
		}		
		return -1;
	}

	/*
	 * name of the requested sheet
	 * 
	 * @param 		index			index of sheet, where name is required
	 * @return		nameOfSheet		sheet name if valid; "" if invalid
	 */
	public String sheetName(int pos) {
		
		if(isValidPosition(pos)) {
			return workbook[pos - 1];
		}
		return null;
	}
	
	/*
	 * displays all non-null elements in the workbook
	 */
	public void display() {

    for (int i = 0; i < nextSheetIndex; i++) {
        System.out.print("Position " + (i + 1) + "is named " + this.workbook[i]);
    }
    System.out.println();
}
	
	/*
	 * number of current elements
	 * 
	 * @return 		sheetCount		number of sheets in workbook
	 */
	public int length() {
		
		return nextSheetIndex;
	}

	/*
	 * Add a new sheet, if workbook not full
	 * workbook can have up to 256 sheets
	 * new sheet's default name SheetN
	 * @return						true if added, false if not
	 */
	public boolean add() {
		
		boolean added = false;		
		
		if(nextSheetIndex < workbook.length) {				// check if not full already
			while((index("Sheet" + nextSheetNum)) != -1) {
				nextSheetNum++;
			}			
			workbook[nextSheetIndex] = "Sheet" + nextSheetNum; 
			nextSheetNum++;
			nextSheetIndex++;
			added = true;	
		}
		return added;
	}

	/* 
	 * Remove a sheet if not the last one
	 * must always be at least one sheet
	 * @param 		index			index of the sheet to be removed
	 * @return		sheetName		name of the removed sheet; "" if unsuccessful
	 */
	public String remove(int pos) {
		
		String toBeRemoved = null;
		
		if(isValidPosition(pos) && nextSheetIndex >= 2) {		// if minimum 2 sheet and index valid
			toBeRemoved = workbook[pos - 1];					// decrement from position to index
			shift('L', pos, nextSheetIndex);
			nextSheetIndex--;
		}
		return toBeRemoved;
	}
		
	/* 
	 * Remove a sheet if not the last one
	 * @param 		sheetName		name of the sheet to be removed
	 * @return		index			index of removed sheet; -1 if unsuccessful
	 */
	public int remove(String sheetName) {
	
		int	pos = index(sheetName); 		
		if(remove(pos) != null) {
			return pos;			
		}
		return -1;
	}
	
	/*
	 * Move a sheet from one position to another
	 * @param	from	index of the sheet to be moved
	 * @param	to		index of the sheet to be moved to before/after
	 * @param	before	if true, goes before the to sheet, else goes after
	 * @return			name of the sheet moved, or "" if none moved
	 */
	public String move(int from, int to, boolean before) {	// takes position
		if (from == to || !isValidPosition(from) || !isValidPosition(to)) {  // checks if 'from' and 'to' are invalid or the same
			return null;
		}					
		if(before == true && from + 1 == to) {	// if "move" to before the following sheet (no movement)
			return null;
		}
		if(before == false && from - 1 == to) {	// if "move" to after the preceding sheet (no movement)
			return null;
		}		
		
		String movedSheet = workbook[from - 1];
		if (from < to) {	// positions
			to--;
			if(before) {
				shift('L', from + 1, to - 1);
				workbook[to - 1] = movedSheet;
			} else {
				shift('L', from + 1, to);
				workbook[to] = movedSheet;
			}
		} else {		// from > to
			from--;
            if(before) {
                shift('R',to,from - 1);
                workbook[to] = movedSheet;
            } else {
                shift('R',to + 1,from - 1);
                workbook[to + 1] = movedSheet;
            }
		}
		return movedSheet;
	}

	/*
	 * Move a sheet from one position to another
	 * @param	from	name of the sheet to be moved
	 * @param	to		name of the sheet to be moved to before/after
	 * @param	before	if true, goes before the to sheet, else goes after
	 * @return			index of the sheet moved to, or -1 if none moved
	 */
	public int move(String from, String to, boolean before) {
			
	int position = -1;
	if(!isValidPosition(index(from)) || !isValidPosition(index(to)) || index(from) == index(to)) {
		return -1;							// checks if 'from' and 'to' are invalid or the same
		}
	if(index(from)+1 == index(to)) {		// if "move" to before the following sheet (no movement)
		return -1;
		}
	if(index(from)-1 == index(to)) {		// if "move" to after the preceding sheet (no movement)
		return -1;
		}
	
		if(before) {
			
			if(index(from) < index(to)) {
				position = index(to) - 1;
			} else {
				position = index(to);
			}
		} else {
			
			if(index(from) < index(to)) {
				position = index(to);
			} else {
				position = index(to) + 1;
			}
		}
		move(index(from), index(to), before);	
	return position;
	}

	/*
	 * Move sheet behind the last active sheet
	 * @param		from	(user)position of sheet to be moved
	 * @return				name of moved sheet or "" if unsuccessful
	 */
	public String moveToEnd(int from) {
		
		return(move(from, nextSheetIndex, false));		
	}
	
	/*
	 * Move sheet behind the last active sheet
	 * @param		from	name of sheet to be moved
	 * @return				original (user)position of sheet or -1 if unsuccessful
	 */
	public int moveToEnd(String from) {
		
		if(index(from) == nextSheetIndex) {		// if moved sheet is already last (no movement)
			return -1;
		}
		int movedSheet = index(from);
		move(from, sheetName(nextSheetIndex), false);					
		return movedSheet;

	}
	
	/*
	 * Renames the specified sheet
	 * name must be at least one character long
	 * names can include the characters A to Z, upper and lower case; the digits 0 to 9 and space
	 * names must be unique case-insensitive
	 * @param	currentName	the name of the sheet to be changed
	 * @param 	newName		the new name of the sheet
	 * @return 				the position of the renamed sheet or -1 if not successful
	 */
	public int rename(String currentName, String newName) {
		
		int currentSheetIndex = index(currentName) - 1;
		if(currentSheetIndex  == -2 || isNameExists(newName) || !containsOnlyValidChars(newName)) {	// - 2 to factor in pos to index conversion
			return -1;
		}		 		
		workbook[currentSheetIndex] = newName;
		return currentSheetIndex + 1;	    	// increment from index to (user)position
	}

	//////////////////////////////////////////////////////
	public String toString() {
		
		String s = "";
		for (int i = 0; i < nextSheetIndex; i++) {
	        s = s + (workbook[i] + ",");
	    }
		s = ("[" + s.substring(0, s.length() - 1) + "]"); // - 1 to get rid of last comma
		
		return s;
	}
	
	// Dermot's method for shifting the elements right and left
	private void shift(char direction, int first, int last) {	// indexes not positions
		 if(direction == 'L' || direction == 'l') {
			 for(int i = first; i <= last; i++) {
				 workbook[i-1] = workbook[i]; // Note i-1 to move down/left
	         }
	     } else {
	            for(int i = last; i >= first; i--) {
	                workbook[i+1] = workbook[i];// Note i+1 to move up/right
	            }
	        }
	}
	
	// checks if proposed name only contains valid characters
	private boolean containsOnlyValidChars(String newName) {
		
		for(int i = 0; i < newName.length(); i++) {					
			if(allowedChars.indexOf(newName.charAt(i)) == -1) {		// contains invalid char
			return false;
			}
		}
		return true;
	}
	
	// checks if new sheet name already exists
	private boolean isNameExists(String newName) {
		for(int i = 0; i < nextSheetIndex; i++) {			
			if (newName.equalsIgnoreCase(workbook[i])) {
				return true;
			}
		}		
		return false;
	}
	
	// checks if input position is valid position of the array
	private boolean isValidPosition(int pos) {
		return pos >0 && pos <= nextSheetIndex;
	}
	
	// method for JUnit, that returns the current elements in an []
	public String[] toArray() {
		
		return workbook;
	}
}
