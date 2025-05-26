package puzzle;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageLoader {
    public static BufferedImage loadImage(File file) {
        try {
            return ImageIO.read(file);
        } catch (Exception e) {
            System.out.println("Erreur de chargement: " + file.getName());
            return null;
        }
    }

    public static File[] listImageFiles(String folderPath) {
        File folder = new File(folderPath);
        return folder.isDirectory() ? folder.listFiles((dir, name) -> name.endsWith(".png")) : new File[0];
    }
}