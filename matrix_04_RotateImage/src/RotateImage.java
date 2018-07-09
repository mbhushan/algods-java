/**
 LeetCode – Rotate Image (Java)

 You are given an n x n 2D matrix representing an image.

 Rotate the image by 90 degrees (clockwise).
 By using the relation "matrix[i][j] = matrix[n-1-j][i]", we can loop through the matrix.

 ===============
 INPUT / OUTPUT
 ===============
 input matrix:
 1 4 7 11 15
 2 5 8 12 19
 3 6 9 16 22
 10 13 14 17 24
 18 21 23 26 30

 after rotation by 90 degrees:
 18 13 7 2 1
 30 13 8 5 4
 3 6 9 16 22
 26 17 14 12 11
 30 24 23 19 15

 */

public class RotateImage {

    public static void main(String [] args) {
        int [][] M = {
                {1,   4,  7, 11, 15},
                {2,   5,  8, 12, 19},
                {3,   6,  9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30},
        };

        RotateImage ri = new RotateImage();

        System.out.println("input matrix: ");
        ri.printMatrix(M);

        System.out.println();
        ri.rotateImage(M);
        System.out.println("after rotation by 90 degrees: ");
        ri.printMatrix(M);
    }

    public void rotateImage(int [][] M) {
        if (M == null) {
            return;
        }

        int row = M.length;
        int col = M[0].length;
        int n = row-1;

        for (int i=0; i<Math.ceil(row/2); i++) {
            for (int j=0; j<Math.ceil(col/2); j++) {
                int temp = M[i][j];
                M[i][j] = M[n-j][j];
                M[n-j][i] = M[n-i][n-j];
                M[n-i][n-j] = M[j][n-i];
                M[j][n-i] = temp;
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
