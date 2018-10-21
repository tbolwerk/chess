package TPN.board;

import TPN.Game;
import TPN.moves.Directions;

import java.awt.*;
import java.util.ArrayList;

public class Board {
    private static ArrayList<Box> grid = new ArrayList<Box>();
    private Game game;

    private int boxId = 0;
    private final static int NROFBOX = 8;
    final private Color BLACKBOX = Color.decode("#769656");
    final private Color WHITEBOX = Color.decode("#eeeed2");
    private ArrayList<Box> optionalBoxes = new ArrayList<>();
    private Color color = WHITEBOX;

    private ArrayList<Box> board;

    public Board(Game game) {
        this.game = game;
        this.board = new ArrayList<Box>();
    }

    public static ArrayList<Box> getGrid() {
        return grid;
    }

    public static void setGrid(ArrayList<Box> grid) {
        Board.grid = grid;
    }

    public boolean boxSelected(Box box) {
        return game.mouseX > box.getCol() * Game.getGAMEWIDTH() / 8 && game.mouseX < box.getCol() * Game.getGAMEWIDTH() / 8 + Game.getGAMEWIDTH() / 8
                && game.mouseY > box.getRow() * Game.getGAMEHEIGHT() / 8 && game.mouseY < box.getRow() * Game.getGAMEHEIGHT() / 8 + Game.getGAMEHEIGHT() / 8;
    }


        public void drawGrid () {
        for (Box box : grid) {

            if (boxSelected(box)) {
                box.setSelected(true);
            } else if (!boxSelected(box)) {
                box.setSelected(false);
            }
            box.drawBox();

        }


    }


    private void switchColor() {
        if (color == WHITEBOX) {
            color = BLACKBOX;
        } else {
            color = WHITEBOX;
        }

    }


    public void setAllBoxesUnclicked() {
        for (Box box : grid) {
            box.setIsClicked(false);

        }
    }

    public void clearHighLightedOptionalBoxes() {
        for (Box box : getGrid()) {
            box.setOption(false);
        }
    }

    public void setHighLightedOptionalBoxes(Box startBox) {
        for (Box box : getOptionalBoxes(startBox)) {
            box.setOption(true);
        }
    }


    private ArrayList<Box> getOptionalBoxes(Box startBox) {
        ArrayList<Box> hightLightedOptions = new ArrayList<>();
        if (startBox != null && startBox.getPiece() != null) {
            for (Box box : getGrid()) {
                if (startBox.getPiece().validateMove(startBox, box)) {
                    hightLightedOptions.add(box);
                }
            }
        }
        return hightLightedOptions;
    }


    public ArrayList<Box> diagonalPath(Box startBox, Box endBox) {
        ArrayList<Box> path = new ArrayList<>();
        int startX = startBox.getCol(), startY = startBox.getRow();
        int endX = endBox.getCol(), endY = endBox.getRow();
        int difX;
        int difY;
        if (startBox.getPiece().horizontalDirections(startBox, endBox) != null && startBox.getPiece().horizontalDirections(startBox, endBox) == Directions.RIGHT) {
            if (startBox.getPiece().verticalDirections(startBox, endBox) != null && startBox.getPiece().verticalDirections(startBox, endBox) == Directions.UP) {
                for (Box box : getGrid()) {
                    if (box.getCol() > startX && box.getCol() < endX && box.getRow() < startY && box.getRow() > endY) {
                        difX = Math.abs(box.getCol() - startX);
                        difY = Math.abs(box.getRow() - startY);
                        if (difX == difY) {
                            path.add(box);
                        }
                    }
                }
            } else if (startBox.getPiece().verticalDirections(startBox, endBox) != null && startBox.getPiece().verticalDirections(startBox, endBox) == Directions.DOWN) {
                for (Box box : getGrid()) {
                    if (box.getCol() > startX && box.getCol() < endX && box.getRow() > startY && box.getRow() < endY) {
                        difX = Math.abs(box.getCol() - startX);
                        difY = Math.abs(box.getRow() - startY);
                        if (difX == difY) {
                            path.add(box);
                        }

                    }
                }
            }
        } else if (startBox.getPiece().horizontalDirections(startBox, endBox) != null && startBox.getPiece().horizontalDirections(startBox, endBox) == Directions.LEFT) {
            if (startBox.getPiece().verticalDirections(startBox, endBox) != null && startBox.getPiece().verticalDirections(startBox, endBox) == Directions.UP) {
                for (Box box : getGrid()) {
                    if (box.getCol() < startX && box.getCol() > endX && box.getRow() < startY && box.getRow() > endY) {
                        difX = Math.abs(box.getCol() - startX);
                        difY = Math.abs(box.getRow() - startY);
                        if (difX == difY) {
                            path.add(box);
                        }

                    }
                }
            } else if (startBox.getPiece().verticalDirections(startBox, endBox) != null && startBox.getPiece().verticalDirections(startBox, endBox) == Directions.DOWN) {
                for (Box box : getGrid()) {
                    if (box.getCol() < startX && box.getCol() > endX && box.getRow() > startY && box.getRow() < endY) {
                        difX = Math.abs(box.getCol() - startX);
                        difY = Math.abs(box.getRow() - startY);
                        if (difX == difY) {
                            path.add(box);
                        }

                    }
                }
            }
        }

        return path;

    }


