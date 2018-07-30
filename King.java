public class King extends Piece {
    public King(Game game, int color, int boxId) {
        super(game, color, boxId);
    }


    public void drawPiece() {
        super.setPiece('K');

    }
}
