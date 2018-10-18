package TPN.pieces;

import TPN.Game;
import TPN.board.Board;
import TPN.board.Box;
import TPN.moves.Directions;
import TPN.players.Player;
import processing.core.PImage;

import java.util.ArrayList;

public class King extends Piece {
    private int y;

    public King(Game game, Player player, boolean isWhite, int boxId) {
        super(game, player, isWhite, boxId);
        drawPiece();
    }

    public boolean isCheckMate() {
        return posibleMoves().size() == 0 && isCheck(this.getBox());
    }

    public boolean isCheck(Box endBox) {

        for (Piece piece : game.getOpponent(player).getPieces()) {
            if (piece instanceof King) {
                if (((King) piece).availableMoves().contains(endBox)) {
                    return true;

                }
            } else if (piece.posibleMoves().contains(endBox)) {

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
        return movementOfKing(startBox, endBox) && !isCheck(endBox);

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
        PImage pieceImage = game.getGameState().getImageLoader().getImage(0, y, 2000 / 6, 667 / 2);
        super.setPieceImage(pieceImage);
        super.setPiece('K');

    }

    @Override
    public ArrayList<Box> posibleMoves() {
        ArrayList<Box> posibleMoves = new ArrayList<>();
        for (Box box : Board.getGrid()) {
            if (validateMove(getBox(), box)) {
                posibleMoves.add(box);
            }
        }
        return posibleMoves;
    }

    private ArrayList<Box> availableMoves() {
        ArrayList<Box> availableMoves = new ArrayList<>();
        for (Box box : Board.getGrid()) {
            if (movementOfKing(getBox(), box)) {
                availableMoves.add(box);
            }
        }
        return availableMoves;
    }

    public boolean isAbleToCastle() {
        //TO-DO making it more efficient by breaking up this code
        //if rook goes right king goes left 2 boxes always ends on the same coloured box!
        Box firstBox;
        Box secondBox;
        if (game.getClickedBoxes().size() > 1) {
            firstBox = game.getClickedBoxes().get(0);
            secondBox = game.getClickedBoxes().get(1);
            if (game.getClickedBoxes().get(0) != null && game.getClickedBoxes().get(1).getPiece() != null) {
                if (game.getClickedBoxes().get(0).getPiece() instanceof Rook && game.getClickedBoxes().get(1).getPiece() instanceof King || game.getClickedBoxes().get(0).getPiece() instanceof King && game.getClickedBoxes().get(1).getPiece() instanceof Rook) {
                    if (game.getClickedBoxes().get(0).getPiece() instanceof Rook && game.getClickedBoxes().get(0).getPiece().validPath(firstBox, secondBox) || secondBox.getPiece().validPath(firstBox, secondBox)) {
                        if (firstBox.getPiece().countMovement == 0 && secondBox.getPiece().countMovement == 0) {
                            if (firstBox.getPiece() instanceof Rook && firstBox.getPiece().horizontalDirections(firstBox, secondBox) == Directions.RIGHT)
                                return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "King";
    }


}
