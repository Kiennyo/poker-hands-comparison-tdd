package paulius.apulskis.pokerhandscomparison;

import paulius.apulskis.pokerhandscomparison.model.card.Card;
import paulius.apulskis.pokerhandscomparison.model.card.CardValue;
import paulius.apulskis.pokerhandscomparison.model.hand.Hand;
import paulius.apulskis.pokerhandscomparison.model.hand.HandRanking;

import java.util.Map;
import java.util.stream.Collectors;

public class HandParser {

    private static final int TWO_PAIR = 2;
    private static final int FOUR_OF_KIND = 4;
    private static final int THREE_OF_KIND = 3;
    private static final int FULL_HOUSE_GROUPS = 2;

    public HandRanking parse(Hand hand) {
        if (isRoyalFlush(hand)) {
            return HandRanking.ROYAL_FLUSH;
        } else if (isStraightFlush(hand)) {
            return HandRanking.STRAIGHT_FLUSH;
        } else if (isFourOfKind(hand)) {
            return HandRanking.FOUR_OF_A_KIND;
        } else if (isFullHouse(hand)) {
            return HandRanking.FULL_HOUSE;
        } else if (isFlush(hand)) {
            return HandRanking.FLUSH;
        } else if (isStraight(hand)) {
            return HandRanking.STRAIGHT;
        } else if (isThreeOfKind(hand)) {
            return HandRanking.THREE_OF_A_KIND;
        } else if (isTwoPair(hand)) {
            return HandRanking.TWO_PAIR;
        } else if (isPair(hand)) {
            return HandRanking.PAIR;
        }
        return HandRanking.HIGH_CARD;
    }

    private boolean isRoyalFlush(Hand hand) {
        return hand.getCards().get(0).getCardsValue() == CardValue.TEN.getValue() && isStraightFlush(hand);
    }

    private boolean isStraightFlush(Hand hand) {
        return isFlush(hand) && isStraight(hand);
    }

    private boolean isFlush(Hand hand) {
        var suite = hand.getCards().get(0).getSuite();
        return hand.getCards()
                .stream()
                .allMatch(e -> e.getSuite().equals(suite));
    }

    private boolean isStraight(Hand hand) {
        var cards = hand.getCards();
        for (int i = 1; i < cards.size(); i++) {
            if (cards.get(i - 1).getCardsValue() + 1 != cards.get(i).getCardsValue()) {
                return false;
            }
        }
        return true;
    }

    private boolean isFourOfKind(Hand hand) {
        var cardsGroup = getCardsValueGroupCount(hand);
        return cardsGroup.entrySet()
                .stream()
                .anyMatch(e -> e.getValue() == FOUR_OF_KIND);
    }

    private boolean isFullHouse(Hand hand) {
        var cardsGroup = getCardsValueGroupCount(hand);
        return cardsGroup.size() == FULL_HOUSE_GROUPS;
    }

    private boolean isThreeOfKind(Hand hand) {
        return getCardsValueGroupCount(hand).entrySet()
                .stream()
                .anyMatch(e -> e.getValue() == THREE_OF_KIND);
    }

    private boolean isTwoPair(Hand hand) {
        var cardsGroup = getCardsValueGroupCount(hand);
        var cardGroupCount = cardsGroup.entrySet()
                .stream()
                .collect(Collectors.groupingBy(Map.Entry::getValue, Collectors.counting()));
        return cardGroupCount.containsValue((long) TWO_PAIR);
    }

    private boolean isPair(Hand hand) {
        var cardsGroup = getCardsValueGroupCount(hand);
        return cardsGroup.containsValue((long) TWO_PAIR);
    }

    private Map<Integer, Long> getCardsValueGroupCount(Hand hand) {
        return hand.getCards()
                .stream()
                .collect(Collectors.groupingBy(Card::getCardsValue, Collectors.counting()));
    }
}
