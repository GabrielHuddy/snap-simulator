package cc.huddy.snapsimulator;

import cc.huddy.snapsimulator.options.*;
import org.apache.commons.cli.*;

/**
 * Helper class to parse command line arguments
 */
public class ArgsParser {
    private final GameOptions defaults = new GameOptions(
            2,
            1,
            DeckTypeOption.FRENCH_SUITED,
            ComparatorTypeOption.RANK,
            StopConditionOption.LIMITED_ROUNDS,
            1,
            FirstPlayerOption.AFTER_LAST_WINNER
    );

    private static final String OPT_PLAYERS = "players";
    private static final String OPT_DECKS = "decks";
    private static final String OPT_JOKERS = "jokers";
    private static final String OPT_FRENCH_SUITED = "french-suited";
    private static final String OPT_PIQUET_PACK = "piquet-pack";
    private static final String OPT_COMPARE_BY_RANK = "compare-by-rank";
    private static final String OPT_COMPARE_BY_SUIT = "compare-by-suit";
    private static final String OPT_COMPARE_BY_COLOR = "compare-by-color";
    private static final String OPT_COMPARE_BY_SUIT_AND_RANK = "compare-by-suit-and-rank";
    private static final String OPT_COMPARE_BY_COLOR_AND_RANK = "compare-by-color-and-rank";
    private static final String OPT_COMPARE_BY_BLACKJACK_VALUE = "compare-by-blackjack-value";
    private static final String OPT_STOP_AFTER_ROUNDS = "stop-after-rounds";
    private static final String OPT_STOP_ON_CARD_EXHAUSTION = "stop-on-card-exhaustion";
    private static final String OPT_PLAYER_1_GOES_FIRST = "player-1-goes-first";
    private static final String OPT_LAST_WINNER_GOES_FIRST = "last-winner-goes-first";
    private static final String OPT_NEXT_PLAYER_GOES_FIRST = "next-player-goes-first";
    private static final String OPT_HELP = "help";

    private boolean help = false;

    /**
     * Parse the given command line arguments to configure game options
     * @param args CLI arguments
     * @return configured game options
     */
    public GameOptions parse(String[] args) {
        Options options = definitionStage();

        GameOptions gameOptions = null;
        try {
            CommandLine commandLine = parsingStage(options, args);
            gameOptions = interrogationStage(commandLine);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            printHelp(options);
            System.exit(1);
        }

        if (help) {
            printHelp(options);
            System.exit(0);
        }

        return gameOptions;
    }

    private Options definitionStage() {
        Options options = new Options();

        // Help
        options.addOption(
                Option.builder("h")
                        .longOpt(OPT_HELP)
                        .build()
        );

        // Number of players
        options.addOption(
                Option.builder("p")
                        .longOpt(OPT_PLAYERS)
                        .desc("number of players (default: 2)")
                        .hasArg()
                        .build()
        );

        // Number of decks
        options.addOption(
                Option.builder("d")
                        .longOpt(OPT_DECKS)
                        .hasArg()
                        .desc("number of decks to use (default: 1)")
                        .build()
        );

        // Jokers
        options.addOption(
                Option.builder("j")
                        .longOpt(OPT_JOKERS)
                        .desc("use a deck of cards with jokers (default: off)")
                        .build()
        );

        // Deck type
        OptionGroup deckTypeOptions = new OptionGroup();

        // Deck type - french suited
        deckTypeOptions.addOption(
                Option.builder()
                        .longOpt(OPT_FRENCH_SUITED)
                        .desc("use a standard 52-card french-suited deck (default)")
                        .build()
        );

        // Deck type - piquet-pack
        deckTypeOptions.addOption(
                Option.builder()
                        .longOpt(OPT_PIQUET_PACK)
                        .desc("use a 32-card piquet pack (french-suited deck with cards of rank 2-6 removed)")
                        .build()
        );

        options.addOptionGroup(deckTypeOptions);

        // Comparator type
        OptionGroup comparatorTypeOptions = new OptionGroup();

        // Comparator type - Rank
        comparatorTypeOptions.addOption(
                Option.builder()
                        .longOpt(OPT_COMPARE_BY_RANK)
                        .desc("cards must have the same rank to snap (default)")
                        .build()
        );

        // Comparator type - Suit
        comparatorTypeOptions.addOption(
                Option.builder()
                        .longOpt(OPT_COMPARE_BY_SUIT)
                        .desc("cards must have the same suit to snap")
                        .build()
        );

        // Comparator type - Suit and rank
        comparatorTypeOptions.addOption(
                Option.builder()
                        .longOpt(OPT_COMPARE_BY_SUIT_AND_RANK)
                        .desc("cards must have the same suit AND rank to snap")
                        .build()
        );

        // Comparator type - Color
        comparatorTypeOptions.addOption(
                Option.builder()
                        .longOpt(OPT_COMPARE_BY_COLOR)
                        .desc("cards must have the same color to snap")
                        .build()
        );

        // Comparator type - Color and rank
        comparatorTypeOptions.addOption(
                Option.builder()
                        .longOpt(OPT_COMPARE_BY_COLOR_AND_RANK)
                        .desc("cards must have the same color AND rank to snap")
                        .build()
        );

        // Comparator type - Blackjack value
        comparatorTypeOptions.addOption(
                Option.builder()
                        .longOpt(OPT_COMPARE_BY_BLACKJACK_VALUE)
                        .desc("cards must have the same value in blackjack to snap")
                        .build()
        );

        options.addOptionGroup(comparatorTypeOptions);

        // Stop condition
        OptionGroup stopConditionOptions = new OptionGroup();

        // Stop condition - Limited Rounds
        stopConditionOptions.addOption(
                Option.builder("r")
                        .longOpt(OPT_STOP_AFTER_ROUNDS)
                        .desc("the game will stop after the given number of rounds (default: 1)")
                        .hasArg()
                        .build()
        );

        // Stop condition - Cards Exhausted
        stopConditionOptions.addOption(
                Option.builder()
                        .longOpt(OPT_STOP_ON_CARD_EXHAUSTION)
                        .desc("the game will stop after all cards have been exhausted")
                        .build()
        );

        options.addOptionGroup(stopConditionOptions);

        // First Player
        OptionGroup firstPlayerOptions = new OptionGroup();

        // First Player - Player 1
        firstPlayerOptions.addOption(
                Option.builder()
                        .longOpt(OPT_PLAYER_1_GOES_FIRST)
                        .desc("player 1 goes first each round")
                        .build()
        );

        // First Player - Last winner
        firstPlayerOptions.addOption(
                Option.builder()
                        .longOpt(OPT_LAST_WINNER_GOES_FIRST)
                        .desc("the winner of the last round goes first each round")
                        .build()
        );

        // First Player - After last winner
        firstPlayerOptions.addOption(
                Option.builder()
                        .longOpt(OPT_NEXT_PLAYER_GOES_FIRST)
                        .desc("the player after the winner of the last round goes first each round (default)")
                        .build()
        );

        options.addOptionGroup(firstPlayerOptions);

        return options;
    }

