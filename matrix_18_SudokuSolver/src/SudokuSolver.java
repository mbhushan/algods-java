/**
 Backtracking | Set 7 (Sudoku)
 Given a partially filled 9×9 2D array ‘grid[9][9]’, the goal is to assign digits (from 1 to 9) to the
 empty cells so that every row, column, and subgrid of size 3×3 contains exactly one instance of the digits from 1 to 9.

 =================
 INPUT / OUTPUT
 ================

 */

public class SudokuSolver {

    public static void main(String[] args) {
        SudokuSolver ss = new SudokuSolver();

        int [][] M = {
                {3, 0, 6, 5, 0, 8, 4, 0, 0},
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                {0, 0, 5, 2, 0, 6, 3, 0, 0}
        };

        System.out.println("input sudoku grid: ");
        ss.printMatrix(M);
        System.out.println();


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
