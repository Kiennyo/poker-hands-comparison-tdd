package paulius.apulskis.pokerhandscomparison.model.hand;

import paulius.apulskis.pokerhandscomparison.model.card.Card;
import paulius.apulskis.pokerhandscomparison.model.card.CardSuite;
import paulius.apulskis.pokerhandscomparison.model.card.CardValue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HandPair {

    private final Hand playerOneHand;
    private final Hand playerTwoHand;

    private HandPair(Hand playerOneHand, Hand playerTwoHand) {
        this.playerOneHand = playerOneHand;
        this.playerTwoHand = playerTwoHand;
    }

    public Hand getPlayerOneHand() {
        return playerOneHand;
    }

    public Hand getPlayerTwoHand() {
        return playerTwoHand;
    }

    public static HandPair createPairOfHands(String line) {
        var cards = line.split(" ");
        var playerOneCardStrings = Arrays.copyOfRange(cards, 0, 5);
        var playerTwoCardStrings = Arrays.copyOfRange(cards, 5, 10);

        return new HandPair(Hand.createHand(getCollect(playerOneCardStrings)), Hand.createHand(getCollect(playerTwoCardStrings)));
    }

    private static List<Card> getCollect(String[] playerOneCardStrings) {
        return Arrays.stream(playerOneCardStrings).map(HandPair::getCard).collect(Collectors.toList());
    }

    private static Card getCard(String cardString) {
        String[] valueSuite = cardString.split("");
        return Card.createCard(CardValue.getCardValueByDisplayValue(valueSuite[0]), CardSuite.getCardSuite(valueSuite[1]));
    }
}
