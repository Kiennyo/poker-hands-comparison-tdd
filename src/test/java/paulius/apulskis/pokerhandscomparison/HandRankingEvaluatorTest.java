package paulius.apulskis.pokerhandscomparison;

import org.junit.Test;
import paulius.apulskis.pokerhandscomparison.model.hand.Hand;
import paulius.apulskis.pokerhandscomparison.model.hand.HandRanking;

import static org.junit.Assert.assertEquals;
import static paulius.apulskis.pokerhandscomparison.utils.values.Hands.*;

public class HandRankingEvaluatorTest {

    private final HandRankingEvaluator handRankingEvaluator = new HandRankingEvaluator();

    @Test
    public void parse_royalFlush() {
        var ranking = getHandRanking(royalFlush());
        assertEquals(HandRanking.ROYAL_FLUSH, ranking);
    }

    @Test
    public void parse_straightFlush() {
        var ranking = getHandRanking(straightFlush());
        assertEquals(HandRanking.STRAIGHT_FLUSH, ranking);
    }

    @Test
    public void parse_fourOfKind() {
        var ranking = getHandRanking(fourOfKind());
        assertEquals(HandRanking.FOUR_OF_A_KIND, ranking);
    }

    @Test
    public void parse_fullHouse() {
        var ranking = getHandRanking(fullHouse());
        assertEquals(HandRanking.FULL_HOUSE, ranking);
    }

    @Test
    public void parse_flush() {
        var ranking = getHandRanking(flush());
        assertEquals(HandRanking.FLUSH, ranking);
    }

    @Test
    public void parse_straight() {
        var ranking = getHandRanking(straight());
        assertEquals(HandRanking.STRAIGHT, ranking);
    }

    @Test
    public void parse_threeOfKind() {
        var ranking = getHandRanking(threeOfKind1());
        assertEquals(HandRanking.THREE_OF_A_KIND, ranking);
    }

    @Test
    public void parse_twoPair() {
        var ranking = getHandRanking(twoPair1());
        assertEquals(HandRanking.TWO_PAIR, ranking);
    }

    @Test
    public void parse_pair() {
        var ranking = getHandRanking(pair1());
        assertEquals(HandRanking.PAIR, ranking);
    }

    @Test
    public void parse_highCard() {
        var ranking = getHandRanking(highCard1());
        assertEquals(HandRanking.HIGH_CARD, ranking);
    }

    private HandRanking getHandRanking(Hand straight) {
        return handRankingEvaluator.evaluate(straight);
    }
}