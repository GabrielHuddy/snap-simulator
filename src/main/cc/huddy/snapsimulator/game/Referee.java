package cc.huddy.snapsimulator.game;

import cc.huddy.snapsimulator.game.state.GameState;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper for determining who has won a game of snap based on the current game state
 */
public class Referee {
    /**
     * Determine the victor(s) based on the current game state
     * @param gameState current game state
     * @return list of victors by player index, multiple if tied
     */
    public List<Integer> determineVictors(GameState gameState) {
        Player[] players = gameState.players();

        List<Integer> winners = new ArrayList<Integer>();
        winners.add(0);
        int currentWinCount = players[0].countWon();
        for (int i = 1; i < players.length; i++) {
            if (players[i].countWon() == currentWinCount) {
                winners.add(i);
            } else if (players[i].countWon() > currentWinCount) {
                winners.clear();
                winners.add(i);
                currentWinCount = players[i].countWon();
            }
        }

        return winners;
    }
}
