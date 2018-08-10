import processing.core.PImage;

public class King extends Piece {
    private int y;

    public King(Game game, Player player, boolean isWhite, int boxId) {
        super(game, player, isWhite, boxId);
        drawPiece();
    }

    @Override
    public Enum<Directions> horizontalDirections() {
        return null;
    }

    @Override
    public Enum<Directions> verticalDirections() {
        return null;
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
        PImage pieceImage = game.getImageLoader().getImage(0, y, 2000 / 6, 667 / 2);
        super.setPieceImage(pieceImage);
        super.setPiece('K');

    }

    @Override
    public String toString() {
        return "King";
    }
}
