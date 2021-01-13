package paulius.apulskis.pokerhandscomparison.utils.values;

import paulius.apulskis.pokerhandscomparison.model.card.CardSuite;
import paulius.apulskis.pokerhandscomparison.model.card.CardValue;
import paulius.apulskis.pokerhandscomparison.model.hand.Hand;

import java.util.Arrays;

import static paulius.apulskis.pokerhandscomparison.model.card.Card.createCard;
import static paulius.apulskis.pokerhandscomparison.model.hand.Hand.createHand;

public class Hands {

    public static Hand royalFlush() {
        return createHand(Arrays.asList(
                createCard(CardValue.ACE, CardSuite.SPADES),
                createCard(CardValue.KING, CardSuite.SPADES),
                createCard(CardValue.QUEEN, CardSuite.SPADES),
                createCard(CardValue.JACK, CardSuite.SPADES),
                createCard(CardValue.TEN, CardSuite.SPADES)
        ));
    }

    public static Hand straightFlush() {
        return createHand(Arrays.asList(
                createCard(CardValue.TWO, CardSuite.SPADES),
                createCard(CardValue.THREE, CardSuite.SPADES),
                createCard(CardValue.FOUR, CardSuite.SPADES),
                createCard(CardValue.FIVE, CardSuite.SPADES),
                createCard(CardValue.SIX, CardSuite.SPADES)
        ));
    }

    public static Hand straightFlush1() {
        return createHand(Arrays.asList(
                createCard(CardValue.FOUR, CardSuite.SPADES),
                createCard(CardValue.SEVEN, CardSuite.SPADES),
                createCard(CardValue.SIX, CardSuite.SPADES),
                createCard(CardValue.FIVE, CardSuite.SPADES),
                createCard(CardValue.EIGHT, CardSuite.SPADES)
        ));
    }

    public static Hand fourOfKind() {
        return createHand(Arrays.asList(
                createCard(CardValue.ACE, CardSuite.SPADES),
                createCard(CardValue.ACE, CardSuite.HEARTS),
                createCard(CardValue.ACE, CardSuite.CLUBS),
                createCard(CardValue.ACE, CardSuite.DIAMONDS),
                createCard(CardValue.TEN, CardSuite.SPADES)
        ));
    }

    public static Hand fourOfKind1() {
        return createHand(Arrays.asList(
                createCard(CardValue.KING, CardSuite.SPADES),
                createCard(CardValue.KING, CardSuite.HEARTS),
                createCard(CardValue.KING, CardSuite.CLUBS),
                createCard(CardValue.KING, CardSuite.DIAMONDS),
                createCard(CardValue.TEN, CardSuite.SPADES)
        ));
    }

    public static Hand fullHouse() {
        return createHand(Arrays.asList(
                createCard(CardValue.ACE, CardSuite.SPADES),
                createCard(CardValue.ACE, CardSuite.DIAMONDS),
                createCard(CardValue.ACE, CardSuite.CLUBS),
                createCard(CardValue.SEVEN, CardSuite.SPADES),
                createCard(CardValue.SEVEN, CardSuite.HEARTS)
        ));
    }

    public static Hand fullHouse1() {
        return createHand(Arrays.asList(
                createCard(CardValue.KING, CardSuite.SPADES),
                createCard(CardValue.KING, CardSuite.DIAMONDS),
                createCard(CardValue.KING, CardSuite.CLUBS),
                createCard(CardValue.JACK, CardSuite.SPADES),
                createCard(CardValue.JACK, CardSuite.HEARTS)
        ));
    }

    public static Hand fullHouse2() {
        return createHand(Arrays.asList(
                createCard(CardValue.KING, CardSuite.SPADES),
                createCard(CardValue.KING, CardSuite.DIAMONDS),
                createCard(CardValue.KING, CardSuite.CLUBS),
                createCard(CardValue.ACE, CardSuite.SPADES),
                createCard(CardValue.ACE, CardSuite.HEARTS)
        ));
    }

    public static Hand flush() {
        return createHand(Arrays.asList(
                createCard(CardValue.ACE, CardSuite.HEARTS),
                createCard(CardValue.TEN, CardSuite.HEARTS),
                createCard(CardValue.FOUR, CardSuite.HEARTS),
                createCard(CardValue.SEVEN, CardSuite.HEARTS),
                createCard(CardValue.NINE, CardSuite.HEARTS)
        ));
    }

    public static Hand flush1() {
        return createHand(Arrays.asList(
                createCard(CardValue.TWO, CardSuite.HEARTS),
                createCard(CardValue.FOUR, CardSuite.HEARTS),
                createCard(CardValue.SEVEN, CardSuite.HEARTS),
                createCard(CardValue.JACK, CardSuite.HEARTS),
                createCard(CardValue.ACE, CardSuite.HEARTS)
        ));
    }

    public static Hand flush2() {
        return createHand(Arrays.asList(
                createCard(CardValue.THREE, CardSuite.HEARTS),
                createCard(CardValue.FIVE, CardSuite.HEARTS),
                createCard(CardValue.EIGHT, CardSuite.HEARTS),
                createCard(CardValue.TEN, CardSuite.HEARTS),
                createCard(CardValue.QUEEN, CardSuite.HEARTS)
        ));
    }

    public static Hand straight() {
        return createHand(Arrays.asList(
                createCard(CardValue.TWO, CardSuite.DIAMONDS),
                createCard(CardValue.THREE, CardSuite.HEARTS),
                createCard(CardValue.FOUR, CardSuite.CLUBS),
                createCard(CardValue.SIX, CardSuite.SPADES),
                createCard(CardValue.FIVE, CardSuite.SPADES)
        ));
    }

