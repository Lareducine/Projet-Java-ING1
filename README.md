"# Projet-Java-ING1" 

## CY-Puzzle

CY-Puzzle est un projet Java permettant de reconstituer automatiquement un puzzle à partir d’images découpées en pièces. Le système repose sur une analyse des formes des pièces pour identifier celles qui s’emboîtent correctement, sans orientation imposée au départ.

## Fonctionnalités principales

- Chargement automatique des pièces depuis un dossier.
- Conversion des pièces en matrices binaires (1 = zone utile, 0 = vide).
- Comparateur de forme basé sur la distance entre les bords des pièces.
- Détection de la hauteur réelle des pièces à l’aide de colonnes stables.
- Construction automatique de lignes de pièces compatibles entre elles.
- Génération d’une image finale représentant les lignes reconstituées.

## Structure du projet

- `Main.java` : point d’entrée, lance le traitement et la construction du puzzle.
- `PuzzlePiece.java` : classe représentant une pièce du puzzle.
- `ShapeComparator.java` : contient les méthodes d’analyse des formes.
- `PuzzleSolver.java` : assemble les pièces en lignes selon leur compatibilité.
- `ImageLoader.java` : utilitaires pour charger les images depuis un dossier.
- `ImageBuilder.java` : assemble les lignes en une seule image.
- `PuzzleGrid.java` : structure de stockage de la grille du puzzle.
