import processing.core.PApplet;

import java.util.ArrayList;


public class Game extends PApplet {
    final private static float GAMEWIDTH = 500;
    final private static float GAMEHEIGHT = GAMEWIDTH;

    private State currentState = State.MENU;
    private Button button;

    private static ArrayList<Player> players = new ArrayList<Player>();

    private ArrayList<Box> clickedBoxes = new ArrayList<Box>();
    private boolean turn;

    private ImageLoader imageLoader;

    private Board board;
    private Player player;
    private Player player2;


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

    public static ArrayList<Player> getPlayers() {
        return players;
    }

    public void draw() {
        if (currentState == State.MENU) {
            button.drawRectangle();
        }
        if (currentState == State.SETUP) {
            board.drawGrid();
            player.drawPieces();
            player2.drawPieces();
        }
        if (currentState == State.GAME_OVER) {
            background(0);
            fill(255);
            textSize(30);
            button.drawRectangle();
            text("Game over " + getWinner() + " won!", (getGAMEWIDTH() / 3) - (getGAMEWIDTH() / 3) / 2, getGAMEHEIGHT() / 3);

        }
    }


    public void setup() {
        background(244, 161, 66);
        if (currentState == State.MENU) {
            button = new Button(this, (int) getGAMEWIDTH() / 2 - ((int) (getGAMEWIDTH() / 3) / 2), (int) getGAMEHEIGHT() / 2, (int) getGAMEHEIGHT() / 3, 30);
            button.setColor(100);
            button.setTextColor(255);
            button.setSelectColor(123);
            button.setText("Start Game");
        } else if (currentState == State.GAME_OVER) {
            button.setText("New Game");
        }


        if (currentState == State.SETUP) {
            board = new Board(this);
            board.initGrid();
            imageLoader = new ImageLoader(this, "pieces/chess_pieces.png");
            player = new Player(this, 125, true);
            player2 = new Player(this, 75, false);
            players.add(player);
            players.add(player2);
        }

    }

    @Override
    public void mouseClicked() {
        if (currentState != State.SETUP && currentState != State.GAME_OVER) {
            currentState = State.SETUP;
            setup();
        } else if (currentState == State.GAME_OVER) {
            setup();
            if (button.overRect()) {
                currentState = State.MENU;
            }
        } else if (currentState == State.MENU) {
            setup();
        }


    }

    @Override
    public void mousePressed() {

        if (currentState == State.SETUP) {
            for (Box box : Board.getGrid()) {
                if (board.boxSelected(box) && box.getPiece() != null) {
                    box.setIsClicked(true);
                    clickedBoxes.add(box);
                } else if (clickedBoxes.size() > 0 && board.boxSelected(box)) {
                    box.setIsClicked(true);
                    clickedBoxes.add(box);
                }
            }


            if (clickedBoxes.size() > 1) {
                movePiece();
                board.setAllBoxesUnclicked();
                clickedBoxes.clear();
            }
        }

    }

    private void movePiece() {
        int newBoxId = 0;
        for (Box box : clickedBoxes) {
            newBoxId = box.getBoxId();
        }
        for (Box box : clickedBoxes) {
            if (box.getPiece() != null && box.getPiece().validateMove()) {
                if (checkIsTurn())
                box.getPiece().setBoxId(newBoxId);
                box.unSetPiece();
            }
        }
    }

    private boolean checkIsTurn() {
        if (getPlayers().get(0).getPieces().contains(clickedBoxes.get(0).getPiece()) && getPlayers().get(0).getIsTurn()) {
            getPlayers().get(0).setTurn(false);
            getPlayers().get(1).setTurn(true);
            return true;
        }
        if (getPlayers().get(1).getPieces().contains(clickedBoxes.get(0).getPiece()) && getPlayers().get(1).getIsTurn()) {
            getPlayers().get(0).setTurn(true);
            getPlayers().get(1).setTurn(false);
            return true;
        }
        return false;
    }

    public Board getBoard() {
        return board;
    }

    public void removePiece() {
        //checks piece for color
        if (clickedBoxes.get(0).getPiece() != null && clickedBoxes.get(1).getPiece() != null && clickedBoxes.get(0).getPiece().getIsWhite() != clickedBoxes.get(1).getPiece().getIsWhite()) {
            if (clickedBoxes.get(0).getPiece().getPlayer().getIsTurn()) {
                clickedBoxes.get(1).getPiece().getPlayer().removePiece(clickedBoxes.get(1).getPiece());
                clickedBoxes.get(1).unSetPiece();
            }
        }
    }


    public ImageLoader getImageLoader() {
        return imageLoader;
    }


    public ArrayList<Box> getClickedBoxes() {
        return clickedBoxes;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public String getWinner() {
        String winner = "Draw";
        for (Player player : getPlayers()) {
            for (Piece piece : player.getPieces()) {
                if (piece instanceof King) {
                    winner = player.toString();
                }
            }
        }
        return winner;
    }
}
