package paulius.apulskis.pokerhandscomparison;

import paulius.apulskis.pokerhandscomparison.model.card.Card;
import paulius.apulskis.pokerhandscomparison.model.hand.Hand;
import paulius.apulskis.pokerhandscomparison.model.utils.CardHelpers;

import java.util.*;


public class HandComparer {

    public static final int TIE = 0;
    public static final int PLAYER_ONE_WIN = 1;
    public static final int PLAYER_TWO_WIN = 2;

    public int compareSameHands(Hand handOne, Hand handTwo) {
        var handRanking = handOne.getHandRanking();

        return switch (handRanking) {
            case HIGH_CARD -> compareHighCardHands(handOne, handTwo);
            case PAIR -> comparePairHands(handOne, handTwo);
            case TWO_PAIR -> compareTwoPairHands(handOne, handTwo);
            case THREE_OF_A_KIND -> compareThreeOfKindHands(handOne, handTwo);
            case STRAIGHT, STRAIGHT_FLUSH -> compareStraightHands(handOne, handTwo);
            case FLUSH -> compareFlushHands(handOne, handTwo);
            case FULL_HOUSE -> compareFullHouseHands(handOne, handTwo);
            case FOUR_OF_A_KIND -> compareFourOfKindHands(handOne, handTwo);
            case ROYAL_FLUSH -> TIE;
        };
    }

    private int compareFourOfKindHands(Hand handOne, Hand handTwo) {
        var groupedHandOneValues = CardHelpers.groupCardsByValue(handOne.getCards());
        var groupedHandTwoValues = CardHelpers.groupCardsByValue(handTwo.getCards());
        var handOneFourOfKindValue = groupedHandOneValues.entrySet().stream()
                .filter(e -> e.getValue() == HandRankingEvaluator.FOUR_OF_KIND)
                .findFirst().map(Map.Entry::getKey)
                .orElseThrow();
        var handTwoFourOfKindValue = groupedHandTwoValues.entrySet().stream()
                .filter(e -> e.getValue() == HandRankingEvaluator.FOUR_OF_KIND)
                .findFirst()
                .map(Map.Entry::getKey)
                .orElseThrow();

        return handOneFourOfKindValue > handTwoFourOfKindValue ? PLAYER_ONE_WIN : PLAYER_TWO_WIN;
    }

    private int compareStraightHands(Hand handOne, Hand handTwo) {
        var highestHandOneCard = handOne.getCards().stream()
                .max(Card::compareTo)
                .map(Card::getCardsValue)
                .orElseThrow();
        var highestHandTwoCard = handTwo.getCards().stream()
                .max(Card::compareTo)
                .map(Card::getCardsValue)
                .orElseThrow();

        return highestHandOneCard > highestHandTwoCard ? PLAYER_ONE_WIN : PLAYER_TWO_WIN;
    }

    private int compareThreeOfKindHands(Hand handOne, Hand handTwo) {
        var groupedHandOneValues = CardHelpers.groupCardsByValue(handOne.getCards());
        var groupedHandTwoValues = CardHelpers.groupCardsByValue(handTwo.getCards());

        var handOneThreeOfKindValue = groupedHandOneValues.entrySet().stream()
                .filter(e -> e.getValue().intValue() == HandRankingEvaluator.THREE_OF_KIND)
                .findFirst()
                .map(Map.Entry::getKey)
                .orElseThrow();
        var handTwoThreeOfKindValue = groupedHandTwoValues.entrySet().stream()
                .filter(e -> e.getValue().intValue() == HandRankingEvaluator.THREE_OF_KIND)
                .findFirst()
                .map(Map.Entry::getKey)
                .orElseThrow();

        if (handOneThreeOfKindValue.equals(handTwoThreeOfKindValue)) {
            var handOneHighestKicker = groupedHandOneValues.keySet().stream()
                    .filter(e -> !e.equals(handOneThreeOfKindValue))
                    .max(Integer::compareTo)
                    .orElseThrow();
            var handTwoHighestKicker = groupedHandTwoValues.keySet().stream()
                    .filter(e -> !e.equals(handTwoThreeOfKindValue))
                    .max(Integer::compareTo)
                    .orElseThrow();
            return handOneHighestKicker > handTwoHighestKicker ? PLAYER_ONE_WIN : PLAYER_TWO_WIN;
        }
        return handOneThreeOfKindValue > handTwoThreeOfKindValue ? PLAYER_ONE_WIN : PLAYER_TWO_WIN;
    }

    private int compareFlushHands(Hand handOne, Hand handTwo) {
        var highestHandOneCard = handOne.getCards().stream()
                .max(Card::compareTo)
                .map(Card::getCardsValue)
                .orElseThrow();
        var highestHandTwoCard = handTwo.getCards().stream()
                .max(Card::compareTo)
                .map(Card::getCardsValue)
                .orElseThrow();

        if (highestHandOneCard.equals(highestHandTwoCard)) {
            var filteredHandOne = handOne.getCards().stream()
                    .filter(card -> handTwo.getCards().stream()
                            .noneMatch(handTwoCard -> handTwoCard.getCardsValue() == card.getCardsValue()))
                    .max(Card::compareTo)
                    .map(Card::getCardsValue)
                    .orElseThrow();

            var filteredHandTwo = handTwo.getCards().stream()
                    .filter(card -> handOne.getCards().stream()
                            .noneMatch(handTwoCard -> handTwoCard.getCardsValue() == card.getCardsValue()))
                    .max(Card::compareTo)
                    .map(Card::getCardsValue)
                    .orElseThrow();

            return filteredHandOne > filteredHandTwo ? PLAYER_ONE_WIN : PLAYER_TWO_WIN;
        }
        return highestHandOneCard > highestHandTwoCard ? PLAYER_ONE_WIN : PLAYER_TWO_WIN;
    }

