/**
 LeetCode – Game of Life (Java)

 Given a board with m by n cells, each cell has an initial state live (1) or dead (0).
 Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules:

 Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 Any live cell with two or three live neighbors lives on to the next generation.
 Any live cell with more than three live neighbors dies, as if by over-population..
 Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.


 Write a function to compute the next state (after one update) of the board given its current state.
 ===============
 INPUT / OUTPUT
 ===============
 input matrix:
 0 1 0
 0 0 1
 1 1 1
 0 0 0

 next state 1 of game of life:
 0 0 0
 1 0 1
 0 1 1
 0 1 0

 next state 2 of game of life:
 0 0 0
 0 0 1
 1 0 1
 0 1 1

 next state 3 of game of life:
 0 0 0
 0 1 0
 0 0 1
 0 1 1

 input matrix:
 0 0 0 0 0 0 0 0 0 0
 0 0 0 1 1 0 0 0 0 0
 0 0 0 0 1 0 0 0 0 0
 0 0 0 0 0 0 0 0 0 0
 0 0 0 0 0 0 0 0 0 0
 0 0 0 1 1 0 0 0 0 0
 0 0 1 1 0 0 0 0 0 0
 0 0 0 0 0 1 0 0 0 0
 0 0 0 0 1 0 0 0 0 0
 0 0 0 0 0 0 0 0 0 0

 next state 1 of game of life:
 0 0 0 0 0 0 0 0 0 0
 0 0 0 1 1 0 0 0 0 0
 0 0 0 1 1 0 0 0 0 0
 0 0 0 0 0 0 0 0 0 0
 0 0 0 0 0 0 0 0 0 0
 0 0 1 1 1 0 0 0 0 0
 0 0 1 1 0 0 0 0 0 0
 0 0 0 1 1 0 0 0 0 0
 0 0 0 0 0 0 0 0 0 0
 0 0 0 0 0 0 0 0 0 0

 next state 2 of game of life:
 0 0 0 0 0 0 0 0 0 0
 0 0 0 1 1 0 0 0 0 0
 0 0 0 1 1 0 0 0 0 0
 0 0 0 0 0 0 0 0 0 0
 0 0 0 1 0 0 0 0 0 0
 0 0 1 0 1 0 0 0 0 0
 0 0 0 0 0 0 0 0 0 0
 0 0 1 1 1 0 0 0 0 0
 0 0 0 0 0 0 0 0 0 0
 0 0 0 0 0 0 0 0 0 0
 */
public class GameOfLife {

    public static void main(String[] args) {
        GameOfLife gl = new GameOfLife();

        int [][] M = {
                {0,1,0},
                {0,0,1},
                {1,1,1},
                {0,0,0}
        };

        int[][] grid = {
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 1, 1, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 1, 1, 0, 0, 0, 0, 0 },
                { 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
        };

        System.out.println("input matrix: ");
        gl.printMatrix(M);
        System.out.println();

        int i=1;
        int numState = 3;
        while (i <= numState) {
            System.out.println("next state " + i + " of game of life: ");
            gl.nextState(M);
            gl.printMatrix(M);
            System.out.println();
            ++i;
        }

        System.out.println("input matrix: ");
        gl.printMatrix(grid);
        System.out.println();

        i=1;
        numState = 2;
        while (i <= numState) {
            System.out.println("next state " + i + " of game of life: ");
            gl.nextState(grid);
            gl.printMatrix(grid);
            System.out.println();
            ++i;
        }


    }


    public void nextState(int [][] M) {
        if (M == null) {
            return;
        }

        int row = M.length;
        int col = M[0].length;



        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                udpateCell(M, i, j);
            }
        }

        for (int r=0; r<row; r++) {
            for (int k=0; k<col; k++) {
                if (M[r][k] == 2) {
                    M[r][k] = 0;
                } else if (M[r][k] == 3) {
                    M[r][k] = 1;
                }
            }
        }
    }

    public void udpateCell(int [][]M, int r, int c) {

        int adjCount = 0;
        for (int i=-1; i<=1; i++) {
            for (int j=-1; j<=1; j++) {
                if (i == 0 && j ==0) {
                    continue;
                }
                int r1 = r + i;
                int c1 = c + j;

                if (r1 < 0 || r1 >= M.length || c1 < 0 || c1 >= M[0].length) {
                    continue;
                }

                if (M[r1][c1] == 1) {
                    ++adjCount;
                } else if (M[r1][c1] == 2) {
                    ++adjCount;
                }
            }
        }

        if (adjCount < 2 && M[r][c] == 1) {
            M[r][c] = 2;
        } else if (adjCount > 3 && M[r][c] == 1) {
            M[r][c] = 2;
        } else if (adjCount == 3 && M[r][c] == 0) {
            M[r][c] = 3;
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
