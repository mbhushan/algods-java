import java.util.Arrays;

/**
 309. Best Time to Buy and Sell Stock with Cooldown
 https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/description/

 Say you have an array for which the ith element is the price of a given stock on day i.

 Design an algorithm to find the maximum profit. You may complete as many transactions as you like
 (ie, buy one and sell one share of the stock multiple times) with the following restrictions:

 You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
 Example:

 Input: [1,2,3,0,2]
 Output: 3
 Explanation: transactions = [buy, sell, cooldown, buy, sell]
 */

public class BuySellStockCooldown {

    public static void main(String [] args) {
        BuySellStockCooldown bs = new BuySellStockCooldown();

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

        int profit1 = 0; //max profit on day i if I sell
        int profit2 = 0; //max profit on day i if I do nothing

        for (int i=1; i<prices.length; i++) {
            int copy = profit1;
            profit1 = Math.max(profit1 + (prices[i] - prices[i-1]), profit2);
            profit2 = Math.max(copy, profit2);
        }

        return Math.max(profit1, profit2);
    }
}
