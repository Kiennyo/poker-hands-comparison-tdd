package paulius.apulskis.pokerhandscomparison.model.hand;

import paulius.apulskis.pokerhandscomparison.utils.CardUtils;

import java.util.Arrays;

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

        return new HandPair(Hand.createHand(CardUtils.toCards(playerOneCardStrings)), Hand.createHand(CardUtils.toCards(playerTwoCardStrings)));
    }
}
