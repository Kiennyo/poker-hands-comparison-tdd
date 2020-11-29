package paulius.apulskis.pokerhandscomparison;

import paulius.apulskis.pokerhandscomparison.model.hand.HandPair;
import paulius.apulskis.pokerhandscomparison.utils.FileReaderUtils;

public class Main {

    private static final HandRankingEvaluator handEvaluator = new HandRankingEvaluator();

    public static void main(String[] args) {
        var handComparer = new HandComparer();
        var collect = (Integer) FileReaderUtils.getStreamOfLines("p054_poker.txt")
                .map(Main::getPairOfHands)
                .map(pairOfHands -> {
                    var playerOneHand = pairOfHands.getPlayerOneHand();
                    var playerTwoHand = pairOfHands.getPlayerTwoHand();
                    var playerOneHandRanking = playerOneHand.getHandRanking();
                    var playerTwoHandRanking = playerTwoHand.getHandRanking();
                    if (playerOneHandRanking.getRanking() == playerTwoHandRanking.getRanking()) {
                        return handComparer.compareSameHands(playerOneHand, playerTwoHand) == HandComparer.PLAYER_ONE_WIN ? 1 : 0;
                    }
                    return playerOneHandRanking.getRanking() < playerTwoHandRanking.getRanking() ? 1 : 0;
                })
                .mapToInt(Integer::intValue).sum();
        System.out.println(collect);
    }

    private static HandPair getPairOfHands(String line) {
        var pairOfHands = HandPair.createPairOfHands(line);
        var playerOneHand = pairOfHands.getPlayerOneHand();
        var playerTwoHand = pairOfHands.getPlayerTwoHand();
        playerOneHand.setHandRanking(handEvaluator.evaluate(playerOneHand));
        playerTwoHand.setHandRanking(handEvaluator.evaluate(playerTwoHand));
        return pairOfHands;
    }
}