    public ArrayList<Box> straightPath(Box startBox, Box endBox) {//parameters for starting box and end box of 2 given inputs
        ArrayList<Box> path = new ArrayList<>();
        int startX = startBox.getCol(), startY = startBox.getRow();
        int endX = endBox.getCol(), endY = endBox.getRow();

//        System.out.println(startBox.getPiece().horizontalDirections());
//        System.out.println(startBox.getPiece().verticalDirections());

        if (startBox.getPiece().horizontalDirections(startBox, endBox) != null && startBox.getPiece().horizontalDirections(startBox, endBox) == Directions.RIGHT) {
            for (Box box : getGrid()) {
                if (box.getRow() == startY && box.getRow() == endY) {//if vertical is same
                    if (box.getCol() > startX && box.getCol() < endX) {//if horizontal is incrementing between start and endpoint
                        path.add(box);
                    }
                }
            }
        }

        if (startBox.getPiece().horizontalDirections(startBox, endBox) != null && startBox.getPiece().horizontalDirections(startBox, endBox) == Directions.LEFT) {
            for (Box box : getGrid()) {
                if (box.getRow() == startY && box.getRow() == endY) {//if vertical is same
                    if (box.getCol() < startX && box.getCol() > endX) {//if horizontal is decrementing between start and endpoint
                        path.add(box);
                    }
                }
            }
        }
        if (startBox.getPiece().verticalDirections(startBox, endBox) != null && startBox.getPiece().verticalDirections(startBox, endBox) == Directions.UP) {
            for (Box box : getGrid()) {
                if (box.getRow() < startY && box.getRow() > endY) {//if vertical is decrementing between start and endpoint
                    if (box.getCol() == startX && box.getCol() == endX) {//if horizontal is same
                        path.add(box);
                    }
                }
            }
        }
        if (startBox.getPiece().verticalDirections(startBox, endBox) != null && startBox.getPiece().verticalDirections(startBox, endBox) == Directions.DOWN) {
            for (Box box : getGrid()) {
                if (box.getRow() > startY && box.getRow() < endY) {//if vertical is incrementing between start and endpoint
                    if (box.getCol() == startX && box.getCol() == endX) {//if horizontal is same
                        path.add(box);
                    }
                }
            }
        }
        return path;
    }

    public static int getNROFBOX() {
        return NROFBOX;
    }

    @SuppressWarnings("Duplicates")
    public void initBoard() {
        for (int row = 0; row < NROFBOX; row++) {
            switchColor();
            for (int col = 0; col < NROFBOX; col++) {
                switchColor();
                board.add(new Box(game, boxId, row, col, color));
                boxId++;

            }
        }
    }

    @SuppressWarnings("Duplicates")
    public void initGrid() {
        for (int row = 0; row < NROFBOX; row++) {
            switchColor();
            for (int col = 0; col < NROFBOX; col++) {
                switchColor();
                grid.add(new Box(game, boxId, row, col, color));
                boxId++;

            }
        }
    }

    public static boolean checkBoardState(Box startBox, Box endBox) {
        //TODO:make it working
        ArrayList<Box> board = new ArrayList<>();
        board = getGrid();
        for (Box box : board) {
            if (box.equals(startBox)) {
                box.unSetPiece();
            } else if (box.equals(endBox)) {
                if (box.getPiece() != null && endBox.getPiece().getIsWhite() != box.getPiece().getIsWhite()) {
                    box.unSetPiece();
                    box.setPiece(startBox.getPiece());
                }
                box.setPiece(startBox.getPiece());
            }


        }
        for (Box box : board) {
            if (box.getPiece().getPlayer().getKing().isCheck(box.getPiece().getPlayer().getKing().getBox())) {

                return false;
            }

        }
        return true;
    }
}
