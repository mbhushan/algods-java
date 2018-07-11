/**
 LeetCode – Number of Islands (Java)

 Given a 2-d grid map of '1's (land) and '0's (water), count the number of islands.
 An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 You may assume all four edges of the grid are all surrounded by water.
 Example 1:

 11110
 11010
 11000
 00000

 Answer: 1

 ===============
 INPUT / OUTPUT
 ===============
 input matrix:
 1 1 1 1 0
 1 1 0 1 0
 1 1 0 0 0
 0 0 0 0 0
 at count :1, matrix looks like:
 0 0 0 0 0
 0 0 0 0 0
 0 0 0 0 0
 0 0 0 0 0

 island count: 1
 input matrix:
 1 0 1 0 0
 1 0 0 1 0
 1 1 0 0 0
 0 0 0 0 0
 at count :1, matrix looks like:
 0 0 1 0 0
 0 0 0 1 0
 0 0 0 0 0
 0 0 0 0 0

 at count :2, matrix looks like:
 0 0 0 0 0
 0 0 0 0 0
 0 0 0 0 0
 0 0 0 0 0

 island count: 2
 input matrix:
 1 1 0 1 0
 0 1 0 1 0
 0 0 0 0 0
 1 0 1 0 1
 at count :1, matrix looks like:
 0 0 0 1 0
 0 0 0 1 0
 0 0 0 0 0
 1 0 1 0 1

 at count :2, matrix looks like:
 0 0 0 0 0
 0 0 0 0 0
 0 0 0 0 0
 1 0 1 0 1

 at count :3, matrix looks like:
 0 0 0 0 0
 0 0 0 0 0
 0 0 0 0 0
 0 0 1 0 1

 at count :4, matrix looks like:
 0 0 0 0 0
 0 0 0 0 0
 0 0 0 0 0
 0 0 0 0 1

 at count :5, matrix looks like:
 0 0 0 0 0
 0 0 0 0 0
 0 0 0 0 0
 0 0 0 0 0

 island count: 5
 */

public class IslandCount {

    public static void main(String [] args) {

        IslandCount ic = new IslandCount();

        int [][][] islands = {
                {       {1, 1, 1, 1, 0},
                        {1, 1, 0, 1, 0},
                        {1, 1, 0, 0, 0},
                        {0, 0, 0, 0, 0}
                },
                {
                        {1, 0, 1, 0, 0},
                        {1, 0, 0, 1, 0},
                        {1, 1, 0, 0, 0},
                        {0, 0, 0, 0, 0}
                },
                {
                        {1, 1, 0, 1, 0},
                        {0, 1, 0, 1, 0},
                        {0, 0, 0, 0, 0},
                        {1, 0, 1, 0, 1}
                }
        };

        for (int i=0; i<islands.length; i++) {
            System.out.println("input matrix: ");
            ic.printMatrix(islands[i]);
            int numIslands = ic.countIslandsDFS(islands[i]);
            System.out.println("island count: " + numIslands);
        }


    }


    public int countIslandsDFS(int [][] M) {

        int row = M.length;
        int col = M[0].length;

        int count = 0;

        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                if (M[i][j] == 1) {
                    ++count;
                    islandMerge(M, row, col, i, j);
                    System.out.println("at count :" +count + ", matrix looks like: ");
                    printMatrix(M);
                    System.out.println();
                }
            }
        }


        return count;
    }

    private void islandMerge(int [][] M, int row, int col, int r, int c) {
        if (r >= row || c >= col || r < 0 || c < 0) {
            return;
        }

        if (M[r][c] == 0) {
            return;
        }

        if (M[r][c] == 1) {
            M[r][c] = 0;
        }

        for (int i=-1; i<=1; i++) {
            for (int j=-1; j<=1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
               islandMerge(M, row, col, r+i, c+j);
            }
        }
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
