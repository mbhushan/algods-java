import java.util.Arrays;

/**
 689. Maximum Sum of 3 Non-Overlapping Subarrays
 https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/description/

 In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.

 Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.

 Return the result as a list of indices representing the starting position of each interval (0-indexed).
 If there are multiple answers, return the lexicographically smallest one.

 Example:
 Input: [1,2,1,2,6,7,5,1], 2
 Output: [0, 3, 5]
 Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
 We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
 Note:
 nums.length will be between 1 and 20000.
 nums[i] will be between 1 and 65535.
 k will be between 1 and floor(nums.length / 3).

 ===================
 INPUT / OUTPUT
 ===================
 input: [1, 2, 1, 2, 6, 7, 5, 1]
 Sum Arr: [0, 1, 3, 4, 6, 12, 19, 24, 25]
 left: [0, 0, 0, 0, 3, 4, 4, 4]
 right: [4, 4, 4, 4, 4, 5, 6, 0]
 res iter: 2 ->[0, 2, 4]==== max: 19
 res iter: 3 ->[0, 3, 5]==== max: 23
 res iter: 4 ->[0, 3, 5]==== max: 23
 Ans: [0, 3, 5]
 */
public class MaxSumThreeSubarrays {

    public static void main(String[] args) {
        MaxSumThreeSubarrays mst = new MaxSumThreeSubarrays();

        int [] A = {1,2,1,2,6,7,5,1};
        int k = 2;


        System.out.println("input: " + Arrays.toString(A));
        int [] R = mst.maxSumOfThreeSubarrays(A, k);
        System.out.println("Ans: " + Arrays.toString(R));


    }

    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {

        if(nums==null || nums.length==0) {
            return null;
        }
        int len = nums.length;

        // this is very important because otherwise i+k is going to out of bounds.
        int[] sum = new int[len+1];
        int[] left = new int[len];
        int[] right = new int[len];
        int[] res = new int[3];
        int max = 0;

        // Computing the running sum
        // sum[i] stores the value from index 0 to i-1
        for (int i=0; i<len; i++) {
            sum[i+1] = sum[i] + nums[i];
        }

        System.out.println("Sum Arr: " + Arrays.toString(sum) );

        // traversing from left to right
        int leftMax = sum[k]-sum[0];
        left[k-1] = 0;
        for (int i=k; i<len; i++) {
            if (sum[i+1] - sum[i+1-k] > leftMax) {
                left[i] = i-k+1;
                leftMax = sum[i+1] - sum[i+1-k];
            } else {
                left[i] = left[i-1];
            }
        }

        System.out.println("left: " + Arrays.toString(left));

        // traversing from right to left
        int rightMax = sum[len] - sum[len-k];
        right[len-k] = len-k;
        for (int i=len-k-1; i>=0; i--) {
            if (sum[i+k] - sum[i] > rightMax) {
                right[i] = i;
                rightMax = sum[i+k] - sum[i];
            } else {
                right[i] = right[i+1];
            }
        }

        System.out.println("right: " + Arrays.toString(right));

        // now consider the middle part where k<=i<=n-2k
        for (int i=k; i<=len-2*k; i++) {
            int l = left[i-1];
            int r = right[i+k];
            int total = (sum[l+k] - sum[l]) + (sum[i+k] - sum[i]) + (sum[r+k] - sum[r]);
            if (total > max) {
                max = total;
                res[0] = l;
                res[1] = i;
                res[2] = r;
            }
            System.out.println("res iter: " + i + " ->" + Arrays.toString(res) + "==== max: " + max);
        }
        return res;

    }
}
