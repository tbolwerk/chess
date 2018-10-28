package TPN.states;

import TPN.Game;
import TPN.board.Board;
import TPN.board.Box;
import TPN.moves.FENParser;
import TPN.players.AI;
import TPN.players.Computer;
import TPN.players.Player;
import TPN.players.StockFishAI;
import TPN.ui.Button;
import TPN.ui.ImageLoader;

public class GameState extends State {

    private ImageLoader imageLoader;

    //    private src.main.java.TPN.Board board;



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


        for (Player player : Game.getPlayers()) {
            player.initPieces();
            player.drawPieces();
        }
//        FENParser.printFENArray();
//        FENParser.printFENArrayValue();


    }


    public void draw() {
        if(board != null) {
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

    @Override
    public Button getSelectWhitePlayerButton() {
        return null;
    }

    @Override
    public Button getSelectBlackPlayerButton() {
        return null;
    }

    @Override
    public Button getSettingsButton() {
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

    @Override
    public void mouseClicked() {

    }

    @Override
    public void mousePressed() {
        for (Box box : Board.getGrid()) {
            if (this.getBoard().boxSelected(box) && box.getPiece() != null) {
                box.setIsClicked(true);
                game.getClickedBoxes().add(box);
            } else if (game.getClickedBoxes().size() > 0 && this.getBoard().boxSelected(box)) {
                box.setIsClicked(true);
                game.getClickedBoxes().add(box);
            }
        }
        if (game.getClickedBoxes().size() == 1 && game.getClickedBoxes().get(0).getPiece() != null) {
            game.showOptions();
        }

        if (game.getClickedBoxes().size() > 1) {
            game.movePiece();
//                if(clickedBoxes.get(0).getPiece() instanceof src.main.java.TPN.Pawn){
//                    clickedBoxes.get(0).getPiece().getPlayer().promote();
//                }
            this.getBoard().setAllBoxesUnclicked();
            game.getClickedBoxes().clear();
            this.getBoard().clearHighLightedOptionalBoxes();
        }
    }

    @Override
    public void keyPressed() {
        if(game.key == game.ESC)
            game.key=0;
        if (game.key == 0) {
            State.setCurrentState(game.getMenuState());
            Game.getPlayers().clear();
            game.getMenuState().setup();
        }
    }
}
