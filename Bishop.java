import processing.core.PImage;

import java.util.ArrayList;

public class Bishop extends Piece {
    private int y;

    public Bishop(Game game, Player player, boolean isWhite, int boxId) {
        super(game, player, isWhite, boxId);
        drawPiece();
    }

    @Override
    public boolean checkForCapture() {
        return false;
    }


    @Override
    public boolean validateMove() {
        ArrayList<Box> clickedBoxes = game.getClickedBoxes();
        int column = Math.abs(clickedBoxes.get(0).getCol() - clickedBoxes.get(1).getCol());
        int row = Math.abs(clickedBoxes.get(0).getRow() - clickedBoxes.get(1).getRow());
        System.out.println(verticalDirections() + " " + horizontalDirections());
        if (!clickedBoxes.get(0).getPiece().getPlayer().getIsTurn()) {
            return false;
        }

        if (row == column) {
            for (Box box : game.getBoard().diagonalPath(clickedBoxes.get(0), clickedBoxes.get(1))) {
                if (box.getPiece() != null) {

                    System.out.println(box.getPiece().toString());
                    return false;
                }
            }
            if (clickedBoxes.get(1).getPiece() != null && clickedBoxes.get(0).getPiece().getIsWhite() == clickedBoxes.get(1).getPiece().getIsWhite()) {
                return false;
            }
            if (clickedBoxes.get(1).getPiece() != null && clickedBoxes.get(0).getPiece().getIsWhite() != clickedBoxes.get(1).getPiece().getIsWhite()) {
                game.removePiece();
                return true;
            }
            return true;
        }
        return false;
    }


    public void drawPiece() {
        if (!getIsWhite()) {
            y = 667 / 2;
        } else {
            y = 0;
        }
        PImage pieceImage = game.getImageLoader().getImage((2000 / 6) * 2, y, 2000 / 6, 667 / 2);
        super.setPieceImage(pieceImage);
        setPiece('B');
    }

    @Override
    public String toString() {
        return "Bischop";
    }
}
