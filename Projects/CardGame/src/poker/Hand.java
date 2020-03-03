package poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Hand of cards has an array of 5 cards, randomly selected from the deck
 * */
public class Hand {

	List<Card> myHand = new ArrayList<>();
	

	// hand with 5 random cards
	public Hand(Deck deck) {

		for (int i = 0; i < 5; i++) {
			myHand.add(deck.draw());
		}

		// sort hand by rank
		Collections.sort(myHand);
	}
	
	public List getHand() {
		return myHand;
	}

	// https://stackoverflow.com/questions/608915/need-help-determining-poker-hand-one-pair

	// check if hand has pairs
	private boolean hasPair(List sortedHand) { // requires sorted hand
		for (int i = 1; i < 5; i++) {
			if (((Card) sortedHand.get(i)).getRank() == ((Card) sortedHand.get(i - 1)).getRank()) {
				return true;
			}
		}
		return false;
	}

	// check if hand has 2 pairs
	private boolean isTwoPair(List sortedHand) { // requires sorted hand
		return (((Card) sortedHand.get(0)).getRank() == ((Card) sortedHand.get(1)).getRank()
				&& ((Card) sortedHand.get(2)).getRank() == ((Card) sortedHand.get(3)).getRank()
				&& ((Card) sortedHand.get(0)).getRank() != ((Card) sortedHand.get(2)).getRank())
				|| (((Card) sortedHand.get(1)).getRank() == ((Card) sortedHand.get(2)).getRank()
						&& ((Card) sortedHand.get(3)).getRank() == ((Card) sortedHand.get(4)).getRank()
						&& ((Card) sortedHand.get(1)).getRank() != ((Card) sortedHand.get(3)).getRank())
				|| (((Card) sortedHand.get(0)).getRank() == ((Card) sortedHand.get(1)).getRank()
						&& ((Card) sortedHand.get(3)).getRank() == ((Card) sortedHand.get(4)).getRank()
						&& ((Card) sortedHand.get(0)).getRank() != ((Card) sortedHand.get(3)).getRank());
	}

	private boolean isTrips(List sortedHand) { // requires sorted hand
		return (((Card) sortedHand.get(0)).getRank() == ((Card) sortedHand.get(2)).getRank()
				&& ((Card) sortedHand.get(0)).getRank() != ((Card) sortedHand.get(3)).getRank()
				&& ((Card) sortedHand.get(3)).getRank() != ((Card) sortedHand.get(4)).getRank())
				|| (((Card) sortedHand.get(1)).getRank() == ((Card) sortedHand.get(3)).getRank()
						&& ((Card) sortedHand.get(0)).getRank() != ((Card) sortedHand.get(1)).getRank()
						&& ((Card) sortedHand.get(0)).getRank() != ((Card) sortedHand.get(4)).getRank())
				|| (((Card) sortedHand.get(2)).getRank() == ((Card) sortedHand.get(4)).getRank()
						&& ((Card) sortedHand.get(1)).getRank() != ((Card) sortedHand.get(2)).getRank()
						&& ((Card) sortedHand.get(1)).getRank() != ((Card) sortedHand.get(0)).getRank());
	}

	private boolean isBoat(List sortedHand) { // requires sorted hand
		return (((Card) sortedHand.get(0)).getRank() == ((Card) sortedHand.get(1)).getRank()
				&& ((Card) sortedHand.get(2)).getRank() == ((Card) sortedHand.get(3)).getRank()
				&& ((Card) sortedHand.get(3)).getRank() == ((Card) sortedHand.get(4)).getRank())
				|| (((Card) sortedHand.get(0)).getRank() == ((Card) sortedHand.get(1)).getRank()
						&& ((Card) sortedHand.get(1)).getRank() == ((Card) sortedHand.get(2)).getRank()
						&& ((Card) sortedHand.get(3)).getRank() == ((Card) sortedHand.get(4)).getRank());
	}

	private boolean isQuads(List sortedHand) { // requires sorted hand
		return (((Card) sortedHand.get(1)).getRank() == ((Card) sortedHand.get(2)).getRank()
				&& ((Card) sortedHand.get(2)).getRank() == ((Card) sortedHand.get(3)).getRank()
				&& (((Card) sortedHand.get(0)).getRank() == ((Card) sortedHand.get(1)).getRank())
				|| ((Card) sortedHand.get(1)).getRank() == ((Card) sortedHand.get(2)).getRank()
						&& ((Card) sortedHand.get(2)).getRank() == ((Card) sortedHand.get(3)).getRank()
						&& ((Card) sortedHand.get(4)).getRank() == ((Card) sortedHand.get(1)).getRank());
	}

	private boolean isStraight(List sortedHand) { // requires sorted hand with no pair
		return (((Card) sortedHand.get(0)).getRank().ordinal() == ((Card) sortedHand.get(4)).getRank().ordinal() - 4);
	}

	private boolean isFlush(List sortedHand) {
		return ((Card) sortedHand.get(0)).getSuit() == ((Card) sortedHand.get(1)).getSuit()
				&& ((Card) sortedHand.get(1)).getSuit() == ((Card) sortedHand.get(2)).getSuit()
				&& ((Card) sortedHand.get(1)).getSuit() == ((Card) sortedHand.get(3)).getSuit()
				&& ((Card) sortedHand.get(1)).getSuit() == ((Card) sortedHand.get(4)).getSuit();
	}

	public String handStrength(List sortedHand) {

		if (hasPair(sortedHand)) {
			if (isTwoPair(sortedHand)) {
				return "two pair";
			} else if (isTrips(sortedHand)) {
				return "three of a kind";
			} else if (isBoat(sortedHand)) {
				return "full house";
			} else if (isQuads(sortedHand)) {
				return "four of a kind";
			} else {
				return "pair";
			}
		} else {
			if (isStraight(sortedHand)) {
				if (isFlush(sortedHand)) {
					return "straight flush";
				} else {
					return "straight";
				}
			} else if (isFlush(sortedHand)) {
				return "flush";
			} else {
				return "high card";
			}
		}
	}
}
