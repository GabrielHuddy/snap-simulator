package cc.huddy.snapsimulator.playingcards;

/**
 * Enum representing the possible ranks of a playing card.
 */
public enum Rank {
    ACE("ace", "A", 11),
    TWO("two", "2", 2),
    THREE("three", "3", 3),
    FOUR("four", "4", 4),
    FIVE("five", "5", 5),
    SIX("six", "6", 6),
    SEVEN("seven", "7", 7),
    EIGHT("eight", "8", 8),
    NINE("nine", "9", 9),
    TEN("ten", "10", 10),
    JACK("jack", "J", 10),
    QUEEN("queen", "Q", 10),
    KING("king", "K", 10),
    JOKER("joker", "🂿", -1);

    private final String name;
    private final String symbol;
    private final int blackjackMaxValue;

    Rank(String name, String symbol, int blackjackValue) {
        this.name = name;
        this.symbol = symbol;
        this.blackjackMaxValue = blackjackValue;
    }

    /**
     * Get the lowercase name of this rank.
     * @return the name of the rank
     */
    public String getName() {
        return name;
    }

    /**
     * Get a symbolic representation of this rank as a short string of unicode characters.
     * @return the rank's symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Get this rank's maximum value in blackjack.
     * We use maximum value because an ace can be 1 or 11.
     * @return the rank's value
     */
    public int getBlackjackMaxValue() {
        return blackjackMaxValue;
    }
}
