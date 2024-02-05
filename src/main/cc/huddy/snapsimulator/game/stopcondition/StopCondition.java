package cc.huddy.snapsimulator.game.stopcondition;

import cc.huddy.snapsimulator.game.state.GameState;

/**
 * Stop condition checker for a game of snap
 */
public interface StopCondition {
    /**
     * Check if the stop condition is met
     * @param gameState the game state to check
     * @return true if the stop condition is met
     */
    public boolean isMet(GameState gameState);
}
