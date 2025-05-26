package puzzle;

import java.awt.image.BufferedImage;

public class PuzzlePiece {
    private String name;
    private BufferedImage image;

    public PuzzlePiece(String name, BufferedImage image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getWidth() {
        return image.getWidth();
    }

    public int getHeight() {
        return image.getHeight();
    }

    public int[] getTopBorder() {
        int width = image.getWidth();
        int[] border = new int[width];
        for (int x = 0; x < width; x++) {
            border[x] = image.getRGB(x, 0);
        }
        return border;
    }

    public int[] getBottomBorder() {
        int width = image.getWidth();
        int height = image.getHeight();
        int[] border = new int[width];
        for (int x = 0; x < width; x++) {
            border[x] = image.getRGB(x, height - 1);
        }
        return border;
    }

    public int[] getLeftBorder() {
        int height = image.getHeight();
        int[] border = new int[height];
        for (int y = 0; y < height; y++) {
            border[y] = image.getRGB(0, y);
        }
        return border;
    }

    public int[] getRightBorder() {
        int width = image.getWidth();
        int height = image.getHeight();
        int[] border = new int[height];
        for (int y = 0; y < height; y++) {
            border[y] = image.getRGB(width - 1, y);
        }
        return border;
    }
}
