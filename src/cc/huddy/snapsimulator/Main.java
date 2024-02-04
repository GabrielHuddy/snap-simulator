package cc.huddy.snapsimulator;

import cc.huddy.snapsimulator.game.Game;
import cc.huddy.snapsimulator.options.*;

public class Main {
    public static void main(String[] args) throws BadConfigurationException, InterruptedException {
        GameOptions gameOptions = new ArgsParser().parse(args);
        Game game = new Game(gameOptions);
        game.play();
    }
}