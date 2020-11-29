package paulius.apulskis.pokerhandscomparison;

import paulius.apulskis.pokerhandscomparison.model.hand.HandPair;
import paulius.apulskis.pokerhandscomparison.model.player.Winner;
import paulius.apulskis.pokerhandscomparison.utils.FileReaderUtils;

public class GameResolver {

    private final HandComparer handComparer = new HandComparer();
    private final HandRankingEvaluator handEvaluator = new HandRankingEvaluator();

    public long countPlayerOneWinningHands() {
        return FileReaderUtils.getStreamOfLines("p054_poker.txt")
                .map(this::pairOfHandsMapper)
                .map(this::winnerMapper)
                .filter(winner -> winner == Winner.PLAYER_ONE_WIN)
                .count();
    }

    private Winner winnerMapper(HandPair pairOfHands) {
        var playerOneHand = pairOfHands.getPlayerOneHand();
        var playerTwoHand = pairOfHands.getPlayerTwoHand();
        var playerOneHandRanking = playerOneHand.getHandRanking();
        var playerTwoHandRanking = playerTwoHand.getHandRanking();

        if (playerOneHandRanking.getRankingValue() == playerTwoHandRanking.getRankingValue()) {
            return handComparer.compareSameHands(playerOneHand, playerTwoHand);
        }
        return playerOneHandRanking.getRankingValue() < playerTwoHandRanking.getRankingValue() ? Winner.PLAYER_ONE_WIN : Winner.PLAYER_TWO_WIN;
    }

    private HandPair pairOfHandsMapper(String line) {
        var pairOfHands = HandPair.createPairOfHands(line);
        var playerOneHand = pairOfHands.getPlayerOneHand();
        var playerTwoHand = pairOfHands.getPlayerTwoHand();

        playerOneHand.setHandRanking(handEvaluator.evaluate(playerOneHand));
        playerTwoHand.setHandRanking(handEvaluator.evaluate(playerTwoHand));
        return pairOfHands;
    }

}
