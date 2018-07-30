public class Pawn extends Piece {
    public Pawn(Game game, int color, int boxId) {
        super(game, color, boxId);
        drawPiece();
    }

    public void drawPiece() {
        super.setPiece('P');
    }
}
