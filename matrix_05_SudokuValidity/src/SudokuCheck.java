import java.util.BitSet;

/**
 LeetCode – Valid Sudoku (Java)

 Determine if a Sudoku is valid. The Sudoku board could be partially filled,
 where empty cells are filled with the character '.'.

 ===============
 INPUT / OUTPUT
 ===============
 input sudoku board:
 5 3 . . 7 . . . .
 6 . . 1 9 5 . . .
 . 9 8 . . . . 6 .
 8 . . . 6 . . . 3
 4 . . 8 . 3 . . 1
 7 . . . 2 . . . 6
 . 6 . . . . 2 8 .
 . . . 4 1 9 . . 5
 . . . . 8 . . 7 9

 is sudoku valid: true

 */


public class SudokuCheck {

    public static void main(String [] args) {

        char [][] M = {
                { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
                { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
                { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
                { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
                { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
                {'.', '.', '.', '.', '8', '.', '.', '7', '9' } };

        SudokuCheck sc = new SudokuCheck();
//        char ch = '5';
//        System.out.println((int)ch - '0');

        System.out.println("input sudoku board: ");
        sc.printMatrix(M);

        System.out.println();
        boolean ans = sc.checkSudoku(M);
        System.out.println("is sudoku valid: " + ans);

    }

    public boolean checkSudoku(char [][] M) {

        if (M == null) {
            return false;
        }

        int row = M.length;
        int col = M[0].length;

        //check each row;
        for (int i=0; i<row; i++) {
            BitSet bs = new BitSet(10);
            for (int j=0; j<col; j++) {
                if (M[i][j] != '.') {
                    if (bs.get(((int)M[i][j] - '0'))) {
                        return false;
                    } else {
                        bs.set(((int)M[i][j] - '0'));
                    }
                }
            }
        }

        //check each col;
        for (int j=0; j<col; j++) {
            BitSet bs = new BitSet(10);
            for (int i=0; i<row; i++) {
                if (M[i][j] != '.') {
                    if (bs.get(((int)M[i][j] - '0'))) {
                        return false;
                    } else {
                        bs.set(((int)M[i][j] - '0'));
                    }
                }
            }
        }

        //check each 3x3 cell;
        for (int i=0; i<row; i=i+3) {
            for (int j=0; j<col; j=j+3) {
                BitSet bs = new BitSet(10);
                for (int k=0; k<3; k++) {
                    for (int l=0; l<3; l++) {
                        if (M[i+k][j+l] != '.') {
                            if (bs.get(((int)M[i+k][j+l] - '0'))) {
                                return false;
                            } else {
                                bs.set(((int)M[i+k][j+l] - '0'));
                            }
                        }

                    }
                }
            }
        }

        return true;
    }

    public void printMatrix(char [][] M) {
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
