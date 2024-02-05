package cc.huddy.snapsimulator.game.cardcomparator;

import cc.huddy.snapsimulator.playingcards.Card;

/**
 * Comparator that matches cards by their blackjack value
 */
public class BlackjackCardComparator extends CardComparator {
    @Override
    public boolean compare(Card card1, Card card2) {
        if (card1 == null || card2 == null) {
            return false;
        }

        return card1.rank().getBlackjackMaxValue() == card2.rank().getBlackjackMaxValue();
    }
}
