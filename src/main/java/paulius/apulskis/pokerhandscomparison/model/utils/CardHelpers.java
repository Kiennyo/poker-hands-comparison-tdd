package paulius.apulskis.pokerhandscomparison.model.utils;

import paulius.apulskis.pokerhandscomparison.model.card.Card;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CardHelpers {

    private CardHelpers() {
    }

    public static Map<Integer, Long> groupCardsByValue(List<Card> cards) {
        return cards.stream().collect(Collectors.groupingBy(Card::getCardsValue, Collectors.counting()));
    }
}
