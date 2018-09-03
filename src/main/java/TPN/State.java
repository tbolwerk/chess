package src.main.java.TPN;

public abstract class State {
    protected static Board board;
    private static State currentState = null;
    protected Game game;
    protected Button button;

    public State(Game game) {
        this.game = game;
    }

    public static State getCurrentState() {
        return currentState;
    }

    public static void setCurrentState(State currentState) {
        State.currentState = currentState;
    }

    public abstract void setup() throws InterruptedException;

    public abstract void draw();

    public abstract Button getButton();

    public abstract String toString();
}