    private CommandLine parsingStage(Options options, String[] args) throws ParseException {
        CommandLineParser parser = new DefaultParser();
        return (parser.parse(options, args));
    }

    private GameOptions interrogationStage(CommandLine commandLine) {
        // Help
        help = commandLine.hasOption(OPT_HELP);

        // Number of players
        int numberOfPlayers = commandLine.hasOption(OPT_PLAYERS)
                ? Integer.parseInt(commandLine.getOptionValue(OPT_PLAYERS))
                : defaults.numberOfPlayers();

        // Number of decks
        int numberOfDecks = commandLine.hasOption(OPT_DECKS)
                ? Integer.parseInt(commandLine.getOptionValue(OPT_DECKS))
                : defaults.numberOfDecks();

        // Deck type
        DeckTypeOption deckType = defaults.deckType();
        if (commandLine.hasOption(OPT_FRENCH_SUITED)) {
            deckType = DeckTypeOption.FRENCH_SUITED;
        }
        if (commandLine.hasOption(OPT_PIQUET_PACK)) {
            deckType = DeckTypeOption.PIQUET_PACK;
        }

        // Jokers
        if (commandLine.hasOption(OPT_JOKERS)) {
            switch (deckType) {
                case DeckTypeOption.FRENCH_SUITED -> deckType = DeckTypeOption.FRENCH_SUITED_JOKERS;
                case DeckTypeOption.PIQUET_PACK -> deckType = DeckTypeOption.PIQUET_PACK_JOKERS;
            }
        }

        // Comparator type
        ComparatorTypeOption comparatorType = defaults.comparatorType();
        if (commandLine.hasOption(OPT_COMPARE_BY_RANK)) {
            comparatorType = ComparatorTypeOption.RANK;
        }
        if (commandLine.hasOption(OPT_COMPARE_BY_SUIT)) {
            comparatorType = ComparatorTypeOption.SUIT;
        }
        if (commandLine.hasOption(OPT_COMPARE_BY_COLOR)) {
            comparatorType = ComparatorTypeOption.COLOR;
        }
        if (commandLine.hasOption(OPT_COMPARE_BY_SUIT_AND_RANK)) {
            comparatorType = ComparatorTypeOption.SUIT_AND_RANK;
        }
        if (commandLine.hasOption(OPT_COMPARE_BY_COLOR_AND_RANK)) {
            comparatorType = ComparatorTypeOption.COLOR_AND_RANK;
        }
        if (commandLine.hasOption(OPT_COMPARE_BY_BLACKJACK_VALUE)) {
            comparatorType = ComparatorTypeOption.COLOR_AND_RANK;
        }

        // Stop condition
        StopConditionOption stopCondition = defaults.stopCondition();
        if (commandLine.hasOption(OPT_STOP_AFTER_ROUNDS)) {
            stopCondition = StopConditionOption.LIMITED_ROUNDS;
        }
        if (commandLine.hasOption(OPT_STOP_ON_CARD_EXHAUSTION)) {
            stopCondition = StopConditionOption.CARDS_EXHAUSTED;
        }

        // Limited rounds
        int roundLimit = commandLine.hasOption(OPT_STOP_AFTER_ROUNDS)
                ? Integer.parseInt(commandLine.getOptionValue(OPT_STOP_AFTER_ROUNDS))
                : defaults.roundLimit();

        // First player
        FirstPlayerOption firstPlayer = defaults.firstPlayer();
        if (commandLine.hasOption(OPT_PLAYER_1_GOES_FIRST)) {
            firstPlayer = FirstPlayerOption.PLAYER_ONE;
        }
        if (commandLine.hasOption(OPT_LAST_WINNER_GOES_FIRST)) {
            firstPlayer = FirstPlayerOption.LAST_WINNER;
        }
        if (commandLine.hasOption(OPT_NEXT_PLAYER_GOES_FIRST)) {
            firstPlayer = FirstPlayerOption.AFTER_LAST_WINNER;
        }

        return new GameOptions(
                numberOfPlayers,
                numberOfDecks,
                deckType,
                comparatorType,
                stopCondition,
                roundLimit,
                firstPlayer
        );
    }

    private void printHelp(Options options) {
        String cmdLineSyntax = "snap-simulator";
        String header = "Simulate a game of snap";
        String footer = "Authored by Gabriel Huddy";

        HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.printHelp(cmdLineSyntax, header, options, footer);
    }
}
