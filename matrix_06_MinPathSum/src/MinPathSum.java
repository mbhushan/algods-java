/**
 LeetCode – Minimum Path Sum (Java)

 Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right
 which minimizes the sum of all numbers along its path.

 ================
 INPUT / OUTPUT
 =================
 input matrix:
 1 2 3
 4 8 2
 1 5 3

 min path cost (movement: right and down): 11
 min path cost (movement: right, down, diagonally): 8

 DP Matrix:
 1 3 6
 5 9 5
 6 10 8

 DP Approach: min path cost (movement: right, down, diagonally): 8
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
        pathCost = minPathSum3Directions(M, r, c, row-1, col-1);
        System.out.println("min path cost (movement: right, down, diagonally): " + pathCost);
        System.out.println();

        pathCost = minPathSum3DirDP(M);
        System.out.println("DP Approach: min path cost (movement: right, down, diagonally): " + pathCost);
    }

    //DP Solution: only traverse down, right and diagonally lower cells from a given cell.
    public int minPathSum3DirDP(int [][] M) {

        if (M == null) {
            return 0;
        }
        int row = M.length;
        int col = M[0].length;
        int [][] T = new int[row][col];

        T[0][0] = M[0][0];

        for (int i=1; i<col; i++) {
            T[0][i] = M[0][i] + T[0][i-1];
        }

        for (int i=1; i<row; i++) {
            T[i][0] += M[i][0] + T[i-1][0];
        }

        for (int i=1; i<row; i++) {
            for (int j=1; j<col; j++) {
                T[i][j] = M[i][j] + Math.min(T[i-1][j-1], Math.min(T[i-1][j], T[i][j-1]));
            }
        }

        //System.out.println("min cost path: " + T[row-1][col-1]);
        System.out.println("DP Matrix: ");
        printMatrix(T);
        System.out.println();

        return T[row-1][col-1];

    }

    //only traverse down, right and diagonally lower cells from a given cell, i.e.,
    // from a given cell (i, j), cells (i+1, j), (i, j+1) and (i+1, j+1) can be traversed.
    private int minPathSum3Directions(int [][] M, int r, int c, int row, int col) {
        if (r == row && c == col) {
            return M[row][col];
        }

        if (r < row && c < col) {
            int cost1 = M[r][c] + minPathSum3Directions(M, r+1, c, row, col);
            int cost2 = M[r][c] + minPathSum3Directions(M, r, c+1, row, col);
            int cost3 = M[r][c] + minPathSum3Directions(M, r+1, c+1, row, col);
            return Math.min(cost1, Math.min(cost2, cost3));
        }

        if (r < row) {
            return M[r][c] + minPathSum3Directions(M, r+1, c, row, col);
        }

        if (c < col) {
            return M[r][c] + minPathSum3Directions(M, r, c+1, row, col);
        }

        return 0;
    }

    // traverse right and botton direction only.
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
