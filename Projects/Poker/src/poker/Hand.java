/*
 * @author: Laszlo Szlatki
 * @date: 05/Mar/2020
 * */
package poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Hand of cards has an array of 5 cards.
 * the top 5 cards of a shuffled stack
 * Contains methods to check greatest strength, highest and second highest card
 * */
public class Hand {

	List<Card> myHand = new ArrayList<>();

	// variables to store rank of highest combination
	Ranks highestCard = null;
	Ranks secondHighestCard = null;

	/*
	 * constructor to create a hand with 5 cards off the shuffled deck
	 */
	public Hand(Deck deck) {

		for (int i = 0; i < 5; i++) {
			myHand.add(deck.draw());
		}

		// sort hand by rank
		Collections.sort(myHand);
	}

	/*
	 * get the cards in hand
	 * 
	 * @return: list of cards in hand
	 */
	public List<Card> getHand() {
		return myHand;
	}

	// all the below methods require sorted hands
	/*
	 * check for highest rank if no valuable combination of cards
	 * 
	 * @param: list of cards in the hand
	 * 
	 * @return: always return true, there always a highest card in the hand
	 */
	private boolean hasHighCard(List<Card> sortedHand) {
		highestCard = ((Card) sortedHand.get(4)).getRank();
		secondHighestCard = ((Card) sortedHand.get(3)).getRank();
		return true;
	}

	/*
	 * check if hand has a pair
	 * 
	 * @param: list of cards in the hand
	 * 
	 * @return: return true, if there is at least one pair. false otherwise
	 */
	private boolean hasPair(List<Card> sortedHand) {
		for (int i = 1; i < 5; i++) {
			if (((Card) sortedHand.get(i)).getRank() == ((Card) sortedHand.get(i - 1)).getRank()) {
				highestCard = ((Card) sortedHand.get(i)).getRank();
				if (i != 4) {
					secondHighestCard = ((Card) sortedHand.get(i)).getRank();
				} else
					secondHighestCard = ((Card) sortedHand.get(i - 2)).getRank();
				return true;
			}
		}
		return false;
	}

	/*
	 * check if hand has two pairs
	 * 
	 * @param: list of cards in the hand
	 * 
	 * @return: return true, if there is at least two pairs. false otherwise
	 */
	private boolean isTwoPair(List<Card> sortedHand) {
		if (((Card) sortedHand.get(0)).getRank() == ((Card) sortedHand.get(1)).getRank()
				&& ((Card) sortedHand.get(2)).getRank() == ((Card) sortedHand.get(3)).getRank()
				&& ((Card) sortedHand.get(0)).getRank() != ((Card) sortedHand.get(2)).getRank()) {
			highestCard = ((Card) sortedHand.get(3)).getRank();
			secondHighestCard = ((Card) sortedHand.get(1)).getRank();
			return true;
		} else if (((Card) sortedHand.get(1)).getRank() == ((Card) sortedHand.get(2)).getRank()
				&& ((Card) sortedHand.get(3)).getRank() == ((Card) sortedHand.get(4)).getRank()
				&& ((Card) sortedHand.get(1)).getRank() != ((Card) sortedHand.get(3)).getRank()) {
			highestCard = ((Card) sortedHand.get(4)).getRank();
			secondHighestCard = ((Card) sortedHand.get(2)).getRank();
			return true;
		} else if (((Card) sortedHand.get(0)).getRank() == ((Card) sortedHand.get(1)).getRank()
				&& ((Card) sortedHand.get(3)).getRank() == ((Card) sortedHand.get(4)).getRank()
				&& ((Card) sortedHand.get(0)).getRank() != ((Card) sortedHand.get(3)).getRank()) {
			highestCard = ((Card) sortedHand.get(4)).getRank();
			secondHighestCard = ((Card) sortedHand.get(1)).getRank();
			return true;
		}
		return false;
	}

