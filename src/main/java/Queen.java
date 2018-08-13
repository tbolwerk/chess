import processing.core.PImage;

import java.util.ArrayList;

@SuppressWarnings("ALL")
public class Queen extends Piece {
    private int y;

    public Queen(Game game, Player player, boolean isWhite, int boxId) {
        super(game, player, isWhite, boxId);
        drawPiece();
    }


    @Override
    public boolean validateMove(Box startBox, Box endBox) {

        int column = Math.abs(startBox.getCol() - endBox.getCol());
        int row = Math.abs(startBox.getRow() - endBox.getRow());


        if (endBox.getPiece() != null && startBox.getPiece().getIsWhite() == endBox.getPiece().getIsWhite()) {
            return false;
        }

        if (movesVertical(startBox, endBox)) {
            for (Box box : game.getBoard().straightPath(startBox, endBox)) {
                if (box.getPiece() != null) {
                    return false;
                }
            }
            return true;
        } else if (movesHorizontal(startBox, endBox)) {
            for (Box box : game.getBoard().straightPath(startBox, endBox)) {
                if (box.getPiece() != null) {
                    return false;
                }
            }
            return true;
        } else if (movesDiagonal(startBox, endBox)) {
            for (Box box : game.getBoard().diagonalPath(startBox, endBox)) {
                if (box.getPiece() != null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public int valueOfPiece() {
        return 5;
    }


    public void drawPiece() {
        if (!getIsWhite()) {
            y = 667 / 2;
        } else {
            y = 0;
        }
        PImage pieceImage = game.getImageLoader().getImage(2000 / 6, y, 2000 / 6, 667 / 2);
        super.setPieceImage(pieceImage);
        super.setPiece('Q');
    }

    @Override
    public String toString() {
        return "Queen";
    }
}
