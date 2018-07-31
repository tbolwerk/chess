import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;


public class Game extends PApplet {
    final private static float GAMEWIDTH = 700;
    final private static float GAMEHEIGHT = GAMEWIDTH;

    private ArrayList<Box> clickedBoxes = new ArrayList<Box>();

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

    public void setup() {
        background(255);
        board = new Board(this);
        board.initGrid();
        imageLoader = new ImageLoader(this, "pieces/chess_pieces.png");
        player = new Player(this, 125, true);
        player2 = new Player(this, 75, false);

    }

    public void draw() {
        board.drawGrid();

        player.drawPieces();
        player2.drawPieces();
    }

    @Override
    public void mousePressed() {

        for (Box box : Board.getGrid()) {
            if (board.boxSelected(box)) {
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
            if (box.getPiece() == null) {
                newBoxId = box.getBoxId();
            }
        }
        for (Box box : clickedBoxes) {
            if (box.getPiece() != null) {
                box.getPiece().setBoxId(newBoxId);
                box.unSetPiece();
            }
        }
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }
}
