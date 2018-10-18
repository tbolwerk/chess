package TPN.pieces;

import TPN.Game;
import TPN.board.Box;
import TPN.players.Player;
import processing.core.PImage;

@SuppressWarnings("ALL")
public class Queen extends Piece {
    private int y;

    public Queen(Game game, Player player, boolean isWhite, int boxId) {
        super(game, player, isWhite, boxId);
        drawPiece();
    }


    @Override
    public boolean validateMove(Box startBox, Box endBox) {


        if (endBox.getPiece() != null && startBox.getPiece().getIsWhite() == endBox.getPiece().getIsWhite()) {
            return false;
        }
        return movesVertical(startBox, endBox) && validPath(startBox, endBox) || movesDiagonal(startBox, endBox) && validPath(startBox, endBox) || movesHorizontal(startBox, endBox) && validPath(startBox, endBox);

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
        PImage pieceImage = game.getGameState().getImageLoader().getImage(2000 / 6, y, 2000 / 6, 667 / 2);
        super.setPieceImage(pieceImage);
        super.setPiece('Q');
    }

    @Override
    public String toString() {
        return "Queen";
    }
}
