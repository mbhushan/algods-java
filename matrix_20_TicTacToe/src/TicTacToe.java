/**
 A Tic-Tac-Toe board is given as a string array board. Return True if and only if it is possible
 to reach this board position during the course of a valid tic-tac-toe game.

 The board is a 3 x 3 array, and consists of characters " ", "X", and "O".
 The " " character represents an empty square.

 Here are the rules of Tic-Tac-Toe:

 Players take turns placing characters into empty squares (" ").
 The first player always places "X" characters, while the second player always places "O" characters.
 "X" and "O" characters are always placed into empty squares, never filled ones.
 The game ends when there are 3 of the same (non-empty) character filling any row, column, or diagonal.
 The game also ends if all squares are non-empty.
 No more moves can be played if the game is over.

 Example 1:
 Input: board = ["O  ", "   ", "   "]
 Output: false
 Explanation: The first player always plays "X".

 Example 2:
 Input: board = ["XOX", " X ", "   "]
 Output: false
 Explanation: Players take turns making moves.

 Example 3:
 Input: board = ["XXX", "   ", "OOO"]
 Output: false

 Example 4:
 Input: board = ["XOX", "O O", "XOX"]
 Output: true
 Note:

 board is a length-3 array of strings, where each string board[i] has length 3.
 Each board[i][j] is a character in the set {" ", "X", "O"}.

 ======================
 INPUT / OUTPUT
 ======================
 input board configuration:
 X X O
 O O X
 X O X

 valid: true
 input board configuration:
 O



 valid: false
 input board configuration:
 X O X
 X


 valid: false
 input board configuration:
 X X X

 O O O

 valid: false
 input board configuration:
 X O X
 O   O
 X O X

 valid: true

 */

public class TicTacToe {

    public static void main(String[] args) {
        TicTacToe tt = new TicTacToe();

        char [][] board = {
                {'X', 'X', 'O'},
                {'O', 'O', 'X'},
                {'X', 'O', 'X'}
        };

        char [][][] boards = {
                {{'X', 'X', 'O'}, {'O', 'O', 'X'}, {'X', 'O', 'X'}},
                {{'O', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}},
                {{'X', 'O', 'X'}, {' ', 'X', ' '}, {' ', ' ', ' '}},
                {{'X', 'X', 'X'}, {' ', ' ', ' '}, {'O', 'O', 'O'}},
                {{'X', 'O', 'X'}, {'O', ' ', 'O'}, {'X', 'O', 'X'}},

        };

        for (int i=0; i<boards.length; i++) {

            System.out.println("input board configuration: ");
            tt.printMatrix(boards[i]);
            System.out.println();

            System.out.println("valid: " + tt.checkValidity(boards[i]));
        }


    }

    public boolean checkValidity(char [][] M) {
        if (M == null) {
            return false;
        }

        int xCount = 0;
        int oCount = 0;

        for (int i=0; i<M.length; i++) {
            for (int j=0; j<M.length; j++) {
                if (M[i][j] == 'X') {
                    ++xCount;
                } else if (M[i][j] == 'O') {
                    ++oCount;
                }
            }
        }

        //check validity conditions


        //1. if xCount == oCount or xCount = oCount+1
        if (xCount == oCount || xCount == oCount+1) {
            //check if x wins.
            if (checkWin(M, 'X', xCount)) {
                if (xCount != oCount+1) {
                    return false;
                }
            }

            //check if O wins
            if (checkWin(M, 'O', oCount)) {
                if (xCount != oCount || checkWin(M, 'X', xCount)) {
                    return false;
                }
            }
            return true;
        }



        return false;
    }

    public boolean checkWin(char [][] M, char player, int count) {
        if (count < 3) {
            return false;
        }

        //check all rows and cols
        int r = 0;

        while (r < M.length) {
            count = 0;
            for (int i=0; i<M.length; i++) {
                if (M[r][i] == player) {
                    ++count;
                }
            }
            if (count == 3) {
                return true;
            }
            count = 0;
            for (int i=0; i<M.length; i++) {
                if (M[i][r] == player) {
                    ++count;
                }
            }
            if (count == 3) {
                return true;
            }
            ++r;
        }

        //check back diagonal
        for (int i=0; i<M.length; i++) {
            count = 0;
            for (int j=0; j<M.length; j++) {
                if (i == j && M[i][j] == player) {
                    ++count;
                }
            }
            if (count == 3) {
                return true;
            }
        }

        //check front diagonal
        for (int i=0; i<M.length; i++) {
            count = 0;
            for (int j=0; j<M.length; j++) {
                if ((i+j == M.length-1) && M[i][j] == player) {
                    ++count;
                }
            }
            if (count == 3) {
                return true;
            }
        }


        return false;

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
