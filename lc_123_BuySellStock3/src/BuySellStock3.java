import java.util.Arrays;

/**

 123. Best Time to Buy and Sell Stock III

 https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/description/

 Say you have an array for which the ith element is the price of a given stock on day i.

 Design an algorithm to find the maximum profit. You may complete at most two transactions.

 Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

 Example 1:

 Input: [3,3,5,0,0,3,1,4]
 Output: 6
 Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
 Example 2:

 Input: [1,2,3,4,5]
 Output: 4
 Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
 engaging multiple transactions at the same time. You must sell before buying again.
 Example 3:

 Input: [7,6,4,3,1]
 Output: 0
 Explanation: In this case, no transaction is done, i.e. max profit = 0.


 =================
 INPUT / OUTPUT
 =================
 */


public class BuySellStock3 {


    public static void main(String [] args) {
        BuySellStock3 bs = new BuySellStock3();

        int [][] A = {
                {1, 4, 5, 7, 6, 3, 2, 9},
                {3,3,5,0,0,3,1,4},
                {1,2,3,4,5},
                {7,1,5,3,6,4},
                {7,6,4,3,1},
                {1,2,3,4,5}
        };

        for (int i=0; i<A.length; i++) {
            System.out.println("input: " + Arrays.toString(A[i]));
            System.out.println("max profit: " + bs.maxProfit(A[i]));
            System.out.println();
        }


    }


    public int maxProfit(int[] prices) {

        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int maxProfit = 0;

        int [] left = new int[prices.length];
        int [] right = new int[prices.length];
        left[0] = 0;
        right[0] = 0;

        int min = prices[0];
        for (int i=1; i<left.length; i++) {
            left[i] = Math.max(left[i-1], prices[i] - min);
            min = Math.min(min, prices[i]);
        }

        int max = prices[prices.length-1];
        for (int i=prices.length-2; i>=0; --i) {
            right[i] = Math.max(right[i+1], max - prices[i]);
            max = Math.max(max, prices[i]);
        }

        System.out.println("Left: " + Arrays.toString(left));
        System.out.println("Right: " + Arrays.toString(right));

        for (int i=0; i<prices.length; i++) {
            maxProfit = Math.max(maxProfit, left[i] + right[i]);
        }

        return maxProfit;
    }

    private int maxProfitDP(int [] prices, int k) {
        int [][] T = new int[k+1][prices.length];

        return 0;
    }

}
