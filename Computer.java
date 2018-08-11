import java.util.ArrayList;
import java.util.Random;

public class Computer extends Player implements AI {

    public Computer(Game game, int color, boolean isWhite) {
        super(game, color, isWhite);
    }


    private Piece getAvailablePiece() {
        ArrayList<Piece> moveAblePieces = new ArrayList<>();
        for (Piece piece : getPieces()) {
            for (Box box : Board.getGrid()) {
                if (piece.validateMove(piece.getBox(), box)) {

                    if (box.getPiece() != null && box.getPiece().getIsWhite() != piece.getIsWhite()) {
                        if (box.getPiece().valueOfPiece() > 4 || box.getPiece().valueOfPiece() == piece.valueOfPiece()) {
                            return piece;
                        }


                    } else {
                        moveAblePieces.add(piece);
                    }


                }
            }
        }
        Random random = new Random();
        Piece moveAblePiece = moveAblePieces.get(Math.abs(random.nextInt(moveAblePieces.size())));
        return moveAblePiece;
    }

    private Box getAvailableMove(Piece moveAblePiece) {
        ArrayList<Box> availableMoves = new ArrayList<>();
        Random random = new Random();
        for (Box box : Board.getGrid()) {
            if (moveAblePiece.validateMove(moveAblePiece.getBox(), box)) {
                if (box.getPiece() != null && box.getPiece().getIsWhite() != moveAblePiece.getIsWhite()) {
                    return box;
                } else {
                    availableMoves.add(box);
                }
            }
        }

        Box availableMove = availableMoves.get(random.nextInt(availableMoves.size()));
        return availableMove;
    }

    private ArrayList<Box> makeDecision() {


        ArrayList<Box> output = new ArrayList<>();


        output.add(getAvailablePiece().getBox());
        output.add(getAvailableMove(getAvailablePiece()));
        return output;

    }

    @Override
    public void makeMove() {
        if (getIsTurn()) {
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
}
