public class Queen extends Piece {
    public Queen(Game game, int color, int boxId) {
        super(game, color, boxId);
    }


    public void drawPiece() {
        super.setPiece('Q');
    }
}
