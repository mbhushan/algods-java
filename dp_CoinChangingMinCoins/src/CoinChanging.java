import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 Given a total and coins of certain denomination with infinite supply,
 what is the minimum number of coins it takes to form this total V.

 ====================
 INPUT / OUTPUT
 ====================
 0 X X X X X X X X X X X X X
 0 X 1 X 2 X 3 X 4 X 5 X 6 X
 0 X 1 1 2 2 2 3 3 3 4 4 4 5
 0 X 1 1 2 2 1 3 2 2 3 3 2 4
 0 X 1 1 2 2 1 1 2 2 2 3 2 2
 min coins required: 2
 result indices: [3, 2]
 coints are:
 6 7

 */

public class CoinChanging {

    public static void main(String [] args) {
        CoinChanging cc = new CoinChanging();
        int [] A = {2, 3, 6, 7};
        int V = 13;

        cc.changeCoins(A, V);
    }

    public void changeCoins(int [] A, int total) {
        if (A == null) {
            return;
        }
        int row = A.length+1;
        int col = total+1;
        int [][] T = new int[row][col];
        for (int c=1; c < col; c++) {
            T[0][c] = Integer.MAX_VALUE-1;
        }

        for (int i=1; i<row; i++) {
            for (int j=1; j<col; j++) {
                if (A[i-1] <= j) {
                    T[i][j] = Math.min(T[i-1][j], 1 + T[i][j-A[i-1]]);
                } else {
                    T[i][j] = T[i-1][j];
                }
            }
        }

        printDPMatrix(T);
        System.out.println("min coins required: " + T[row-1][col-1]);
        printCoins(T, A);
    }

    public void printCoins(int [][] M, int [] coins) {
        List<Integer> result = new ArrayList<>();
        int row = M.length-1;
        int col = M[0].length-1;

        while (row > 0 && col > 0) {
            if (M[row][col] == M[row-1][col]) {
                --row;
            } else {
                result.add(row-1);
                col = col - coins[row-1];
            }
        }
        System.out.println("result indices: " + result);
        System.out.println("coints are: ");
        ListIterator<Integer> iter = result.listIterator(result.size());
        while (iter.hasPrevious()) {
            int index = iter.previous();
            System.out.print(coins[index] + " ");
        }
        System.out.println();
    }

    private void printDPMatrix(int [][] M) {
        int row = M.length;
        int col = M[0].length;
        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                if (M[i][j] == (Integer.MAX_VALUE-1)) {
                    System.out.print("X" + " ");
                } else {
                    System.out.print(M[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
