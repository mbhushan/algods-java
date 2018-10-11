/**
 286. Walls and Gates
 https://leetcode.com/problems/walls-and-gates/description/

 You are given a m x n 2D grid initialized with these three possible values.

 -1 - A wall or an obstacle.
 0 - A gate.
 INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent
 INF as you may assume that the distance to a gate is less than 2147483647.
 Fill each empty room with the distance to its nearest gate.
 If it is impossible to reach a gate, it should be filled with INF.

 Example:

 Given the 2D grid:

 INF  -1  0  INF
 INF INF INF  -1
 INF  -1 INF  -1
 0  -1 INF INF
 After running your function, the 2D grid should be:

 3  -1   0   1
 2   2   1  -1
 1  -1   2  -1
 0  -1   3   4
 */
public class WallsAndGates {

    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length < 1) {
            return;
        }
        int row = rooms.length;
        int col = rooms[0].length;

        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                if (rooms[i][j] == 0) {
                    dfs(rooms, i, j);
                }
            }
        }
    }

    private void dfs(int [][] rooms, int r, int c) {

        int [][] directions = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

        for (int i=0; i<directions.length; i++) {
            int row = r + directions[i][0];
            int col = c + directions[i][1];
            if (row < 0 || col < 0 || row >= rooms.length || col >= rooms[0].length || rooms[row][col] == -1) {
                continue;
            }
            if (rooms[row][col] > rooms[r][c]+1) {
                rooms[row][col] = rooms[r][c]+1;
                dfs(rooms, row, col);
            }
        }

    }
}
