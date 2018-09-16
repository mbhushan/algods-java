/**
 200. Number of Islands
 https://leetcode.com/problems/number-of-islands/description/

 Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
 An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 You may assume all four edges of the grid are all surrounded by water.

 Example 1:

 Input:
 11110
 11010
 11000
 00000

 Output: 1
 Example 2:

 Input:
 11000
 11000
 00100
 00011

 Output: 3

 */

class NumberOfIslands {
    public int numIslands(char[][] grid) {

        if (grid == null || grid.length < 1) {
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;

        boolean [][] visited = new boolean[row][col];

        int islandCount = 0;
        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    visited[i][j] = true;
                    //traverse all connected 1s
                    countIslands(grid, i, j, visited);
                    //incr island count.
                    ++islandCount;

                    // System.out.println("visited: " + visited)
                    // System.out.println("i - j: [" + i + "; " + j + "]");
                    //     printMatrix(visited, row, col);
                    //System.out.println();

                }
            }
        }

        return islandCount;

    }

    private void printMatrix(boolean [][] M, int row, int col) {
        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                if (M[i][j]) {
                    System.out.print('1' + " ");
                } else {
                    System.out.print('0' + " ");
                }
            }
            System.out.println();
        }
    }

    private void countIslands(char [][] grid, int row, int col, boolean [][] visited) {


        int [][] directions = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

        for (int i=0; i<directions.length; i++) {
            int r = row + directions[i][0];
            int c = col + directions[i][1];

            if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || visited[r][c]) {
                continue;
            }

            if (grid[r][c] == '1') {
                visited[r][c] = true;
                countIslands(grid, r, c, visited);
            }
        }

    }
}
