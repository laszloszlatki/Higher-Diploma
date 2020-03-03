/*https://github.com/tbourvon/sudoku/blob/master/Sudoku.java*/
package sudoku;

import java.util.Scanner;

public class Sudoku {

    /**
     * Handles command line arguments and first-level logic.
     * Possible commands (size should most of the time be 4 or 9, the difficulty can be EASY, MEDIUM, HARD, EXTREME):<br>
     * java Sudoku generate &lt;size&gt; &lt;difficulty&gt;<br>
     * java Sudoku solve &lt;size&gt;<br>
     * java Sudoku generate_solve &lt;size&gt; &lt;difficulty&gt;<br>
     * java Sudoku generate_check &lt;size&gt; &lt;difficulty&gt;
     * @param args The arguments passed to the program.
     */
    public static void main (String[] args ) {
        if (args.length < 2) {
            System.out.println("Error: Not enough arguments.");
            printUsage();
            return;
        }

        if (args[0].contains("generate")) {
            if (args.length != 3) {
                System.out.println("Error: Invalid argument count.");
                printUsage();
                return;
            }

            Board.SIZE = Integer.parseInt(args[1]);

            Generator.DIFFICULTY difficulty;

            if (args[2].contains("EASY")) {
                difficulty = Generator.DIFFICULTY.EASY;
            } else if (args[2].contains("MEDIUM")) {
                difficulty = Generator.DIFFICULTY.MEDIUM;
            } else if (args[2].contains("HARD")) {
                difficulty = Generator.DIFFICULTY.HARD;
            } else if (args[2].contains("EXTREME")) {
                difficulty = Generator.DIFFICULTY.EXTREME;
            } else {
                System.out.println("Error: Invalid difficulty.");
                printUsage();
                return;
            }

            Generator gen = new Generator();
            Board b = gen.generateSudoku(difficulty);

            System.out.println();
            System.out.print(b);

            if (args[0].contains("solve")) {
                Solver solver = new Solver(b);
                solver.solveBoard();

                System.out.print(b);
            } else if (args[0].contains("check")){
                boolean wrong = true;
                Board board= new Board();
                while(wrong) {
                    wrong = false;
                    board= typeBoard(b);
                    Solver solver = new Solver (board);
                    solver.solveBoard();

                    for (int j = 0; j < Board.SIZE && !wrong; j++) {
                        for (int k = 0; k < Board.SIZE && !wrong; k++) {
                            if (b.getNumber(j, k) != board.getNumber(j, k)){
                                System.out.println ("Error: Invalid Solution.");
                                wrong = true;
                            }
                        }
                    }
                }

                System.out.print(board);

                System.out.println("Correct Solution! :D");
            }
        } else if (args[0].contains("solve")) {
            if (args.length != 2) {
                System.out.println("Error: Invalid argument count.");
                printUsage();
                return;
            }

            Board.SIZE = Integer.parseInt(args[1]);

            Board b = new Board();
            b = typeBoard(b);

            System.out.print("Solving... ");

            Solver solver = new Solver(b);
            solver.solveBoard();

            if (b.isSolved()==false){
                System.out.println("Error: The sudoku cannot be solved");
            }
            else {
                System.out.println("Solved!");
                System.out.print(b);
            }
        } else {
            System.out.println("Invalid command.");
            printUsage();
            return;
        }
    }

    public static void printUsage() {
        System.out.println();
        System.out.println("Usage:");
        System.out.println("java Sudoku generate <size> <difficulty>");
        System.out.println("\tGenerates a sudoku.");
        System.out.println("java Sudoku generate_check <size> <difficulty>");
        System.out.println("\tGenerates a sudoku and lets the user check his/her solution.");
        System.out.println("java Sudoku generate_solve <size> <difficulty>");
        System.out.println("\tGenerates a sudoku and solves it.");
        System.out.println("java Sudoku solve <size>");
        System.out.println("\tSolves a sudoku given by the user.");
        System.out.println();
        System.out.println("size: any squared number, preferrably 4 or 9");
        System.out.println("difficulty: EASY, MEDIUM, HARD or EXTREME");
    }

    /**
     * Prompts the user to enter a board, with a given board as default board.
     * @param b The template board to fill in the prompt.
     * @return The board filled by the user.
     */
    public static Board typeBoard(Board b) {
        Board board = new Board(b);

        Scanner scanner = new Scanner(System.in);

        int x = 0;
        int y = 0;
        while (true) {
            if (!board.isConst(x, y)) {
                board.setNumber(x, y, 10);
                System.out.print(board);
                System.out.print("Enter the next number (0 if empty):");
                int number = scanner.nextInt();
                if (number> Board.SIZE){
                    System.out.println("Illegal value, must be less than"+(Board.SIZE + 1));
                    continue;
                }
                board.setNumber(x, y, number);
            }

            if (x == Board.SIZE - 1) {
                if (y == Board.SIZE - 1) {
                    break;
                }

                y++;
                x = 0;
            } else {
                x++;
            }
        }

        return board;
    }
}