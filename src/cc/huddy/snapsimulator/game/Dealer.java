package cc.huddy.snapsimulator.game;

import cc.huddy.snapsimulator.deck.*;
import cc.huddy.snapsimulator.options.BadConfigurationException;
import cc.huddy.snapsimulator.options.DeckTypeOption;
import cc.huddy.snapsimulator.options.GameOptions;

/**
 * A simulated dealer that sets up a game of snap.
 */
public class Dealer {
    private final GameOptions gameOptions;
    private final DeckFactory deckFactory;
    private final Deck pool;

    /**
     * Constructor for injecting a custom deck factory, and a custom Deck object to use as the pool of cards.
     * @param gameOptions game options
     * @param deckFactory factory for constructing deck
     */
    public Dealer(GameOptions gameOptions, DeckFactory deckFactory, Deck pool) {
        this.gameOptions = gameOptions;
        this.deckFactory = deckFactory;
        this.pool = pool;
    }

    /**
     * Constructor for using a deck factory based on options
     * @param gameOptions game options
     */
    public Dealer (GameOptions gameOptions) throws BadConfigurationException {
        this.gameOptions = gameOptions;
        this.pool = new Deck();

        switch (gameOptions.deckType()) {
            case DeckTypeOption.FRENCH_SUITED -> this.deckFactory = new FrenchSuitedDeckFactory();
            case DeckTypeOption.FRENCH_SUITED_JOKERS -> this.deckFactory = new FrenchSuitedJokersDeckFactory();
            case DeckTypeOption.PIQUET_PACK -> this.deckFactory = new PiquetDeckFactory();
            case DeckTypeOption.PIQUET_PACK_JOKERS -> this.deckFactory = new PiquetJokersDeckFactory();
            default -> throw new BadConfigurationException("Invalid deck type");
        }
    }

    /**
     * Given players, clear their stacks and distribute cards for a new game of snap
     * @param players players of the round
     */
    public void deal(Player[] players) {
        pool.clear(); // Ensure game state is clear
        for (int i = 0; i < gameOptions.numberOfDecks(); i++) {
            pool.addAll(deckFactory.get());
        }
        pool.shuffle();

        Deck[] decks = pool.cut(players.length);

        for (int i = 0; i < players.length; i++) {
            players[i].discardPile();
            players[i].addToPile(decks[i]);
        }
    }
}
