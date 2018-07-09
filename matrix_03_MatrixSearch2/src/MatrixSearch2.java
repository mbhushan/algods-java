/**
 LeetCode – Search a 2D Matrix II (Java)

 Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

 Integers in each row are sorted in ascending from left to right.
 Integers in each column are sorted in ascending from top to bottom.


 For example, consider the following matrix:

 }
 }1,   4,  7, 11, 15],
 }2,   5,  8, 12, 19],
 }3,   6,  9, 16, 22],
 }10, 13, 14, 17, 24],
 }18, 21, 23, 26, 30]
 ]
 Given target = 5, return true.

 ================
 INPUT / OUTPUT
 ==================
 input matrix:
 1 4 7 11 15
 2 5 8 12 19
 3 6 9 16 22
 10 13 14 17 24
 18 21 23 26 30

 2 found: true
 6 found: true
 14 found: true
 24 found: true
 18 found: true
 0 found: false
 20 found: false
 26 found: true
 29 found: false
 30 found: true
 */

public class MatrixSearch2 {

    public static void main(String [] args) {

        MatrixSearch2 ms = new MatrixSearch2();

        int [][] M = {
                {1,   4,  7, 11, 15},
                {2,   5,  8, 12, 19},
                {3,   6,  9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30},
        };

        System.out.println("input matrix: ");
        ms.printMatrix(M);

        System.out.println();
        int [] keys = {2, 6, 14, 24, 18, 0, 20, 26, 29, 30};

        for (int i=0; i<keys.length; i++) {
           boolean ans = ms.searchMatrix(M, keys[i]);
            System.out.println(keys[i] + " found: " + ans);
        }

        //System.out.println(0 + " found: " + ms.searchMatrix(M, 0));

    }

    public boolean searchMatrix(int [][] M, int key) {
        if (M == null) {
            return false;
        }

        int row = M.length;
        int col = M[0].length;

        int r = 0;
        int c = col-1;

        while (r < row && c >= 0) {
            if (M[r][c] == key) {
                return true;
            }

            if (key > M[r][c]) {
                ++r;
            } else {
                --c;
            }
        }

        return false;
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
