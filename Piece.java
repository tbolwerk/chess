public abstract class Piece {
    private Game game;
    private int color;
    private char pieceName = 'p';
    private int boxId;

    public Piece(Game game, int color, int boxId) {
        this.game = game;
        this.color = color;
        this.boxId = boxId;

    }

    public void drawPiece(float x, float y) {
        game.fill(color);
        game.textSize(Game.getGAMEHEIGHT() / 8);
        game.text(pieceName, x, y);
    }

    protected void setPiece(char letter) {
        pieceName = letter;
    }

    public int getBoxId() {
        return boxId;
    }

    public void setBoxId(int boxId) {
        this.boxId = boxId;
    }
}
