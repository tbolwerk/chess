import processing.core.PImage;

import java.util.ArrayList;

public class Pawn extends Piece {
    private int y;

    public Pawn(Game game, Player player, boolean isWhite, int boxId) {
        super(game, player, isWhite, boxId);
        if (!getIsWhite()) {
            y = 667 / 2;
        } else {
            y = 0;
        }
        drawPiece();
    }


    @Override
    public boolean validateMove(Box startBox, Box endBox) {
        if (endBox.getPiece() != null && startBox.getPiece().getIsWhite() == endBox.getPiece().getIsWhite()) {
            return false;
        }


        //checks movement pawn
        if (!startBox.getPiece().getIsWhite() && startBox.getPiece() instanceof Pawn) {

            if (startBox.getBoxId() < endBox.getBoxId() && endBox.getBoxId() == startBox.getBoxId() + 16 && startBox.getBoxId() < 16 && endBox.getPiece() == null) {
                return true;
            }
            if (startBox.getBoxId() < endBox.getBoxId() && endBox.getBoxId() == startBox.getBoxId() + 8 && endBox.getPiece() == null) {

                return true;
            }
            if (startBox.getBoxId() < endBox.getBoxId() && endBox.getBoxId() == startBox.getBoxId() + 7 && endBox.getPiece() != null) {

                return true;
            }
            if (startBox.getBoxId() < endBox.getBoxId() && endBox.getBoxId() == startBox.getBoxId() + 9 && endBox.getPiece() != null) {

                return true;
            }
        }
        if (startBox.getPiece().getIsWhite() && startBox.getPiece() instanceof Pawn) {
            if (startBox.getBoxId() > endBox.getBoxId() && endBox.getBoxId() == startBox.getBoxId() - 16 && startBox.getBoxId() > 45 && endBox.getPiece() == null) {
                return true;
            }
            if (startBox.getBoxId() > endBox.getBoxId() && endBox.getBoxId() == startBox.getBoxId() - 8 && endBox.getPiece() == null) {

                return true;
            }
            if (startBox.getBoxId() > endBox.getBoxId() && endBox.getBoxId() == startBox.getBoxId() - 7 && endBox.getPiece() != null) {

                return true;
            }
            if (startBox.getBoxId() > endBox.getBoxId() && endBox.getBoxId() == startBox.getBoxId() - 9 && endBox.getPiece() != null) {

                return true;
            }
        }

        return false;
    }

    public void drawPiece() {

        PImage pieceImage = game.getImageLoader().getImage((2000 / 6) * 5, y, 2000 / 6, 667 / 2);
        super.setPieceImage(pieceImage);
        super.setPiece('P');
    }


    @Override
    public String toString() {
        return "Pawn";
    }
}
