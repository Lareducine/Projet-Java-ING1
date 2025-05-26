package puzzle;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import java.awt.image.BufferedImage;

public class Main {
    public static void main(String[] args) {
        System.out.println("CY-Puzzle project started!");

        String imageFolder = "C:/Users/loulo/Projet-Java-ING1/pieces/puzzle2/4x2";
        File[] imageFiles = ImageLoader.listImageFiles(imageFolder);
        System.out.println("Number of images found: " + imageFiles.length);

        ArrayList<PuzzlePiece> pieces = new ArrayList<>();
        Map<String, int[][]> binaryMatrices = new HashMap<>();
        Map<String, Integer> realHeights = new HashMap<>();

        for (File file : imageFiles) {
            BufferedImage image = ImageLoader.loadImage(file);
            if (image != null) {
                PuzzlePiece piece = new PuzzlePiece(file.getName(), image);
                pieces.add(piece);

                int[][] matrix = ShapeComparator.imageToBinaryMatrix(image);
                binaryMatrices.put(piece.getName(), matrix);

                int height = ShapeComparator.detectRealHeightFromStableColumns(matrix);
                realHeights.put(piece.getName(), height);
                System.out.println(piece.getName() + " → hauteur réelle détectée : " + height);
            }
        }

        // Regrouper les pièces par hauteur
        Map<Integer, List<PuzzlePiece>> groupesParHauteur = new HashMap<>();
        for (PuzzlePiece piece : pieces) {
            int h = realHeights.get(piece.getName());
            if (h <= 0) continue; // ignorer les erreurs
            groupesParHauteur.computeIfAbsent(h, k -> new ArrayList<>()).add(piece);
        }

        // Générer une ligne par groupe
        for (Map.Entry<Integer, List<PuzzlePiece>> entry : groupesParHauteur.entrySet()) {
            int hauteur = entry.getKey();
            List<PuzzlePiece> groupe = entry.getValue();
            System.out.println("Construction ligne pour hauteur " + hauteur + " (" + groupe.size() + " pièces)");

            if (groupe.size() < 2) continue;

            List<PuzzlePiece> row = PuzzleSolver.buildRowWithoutStart(groupe, 4,  binaryMatrices);

            PuzzleGrid grid = new PuzzleGrid(1, row.size());
            for (int j = 0; j < row.size(); j++) {
                grid.setPiece(0, j, row.get(j));
            }

            BufferedImage finalImage = ImageBuilder.buildImage(grid);
            try {
                File output = new File("output_row_" + hauteur + ".png");
                javax.imageio.ImageIO.write(finalImage, "png", output);
                System.out.println("Ligne générée et enregistrée dans : " + output.getAbsolutePath());
            } catch (Exception e) {
                System.out.println("Erreur sauvegarde : " + e.getMessage());
            }
        }
    }
}
