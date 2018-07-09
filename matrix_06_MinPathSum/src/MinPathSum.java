/**
 LeetCode – Minimum Path Sum (Java)

 Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right
 which minimizes the sum of all numbers along its path.
 */

public class MinPathSum {

    public static void main(String [] args) {
        MinPathSum mps = new MinPathSum();

        int M[][] = {
                {1, 2, 3},
                {4, 8, 2},
                {1, 5, 3}
        };

        System.out.println("input matrix: ");

        mps.printMatrix(M);
        System.out.println();
        mps.minPathSum2Directions(M);
    }

    public void minPathSum2Directions(int [][] M) {
        int row = M.length;
        int col = M[0].length;
        int r = 0;
        int c = 0;
        int pathCost = minPathSum2Directions(M, r, c, row-1, col-1);
        System.out.println("min path cost (movement: right and down): " + pathCost);
    }

    private int minPathSum2Directions(int [][] M, int r, int c, int row, int col) {
        if (r == row && c == col) {
            return M[row][col];
        }

        if (r < row && c < col) {
            int cost1 = M[r][c] + minPathSum2Directions(M, r+1, c, row, col);
            int cost2 = M[r][c] + minPathSum2Directions(M, r, c+1, row, col);
            return Math.min(cost1, cost2);
        }

        if (r < row) {
            return M[r][c] + minPathSum2Directions(M, r+1, c, row, col);
        }

        if (c < col) {
            return M[r][c] + minPathSum2Directions(M, r, c+1, row, col);
        }

        return 0;
    }

    public void printMatrix(int [][] M) {
        if (M == null) {
            return;
        }
        int row = M.length;
        int col = M[0].length;

        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                System.out.print(M[i][j] + " ");
            }
            System.out.println();
        }
    }

}