    public static Hand straight1() {
        return createHand(Arrays.asList(
                createCard(CardValue.FIVE, CardSuite.DIAMONDS),
                createCard(CardValue.SIX, CardSuite.HEARTS),
                createCard(CardValue.SEVEN, CardSuite.CLUBS),
                createCard(CardValue.EIGHT, CardSuite.SPADES),
                createCard(CardValue.NINE, CardSuite.SPADES)
        ));
    }

    public static Hand threeOfKind1() {
        return createHand(Arrays.asList(
                createCard(CardValue.ACE, CardSuite.SPADES),
                createCard(CardValue.ACE, CardSuite.DIAMONDS),
                createCard(CardValue.ACE, CardSuite.CLUBS),
                createCard(CardValue.FIVE, CardSuite.SPADES),
                createCard(CardValue.TEN, CardSuite.HEARTS)
        ));
    }

    public static Hand threeOfKind2() {
        return createHand(Arrays.asList(
                createCard(CardValue.KING, CardSuite.SPADES),
                createCard(CardValue.KING, CardSuite.DIAMONDS),
                createCard(CardValue.KING, CardSuite.CLUBS),
                createCard(CardValue.FIVE, CardSuite.SPADES),
                createCard(CardValue.TEN, CardSuite.HEARTS)
        ));
    }

    public static Hand threeOfKind3() {
        return createHand(Arrays.asList(
                createCard(CardValue.KING, CardSuite.SPADES),
                createCard(CardValue.KING, CardSuite.DIAMONDS),
                createCard(CardValue.KING, CardSuite.CLUBS),
                createCard(CardValue.TWO, CardSuite.SPADES),
                createCard(CardValue.ACE, CardSuite.HEARTS)
        ));
    }

    public static Hand twoPair1() {
        return createHand(Arrays.asList(
                createCard(CardValue.ACE, CardSuite.SPADES),
                createCard(CardValue.ACE, CardSuite.DIAMONDS),
                createCard(CardValue.FIVE, CardSuite.CLUBS),
                createCard(CardValue.FIVE, CardSuite.SPADES),
                createCard(CardValue.JACK, CardSuite.HEARTS)
        ));
    }

    public static Hand twoPair2() {
        return createHand(Arrays.asList(
                createCard(CardValue.JACK, CardSuite.SPADES),
                createCard(CardValue.JACK, CardSuite.DIAMONDS),
                createCard(CardValue.TWO, CardSuite.CLUBS),
                createCard(CardValue.TWO, CardSuite.SPADES),
                createCard(CardValue.FIVE, CardSuite.HEARTS)
        ));
    }

    public static Hand twoPair3() {
        return createHand(Arrays.asList(
                createCard(CardValue.JACK, CardSuite.CLUBS),
                createCard(CardValue.JACK, CardSuite.HEARTS),
                createCard(CardValue.TWO, CardSuite.DIAMONDS),
                createCard(CardValue.TWO, CardSuite.HEARTS),
                createCard(CardValue.SEVEN, CardSuite.HEARTS)
        ));
    }

    public static Hand twoPair4() {
        return createHand(Arrays.asList(
                createCard(CardValue.JACK, CardSuite.CLUBS),
                createCard(CardValue.JACK, CardSuite.HEARTS),
                createCard(CardValue.THREE, CardSuite.DIAMONDS),
                createCard(CardValue.THREE, CardSuite.HEARTS),
                createCard(CardValue.TWO, CardSuite.HEARTS)
        ));
    }

    public static Hand pair1() {
        return createHand(Arrays.asList(
                createCard(CardValue.KING, CardSuite.SPADES),
                createCard(CardValue.KING, CardSuite.DIAMONDS),
                createCard(CardValue.ACE, CardSuite.CLUBS),
                createCard(CardValue.FIVE, CardSuite.SPADES),
                createCard(CardValue.TEN, CardSuite.HEARTS)
        ));
    }

    public static Hand pair2() {
        return createHand(Arrays.asList(
                createCard(CardValue.EIGHT, CardSuite.HEARTS),
                createCard(CardValue.EIGHT, CardSuite.DIAMONDS),
                createCard(CardValue.TWO, CardSuite.CLUBS),
                createCard(CardValue.SEVEN, CardSuite.SPADES),
                createCard(CardValue.JACK, CardSuite.HEARTS)
        ));
    }

    public static Hand pair3() {
        return createHand(Arrays.asList(
                createCard(CardValue.EIGHT, CardSuite.SPADES),
                createCard(CardValue.EIGHT, CardSuite.CLUBS),
                createCard(CardValue.JACK, CardSuite.CLUBS),
                createCard(CardValue.ACE, CardSuite.SPADES),
                createCard(CardValue.NINE, CardSuite.HEARTS)
        ));
    }

    public static Hand highCard1() {
        return createHand(Arrays.asList(
                createCard(CardValue.JACK, CardSuite.SPADES),
                createCard(CardValue.TWO, CardSuite.DIAMONDS),
                createCard(CardValue.ACE, CardSuite.CLUBS),
                createCard(CardValue.FIVE, CardSuite.SPADES),
                createCard(CardValue.TEN, CardSuite.HEARTS)
        ));
    }

    public static Hand highCard2() {
        return createHand(Arrays.asList(
                createCard(CardValue.JACK, CardSuite.SPADES),
                createCard(CardValue.TWO, CardSuite.DIAMONDS),
                createCard(CardValue.EIGHT, CardSuite.CLUBS),
                createCard(CardValue.FIVE, CardSuite.SPADES),
                createCard(CardValue.TEN, CardSuite.HEARTS)
        ));
    }
}
