package TPN.pieces;

import TPN.Game;
import TPN.board.Box;
import TPN.players.Player;
import processing.core.PImage;

import java.awt.*;


public class Knight extends Piece {
    private int y;

    public Knight(Game game, Player player, boolean isWhite, int boxId) {
        super(game, player, isWhite, boxId);
        drawPiece();
    }


    @Override
    public boolean validateMove(Box startBox, Box endBox) {
        if (endBox.getPiece() != null && startBox.getPiece().getIsWhite() == endBox.getPiece().getIsWhite()) {
            return false;
        }

        Point[] cords = new Point[8];
        cords[0] = new Point(-1, -2);
        cords[1] = new Point(-1, 2);
        cords[2] = new Point(2, -1);
        cords[3] = new Point(2, 1);
        cords[4] = new Point(1, 2);
        cords[5] = new Point(1, -2);
        cords[6] = new Point(-2, -1);
        cords[7] = new Point(-2, 1);

        int x1 = startBox.getCol();
        int x2 = endBox.getCol();
        int y1 = startBox.getRow();
        int y2 = endBox.getRow();
        int yDif = y1 - y2;
        int xDif = x1 - x2;
//        System.out.println(xDif + " " + yDif);
        for (Point point : cords) {
            if (point.x == xDif && point.y == yDif) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int valueOfPiece() {
        return 4;
    }


    public void drawPiece() {
        if (!getIsWhite()) {
            y = 667 / 2;
        } else {
            y = 0;
        }
        PImage pieceImage = game.getGameState().getImageLoader().getImage((2000 / 6) * 3, y, 2000 / 6, 667 / 2);
        super.setPieceImage(pieceImage);
        super.setPiece('H');

    }

    @Override
    public String toString() {
        return "Knight";
    }
}
