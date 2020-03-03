package poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Deck {

	private Stack<Card> deck = new Stack<Card>();

	// constructor for a deck of cards in order
	public Deck() {
		for (Suits suit : Suits.values()) {
			for (Ranks rank : Ranks.values()) {
				deck.add(new Card(suit, rank));
			}
		}
	}

	// constructor for a deck of cards shuffled
	public Deck(int shuffle) {
		this();
		for (int i = 0; i < shuffle; i++) {
			Collections.shuffle(deck, new Random());
		}
	}

	// shuffle deck
	public void shuffle() {
		// List<Card> oldDeck = new ArrayList<>();
		Collections.shuffle(this.deck, new Random());
	}

	// draw top most
	public Card draw() {
		if (deck.size() <= 0) {
			throw new EmptyDeckException();
		}
		return deck.pop();
	}

	// get deck size
	public int size() {
		return deck.size();
	}
}
