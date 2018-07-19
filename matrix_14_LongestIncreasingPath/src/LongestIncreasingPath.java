import java.util.ArrayList;
import java.util.List;

/**
 Longest Increasing Path in Matrix
 Given a matrix of N rows and M columns. From m[i][j],
 we can move to m[i+1][j], if m[i+1][j] > m[i][j], or can move to m[i][j+1] if m[i][j+1] > m[i][j].
 The task is print longest path length if we start from (0, 0).

 Examples:

 Input : N = 4, M = 4
 m[][] = { { 1, 2, 3, 4 },
 { 2, 2, 3, 4 },
 { 3, 2, 3, 4 },
 { 4, 5, 6, 7 } };
 Output : 7
 Longest path is 1 2 3 4 5 6 7.

 Input : N = 2, M =2
 m[][] = { { 1, 2 },
 { 3, 4 } };
 Output :3
 Longest path is either 1 2 4 or
 1 3 4.

==================
 INPUT / OUTPUT
 =================
 input matrix:
 1 2 3 4
 2 2 3 4
 3 2 3 4
 4 5 6 7
 longest increasing path:
 [1, 2, 3, 4, 5, 6, 7]
 */

public class LongestIncreasingPath {

    public static void main(String [] args) {
        LongestIncreasingPath lip = new LongestIncreasingPath();

        int [][] M = {
                { 1, 2, 3, 4 },
                { 2, 2, 3, 4 },
                { 3, 2, 3, 4 },
                { 4, 5, 6, 7 },
        };

        lip.longestInceasingPath(M);

        lip.longestIncrPathDP(M);


    }


    public void longestInceasingPath(int [][] M) {
        if (M == null) {
            return;
        }

        int row = M.length;
        int col = M[0].length;

        boolean [][] visited = new boolean[row][col];
        List<Integer> path = new ArrayList<>();
        List<Integer> result = new ArrayList<>();

        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                visited = new boolean[row][col];
                path = new ArrayList<>();
                path.add(M[i][j]);
                dfs(M, visited, i, j, path, result);
               // System.out.println("path: " + path);
            }
        }

        System.out.println("input matrix: ");
        printMatrix(M);

        System.out.println("longest increasing path: ");
        System.out.println(result);

    }

    private void dfs(int [][] M, boolean [][] visited, int r, int c, List<Integer> path, List<Integer> result) {

        int [][] directions = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

        for (int i=0; i<directions.length; i++) {
            int r1 = r + directions[i][0];
            int c1 = c + directions[i][1];

            if (r1 < 0 || r1 >= M.length || c1 < 0 || c1 >= M[0].length) {
                continue;
            }

            if (M[r1][c1] > M[r][c]) {
                visited[r1][c1] = true;
                path.add(M[r1][c1]);
                dfs(M, visited, r1, c1, path, result);
            }
        }
        //System.out.println("path: " + path);
        if (path.size() > result.size()) {
            result.clear();
            result.addAll(path);
        }
        path.clear();
    }

    public void longestIncrPathDP(int [][] M) {
        if (M == null) {
            return;
        }

        int row = M.length;
        int col = M[0].length;

        int [][] T = new int[row][col];
        T[0][0] = 1;

        for (int i=1; i<col; i++) {
            if (M[0][i] > M[0][i-1]) {
                T[0][i] = T[0][i-1] + 1;
            } else {
                T[0][i] = 1;
            }
        }

        for (int i=1; i<row; i++) {
            if (M[i][0] > M[i-1][0]) {
                T[i][0] = T[i-1][0] + 1;
            } else {
                T[i][0] = 1;
            }
        }

        int lip = 1;

        for (int i=1; i<row; i++) {
            for (int j=1; j<col; j++) {
                if (M[i][j] > M[i-1][j] && M[i][j] > M[i][j-1] ) {
                    T[i][j] = Math.max(T[i-1][j], T[i][j-1]) + 1;
                } else if (M[i][j] > M[i-1][j] ) {
                    T[i][j] = 1 + T[i-1][j];
                } else if (M[i][j] > M[i][j-1]) {
                    T[i][j] = 1 + T[i][j-1];
                } else {
                    T[i][j] = 1 ;
                }

                if (lip < T[i][j]) {
                    lip = T[i][j];
                }

            }
        }

        System.out.println("printing DP matrix: ");
        printMatrix(T);
        System.out.println("longest increasing path: " + lip);
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
