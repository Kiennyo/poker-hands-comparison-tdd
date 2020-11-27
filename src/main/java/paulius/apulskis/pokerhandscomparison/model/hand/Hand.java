package paulius.apulskis.pokerhandscomparison.model.hand;

import paulius.apulskis.pokerhandscomparison.model.card.Card;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class Hand {

    private final List<Card> cards;

    private Hand(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getCards() {
        return cards;
    }

    public static Hand createHand(List<Card> cards) {
        var sortedCards = cards.stream().sorted().collect(toList());
        return new Hand(sortedCards);
    }
}
