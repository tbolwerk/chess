import processing.core.PImage;

public abstract class Piece {
    protected Game game;
    private boolean isWhite;
    private char pieceName = 'p';
    private PImage pieceImage = null;
    private int boxId;


    public Piece(Game game, boolean isWhite, int boxId) {
        this.game = game;
        this.isWhite = isWhite;
        this.boxId = boxId;


    }

    public void drawPiece(float x, float y) {
//        game.fill(color);
//        game.textSize(Game.getGAMEHEIGHT() / 8);
//        game.text(pieceName, x, y);

        if (pieceImage != null) {
            pieceImage.resize((int) Game.getGAMEWIDTH() / 8, (int) Game.getGAMEHEIGHT() / 8);
            game.image(pieceImage, x, y);
        }
    }

    protected void setPiece(char letter) {
        pieceName = letter;
    }

    protected void setPieceImage(PImage image) {
        pieceImage = image;
    }

    public int getBoxId() {
        return boxId;
    }

    public boolean getIsWhite() {
        return isWhite;
    }

    public void setBoxId(int boxId) {
        this.boxId = boxId;
    }


}
