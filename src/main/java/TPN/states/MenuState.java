package TPN.states;

import TPN.Game;
import TPN.players.Computer;
import TPN.ui.Button;
import processing.core.PImage;

import java.awt.*;

public class MenuState extends State {
    private Color color;
    private PImage image;


    public MenuState(Game game) {
        super(game);
        color = new Color(100, 255, 0);

//        setup();
    }

    public void setBackground(Color color) {
        this.color = color;
    }

    public void setup() {
        if(Game.getPlayers().size()<2) {
            Game.getPlayers().clear();
            Game.getPlayers().add(0, new Computer(game, 0, false));
            Game.getPlayers().add(1, new Computer(game, 0, true));
        }

        if (Game.getBackground() == null || !Game.getBackground().loaded) {
            game.background(color.getRGB());
        } else {
            game.background(Game.getBackground());
        }
        button = new Button(game, 50, 30, (int) Game.getGAMEHEIGHT() / 3, 30);
        button.setColor(new Color(0, 0, 0));
        button.setTextColor(new Color(255, 255, 255));
        button.setSelectColor(new Color(123, 123, 123));
        button.setText("Start Game");

        settingsButton = new Button(game, 50, 70, (int) Game.getGAMEWIDTH() / 3, 30);
        settingsButton.setColor(new Color(0, 0, 0));
        settingsButton.setTextColor(new Color(255, 255, 255));
        settingsButton.setSelectColor(new Color(123, 123, 123));
        settingsButton.setText("Settings");


    }

    public Button getButton() {
        return button;
    }

    @Override
    public Button getSelectWhitePlayerButton() {
        return null;
    }

    @Override
    public Button getSelectBlackPlayerButton() {
        return null;
    }

    @Override
    public Button getSettingsButton() {
        return settingsButton;
    }

    public void draw() {
        button.drawRectangle();
        settingsButton.drawRectangle();
    }

    @Override
    public String toString() {
        return "MenuState";
    }

    @Override
    public void mouseClicked() {
        if (settingsButton != null && settingsButton.overRect()) {
            State.setCurrentState(game.getSettingsState());
            game.getSettingsState().setup();
        }
        if (button != null && button.overRect() && button.getText().matches("Start Game")) {
            State.setCurrentState(game.getGameState());
            game.getGameState().setup();
        }
    }

    @Override
    public void mousePressed() {

    }

    @Override
    public void keyPressed() {

    }
}
