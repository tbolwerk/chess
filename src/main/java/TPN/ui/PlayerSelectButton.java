package TPN.ui;

import TPN.Game;

import java.lang.reflect.Array;

public class PlayerSelectButton extends Button{
    private int timesClicked=0;
    private boolean isWhitePlayer;
    public PlayerSelectButton(Game game, int x, int y, int width, int height) {
        super(game, x, y, width, height);
    }

    public void setWhitePlayer(boolean whitePlayer) {
        isWhitePlayer = whitePlayer;
    }

    public void setTimesClicked(int timesClicked) {
        if(this.timesClicked==2){
            this.timesClicked=0;
        }else
        this.timesClicked = timesClicked;
    }

    public int getTimesClicked() {
        return timesClicked;
    }
}
