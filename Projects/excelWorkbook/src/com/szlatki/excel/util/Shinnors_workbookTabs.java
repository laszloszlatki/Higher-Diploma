package com.szlatki.excel.util;
public class Shinnors_workbookTabs {
    private String[] tabs;        // space for storing list 
    private int nextAvailablePosition;    // identifies next available position
    private int nextSheetNameNumber;  // stores next usable new sheet number value

    public Shinnors_workbookTabs() {
        // Using the Default constructor the list can have up to 256 entries
        // To reduce coding duplication we use the other constructor (i.e. the one that takes a parameter)
        // to initialise the default settings. This saves us having to
        // duplicate code.
        this(256); // invoke the other constructor specifying a capacity of 256
    }

    // Overloaded constructor allows YOU to specify the list capacity
    // Useful for testing with small lists
    public Shinnors_workbookTabs(int n) {
        // Create a list of size 'n'
        // and insert the sheet names for "Sheet1", "Sheet2" and "Sheet3".
        //
        // Just make sure that 0 < n <= 256. If it is not then default to 256 sheets.
        if(n < 1 || n > 256) {
            n = 256;
        }
        // Create the array with capacity 'n'
        tabs = new String[n];

        int defaultSheetCount = 3;// Usually start with 3 sheets in the list
        if(n < 3) {
            // if can't have 3 then have whatever 'n' is
            defaultSheetCount = n;
        }
        // Populate the array with sheets named in sequence "Sheet1", "Sheet2" etc.
        // NOTE: Sheet1 in position 0; Sheet2 in position 1; so the number in the new sheet name is position+1
        for(int i=0; i < defaultSheetCount; i++) {
            // NOTE:
            // list positions numbered starting at zero       
            //   |               BUT Sheet names contain numbers that start at 1            
            //   |                         |                          
            //   V                         V                         
            tabs[i] = new String("Sheet"+(i+1)); 
        }
        // Record initial state of the array utilisation by setting the instance variables 
        nextAvailablePosition = defaultSheetCount;
        nextSheetNameNumber = defaultSheetCount+1; // remember Sheet NAMES (not positions) have numbers 1 to N
    }

    public boolean add() {
        // Check if we have space for an additional sheet
        if(nextAvailablePosition < tabs.length) {
            // Identify a valid name for the new sheet
            // Try nextSheetNameNumber value 
            // If a sheet with that value already exists then repeatedly 
            // increment until we get to a value that doesn't already exist
            while(index("Sheet"+nextSheetNameNumber) != -1) {
                nextSheetNameNumber++;
            }
            // When the while stops we MUST have a nextSheetNameNumber for a 
            // sheet that DOESN'T exist - so insert the new name
            tabs[nextAvailablePosition] = "Sheet" + nextSheetNameNumber;
            // record the fact the position is in use
            nextAvailablePosition++;
            // report a successful add operation
            return true;
        }
        // If we reach this point in the code then the list is FULL.
        // We couldn't add a new item so we report an unsuccessful add operation
        return false;
    }

    public int remove(String sheetName) {
        // Find the position of the sheet
        int pos = index(sheetName);
        // Attempt to remove the sheet using the position/index 
        // version of the remove operation. 
        if(remove(pos) != null) {
            return pos;
        }
        return -1;
    }

    public String remove(int index) {
        // Make an assumption:
        //      The index value is INVALID and so there is no sheetName to return
        String sheetName = null; 
        // Check that the index is valid and there is more than one sheet left 
        if(isValidIndex(index) && nextAvailablePosition > 1 ) {
            // The sheet exists and can be removed
            // Make a note of the sheetName to be removed
            sheetName = tabs[index];
            // Remove it
            shift('L',index+1,nextAvailablePosition-1);
            // Decrement the nextAvailablePosition counter
            nextAvailablePosition--;
        }
        // Return the sheetName or null
        return sheetName;
    } 

    private boolean isValidIndex(int index) {
        // This is a 'helper' method (i.e. one NOT specified in the specification
        // but which provides a useful operation that "helps" with something else).
        // It checks if the parameter value is the index value of one of the list 
        // locations currently in us.
        return index >= 0 && index < nextAvailablePosition;
        // Alternatively
        // if(index >= 0 && index < nextAvailablePosition;) {
        //     return true;
        // } else {
        //     return false;
        // }
        // Alternatively, again
        // if(index >= 0 && index < nextAvailablePosition;) {
        //     return true;
        // }
        // // Can only reach this statement when if is false
        // return false;      
    }

    public void display() {
        // Displays the entries in the list. NOTE - NOT the entire list. ONLY the 'active' or 
        // currently used locations.
        for(int i=0; i < nextAvailablePosition; i++) {
            System.out.println("Position " + i + " is named " + this.tabs[i]);
        }
    }

