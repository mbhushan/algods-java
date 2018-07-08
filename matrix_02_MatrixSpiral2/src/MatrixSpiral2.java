/**
 LeetCode – Spiral Matrix II (Java)

 Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order. For example, given n = 4,

 [
 [1,   2,  3, 4],
 [12, 13, 14, 5],
 [11, 16, 15, 6],
 [10,  9,  8, 7]
 ]

 ====================
 INPUT / OUTPUT
 ===================
 generating spiral matrix:
 printing generated matrix:
 1 2 3 4
 12 13 14 5
 11 16 15 6
 10 9 8 7

 */

public class MatrixSpiral2 {

    public static void main(String[] args) {

        MatrixSpiral2 ms = new MatrixSpiral2();

        int row = 4;
        int col = 4;
        int [][]M = new int[row][col];
        System.out.println("generating spiral matrix: ");
        ms.generateSpiral(M);

        System.out.println("printing generated matrix: ");
        ms.printMatrix(M);


    }

    public void generateSpiral(int [][] M) {
        if (M == null) {
            return;
        }

        int row = M.length;
        int col = M[0].length;
        int value = 1;

        int r = 0;
        int c = 0;

        while (row > 0 && col > 0) {

            if (row == 1) {
                for (int i=0; i<col; i++) {
                    M[r][c++] = value++;
                }
                break;
            } else if (col == 1) {
                for (int i=0; i<row; i++) {
                    M[r++][c] = value++;
                }
                break;
            }

            //go right
            for (int i=0; i<col-1; i++) {
                M[r][c++] = value++;
            }

            //go down
            for (int i=0; i<row-1; i++) {
                M[r++][c] = value++;
            }

            //go left
            for (int i=0; i<col-1; i++) {
                M[r][c--] = value++;
            }

            //go up
            for (int i=0; i<row-1; i++) {
                M[r--][c] = value++;
            }

            ++r;
            ++c;
            row = row-2;
            col = col-2;
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
