/**
 Write a program to solve a Sudoku puzzle by filling the empty cells.

 A sudoku solution must satisfy all of the following rules:

 Each of the digits 1-9 must occur exactly once in each row.
 Each of the digits 1-9 must occur exactly once in each column.
 Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 Empty cells are indicated by the character '.'.


 A sudoku puzzle...


 ...and its solution numbers marked in red.

 Note:

 The given board contain only digits 1-9 and the character '.'.
 You may assume that the given Sudoku puzzle will have a single unique solution.
 The given board size is always 9x9.


 */

public class SudokuSolver {

    public static void main(String[] args) {
        SudokuSolver ss = new SudokuSolver();

        int [][] board = {
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

        ss.sudokuSolver(board);
        ss.printMatrix(board);

    }

    public boolean sudokuSolver(int [][] M) {

        int row = M.length;
        int col = M[0].length;

        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                if (M[i][j] != 0) {
                    continue;
                }
                for (int k=1; k<=9; k++) {
                    M[i][j] = k;
                    if (isValidSudoku(M) && sudokuSolver(M)) {
                        return true;
                    }
                    M[i][j] = 0;
                }
                return false;
            }
        }

        return true;
    }

    private boolean isValidSudoku(int [][] M) {
        int row = M.length;
        int col = M[0].length;

        //check each Rows
        for (int i=0; i<row; i++) {
            boolean [] values = new boolean[10];
            for (int j=0; j<col; j++) {
                if (M[i][j] == 0) {
                    continue;
                }
                if (values[M[i][j]]) {
                    return false;
                }
                values[M[i][j]] = true;
            }
        }

        //check each Column.
        for (int i=0; i<col; i++) {
            boolean [] values = new boolean[10];
            for (int j=0; j<row; j++) {
                if (M[j][i] == 0) {
                    continue;
                }
                if (values[M[j][i]]) {
                    return false;
                }
                values[M[j][i]] = true;
            }
        }

        //check each 3x3 cell
        for (int i=0; i<9; i+=3) {
            for (int j=0; j<9; j+=3) {
                boolean [] values = new boolean[10];
                for (int k=0; k<3; k++) {
                    for (int l=0; l<3; l++) {

                        if ( M[i+k][j+l] == 0) {
                            continue;
                        }
                        if (values[ M[i+k][j+l]]) {
                            return false;
                        }
                        values[ M[i+k][j+l]] = true;
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
