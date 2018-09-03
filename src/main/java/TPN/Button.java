package src.main.java.TPN;

public class Button {
    int x, y, width, height, diameter;
    private Game game;
    private int color;
    private int textColor;
    private String text = null;
    private int selectColor;

    public Button(Game game, int x, int y, int diameter) {
        this(game, x, y, 0, 0);
        this.diameter = diameter;

    }

    public Button(Game game, int x, int y, int width, int height) {
        this.game = game;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

    public void setColor(int color) {
        this.color = color;
    }

    public void drawRectangle() {
        if (text != null) {
            if (textColor != 0) {
                game.fill(textColor);
            }
            game.textSize(height);
            game.text(text, x, y);
        }
        if (color != 0) {
            game.fill(color);
        }
        if (overRect()) {
            game.fill(selectColor);
        }
        game.rect(x, y, width, height);
    }


    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSelectColor(int selectColor) {
        this.selectColor = selectColor;
    }


    boolean overRect() {
        if (game.mouseX >= x && game.mouseX <= x + width &&
                game.mouseY >= y && game.mouseY <= y + height) {
            return true;
        } else {
            return false;
        }
    }

    boolean overCircle(int diameter) {
        float disX = x - game.mouseX;
        float disY = y - game.mouseY;
        if (Game.sqrt(Game.sq(disX) + Game.sq(disY)) < diameter / 2) {
            return true;
        } else {
            return false;
        }
    }
}
