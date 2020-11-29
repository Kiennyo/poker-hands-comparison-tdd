package paulius.apulskis.pokerhandscomparison;

import paulius.apulskis.pokerhandscomparison.model.card.Card;
import paulius.apulskis.pokerhandscomparison.model.hand.Hand;
import paulius.apulskis.pokerhandscomparison.model.player.Winner;
import paulius.apulskis.pokerhandscomparison.utils.CardUtils;

import java.util.*;

public class HandComparer {

    public Winner compareSameHands(Hand handOne, Hand handTwo) {
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
            case ROYAL_FLUSH -> Winner.TIE;
        };
    }

    private Winner compareFourOfKindHands(Hand handOne, Hand handTwo) {
        var groupedHandOneValues = CardUtils.groupCardsByValue(handOne.getCards());
        var groupedHandTwoValues = CardUtils.groupCardsByValue(handTwo.getCards());
        var handOneFourOfKindValue = groupedHandOneValues.entrySet().stream()
                .filter(e -> e.getValue() == HandRankingEvaluator.FOUR_OF_KIND)
                .findFirst().map(Map.Entry::getKey)
                .orElseThrow();
        var handTwoFourOfKindValue = groupedHandTwoValues.entrySet().stream()
                .filter(e -> e.getValue() == HandRankingEvaluator.FOUR_OF_KIND)
                .findFirst()
                .map(Map.Entry::getKey)
                .orElseThrow();

        return handOneFourOfKindValue > handTwoFourOfKindValue ? Winner.PLAYER_ONE_WIN : Winner.PLAYER_TWO_WIN;
    }

    private Winner compareStraightHands(Hand handOne, Hand handTwo) {
        var highestHandOneCard = CardUtils.getHighestCard(handOne.getCards());
        var highestHandTwoCard = CardUtils.getHighestCard(handTwo.getCards());

        return highestHandOneCard > highestHandTwoCard ? Winner.PLAYER_ONE_WIN : Winner.PLAYER_TWO_WIN;
    }

    private Winner compareThreeOfKindHands(Hand handOne, Hand handTwo) {
        var groupedHandOneValues = CardUtils.groupCardsByValue(handOne.getCards());
        var groupedHandTwoValues = CardUtils.groupCardsByValue(handTwo.getCards());
        var handOneThreeOfKindValue = getCardValueFromCardGroup(groupedHandOneValues, HandRankingEvaluator.THREE_OF_KIND);
        var handTwoThreeOfKindValue = getCardValueFromCardGroup(groupedHandTwoValues, HandRankingEvaluator.THREE_OF_KIND);

        if (handOneThreeOfKindValue.equals(handTwoThreeOfKindValue)) {
            var handOneKicker = getKickerInThreeOfKind(groupedHandOneValues, handOneThreeOfKindValue);
            var handTwoKicker = getKickerInThreeOfKind(groupedHandTwoValues, handTwoThreeOfKindValue);

            return handOneKicker > handTwoKicker ? Winner.PLAYER_ONE_WIN : Winner.PLAYER_TWO_WIN;
        }
        return handOneThreeOfKindValue > handTwoThreeOfKindValue ? Winner.PLAYER_ONE_WIN : Winner.PLAYER_TWO_WIN;
    }

    private Winner compareFlushHands(Hand handOne, Hand handTwo) {
        var highestHandOneCard = CardUtils.getHighestCard(handOne.getCards());
        var highestHandTwoCard = CardUtils.getHighestCard(handTwo.getCards());

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

            return filteredHandOne > filteredHandTwo ? Winner.PLAYER_ONE_WIN : Winner.PLAYER_TWO_WIN;
        }
        return highestHandOneCard > highestHandTwoCard ? Winner.PLAYER_ONE_WIN : Winner.PLAYER_TWO_WIN;
    }

    private Winner compareTwoPairHands(Hand handOne, Hand handTwo) {
        var groupedHandOneValues = CardUtils.groupCardsByValue(handOne.getCards());
        var groupedHandTwoValues = CardUtils.groupCardsByValue(handTwo.getCards());
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
            var handOneKicker = getKickInTwoPair(groupedHandOneValues);
            var handTwoKicker = getKickInTwoPair(groupedHandTwoValues);

            return handOneKicker > handTwoKicker ? Winner.PLAYER_ONE_WIN : Winner.PLAYER_TWO_WIN;
        }
        return highestHandOnePairCard > highestHandTwoPairCard ? Winner.PLAYER_ONE_WIN : Winner.PLAYER_TWO_WIN;
    }

    private Integer getKickInTwoPair(Map<Integer, Long> groupedHandOneValues) {
        var countOfGroup = 1;
        return groupedHandOneValues.entrySet().stream()
                .filter(e -> e.getValue() == countOfGroup)
                .findFirst()
                .map(Map.Entry::getKey)
                .orElseThrow();
    }

    private Winner comparePairHands(Hand handOne, Hand handTwo) {
        var groupedHandOneValues = CardUtils.groupCardsByValue(handOne.getCards());
        var groupedHandTwoValues = CardUtils.groupCardsByValue(handTwo.getCards());
        var handOnePairValue = getCardValueFromCardGroup(groupedHandOneValues, HandRankingEvaluator.PAIR);
        var handTwoPairValue = getCardValueFromCardGroup(groupedHandTwoValues, HandRankingEvaluator.PAIR);
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

            return highestHandOneCard > highestHandTwoCard ? Winner.PLAYER_ONE_WIN : Winner.PLAYER_TWO_WIN;
        }
        return handOnePairValue > handTwoPairValue ? Winner.PLAYER_ONE_WIN : Winner.PLAYER_TWO_WIN;
    }

    private Winner compareFullHouseHands(Hand handOne, Hand handTwo) {
        var groupedHandOneValues = CardUtils.groupCardsByValue(handOne.getCards());
        var groupedHandTwoValues = CardUtils.groupCardsByValue(handTwo.getCards());
        var highestHandOneTriple = getCardValueFromCardGroup(groupedHandOneValues, HandRankingEvaluator.THREE_OF_KIND);
        var highestHandTwoTriple = getCardValueFromCardGroup(groupedHandTwoValues, HandRankingEvaluator.THREE_OF_KIND);

        if (highestHandOneTriple.equals(highestHandTwoTriple)) {
            var handOnePairValue = getCardValueFromCardGroup(groupedHandOneValues, HandRankingEvaluator.PAIR);
            var handTwoPairValue = getCardValueFromCardGroup(groupedHandTwoValues, HandRankingEvaluator.PAIR);

            return handOnePairValue > handTwoPairValue ? Winner.PLAYER_ONE_WIN : Winner.PLAYER_TWO_WIN;
        }
        return highestHandOneTriple > highestHandTwoTriple ? Winner.PLAYER_ONE_WIN : Winner.PLAYER_TWO_WIN;
    }

    private Winner compareHighCardHands(Hand handOne, Hand handTwo) {
        var highestHandOneCard = Collections.max(handOne.getCards(), Comparator.comparing(Card::getCardsValue));
        var highestHandTwoCard = Collections.max(handTwo.getCards(), Comparator.comparing(Card::getCardsValue));

        return highestHandOneCard.getCardsValue() > highestHandTwoCard.getCardsValue() ? Winner.PLAYER_ONE_WIN : Winner.PLAYER_TWO_WIN;
    }

    private Integer getCardValueFromCardGroup(Map<Integer, Long> groupedHandValues, int cardGroup) {
        return groupedHandValues.entrySet().stream()
                .filter(e -> e.getValue().intValue() == cardGroup)
                .findFirst()
                .map(Map.Entry::getKey)
                .orElseThrow();
    }

    private Integer getKickerInThreeOfKind(Map<Integer, Long> groupedHandTwoValues, Integer handTwoThreeOfKindValue) {
        return groupedHandTwoValues.keySet().stream()
                .filter(e -> !e.equals(handTwoThreeOfKindValue))
                .max(Integer::compareTo)
                .orElseThrow();
    }
}
