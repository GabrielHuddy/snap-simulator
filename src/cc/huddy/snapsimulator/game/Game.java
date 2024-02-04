package cc.huddy.snapsimulator.game;

import cc.huddy.snapsimulator.game.state.FreshGameStateFactory;
import cc.huddy.snapsimulator.game.state.GameState;
import cc.huddy.snapsimulator.game.state.GameStateFactory;
import cc.huddy.snapsimulator.game.stopcondition.StopCondition;
import cc.huddy.snapsimulator.game.stopcondition.StopConditionFactory;
import cc.huddy.snapsimulator.options.BadConfigurationException;
import cc.huddy.snapsimulator.options.GameOptions;

import java.util.List;

/**
 * Simulator for a game of snap
 */
public class Game {
    private final GameOptions gameOptions;
    private final Dealer dealer;
    private final Referee referee;
    private final Round round;
    private final GameStateFactory gameStateFactory;
    private final StopCondition stopCondition;
    private final StopCondition cardsExhaustedStopCondition;
    private final Visualizer visualizer;

    /**
     * Constructor with dependency injections.
     * @param gameOptions game options
     * @param dealer dealer simulator to use
     * @param round round simulator to use
     * @param gameStateFactory player factory to use
     * @param visualizer visualizer to use
     */
    public Game(
            GameOptions gameOptions,
            Dealer dealer,
            Referee referee,
            Round round,
            GameStateFactory gameStateFactory,
            StopConditionFactory stopConditionFactory,
            Visualizer visualizer)
            throws BadConfigurationException {
        this.gameOptions = gameOptions;
        this.dealer = dealer;
        this.referee = referee;
        this.round = round;
        this.gameStateFactory = gameStateFactory;
        this.stopCondition = stopConditionFactory.get();
        this.cardsExhaustedStopCondition = stopConditionFactory.getCardsExhausted();
        this.visualizer = visualizer;
    }

    /**
     * Basic constructor.
     * @param gameOptions game options
     * @throws BadConfigurationException propagated from dependency
     */
    public Game(GameOptions gameOptions) throws BadConfigurationException {
        this(
                gameOptions,
                new Dealer(gameOptions),
                new Referee(),
                new Round(gameOptions),
                new FreshGameStateFactory(gameOptions),
                new StopConditionFactory(gameOptions),
                new Visualizer());
    }

    /**
     * Play a full game of snap.
     * @throws InterruptedException propagated from dependencies
     * @throws BadConfigurationException propagated from dependencies
     */
    public void play() throws InterruptedException, BadConfigurationException {
        GameState gameState = gameStateFactory.get();

        dealer.deal(gameState.players());

        while (!stopConditionMet(gameState)) {
            int roundWinner = round.play(gameState);
            gameState = new GameState(
                    gameState.players(),
                    gameState.roundNumber() + 1,
                    roundWinner);
        }

        visualizer.printStopConditionMet(gameOptions);

        List<Integer> winners = referee.determineVictors(gameState);
        visualizer.printWinners(winners);
    }

    private boolean stopConditionMet(GameState gameState) {
        // Card exhaustion must be checked for, even if not set in options, because the game cannot continue playing
        // without cards.
        return stopCondition.isMet(gameState) || cardsExhaustedStopCondition.isMet(gameState);
    }
}
