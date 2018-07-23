/**

 Leetcode: Walls and Gates

 You are given a m x n 2D grid initialized with these three possible values.

 -1 - A wall or an obstacle.
 0 - A gate.

 INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume
 that the distance to a gate is less than2147483647.

 Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate,
 it should be filled with INF.

 For example, given the 2D grid:
 INF  -1  0  INF
 INF INF INF  -1
 INF  -1 INF  -1
 0  -1 INF INF

 After running your function, the 2D grid should be:
 3  -1   0   1
 2   2   1  -1
 1  -1   2  -1
 0  -1   3   4

 Understand the problem:

 It is very classic backtracking problem. We can start from each gate (0 point), and searching for its neighbors.
 We can either use DFS or BFS solution.
 =================
 INPUT / OUTPUT
 =================
 input matrix:
 INF   -1    0    INF
 INF   INF   INF   -1
 INF   -1    INF   -1
 0    -1    INF   INF

 matrix with distance updated:
 3    -1    0    1
 2    2    1    -1
 1    -1    2    -1
 0    -1    3    4



 */

public class WallsAndGates {

    public static void main(String[] args) {
        WallsAndGates wg = new WallsAndGates();

        int [][] M = {
                {Integer.MAX_VALUE, -1, 0, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, -1},
                {Integer.MAX_VALUE, -1, Integer.MAX_VALUE, -1},
                {0, -1, Integer.MAX_VALUE, Integer.MAX_VALUE},
        };

        System.out.println("input matrix: ");
        wg.printMatrix(M);
        System.out.println();

        wg.findDistances(M);
        System.out.println("matrix with distance updated: ");
        wg.printMatrix(M);
        System.out.println();



    }

    public void findDistances(int [][] M) {

        int row = M.length;
        int col = M[0].length;

        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                boolean [][] visited = new boolean[row][col];
                if (M[i][j] == 0) {
                    dfs(M, visited, 0, i, j);
                }
            }
        }

    }

    public void dfs(int [][] M, boolean [][] visited, int distance, int r, int c) {

        if (distance < M[r][c] && M[r][c] != 0) {
            M[r][c] = distance;
        }

        int [][] directions = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

        for (int i=0; i<directions.length; i++) {
            int r1 = r + directions[i][0];
            int c1 = c + directions[i][1];

            if (r1 < 0 || r1 >= M.length || c1 < 0 || c1 >= M[0].length || M[r1][c1] == -1 || M[r1][c1] == 0) {
                continue;
            }
            if (visited[r1][c1]) {
                continue;
            }

            visited[r1][c1] = true;
            dfs(M, visited, distance+1, r1, c1);

        }
        visited[r][c] = false;
    }

    public void printMatrix(int [][] M) {
        if (M == null) {
            return;
        }
        int row = M.length;
        int col = M[0].length;

        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                if (M[i][j] == Integer.MAX_VALUE) {
                    System.out.print("INF" + "   ");
                } else {
                    System.out.print(M[i][j] + "    ");
                }
            }
            System.out.println();
        }
    }
}
