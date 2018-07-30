import processing.core.PApplet;


public class Game extends PApplet {
    final private static float GAMEWIDTH = 700;
    final private static float GAMEHEIGHT = GAMEWIDTH;

    private Board board;
    private Player player;


    public static void main(String args[]) {
        PApplet.main("Game");

    }

    public static float getGAMEHEIGHT() {
        return GAMEHEIGHT;
    }

    public static float getGAMEWIDTH() {
        return GAMEWIDTH;
    }

    public void settings() {
        size((int) GAMEWIDTH, (int) GAMEHEIGHT);
    }

    public void setup() {
        background(255);
        board = new Board(this);
        board.initGrid();
        player = new Player(this, 125);


    }

    public void draw() {

        board.drawGrid();
        player.drawPieces();
    }
}
