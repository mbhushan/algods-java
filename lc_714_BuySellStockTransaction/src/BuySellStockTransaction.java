import java.util.Arrays;

/**
 714. Best Time to Buy and Sell Stock with Transaction Fee
 https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/description/

 Your are given an array of integers prices, for which the i-th element is the price of a given stock on day i; and a non-negative integer fee representing a transaction fee.

 You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction. You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)

 Return the maximum profit you can make.

 Example 1:
 Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
 Output: 8
 Explanation: The maximum profit can be achieved by:
 Buying at prices[0] = 1
 Selling at prices[3] = 8
 Buying at prices[4] = 4
 Selling at prices[5] = 9
 The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.

 =================
 INPUT / OUTPUT
 =================
 input: [1, 3, 2, 8, 4, 9]
 max profit: 8

 input: [1, 4, 5, 7, 6, 3, 2, 9]
 max profit: 9

 input: [3, 3, 5, 0, 0, 3, 1, 4]
 max profit: 2

 input: [1, 2, 3, 4, 5]
 max profit: 2

 input: [7, 1, 5, 3, 6, 4]
 max profit: 3

 input: [7, 6, 4, 3, 1]
 max profit: 0

 input: [1, 2, 3, 4, 5]
 max profit: 2

 */
public class BuySellStockTransaction {

    public static void main(String[] args) {
        BuySellStockTransaction bs = new BuySellStockTransaction();

        int [][] A = {
                {1, 3, 2, 8, 4, 9},
                {1, 4, 5, 7, 6, 3, 2, 9},
                {3,3,5,0,0,3,1,4},
                {1,2,3,4,5},
                {7,1,5,3,6,4},
                {7,6,4,3,1},
                {1,2,3,4,5}
        };

        for (int i=0; i<A.length; i++) {
            System.out.println("input: " + Arrays.toString(A[i]));
            System.out.println("max profit: " + bs.maxProfit(A[i], 2));
            System.out.println();
        }
    }

    public int maxProfit(int[] prices, int fee) {
        /**
         Intuition and Algorithm

         At the end of the i-th day, we maintain cash, the maximum profit we could have if we
         did not have a share of stock, and hold, the maximum profit we could have if we owned a share of stock.

         To transition from the i-th day to the i+1-th day, we either sell our stock
         cash = max(cash, hold + prices[i] - fee) or buy a stock hold = max(hold, cash - prices[i]).
         At the end, we want to return cash. We can transform cash first without using temporary variables
         because selling and buying on the same day can't be better than just continuing to hold the stock.
         */

        int cash = 0, hold = -prices[0];
        for (int i=1; i<prices.length; i++) {
            cash = Math.max(cash, hold + prices[i] - fee); //sell
            hold = Math.max(hold, cash - prices[i]); //buy
        }

        return cash;
    }
}