	/*
	 * check if hand has three of the same rank
	 * 
	 * @param: list of cards in the hand
	 * 
	 * @return: return true, if there is at least three of the same rank. false
	 * otherwise
	 */
	private boolean isTrips(List<Card> sortedHand) {
		if (((Card) sortedHand.get(0)).getRank() == ((Card) sortedHand.get(2)).getRank()
				&& ((Card) sortedHand.get(0)).getRank() != ((Card) sortedHand.get(3)).getRank()
				&& ((Card) sortedHand.get(3)).getRank() != ((Card) sortedHand.get(4)).getRank()) {
			highestCard = ((Card) sortedHand.get(2)).getRank();
			secondHighestCard = ((Card) sortedHand.get(4)).getRank();
			return true;

		} else if (((Card) sortedHand.get(1)).getRank() == ((Card) sortedHand.get(3)).getRank()
				&& ((Card) sortedHand.get(0)).getRank() != ((Card) sortedHand.get(1)).getRank()
				&& ((Card) sortedHand.get(0)).getRank() != ((Card) sortedHand.get(4)).getRank()) {
			highestCard = ((Card) sortedHand.get(3)).getRank();
			secondHighestCard = ((Card) sortedHand.get(4)).getRank();
			return true;

		} else if (((Card) sortedHand.get(2)).getRank() == ((Card) sortedHand.get(4)).getRank()
				&& ((Card) sortedHand.get(1)).getRank() != ((Card) sortedHand.get(2)).getRank()
				&& ((Card) sortedHand.get(1)).getRank() != ((Card) sortedHand.get(0)).getRank()) {
			highestCard = ((Card) sortedHand.get(4)).getRank();
			secondHighestCard = ((Card) sortedHand.get(1)).getRank();
			return true;
		}
		return false;
	}

	/*
	 * check if hand has full house (a pair and a trips)
	 * 
	 * @param: list of cards in the hand
	 * 
	 * @return: return true, if there is a full house. false otherwise
	 */
	private boolean isBoat(List<Card> sortedHand) {
		if (((Card) sortedHand.get(0)).getRank() == ((Card) sortedHand.get(1)).getRank()
				&& ((Card) sortedHand.get(2)).getRank() == ((Card) sortedHand.get(3)).getRank()
				&& ((Card) sortedHand.get(3)).getRank() == ((Card) sortedHand.get(4)).getRank()) {
			highestCard = ((Card) sortedHand.get(4)).getRank();
			secondHighestCard = ((Card) sortedHand.get(1)).getRank();
			return true;
		} else if (((Card) sortedHand.get(0)).getRank() == ((Card) sortedHand.get(1)).getRank()
				&& ((Card) sortedHand.get(1)).getRank() == ((Card) sortedHand.get(2)).getRank()
				&& ((Card) sortedHand.get(3)).getRank() == ((Card) sortedHand.get(4)).getRank()) {
			highestCard = ((Card) sortedHand.get(2)).getRank();
			secondHighestCard = ((Card) sortedHand.get(4)).getRank();
			return true;
		}
		return false;
	}

	/*
	 * check if hand has poker (four of the same rank)
	 * 
	 * @param: list of cards in the hand
	 * 
	 * @return: return true, if there is four of the same rank. false otherwise
	 */
	private boolean isQuads(List<Card> sortedHand) {
		if (((Card) sortedHand.get(1)).getRank() == ((Card) sortedHand.get(2)).getRank()
				&& ((Card) sortedHand.get(2)).getRank() == ((Card) sortedHand.get(3)).getRank()
				&& (((Card) sortedHand.get(0)).getRank() == ((Card) sortedHand.get(1)).getRank())) {
			highestCard = ((Card) sortedHand.get(3)).getRank();
			secondHighestCard = ((Card) sortedHand.get(4)).getRank();
			return true;
		} else if (((Card) sortedHand.get(1)).getRank() == ((Card) sortedHand.get(2)).getRank()
				&& ((Card) sortedHand.get(2)).getRank() == ((Card) sortedHand.get(3)).getRank()
				&& ((Card) sortedHand.get(4)).getRank() == ((Card) sortedHand.get(1)).getRank()) {
			highestCard = ((Card) sortedHand.get(3)).getRank();
			secondHighestCard = ((Card) sortedHand.get(0)).getRank();
			return true;
		}
		return false;
	}

