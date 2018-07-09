/**
 LeetCode – Search a 2D Matrix (Java)

 Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has properties:

 1) Integers in each row are sorted from left to right. 2) The first integer of each row
 is greater than the last integer of the previous row.

 For example, consider the following matrix:
 [
 [1,   3,  5,  7],
 [10, 11, 16, 20],
 [23, 30, 34, 50]
 ]
 Given target = 3, return true.

 ================
 INPUT / OUTPUT
 ================

 input matrix:
 1 3 5 7
 10 11 16 20
 23 30 34 50

 3 found: true
 23 found: true
 12 found: false
 11 found: true
 16 found: true
 7 found: true
 50 found: true
 35 found: false

 */

public class MatrixSearch {

    public static void main(String[] args) {

        MatrixSearch ms = new MatrixSearch();

        int [][] M = {
                {1,   3,  5,  7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        };
        int [] keys = {3, 23, 12, 11, 16, 7, 50, 35};

        System.out.println("input matrix: ");

        ms.printMatrix(M);

        System.out.println();
        for (int i=0; i<keys.length; i++) {
            System.out.println(keys[i] + " found: " + ms.searchMatrix(M, keys[i]));
            System.out.println(keys[i] + " found (binary search): " + ms.binarySearchMatrix(M, keys[i]));
        }


    }

    public boolean binarySearchMatrix(int [][] M, int key) {

        if (M == null || M.length < 1 || M[0].length < 1) {
            return false;
        }

        int row = M.length;
        int col = M[0].length;
        int low = 0;
        int high = row*col-1;
        while (low <= high) {
            int mid = low + (high-low)/2;
            int r = mid / col;
            int c = mid % col;
            if (M[r][c] == key) {
                return true;
            } else if (key < M[r][c]){
               high = mid-1;
            } else {
                low = mid+1;
            }
        }

        return false;
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
            } else if (key < M[r][c]) {
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
