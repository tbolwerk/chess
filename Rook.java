import processing.core.PImage;

import java.util.ArrayList;

public class Rook extends Piece {
    private int y;

    public Rook(Game game, Player player, boolean isWhite, int boxId) {
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

        //checks for turn
        if (!clickedBoxes.get(0).getPiece().getPlayer().getIsTurn() || clickedBoxes.get(1).getPiece() != null && clickedBoxes.get(0).getPiece().getIsWhite() == clickedBoxes.get(1).getPiece().getIsWhite()) {
            return false;
        }

        //checks if moved left or right
        if (clickedBoxes.get(0).getCol() == clickedBoxes.get(1).getCol() && clickedBoxes.get(0).getRow() != clickedBoxes.get(1).getRow()) {
            //checks for pieces in path
//            System.out.println(Board.path(clickedBoxes.get(0),clickedBoxes.get(1)).size());
            for (Box box : game.getBoard().straightPath(clickedBoxes.get(0), clickedBoxes.get(1))) {
                if (box.getPiece() != null) {
//                System.out.println(box.getPiece().toString());
                    return false;
                }
            }
            //checks for capture
            if (clickedBoxes.get(1).getPiece() != null && clickedBoxes.get(0).getPiece().getIsWhite() != clickedBoxes.get(1).getPiece().getIsWhite()) {
                game.removePiece();
                return true;
            }
            return true;
            //checks if moved up or down
        } else if (clickedBoxes.get(0).getCol() != clickedBoxes.get(1).getCol() && clickedBoxes.get(0).getRow() == clickedBoxes.get(1).getRow()) {
            //checks for pieces in path
            for (Box box : game.getBoard().straightPath(clickedBoxes.get(0), clickedBoxes.get(1))) {
                if (box.getPiece() != null) {
                    return false;
                }
            }
            //checks for capture
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
        PImage pieceImage = game.getImageLoader().getImage((2000 / 6) * 4, y, 2000 / 6, 667 / 2);
        super.setPieceImage(pieceImage);
        super.setPiece('R');
    }

    @Override
    public String toString() {
        return "Rook";
    }
}
