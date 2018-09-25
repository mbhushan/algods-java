import java.util.Arrays;

/**
 300. Longest Increasing Subsequence
 https://leetcode.com/problems/longest-increasing-subsequence/description/
 Given an unsorted array of integers, find the length of longest increasing subsequence.

 Example:

 Input: [10,9,2,5,3,7,101,18]
 Output: 4
 Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 Note:

 There may be more than one LIS combination, it is only necessary for you to return the length.
 Your algorithm should run in O(n2) complexity.
 Follow up: Could you improve it to O(n log n) time complexity?

 */
public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();

        int [] A = {10,9,2,5,3,7,101,18};

        System.out.println("input: " + Arrays.toString(A));
        System.out.println("ans: " + lis.lengthOfLIS_BS(A));
    }

    //https://leetcode.com/problems/longest-increasing-subsequence/discuss/74825/Short-Java-solution-using-DP-O(n-log-n)
    public int lengthOfLIS_BS(int[] nums) {
        int[] tails = new int[nums.length];
        int size = 0;
        System.out.println("0: " + Arrays.toString(tails));
        for (int x : nums) {
            int i = 0, j = size;
            while (i != j) {
                int m = (i + j) / 2;
                if (tails[m] < x)
                    i = m + 1;
                else
                    j = m;
            }
            tails[i] = x;
            if (i == size) ++size;
            System.out.println(x + ": " + Arrays.toString(tails));
        }
        return size;
    }

    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }

        int [] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        int max = 1;
        for (int i=1; i<nums.length; i++) {
            int j = 0;
            while (j < i) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                    max = Math.max(max, dp[i]);
                }
                ++j;
            }
        }

        return max;
    }


}
