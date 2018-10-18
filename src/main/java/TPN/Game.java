package TPN;

import TPN.board.Board;
import TPN.board.Box;
import TPN.moves.FENParser;
import TPN.pieces.King;
import TPN.pieces.Piece;
import TPN.players.Player;
import TPN.states.GameOverState;
import TPN.states.GameState;
import TPN.states.MenuState;
import TPN.states.State;
import processing.core.PApplet;

import java.util.ArrayList;


public class Game extends PApplet {
    final private static float GAMEWIDTH = 700;
    final private static float GAMEHEIGHT = GAMEWIDTH;

    private static ArrayList<Player> players = new ArrayList<Player>();


    private GameState gameState = new GameState(this);
    private GameOverState gameOverState = new GameOverState(this);
    private MenuState menuState = new MenuState(this);

    private State state;


    private ArrayList<Box> clickedBoxes = new ArrayList<Box>();


    private String winner;


    public static void main(String args[]) {
        PApplet.main("TPN.Game");

    }

    public static float getGAMEHEIGHT() {
        return GAMEHEIGHT;
    }

    public static float getGAMEWIDTH() {
        return GAMEWIDTH;
    }

    public static ArrayList<Player> getPlayers() {
        return players;
    }

    public void settings() {
        size((int) GAMEWIDTH, (int) GAMEHEIGHT);
    }


    public void draw() {
//        System.out.println(src.main.java.TPN.State.getCurrentState());
        State.getCurrentState().draw();


//        System.out.println(src.main.java.TPN.State.getCurrentState().toString());

    }


    public void setup() {
        background(244, 161, 66);
        frameRate(240);
        if (State.getCurrentState() == null) {
            State.setCurrentState(menuState);
        }


//        try {
        try {
            State.getCurrentState().setup();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }

    @Override
    public void mouseClicked() {
        if (State.getCurrentState().getButton() != null && State.getCurrentState().getButton().overRect()) {

            State.setCurrentState(gameState);

            setup();

        }
    }


    public void keyboardPressed() {

    }

    @Override
    public void mousePressed() {

        if (getGameState() == gameState) {
            for (Box box : Board.getGrid()) {
                if (gameState.getBoard().boxSelected(box) && box.getPiece() != null) {
                    box.setIsClicked(true);
                    clickedBoxes.add(box);
                } else if (clickedBoxes.size() > 0 && gameState.getBoard().boxSelected(box)) {
                    box.setIsClicked(true);
                    clickedBoxes.add(box);
                }
            }
            if (clickedBoxes.size() == 1 && clickedBoxes.get(0).getPiece() != null) {
                showOptions();
            }

            if (clickedBoxes.size() > 1) {
                movePiece();
//                if(clickedBoxes.get(0).getPiece() instanceof src.main.java.TPN.Pawn){
//                    clickedBoxes.get(0).getPiece().getPlayer().promote();
//                }
                gameState.getBoard().setAllBoxesUnclicked();
                clickedBoxes.clear();
                gameState.getBoard().clearHighLightedOptionalBoxes();
            }
        }

    }

    private void showOptions() {
        Box startBox = clickedBoxes.get(0);
        if (startBox.getPiece() != null) {
            gameState.getBoard().setHighLightedOptionalBoxes(startBox);

        }
    }

    private void stalemate() {

        players.get(0).setHasLost(true);
        players.get(1).setHasLost(true);
        setWinner("Draw");

    }

    private void checksGameStateAfterTurn() {
        int totalPieces = 0;
        for (Player player : Game.getPlayers()) {
            for (Piece piece : player.getPieces()) {
                if (piece instanceof King) {
                    if (((King) piece).isCheckMate()) {
                        gameover();
                    }
                }
            }
            if (player.getPieces().size() == 1) {
                totalPieces++;
            }
            if (player.getPieces().size() == 0) {
                player.setHasLost(true);
            }
            if (totalPieces == 2) {
                stalemate();
            }

//            System.out.println(totalPieces);
            if (player.isHasLost()) {
                gameover();
            }
        }
    }

    public Player getOpponent(Player ownPlayer) {
        for (Player player : getPlayers()) {
            if (!ownPlayer.equals(player)) {
                return player;
            }
        }
        return null;
    }

    private void turnHandler() {

    }

    private void movePiece() {


        Box startBox = clickedBoxes.get(0);
        Box endBox = clickedBoxes.get(1);
        int newBoxId = endBox.getBoxId();

        if (checkIsTurn(startBox.getPiece().getPlayer())) {

            if (startBox.getPiece().validateMove(startBox, endBox)) {//checks if starting box has piece in it and if it makes a validmove


                if (endBox.getPiece() != null && startBox.getPiece().checkForCapture()) {//checks if endpoint has a piece in it + capture
                    removePiece();
                    endBox.unSetPiece();
                }

                startBox.getPiece().setBoxId(newBoxId);
                startBox.getPiece().countingMovement();
                clickedBoxes.get(1).setPiece(startBox.getPiece());
                System.out.println(clickedBoxes.get(1).toString());
                startBox.unSetPiece();
                checksGameStateAfterTurn();
                FENParser.printFENArrayValue();
                endTurn();


            }

        }

    }


    private void endTurn() {

        for (Player player : getPlayers()) {
            player.setTurn(!player.getIsTurn());
        }
    }

    private boolean checkIsTurn(Player player) {
        return player.getIsTurn();
    }


    public void removePiece() {
        //checks piece for color

        clickedBoxes.get(1).getPiece().getPlayer().removePiece(clickedBoxes.get(1).getPiece());
        clickedBoxes.get(1).unSetPiece();


    }


    public ArrayList<Box> getClickedBoxes() {
        return clickedBoxes;
    }


    public void setWinner(String winner) {
        this.winner = winner;
    }

    public void gameover() {


        for (Player player : getPlayers()) {


            if (!player.isHasLost()) {
                setWinner(player.toString());
            }
        }
        State.setCurrentState(gameOverState);
        setup();

    }

    public Game getGame() {
        return this;
    }

    public String getWinner() {
        return winner;
    }


    public GameState getGameState() {
        return gameState;
    }
}
