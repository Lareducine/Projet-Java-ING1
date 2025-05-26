package puzzle;

public class BinaryMatrixComparator {

    public static double compareHorizontalDistance(int[][] matrixA, int[][] matrixB) {
        int height = Math.min(matrixA.length, matrixB.length);
        int expectedDistance = -1;

        for (int y = 0; y < height; y++) {
            int last1A = findLastOne(matrixA[y]);
            int first1B = findFirstOne(matrixB[y]);

            if (last1A == -1 || first1B == -1) {
                // Ligne vide dans une des pièces : on ignore cette ligne
                continue;
            }

            int distance = first1B - last1A - 1;

            if (distance < 0) {
                // Les formes se chevauchent horizontalement
                return Double.MAX_VALUE;
            }

            if (expectedDistance == -1) {
                expectedDistance = distance; // Première ligne non vide
            } else if (distance != expectedDistance) {
                return Double.MAX_VALUE; // Pas la même distance partout
            }
        }

        return expectedDistance; // Plus c’est petit, mieux c’est
    }

    private static int findLastOne(int[] row) {
        for (int x = row.length - 1; x >= 0; x--) {
            if (row[x] == 1) return x;
        }
        return -1;
    }

    private static int findFirstOne(int[] row) {
        for (int x = 0; x < row.length; x++) {
            if (row[x] == 1) return x;
        }
        return -1;
    }
}
