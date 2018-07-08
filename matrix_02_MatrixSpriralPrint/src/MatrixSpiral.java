/**
 LeetCode – Spiral Matrix (Java)

 Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

 For example, given the following matrix:
 [
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
 ]
 You should return [1,2,3,6,9,8,7,4,5].

 ==================
 INPUT / OUTPUT
 =================
 printing input matrix:
 1 2 3
 4 5 6
 7 8 9

 spiral matrix:
 1 2
 3 6
 9 8
 7 4
 5

 */
public class MatrixSpiral {

    public static void main(String[] args) {
        MatrixSpiral ms = new MatrixSpiral();

        int [][] M = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };

        System.out.println("printing input matrix:");
        ms.printMatrix(M);
        System.out.println();
        System.out.println("spiral matrix: ");
        ms.spiralPrint(M);


    }

    public void spiralPrint(int [][] M) {
        if (M == null) {
            return;
        }

        int row = M.length;
        int col = M[0].length;

        int r = 0;
        int c = 0;

        while (row > 0 && col > 0) {

            //if one row/column left, no circle can be formed
            if (row == 1) {
                for (int i=0; i<col; i++) {
                    System.out.print(M[r][c++] + " ");
                }
                break;
            } else if (col == 1) {
                for (int i=0; i<row; i++) {
                    System.out.print(M[r++][c] + " ");
                }
                break;
            }

            //top - move right
            for (int i=0; i<col-1; i++) {
                System.out.print(M[r][c++] + " ");
            }

            System.out.println();

            //right - move down
            for (int i=0; i<row-1; i++) {
                System.out.print(M[r++][c] + " ");
            }

            System.out.println();

            //bottom - move left
            for (int i=0; i<col-1; i++) {
                System.out.print(M[r][c--] + " ");
            }

            System.out.println();

            //left - move up
            for (int i=0; i<row-1; i++) {
                System.out.print(M[r--][c] + " ");
            }
            System.out.println();

            r++;
            c++;
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
