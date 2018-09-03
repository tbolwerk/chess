import processing.core.PImage;

import java.util.ArrayList;

public class Rook extends Piece {
    private int y;

    public Rook(Game game, Player player, boolean isWhite, int boxId) {
        super(game, player, isWhite, boxId);
        drawPiece();
    }




    @Override
    public boolean validateMove(Box startBox, Box endBox) {
        if (endBox.getPiece() != null && startBox.getPiece().getIsWhite() == endBox.getPiece().getIsWhite()) {
            return false;
        }
        return validPath(startBox, endBox) && movesHorizontal(startBox, endBox) || validPath(startBox, endBox) && movesVertical(startBox, endBox);
    }

    @Override
    public int valueOfPiece() {
        return 3;
    }


    public void drawPiece() {
        if (!getIsWhite()) {
            y = 667 / 2;
        } else {
            y = 0;
        }
        PImage pieceImage = game.getGameState().getImageLoader().getImage((2000 / 6) * 4, y, 2000 / 6, 667 / 2);
        super.setPieceImage(pieceImage);
        super.setPiece('R');
    }

    @Override
    public String toString() {
        return "Rook";
    }
}
