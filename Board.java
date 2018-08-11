import java.util.ArrayList;

public class Board {
    private static ArrayList<Box> grid = new ArrayList<Box>();
    private Game game;
    private int color = 255;
    private int boxId = 0;
    final private int BLACKBOX = 50;
    final private int WHITEBOX = 255;

    private ArrayList<Box> optionalBoxes = new ArrayList<>();

    public Board(Game game) {
        this.game = game;
    }

    public static ArrayList<Box> getGrid() {
        return grid;
    }

    public void initGrid() {
        for (int row = 0; row < 8; row++) {
            switchColor();
            for (int col = 0; col < 8; col++) {
                switchColor();
                grid.add(new Box(game, boxId, row, col, color));
                boxId++;

            }
        }
    }

    public boolean boxSelected(Box box) {
        return game.mouseX > box.getCol() * Game.getGAMEWIDTH() / 8 && game.mouseX < box.getCol() * Game.getGAMEWIDTH() / 8 + Game.getGAMEWIDTH() / 8
                && game.mouseY > box.getRow() * Game.getGAMEHEIGHT() / 8 && game.mouseY < box.getRow() * Game.getGAMEHEIGHT() / 8 + Game.getGAMEHEIGHT() / 8;
    }

    public void drawGrid() {

        for (Box box : grid) {
            if (boxSelected(box)) {
                box.setSelected(true);
            } else {
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

    public ArrayList<Box> diagonalPath(Box startBox, Box endBox) {
        ArrayList<Box> path = new ArrayList<>();
        int startX = startBox.getCol(), startY = startBox.getRow();
        int endX = endBox.getCol(), endY = endBox.getRow();
        int difX;
        int difY;
        if (startBox.getPiece().horizontalDirections() != null && startBox.getPiece().horizontalDirections() == Directions.RIGHT) {
            if (startBox.getPiece().verticalDirections() != null && startBox.getPiece().verticalDirections() == Directions.UP) {
                for (Box box : getGrid()) {
                    if (box.getCol() > startX && box.getCol() < endX && box.getRow() < startY && box.getRow() > endY) {
                        difX = Math.abs(box.getCol() - startX);
                        difY = Math.abs(box.getRow() - startY);
                        if (difX == difY) {
                            path.add(box);
                        }
                    }
                }
            } else if (startBox.getPiece().verticalDirections() != null && startBox.getPiece().verticalDirections() == Directions.DOWN) {
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
        } else if (startBox.getPiece().horizontalDirections() != null && startBox.getPiece().horizontalDirections() == Directions.LEFT) {
            if (startBox.getPiece().verticalDirections() != null && startBox.getPiece().verticalDirections() == Directions.UP) {
                for (Box box : getGrid()) {
                    if (box.getCol() < startX && box.getCol() > endX && box.getRow() < startY && box.getRow() > endY) {
                        difX = Math.abs(box.getCol() - startX);
                        difY = Math.abs(box.getRow() - startY);
                        if (difX == difY) {
                            path.add(box);
                        }

                    }
                }
            } else if (startBox.getPiece().verticalDirections() != null && startBox.getPiece().verticalDirections() == Directions.DOWN) {
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

        if (startBox.getPiece().horizontalDirections() != null && startBox.getPiece().horizontalDirections() == Directions.RIGHT) {
            for (Box box : getGrid()) {
                if (box.getRow() == startY && box.getRow() == endY) {//if vertical is same
                    if (box.getCol() > startX && box.getCol() < endX) {//if horizontal is incrementing between start and endpoint
                        path.add(box);
                    }
                }
            }
        }

        if (startBox.getPiece().horizontalDirections() != null && startBox.getPiece().horizontalDirections() == Directions.LEFT) {
            for (Box box : getGrid()) {
                if (box.getRow() == startY && box.getRow() == endY) {//if vertical is same
                    if (box.getCol() < startX && box.getCol() > endX) {//if horizontal is decrementing between start and endpoint
                        path.add(box);
                    }
                }
            }
        }
        if (startBox.getPiece().verticalDirections() != null && startBox.getPiece().verticalDirections() == Directions.UP) {
            for (Box box : getGrid()) {
                if (box.getRow() < startY && box.getRow() > endY) {//if vertical is decrementing between start and endpoint
                    if (box.getCol() == startX && box.getCol() == endX) {//if horizontal is same
                        path.add(box);
                    }
                }
            }
        }
        if (startBox.getPiece().verticalDirections() != null && startBox.getPiece().verticalDirections() == Directions.DOWN) {
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


}
