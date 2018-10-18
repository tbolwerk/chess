package TPN.pieces;

import TPN.Game;
import TPN.board.Box;
import TPN.moves.Directions;
import TPN.players.Player;
import processing.core.PImage;

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

    public boolean posibleCaptureMove(Box startBox, Box endBox) {
        int xDif = Math.abs(startBox.getCol() - endBox.getCol());
        int yDif = Math.abs(startBox.getRow() - endBox.getRow());

        if (movesDiagonal(startBox, endBox) && yDif == 1 && xDif == 1) {
            return true;
        }
        return false;
    }

    private boolean captureMove(Box startBox, Box endBox) {


        if (endBox.getPiece() != null && posibleCaptureMove(startBox, endBox)) {
            return true;
        }
        return false;

    }


    @Override
    public boolean validateMove(Box startBox, Box endBox) {
        if (endBox.getPiece() != null && startBox.getPiece().getIsWhite() == endBox.getPiece().getIsWhite()) {
            return false;
        }
        int xDif = Math.abs(startBox.getCol() - endBox.getCol());
        int yDif = Math.abs(startBox.getRow() - endBox.getRow());
        if (verticalDirections(startBox, endBox) == Directions.UP && !startBox.getPiece().getIsWhite()) {
            return false;
        } else if (verticalDirections(startBox, endBox) == Directions.DOWN && startBox.getPiece().getIsWhite()) {
            return false;
        }

        if (endBox.getPiece() != null && yDif > 1) {
            return false;


        } else if (endBox.getPiece() == null && yDif == 2 && countMovement == 0 && xDif == 0) {
            //checks for pieces in path
            for (Box box : game.getGameState().getBoard().straightPath(startBox, endBox)) {
                if (box.getPiece() != null) {
                    return false;
                }
            }
            return true;
        } else if (endBox.getPiece() == null && yDif == 1 && xDif == 0) {

            return true;
        }
        return captureMove(startBox, endBox);

    }

    @Override
    public int valueOfPiece() {
        return 1;
    }


    public void drawPiece() {
        PImage pieceImage = game.getGameState().getImageLoader().getImage((2000 / 6) * 5, y, 2000 / 6, 667 / 2);
        super.setPieceImage(pieceImage);
        super.setPiece('P');
    }

    public boolean readyForPromotion() {
        if (this.getBox().getRow() == 0 || this.getBox().getRow() == 7) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Pawn";
    }
}
