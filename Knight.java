import processing.core.PImage;

public class Knight extends Piece {
    private int y;

    public Knight(Game game, Player player, boolean isWhite, int boxId) {
        super(game, player, isWhite, boxId);
        drawPiece();
    }

    @Override
    public boolean checkForCapture() {
        return false;
    }


    @Override
    public boolean validateMove() {
        return false;
    }


    public void drawPiece() {
        if (!getIsWhite()) {
            y = 667 / 2;
        } else {
            y = 0;
        }
        PImage pieceImage = game.getImageLoader().getImage((2000 / 6) * 3, y, 2000 / 6, 667 / 2);
        super.setPieceImage(pieceImage);
        super.setPiece('H');

    }

    @Override
    public String toString() {
        return "Knight";
    }
}
