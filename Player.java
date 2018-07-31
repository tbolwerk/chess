import java.util.ArrayList;

public class Player {
    final int PAWNS = 8;
    private int color;
    private ArrayList<Piece> pieces = new ArrayList<Piece>();
    private Game game;
    private boolean isBlack;
    private boolean isWhite;


    public Player(Game game, int color, boolean isWhite) {
        this.game = game;
        this.color = color;
        if (isWhite) {
            this.isBlack = false;
            this.isWhite = true;
        } else {
            this.isBlack = true;
            this.isWhite = false;
        }
        initPieces();
    }

    private void initPieces() {
        if (isBlack) {
            for (int i = 8; i < 8 + PAWNS; i++) {
                pieces.add(new Pawn(game, false, i));
            }
            pieces.add(new Rook(game, false, 0));
            pieces.add(new Knight(game, false, 1));
            pieces.add(new Bishop(game, false, 2));
            pieces.add(new King(game, false, 3));
            pieces.add(new Queen(game, false, 4));
            pieces.add(new Bishop(game, false, 5));
            pieces.add(new Knight(game, false, 6));
            pieces.add(new Rook(game, false, 7));

        }
        if (isWhite) {
            for (int i = 48; i < 48 + PAWNS; i++) {
                pieces.add(new Pawn(game, true, i));
            }
            pieces.add(new Rook(game, true, 56));
            pieces.add(new Knight(game, true, 57));
            pieces.add(new Bishop(game, true, 58));
            pieces.add(new King(game, true, 59));
            pieces.add(new Queen(game, true, 60));
            pieces.add(new Bishop(game, true, 61));
            pieces.add(new Knight(game, true, 62));
            pieces.add(new Rook(game, true, 63));
        }

    }

    public void drawPieces() {
        for (Box box : Board.getGrid()) {
            for (Piece piece : pieces) {
                if (box.getBoxId() == piece.getBoxId())
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

    public boolean getIsBlack() {
        return isBlack;
    }

    public boolean getIsWhite() {
        return isWhite;
    }

    public void setBlack(boolean black) {
        isBlack = black;
    }

    public void setWhite(boolean white) {
        isWhite = white;
    }
}
