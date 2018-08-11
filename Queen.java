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
    public boolean checkForCapture() {
        ArrayList<Box> clickedBoxes = game.getClickedBoxes();
        if (clickedBoxes.get(1).getPiece() != null && clickedBoxes.get(1).getPiece().getIsWhite() != clickedBoxes.get(0).getPiece().getIsWhite()) {
            game.removePiece();
            return true;
        }
        return true;

    }


    @Override
    public boolean validateMove() {
        ArrayList<Box> clickedBoxes = game.getClickedBoxes();
        int column = Math.abs(clickedBoxes.get(0).getCol() - clickedBoxes.get(1).getCol());
        int row = Math.abs(clickedBoxes.get(0).getRow() - clickedBoxes.get(1).getRow());
        if (!clickedBoxes.get(0).getPiece().getPlayer().getIsTurn()) {
            return false;
        }
        if (clickedBoxes.get(1).getPiece() != null && clickedBoxes.get(0).getPiece().getIsWhite() == clickedBoxes.get(1).getPiece().getIsWhite()) {
            return false;
        }

        if (verticalDirections() != null) {
            if (horizontalDirections() == null) {
                if (verticalDirections() == Directions.UP) {

                    for (Box box : game.getBoard().straightPath(clickedBoxes.get(0), clickedBoxes.get(1))) {
                        if (box.getPiece() != null) {
                            return false;
                        }
                    }
                    return checkForCapture();
                    //up is true

                } else if (verticalDirections() == Directions.DOWN) {

                    for (Box box : game.getBoard().straightPath(clickedBoxes.get(0), clickedBoxes.get(1))) {
                        if (box.getPiece() != null) {
                            return false;
                        }
                    }
                    //down is true
                    return checkForCapture();

                }
            } else if (horizontalDirections() != null && horizontalDirections() == Directions.LEFT) {
                for (Box box : game.getBoard().diagonalPath(clickedBoxes.get(0), clickedBoxes.get(1))) {
                    if (box.getPiece() != null) {
                        return false;
                    }
                }
                return checkForCapture();
            } else if (horizontalDirections() != null && horizontalDirections() == Directions.RIGHT) {
                for (Box box : game.getBoard().diagonalPath(clickedBoxes.get(0), clickedBoxes.get(1))) {
                    if (box.getPiece() != null) {
                        return false;
                    }
                }
                return checkForCapture();
            }

        } else if (verticalDirections() == null) {
            if (horizontalDirections() != null) {
                if (horizontalDirections() == Directions.LEFT) {
                    for (Box box : game.getBoard().straightPath(clickedBoxes.get(0), clickedBoxes.get(1))) {
                        if (box.getPiece() != null) {
                            return false;
                        }
                    }
                    return checkForCapture();
                } else if (horizontalDirections() == Directions.RIGHT) {
                    for (Box box : game.getBoard().straightPath(clickedBoxes.get(0), clickedBoxes.get(1))) {
                        if (box.getPiece() != null) {
                            return false;
                        }
                    }
                    return checkForCapture();
                }

            }

        }
        return false;

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
