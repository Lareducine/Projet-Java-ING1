package puzzle;

import java.awt.image.BufferedImage;

public class ShapeComparator {

    // Transforme une image en matrice binaire (0 = pixel noir, 1= autre)
    public static int[][] imageToBinaryMatrix(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        int[][] matrix = new int[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = img.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                // On considère noir pur comme pixel utile
                matrix[y][x] = (r == 0 && g == 0 && b == 0) ? 0 : 1;
            }
        }

        return matrix;
    }

    // Compare deux matrices représentant une pièce A et une pièce B
  public static double compareShapeDistance(int[][] matrixA, int[][] matrixB) {
        int height = Math.min(matrixA.length, matrixB.length);
        int lignesOK = 0;
        int lignesTrou = 0;
        int lignesChevauchement = 0;
        int total = 0;
        int sommeDistance = 0;

        for (int y = 0; y < height; y++) {
            int lastA = findLastOne(matrixA[y]);
            int firstB = findFirstOne(matrixB[y]);

            if (lastA == -1 || firstB == -1) continue;

            int distance = firstB - lastA - 1;

            // comptage ligne utile
            total++;
            sommeDistance += distance;

            if (distance < 0) {
                lignesChevauchement++;
            } else if (distance == 0) {
                lignesOK++;
            } else {
                lignesTrou++;
            }
        }

        if (total == 0) return Double.MAX_VALUE;

        
        double score = (lignesChevauchement*10) + (lignesTrou*10);

       
        double moyenne = (double) sommeDistance / total;

       
        score += moyenne * 0.2;  // 

        return score / total;
    }

    // Trouve l'indice du dernier 1 dans une ligne
    private static int findLastOne(int[] row) {
        for (int i = row.length - 1; i >= 0; i--) {
            if (row[i] == 1) return i;
        }
        return -1;
    }

    // Trouve l'indice du premier 1 dans une ligne
    private static int findFirstOne(int[] row) {
        for (int i = 0; i < row.length; i++) {
            if (row[i] == 1) return i;
        }
        return -1;
    }

    public static int detectRealHeightFromStableColumns(int[][] matrix) {
        int width = matrix[0].length;
        int height = matrix.length;
  
        int[] columnHeights = new int[width];

        for (int x = 0; x < width; x++) {
            int count = 0;
            for (int y = 0; y < height; y++) {
                if (matrix[y][x] == 1) count++;
            }
            columnHeights[x] = count;
        }

        for (int i = 0; i <= width - 10; i++) {
            boolean stable = true;
            int h = columnHeights[i];
            for (int j = 1; j < 10; j++) {
                if (columnHeights[i + j] != h) {
                    stable = false;
                    break;
                }
            }
            if (stable && h > 0) {
                return h; // Hauteur réelle détectée
            }
        }

        
        return -1;
    }

    public static int[] extractLeftColumn(int[][] matrix) {
        int[] col = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            col[i] = matrix[i][0];
        }
        return col;
    }

    public static int[] extractRightColumn(int[][] matrix) {
        int[] col = new int[matrix.length];
        int width = matrix[0].length;
        for (int i = 0; i < matrix.length; i++) {
            col[i] = matrix[i][width - 1];
        }
        return col;
    }

    public static int[] usefulVerticalRange(int[] column) {
        int top = -1, bottom = -1;
        for (int y = 0; y < column.length; y++) {
            if (column[y] == 1) {
                if (top == -1) top = y;
                bottom = y;
            }
        }
        return (top == -1) ? new int[]{-1, -1} : new int[]{top, bottom};
    }
}




