package cc.huddy.snapsimulator.game.cardcomparator;

import cc.huddy.snapsimulator.playingcards.Card;

/**
 * Comparator that matches cards based on rank
 */
public class ColorAndRankCardComparator extends CardComparator {
    @Override
    public boolean compare(Card card1, Card card2) {
        if (card1 == null || card2 == null) {
            return false;
        }

        return (card1.color() == card2.color()) && (card1.rank() == card2.rank());
    }
}
