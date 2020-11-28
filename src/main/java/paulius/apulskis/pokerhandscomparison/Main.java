package paulius.apulskis.pokerhandscomparison;

import paulius.apulskis.pokerhandscomparison.model.hand.HandPair;
import paulius.apulskis.pokerhandscomparison.utils.FileReaderUtils;

import java.util.stream.Collectors;

public class Main {

    private static HandRankingEvaluator handEvaluator = new HandRankingEvaluator();

    public static void main(String[] args) {
        var handComparer = new HandComparer();
        var handEvaluator = new HandRankingEvaluator();
        var collect = FileReaderUtils.getStreamOfLines("p054_poker.txt")
                .map(Main::getHandPair)
                .collect(Collectors.toList());
    }

    private static HandPair getHandPair(String line) {
        var pairOfHands = HandPair.createPairOfHands(line);
        var playerOneHand = pairOfHands.getPlayerOneHand();
        var playerTwoHand = pairOfHands.getPlayerTwoHand();
        playerOneHand.setHandRanking(handEvaluator.evaluate(playerOneHand));
        playerTwoHand.setHandRanking(handEvaluator.evaluate(playerTwoHand));
        return pairOfHands;
    }
}
