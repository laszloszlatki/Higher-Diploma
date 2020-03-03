package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DiceGame {

	public static void main(String[] args) {

		// rollTheDice();
		// rollUntilOver3();

		// rollTwoDices();
		// rollTwoUntilWinner();

		// rollTwelveDices();
		// rollTwelveDicesFrequency();
		// rollTwelveDicesBoolean();
		// rollTwelveDicesArrayList();
		// rollTwelveUntilWinner();
		rollTwelveDicesUserInput();
	}

	/*
	 * Create a dice Roll it and show the result, saying if it is greater than 3
	 */
	public static int rollTheDice() {
		Dice dice = new Dice();
		dice.rollDice();
		dice.reportDice();
		if (dice.getFaceUp() > 3) {
			System.out.println("Greater than 3");
		}
		return dice.getFaceUp();
	}

	/*
	 * Repeat rolling it and show the result, until it is greater than 3
	 */
	public static void rollUntilOver3() {
		int counter = 0;
		do {
			System.out.printf("This is your roll #%d:\n", ++counter);
			// method call in condition will actually roll the dice
		} while (rollTheDice() <= 3);
	}

	/*
	 * Create two dice; Roll them and show the results; (Alternatively, create one
	 * dice and roll it twice!) Assesses if the rolls are equal and report "You're a
	 * winner" if so;
	 */
	public static boolean rollTwoDices() {
		Dice dice1 = new Dice();
		Dice dice2 = new Dice();
		dice1.rollDice();
		dice1.reportDice();
		dice2.rollDice();
		dice2.reportDice();
		if (dice1.getFaceUp() == dice2.getFaceUp()) {
			System.out.println("You're a winner");
			return true;
		}
		return false;
	}

	/*
	 * Repeat rolling 2 dices, until this ("You're a winner") has happened
	 */
	public static void rollTwoUntilWinner() {
		int counter = 0;
		do {
			System.out.printf("This is your roll #%d:\n", ++counter);
			// method call in condition will actually roll the dices
		} while (rollTwoDices() == false);
	}

	/*
	 * Create an array of 12 rolls; Show the results; See if, between all rolls, all
	 * the numbers from 1-6 have been rolled. If so, report "You're a winner" and
	 * end.
	 *
	 * You may have to up or lower the # to get winner status! See how you do
	 */
	public static boolean rollTwelveDices() {
		boolean winner = false;
		final int numRolls = 12;
		int[] rolls = new int[numRolls];
		Dice dice = new Dice();
		for (int i = 0; i < numRolls; i++) {
			rolls[i] = dice.rollDice();
		}

		for (int roll : rolls) {
			System.out.println(roll);
		}
		if (hasAllSix(rolls) == true) {
			System.out.println("You're a winner");
			winner = true;
		}
		return winner;
	}

	private static boolean hasAllSix(int[] rolls) {
		boolean winner = false;
		int[] numbers = { 1, 2, 3, 4, 5, 6 };
		int count = 0;
		int[] hits = new int[numbers.length];

		for (int i = 0; i < rolls.length; i++) {
			for (int j = 0; j < numbers.length; j++) {

				// check if hit, and save current hit in the hits[], if not already saved
				if (rolls[i] == numbers[j] && !check(hits, numbers[j])) {

					hits[count++] = numbers[j];
				}
			}
		}
		// check if all required numbers are found
		if (count == 6) {
			winner = true;
		}
		return winner;
	}

	private static boolean check(int[] arr, int valueToCheck) {
		// check if the specified element is present in the array or not
		// using Linear Search method
		boolean test = false;
		for (int element : arr) {
			if (element == valueToCheck) {
				test = true;
				break;
			}
		}
		return test;
	}

	/*
	 * Create an array of 12 rolls; Show the results; See if, between all rolls, all
	 * the numbers from 1-6 have been rolled. If so, report "You're a winner" and
	 * end.
	 *
	 * You may have to up or lower the # to get winner status! See how you do
	 */
	public static boolean rollTwelveDicesFrequency() {
		boolean winner = false;
		final int numRolls = 12;
		int[] rolls = new int[numRolls];
		Dice dice = new Dice();
		int[] frequency = new int[6];

		// fill array with rolls and increment frequency[] with each roll
		for (int i = 0; i < numRolls; i++) {
			rolls[i] = dice.rollDice();
			dice.reportDice();
			frequency[rolls[i] - 1]++;
		}

		// count of zeros in the frequency[] = how many numbers weren't rolled
		int countOfZeros = 0;
		System.out.print("\nFrequency is: ");
		for (int freq : frequency) {
			System.out.print(freq + " ");
			if (freq == 0) {
				countOfZeros++;
			}
		}

		if (countOfZeros == 0) {
			winner = true;
			System.out.println("\nYou're a winner");
		}
		return winner;
	}

	/*
	 * Create an array of 12 rolls; Show the results; See if, between all rolls, all
	 * the numbers from 1-6 have been rolled. If so, report "You're a winner" and
	 * end.
	 *
	 * You may have to up or lower the # to get winner status! See how you do
	 */
	public static boolean rollTwelveDicesBoolean() {
		boolean ActualWinner = false;
		final int numRolls = 12;
		int[] rolls = new int[numRolls];
		Dice dice = new Dice();
		boolean[] winner = new boolean[6];

		// fill array with rolls and 'tick' what number was rolled
		for (int i = 0; i < numRolls; i++) {
			rolls[i] = dice.rollDice();
			dice.reportDice();
			winner[rolls[i] - 1] = true;
		}
		for (boolean win : winner) {
			if (win == false) {
				return ActualWinner;
			}
		}
		ActualWinner = true;
		System.out.println("\nYou're a winner");
		return ActualWinner;
	}

	/*
	 * Create an array of 12 rolls; Show the results; See if, between all rolls, all
	 * the numbers from 1-6 have been rolled. If so, report "You're a winner" and
	 * end.
	 *
	 * You may have to up or lower the # to get winner status! See how you do
	 */
	public static boolean rollTwelveDicesArrayList() {
		boolean winner = false;
		Dice dice = new Dice();
		final int numRolls = 12;

		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < numRolls; i++) {
			list.add(dice.rollDice());
			dice.reportDice();
		}
		List<Integer> numbers = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			numbers.add(i + 1);
		}

		if (list.containsAll(numbers)) {
			winner = true;
			System.out.println("\nYou're a winner");
		}
		return winner;
	}

	/*
	 * Repeat rolling twelve until "You're a winner"
	 */
	public static void rollTwelveUntilWinner() {
		int counter = 0;
		do {
			System.out.printf("\n\nThis is your roll #%d:\n", ++counter);
			// method call in condition will actually roll the dices
		} while (rollTwelveDicesFrequency() == false);
	}

	/*
	 * Repeat rolling twelve as long as user wants
	 */
	public static void rollTwelveDicesUserInput() {
		int counter = 0;
		do {
			System.out.printf("\n\nThis is your roll #%d:\n", ++counter);
			rollTwelveDicesBoolean();
		} while (continueToPlay() == true);
	}

	/*
	 * User decide to continue playing or not
	 */
	private static boolean continueToPlay() {
		boolean playMore = false;

		Scanner sc = new Scanner(System.in);
		System.out.println("Would you like to play again? (Y/N)");
		char play = sc.next().charAt(0);
		sc.close();

		if (play == 'Y' || play == 'y') {
			playMore = true;
		} else {
			System.out.println("Have a great day!");
		}
		return playMore;

	}

}