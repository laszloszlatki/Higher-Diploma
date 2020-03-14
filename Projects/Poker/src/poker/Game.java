/*
 * @author: Laszlo Szlatki
 * @date: 06/Mar/2020
 * */
package poker;

import javax.swing.JOptionPane;

/*
 * Game class contains main() to drive the program.
 * - It generates a shuffled deck of cards,
 * - deals 5 cards in each hands
 * - displays each hand with highest strength in it
 * - checks the 2 hands against the rules
 * - announces the winner
 * */
public class Game {

	public static void main(String[] agrs) {

		// create a shuffled deck
		Deck deck = new Deck(5);

		// ask user for number of hands
		boolean askAgain = false;
		int playerNo = 0;
		do {
			String playerNumber = JOptionPane.showInputDialog(null, "How many player?");

			// validate user input
			try {
				playerNo = Integer.parseInt(playerNumber);
				if (playerNo > 0 && playerNo < 11) {
					askAgain = true;
				} else {
					JOptionPane.showMessageDialog(null, "Number must between 1 and 10 inclusive");
					askAgain = false;
				}
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "Incorrect number format");
			}
		} while (!askAgain);

		// create players array to hold required hands
		Hand[] players = new Hand[playerNo];

		// create required number of hands and store it in players[]
		for (int i = 0; i < playerNo; i++) {
			players[i] = createHand(deck);
			System.out.println(players[i] + "\n");
		}

		// nominate first hand as winner than compare rest of them to it
		Hand winner = players[0];
		// winningHand starts from 1, as it represents the winning player
		int winningHand = 1;
		// i starts from 1 as players[0] already handled
		for (int i = 1; i < playerNo; i++) {
			Hand oldWinner = winner;
			winner = Rules.checkWinner(winner, players[i]);
			if (oldWinner != winner) {
				winningHand = i + 1;
			} 
		}
		
		// announce winner
		JOptionPane.showMessageDialog(null, "The winner is player " + winningHand + " with: " + winner);

//		// create 2 hand from same deck
//		Hand myHand = new Hand(deck);
//		Hand yourHand = new Hand(deck);
//
//		// display hand 1
//		for (Card c : myHand.getHand()) {
//			System.out.println(c);
//		}
//		System.out.println(myHand.handStrength(myHand.getHand()) + "\n");
//
//		// display hand 2
//		for (Card c : yourHand.getHand()) {
//			System.out.println(c);
//		}
//		System.out.println(yourHand.handStrength(yourHand.getHand()));
//
//		// pass the 2 hands to Rules.checkWinner() to decide winner
//		Hand winner = Rules.checkWinner(myHand, yourHand);
//
//		// announce the winner
//		if (winner == myHand) {
//			JOptionPane.showMessageDialog(null, "The winner is player one with: " + winner);
//			System.out.println("The winner is player one with: " + winner);
//
//		} else if (winner == yourHand) {
//			JOptionPane.showMessageDialog(null, "The winner is player two with: " + winner);
//			System.out.println("The winner is player two with: " + winner);
//		} else {
//			System.out.println("draw");
//		}

	}

	// method to create a hand
	private static Hand createHand(Deck deck) {
		Hand hand = new Hand(deck);
		return hand;
	}
}