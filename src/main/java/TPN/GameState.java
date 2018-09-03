package src.main.java.TPN;

import java.util.ArrayList;

public class GameState extends State {

    private ImageLoader imageLoader;

    //    private src.main.java.TPN.Board board;
    private Player player;
    private Player player2;
    private Computer computer;
    private Computer computer2;


    public GameState(Game game) {
        super(game);

    }


    public void setup() {
        Board.getGrid().clear();
        board = new Board(game);
        board.initGrid();

        imageLoader = new ImageLoader(game, "pieces/chess_pieces.png");
        player = new Player(game, 125, true);
        player2 = new Player(game, 75, false);
        computer = new Computer(game, 75, false);
        computer2 = new Computer(game, 125, true);
//            src.main.java.TPN.AI = new Ai(this,125,true);

        Game.getPlayers().clear();
        Game.getPlayers().add(computer2);
        Game.getPlayers().add(computer);

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
        return "src.main.java.TPN.GameState";
    }
}
