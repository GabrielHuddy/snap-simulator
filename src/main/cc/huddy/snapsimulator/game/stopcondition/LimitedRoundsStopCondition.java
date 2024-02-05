package cc.huddy.snapsimulator.game.stopcondition;

import cc.huddy.snapsimulator.game.state.GameState;

/**
 * Stop condition that is met when the round limit is exceeded
 */
public class LimitedRoundsStopCondition implements StopCondition {
    private final int roundsLimit;

    /**
     * Constructor.
     * @param roundsLimit number of rounds to limit to
     */
    public LimitedRoundsStopCondition(int roundsLimit) {
        this.roundsLimit = roundsLimit;
    }

    @Override
    public boolean isMet(GameState gameState) {
        return gameState.roundNumber() > roundsLimit;
    }
}
