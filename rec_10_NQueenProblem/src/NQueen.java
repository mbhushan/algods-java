import java.util.ArrayList;
import java.util.List;

/**
 The N Queen is the problem of placing N chess queens on an N×N chessboard so that no two queens
 attack each other. For example, following is a solution for 4 Queen problem.



 The expected output is a binary matrix which has 1s for the blocks where queens are placed.
 For example, following is the output matrix for above 4 queen solution.

 { 0,  1,  0,  0}
 { 0,  0,  0,  1}
 { 1,  0,  0,  0}
 { 0,  0,  1,  0}

 ==================
 INPUT / OUTPUT
 ==================
 solving n-queen for N: 4
 Queen placements: [[0, 1], [1, 3], [2, 0], [3, 2]]

 All Possible Queen placements:
 [[0, 1], [1, 3], [2, 0], [3, 2]]
 [[0, 2], [1, 0], [2, 3], [3, 1]]

 */

public class NQueen {

    public static void main(String[] args) {
        NQueen nq = new NQueen();

        nq.solveNQueen(4);

    }

    public void solveNQueen(int n) {
        List<Cell> result = new ArrayList<>();
        System.out.println("solving n-queen for N: " + n);
        System.out.print("Queen placements: ");
        solveNQueen(n, 0, result);
        System.out.println();

        result.clear();
        System.out.println("All Possible Queen placements: ");
        solveNQueenAllSolution(n, 0, result);

    }

    private void solveNQueenAllSolution(int N, int row, List<Cell> result) {

        if (row >= N) {
            System.out.println(result);
            return;
        }

        for (int col = 0; col < N; col++) {
            //check if current cell conflicts with prev queen placements.
            boolean flag = true;
            for (Cell cell : result) {
                if (cell.row == row || cell.col == col || ((cell.row + cell.col) == (row + col))
                        || ((cell.col - cell.row) == (col - row))) {
                    flag = false;
                    break;
                }
            }
            if (!flag) {
                continue;
            }
            Cell cell = new Cell(row, col);
            result.add(cell);
            solveNQueenAllSolution(N, row + 1, result);
            result.remove(result.size() - 1);
        }
    }

    private boolean solveNQueen(int N, int row, List<Cell> result) {

        if (row >= N) {
            System.out.println(result);
            return true;
        }

        for (int col=0; col<N; col++) {
            boolean foundSafe = true;
            //check if current cell conflicts with prev queen placements.
            for (Cell cell: result) {
                if (cell.row == row || cell.col == col || ((cell.row + cell.col) == (row+col))
                        || ((cell.col - cell.row) == (col-row))) {
                   foundSafe = false;
                   break;
                }
            }
            if (foundSafe) {
                Cell cell = new Cell(row, col);
                result.add(cell);
                boolean flag = solveNQueen(N, row + 1, result);
                if (flag) {
                    return true;
                }
                result.remove(result.size()-1);
            }

        }
        return false;
    }


}

class Cell {
    int row;
    int col;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return "[" + this.row + ", " + this.col + "]";
    }
}
