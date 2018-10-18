package TPN.states;

import TPN.Game;
import TPN.players.Player;
import TPN.ui.Button;

public class GameOverState extends State {


    private static boolean readyToRemovePlayers = false;
    private static boolean readyToRemoveBoard = false;

    public GameOverState(Game game) {
        super(game);
//    setup();
    }

    public synchronized void removePieces() {
//        while (readyToRemovePlayers) {
//            wait();
//        }
        for (Player player : Game.getPlayers()) {
            player.removeAllPieces();
        }
//            notify();
        readyToRemovePlayers = true;

    }

    public void removeBoard() {
//        while (readyToRemoveBoard){
//            wait();
//        }
        board = null;
    }

    public void setup() {
//        removePieces();

//        removeBoard();


//        System.out.println(src.main.java.TPN.Board.getGrid().size());
        button = new Button(game, (int) Game.getGAMEWIDTH() / 2 - ((int) (Game.getGAMEWIDTH() / 3) / 2), (int) Game.getGAMEHEIGHT() / 2, (int) Game.getGAMEHEIGHT() / 3, 30);


        button.setColor(100);
        button.setTextColor(120);
        button.setSelectColor(123);
        button.setText("New Game");
    }


    public void draw() {
        board.drawGrid();
        for (Player player : Game.getPlayers()) {
            player.drawPieces();
        }
        game.fill(255);
        game.textSize(30);
        button.drawRectangle();
        game.text("Game over " + game.getWinner() + " won!", (Game.getGAMEWIDTH() / 3) - (Game.getGAMEWIDTH() / 3) / 2, Game.getGAMEHEIGHT() / 3);
//        game.background(0);
    }

    public Button getButton() {
        return button;
    }

    @Override
    public String toString() {
        return "GameOverState";
    }


}
