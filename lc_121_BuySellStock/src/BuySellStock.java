import java.util.Arrays;

/**

 121. Best Time to Buy and Sell Stock
 https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/

 Say you have an array for which the ith element is the price of a given stock on day i.

 If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock),
 design an algorithm to find the maximum profit.

 Note that you cannot sell a stock before you buy one.

 Example 1:

 Input: [7,1,5,3,6,4]
 Output: 5
 Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 Not 7-1 = 6, as selling price needs to be larger than buying price.
 Example 2:

 Input: [7,6,4,3,1]
 Output: 0
 Explanation: In this case, no transaction is done, i.e. max profit = 0.

 =================
 input / output
 =================
 input: [7, 1, 5, 3, 6, 4]
 max profit: 5

 input: [7, 6, 4, 3, 1]
 max profit: 0
 */

public class BuySellStock {

    public static void main(String [] args) {
        BuySellStock bs = new BuySellStock();

        int [][] A = {
                {7,1,5,3,6,4},
                {7,6,4,3,1}
        };

        for (int i=0; i<A.length; i++) {
            System.out.println("input: " + Arrays.toString(A[i]));
            System.out.println("max profit: " + bs.maxProfit(A[i]));
        }

    }

    public int maxProfit(int[] prices) {

        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int min = prices[0];
        int maxProfit = 0;

        for (int i=1; i<prices.length; i++) {
            int diff = prices[i] - min;
            if (diff > maxProfit) {
                maxProfit = diff;
            }
            if (prices[i] < min) {
                min = prices[i];
            }

        }

        return maxProfit;
    }
}
