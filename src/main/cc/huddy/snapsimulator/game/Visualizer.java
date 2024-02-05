package cc.huddy.snapsimulator.game;

import cc.huddy.snapsimulator.options.GameOptions;
import cc.huddy.snapsimulator.playingcards.Card;

import java.io.PrintStream;
import java.util.List;

/**
 * CLI visualizer for various game events
 */
public class Visualizer {
    private final PrintStream out;

    /**
     * Constructor injecting a custom PrintStream
     * @param out PrintStream to print to
     */
    public Visualizer(PrintStream out) {
        this.out = out;
    }

    /**
     * Constructor for printing to the default PrintStream (System.out)
     */
    public Visualizer() {
        this(System.out);
    }

    /**
     * Prints a header denoting the current round
     * @param roundNumber the one-base number of the current round
     */
    public void printRoundHeader(int roundNumber) {
        String message = String.format("==== ROUND %d ====", roundNumber);
        out.println(message);
    }

    /**
     * Prints a line explaining the card that was revealed, or that no card was revealed
     * @param playerNumber the zero-base number of the player who just played
     * @param card the card that was revealed, or null if now card was revealed
     */
    public void printCardReveal(int playerNumber, Card card) {
        String message;
        if (card == null) {
            message = String.format("Player %d could not reveal a card because their stack was empty.", playerNumber + 1);
        } else {
            message = String.format(
                    "Player %d revealed %s%c",
                    playerNumber + 1,
                    card.rank().getSymbol(),
                    card.suit().getSymbol());
        }
        out.println(message);
    }

    /**
     * Print a line representing a player shouting snap.
     * @param playerNumber the zero-base number of the player who is shouting
     */
    public void printSnap(int playerNumber) {
        String message = String.format("Player %d: Snap!", playerNumber + 1);
        out.println(message);
    }

    /**
     * print a line explaining that a player won the round and is taking cards.
     * @param playerNumber the zero-base number of the player who is taking
     * @param cardsWon the number of cards the player takes
     */
    public void printTake(int playerNumber, int cardsWon) {
        String message = String.format(
                "Player %d wins the round! They take %d cards to their win pile.",
                playerNumber + 1,
                cardsWon);
        out.println(message);
    }

    /**
     * print a line declaring winners, if any.
     * @param winners the players who won or are tied; by index (zero-base) NOT player number (one-base)
     */
    public void printWinners(List<Integer> winners) {
        String message;

        switch (winners.size()) {
            case 0 -> message = "The game is over. There were no winners.";
            case 1 -> message = String.format("Player %d won the game!", winners.getFirst() + 1);
            case 2 -> message = String.format(
                    "The game was tied between players %d and %d.",
                    winners.get(0) + 1,
                    winners.get(1) + 1);
            case 3 -> message = String.format(
                    "The game was tied between players %d, %d, and %d.",
                    winners.get(0) + 1,
                    winners.get(1) + 1,
                    winners.get(2) + 1);
            default -> message = "The game was tied between many players.";
        }

        out.println(message);
    }

    /**
     * Prints text explaining that all players are out of cards, so the round is ending.
     */
    public void printRoundOutOfCards() {
        out.println("The round had to end early because all players are out of cards.");
    }

    public void printStopConditionMet(GameOptions gameOptions) {
        String message;
        switch (gameOptions.stopCondition()) {
            case LIMITED_ROUNDS -> { message = "The last round has finished."; }
            case CARDS_EXHAUSTED -> { message = "The game is ending"; }
            default -> throw new IllegalStateException("Unexpected value: " + gameOptions.stopCondition());
        }
    }
}
