/**
 LeetCode – Surrounded Regions (Java)

 Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.
 A region is captured by flipping all 'O's into 'X's in that surrounded region.

 For example,


 X X X X
 X O O X
 X X O X
 X O X X
 After running your function, the board should be:

 X X X X
 X X X X
 X X X X
 X O X X

 ==============
 Rephrased problem:
 Given a matrix of ‘O’ and ‘X’, replace ‘O’ with ‘X’ if surrounded by ‘X’
 Given a matrix where every element is either ‘O’ or ‘X’, replace ‘O’ with ‘X’ if surrounded by ‘X’. A ‘O’ (or a set of ‘O’) is considered to be by surrounded by ‘X’ if there are ‘X’ at locations just below, just above, just left and just right of it.

 Examples:

 Input: mat[M][N] =  {
 {'X', 'O', 'X', 'X', 'X', 'X'},
 {'X', 'O', 'X', 'X', 'O', 'X'},
 {'X', 'X', 'X', 'O', 'O', 'X'},
 {'O', 'X', 'X', 'X', 'X', 'X'},
 {'X', 'X', 'X', 'O', 'X', 'O'},
 {'O', 'O', 'X', 'O', 'O', 'O'},
 };
 Output: mat[M][N] =  {
 {'X', 'O', 'X', 'X', 'X', 'X'},
 {'X', 'O', 'X', 'X', 'X', 'X'},
 {'X', 'X', 'X', 'X', 'X', 'X'},
 {'O', 'X', 'X', 'X', 'X', 'X'},
 {'X', 'X', 'X', 'O', 'X', 'O'},
 {'O', 'O', 'X', 'O', 'O', 'O'},
 };

 Input: mat[M][N] =  {{'X', 'X', 'X', 'X'}
 {'X', 'O', 'X', 'X'}
 {'X', 'O', 'O', 'X'}
 {'X', 'O', 'X', 'X'}
 {'X', 'X', 'O', 'O'}
 };
 Input: mat[M][N] =  {{'X', 'X', 'X', 'X'}
 {'X', 'X', 'X', 'X'}
 {'X', 'X', 'X', 'X'}
 {'X', 'X', 'X', 'X'}
 {'X', 'X', 'O', 'O'}
 };

 =================
 INPUT / OUTPUT
 =================
 input matrix:
 X O X X X X
 X O X X O X
 X X X O O X
 O X X X X X
 X X X O X O
 O O X O O O

 intermediate matrix:
 X # X X X X
 X # X X O X
 X X X O O X
 # X X X X X
 X X X # X #
 # # X # # #

 final matrix:
 X O X X X X
 X O X X X X
 X X X X X X
 O X X X X X
 X X X O X O
 O O X O O O

 input matrix:
 X X X X
 X O X X
 X O O X
 X O X X
 X X O O

 intermediate matrix:
 X X X X
 X O X X
 X O O X
 X O X X
 X X # #

 final matrix:
 X X X X
 X X X X
 X X X X
 X X X X
 X X O O

 */

public class SurroundedRegions {

    public static void main(String [] args) {
        SurroundedRegions sr = new SurroundedRegions();

        char [][] M1 =  {
                {'X', 'O', 'X', 'X', 'X', 'X'},
                {'X', 'O', 'X', 'X', 'O', 'X'},
                {'X', 'X', 'X', 'O', 'O', 'X'},
                {'O', 'X', 'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'O', 'X', 'O'},
                {'O', 'O', 'X', 'O', 'O', 'O'},
        };

        char [][] M2 = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'O', 'X', 'X'},
                {'X', 'X', 'O', 'O'},
        };

        System.out.println("input matrix: ");
        sr.printMatrix(M1);
        System.out.println();

        sr.updateRegion(M1);

        System.out.println();

        System.out.println("final matrix: ");
        sr.udpateMatrix(M1);
        sr.printMatrix(M1);

        System.out.println();
        System.out.println("input matrix: ");
        sr.printMatrix(M2);
        System.out.println();

        sr.updateRegion(M2);

        System.out.println();

        System.out.println("final matrix: ");
        sr.udpateMatrix(M2);
        sr.printMatrix(M2);

    }

    public void updateRegion(char [][] M) {

        int row = M.length;
        int col = M[0].length;

        for (int r=0; r<row; r++) {
            for (int c=0; c<col; c++) {
                if (r == 0 || c == 0 || r == row-1 || c == col-1) {
                    if (M[r][c] == 'O') {
                        M[r][c] = '#';
                        updateRegion(M, row, col, r, c);
                    }
                }
            }
        }
        System.out.println("intermediate matrix: ");
        printMatrix(M);
    }

    private void updateRegion(char [][] M, int row, int col, int r, int c) {
        if (r < 0 || r >= row || c < 0 || c >= col) {
            return;
        }

        if (M[r][c] == 'X') {
            return;
        }
//        if (M[r][c] == 'O') {
//            M[r][c] = '#';
//        }

        int [][] directions = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

        for (int i=0; i<directions.length; i++) {
            int r1 = r + directions[i][0];
            int c1 = c + directions[i][1];

            if ((r1 >= 0 && r1 < row) && (c1 >= 0 && c1 < col) && M[r1][c1] == 'O') {
                M[r1][c1] = '#';
                updateRegion(M, row, col, r1, c1);
            }
        }
    }

    private void udpateMatrix(char [][] M) {
        if (M == null) {
            return;
        }
        int row = M.length;
        int col = M[0].length;

        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                if (M[i][j] == 'O') {
                    M[i][j] = 'X';
                }
            }
        }

        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                if (M[i][j] == '#') {
                    M[i][j] = 'O';
                }
            }
        }
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
