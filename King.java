import processing.core.PImage;

import java.util.ArrayList;

public class King extends Piece {
    private int y;

    public King(Game game, Player player, boolean isWhite, int boxId) {
        super(game, player, isWhite, boxId);
        drawPiece();
    }


    @Override
    public boolean validateMove(Box startBox, Box endBox) {
        if (endBox.getPiece() != null && startBox.getPiece().getIsWhite() == endBox.getPiece().getIsWhite()) {
            return false;
        }


        if (endBox.getPiece() != null && startBox.getPiece().getIsWhite() == endBox.getPiece().getIsWhite()) {
            return false;
        }
        int xStart = startBox.getCol();
        int yStart = startBox.getRow();
        int xEnd = endBox.getCol();
        int yEnd = endBox.getRow();
        int xDif = Math.abs(xStart - xEnd);
        int yDif = Math.abs(yStart - yEnd);
        if (xDif > 1 || yDif > 1) {

            return false;
        }
        return true;
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
