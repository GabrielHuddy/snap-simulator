package cc.huddy.snapsimulator.options;

/**
 * Configuration of a game of snap
 * @param numberOfPlayers number of players
 * @param numberOfDecks number of decks to pool together before splitting between the players.
 * @param deckType type of deck to use
 * @param comparatorType card comparator to use to determine snap
 * @param stopCondition stop condition for the game
 * @param roundLimit number of rounds to play for, only used if stopCondition =
 */
public record GameOptions(
    int numberOfPlayers,
    int numberOfDecks,
    DeckTypeOption deckType,
    ComparatorTypeOption comparatorType,
    StopConditionOption stopCondition,
    int roundLimit,
    FirstPlayerOption firstPlayer
) {
}
