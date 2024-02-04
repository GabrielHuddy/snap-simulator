package cc.huddy.snapsimulator.game;

import cc.huddy.snapsimulator.game.cardcomparator.CardComparator;
import cc.huddy.snapsimulator.game.cardcomparator.CardComparatorFactory;
import cc.huddy.snapsimulator.game.state.GameState;
import cc.huddy.snapsimulator.game.stopcondition.CardsExhaustedStopCondition;
import cc.huddy.snapsimulator.game.stopcondition.StopCondition;
import cc.huddy.snapsimulator.options.BadConfigurationException;
import cc.huddy.snapsimulator.options.GameOptions;
import cc.huddy.snapsimulator.playingcards.Card;
import cc.huddy.snapsimulator.deck.Deck;

import java.util.Arrays;
import java.util.List;

/**
 * Simulator for a round of snap
 */
public class Round {
    private final GameOptions gameOptions;
    private final Visualizer visualizer;
    private final CardComparator cardComparator;
    private final StopCondition cardsExhaustedStopCondition;

    /**
     * Constructor with custom dependency injections
     * @param gameOptions game options
     * @param visualizer visualizer
     * @param cardComparatorFactory card comparator
     * @param cardsExhaustedStopCondition checker for card exhaustion
     * @throws BadConfigurationException propagated from dependencies
     */
    public Round(
            GameOptions gameOptions,
            Visualizer visualizer,
            CardComparatorFactory cardComparatorFactory,
            StopCondition cardsExhaustedStopCondition) throws BadConfigurationException {
        this.gameOptions = gameOptions;
        this.visualizer = visualizer;
        this.cardComparator = cardComparatorFactory.get(gameOptions.comparatorType());
        this.cardsExhaustedStopCondition = cardsExhaustedStopCondition;
    }

    /**
     * Basic constructor using default dependencies
     * @param gameOptions game options
     * @throws BadConfigurationException propagated from dependencies
     */
    public Round(GameOptions gameOptions) throws BadConfigurationException {
        this(gameOptions, new Visualizer(), new CardComparatorFactory(), new CardsExhaustedStopCondition());
    }

    /**
     * Play a round of snap
     * @param gameState the current game state
     * @return the winner of this round by index, or -1 if there was no winner
     */
    public int play(GameState gameState) throws InterruptedException, BadConfigurationException {
        Player[] players = gameState.players();
        int numberOfPlayers = players.length;
        int roundNumber = gameState.roundNumber();

        visualizer.printRoundHeader(roundNumber);

        Card[] cardsOnTop = new Card[numberOfPlayers];

        int first = firstPlayer(gameState);

        for(int i = first; true; i = (i + 1) % numberOfPlayers) {
            // Card exhaustion would prevent gameplay at this point regardless of the stop condition set in options
            if (cardsExhaustedStopCondition.isMet(gameState)) {
                visualizer.printRoundOutOfCards();
                return -1;
            }

            // TODO: this could be separated into a Turn class in line with SRP
            cardsOnTop[i] = players[i].play();
            visualizer.printCardReveal(i, cardsOnTop[i]);

            if (cardComparator.isAnyMatching(cardsOnTop)) {
                // Snap!
                int winner = simulateReaction(players);
                visualizer.printSnap(winner);

                int cardsWon = winnerTake(players, winner);
                visualizer.printTake(winner, cardsWon);

                return i;
            } else {
                // Wait for a bit then continue to next player
                Thread.sleep(2000);
            }
        }
    }

    /**
     * Makes the winner take cards from all revealed stacks.
     * @param players the players
     * @param winner the player number of the winner
     * @return the number of cards taken
     */
    private int winnerTake(Player[] players, int winner) {
        int count = 0;

        for (Player player : players) {
            Deck stack = player.getStack();
            count += stack.size();
            players[winner].take(stack);
        }

        return count;
    }

    /**
     * Simulate a competition to react first to something, sleeping for the duration then returning the winner.
     * @param players the players
     * @return number of player who reacts first
     */
    private int simulateReaction(Player[] players) throws InterruptedException {
        List<Float> reactionTimes = Arrays.stream(players)
                .map(Player::react)
                .toList();

        int winner = 0;
        for (int i = 1; i < reactionTimes.size(); i++) {
            if (reactionTimes.get(i) < reactionTimes.get(winner)) {
                winner = i;
            }
        }

        long millisecondsToSleep = Math.round(reactionTimes.get(winner) * 1000);

        Thread.sleep(millisecondsToSleep);

        return winner;
    }

    /**
     * Determine who should go first in the round
     * @param gameState current game state
     * @return player who will go first (zero-base)
     * @throws BadConfigurationException thrown if options are incorrectly configured
     */
    private int firstPlayer(GameState gameState) throws BadConfigurationException {
        int numberOfPlayers = gameState.players().length;
        switch (gameOptions.firstPlayer()) {
            case PLAYER_ONE -> { return 0; }
            case LAST_WINNER -> { return gameState.lastWinner() % numberOfPlayers; }
            case AFTER_LAST_WINNER -> { return (gameState.lastWinner() + 1) % numberOfPlayers; }
            default -> throw new BadConfigurationException("Invalid setting for first player");
        }
    }
}
