## Poker game
The computer generates two hands with five cards each, then decides wich hand is the winner according to this rules:
* High card. If no combination can be made, then a player’s hand is valued at the highest single card. If two players have the same high card, then the second highest card would break the tie.
    
* One Pair. A pair is formed when you have two of any of the same ranks.
    
* Two Pairs. When more than one player has two pairs, the player with the highest pair wins.
    
* Three of a Kind. 3 of the same ranks.
  
* Straight. A straight is a five-card hand consisting of a running sequence of cards, regardless of suit. If two players have straights, the straight of the higher card wins.

* Flush. When all five cards in a hand are of the same suit, it is a flush. If two players have a flush, the person with the highest card in that suit wins.

* Full House. When a player has three-of-a-kind and a pair in the same hand, it is called a Full House.

* Four of a Kind. If you are lucky enough to have all four of a given number, then you have a very powerful hand.

* Straight Flush. Even rarer than four of a kind, a straight flush is made up of five consecutive cards, all from the same suit.
    
* Royal Flush. The best hand of them all is this famous combination, formed by a Straight Flush that runs to the Ace, making it unbeatable. Odds of being dealt this hand can be as high as 1 in 650,000 deals.

## Folder contains

```
Ranks enum,          to store all available ranks
Suits enum,          to store all available suits
Strengths enum,      to order the different hand strengths
Rules class,         to decide winner of the two hands according to rules
Card class,          to characterise a card
Deck class,          to store the entire deck (ordered or shuffled)
EmptyDeckException,  to handle empty deck
Hand class,          to store and manage the hand dealt
Game class,          to be used as the driver for the game
```
