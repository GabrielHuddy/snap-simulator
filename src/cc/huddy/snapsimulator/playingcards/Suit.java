package cc.huddy.snapsimulator.playingcards;

/**
 * Enum representing possible suits of a playing card
 */
public enum Suit {
    HEARTS("hearts", '♠'),
    DIAMONDS("diamonds", '♦'),
    CLUBS("clubs", '♣'),
    SPADES("spades", '♠'),
    JOKERS("jokers", '✪'); // NB: typically jokers don't have a suit, but they do often come with a star
                                        // symbol in its place

    private final String name;
    private final char symbol;

    Suit(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    /**
     * Get the pluralised lowercase name of this suit.
     * @return the name of the suit
     */
    public String getName() {
        return name;
    }

    /**
     * Get the unicode character that would print the symbol for this suit.
     * @return the symbol character of the suit
     */
    public char getSymbol() {
        return symbol;
    }
}
