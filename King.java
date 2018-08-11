import processing.core.PImage;

import java.util.ArrayList;

public class King extends Piece {
    private int y;

    public King(Game game, Player player, boolean isWhite, int boxId) {
        super(game, player, isWhite, boxId);
        drawPiece();
    }

    @Override
    public boolean checkForCapture() {
        ArrayList<Box> clickedBoxes = game.getClickedBoxes();
        if (clickedBoxes.get(1).getPiece() != null && clickedBoxes.get(1).getPiece().getIsWhite() != clickedBoxes.get(0).getPiece().getIsWhite()) {
            game.removePiece();
            return true;
        }
        return true;
    }


    @Override
    public boolean validateMove() {
        ArrayList<Box> clickedBoxes = game.getClickedBoxes();
        if (!clickedBoxes.get(0).getPiece().getPlayer().getIsTurn()) {
            return false;
        }
        if (clickedBoxes.get(1).getPiece() != null && clickedBoxes.get(0).getPiece().getIsWhite() == clickedBoxes.get(1).getPiece().getIsWhite()) {
            return false;
        }
        int xStart = clickedBoxes.get(0).getCol();
        int yStart = clickedBoxes.get(0).getRow();
        int xEnd = clickedBoxes.get(1).getCol();
        int yEnd = clickedBoxes.get(1).getRow();
        int xDif = Math.abs(xStart - xEnd);
        int yDif = Math.abs(yStart - yEnd);
        if (xDif > 1 || yDif > 1) {

            return false;
        }
        return checkForCapture();
    }


    public void drawPiece() {
        if (!getIsWhite()) {
            y = 667 / 2;
        } else {
            y = 0;
        }
        PImage pieceImage = game.getImageLoader().getImage(0, y, 2000 / 6, 667 / 2);
        super.setPieceImage(pieceImage);
        super.setPiece('K');

    }

    @Override
    public String toString() {
        return "King";
    }
}
