package cc.huddy.snapsimulator.game.cardcomparator;

import cc.huddy.snapsimulator.playingcards.Card;

/**
 * Comparator for comparing to cards and determining whether they are a snap
 */
public abstract class CardComparator {
    /**
     * Compares two cards to see if they match
     * @param card1 first card to compare
     * @param card2 second card to compare
     * @return true if the cards match
     */
    public abstract boolean compare(Card card1, Card card2);

    /**
     * Compares all possible pairings of cards to see if there are any matches
     * @param cards cards to compare
     * @return true if there is at least one match
     */
    public boolean isAnyMatching(Card... cards) {
        for (int a = 0; a < cards.length; a++) {
            for (int b = a + 1; b < cards.length; b++) {
                if (compare(cards[a], cards[b])) {
                    return true;
                }
            }
        }

        return false;
    }
}
