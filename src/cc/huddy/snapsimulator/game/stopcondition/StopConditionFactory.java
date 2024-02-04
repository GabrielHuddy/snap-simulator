package cc.huddy.snapsimulator.game.stopcondition;

import cc.huddy.snapsimulator.options.BadConfigurationException;
import cc.huddy.snapsimulator.options.GameOptions;

/**
 * Factory to get one of the types of stop conditions
 */
public class StopConditionFactory {
    private final GameOptions gameOptions;

    public StopConditionFactory(GameOptions gameOptions) {
        this.gameOptions = gameOptions;
    }

    /**
     * Get a stop condition
     * @return stop condition
     * @throws BadConfigurationException thrown if options for stop condition are invalid
     */
    public StopCondition get() throws BadConfigurationException {
        switch (gameOptions.stopCondition()) {
            case LIMITED_ROUNDS -> { return new LimitedRoundsStopCondition(gameOptions.roundLimit()); }
            case CARDS_EXHAUSTED -> { return new CardsExhaustedStopCondition(); }
            default -> throw new BadConfigurationException("Invalid stop condition configuration");
        }
    }

    /**
     * Specifically gets the cards exhausted stop condition.
     * @return the stop condition
     */
    public StopCondition getCardsExhausted() {
        return new CardsExhaustedStopCondition();
    }
}
