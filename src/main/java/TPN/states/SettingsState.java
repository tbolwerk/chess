package TPN.states;

import TPN.Game;
import TPN.players.Computer;
import TPN.players.Player;
import TPN.players.StockFishAI;
import TPN.ui.Button;


import java.awt.*;

public class SettingsState extends State {



    public SettingsState(Game game) {
        super(game);
    }

    @Override
    public void setup()  {
        game.background(Game.getSettingsImage());
        button = new Button(game,  50,30, (int) Game.getGAMEHEIGHT() / 3, 30);
        button.setColor(new Color(0,0,0));
        button.setTextColor(new Color(255,255,255));
        button.setSelectColor(new Color(123,123,123));
        button.setText("Return");

        selectBlackPlayerButton = new Button(game,50,100,(int) Game.getGAMEHEIGHT() / 3,(int) Game.getGAMEHEIGHT() / 3,Game.getStockFishBlack());
        selectWhitePlayerButton = new Button(game,(int)(Game.getGAMEWIDTH()-(50+ Game.getGAMEHEIGHT() / 3)),100,(int) Game.getGAMEHEIGHT() / 3,(int) Game.getGAMEHEIGHT() / 3,Game.getStockFishWhite());

    }

    @Override
    public void draw() {
        button.drawRectangle();
        selectWhitePlayerButton.drawRectangle();
        selectBlackPlayerButton.drawRectangle();
    }

    public Button getSelectBlackPlayerButton() {
        return selectBlackPlayerButton;
    }

    public Button getSelectWhitePlayerButton() {
        return selectWhitePlayerButton;
    }

    @Override
    public Button getButton() {
        return button;
    }

    @Override
    public Button getSettingsButton() {
        return null;
    }



    @Override
    public String toString() {
        return "SettingsState";
    }

    @Override
    public void mouseClicked() {
        if (button != null && button.overRect() && button.getText().matches("Return")) {
            State.setCurrentState(game.getMenuState());
            game.getMenuState().setup();
        }

        if (State.getCurrentState().getSelectBlackPlayerButton() != null && State.getCurrentState().getSelectBlackPlayerButton().overRect()) {
            System.out.println(Game.getPlayers().get(0).toString());
            State.getCurrentState().getSelectBlackPlayerButton().setClicked(State.getCurrentState().getSelectBlackPlayerButton().getClicked() + 1);

            if (State.getCurrentState().getSelectBlackPlayerButton().getClicked() % 2 == 0) {
                State.getCurrentState().getSelectBlackPlayerButton().setImage(Game.getHumanBlack());
                Game.getPlayers().remove(0);
                Game.getPlayers().add(0,new Player(game,0,false));

            } else {
                State.getCurrentState().getSelectBlackPlayerButton().setImage(Game.getStockFishBlack());
                Game.getPlayers().remove(0);
                Game.getPlayers().add(0,new StockFishAI(game,0,false));
            }
        } else if (State.getCurrentState().getSelectWhitePlayerButton() != null && State.getCurrentState().getSelectWhitePlayerButton().overRect()) {
            System.out.println(Game.getPlayers().get(1).toString());
            State.getCurrentState().getSelectWhitePlayerButton().setClicked(State.getCurrentState().getSelectWhitePlayerButton().getClicked() + 1);


            if (State.getCurrentState().getSelectWhitePlayerButton().getClicked() % 2 == 0) {
                State.getCurrentState().getSelectWhitePlayerButton().setImage(Game.getHumanWhite());
                Game.getPlayers().remove(1);
                Game.getPlayers().add(1,new Player(game,255,true));
            } else {
                State.getCurrentState().getSelectWhitePlayerButton().setImage(Game.getStockFishWhite());
                Game.getPlayers().remove(1);
                Game.getPlayers().add(1,new StockFishAI(game,255,true));

            }
        }
    }

    @Override
    public void mousePressed() {

    }

    @Override
    public void keyPressed() {

    }
}
