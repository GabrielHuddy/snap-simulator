package cc.huddy.snapsimulator.game.stopcondition;

import cc.huddy.snapsimulator.game.Player;
import cc.huddy.snapsimulator.game.state.GameState;

public class CardsExhaustedStopCondition implements StopCondition {
    @Override
    public boolean isMet(GameState gameState) {
        for (Player player : gameState.players()) {
            if (player.countPile() > 0) {
                return false;
            }
        }

        return true;
    }
}
