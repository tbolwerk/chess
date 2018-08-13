import processing.core.PImage;

import java.util.ArrayList;

public class King extends Piece {
    private int y;

    public King(Game game, Player player, boolean isWhite, int boxId) {
        super(game, player, isWhite, boxId);
        drawPiece();
    }

    public boolean isCheck(Box endBox) {
        for (Piece piece : game.getOpponent(player).getPieces()) {
            if (piece.posibleMoves().contains(endBox)) {
                return true;
            }
        }

        return false;
    }

    public boolean movementOfKing(Box startBox, Box endBox) {
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


    @Override
    public boolean validateMove(Box startBox, Box endBox) {
        if (movementOfKing(startBox, endBox) && !isCheck(endBox)) {
            return true;
        }
        return false;

    }

    @Override
    public int valueOfPiece() {
        return 6;
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
    public ArrayList<Box> posibleMoves() {
        ArrayList<Box> posibleMoves = new ArrayList<>();
        for (Box box : Board.getGrid()) {
            if (this.movementOfKing(this.getBox(), box)) {
                posibleMoves.add(box);
            }
        }
        return posibleMoves;
    }

    @Override
    public String toString() {
        return "King";
    }
}
