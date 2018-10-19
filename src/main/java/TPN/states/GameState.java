package TPN.states;

import TPN.Game;
import TPN.board.Board;
import TPN.players.AI;
import TPN.players.Computer;
import TPN.players.Player;
import TPN.players.StockFishAI;
import TPN.ui.Button;
import TPN.ui.ImageLoader;

public class GameState extends State {

    private ImageLoader imageLoader;

    //    private src.main.java.TPN.Board board;
    private Player whitePlayer;
    private Player blackPlayer;
    private Computer blackComputer;
    private Computer whiteComputer;
    private StockFishAI blackStockFishAI;
    private StockFishAI whiteStockFishAI;


    private static int countMoves;
    private static int countHalfMoves;


    public GameState(Game game) {
        super(game);

    }


    public void setup() {
        Board.getGrid().clear();
        board = new Board(game);
        board.initGrid();
        countMoves = 0;
        imageLoader = new ImageLoader(game, "pieces/chess_pieces.png");
        whitePlayer = new Player(game, 125, true);
        blackPlayer = new Player(game, 75, false);
        blackComputer = new Computer(game, 75, false);
        whiteComputer = new Computer(game, 125, true);
        blackStockFishAI = new StockFishAI(game, 75, false);
        whiteStockFishAI = new StockFishAI(game, 125, true);

//            src.main.java.TPN.AI = new Ai(this,125,true);

        Game.getPlayers().clear();
        Game.getPlayers().add(whiteComputer);
        Game.getPlayers().add(blackStockFishAI);
        for (Player player : Game.getPlayers()) {
            player.initPieces();
            player.drawPieces();
        }
//        FENParser.printFENArray();
//        FENParser.printFENArrayValue();


    }


    public void draw() {
        board.drawGrid();

        for (Player player : Game.getPlayers()) {

            player.drawPieces();
            if (player instanceof AI) {
                if (player.getIsTurn()) {
                    ((AI) player).makeMove();
                }
            }
        }
    }

    public static void setHalfCountMoves(int counter) {
        countHalfMoves = counter;
    }

    public static void setCountMoves(int counter) {

        countMoves = countMoves + counter;
    }

    public static int getCountHalfMoves() {
        return countHalfMoves;
    }

    public static int getCountMoves() {
        return countMoves;
    }

    @Override
    public Button getButton() {
        return null;
    }


    public Board getBoard() {
        return board;
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    @Override
    public String toString() {
        return "GameState";
    }
}
