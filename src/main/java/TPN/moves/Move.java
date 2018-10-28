package TPN.moves;

import TPN.Game;

import TPN.board.Box;
import TPN.pieces.*;
import TPN.players.Player;
import TPN.states.GameState;


public class Move {
    private Box startBox, endBox;
    private Piece startPiece, endPiece;
    private Game game;

    public Move(Game game, Box startBox, Box endBox) {
        this.startBox = startBox;
        this.endBox = endBox;
        this.game = game;


        if (boxWithPiece(startBox)) {
            this.startPiece = startBox.getPiece();
        }
    }

    private boolean boxWithPiece(Box box) {
        return box.getPiece() != null;
    }
    private Player movingPlayer(){
        return startPiece.getPlayer();
    }

    private King kingFromPlayer(){
       return movingPlayer().getKing();
    }

    private boolean validMove(){
       return true;
    }

    public Box getStartBox() {
        return startBox;
    }

    public Box getEndBox() {
        return endBox;
    }

//    @SuppressWarnings("Duplicates")
    public void makeMove(Box startBox, Box endBox) {
        int newBoxId = endBox.getBoxId();


        if (game.checkIsTurn(startBox.getPiece().getPlayer())) {

            if (startBox.getPiece().validateMove(startBox, endBox) && validMove()) {//checks if starting box has piece in it and if it makes a validmove
                System.out.println("Evaluation score of " + startBox.getPiece().getPlayer().toString() + ": " + startBox.getPiece().getPlayer().getStockFish().getEvalScore(MoveParser.getLastMove(), 100));

                GameState.setHalfCountMoves(GameState.getCountHalfMoves() + 1);
                if (startBox.getPiece() instanceof Pawn) {
                    if (endBox.getFENNotationRow() == 5 && startBox.getPiece().getCountMovement() == 0 || endBox.getFENNotationRow() == 4 && startBox.getPiece().getCountMovement() == 0) {
                        FENParser.setEnPassantFENPosistion(" " + ((Pawn) startBox.getPiece()).getEnPassant());
                    } else {
                        FENParser.setEnPassantFENPosistion(" -");

                    }
                    GameState.setHalfCountMoves(0);
                } else {
                    FENParser.setEnPassantFENPosistion(" -");

                }
                if (endBox.getPiece() != null && startBox.getPiece().checkForCapture()) {//checks if endpoint has a piece in it + capture
                    game.removePiece();
                    endBox.unSetPiece();
                    GameState.setHalfCountMoves(0);
                }

                startBox.getPiece().countingMovement();
                startBox.getPiece().setBoxId(newBoxId);
                endBox.setPiece(startBox.getPiece());
                startBox.unSetPiece();

                MoveParser.setEndpos(endBox.toString());
                MoveParser.setStartpos(startBox.toString());

                game.checksGameStateAfterTurn();
                game.endTurn();
                GameState.setCountMoves(1);
                System.out.println(MoveParser.getLastMove());

            }

        }
    }
}