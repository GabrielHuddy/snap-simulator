package cc.huddy.snapsimulator.deck;

import cc.huddy.snapsimulator.playingcards.Card;
import cc.huddy.snapsimulator.wrappers.ShuffleWrapper;

import java.util.Stack;

/**
 * Representation of a deck of playing cards
 */
public class Deck extends Stack<Card> {
    private final ShuffleWrapper shuffleWrapper;

    /**
     * Constructor injecting a custom wrapper for a shuffle function.
     * @param shuffleWrapper wrapper for a shuffle function
     */
    public Deck(ShuffleWrapper shuffleWrapper) {
        this.shuffleWrapper = shuffleWrapper;
    }

    /**
     * Constructor that uses the default shuffle function
     */
    public Deck() {
        this.shuffleWrapper = new ShuffleWrapper();
    }

    /**
     * Shuffle the deck.
     */
    public void shuffle() {
        shuffleWrapper.shuffle(this);
    }

    /**
     * Remove the top card of the deck. Alias for pop().
     * @return the card that was removed
     */
    public Card draw() {
        return pop();
    }

    /**
     * Splits this deck into a specified number of new roughly equally-sized decks. This deck will be left empty.
     * @param number number of decks to split this one into
     * @return array containing the new decks
     */
    public Deck[] cut(int number) {
        Deck[] decks = new Deck[number];
        for (int i = 0; i < number; i++) {
            decks[i] = new Deck();
        }

        // Iterating through all the cards is probably less performant then using subList(), but this is simpler and
        // more readable than calculating sublist sizes in advance.
        for (int i = size() - 1; i >= 0; i--) {
            decks[i % number].push(pop());
        }

        return decks;
    }

    /**
     * Splits this deck into two new roughly equally-sized decks. This deck will be left empty.
     * @return array containing the resulting decks
     */
    public Deck[] cut() {
        return (cut(2));
    }
}
