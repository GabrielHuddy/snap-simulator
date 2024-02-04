package cc.huddy.snapsimulator.deck;

/**
 * Factory that creates a deck of cards according to some standard.
 */
public interface DeckFactory {
    /**
     * Gets a deck populated with cards.
     * @return the deck of cards
     */
    public Deck get();
}
