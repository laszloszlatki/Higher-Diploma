/*
 * @author: Laszlo Szlatki
 * @date: 05/Mar/2020
 * */
package poker;

import poker.Card;
import poker.Ranks;
import poker.Suits;

/*
 * An object of class card represents one of the 52 cards in a
 * standard deck of playing cards.  Each card has a suit and
 * a value.
*/

// card has to implement Comparable to allow sorting later
public class Card implements Comparable<Card> {

	private Suits suit;
	private Ranks rank;

	/*
	 * card constructor to set suit and rank
	 * 
	 * @param: suit from the suits enum
	 * 
	 * @param: rank from the ranks enum
	 */
	public Card(Suits suit, Ranks rank) {
		this.suit = suit;
		this.rank = rank;
	}

	/*
	 * getter for suit of card
	 * 
	 * @return: suit of card
	 */
	public Suits getSuit() {
		return suit;
	}

	/*
	 * getter for rank of card
	 * 
	 * @return: rank of card
	 */
	public Ranks getRank() {
		return rank;
	}

	/*
	 * compareTo from interface helps ordering cards comparing is done as per
	 * position in the enum
	 * 
	 * @param: the card to be compared to
	 * 
	 * @return: 1 if rank or suit is greater than the one we are comparing to
	 * 
	 * @return: -1 if rank or suit is smaller than the one we are comparing to
	 * 
	 * @return: 0 if both cards are the same
	 */
	@Override
	public int compareTo(Card card) {

		if (this.rank.compareTo(card.rank) > 0) {
			return 1;
		} else if (this.rank.compareTo(card.rank) < 0) {
			return -1;
		} else {
			return this.suit.compareTo(card.suit);
		}
	}

	/*
	 * overriding Object's toString() to display card in a nice manner
	 * 
	 * @return: String representation of the card
	 */
	@Override
	public String toString() {
		return (rank + " of " + suit);
	}

	// overriding Object's equals() to compare cards
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		Card other = (Card) obj;
		return rank == other.rank && suit == other.suit;
	}
}