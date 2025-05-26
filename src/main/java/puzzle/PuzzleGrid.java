package puzzle;

public class PuzzleGrid {
    private PuzzlePiece[][] grid;
    private int rows;
    private int cols;

    public PuzzleGrid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new PuzzlePiece[rows][cols];
    }

    public void setPiece(int row, int col, PuzzlePiece piece) {
        grid[row][col] = piece;
    }

    public PuzzlePiece getPiece(int row, int col) {
        return grid[row][col];
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public void printGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                PuzzlePiece piece = grid[i][j];
                if (piece != null) {
                    System.out.print("[" + piece.getName() + "]");
                } else {
                    System.out.print("[   ]");
                }
            }
            System.out.println();
        }
    }
}
