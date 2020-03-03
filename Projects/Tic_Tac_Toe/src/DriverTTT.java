import java.util.Scanner;

public class DriverTTT {
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);	
		TTT game = new TTT();
		System.out.println("Tic-Tac-Toe!");
		do {
			System.out.println("Current board layout:");			
			game.printBoard();
			int row;
			int col;
			do {
				System.out.println("Player " + game.getCurrentPlayerMark() + ", enter an empty row and column to place your mark!");
				row = scan.nextInt() - 1;
				col = scan.nextInt() - 1;	
				
			} while(!game.placeMark(row, col));	
			
			game.changePlayer();	
			
		} while(!game.isBoardFull() && !game.checkForWin());	
		
		if(game.isBoardFull() && !game.checkForWin()) {
			game.printBoard();
			System.out.println("The game was a tie!");
		} 
		else {
			System.out.println("Current board layout:");
			game.printBoard();
			game.changePlayer();
			System.out.println("Gratulations!\n" + Character.toUpperCase(game.getCurrentPlayerMark()) + " Wins!");
		}
		scan.close();
	}
}
