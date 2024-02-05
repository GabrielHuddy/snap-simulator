package cc.huddy.snapsimulator.game.state;

/**
 * Factory for creating a game state
 */
public interface GameStateFactory {
    /**
     * Get a game state
     * @return the game state
     */
    public GameState get();
}
