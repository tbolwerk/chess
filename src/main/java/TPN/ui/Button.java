package TPN.ui;

import TPN.Game;
import processing.core.PImage;

import java.awt.*;

public class Button {
    private int x, y, width, height, diameter;
    private Game game;
    private Color color;
    private Color textColor;
    private String text = null;
    private Color selectColor;
    private PImage image;
    private int clicked;

    public Button(Game game, int x, int y, int diameter) {
        this(game, x, y, 0, 0);
        this.diameter = diameter;
        this.clicked = 3;
    }

    public Button(Game game, int x, int y, int width, int height) {
        this(game,x,y,width,height,null);

    }

    public Button(Game game,int x, int y, int width, int height,PImage image){
        this.game = game;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image=image;
        this.clicked = 0;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    private void drawText(){
        if (text != null) {
            if(image ==null) {
                if (textColor == null) {
                    game.fill(new Color(0, 0, 0).getRGB());
                } else {
                    game.fill(textColor.getRGB());
                }
            }else {
                image.resize(width,height);
            }
            game.textSize(height);
            game.text(text, x+((x+width)/8), y+height);
        }
    }

    public void drawRectangle() {
        if(image == null) {
            if (color == null) {
                game.fill(new Color(125, 0, 0).getRGB());
            } else {
                game.fill(color.getRGB());
            }
        if (overRect()) {
            if(selectColor == null) {
                game.fill(new Color(200, 200, 0).getRGB());
            }else {
                game.fill(selectColor.getRGB());
            }
        }
        game.stroke(new Color(255,255,255).getRGB());
        game.strokeWeight(2);
        game.rect(x, y, width, height);
        }else {

            image.resize(width,height);
                game.image(image,x,y);

        }
        drawText();


    }


    public void setImage(PImage image){
        this.image=image;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSelectColor(Color selectColor) {
        this.selectColor = selectColor;
    }


    public boolean overRect() {
        if (game.mouseX >= x && game.mouseX <= x + width &&
                game.mouseY >= y && game.mouseY <= y + height) {
            return true;
        } else {
            return false;
        }
    }

    public boolean overCircle(int diameter) {
        float disX = x - game.mouseX;
        float disY = y - game.mouseY;
        if (Game.sqrt(Game.sq(disX) + Game.sq(disY)) < diameter / 2) {
            return true;
        } else {
            return false;
        }
    }

    public String getText() {
        return text;
    }

    public int getClicked() {
        return clicked;
    }

    public void setClicked(int clicked) {
        this.clicked = clicked;
    }
}
