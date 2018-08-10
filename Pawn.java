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
    public Enum<Directions> horizontalDirections() {
        return null;
    }

    @Override
    public Enum<Directions> verticalDirections() {
        return null;
    }


    @Override
    public boolean validateMove() {
        ArrayList<Box> clickedBoxes = game.getClickedBoxes();

        //checks movement pawn
        if (!clickedBoxes.get(0).getPiece().getPlayer().getIsTurn())
            return false;
        if (!clickedBoxes.get(0).getPiece().getIsWhite() && clickedBoxes.get(0).getPiece() instanceof Pawn) {

            if (clickedBoxes.get(0).getBoxId() < clickedBoxes.get(1).getBoxId() && clickedBoxes.get(1).getBoxId() == clickedBoxes.get(0).getBoxId() + 16 && clickedBoxes.get(0).getBoxId() < 16 && clickedBoxes.get(1).getPiece() == null) {
                return true;
            }
            if (clickedBoxes.get(0).getBoxId() < clickedBoxes.get(1).getBoxId() && clickedBoxes.get(1).getBoxId() == clickedBoxes.get(0).getBoxId() + 8 && clickedBoxes.get(1).getPiece() == null) {

                return true;
            }
            if (clickedBoxes.get(0).getBoxId() < clickedBoxes.get(1).getBoxId() && clickedBoxes.get(1).getBoxId() == clickedBoxes.get(0).getBoxId() + 7 && clickedBoxes.get(1).getPiece() != null) {
                game.removePiece();
                return true;
            }
            if (clickedBoxes.get(0).getBoxId() < clickedBoxes.get(1).getBoxId() && clickedBoxes.get(1).getBoxId() == clickedBoxes.get(0).getBoxId() + 9 && clickedBoxes.get(1).getPiece() != null) {
                game.removePiece();
                return true;
            }
        }
        if (clickedBoxes.get(0).getPiece().getIsWhite() && clickedBoxes.get(0).getPiece() instanceof Pawn) {
            if (clickedBoxes.get(0).getBoxId() > clickedBoxes.get(1).getBoxId() && clickedBoxes.get(1).getBoxId() == clickedBoxes.get(0).getBoxId() - 16 && clickedBoxes.get(0).getBoxId() > 45 && clickedBoxes.get(1).getPiece() == null) {
                return true;
            }
            if (clickedBoxes.get(0).getBoxId() > clickedBoxes.get(1).getBoxId() && clickedBoxes.get(1).getBoxId() == clickedBoxes.get(0).getBoxId() - 8 && clickedBoxes.get(1).getPiece() == null) {

                return true;
            }
            if (clickedBoxes.get(0).getBoxId() > clickedBoxes.get(1).getBoxId() && clickedBoxes.get(1).getBoxId() == clickedBoxes.get(0).getBoxId() - 7 && clickedBoxes.get(1).getPiece() != null) {
                game.removePiece();
                return true;
            }
            if (clickedBoxes.get(0).getBoxId() > clickedBoxes.get(1).getBoxId() && clickedBoxes.get(1).getBoxId() == clickedBoxes.get(0).getBoxId() - 9 && clickedBoxes.get(1).getPiece() != null) {
                game.removePiece();
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
