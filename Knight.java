import processing.core.PImage;

import java.awt.*;
import java.util.ArrayList;

public class Knight extends Piece {
    private int y;

    public Knight(Game game, Player player, boolean isWhite, int boxId) {
        super(game, player, isWhite, boxId);
        drawPiece();
    }

    @Override
    public boolean checkForCapture() {
        ArrayList<Box> clickedBoxes = game.getClickedBoxes();
        if (clickedBoxes.get(1).getPiece() != null && clickedBoxes.get(1).getPiece().getIsWhite() != clickedBoxes.get(0).getPiece().getIsWhite()) {
            game.removePiece();
            return true;
        }
        return true;
    }


    @Override
    public boolean validateMove() {
        Point[] cords = new Point[8];
        cords[0] = new Point(-1, -2);
        cords[1] = new Point(-1, 2);
        cords[2] = new Point(2, -1);
        cords[3] = new Point(2, 1);
        cords[4] = new Point(1, 2);
        cords[5] = new Point(1, -2);
        cords[6] = new Point(-2, -1);
        cords[7] = new Point(-2, 1);
        ArrayList<Box> clickedBoxes = game.getClickedBoxes();
        int x1 = clickedBoxes.get(0).getCol();
        int x2 = clickedBoxes.get(1).getCol();
        int y1 = clickedBoxes.get(0).getRow();
        int y2 = clickedBoxes.get(1).getRow();
        int yDif = y1 - y2;
        int xDif = x1 - x2;
        System.out.println(xDif + " " + yDif);
        for (Point point : cords) {
            if (point.x == xDif && point.y == yDif) {
                return checkForCapture();
            }
        }
        return false;
    }


    public void drawPiece() {
        if (!getIsWhite()) {
            y = 667 / 2;
        } else {
            y = 0;
        }
        PImage pieceImage = game.getImageLoader().getImage((2000 / 6) * 3, y, 2000 / 6, 667 / 2);
        super.setPieceImage(pieceImage);
        super.setPiece('H');

    }

    @Override
    public String toString() {
        return "Knight";
    }
}
