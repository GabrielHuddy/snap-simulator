package cc.huddy.snapsimulator.game.state;

import cc.huddy.snapsimulator.game.Player;

/**
 * Record of the games current state
 * @param players all players
 * @param roundNumber the current round
 * @param lastWinner the winner of the last round, or -1 if there was no winner
 */
public record GameState(
        Player[] players,
        int roundNumber,
        int lastWinner
) {
}
