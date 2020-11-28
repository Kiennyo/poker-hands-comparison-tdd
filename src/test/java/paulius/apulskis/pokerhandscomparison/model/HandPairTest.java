package paulius.apulskis.pokerhandscomparison.model;

import org.junit.Test;
import paulius.apulskis.pokerhandscomparison.model.card.Card;
import paulius.apulskis.pokerhandscomparison.model.card.CardSuite;
import paulius.apulskis.pokerhandscomparison.model.card.CardValue;
import paulius.apulskis.pokerhandscomparison.model.hand.HandPair;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HandPairTest {

    @Test
    public void createPairOfHands() {
        var handPair = createPairOfHands("8C TS KC 9H 4S 7D 2S 5D 3S AC");

        assertEquals(playerOneCards(), handPair.getPlayerOneHand().getCards());
        assertEquals(playerTwoCards(), handPair.getPlayerTwoHand().getCards());
    }

    private List<Card> playerOneCards() {
        var cards = Arrays.asList(
                Card.createCard(CardValue.EIGHT, CardSuite.CLUBS),
                Card.createCard(CardValue.TEN, CardSuite.SPADES),
                Card.createCard(CardValue.KING, CardSuite.CLUBS),
                Card.createCard(CardValue.NINE, CardSuite.HEARTS),
                Card.createCard(CardValue.FOUR, CardSuite.SPADES)
        );
        cards.sort(Card::compareTo);
        return cards;
    }

    private List<Card> playerTwoCards() {
        var cards = Arrays.asList(
                Card.createCard(CardValue.SEVEN, CardSuite.DIAMONDS),
                Card.createCard(CardValue.TWO, CardSuite.SPADES),
                Card.createCard(CardValue.FIVE, CardSuite.DIAMONDS),
                Card.createCard(CardValue.THREE, CardSuite.SPADES),
                Card.createCard(CardValue.ACE, CardSuite.CLUBS)
        );
        cards.sort(Card::compareTo);
        return cards;
    }

    private HandPair createPairOfHands(String line) {
        return HandPair.createPairOfHands(line);
    }
}