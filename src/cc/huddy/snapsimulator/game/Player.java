package cc.huddy.snapsimulator.game;

import cc.huddy.snapsimulator.playingcards.Card;
import cc.huddy.snapsimulator.deck.Deck;

import java.util.Random;

/**
 * A single player of snap.
 */
public class Player {
    private final Deck pile;
    private final Deck won;
    private final Deck stack;
    private final Random random;
    private final float minReactionTime = 0.5f;
    private final float maxReactionTime = 5.0f;

    /**
     * Constructor with custom dependencies
     * @param startingPile the starting pile of unrevealed cards
     * @param startingWon cards that the player starts as having won
     * @param startingStack cards that the player has revealed
     * @param random injection of the Random class
     */
    public Player(Deck startingPile, Deck startingWon, Deck startingStack, Random random) {
        this.pile = startingPile;
        this.won = startingWon;
        this.stack = startingStack;
        this.random = random;
    }

    /**
     * Default constructor
     */
    public Player() {
        this(new Deck(), new Deck(), new Deck(), new Random());
    }

    /**
     * Plays a card from the pile into the player's revealed stack.
     * @return the card that was played, or null if a card couldn't be played
     */
    public Card play() {
        if (pile.isEmpty()) {
            return null;
        }

        Card card = pile.pop();
        stack.push(card);
        return card;
    }

    /**
     * Takes a deck of cards and adds it to the cards the player has won. Leaves the deck taken from empty.
     * @param deck The deck of cards to take
     */
    public void take(Deck deck) {
        this.won.addAll(deck);
        deck.clear();
    }

    /**
     * Get the player's stack of revealed cards
     */
    public Deck getStack() {
        return stack;
    }

    /**
     * Counts the number of cards the player has won
     * @return the number of cards won
     */
    public int countWon() {
        return won.size();
    }

    /**
     * Adds the given cards to a player's pile that they reveal from.
     * @param deck The deck of cards to take
     */
    public void addToPile(Deck deck) {
        pile.addAll(deck);
        deck.clear();
    }

    /**
     * Counts the number of cards remaining in the pile that the player reveals from.
     * @return the number of cards in the pile
     */
    public int countPile() {
        return pile.size();
    }

    /**
     * Discards a player's entire pile.
     */
    public void discardPile() {
        pile.clear();
    }

    /**
     * Gives a random number which represents the player's time to react in seconds.
     * @return the reaction time in seconds
     */
    public float react() {
        return minReactionTime + (random.nextFloat() * (maxReactionTime - minReactionTime));
    }
}
