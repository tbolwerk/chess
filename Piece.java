import processing.core.PImage;

import java.util.ArrayList;

public abstract class Piece {

    protected Game game;
    private boolean isWhite;
    private char pieceName = 'p';
    private PImage pieceImage = null;
    private int boxId;
    private Player player;


    public Piece(Game game, Player player, boolean isWhite, int boxId) {

        this.game = game;
        this.player = player;
        this.isWhite = isWhite;
        this.boxId = boxId;


    }

    public void drawPiece(float x, float y) {
        if (pieceImage != null) {
            pieceImage.resize((int) Game.getGAMEWIDTH() / 8, (int) Game.getGAMEHEIGHT() / 8);
            game.image(pieceImage, x, y);
        }
    }

    protected void setPiece(char letter) {
        pieceName = letter;
    }

    protected void setPieceImage(PImage image) {
        pieceImage = image;
    }

    public int getBoxId() {
        return boxId;
    }

    public boolean getIsWhite() {
        return isWhite;
    }

    public void setBoxId(int boxId) {
        this.boxId = boxId;
    }

    public abstract boolean checkForCapture();

    public Enum<Directions> horizontalDirections() {
        ArrayList<Box> clickedBoxes = game.getClickedBoxes();
        int row = clickedBoxes.get(0).getRow() - clickedBoxes.get(1).getRow();
        int column = clickedBoxes.get(0).getCol() - clickedBoxes.get(1).getCol();

        if (column < 0) {
            return Directions.RIGHT;
        } else if (column > 0) {
            return Directions.LEFT;
        }

        return null;
    }


    public Enum<Directions> verticalDirections() {
        ArrayList<Box> clickedBoxes = game.getClickedBoxes();
        int column = clickedBoxes.get(0).getCol() - clickedBoxes.get(1).getCol();
        int row = clickedBoxes.get(0).getRow() - clickedBoxes.get(1).getRow();
        if (row < 0) {
            return Directions.DOWN;
        } else if (row > 0) {
            return Directions.UP;
        }
        return null;
    }

    public abstract boolean validateMove();

    public Player getPlayer() {
        return player;
    }
}
