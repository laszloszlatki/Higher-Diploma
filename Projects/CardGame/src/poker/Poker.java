package poker;

import java.util.List;

public class Poker {

	public static void main(String[] agrs) {

		// create a shuffled deck
		Deck deck = new Deck(5);
		
		// create 2 hand from same deck
		Hand myHand = new Hand(deck);
		Hand yourHand = new Hand(deck);
		
		System.out.println(myHand.handStrength(myHand.getHand ()));
		System.out.println(yourHand.handStrength(yourHand.getHand()));
		
		
		
	}
}
