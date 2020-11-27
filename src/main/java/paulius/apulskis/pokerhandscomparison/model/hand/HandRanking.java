package paulius.apulskis.pokerhandscomparison.model.hand;

public enum HandRanking {
    ROYAL_FLUSH(1),
    STRAIGHT_FLUSH(2),
    FOUR_OF_A_KIND(3),
    FULL_HOUSE(3),
    FLUSH(5),
    STRAIGHT(6),
    THREE_OF_A_KIND(7),
    TWO_PAIR(8),
    PAIR(9),
    HIGH_CARD(10);

    private final int ranking;

    HandRanking(int ranking) {
        this.ranking = ranking;
    }
}
