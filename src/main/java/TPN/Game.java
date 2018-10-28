package TPN;

import TPN.board.Board;
import TPN.board.Box;
import TPN.moves.FENParser;
import TPN.moves.Move;
import TPN.moves.MoveParser;
import TPN.pieces.King;
import TPN.pieces.Pawn;
import TPN.pieces.Piece;
import TPN.players.Computer;
import TPN.players.Player;
import TPN.players.StockFishAI;
import TPN.states.*;
import TPN.ui.Button;
import TPN.ui.Main;
import processing.core.PApplet;
import processing.core.PImage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;


public class Game extends PApplet{
    final private static float GAMEWIDTH = 600;
    final private static float GAMEHEIGHT = GAMEWIDTH;

    private static ArrayList<Player> players = new ArrayList<Player>();

    private static PImage background;
    private static PImage settingsImage;

    private static PImage stockFishWhite;
    private static PImage stockFishBlack;
    private static PImage humanWhite;
    private static PImage humanBlack;

    private GameState gameState = new GameState(this);
    private GameOverState gameOverState = new GameOverState(this);
    private MenuState menuState = new MenuState(this);
    private SettingsState settingsState = new SettingsState(this);

    private State state;


    private ArrayList<Box> clickedBoxes = new ArrayList<Box>();


    private String winner;


    public static void main(String args[]) {
        PApplet.main("TPN.Game");

        try {
            Main.main(args);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


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

    public static PImage getStockFishWhite() {
        return stockFishWhite;
    }

    public static PImage getStockFishBlack() {
        return stockFishBlack;
    }

    public static PImage getHumanBlack() {
        return humanBlack;
    }

    public static PImage getHumanWhite() {
        return humanWhite;
    }

    public void settings() {
        size((int) GAMEWIDTH, (int) GAMEHEIGHT);
    }


    public void draw() {
        State.getCurrentState().draw();
    }



    public void setup() {
        background = loadImage("background.jpeg");
        background.resize((int)getGAMEWIDTH(),(int)getGAMEHEIGHT());
        settingsImage = loadImage("settings.jpg");
        settingsImage.resize((int)getGAMEWIDTH(),(int)getGAMEHEIGHT());
        stockFishBlack = loadImage("stockfish_black.jpg");
        stockFishWhite = loadImage("stockfish_white.jpg");
        humanBlack = loadImage("human_black.png");
        humanWhite = loadImage("human_white.png");
        frameRate(240);
        if (State.getCurrentState() == null) {

            State.setCurrentState(menuState);
            Game.getPlayers().clear();
            Game.getPlayers().add(0, new Computer(this, 0, false));
            Game.getPlayers().add(1, new Computer(this, 255, true));
            State.getCurrentState().setup();

        }
    }
    @Override
    public void keyPressed(){
        State.getCurrentState().keyPressed();
    }

    @Override
    public void mouseClicked() {
        State.getCurrentState().mouseClicked();
    }


    public void keyboardPressed() {

    }

    @Override
    public void mousePressed() {

        State.getCurrentState().mousePressed();

    }

    public void showOptions() {
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

    public void checksGameStateAfterTurn() {
        int totalPieces = 0;
        for (Player player : Game.getPlayers()) {
            for (Piece piece : player.getPieces()) {
                if (piece instanceof King) {
                    if (((King) piece).isCheckMate()) {
//                        gameover();
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

    public static Player getOpponent(Player ownPlayer) {
        for (Player player : getPlayers()) {
            if (!ownPlayer.equals(player)) {
                return player;
            }
        }
        return null;
    }



    public void movePiece() {


        Box startBox = clickedBoxes.get(0);
        Box endBox = clickedBoxes.get(1);
        Move move = new Move(this,startBox,endBox);
        move.makeMove(startBox,endBox);

    }


    public void endTurn() {

        for (Player player : getPlayers()) {
            player.setTurn(!player.getIsTurn());
        }
    }

    public boolean checkIsTurn(Player player) {
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

    public static PImage getBackground() {
        return background;
    }

    public static PImage getSettingsImage() {
        return settingsImage;
    }


    public SettingsState getSettingsState() {
        return settingsState;
    }

    public MenuState getMenuState() {
        return menuState;
    }
}
