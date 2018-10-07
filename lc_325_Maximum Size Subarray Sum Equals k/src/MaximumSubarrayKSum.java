import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 325. Maximum Size Subarray Sum Equals k
 Given an array nums and a target value k, find the maximum length of a subarray that sums to k.
 If there isn't one, return 0 instead.

 Note:
 The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.

 Example 1:

 Input: nums = [1, -1, 5, -2, 3], k = 3
 Output: 4
 Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.
 Example 2:

 Input: nums = [-2, -1, 2, 1], k = 1
 Output: 2
 Explanation: The subarray [-1, 2] sums to 1 and is the longest.
 Follow Up:
 Can you do it in O(n) time?
 */
public class MaximumSubarrayKSum {

    /**
     The subarray sum reminds me the range sum problem. Preprocess the input array such that you get
     the range sum in constant time.
     sum[i] means the sum from 0 to i inclusively
     the sum from i to j is sum[j] - sum[i - 1] except that from 0 to j is sum[j].

     j-i is equal to the length of subarray of original array. we want to find the max(j - i)
     for any sum[j] we need to find if there is a previous sum[i] such that sum[j] - sum[i] = k
     Instead of scanning from 0 to j -1 to find such i, we use hashmap to do the job in constant time.
     However, there might be duplicate value of of sum[i] we should avoid overriding its index as we want the max j - i, so we want to keep i as left as possible.
     */
    public int maxSubArrayLen(int[] nums, int k) {
        //[1, -1, 5, -2, 0, 1], k = 3
        //1 -> 0
        //0 -> 1
        //5 -> 2
        //3 -> 3
        //3 -> 5
        //
        Deque<Integer> deque = new ArrayDeque<>();
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int max = 0;
        for (int i=0; i<nums.length; i++) {
            sum = sum + nums[i];
            if (sum == k) {
                max = i+1;
            } else if (map.containsKey(sum-k)) {
                max = Math.max(max, i-map.get(sum-k));
            }

            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }


        }

        return max;
    }
}
