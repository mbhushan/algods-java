import java.util.Arrays;

/**
 53. Maximum Subarray
 https://leetcode.com/problems/maximum-subarray/description/

 Given an integer array nums, find the contiguous subarray (containing at least one number)
 which has the largest sum and return its sum.

 Example:

 Input: [-2,1,-3,4,-1,2,1,-5,4],
 Output: 6
 Explanation: [4,-1,2,1] has the largest sum = 6.
 Follow up:

 If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach,
 which is more subtle.

 ==============
 INPUT / OUTPUT
 ==============

 input: [-2, 1, -3, 4, -1, 2, 1, -5, 4]
 max sum: 6

 input: [4, -1, 2, 1]
 max sum: 6
 */
public class MaximumSubarray {

    public static void main(String[] args) {
        MaximumSubarray ms = new MaximumSubarray();

        int [][] A = {
                {-2, 1, -3, 4, -1, 2, 1, -5, 4},
                {4, -1, 2, 1}
        };

        for (int i=0; i<A.length; i++) {
            System.out.println("input: " + Arrays.toString(A[i]));
            int ans = ms.maxSubArray(A[i]);
            System.out.println("max sum: " + ans);
            System.out.println();
        }

    }

    public int maxSubArray(int[] nums) {

        if (nums == null || nums.length < 1) {
            return 0;
        }

        int sum = 0;
        int maxSum = Integer.MIN_VALUE;

        for (int i=0; i<nums.length; i++) {
            sum += nums[i];
            sum = Math.max(sum, nums[i]);
            if (sum > maxSum) {
                maxSum = sum;
            }
        }

        return maxSum;
    }
}
