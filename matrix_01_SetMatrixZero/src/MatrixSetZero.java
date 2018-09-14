/**
 LeetCode – Set Matrix Zeroes (Java)

 73. Set Matrix Zeroes

 Given a m * n matrix, if an element is 0, set its entire row and column to 0.
 Do it in place.

 ================
 INPUT / OUTPUT
 ================
 input matrix:
 1 0 1 1
 1 1 1 1
 1 1 0 1
 1 1 1 1
 after initial setup, matrix is:
 0 0 0 1
 1 1 1 1
 0 1 0 1
 1 1 1 1

 after set zero matrix operation:
 0 0 0 0
 1 0 0 1
 0 0 0 0
 1 0 0 1

 */

public class MatrixSetZero {

    public static void main(String[] args) {

        MatrixSetZero mat = new MatrixSetZero();

        int [][] M1 = {
                {1, 0, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 0, 1},
                {1, 1, 1, 1}
        };

        int [][] M = {
                {0, 1, 2, 0},
                {3, 4, 5, 2},
                {1, 3, 1, 5}
        };

        System.out.println("input matrix: ");
        mat.printMatrix(M);
        mat.setZeroes(M);
        System.out.println();
        System.out.println("after set zero matrix operation: ");
        mat.printMatrix(M);
    }

    public void setZeroes(int[][] M) {

        if (M == null || M.length < 1) {
            return;
        }

        int row = M.length;
        int col = M[0].length;

        boolean row_flag = false;
        boolean col_flag = false;

        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                if (i==0 && M[i][j] == 0) {
                    row_flag = true;
                }
                if (j == 0 && M[i][j] == 0) {
                    col_flag = true;
                }
                if (M[i][j] == 0) {
                    M[i][0] = M[0][j] = 0;
                }
            }
        }

        System.out.println("after initial setup, matrix is: ");
        printMatrix(M);

        for (int i=1; i<row; i++) {
            for (int j=1; j<col; j++) {
                if (M[i][0] == 0 || M[0][j] == 0) {
                    M[i][j] = 0;
                }
            }
        }



        if (row_flag) {
            for (int i=0; i<col; i++) {
                M[0][i] = 0;
            }
        }

        if (col_flag) {
            for (int i=0; i<row; i++) {
                M[i][0] = 0;
            }
        }



    }

    public void setMatrixZero(int [][] M) {
        if (M == null) {
            return;
        }

        int row = M.length;
        int col = M[0].length;

        int r = 0;
        int c = 0;

       while (r < row || c < col) {
           //check row, if any zero present
           for (int i=0; i<col && r < row; i++) {
               if (M[r][i] == 0) {
                   M[r][0] = 0;

               }
           }
           //check col, if any zero present.
           for (int i=0; i<row && c < col; i++) {
               if (M[i][c] == 0) {
                   M[0][c] = 0;
               }
           }
           ++r;
           ++c;
       }

        System.out.println("after initial setup, matrix is: ");
        printMatrix(M);
        for (int i = 1; i < col; i++) {
            if (M[0][i] == 0) {
                for (int j = 0; j < row; j++) {
                    M[j][i] = 0;
                }
            }
        }

        for (int i = 0; i < row; i++) {
            if (M[i][0] == 0) {
                for (int j = 0; j < col; j++) {
                    M[i][j] = 0;
                }
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
