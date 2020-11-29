package paulius.apulskis.pokerhandscomparison;

public class Main {

    public static void main(String[] args) {
        GameResolver gameResolver = new GameResolver();
        System.out.printf("Player 1 has won %d hands%n", gameResolver.countPlayerOneWinningHands());
    }
}
