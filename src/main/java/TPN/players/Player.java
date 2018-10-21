package TPN.players;

import TPN.Game;
import TPN.StockFish;
import TPN.board.Board;
import TPN.board.Box;
import TPN.moves.MoveParser;
import TPN.pieces.*;

import java.util.ArrayList;

public class Player {
    final private int PAWNS = 8;
    private int color;
    private ArrayList<Piece> pieces = new ArrayList<Piece>();
    protected Game game;
    private boolean isBlack;
    private boolean isWhite;
    private String playerName;
    private boolean isTurn;
    private boolean hasLost = false;
    protected StockFish stockFish;


    public Player(Game game, int color, boolean isWhite) {
        this.game = game;
        this.color = color;
        if (isWhite) {
            this.playerName = "White";
            this.isBlack = false;
            this.isWhite = true;
            this.isTurn = true;
        } else {
            this.playerName = "Black";
            this.isBlack = true;
            this.isWhite = false;
            this.isTurn = false;
        }
        stockFish = new StockFish();
        initStockFish();

    }

    public StockFish getStockFish() {
        return stockFish;
    }

    private void initStockFish() {
        this.stockFish.startEngine();
        this.stockFish.sendCommand("uci");
    }

    public void initPieces() {
        if (isBlack) {
            for (int i = 8; i < 8 + PAWNS; i++) {
                pieces.add(new Pawn(game, this, false, i));
            }
            pieces.add(new Rook(game, this, false, 0));
            pieces.add(new Knight(game, this, false, 1));
            pieces.add(new Bishop(game, this, false, 2));
            pieces.add(new King(game, this, false, 4));
            pieces.add(new Queen(game, this, false, 3));
            pieces.add(new Bishop(game, this, false, 5));
            pieces.add(new Knight(game, this, false, 6));
            pieces.add(new Rook(game, this, false, 7));

        }
        if (isWhite) {
            for (int i = 48; i < 48 + PAWNS; i++) {
                pieces.add(new Pawn(game, this, true, i));
            }
            pieces.add(new Rook(game, this, true, 56));
            pieces.add(new Knight(game, this, true, 57));
            pieces.add(new Bishop(game, this, true, 58));
            pieces.add(new King(game, this, true, 60));
            pieces.add(new Queen(game, this, true, 59));
            pieces.add(new Bishop(game, this, true, 61));
            pieces.add(new Knight(game, this, true, 62));
            pieces.add(new Rook(game, this, true, 63));
        }

    }


    public void drawPieces() {
        promote();

        for (Box box : Board.getGrid()) {
            for (Piece piece : pieces) {
                if (box.getBoxId() == piece.getBoxId())
                    box.setPiece(piece);
            }
        }
        if (getKing() == null || isCheckMate()) {
            hasLost = true;
        }
    }

    private boolean isCheckMate() {
        int countsPieces = 0;
        for (Piece piece : pieces) {
            if (piece.posibleMoves().size() == 0) {
                countsPieces++;
            }
        }
        return countsPieces == pieces.size();
    }

    public String getLastMoveInFenNotation() {
        return MoveParser.getLastMove();
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

    public void setTurn(boolean turn) {
        isTurn = turn;
    }

    public boolean getIsTurn() {
        return isTurn;
    }

    public void removePiece(Piece piece) {
        pieces.remove(piece);
    }

    public King getKing() {
        for (Piece piece : getPieces()) {
            if (piece instanceof King)
                return (King) piece;
        }
        return null;
    }

    public ArrayList<Pawn> getPawns() {

        ArrayList<Pawn> pawns = new ArrayList<>();
        for (Piece piece : getPieces()) {
            if (piece instanceof Pawn) {

                pawns.add((Pawn) piece);
            }

        }
        return pawns;
    }

    public void promote() {
        for (Pawn pawn : getPawns()) {
            if (pawn.readyForPromotion()) {
                getPieces().add(new Queen(game, this, pawn.getIsWhite(), pawn.getBoxId()));
                getPawns().remove(pawn);
                pawn.removePiece();
                removePiece(pawn);
            }
        }
    }

    public void castle() {
        Piece temp;
        if (getKing().isAbleToCastle()) {

        }
    }

    public void removeAllPieces() {
        for (Piece piece : pieces) {

            removePiece(piece);
            piece.getBox().unSetPiece();
        }
//        pieces.clear();
    }

    @Override
    public String toString() {
        return colorPlayer() + "Human player";
    }

    public boolean isHasLost() {
        return hasLost;
    }

    public void setHasLost(boolean hasLost) {
        this.hasLost = hasLost;
    }

    protected String colorPlayer() {
        if (isBlack) {
            return "Black ";
        } else
            return "White ";
    }

}
