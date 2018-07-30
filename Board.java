import java.util.ArrayList;

public class Board {
    private static ArrayList<Box> grid = new ArrayList<Box>();
    private Game game;
    private int color = 255;
    private int boxId = 0;

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

    public void drawGrid() {

        for (Box box : grid) {
            if (game.mouseX > box.getCol() * Game.getGAMEWIDTH() / 8 && game.mouseX < box.getCol() * Game.getGAMEWIDTH() / 8 + Game.getGAMEWIDTH() / 8
                    && game.mouseY > box.getRow() * Game.getGAMEHEIGHT() / 8 && game.mouseY < box.getRow() * Game.getGAMEHEIGHT() / 8 + Game.getGAMEHEIGHT() / 8
                    ) {
                box.setSelected(true);
            } else {
                box.setSelected(false);
            }

            box.drawBox();

        }

    }

    private void switchColor() {
        if (color > 0) {
            color = 0;
        } else {
            color = 255;
        }

    }
}