    private int compareTwoPairHands(Hand handOne, Hand handTwo) {
        var groupedHandOneValues = CardHelpers.groupCardsByValue(handOne.getCards());
        var groupedHandTwoValues = CardHelpers.groupCardsByValue(handTwo.getCards());
        var highestHandOnePairCard = groupedHandOneValues.entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .max(Map.Entry.comparingByKey())
                .map(Map.Entry::getKey)
                .orElseThrow();
        var highestHandTwoPairCard = groupedHandTwoValues.entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .max(Map.Entry.comparingByKey())
                .map(Map.Entry::getKey)
                .orElseThrow();

        if (highestHandOnePairCard.equals(highestHandTwoPairCard)) {
            var handOneKicker = groupedHandOneValues.entrySet().stream().filter(e -> e.getValue() == 1).findFirst().map(Map.Entry::getKey).orElseThrow();
            var handTwoKicker = groupedHandTwoValues.entrySet().stream().filter(e -> e.getValue() == 1).findFirst().map(Map.Entry::getKey).orElseThrow();
            return handOneKicker > handTwoKicker ? PLAYER_ONE_WIN : PLAYER_TWO_WIN;
        }
        return highestHandOnePairCard > highestHandTwoPairCard ? PLAYER_ONE_WIN : PLAYER_TWO_WIN;
    }

    private static int comparePairHands(Hand handOne, Hand handTwo) {
        var groupedHandOneValues = CardHelpers.groupCardsByValue(handOne.getCards());
        var groupedHandTwoValues = CardHelpers.groupCardsByValue(handTwo.getCards());
        var handOnePairValue = groupedHandOneValues.entrySet().stream()
                .filter(e -> e.getValue().intValue() == HandRankingEvaluator.PAIR)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow();
        var handTwoPairValue = groupedHandTwoValues.entrySet().stream()
                .filter(e -> e.getValue().intValue() == HandRankingEvaluator.PAIR)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow();

        var isSamePairHands = handOnePairValue.equals(handTwoPairValue);

        if (isSamePairHands) {
            var highestHandOneCard = handOne.getCards().stream()
                    .filter(e -> e.getCardsValue() != handOnePairValue)
                    .max(Card::compareTo)
                    .map(Card::getCardsValue)
                    .orElseThrow();
            var highestHandTwoCard = handTwo.getCards().stream()
                    .filter(e -> e.getCardsValue() != handTwoPairValue)
                    .max(Card::compareTo)
                    .map(Card::getCardsValue)
                    .orElseThrow();
            return highestHandOneCard > highestHandTwoCard ? PLAYER_ONE_WIN : PLAYER_TWO_WIN;
        }
        return handOnePairValue > handTwoPairValue ? PLAYER_ONE_WIN : PLAYER_TWO_WIN;
    }

    private int compareFullHouseHands(Hand handOne, Hand handTwo) {
        var groupedHandOneValues = CardHelpers.groupCardsByValue(handOne.getCards());
        var groupedHandTwoValues = CardHelpers.groupCardsByValue(handTwo.getCards());
        var highestHandOneTriple = groupedHandOneValues.entrySet().stream()
                .filter(e -> e.getValue().intValue() == HandRankingEvaluator.THREE_OF_KIND)
                .findFirst()
                .map(Map.Entry::getKey)
                .orElseThrow();
        var highestHandTwoTriple = groupedHandTwoValues.entrySet().stream()
                .filter(e -> e.getValue().intValue() == HandRankingEvaluator.THREE_OF_KIND)
                .findFirst()
                .map(Map.Entry::getKey)
                .orElseThrow();

        if (highestHandOneTriple.equals(highestHandTwoTriple)) {
            var handOnePair = groupedHandOneValues.entrySet().stream()
                    .filter(e -> e.getValue().intValue() == HandRankingEvaluator.PAIR)
                    .findFirst()
                    .map(Map.Entry::getKey)
                    .orElseThrow();

            var handTwoPair = groupedHandTwoValues.entrySet().stream()
                    .filter(e -> e.getValue().intValue() == HandRankingEvaluator.PAIR)
                    .findFirst()
                    .map(Map.Entry::getKey)
                    .orElseThrow();
            return handOnePair > handTwoPair ? PLAYER_ONE_WIN : PLAYER_TWO_WIN;
        }
        return highestHandOneTriple > highestHandTwoTriple ? PLAYER_ONE_WIN : PLAYER_TWO_WIN;
    }

    private int compareHighCardHands(Hand handOne, Hand handTwo) {
        var highestHandOneCard = Collections.max(handOne.getCards(), Comparator.comparing(Card::getCardsValue));
        var highestHandTwoCard = Collections.max(handTwo.getCards(), Comparator.comparing(Card::getCardsValue));

        return highestHandOneCard.getCardsValue() > highestHandTwoCard.getCardsValue() ? PLAYER_ONE_WIN : PLAYER_TWO_WIN;
    }
}
