package cc.huddy.snapsimulator.game.cardcomparator;

import cc.huddy.snapsimulator.playingcards.Card;

/**
 * Comparator that matches cards based on rank
 */
public class SuitCardComparator extends CardComparator {
    @Override
    public boolean compare(Card card1, Card card2) {
        if (card1 == null || card2 == null) {
            return false;
        }

        return card1.suit() == card2.suit();
    }
}
