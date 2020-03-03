/*
 * https://www.instructables.com/id/How-to-Write-a-Tic-Tac-Toe-Program-in-Java/
 * */

public class TTT {

	private char[][] board;
	private char currentPlayerMark;
	
	//Gives us access to currentPlayerMark
	public char getCurrentPlayerMark() {
		return currentPlayerMark;
	}
	
	// randomly decide player to start
	private void setCurrentPlayerMark() {
		
		int N = (int)(Math.random()*2);
		
		if(N == 0) {
			currentPlayerMark = 'x';
		} else {
			currentPlayerMark = 'o';			
		}
	}
	
	public TTT() {
		
		board = new char[3][3];
		setCurrentPlayerMark();
		initializeBoard();
	}
	
	// set/reset the board to an empty state and decide who starts
	private void initializeBoard() {
		
		// loop through rows
		for(int i = 0; i < 3; i++) {
			
			// loop through columns
			for(int j = 0; j < 3; j++) {
				board[i][j] = '-';
			}
		}
		
	}
	
	// Print the current board
	public void printBoard() {
		
		System.out.println("-------------");
		
		for(int i = 0; i < 3; i++) {		// row
			System.out.print("| ");
			
			for(int j = 0; j < 3; j++) {
				System.out.print(board[i][j] + " | ");
			}
			System.out.println();
			System.out.println("-------------");
		}
	}
	// Loop through all cells of the board and if one is found to be empty (contains char '-') then return false.
    // Otherwise the board is full.
	public boolean isBoardFull() {
		boolean isFull = true;
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(board[i][j] == '-') {
					isFull = false;
				}
			}
		}
		return isFull;
	}
	
	// Returns true if there is a win, false otherwise.
    // This calls our other win check functions to check the entire board.
	public boolean checkForWin() {		
		return checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin();}
	
	// Loop through rows and see if any are winners.
	private boolean checkRowsForWin() {
		for(int i = 0; i < 3; i++) {
			if(checkRowCol(board[i][0], board[i][1], board[i][2]) == true) {
				 return true;
			}			
		}
		return false;
	}
	
	// Loop through columns and see if any are winners.
	private boolean checkColumnsForWin() {
		for(int i = 0; i < 3; i++) {
		if(checkRowCol(board[0][i], board[1][i], board[2][i]) == true) {
			 return true;
		}
		
	}
	return false;
	}
	
	// Check the two diagonals to see if either is a win. Return true if either wins.
	private boolean checkDiagonalsForWin() {
		return(checkRowCol(board[0][0], board[1][1], board[2][2]) == true ||
				checkRowCol(board[0][2], board[1][1], board[2][0]) == true);
	}
	
	/*
	 * This method will check the three specified characters taken in to see if
	 * all three are the same ‘x’ or ‘o’ letter. If so, it will return true.
	 */
	private boolean checkRowCol(char c1, char c2, char c3) {
		return((c1 != '-') && (c1 == c2) && (c2 == c3));
		}	

	// change player mark back and forth.
	public void changePlayer() {
		if(currentPlayerMark == 'x') {
			currentPlayerMark = 'o';
		} else {
			currentPlayerMark = 'x';
		}
		
	}
	
	// check if place is OK to mark
	public boolean placeMark(int row, int col) {

		// Make sure that row and column are in bounds of the board.
		if(row >= 0 && row <= 2) {
			if(col >= 0 && col <= 2) {
				if(board[row][col] == '-') {
					board[row][col] = currentPlayerMark;
					return true;
				}
			}
		}
		return false;

	}
}
