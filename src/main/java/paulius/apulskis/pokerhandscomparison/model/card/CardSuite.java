package paulius.apulskis.pokerhandscomparison.model.card;

public enum CardSuite {
    SPADES,
    CLUBS,
    DIAMONDS,
    HEARTS;

    public static CardSuite getCardSuite(String suite) {
        return switch (suite) {
            case "S" -> SPADES;
            case "C" -> CLUBS;
            case "D" -> DIAMONDS;
            case "H" -> HEARTS;
            default -> throw new IllegalStateException("Unexpected value: " + suite);
        };
    }
}
