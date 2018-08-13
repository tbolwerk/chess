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

        //checks if moved left or right
        if (movesHorizontal(startBox, endBox)) {
            //checks for pieces in path
//            System.out.println(Board.path(startBox,endBox).size());
            for (Box box : game.getBoard().straightPath(startBox, endBox)) {
                if (box.getPiece() != null) {
//                System.out.println(box.getPiece().toString());
                    return false;
                }
            }

            return true;
            //checks if moved up or down
        } else //noinspection Duplicates
            if (movesVertical(startBox, endBox)) {
            //checks for pieces in path
            for (Box box : game.getBoard().straightPath(startBox, endBox)) {
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
        return 3;
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
