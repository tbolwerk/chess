import processing.core.PImage;

public class Pawn extends Piece {
    private int y;

    public Pawn(Game game, boolean isWhite, int boxId) {
        super(game, isWhite, boxId);
        if (!getIsWhite()) {
            y = 667 / 2;
        } else {
            y = 0;
        }
        drawPiece();
    }

    public void drawPiece() {

        PImage pieceImage = game.getImageLoader().getImage((2000 / 6) * 5, y, 2000 / 6, 667 / 2);
        super.setPieceImage(pieceImage);
        super.setPiece('P');
    }


    @Override
    public String toString() {
        return "Pawn";
    }
}
