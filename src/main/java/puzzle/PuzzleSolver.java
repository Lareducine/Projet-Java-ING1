package puzzle;

import java.util.*;
import java.awt.image.BufferedImage;

public class PuzzleSolver {

    public static List<PuzzlePiece> buildRowWithoutStart(List<PuzzlePiece> pieces, int count, Map<String, int[][]> binaryMatrices) {
        List<PuzzlePiece> row = new ArrayList<>();
        Set<PuzzlePiece> remaining = new HashSet<>(pieces);

        // Étape 1 : trouver la pièce avec le bord gauche le PLUS IRRÉGULIER (flatness max)
        PuzzlePiece first = null;
        int bestFlatness = -1;

        System.out.println("== Flatness (maximale) de chaque pièce ==");
        for (PuzzlePiece piece : pieces) {
            int[][] matrix = binaryMatrices.get(piece.getName());
            int[] leftCol = ShapeComparator.extractLeftColumn(matrix);
            int[] range = ShapeComparator.usefulVerticalRange(leftCol);
            int flatness = range[1] - range[0];

            System.out.println(piece.getName() + " → flatness = " + flatness);

            if (flatness > bestFlatness && flatness >= 0) {
                bestFlatness = flatness;
                first = piece;
            }
        }

        if (first == null) {
            System.out.println("Aucune pièce avec un bord gauche détecté.");
            return row;
        }

        row.add(first);
        remaining.remove(first);
        System.out.println("Première pièce sélectionnée : " + first.getName() + " (flatness = " + bestFlatness + ")");

        // Étape 2 : compléter la ligne
        while (row.size() < count && !remaining.isEmpty()) {
            PuzzlePiece current = row.get(row.size() - 1);
            PuzzlePiece bestMatch = null;
            double bestScore = Double.MAX_VALUE;

            for (PuzzlePiece candidate : remaining) {
                double score = ShapeComparator.compareShapeDistance(
                    binaryMatrices.get(current.getName()),
                    binaryMatrices.get(candidate.getName())
                );
                System.out.println("Comparaison : " + current.getName() + " → " + candidate.getName() + " = " + score);
                if (score < bestScore) {
                    bestScore = score;
                    bestMatch = candidate;
                }
            }

            if (bestMatch != null) {
                row.add(bestMatch);
                remaining.remove(bestMatch);
                System.out.println("Ajout de la pièce : " + bestMatch.getName() + " (score = " + bestScore + ")");
            } else {
                System.out.println("Aucun voisin trouvé pour " + current.getName());
                break;
            }
        }

        return row;
    }
}
