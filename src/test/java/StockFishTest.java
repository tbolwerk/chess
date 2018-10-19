import TPN.StockFish;

public class StockFishTest {
    public StockFishTest() {
    }

    public static void main(String[] args) {
        StockFish client = new StockFish();
        String FEN = "8/6pk/8/1R5p/3K3P/8/6r1/8 b - - 0 42";
        if (client.startEngine()) {
            System.out.println("Engine has started..");
        } else {
            System.out.println("Oops! Something went wrong..");
        }

        client.sendCommand("uci");
        System.out.println(client.getOutput(0));
        System.out.println("Best move : " + client.getBestMove(FEN, 100));
        System.out.println("Legal moves : " + client.getLegalMoves(FEN));
        System.out.println("Board state :");
        client.drawBoard(FEN);
        System.out.println("Evaluation score : " + client.getEvalScore(FEN, 2000));
        System.out.println("Stopping engine..");
        client.stopEngine();
    }
}
