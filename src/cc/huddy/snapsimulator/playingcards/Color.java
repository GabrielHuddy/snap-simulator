package cc.huddy.snapsimulator.playingcards;

/**
 * Enum representing the possible colors of a playing card
 */
public enum Color {
    RED("red"),
    BLACK("black");

    private final String name;

    Color(String name) {
        this.name = name;
    }

    /**
     * Get the lowercase name of this color.
     * @return the name of the color
     */
    public String getName() {
        return name;
    }
}
