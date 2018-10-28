package TPN.pieces;

import TPN.Game;
import TPN.board.Board;
import TPN.board.Box;
import TPN.moves.Directions;
import TPN.players.Player;
import processing.core.PImage;

import java.util.ArrayList;

public abstract class Piece {

    protected Game game;
    private boolean isWhite;
    private char pieceName = 'p';
    private PImage pieceImage = null;
    private int boxId;
    protected Player player;
    protected int countMovement;
    private boolean isMoved;
    private boolean isGettingCaptured;


    public Piece(Game game, Player player, boolean isWhite, int boxId) {

        this.game = game;
        this.player = player;
        this.isWhite = isWhite;
        this.boxId = boxId;
        countMovement = 0;


    }

    public int getCountMovement() {
        return countMovement;
    }

    public void removePiece() {
        getBox().unSetPiece();
    }


    public void drawPiece(float x, float y) {
        if (pieceImage != null) {
            pieceImage.resize((int) Game.getGAMEWIDTH() / 8, (int) Game.getGAMEHEIGHT() / 8);
            game.image(pieceImage, x, y);
        }
    }

    protected void setPiece(char letter) {
        pieceName = letter;
    }

    public char getPieceName() {
        return pieceName;
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

    public boolean checkForCapture() {
        ArrayList<Box> clickedBoxes = game.getClickedBoxes();
        if (clickedBoxes.get(1).getPiece() != null && clickedBoxes.get(1).getPiece().getIsWhite() != clickedBoxes.get(0).getPiece().getIsWhite()) {
//            if(clickedBoxes.get(0).getPiece() instanceof src.main.java.TPN.King && ((src.main.java.TPN.King) clickedBoxes.get(0).getPiece()).isCheck(clickedBoxes.get(1))){
//                return false;
//            }
            return true;
        }
        return false;
    }

    public Enum<Directions> horizontalDirections(Box startBox, Box endBox) {


        int column = startBox.getCol() - endBox.getCol();

        if (column < 0) {
            return Directions.RIGHT;
        } else if (column > 0) {
            return Directions.LEFT;
        }

        return null;
    }

    public boolean movesHorizontal(Box startBox, Box endBox) {
        return startBox.getCol() == endBox.getCol() && startBox.getRow() != endBox.getRow();
    }

    public boolean movesVertical(Box startBox, Box endBox) {
        return startBox.getCol() != endBox.getCol() && startBox.getRow() == endBox.getRow();
    }

    public boolean movesDiagonal(Box startBox, Box endBox) {
        int column = Math.abs(startBox.getCol() - endBox.getCol());
        int row = Math.abs(startBox.getRow() - endBox.getRow());
        return row == column;
    }

    public Enum<Directions> verticalDirections(Box startBox, Box endBox) {


        int row = startBox.getRow() - endBox.getRow();
        if (row < 0) {
            return Directions.DOWN;
        } else if (row > 0) {
            return Directions.UP;
        }
        return null;
    }

    public abstract boolean validateMove(Box startBox, Box endBox);

    public abstract int valueOfPiece();


    public void countingMovement() {
        countMovement++;
    }

    public boolean validPath(Box startBox, Box endBox) {
//        src.main.java.TPN.Box selectedBox = null;
//        if(game.getClickedBoxes().size()!=0) {
//           selectedBox=game.getClickedBoxes().get(0);
//        }
        for (Box box : game.getGameState().getBoard().straightPath(startBox, endBox)) {

            if (box.getPiece() != null && !box.getPiece().isMoved() || box.getPiece() != null && !box.getPiece().isGettingCaptured()) {//&& box.getPiece()!= game.getClickedBoxes().get(0).getPiece()
//                if(selectedBox != null && selectedBox != box) {

                return false;
//                }
            }

        }
        for (Box box : game.getGameState().getBoard().diagonalPath(startBox, endBox)) {
            if (box.getPiece() != null && !box.getPiece().isMoved() || box.getPiece() != null && !box.getPiece().isGettingCaptured()) {
                return false;
            }
        }

        return true;
    }

    public Box getBox() {

        for (Box box : Board.getGrid()) {
            if (box.getBoxId() == this.boxId) {
                return box;
            }
        }
        return null;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isMoved() {
        return isMoved;
//        if(game.getClickedBoxes().size()>0){
//        return game.getClickedBoxes().get(0).getPiece()==this && game.getClickedBoxes().get(0).getPiece() != null;
//        }
//        return false;
    }

    public boolean isGettingCaptured() {
        return isGettingCaptured;
//        if(game.getClickedBoxes().size()>1){
//            return game.getClickedBoxes().get(1).getPiece()==this && game.getClickedBoxes().get(1) != null;
//        }
//        return false;
    }

    public void setIsGettingCaptured(boolean isGettingCaptured) {
        this.isGettingCaptured = isGettingCaptured;
    }

    public void setIsMoved(boolean isMoved) {
        this.isMoved = isMoved;
    }

    public ArrayList<Box> posibleMoves() {
        ArrayList<Box> posibleMoves = new ArrayList<>();

        for (Box box : Board.getGrid()) {

            if (this instanceof Pawn) {

                if (((Pawn) this).posibleCaptureMove(this.getBox(), box)) {
                    posibleMoves.add(box);
                }

            } else if (this.validateMove(this.getBox(), box) && this.validPath(this.getBox(),box)) {
                posibleMoves.add(box);
            }


        }
        return posibleMoves;
    }
}
