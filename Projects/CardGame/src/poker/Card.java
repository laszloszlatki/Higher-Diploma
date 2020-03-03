package poker;
/*
 * @author: Laszlo Szlatki
 * @date: 27/Feb/2020
 * */

/*
 * An object of class card represents one of the 52 cards in a
 * standard deck of playing cards.  Each card has a suit and
 * a value.
*/

// card has to implement Comparable to allow sorting later
public class Card implements Comparable<Card> {

	/*
	 * Separation of Concerns
	 * 
	 * I would like to see the association between a Card and an Image in a
	 * different place in your program. In general when you build GUI applications,
	 * you want to separate the things the User sees from the representation of data
	 * in your program. With you current design, you make it challenging to build a
	 * flexible UI. What if 3 months from now you decide you've got a good game, and
	 * now you want to support multiple "skins" on your cards, so that users can
	 * customize the UI to their liking. Now you've got to come change the
	 * implementation of the Card class - not something you should have to do to
	 * make a change to the way the UI looks.
	 */
	private static final String IMAGE_FOLDER = "image";
	private static final String IMAGE_FORMAT = ".png";
	private static final String BACK_IMAGE = ("image/back_image.png");

	private Image cardImage;
	private Image backImage;

	private Suits suit;
	Ranks rank;

	public Card(Suits suit, Ranks rank) {
		this.suit = suit;
		this.rank = rank;

		String location = generateImageLocation();

		try {
			cardImage = new Image(location);

		} catch (Exception ex) {
			System.out.println(String.format("cannot load image from: (%s)", location));
			cardImage = null;
		}

		try {
			backImage = new Image(BACK_IMAGE);
		} catch (Exception ex) {
			System.out.println(String.format("cannot load image from: (%s)", BACK_IMAGE));
			backImage = null;
		}
	}

	public Suits getSuit() {
		return suit;
	}

	public Ranks getRank() {
		return rank;
	}

	public Image getCardImage() {
		return cardImage;
	}

	public Image getBackImage() {
		return backImage;
	}

	private String generateImageLocation() {

		StringBuilder sb = new StringBuilder();

		sb.append(IMAGE_FOLDER).append("/").append(suit.toString()).append("_").append(rank.toString())
				.append(IMAGE_FORMAT);

		return sb.toString().toLowerCase();

	}

	// compareTo from interface helps ordering cards
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

	// good practice to override Object's toString()
	@Override
	public String toString() {
		return (rank + " of " + suit);
	}

	// good practice to override Object's equals()
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

	// ???????
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rank == null) ? 0 : rank.hashCode());
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		return result;
	}
}