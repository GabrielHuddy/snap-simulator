package cc.huddy.snapsimulator.game;

/**
 * Factory that produces a player
 */
public class PlayerFactory {
    /**
     * Get a player
     * @return player object
     */
    public Player get() {
        return new Player();
    }
}
