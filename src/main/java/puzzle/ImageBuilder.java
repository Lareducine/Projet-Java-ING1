package puzzle;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ImageBuilder {
    public static BufferedImage buildImage(PuzzleGrid grid) {
        int rows = grid.getRows();
        int cols = grid.getCols();

        int tileWidth = grid.getPiece(0, 0).getWidth();
        int tileHeight = grid.getPiece(0, 0).getHeight();

        BufferedImage result = new BufferedImage(cols * tileWidth, rows * tileHeight, BufferedImage.TYPE_INT_RGB);
        Graphics g = result.getGraphics();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                PuzzlePiece piece = grid.getPiece(i, j);
                if (piece != null) {
                    g.drawImage(piece.getImage(), j * tileWidth, i * tileHeight, null);
                }
            }
        }

        g.dispose();
        return result;
    }
}
