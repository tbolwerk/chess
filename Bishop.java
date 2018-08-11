import processing.core.PImage;

import java.util.ArrayList;

public class Bishop extends Piece {
    private int y;

    public Bishop(Game game, Player player, boolean isWhite, int boxId) {
        super(game, player, isWhite, boxId);
        drawPiece();
    }


    @Override
    public boolean validateMove(Box startBox, Box endBox) {
        ArrayList<Box> clickedBoxes = game.getClickedBoxes();
        int column = Math.abs(startBox.getCol() - endBox.getCol());
        int row = Math.abs(startBox.getRow() - endBox.getRow());
        if (endBox.getPiece() != null && startBox.getPiece().getIsWhite() == endBox.getPiece().getIsWhite()) {
            return false;
        }


        if (row == column) {
            for (Box box : game.getBoard().diagonalPath(clickedBoxes.get(0), endBox)) {
                if (box.getPiece() != null) {

                    System.out.println(box.getPiece().toString());
                    return false;
                }
            }
            if (endBox.getPiece() != null && clickedBoxes.get(0).getPiece().getIsWhite() == endBox.getPiece().getIsWhite()) {
                return false;
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
