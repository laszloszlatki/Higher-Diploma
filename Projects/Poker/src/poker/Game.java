/*
 * @author: Laszlo Szlatki
 * @date: 06/Mar/2020
 * */
package poker;

public class Game {

	public static void main(String[] agrs) {

		// create a shuffled deck
		Deck deck = new Deck(5);

		// create 2 hand from same deck
		Hand myHand = new Hand(deck);
		Hand yourHand = new Hand(deck);

		for (Card c : myHand.getHand()) {
			System.out.println(c);
		}
		System.out.println(myHand.handStrength(myHand.getHand()) + "\n");
		for (Card c : yourHand.getHand()) {
			System.out.println(c);
		}
		System.out.println(yourHand.handStrength(yourHand.getHand()));

		// pass the 2 hands to Rules.checkWinner() to decide winner
		Hand winner = Rules.checkWinner(myHand, yourHand);
		if (winner == myHand) {
			System.out.println("I am the winner with: " + winner);
		} else if(winner == yourHand) {
			System.out.println("You are the winner with: " + winner);
		} else {
			System.out.println("draw");
		}
	}
}
