public class Box {
    private Game game;
    private int boxId;
    private int col;
    private int row;
    private int color;
    private int selectedColor = 125;
    private boolean isSelected;

    public Box(Game game, int boxId, int row, int col, int color) {
        this.game = game;
        this.boxId = boxId;
        this.col = col;
        this.row = row;
        this.color = color;
        drawBox();

    }

    public void drawBox() {
        if (isSelected) {
            game.fill(selectedColor);
        } else {
            game.fill(color);
        }
        game.rect(boxWidth() * col, boxHeight() * row, boxWidth(), boxHeight());
        debugBox();

//        game.fill(125);
//        game.text(boxId,boxWidth()*col,boxHeight()+boxHeight()*row);
    }

    private float boxWidth() {
        return Game.getGAMEWIDTH() / 8;
    }

    private float boxHeight() {
        return Game.getGAMEHEIGHT() / 8;
    }


    public int getColor() {
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

    public void setSelectedColor(int selectedColor) {
        this.selectedColor = selectedColor;
    }

    public void debugBox() {
        if (isSelected) {
            System.out.println(boxId);
        }
    }

    public void setPiece(Piece piece) {
        if (piece.getBoxId() == boxId)
            piece.drawPiece(boxWidth() * col, boxHeight() + boxHeight() * row);
    }
}