	/*
	 * check if hand has straight (5 consecutive ranks)
	 * 
	 * @param: list of cards in the hand
	 * 
	 * @return: return true, if there is a straight. false otherwise
	 */
	private boolean isStraight(List<Card> sortedHand) {
		// .ordinal() uses the rank position from the Ranks enum
		if (((Card) sortedHand.get(0)).getRank().ordinal() == ((Card) sortedHand.get(4)).getRank().ordinal() - 4) {
			highestCard = ((Card) sortedHand.get(4)).getRank();
			return true;
		}
		return false;
	}

	/*
	 * check if hand has flush (all cards have the same suit)
	 * 
	 * @param: list of cards in the hand
	 * 
	 * @return: return true, if there is a flush. false otherwise
	 */
	private boolean isFlush(List<Card> sortedHand) {
		if (((Card) sortedHand.get(0)).getSuit() == ((Card) sortedHand.get(1)).getSuit()
				&& ((Card) sortedHand.get(1)).getSuit() == ((Card) sortedHand.get(2)).getSuit()
				&& ((Card) sortedHand.get(1)).getSuit() == ((Card) sortedHand.get(3)).getSuit()
				&& ((Card) sortedHand.get(1)).getSuit() == ((Card) sortedHand.get(4)).getSuit()) {
			highestCard = ((Card) sortedHand.get(4)).getRank();
			secondHighestCard = ((Card) sortedHand.get(3)).getRank();
			return true;
		}
		return false;
	}

	/*
	 * method to decide highest value of the hand
	 * 
	 * @param: list of sorted cards in the hand
	 * 
	 * @return: highest strength of the hand
	 */
	public Strengths handStrength(List<Card> sortedHand) {

		if (hasPair(sortedHand)) {
			if (isTwoPair(sortedHand)) {
				return Strengths.TWO_PAIRS;
			} else if (isTrips(sortedHand)) {
				return Strengths.THREE_OF_A_KIND;
			} else if (isBoat(sortedHand)) {
				return Strengths.FULL_HOUSE;
			} else if (isQuads(sortedHand)) {
				return Strengths.FOUR_OF_A_KIND;
			} else {
				return Strengths.PAIR;
			}
		} else {
			if (isStraight(sortedHand)) {
				if (isFlush(sortedHand)) {
					if (highestCard == Ranks.ACE) {
						return Strengths.ROYAL_FLUSH;
					}
					return Strengths.STRAIGHT_FLUSH;
				} else {
					return Strengths.STRAIGHT;
				}
			} else if (isFlush(sortedHand)) {
				return Strengths.FLUSH;
			} else {
				hasHighCard(sortedHand);
				return Strengths.HIGH_CARD;
			}
		}
	}

	@Override
	public String toString() {
		return "Hand " + myHand;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((highestCard == null) ? 0 : highestCard.hashCode());
		result = prime * result + ((myHand == null) ? 0 : myHand.hashCode());
		result = prime * result + ((secondHighestCard == null) ? 0 : secondHighestCard.hashCode());
		return result;
	}

	/*
	 * overriding Object's equals method to decide if two hands are the same
	 * 
	 * @param: object - a hand of cards this time
	 * 
	 * @return: true if all cards are the same in each hand. False otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hand other = (Hand) obj;
		if (highestCard != other.highestCard)
			return false;
		if (myHand == null) {
			if (other.myHand != null)
				return false;
		} else if (!myHand.equals(other.myHand))
			return false;
		if (secondHighestCard != other.secondHighestCard)
			return false;
		return true;
	}
}
