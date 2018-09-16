/**
 289. Game of Life
 https://leetcode.com/problems/game-of-life/description/

 According to the Wikipedia's article: "The Game of Life, also known simply as Life,
 is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

 Given a board with m by n cells, each cell has an initial state live (1) or dead (0).
 Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following
 four rules (taken from the above Wikipedia article):

 Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 Any live cell with two or three live neighbors lives on to the next generation.
 Any live cell with more than three live neighbors dies, as if by over-population..
 Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 Write a function to compute the next state (after one update) of the board given its current state.
 The next state is created by applying the above rules simultaneously to every cell in the current state,
 where births and deaths occur simultaneously.

 Example:

 Input:
 [
 [0,1,0],
 [0,0,1],
 [1,1,1],
 [0,0,0]
 ]
 Output:
 [
 [0,0,0],
 [1,0,1],
 [0,1,1],
 [0,1,0]
 ]
 Follow up:

 Could you solve it in-place? Remember that the board needs to be updated at the same time: You
 cannot update some cells first and then use their updated values to update other cells.
 In this question, we represent the board using a 2D array. In principle, the board is infinite,
 which would cause problems when the active area encroaches the border of the array.
 How would you address these problems?

 */
public class GameOfLife {

    public static void main(String[] args) {
        int [][] board = {
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
        };

        int n = 2;

        int x = n & 1;
        System.out.println("x: " + x);

        GameOfLife gl = new GameOfLife();

        gl.gameOfLife(board);

        System.out.println("solution: ");
        gl.printBoard(board);
    }

    public void printBoard(int [][] M) {

        int row = M.length;
        int col = M[0].length;

        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                System.out.print(M[i][j] + " ");
            }
            System.out.println();
        }
    }
    public void gameOfLife(int[][] board) {

        int row = board.length;
        int col = board[0].length;

        System.out.println("neighbors: ");

        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                int neighbors = getAliveNeighbors(board, i, j);
                System.out.print(neighbors + " ");

                if (board[i][j] == 1) {
                    if (neighbors == 2 || neighbors == 3) {
                        board[i][j] = 3;
                    } else if (neighbors < 2 || neighbors > 3) {
                        board[i][j] = 1;
                    }
                } else if (board[i][j] == 0 && neighbors == 3) {
                    board[i][j] = 2;
                }

            }
            System.out.println();
        }
        System.out.println();

        System.out.println("intermediate: ");
        printBoard(board);
        System.out.println();

        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {

                board[i][j] = (board[i][j]) >> 1;
            }
        }


    }

    private int getAliveNeighbors(int [][] board, int r, int c) {

        int [][] directions = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        int neighbors = 0;
        for (int i=0; i<directions.length; i++) {
            int row = r + directions[i][0];
            int col = c + directions[i][1];

            if (row < 0 || col < 0 || row >= board.length || col >= board[0].length) {
                continue;
            }

            if ((board[row][col] & 1) == 1) {
                ++neighbors;
            }
        }

        return neighbors;
    }
}
