import processing.core.PImage;

public class Bishop extends Piece {
    private int y;

    public Bishop(Game game, boolean isWhite, int boxId) {
        super(game, isWhite, boxId);
        drawPiece();
    }


    public void drawPiece() {
        if (!getIsWhite()) {
            y = 667 / 2;
        } else {
            y = 0;
        }
        PImage pieceImage = game.getImageLoader().getImage((2000 / 6) * 2, y, 2000 / 6, 667 / 2);
        super.setPieceImage(pieceImage);
        setPiece('B');
    }

    @Override
    public String toString() {
        return "Bischop";
    }
}
