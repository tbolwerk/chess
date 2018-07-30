public class Rook extends Piece {
    public Rook(Game game, int color, int boxId) {
        super(game, color, boxId);
    }


    public void drawPiece() {
        super.setPiece('R');
    }
}
