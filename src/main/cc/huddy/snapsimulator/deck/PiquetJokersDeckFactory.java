package cc.huddy.snapsimulator.deck;

import cc.huddy.snapsimulator.playingcards.Card;
import cc.huddy.snapsimulator.playingcards.Color;
import cc.huddy.snapsimulator.playingcards.Rank;
import cc.huddy.snapsimulator.playingcards.Suit;

/**
 * Factory for a french-suited deck with two jokers.
 */
public class PiquetJokersDeckFactory extends FrenchSuitedDeckFactory {
    private final DeckFactory baseDeckFactory = new PiquetDeckFactory();

    @Override
    public Deck get() {
        Deck deck = baseDeckFactory.get();

        deck.push(new Card(Color.RED, Suit.JOKERS, Rank.JOKER));
        deck.push(new Card(Color.BLACK, Suit.JOKERS, Rank.JOKER));

        return deck;
    }
}
