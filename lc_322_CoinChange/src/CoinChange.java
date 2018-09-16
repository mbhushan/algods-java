import java.util.ArrayList;
import java.util.List;

/**
 322. Coin Change

 https://leetcode.com/problems/coin-change/description/


 You are given coins of different denominations and a total amount of money amount.
 Write a function to compute the fewest number of coins that you need to make up that amount.
 If that amount of money cannot be made up by any combination of the coins, return -1.

 Example 1:

 Input: coins = [1, 2, 5], amount = 11
 Output: 3
 Explanation: 11 = 5 + 5 + 1
 Example 2:

 Input: coins = [2], amount = 3
 Output: -1

 */


class CoinChange {

    public int coinChange(int[] coins, int amount) {

        if (coins == null || coins.length < 1) {
            return -1;
        }

        return findCoinChangeDP(coins, amount, new int[amount+1]);

    }

    public int coinChangeRec(int[] coins, int amount) {

        if (coins == null || coins.length < 1) {
            return -1;
        }

        List<Integer> buff = new ArrayList<>();
        int [] result = new int[1];
        result[0] = Integer.MAX_VALUE;
        int index = 0;

        findCoinChange(coins, index, amount, buff, result);

        if (result[0] == Integer.MAX_VALUE) {
            result[0] = -1;
        }

        return result[0];

    }

    private void findCoinChange(int [] coins, int index, int amount, List<Integer> buff, int [] result) {

        if (amount == 0) {
            if (buff.size() < result[0]) {
                result[0] = buff.size();
            }
            return ;
        }

        if (amount < 0 || index == coins.length) {
            return;
        }

        buff.add(coins[index]);
        findCoinChange(coins, index, amount - coins[index], buff, result);
        buff.remove(buff.size()-1);
        findCoinChange(coins, index+1, amount, buff, result);
    }

    private int findCoinChangeDP(int [] coins, int amount, int [] dp) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }

        if (dp[amount] != 0) {
            return dp[amount];
        }

        int min = Integer.MAX_VALUE;

        for (int c: coins) {
            int result = findCoinChangeDP(coins, amount - c, dp);
            if (result >= 0 && result < min) {
                min = result + 1;
            }
        }
        dp[amount] = (min == Integer.MAX_VALUE) ? -1: min;

        return dp[amount];
    }
}
