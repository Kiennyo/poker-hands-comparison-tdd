package paulius.apulskis.pokerhandscomparison.utils;

import paulius.apulskis.pokerhandscomparison.model.card.Card;
import paulius.apulskis.pokerhandscomparison.model.card.CardSuite;
import paulius.apulskis.pokerhandscomparison.model.card.CardValue;
import paulius.apulskis.pokerhandscomparison.model.hand.HandPair;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CardUtils {

    private CardUtils() {
    }

    public static Map<Integer, Long> groupCardsByValue(List<Card> cards) {
        return cards.stream().collect(Collectors.groupingBy(Card::getCardsValue, Collectors.counting()));
    }

    public static List<Card> toCards(String[] playerOneCardStrings) {
        return Arrays.stream(playerOneCardStrings).map(CardUtils::getCard).collect(Collectors.toList());
    }

    private static Card getCard(String cardString) {
        String[] valueSuite = cardString.split("");
        return Card.createCard(CardValue.getCardValueByDisplayValue(valueSuite[0]), CardSuite.getCardSuite(valueSuite[1]));
    }

    public static Integer getHighestCard(List<Card> cards) {
        return cards.stream()
                .max(Card::compareTo)
                .map(Card::getCardsValue)
                .orElseThrow();
    }
}
