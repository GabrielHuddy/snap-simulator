package cc.huddy.snapsimulator.game.cardcomparator;

import cc.huddy.snapsimulator.options.BadConfigurationException;
import cc.huddy.snapsimulator.options.ComparatorTypeOption;

/**
 * Factory for card comparators
 */
public class CardComparatorFactory {
    /**
     * Get the specified type of comparator
     * @param option the type of comparator to get
     * @return the comparator
     * @throws Exception throws if option was invalid
     */
    public CardComparator get(ComparatorTypeOption option) throws BadConfigurationException{
        switch (option) {
            case ComparatorTypeOption.RANK -> { return new RankCardComparator(); }
            case SUIT -> { return new SuitCardComparator(); }
            case SUIT_AND_RANK -> { return new SuitAndRankCardComparator(); }
            case COLOR -> { return new ColorCardComparator(); }
            case COLOR_AND_RANK -> { return new ColorAndRankCardComparator(); }
            case ComparatorTypeOption.BLACKJACK_VALUE -> { return new BlackjackCardComparator();}
            default -> throw new BadConfigurationException("Unknown comparator type option");
        }
    }
}
