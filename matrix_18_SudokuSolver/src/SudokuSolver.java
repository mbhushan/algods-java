/**
 Backtracking | Set 7 (Sudoku)
 Given a partially filled 9×9 2D array ‘grid[9][9]’, the goal is to assign digits (from 1 to 9) to the
 empty cells so that every row, column, and subgrid of size 3×3 contains exactly one instance of the digits from 1 to 9.

 =================
 INPUT / OUTPUT
 ================
 input sudoku grid:
 3 0 6 5 0 8 4 0 0
 5 2 0 0 0 0 0 0 0
 0 8 7 0 0 0 0 3 1
 0 0 3 0 1 0 0 8 0
 9 0 0 8 6 3 0 0 5
 0 5 0 0 9 0 6 0 0
 1 3 0 0 0 0 2 5 0
 0 0 0 0 0 0 0 7 4
 0 0 5 2 0 6 3 0 0

 sudoku can be solved: true

 solved sudoku board:
 3 1 6 5 7 8 4 9 2
 5 2 9 1 3 4 7 6 8
 4 8 7 6 2 9 5 3 1
 2 6 3 4 1 5 9 8 7
 9 7 4 8 6 3 1 2 5
 8 5 1 7 9 2 6 4 3
 1 3 8 9 4 7 2 5 6
 6 9 2 3 5 1 8 7 4
 7 4 5 2 8 6 3 1 9

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

        boolean ans = ss.solveSudoku(M, M.length);
        System.out.println("sudoku can be solved: " + ans);
        System.out.println();

        System.out.println("solved sudoku board: ");
        ss.printMatrix(M);


    }

    public boolean solveSudoku(int [][] M, int dim) {

        for (int i=0; i<dim; i++) {
            for (int j=0; j<dim; j++) {
                if (M[i][j] != 0) {
                    continue;
                }

                for (int k=1; k<=dim; k++) {
                    M[i][j] = k;
                    if (checkSudoku(M) && solveSudoku(M, dim)) {
                        return true;
                    }
                    M[i][j] = 0;
                }
                return false;
            }
        }

        return true;
    }


    public boolean checkSudoku(int [][] M) {

        if (M == null) {
            return false;
        }

        int row = M.length;
        int col = M[0].length;

        //check rows.
        for (int r=0; r<row; r++) {
            boolean [] values = new boolean[10];
            for (int c=0; c<col; c++) {
                if (M[r][c] != 0) {
                    if (values[M[r][c]]) {
                        return false;
                    }
                    values[M[r][c]] = true;
                }
            }
        }

        //check cols.
        for (int c=0; c<col; c++) {
            boolean [] values = new boolean[10];
            for (int r=0; r<row; r++) {
                if (M[r][c] != 0) {
                    if (values[M[r][c]]) {
                        return false;
                    }
                    values[M[r][c]] = true;
                }
            }
        }


        //check 3x3 blocks.
        for (int r=0; r<row; r = r+3) {
            for (int c=0; c<col; c=c+3) {
                boolean [] values = new boolean[10];
                for (int i=0; i<3; i++) {
                    for (int j=0; j<3; j++) {
                        if (M[r+i][c+j] != 0) {
                            if (values[M[r+i][c+j]]) {
                                return false;
                            }
                            values[M[r+i][c+j]] = true;
                        }
                    }
                }
            }
        }

        return true;
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
