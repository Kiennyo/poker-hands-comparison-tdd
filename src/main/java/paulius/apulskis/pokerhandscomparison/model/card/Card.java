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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (value != card.value) return false;
        return suite == card.suite;
    }

    @Override
    public int hashCode() {
        int result = value.hashCode();
        result = 31 * result + suite.hashCode();
        return result;
    }
}
