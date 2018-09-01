import java.util.Arrays;

/**

 188. Best Time to Buy and Sell Stock IV
 https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/description/


 Say you have an array for which the ith element is the price of a given stock on day i.

 Design an algorithm to find the maximum profit. You may complete at most k transactions.

 Note:
 You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

 Example 1:

 Input: [2,4,1], k = 2
 Output: 2
 Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
 Example 2:

 Input: [3,2,6,5,0,3], k = 2
 Output: 7
 Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
 Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.

 ==============
 INPUT / OUTPUT
 ==============
 input: [2, 4, 1]
 DP Matrix:
 0 0 0
 0 2 2
 0 2 2
 max profit: 2

 input: [3, 2, 6, 5, 0, 3]
 DP Matrix:
 0 0 0 0 0 0
 0 0 4 4 4 4
 0 0 4 4 4 7
 max profit: 7

 input: [1, 4, 5, 7, 6, 3, 2, 9]
 DP Matrix:
 0 0 0 0 0 0 0 0
 0 3 4 6 6 6 6 8
 0 3 4 6 6 6 6 13
 max profit: 13

 input: [3, 3, 5, 0, 0, 3, 1, 4]
 DP Matrix:
 0 0 0 0 0 0 0 0
 0 0 2 2 2 3 3 4
 0 0 2 2 2 5 5 6
 max profit: 6

 input: [1, 2, 3, 4, 5]
 DP Matrix:
 0 0 0 0 0
 0 1 2 3 4
 0 1 2 3 4
 max profit: 4

 input: [7, 1, 5, 3, 6, 4]
 DP Matrix:
 0 0 0 0 0 0
 0 0 4 4 5 5
 0 0 4 4 7 7
 max profit: 7

 input: [7, 6, 4, 3, 1]
 DP Matrix:
 0 0 0 0 0
 0 0 0 0 0
 0 0 0 0 0
 max profit: 0

 input: [1, 2, 3, 4, 5]
 DP Matrix:
 0 0 0 0 0
 0 1 2 3 4
 0 1 2 3 4
 max profit: 4
 */

public class BuySellStock4 {

    public static void main(String[] args) {
        BuySellStock4 bs = new BuySellStock4();

        int [][] A = {
                {2,4,1},
                {3,2,6,5,0,3},
                {1, 4, 5, 7, 6, 3, 2, 9},
                {3,3,5,0,0,3,1,4},
                {1,2,3,4,5},
                {7,1,5,3,6,4},
                {7,6,4,3,1},
                {1,2,3,4,5}
        };

        int k = 2;

        for (int i=0; i<A.length; i++) {
            System.out.println("input: " + Arrays.toString(A[i]));
            System.out.println("max profit: " + bs.maxProfit(k, A[i]));
            System.out.println();
        }
    }

    public int maxProfit(int k, int[] prices) {

        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int [][] T = new int[k+1][prices.length];

        for (int i=0; i<T.length; i++) {
            for (int j=0; j<T[0].length; j++) {
                if (i == 0 || j == 0) {
                    T[i][j] = 0;
                } else {
                    T[i][j] = T[i][j-1]; //no transaction on jth day.
                    for (int m=0; m <= j-1; m++) { //transacting on mth day.
                        T[i][j] = Math.max(T[i][j], T[i-1][m] + prices[j] - prices[m]);
                    }
                }
            }
        }

        System.out.println("DP Matrix: ");
        printMatrix(T);
        System.out.println();


        return T[k][prices.length-1];
    }

    public void printMatrix(int [][] M) {
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
