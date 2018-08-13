import processing.core.PApplet;

import java.util.ArrayList;


public class Game extends PApplet {
    final private static float GAMEWIDTH = 700;
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
    private Computer computer;
    private Computer computer2;


    private String winner;


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
        keyboardPressed();
        if (currentState == State.MENU) {
            button.drawRectangle();
        }
        if (currentState == State.SETUP) {
            board.drawGrid();
            for (Player player : getPlayers()) {
                player.drawPieces();
                if (player instanceof AI) {
                    if (player.getIsTurn()) {
                        ((AI) player).makeMove();
                    }
                }
            }
        }
        if (currentState == State.GAME_OVER) {

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
            players.clear();
            button.setText("New Game");
        } else
        if (currentState == State.SETUP) {
            board = new Board(this);
            board.initGrid();
            imageLoader = new ImageLoader(this, "pieces/chess_pieces.png");
            player = new Player(this, 125, true);
            player2 = new Player(this, 75, false);
            computer = new Computer(this, 75, false);
            computer2 = new Computer(this, 125, true);
//            AI = new Ai(this,125,true);


            players.add(player);
            players.add(computer);
        }

    }

    @Override
    public void mouseClicked() {
        if (currentState != State.SETUP && currentState != State.GAME_OVER) {
            if (button.overRect()) {
                currentState = State.SETUP;
            }
            setup();
        } else if (currentState == State.GAME_OVER) {
            setup();

            if (button.overRect()) {
                currentState = State.MENU;
            }
        }


    }

    public void keyboardPressed() {
        if (keyPressed && key == ENTER) {
            currentState = State.SETUP;
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
            if (clickedBoxes.size() == 1 && clickedBoxes.get(0).getPiece() != null) {
                showOptions();
            }

            if (clickedBoxes.size() > 1) {
                movePiece();
                board.setAllBoxesUnclicked();
                clickedBoxes.clear();
                board.clearHighLightedOptionalBoxes();
            }
        }

    }

    private void showOptions() {
        Box startBox = clickedBoxes.get(0);
        if (startBox.getPiece() != null) {
            board.setHighLightedOptionalBoxes(startBox);

        }
    }

    private void checksGameStateAfterTurn() {
        for (Player player : Game.getPlayers()) {
            if (player.isHasLost()) {
                currentState = State.GAME_OVER;
                gameover();
            }
        }
    }

    public Player getOpponent(Player ownPlayer) {
        for (Player player : getPlayers()) {
            if (!ownPlayer.equals(player)) {
                return player;
            }
        }
        return null;
    }

    private void movePiece() {


        Box startBox = clickedBoxes.get(0);
        Box endBox = clickedBoxes.get(1);
        int newBoxId = endBox.getBoxId();


        if (startBox.getPiece().validateMove(startBox, endBox)) {//checks if starting box has piece in it and if it makes a validmove


            if (checkIsTurn(startBox.getPiece().getPlayer())) {
                if (endBox.getPiece() != null && startBox.getPiece().checkForCapture()) {//checks if endpoint has a piece in it + capture
                    removePiece();
                    endBox.unSetPiece();
                }

                startBox.getPiece().setBoxId(newBoxId);
                startBox.getPiece().countingMovement();
                clickedBoxes.get(1).setPiece(startBox.getPiece());
                startBox.unSetPiece();
                checksGameStateAfterTurn();
                endTurn();


            }

        }

        }


    private void endTurn() {

        for (Player player : getPlayers()) {
            player.setTurn(!player.getIsTurn());
        }
    }

    private boolean checkIsTurn(Player player) {
        return player.getIsTurn();
    }

    public Board getBoard() {
        return board;
    }

    public void removePiece() {
        //checks piece for color

                clickedBoxes.get(1).getPiece().getPlayer().removePiece(clickedBoxes.get(1).getPiece());
                clickedBoxes.get(1).unSetPiece();


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

    public void setWinner(String winner) {
        this.winner = winner;
    }

    private void gameover() {
        for (Player player : getPlayers()) {
            if (!player.isHasLost()) {
                setWinner(player.toString());
            }
        }

    }



    public String getWinner() {
        return winner;
    }

    public State getCurrentState() {

        return currentState;
    }
}
