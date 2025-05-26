package puzzle;

public class SimpleBorderComparator {
    public static double compareRightLeftBorders(int[] right, int[] left) {
        if (right.length != left.length) return Double.MAX_VALUE;

        double total = 0;
        for (int i = 0; i < right.length; i++) {
            int rgbA = right[i];
            int rgbB = left[i];

            int rA = (rgbA >> 16) & 0xFF;
            int gA = (rgbA >> 8) & 0xFF;
            int bA = rgbA & 0xFF;

            int rB = (rgbB >> 16) & 0xFF;
            int gB = (rgbB >> 8) & 0xFF;
            int bB = rgbB & 0xFF;

            int diffR = Math.abs(rA - rB);
            int diffG = Math.abs(gA - gB);
            int diffB = Math.abs(bA - bB);

            total += diffR + diffG + diffB;
        }

        return total / right.length;
    }
}