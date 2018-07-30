public class Knight extends Piece {
    public Knight(Game game, int color, int boxId) {
        super(game, color, boxId);
    }


    public void drawPiece() {
        super.setPiece('K');

    }
}
