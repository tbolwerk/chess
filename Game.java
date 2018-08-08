import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;


public class Game extends PApplet {
    final private static float GAMEWIDTH = 700;
    final private static float GAMEHEIGHT = GAMEWIDTH;

    private static ArrayList<Player> players = new ArrayList<Player>();

    private ArrayList<Box> clickedBoxes = new ArrayList<Box>();
    private boolean turn;

    private ImageLoader imageLoader;

    private Board board;
    private Player player;
    private Player player2;


    public static void main(String args[]) {
        PApplet.main("Game");

    }

    public static float getGAMEHEIGHT() {
        return GAMEHEIGHT;
    }

    public static float getGAMEWIDTH() {
        return GAMEWIDTH;
    }

    public void settings() {
        size((int) GAMEWIDTH, (int) GAMEHEIGHT);
    }

    public static ArrayList<Player> getPlayers() {
        return players;
    }

    public void draw() {
        board.drawGrid();

        player.drawPieces();
        player2.drawPieces();
    }

    public void setup() {
        background(255);
        board = new Board(this);
        board.initGrid();
        imageLoader = new ImageLoader(this, "pieces/chess_pieces.png");
        player = new Player(this, 125, true);
        player2 = new Player(this, 75, false);
        players.add(player);
        players.add(player2);

    }

    @Override
    public void mousePressed() {

        for (Box box : Board.getGrid()) {
            if (board.boxSelected(box) && box.getPiece() != null) {
                box.setIsClicked(true);
                clickedBoxes.add(box);
            } else if (clickedBoxes.size() > 0 && board.boxSelected(box)) {
                box.setIsClicked(true);
                clickedBoxes.add(box);
            }
        }


        if (clickedBoxes.size() > 1) {
            movePiece();
            board.setAllBoxesUnclicked();
            clickedBoxes.clear();
        }

    }

    public void movePiece() {
        int newBoxId = 0;
        for (Box box : clickedBoxes) {
            newBoxId = box.getBoxId();
        }
        for (Box box : clickedBoxes) {
            if (checkIsTurn() && box.getPiece() != null && gameRules()) {
                box.getPiece().setBoxId(newBoxId);
                box.unSetPiece();
            }
        }
    }

    private boolean checkIsTurn() {
        if (getPlayers().get(0).getPieces().contains(clickedBoxes.get(0).getPiece()) && getPlayers().get(0).getIsTurn()) {
            getPlayers().get(0).setTurn(false);
            getPlayers().get(1).setTurn(true);
            return true;
        }
        if (getPlayers().get(1).getPieces().contains(clickedBoxes.get(0).getPiece()) && getPlayers().get(1).getIsTurn()) {
            getPlayers().get(0).setTurn(true);
            getPlayers().get(1).setTurn(false);
            return true;
        }
        return false;
    }

    private void removePiece() {
        //checks piece for color
        if (clickedBoxes.get(0).getPiece() != null && clickedBoxes.get(1).getPiece() != null && clickedBoxes.get(0).getPiece().getIsWhite() != clickedBoxes.get(1).getPiece().getIsWhite()) {
            if (getPlayers().get(0).getPieces().contains(clickedBoxes.get(1).getPiece())) {

                getPlayers().get(0).getPieces().remove(clickedBoxes.get(1).getPiece());
                clickedBoxes.get(1).unSetPiece();
            } else if (getPlayers().get(1).getPieces().contains(clickedBoxes.get(1).getPiece())) {

                getPlayers().get(1).getPieces().remove(clickedBoxes.get(1).getPiece());
                clickedBoxes.get(1).unSetPiece();
            }

        }
    }


    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    private boolean gameRules() {


        //checks movement pawn
        if (!clickedBoxes.get(0).getPiece().getIsWhite() && clickedBoxes.get(0).getPiece() instanceof Pawn) {
            if (clickedBoxes.get(0).getBoxId() < clickedBoxes.get(1).getBoxId() && clickedBoxes.get(1).getBoxId() == clickedBoxes.get(0).getBoxId() + 8 && clickedBoxes.get(1).getPiece() == null) {

                return true;
            }
            if (clickedBoxes.get(0).getBoxId() < clickedBoxes.get(1).getBoxId() && clickedBoxes.get(1).getBoxId() == clickedBoxes.get(0).getBoxId() + 7 && clickedBoxes.get(1).getPiece() != null) {
                removePiece();
                return true;
            }
            if (clickedBoxes.get(0).getBoxId() < clickedBoxes.get(1).getBoxId() && clickedBoxes.get(1).getBoxId() == clickedBoxes.get(0).getBoxId() + 9 && clickedBoxes.get(1).getPiece() != null) {
                removePiece();
                return true;
            }
        }
        if (clickedBoxes.get(0).getPiece().getIsWhite() && clickedBoxes.get(0).getPiece() instanceof Pawn) {
            if (clickedBoxes.get(0).getBoxId() > clickedBoxes.get(1).getBoxId() && clickedBoxes.get(1).getBoxId() == clickedBoxes.get(0).getBoxId() - 8 && clickedBoxes.get(1).getPiece() == null) {

                return true;
            }
            if (clickedBoxes.get(0).getBoxId() > clickedBoxes.get(1).getBoxId() && clickedBoxes.get(1).getBoxId() == clickedBoxes.get(0).getBoxId() - 7 && clickedBoxes.get(1).getPiece() != null) {
                removePiece();
                return true;
            }
            if (clickedBoxes.get(0).getBoxId() > clickedBoxes.get(1).getBoxId() && clickedBoxes.get(1).getBoxId() == clickedBoxes.get(0).getBoxId() - 9 && clickedBoxes.get(1).getPiece() != null) {
                removePiece();
                return true;
            }
        }

        return false;
    }
}
