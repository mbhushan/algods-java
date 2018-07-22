/**
 LeetCode – Paint House II (Java)

 There are a row of n houses, each house can be painted with one of the k colors.
 The cost of painting each house with a certain color is different. You have to paint all
 the houses such that no two adjacent houses have the same color.

 The cost of painting each house with a certain color is represented by a n x k cost matrix.
 For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost
 of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.

 =================
 INPUT / OUTPUT
 ================
 input for K cost matrix:
 7 5 10
 3 6 1
 8 7 4
 6 2 9
 1 4 7
 2 3 6

 DP matrix:
 7 5 10
 8 13 6
 14 13 12
 18 14 22
 15 22 21
 23 18 21
 min cost of coloring: 18

 */

public class PaintHouse2 {

    public static void main(String [] args) {

        PaintHouse2 ph = new PaintHouse2();

        int [][] M = {
                {7, 5, 10},
                {3, 6, 1},
                {8, 7, 4},
                {6, 2, 9},
                {1, 4, 7},
                {2, 3, 6},
        };

        System.out.println("input for K cost matrix: ");
        ph.printMatrix(M);

        System.out.println();
        ph.paintHouse(M);

    }

    public void paintHouse(int [][] M) {

        if (M == null) {
            return;
        }

        int row = M.length;
        int col = M[0].length;

        int minCost = 0;

        int [][] T = new int[row][col];

        for (int i=0; i<col; i++) {
            T[0][i] = M[0][i];
        }

        for (int i=1; i<row; i++) {
            for (int j=0; j<col; j++) {
                T[i][j] = M[i][j] + minPrev(T, i, j, col);
            }
//            T[i][0] = Math.min(T[i-1][1], T[i-1][2]) + M[i][0];
//            T[i][1] = Math.min(T[i-1][0], T[i-1][2]) + M[i][1];
//            T[i][2] = Math.min(T[i-1][0], T[i-1][1]) + M[i][2];
        }

        //minCost = Math.min(T[row-1][0], Math.min(T[row-1][1], T[row-1][2]));

        minCost = minPrev(T, row, -1, col);

        System.out.println("DP matrix: ");
        printMatrix(T);

        System.out.println("min cost of coloring: " + minCost);
    }

    public int minPrev(int [][] T, int row, int index, int col) {
        int minVal = Integer.MAX_VALUE;

        for (int i=0; i<col; i++) {
            if (i == index) {
                continue;
            }
            minVal = Math.min(minVal, T[row-1][i]);
        }

        return minVal;
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
