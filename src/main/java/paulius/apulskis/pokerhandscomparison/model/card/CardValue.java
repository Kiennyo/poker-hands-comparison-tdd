package paulius.apulskis.pokerhandscomparison.model.card;

import java.util.HashMap;
import java.util.Map;

public enum CardValue {
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "T"),
    JACK(11, "J"),
    QUEEN(12, "Q"),
    KING(13, "K"),
    ACE(14, "A");

    private static final Map<String, CardValue> DISPLAY_VALUE_CARD_VALUE_MAP = new HashMap<>();
    private final int value;
    private final String displayValue;

    static {
        for (CardValue cardValue : CardValue.values()) {
            DISPLAY_VALUE_CARD_VALUE_MAP.put(cardValue.displayValue, cardValue);
        }
    }

    CardValue(int value, String displayValue) {
        this.value = value;
        this.displayValue = displayValue;
    }

    public int getValue() {
        return value;
    }

    public static CardValue getCardValueByDisplayValue(String displayValue) {
        return DISPLAY_VALUE_CARD_VALUE_MAP.get(displayValue);
    }
}
