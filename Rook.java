import processing.core.PImage;

public class Rook extends Piece {
    private int y;

    public Rook(Game game, boolean isWhite, int boxId) {
        super(game, isWhite, boxId);
        drawPiece();
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
