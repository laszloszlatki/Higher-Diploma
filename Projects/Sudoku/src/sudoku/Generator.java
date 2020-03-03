/*https://github.com/tbourvon/sudoku/blob/master/Generator.java*/
package sudoku;

import java.util.ArrayList;

public class Generator {
	
	/**
	 * We solve the sudokus 5 times to check for unicity.
	 */	
	private static final int UNICITY_ITERATIONS = 5;

	/**
	 * We define the difficulty that can only take the following difficulty values:
	 * EASY, MEDIUM, HARD, EXTREME.
	 */	
	public enum DIFFICULTY {
		EASY, MEDIUM, HARD, EXTREME
	}

	/**
	 * Method used to generate a Sudoku. We first generate a complete board. Then we
	 * delete random numbers, and verify that the sudoku still possesses a unique
	 * solution. We stop when the solution is no longer unique. According to the
	 * difficulty chosen, we return a board with different amounts of numbers on the
	 * board.
	 * 
	 * @param difficulty The difficulty of the sudoku to be generated.
	 * @return The generated sudoku.
	 */
	public Board generateSudoku(DIFFICULTY difficulty) {
		int lowestSudoku = Board.SIZE * Board.SIZE;
		int attempts = 0;

		while (true) {
			ArrayList<Board> steps = new ArrayList<Board>();

			Board generatedSudoku = new Board();
			Solver solver = new Solver(generatedSudoku);
			solver.solveBoard();

			for (int i = 0; i < Board.SIZE; i++) {
				for (int j = 0; j < Board.SIZE; j++) {
					generatedSudoku.setConst(i, j, true);
				}
			}

			steps.add(generatedSudoku);

			while (true) {
				Board b = new Board(generatedSudoku);
				b = deleteNumber(b);
				if (verifyUnicity(b)) {
					steps.add(b);
					generatedSudoku = b;
				} else {
					break;
				}
			}

			// We display a progress log.
			attempts++;
			if (lowestSudoku > (Board.SIZE * Board.SIZE - steps.size())) {
				lowestSudoku = (Board.SIZE * Board.SIZE - steps.size());
				System.out
						.println("\nFound a sudoku with " + lowestSudoku + " (" + steps.size() + " numbers removed).");
				System.out.print("Tried " + attempts + " sudokus.");
			} else {
				System.out.print("\rTried " + attempts + " sudokus.");
			}

			if (steps.size() > (Board.SIZE * Board.SIZE) / 1.5) {
				if (difficulty == DIFFICULTY.EXTREME) {
					return steps.get(steps.size() - 1);
				}
			}
			if (steps.size() > (Board.SIZE * Board.SIZE) / 1.6) {
				if (difficulty == DIFFICULTY.HARD) {
					return steps.get(steps.size() - 1);
				} else if (difficulty == DIFFICULTY.MEDIUM) {
					return steps.get((int) ((steps.size() - 1) * 0.72));
				} else if (difficulty == DIFFICULTY.EASY) {
					return steps.get((int) ((steps.size() - 1) * 0.44));
				}
			}
			if (steps.size() > (Board.SIZE * Board.SIZE) / 2.3) {
				if (difficulty == DIFFICULTY.MEDIUM) {
					return steps.get((int) ((steps.size() - 1) * 0.95));
				} else if (difficulty == DIFFICULTY.EASY) {
					return steps.get((int) ((steps.size() - 1) * 0.6));
				}
			}
			if (steps.size() > (Board.SIZE * Board.SIZE) / 3.2) {
				if (difficulty == DIFFICULTY.EASY) {
					return steps.get((int) ((steps.size() - 1) * 0.8));
				}
			}
		}
	}

	/**
	 * Method used to verify that a Sudoku posseses a unique solution.
	 * 
	 * @param b The board we need to verify the unicity of.
	 * @return If the board possesses a unique solution.
	 */
	public boolean verifyUnicity(Board b) {
		Board[] boards = new Board[UNICITY_ITERATIONS];

		for (int i = 0; i < boards.length; i++) {
			Board board = new Board(b);
			Solver solver = new Solver(board);
			solver.solveBoard();
			boards[i] = board;
		}

		for (int i = 1; i < boards.length; i++) {
			for (int j = 0; j < Board.SIZE; j++) {
				for (int k = 0; k < Board.SIZE; k++) {
					if (boards[i].getNumber(j, k) != boards[0].getNumber(j, k))
						return false;
				}
			}
		}

		return true;
	}

	/**
	 * Deletes a random number in the board.
	 * 
	 * @param b The board we need to delete a number from.
	 * @return The board with the deleted number.
	 */
	public Board deleteNumber(Board b) {
		Board board = new Board(b);
		int x;
		int y;
		while (true) {
			x = (int) (Math.random() * Board.SIZE);
			y = (int) (Math.random() * Board.SIZE);
			if (board.getNumber(x, y) != 0) {
				board.setNumber(x, y, 0);
				board.setConst(x, y, false);
				break;
			}
		}

		return board;
	}

}