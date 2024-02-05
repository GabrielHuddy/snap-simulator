package cc.huddy.snapsimulator.game.state;

import cc.huddy.snapsimulator.game.Player;
import cc.huddy.snapsimulator.options.GameOptions;

/**
 * Factory that creates a fresh game state with a given number of players
 */
public class FreshGameStateFactory implements GameStateFactory {
    private final GameOptions gameOptions;

    /**
     * Constructor.
     * @param gameOptions game options
     */
    public FreshGameStateFactory(GameOptions gameOptions) {
        this.gameOptions = gameOptions;
    }

    @Override
    public GameState get() {
        Player[] players = new Player[gameOptions.numberOfPlayers()];
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player();
        }

        int roundNumber = 1;
        int lastWinner = -1;

        return new GameState(players, roundNumber, -1);
    }
}
