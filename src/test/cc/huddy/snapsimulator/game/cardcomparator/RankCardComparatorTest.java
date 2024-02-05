package cc.huddy.snapsimulator.game.cardcomparator;

import cc.huddy.snapsimulator.playingcards.Card;
import cc.huddy.snapsimulator.playingcards.Color;
import cc.huddy.snapsimulator.playingcards.Rank;
import cc.huddy.snapsimulator.playingcards.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RankCardComparatorTest {
    private RankCardComparator target;

    @BeforeEach
    public void setup() {
        // Arrange
        target = new RankCardComparator();
    }

    @ParameterizedTest
    @MethodSource("getEqualCards")
    public void givenSameRank_whenCompare_thenTrue(Card card1, Card card2) {
        // Act
        boolean result = target.compare(card1, card2);
        boolean reversedResult = target.compare(card2, card1);

        // Assert
        assertTrue(result);
        assertTrue(reversedResult);
    }

    @ParameterizedTest
    @MethodSource("getUnequalCards")
    public void givenDifferentRank_whenCompare_thenFalse(Card card1, Card card2) {
        // Act
        boolean result = target.compare(card1, card2);
        boolean reversedResult = target.compare(card2, card1);

        // Assert
        assertFalse(result);
        assertFalse(reversedResult);
    }

    @ParameterizedTest
    @MethodSource("getNullCombinations")
    public void givenNullInput_whenCompare_thenFalse(Card card1, Card card2) {
        // Act
        boolean result = target.compare(card1, card2);

        // Assert
        assertFalse(result);
    }

    // TODO: isAnyMatching() tests

    private static Stream<Arguments> getEqualCards() {
        return Stream.of(
                Arguments.of(
                        new Card(Color.RED, Suit.HEARTS, Rank.ACE),
                        new Card(Color.BLACK, Suit.SPADES, Rank.ACE)
                ),
                Arguments.of(
                        new Card(Color.RED, Suit.HEARTS, Rank.TWO),
                        new Card(Color.RED, Suit.HEARTS, Rank.TWO)
                ),
                Arguments.of(
                        new Card(Color.RED, Suit.JOKERS, Rank.JOKER),
                        new Card(Color.BLACK, Suit.SPADES, Rank.JOKER)
                )
        );
    }

    private static Stream<Arguments> getUnequalCards() {
        return Stream.of(
                Arguments.of(
                        new Card(Color.RED, Suit.HEARTS, Rank.ACE),
                        new Card(Color.RED, Suit.HEARTS, Rank.TWO)
                ),
                Arguments.of(
                        new Card(Color.RED, Suit.HEARTS, Rank.KING),
                        new Card(Color.BLACK, Suit.SPADES, Rank.QUEEN)
                ),
                Arguments.of(
                        new Card(Color.RED, Suit.JOKERS, Rank.JOKER),
                        new Card(Color.RED, Suit.HEARTS, Rank.JACK)
                )
        );
    }

    private static Stream<Arguments> getNullCombinations() {
        return Stream.of(
                Arguments.of(
                        new Card(Color.RED, Suit.HEARTS, Rank.ACE),
                        null
                ),
                Arguments.of(
                        null,
                        new Card(Color.RED, Suit.HEARTS, Rank.ACE)
                ),
                Arguments.of(
                        null,
                        null
                )
        );
    }
}