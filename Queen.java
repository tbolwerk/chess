import processing.core.PImage;

public class Queen extends Piece {
    private int y;

    public Queen(Game game, boolean isWhite, int boxId) {
        super(game, isWhite, boxId);
        drawPiece();
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
