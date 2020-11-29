package paulius.apulskis.pokerhandscomparison;

import org.junit.Test;
import paulius.apulskis.pokerhandscomparison.model.hand.HandRanking;
import paulius.apulskis.pokerhandscomparison.model.player.Winner;
import paulius.apulskis.pokerhandscomparison.utils.values.Hands;

import static org.junit.Assert.assertEquals;

public class HandComparerTest {

    private final HandComparer handComparer = new HandComparer();

    @Test
    public void compareSameHands_highCardHands() {
        var hand1 = Hands.highCard1().setHandRanking(HandRanking.HIGH_CARD);
        var hand2 = Hands.highCard2().setHandRanking(HandRanking.HIGH_CARD);

        Winner highestHand = handComparer.compareSameHands(hand1, hand2);

        assertEquals(Winner.PLAYER_ONE_WIN, highestHand);
    }

    @Test
    public void compareSameHands_pair() {
        var hand1 = Hands.pair1().setHandRanking(HandRanking.PAIR);
        var hand2 = Hands.pair2().setHandRanking(HandRanking.PAIR);

        Winner highestHand = handComparer.compareSameHands(hand1, hand2);

        assertEquals(Winner.PLAYER_ONE_WIN, highestHand);
    }

    @Test
    public void compareSameHands_samePair() {
        var hand1 = Hands.pair2().setHandRanking(HandRanking.PAIR);
        var hand2 = Hands.pair3().setHandRanking(HandRanking.PAIR);

        Winner highestHand = handComparer.compareSameHands(hand1, hand2);

        assertEquals(Winner.PLAYER_TWO_WIN, highestHand);
    }

    @Test
    public void compareSameHands_twoPair() {
        var hand1 = Hands.twoPair1().setHandRanking(HandRanking.TWO_PAIR);
        var hand2 = Hands.twoPair2().setHandRanking(HandRanking.TWO_PAIR);

        Winner highestHand = handComparer.compareSameHands(hand1, hand2);

        assertEquals(Winner.PLAYER_ONE_WIN, highestHand);
    }

    @Test
    public void compareSameHands_sameTwoPair() {
        var hand1 = Hands.twoPair2().setHandRanking(HandRanking.TWO_PAIR);
        var hand2 = Hands.twoPair3().setHandRanking(HandRanking.TWO_PAIR);

        Winner highestHand = handComparer.compareSameHands(hand1, hand2);

        assertEquals(Winner.PLAYER_TWO_WIN, highestHand);
    }

    @Test
    public void compareSameHands_threeOfKind() {
        var hand1 = Hands.threeOfKind1().setHandRanking(HandRanking.THREE_OF_A_KIND);
        var hand2 = Hands.threeOfKind2().setHandRanking(HandRanking.THREE_OF_A_KIND);

        Winner highestHand = handComparer.compareSameHands(hand1, hand2);

        assertEquals(Winner.PLAYER_ONE_WIN, highestHand);
    }

    @Test
    public void compareSameHands_sameThreeOfKind() {
        var hand1 = Hands.threeOfKind2().setHandRanking(HandRanking.THREE_OF_A_KIND);
        var hand2 = Hands.threeOfKind3().setHandRanking(HandRanking.THREE_OF_A_KIND);

        Winner highestHand = handComparer.compareSameHands(hand1, hand2);

        assertEquals(Winner.PLAYER_TWO_WIN, highestHand);
    }

    @Test
    public void compareSameHands_straight() {
        var hand1 = Hands.straight().setHandRanking(HandRanking.STRAIGHT);
        var hand2 = Hands.straight1().setHandRanking(HandRanking.STRAIGHT);

        Winner highestHand = handComparer.compareSameHands(hand1, hand2);

        assertEquals(Winner.PLAYER_TWO_WIN, highestHand);
    }

    @Test
    public void compareSameHands_flush() {
        var hand1 = Hands.flush().setHandRanking(HandRanking.FLUSH);
        var hand2 = Hands.flush2().setHandRanking(HandRanking.FLUSH);

        Winner highestHand = handComparer.compareSameHands(hand1, hand2);

        assertEquals(Winner.PLAYER_ONE_WIN, highestHand);
    }

    @Test
    public void compareSameHands_flushWithSameHighestCard() {
        var hand1 = Hands.flush().setHandRanking(HandRanking.FLUSH);
        var hand2 = Hands.flush1().setHandRanking(HandRanking.FLUSH);

        Winner highestHand = handComparer.compareSameHands(hand1, hand2);

        assertEquals(Winner.PLAYER_TWO_WIN, highestHand);
    }

    @Test
    public void compareSameHands_fullHouse() {
        var hand1 = Hands.fullHouse().setHandRanking(HandRanking.FULL_HOUSE);
        var hand2 = Hands.fullHouse1().setHandRanking(HandRanking.FULL_HOUSE);

        Winner highestHand = handComparer.compareSameHands(hand1, hand2);

        assertEquals(Winner.PLAYER_ONE_WIN, highestHand);
    }

    @Test
    public void compareSameHands_fullHouseWithSameTrip() {
        var hand1 = Hands.fullHouse1().setHandRanking(HandRanking.FULL_HOUSE);
        var hand2 = Hands.fullHouse2().setHandRanking(HandRanking.FULL_HOUSE);

        Winner highestHand = handComparer.compareSameHands(hand1, hand2);

        assertEquals(Winner.PLAYER_TWO_WIN, highestHand);
    }

    @Test
    public void compareSameHands_fourOfKind() {
        var hand1 = Hands.fourOfKind().setHandRanking(HandRanking.FOUR_OF_A_KIND);
        var hand2 = Hands.fourOfKind1().setHandRanking(HandRanking.FOUR_OF_A_KIND);

        Winner highestHand = handComparer.compareSameHands(hand1, hand2);

        assertEquals(Winner.PLAYER_ONE_WIN, highestHand);
    }

    @Test
    public void compareSameHands_straightFlush() {
        var hand1 = Hands.straightFlush().setHandRanking(HandRanking.STRAIGHT_FLUSH);
        var hand2 = Hands.straightFlush1().setHandRanking(HandRanking.STRAIGHT_FLUSH);

        Winner highestHand = handComparer.compareSameHands(hand1, hand2);

        assertEquals(Winner.PLAYER_TWO_WIN, highestHand);
    }
}
