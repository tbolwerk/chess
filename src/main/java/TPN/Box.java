package src.main.java.TPN;

import java.awt.*;

public class Box {
    private Game game;
    private int boxId;

    private int col;
    private int row;
    private Color color;
    private Color selectedColor = Color.decode("#FF5733");
    private Color clickedColor = Color.decode("#baca44");
    private Color optionColor = Color.decode("0x00FF7F");
    private Color drawColor;
    private boolean isSelected;
    private boolean isClicked;
    private boolean isOption;
    private boolean isHighlighted;

    private Piece piece = null;

    public Box(Game game, int boxId, int row, int col, Color color) {
        this.game = game;
        this.boxId = boxId;
        this.col = col;
        this.row = row;
        this.color = color;
        this.piece = null;
        drawBox();

    }


    public void drawBox() {
        if (isClicked) {
            drawColor = clickedColor;
        } else if (isSelected) {
            drawColor = selectedColor;
        } else if (isOption) {
            drawColor = optionColor;

        } else {
            drawColor = color;
        }
        game.fill(drawColor.getRGB());
        if (isOption) {
            game.fill(0, 255, 0);
        }
        game.noStroke();
        game.rect(boxWidth() * col, boxHeight() * row, boxWidth(), boxHeight());
//        debugBox();

//        game.fill(125);
//        game.text(boxId,boxWidth()*col,boxHeight()+boxHeight()*row);
    }

    private float boxWidth() {
        return Game.getGAMEWIDTH() / Board.getNROFBOX();
    }

    private float boxHeight() {
        return Game.getGAMEHEIGHT() / Board.getNROFBOX();
    }


    public Color getColor() {
        return color;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }


    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean getIsClicked() {
        return isClicked;
    }

    public void setIsClicked(boolean clicked) {
        isClicked = clicked;
    }

    public void setOption(boolean isOption) {
        this.isOption = isOption;
    }

    public void setSelectedColor(Color selectedColor) {
        this.selectedColor = selectedColor;
    }

    public void debugBox() {
        if (isSelected && getPiece() != null || isClicked && getPiece() != null) {
//            System.out.println(piece.toString());
        }
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;

        if (piece.getBoxId() == boxId)
            piece.drawPiece(boxWidth() * col, boxHeight() * row);
    }

    public void unSetPiece() {
        this.piece = null;
    }

    public int getBoxId() {
        return boxId;
    }

    public void setIsHighlighted(boolean isHighlighted) {
        this.isHighlighted = isHighlighted;
    }


    public boolean isHighlighted() {
        return isHighlighted;
    }
}
