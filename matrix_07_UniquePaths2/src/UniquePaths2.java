/**
 LeetCode – Unique Paths II (Java)

 Follow up for "Unique Paths":

 Now consider if some obstacles are added to the grids. How many unique paths would there be?


 An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 For example, there is one obstacle in the middle of a 3x3 grid as illustrated below,

 [
 [0,0,0],
 [0,1,0],
 [0,0,0]
 ]
 the total number of unique paths is 2.

 ===============
 INPUT / OUTPUT
 ==============
 printing DP matrix:
 1 1 1
 1 0 1
 1 1 2

 find unique paths: 2
 */

public class UniquePaths2 {

    public static void main(String[] args) {

        UniquePaths2 up = new UniquePaths2();

        int [][] paths = {
                   {0,0,0},
                    {0,1,0},
                    {0,0,0}
                };

        int result = up.findUniquePathsDP(paths);

        System.out.println("find unique paths: " + result);



    }

    public int findUniquePathsDP(int [][] M) {

        int row = M.length;
        int col = M[0].length;

        int [][] T = new int[row][col];

        for (int i=0; i<row; i++) {
            if (M[i][0] != 1) {
                T[i][0] = 1;
            }
        }

        for (int i=0; i<col; i++) {
            if (M[0][i] != 1) {
                T[0][i] = 1;
            }
        }

        for (int i=1; i<row; i++) {
            for (int j=1; j<col; j++) {
                if (M[i][j] == 1) {
                    T[i][j] = 0;
                } else {
                    T[i][j] = T[i-1][j] + T[i][j-1];
                }
            }
        }

        System.out.println("printing DP matrix: ");
        printMatrix(T);
        System.out.println();

        return T[row-1][col-1];
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
