package cc.huddy.snapsimulator.deck;

import cc.huddy.snapsimulator.playingcards.Card;
import cc.huddy.snapsimulator.playingcards.Color;
import cc.huddy.snapsimulator.playingcards.Rank;
import cc.huddy.snapsimulator.playingcards.Suit;

/**
 * Factory that creates a standard french-suited deck of 52 cards.
 */
public class FrenchSuitedDeckFactory implements DeckFactory {
    @Override
    public Deck get() {
        Suit[] validSuits = {Suit.HEARTS, Suit.DIAMONDS, Suit.CLUBS, Suit.SPADES};
        Rank[] validRanks = {
                Rank.ACE,
                Rank.TWO,
                Rank.THREE,
                Rank.FOUR,
                Rank.FIVE,
                Rank.SIX,
                Rank.SEVEN,
                Rank.EIGHT,
                Rank.NINE,
                Rank.TEN,
                Rank.JACK,
                Rank.QUEEN,
                Rank.KING
        };

        Deck deck = new Deck();
        for (Suit suit : validSuits) {
            Color color = suit == Suit.HEARTS || suit == Suit.DIAMONDS ? Color.RED : Color.BLACK;

            for (Rank rank : validRanks) {
                deck.push(new Card(color, suit, rank));
            }
        }

        return deck;
    }
}