    public String toString() {
        // NOT included in the project specification but ALWAYS useful to have.
        // This method returns a String that contains the entries in the list.
        String contents;
        //if(nextAvailablePosition == 0) {
        //    contents = "[]";
        //} else if(nextAvailablePosition == 1) {
        //    contents = "[" + tabs[0] + "]";
        //} else {
        //    contents = "[" + tabs[0];
        //    for(int i=1; i < nextAvailablePosition; i++) {
        //        contents = contents + "," + tabs[i];
        //    }
        //    contents = contents + "]";
        //}
        switch(nextAvailablePosition) {
            case 0: contents = "[]"; // empty list
                    break;
            case 1: contents = "[" + tabs[0] + "]"; // contains just 1 item
                    break;
            default:
                    contents = "[" + tabs[0]; // add first list item to string
                    // Now, starting at the second list item, add the remaining items separated by commas
                    for(int i=1; i < nextAvailablePosition; i++) {
                        contents = contents + "," + tabs[i]; // add a "," and then the item
                    }
                    contents = contents + "]"; // close the square brackets at end of list
        }    
        return contents;
    }
    
    public int rename(String currentName, String newName) {
        // Get the index of the tab you want to rename
        int currentNameIndex = index(currentName);
        // If the tab exists     and the new name DOESN'T exist
        if(currentNameIndex != -1 && index(newName) == -1) {
            // change the current name of the tab to the new name
            tabs[currentNameIndex] = newName;
            // return the index of the renamed tab
            return currentNameIndex;
        }
        // return -1 to indicate the operation was not successful
        return -1;
    }

    public int index(String tabName) {
        // traverse the list of tabs from beginning to end
        // Q: Would it make any difference if we traversed from end to beginning?
        for(int i=0; i < nextAvailablePosition ; i++) {
            // if the tab at position i is equal to the one we are looking for
            if(tabs[i].compareToIgnoreCase(tabName) == 0) {
                // return it's position in the list AND exit the method
                return i;
            }
        }
        // If we reach this statement then we must have traversed the ENTIRE list
        // and NOT found the name we were looking for
        // return -1 to indicate the operation was not successful
        return -1;
    }

    public String sheetName(int index) {
        // Check if the index parameter is a valid index value
        // (i.e. we have an sheet name at that position in the list
        if(isValidIndex(index)) {
            // if it's valid return the name at the position
            return tabs[index];
        }
        // return null to indicate that there is no name at the index position
        return null;
    }

    public int length() {
        // return the current length of the tab list.
        // NOTE NOT the capacity of the list .
        // The number of entries currently in the list.
        return nextAvailablePosition;
    }
    
    private void shift(char direction, int first, int last) {
        if(direction == 'L' || direction == 'l') {
            for(int i = first; i <= last; i++) {
                tabs[i-1] = tabs[i]; // Note i-1 to move down/left
            }
            // Alternatively
            //System.arraycopy(tabs,first,tabs,first-1,last-first+1);
        } else {
            for(int i = last; i >= first; i--) {
                tabs[i+1] = tabs[i];// Note i+1 to move up/right
            }
            // Alternatively
            //System.arraycopy(tabs,first,tabs,first+1,last-first+1);
        }
    }
    
    public int moveToEnd(String from) {
        int fromIndex = index(from);
        if(fromIndex != -1) {
            move(fromIndex,nextAvailablePosition - 1 /*end of list */,false /*after*/);
        }
        return fromIndex;
    }
    
    public String moveToEnd(int from) {
        String fromName = null;
        if(isValidIndex(from)) {
            fromName = tabs[from];
            move(from,nextAvailablePosition - 1 /*end of list */,false /*after*/);
        }
        return fromName;
    }
    
    public String move(String from, String to, boolean before) {
        // Use the move operation with index parameters to actually do the remove
        // Remember, it checks the validity of the index parameters
        return move(index(from),index(to),before);
    }
    
    public String move(int from, int to, boolean before) {
        /*
         * 
         * NOTE: Two VERY IMPORTANT points about the "move" method
         *       (1) The ACTUAL MOVING of the item(s) is handled
         *           by the "shift" helper method
         *       (2) The code in this method simply checks the
         *           validity of the parameters, selects the
         *           appropriate move option to use and then passes
         *           the correct parameters to the "shift" method
         *           to actually achieve the move.
         *       In other words, the "move" method does a lot of
         *       administration to ensure that the "shift" method
         *       can successfully move the items (i.e. that it won't
         *       fail or crash).
         *       
         *       NB NB NB: It takes more code to ensure that the 
         *                 program is reliable than it does to move
         *                 the items in the array!!!!!!!!!
         */
        String fromName = null;
        int i;
        // If the indexes are the same OR EITHER of them is not valid
        if(from == to || !isValidIndex(from) || !isValidIndex(to)) {
            // return null to indicate operation unsuccessful
            return null;
        }
        fromName = tabs[from]; // Make a note of the 'from' tab name
        if(from < to) {
            if(before) {
                shift('L',from+1,to-1);
                tabs[to-1] = fromName;
            } else {
                shift('L',from+1,to);
                tabs[to] = fromName;
            }
        } else {
            // from > to
            if(before) {
                shift('R',to,from-1);
                tabs[to] = fromName;
            } else {
                shift('R',to+1,from-1);
                tabs[to+1] = fromName;
            }
        }
        return fromName;
    }      
}