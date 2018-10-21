package TPN.players;

import TPN.Game;
import TPN.board.Board;
import TPN.board.Box;
import TPN.pieces.King;
import TPN.pieces.Piece;

import java.util.ArrayList;
import java.util.Random;

public class Computer extends Player implements AI {

    public Computer(Game game, int color, boolean isWhite) {
        super(game, color, isWhite);
    }


    private Piece getAvailablePiece() {
        ArrayList<Piece> moveAblePieces = new ArrayList<>();
        ArrayList<Piece> bestMoveAblePieces = new ArrayList<>();
        for (Piece piece : getPieces()) {
            for (Box box : Board.getGrid()) {
                if (piece.validateMove(piece.getBox(), box)) {
                    if (piece instanceof King && ((King) piece).isCheck(piece.getBox())) {
                        return piece;
                    }

                    if (box.getPiece() != null && box.getPiece().getIsWhite() != piece.getIsWhite()) {
                        if (box.getPiece().valueOfPiece() > 4 || box.getPiece().valueOfPiece() == piece.valueOfPiece() || box.getPiece().valueOfPiece() > piece.valueOfPiece()) {
                            bestMoveAblePieces.add(piece);
                        }


                    } else {
                        moveAblePieces.add(piece);
                    }


                }
            }
        }
        Random random = new Random();

        if (bestMoveAblePieces.size() > 0) {
            int randomIndexBestMoveablePiece = random.nextInt(bestMoveAblePieces.size());
            return bestMoveAblePieces.get(Math.abs(randomIndexBestMoveablePiece));
        } else if (moveAblePieces.size() < 1) {
            return null;
        }
        int randomIndex = random.nextInt(moveAblePieces.size());
//        System.out.println(randomIndex);
        Piece moveAblePiece = moveAblePieces.get(Math.abs(randomIndex));
        return moveAblePiece;
    }


    private Box getAvailableMove(Piece moveAblePiece) {
        if (moveAblePiece == null) {
            return null;
        }

        moveAblePiece.setIsMoved(true);

        ArrayList<Box> availableMoves = new ArrayList<>();
        Random random = new Random();
        for (Box box : Board.getGrid()) {

            if (box.getPiece() != null) {
                box.getPiece().setIsGettingCaptured(true);
            }
            if (moveAblePiece.validateMove(moveAblePiece.getBox(), box)) {
                if (box.getPiece() != null && box.getPiece().valueOfPiece() >= moveAblePiece.valueOfPiece() && box.getPiece().getIsWhite() != moveAblePiece.getIsWhite() && !checksForCapture(box)) {//checks if piece gets captured when capturing
                    return box;
                } else {
                    availableMoves.add(box);
                }
            }
            if (box.getPiece() != null) {
                box.getPiece().setIsGettingCaptured(false);
            }
        }
        moveAblePiece.setIsMoved(false);
        Box availableMove = availableMoves.get(random.nextInt(availableMoves.size()));
        return availableMove;
    }

    private ArrayList<Box> makeDecision() {


        ArrayList<Box> output = new ArrayList<>();

        if (getAvailablePiece() == null || getAvailableMove(getAvailablePiece()) == null) {
            return null;
        }
        output.add(getAvailablePiece().getBox());
        output.add(getAvailableMove(getAvailablePiece()));
        return output;

    }

    @Override
    public void makeMove() {
        if (getIsTurn()) {
            if (makeDecision() == null) {
                this.setHasLost(true);
                game.gameover();

                return;
            }
            if (makeDecision().size() < 2) {
                makeDecision();
                return;
            }
            Box startBox = makeDecision().get(0);
            Box endBox = makeDecision().get(1);
            game.getClickedBoxes().add(startBox);
            game.getClickedBoxes().add(endBox);
            game.mousePressed();
        }
    }


    public boolean checksForCapture(Box endBox) {
        for (Piece piece : game.getOpponent(this).getPieces()) {
            if (piece.posibleMoves().contains(endBox)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return colorPlayer() + "Computer";
    }
}
