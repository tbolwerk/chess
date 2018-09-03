package src.main.java.TPN;

import processing.core.PImage;

public class ImageLoader {
    private Game game;
    private PImage piecesImage;

    public ImageLoader(Game game, String path) {
        this.game = game;
        this.piecesImage = game.loadImage(path);
    }

    public PImage getImage(int x, int y, int width, int height) {
        PImage croppedImage;
        croppedImage = piecesImage.get(x, y, width, height);
        return croppedImage;
    }


}
