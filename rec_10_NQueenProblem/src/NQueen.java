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

 */

public class NQueen {

    public static void main(String[] args) {
        NQueen nq = new NQueen();

        nq.solveNQueen(4);

    }

    public void solveNQueen(int n) {
        List<Cell> result = new ArrayList<>();
        solveNQueen(n, 0, result);
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
