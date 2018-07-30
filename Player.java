import java.util.ArrayList;

public class Player {
    final int MAXPIECES = 16;
    private int color;
    private ArrayList<Piece> pieces = new ArrayList<Piece>();
    private Game game;
    private float x = 50;
    private float y = 50;

    public Player(Game game, int color) {
        this.game = game;
        this.color = color;
        if (color > 0) {
            y = Game.getGAMEHEIGHT() / 8 * 7;
        }
        initPieces();
    }

    private void initPieces() {

        for (int i = 0; i < MAXPIECES; i++) {
            pieces.add(new Pawn(game, color, i));
        }

    }

    public void drawPieces() {
        for (Box box : Board.getGrid()) {
            for (Piece piece : pieces) {
                box.setPiece(piece);
            }
        }
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }
}
