package paulius.apulskis.pokerhandscomparison.model.card;

public class Card implements Comparable<Card> {

    private final CardValue value;
    private final CardSuite suite;

    private Card(CardValue value, CardSuite suite) {
        this.value = value;
        this.suite = suite;
    }

    public int getCardsValue() {
        return value.getValue();
    }

    public CardSuite getSuite() {
        return suite;
    }

    public static Card createCard(CardValue value, CardSuite suite) {
        return new Card(value, suite);
    }

    @Override
    public int compareTo(Card card) {
        return this.getCardsValue() > card.getCardsValue() ? 1 : -1;
    }
}
