/**
 LeetCode – Unique Paths (Java)

 A robot is located at the top-left corner of a m x n grid. It can only move either down or right
 at any point in time. The robot is trying to reach the bottom-right corner of the grid.

 How many possible unique paths are there?

 */

public class UniquePaths {

    public static void main(String [] args) {
        UniquePaths up = new UniquePaths();

        int row = 3;
        int col = 3;

        System.out.println("unique paths for (" + row + ", " + col + ")");
        System.out.println(up.uniquePathsDFS(row, col));
    }

    public int uniquePathsDP(int m ,int n) {
        return 0;
    }

    public int uniquePathsDFS(int m, int n) {
        int i = 0;
        int j = 0;

        return uniquePathsDFS(m, n, 1, 1);
    }

    private int uniquePathsDFS(int row, int col, int i, int j) {
        if (i == row && j == col) {
            return 1;
        }

        if (i < row && j < col) {
            return uniquePathsDFS(row, col, i+1, j) + uniquePathsDFS(row, col, i, j+1);
        }

        if (i < row) {
            return uniquePathsDFS(row, col, i+1, j);
        }

        if (j < col) {
            return uniquePathsDFS(row, col, i, j+1);
        }

        return 0;
    }
}
