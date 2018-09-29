/**
 74. Search a 2D Matrix
 https://leetcode.com/problems/search-a-2d-matrix/description/

 Write an efficient algorithm that searches for a value in an m x n matrix.
 This matrix has the following properties:

 Integers in each row are sorted from left to right.
 The first integer of each row is greater than the last integer of the previous row.
 Example 1:

 Input:
 matrix = [
 [1,   3,  5,  7],
 [10, 11, 16, 20],
 [23, 30, 34, 50]
 ]
 target = 3
 Output: true
 Example 2:

 Input:
 matrix = [
 [1,   3,  5,  7],
 [10, 11, 16, 20],
 [23, 30, 34, 50]
 ]
 target = 13
 Output: false

 */
public class Search2DMatrix {

    public static void main(String[] args) {

        Search2DMatrix sd = new Search2DMatrix();

        int [][] M = {
                {1,   3,  5,  7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        };

        int target = 3;

        System.out.println(sd.searchMatrix(M, target));
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null) {
            return false;
        }

        if (matrix.length < 1) {
            return false;
        }

        int row = matrix.length;
        int col = matrix[0].length;
        int r = 0;
        int c = col-1;
        while (r < row && c >= 0) {
            if (target == matrix[r][c]) {
                return true;
            }
            if (target < matrix[r][c]) {
                --c;
            } else {
                ++r;
            }
        }

        return false;
    }
}
