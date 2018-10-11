/**
 348. Design Tic-Tac-Toe
 https://leetcode.com/problems/design-tic-tac-toe/discuss/81898/Java-O(1)-solution-easy-to-understand
 https://leetcode.com/problems/design-tic-tac-toe/description/

 */
public class TicTacToe {

    private int[] rows;
    private int[] cols;
    private int diagonal;
    private int antiDiagonal;

    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        rows = new int[n];
        cols = new int[n];
    }

    /** Player {player} makes a move at ({row}, {col}).
     @param row The row of the board.
     @param col The column of the board.
     @param player The player, can be either 1 or 2.
     @return The current winning condition, can be either:
     0: No one wins.
     1: Player 1 wins.
     2: Player 2 wins. */
    public int move(int row, int col, int player) {
        int toAdd = player == 1 ? 1 : -1;

        rows[row] += toAdd;
        cols[col] += toAdd;
        if (row == col)
        {
            diagonal += toAdd;
        }

        if (col == (cols.length - row - 1))
        {
            antiDiagonal += toAdd;
        }

        int size = rows.length;
        if (Math.abs(rows[row]) == size ||
                Math.abs(cols[col]) == size ||
                Math.abs(diagonal) == size  ||
                Math.abs(antiDiagonal) == size)
        {
            return player;
        }

        return 0;
    }

}

class TicTacToeGame {

    private int [][]M;
    private int n;
    private int p1;
    private int p2;

    /** Initialize your data structure here. */
    public TicTacToeGame(int n) {
        M = new int[n][n];
        p1 = 233*n;
        p2 = 269*n;
    }

    /** Player {player} makes a move at ({row}, {col}).
     @param row The row of the board.
     @param col The column of the board.
     @param player The player, can be either 1 or 2.
     @return The current winning condition, can be either:
     0: No one wins.
     1: Player 1 wins.
     2: Player 2 wins. */
    public int move(int row, int col, int player) {
        if (player == 1) {
            M[row][col] = 233;
        } else {
            M[row][col] = 269;
        }

        //check if any player wins.
        return checkWin(M);
    }

    private int checkWin(int [][] M) {
        int row = M.length;
        int col = M[0].length;
        //check rows
        for (int i=0; i<row; i++) {
            int sum = 0;
            for (int j=0; j<col; j++) {
                sum += M[i][j];
            }
            if (sum == p1 || sum == p2) {
                return sum == p1? 1:2;
            }
        }

        //check cols
        for (int i=0; i<col; i++) {
            int sum = 0;
            for (int j=0; j<row; j++) {
                sum += M[j][i];
            }
            if (sum == p1 || sum == p2) {
                return sum == p1? 1:2;
            }
        }

        //check diagonals
        int sum = 0;
        for (int i=0; i<row; i++) {
            sum += M[i][i];
        }
        if (sum == p1 || sum == p2) {
            return sum == p1? 1:2;
        }

        sum = 0;
        for (int i=0,j=row-1; i<row && j>=0; i++, j--) {
            sum += M[i][j];
        }
        if (sum == p1 || sum == p2) {
            return sum == p1? 1:2;
        }

        return 0;
    }
}

