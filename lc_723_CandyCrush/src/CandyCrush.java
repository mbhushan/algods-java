/**
 723. Candy Crush
 https://leetcode.com/problems/candy-crush/description/

 This question is about implementing a basic elimination algorithm for Candy Crush.

 Given a 2D integer array board representing the grid of candy, different positive integers board[i][j]
 represent different types of candies. A value of board[i][j] = 0 represents that the cell at position (i, j) is empty.
 The given board represents the state of the game following the player's move. Now, you need to restore the board to a
 stable state by crushing candies according to the following rules:

 If three or more candies of the same type are adjacent vertically or horizontally, "crush" them all at the
 same time - these positions become empty.
 After crushing all candies simultaneously, if an empty space on the board has candies on top of itself, then these
 candies will drop until they hit a candy or bottom at the same time. (No new candies will drop outside
 the top boundary.)
 After the above steps, there may exist more candies that can be crushed. If so, you need to repeat the above steps.
 If there does not exist more candies that can be crushed (ie. the board is stable), then return the current board.
 You need to perform the above rules until the board becomes stable, then return the current board.

 Example 1:
 Input:
 board =
 [[110,5,112,113,114],[210,211,5,213,214],[310,311,3,313,314],[410,411,412,5,414],[5,1,512,3,3],[610,4,1,613,614],
 [710,1,2,713,714],[810,1,2,1,1],[1,1,2,2,2],[4,1,4,4,1014]]
 Output:
 [[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[110,0,0,0,114],[210,0,0,0,214],[310,0,0,113,314],[410,0,0,213,414],
 [610,211,112,313,614],[710,311,412,613,714],[810,411,512,713,1014]]
 Explanation:

 Note:
 The length of board will be in the range [3, 50].
 The length of board[i] will be in the range [3, 50].
 Each board[i][j] will initially start as an integer in the range [1, 2000].
 */
public class CandyCrush {

    public static void main(String[] args) {
        CandyCrush cc = new CandyCrush();

    }

    public int[][] candyCrush(int[][] board) {
        int N = board.length, M = board[0].length;
        boolean found = true;
        while (found) {
            found = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    int val = Math.abs(board[i][j]);
                    if (val == 0) continue;
                    if (j < M - 2 && Math.abs(board[i][j + 1]) == val && Math.abs(board[i][j + 2]) == val) {
                        found = true;
                        int ind = j;
                        while (ind < M && Math.abs(board[i][ind]) == val) board[i][ind++] = -val;
                    }
                    if (i < N - 2 && Math.abs(board[i + 1][j]) == val && Math.abs(board[i + 2][j]) == val) {
                        found = true;
                        int ind = i;
                        while (ind < N && Math.abs(board[ind][j]) == val) board[ind++][j] = -val;
                    }
                }
            }
            if (found) { // move positive values to the bottom, then set the rest to 0
                for (int j = 0; j < M; j++) {
                    int storeInd = N - 1;
                    for (int i = N - 1; i >= 0; i--) {
                        if (board[i][j] > 0) {
                            board[storeInd--][j] = board[i][j];
                        }
                    }
                    for (int k = storeInd; k >= 0; k--) board[k][j] = 0;
                }
            }
        }
        return board;
    }
}
