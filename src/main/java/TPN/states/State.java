package TPN.states;

import TPN.Game;
import TPN.board.Board;
import TPN.players.Computer;
import TPN.players.Player;
import TPN.players.StockFishAI;
import TPN.ui.Button;

import java.awt.event.MouseListener;

public abstract class State {

    protected static Board board;
    private static State currentState = null;
    protected Game game;
    protected Button button;
    protected Button settingsButton;

    protected Button selectWhitePlayerButton;
    protected Button selectBlackPlayerButton;

    protected Player whitePlayer;
    protected Player blackPlayer;
    protected Computer blackComputer;
    protected Computer whiteComputer;
    protected StockFishAI blackStockFishAI;
    protected StockFishAI whiteStockFishAI;

    public State(Game game) {
        this.game = game;
    }

    public static State getCurrentState() {
        return currentState;
    }

    public static void setCurrentState(State currentState) {
        State.currentState = currentState;
    }

    public abstract void setup();

    public abstract void draw();

    public abstract Button getButton();

    public abstract Button getSelectWhitePlayerButton();

    public abstract Button getSelectBlackPlayerButton();

    public abstract Button getSettingsButton();

    public abstract String toString();

    public abstract void mouseClicked() ;

    public abstract void mousePressed();

    public abstract void keyPressed();
}
