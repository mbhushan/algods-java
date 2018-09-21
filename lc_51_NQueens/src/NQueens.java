import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 51. N-Queens
 52. N-Queens II
 https://leetcode.com/problems/n-queens/description/
 https://leetcode.com/problems/n-queens-ii/description/

 The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.



 Given an integer n, return all distinct solutions to the n-queens puzzle.

 Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.'
 both indicate a queen and an empty space respectively.

 Example:

 Input: 4
 Output: [
 [".Q..",  // Solution 1
 "...Q",
 "Q...",
 "..Q."],

 ["..Q.",  // Solution 2
 "Q...",
 "...Q",
 ".Q.."]
 ]
 Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.

 ==================
 input / output
 ==================
 solutions for board size: 4
 [[.Q.., ...Q, Q..., ..Q.], [..Q., Q..., ...Q, .Q..]]

 solutions for board size: 4
 [[.Q.., ...Q, Q..., ..Q.], [..Q., Q..., ...Q, .Q..]]
 total n-queens solutions: 2

 */

public class NQueens {

    public static void main(String[] args) {

        NQueens nq = new NQueens();

        int n = 4;
        System.out.println("solutions for board size: " + n);
        System.out.println(nq.solveNQueens(n));
        System.out.println("total n-queens solutions: " + nq.totalNQueens(n));
    }

    public int totalNQueens(int n) {

        List<Cell> result = new ArrayList<>();
        int [] ans = new int[1];
        ans[0] = 0;
        solveNQueenHelper(n, 0, result, ans);

        return ans[0];

    }

    public void solveNQueenHelper(int n, int row, List<Cell> result, int [] ans) {
        if (row >= n) {
            ++ans[0];
            return;
        }

        for (int col=0; col<n; col++) {
            int r = row;
            int c = col;
            //check to see if (r,c) is ok with previous placements/
            boolean flag = true;
            for (Cell cell: result) {
                if (cell.r == r || cell.c == c || (r+c) == (cell.r + cell.c) || (c-r) == (cell.c - cell.r) ) {
                    flag = false;
                    break;
                }
            }
            if (!flag) {
                continue;
            }
            Cell cell = new Cell(r, c);
            result.add(cell);
            solveNQueenHelper(n, row+1, result, ans);
            result.remove(result.size()-1);
        }
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<Cell>> sols = new ArrayList<>();
        List<Cell> result = new ArrayList<>();
        solveNQueenHelper(n, 0, result, sols);

        List<List<String>> ans = new ArrayList();


        for (List<Cell> list: sols) {
            char [][] A = new char[n][n];
            List<String> slist = new ArrayList<>();
            for (char[] row: A){
                Arrays.fill(row, '.');
            }
            for (Cell cell: list) {
                A[cell.r][cell.c] = 'Q';
            }
            for (char[] row: A){
                slist.add(String.valueOf(row));
            }

            ans.add(slist);

        }

        return ans;


    }

    public void solveNQueenHelper(int n, int row, List<Cell> result, List<List<Cell>> sols) {
        if (row >= n) {
            List<Cell> tmp = new ArrayList<Cell>();
            tmp.addAll(result);
            sols.add(tmp);
            //result.clear();
            return;
        }

        for (int col=0; col<n; col++) {
            int r = row;
            int c = col;
            //check to see if (r,c) is ok with previous placements/
            boolean flag = true;
            for (Cell cell: result) {
                if (cell.r == r || cell.c == c || (r+c) == (cell.r + cell.c) || (c-r) == (cell.c - cell.r) ) {
                    flag = false;
                    break;
                }
            }
            if (!flag) {
                continue;
            }
            Cell cell = new Cell(r, c);
            result.add(cell);
            solveNQueenHelper(n, row+1, result, sols);
            result.remove(result.size()-1);
        }
    }
}

class Cell {
    int r;
    int c;

    Cell(int r, int c) {
        this.r = r;
        this.c = c;
    }

    public String toString() {
        return "[" + r + ", " + c + "]";
    }
}
