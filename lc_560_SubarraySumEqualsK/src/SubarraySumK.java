import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 560. Subarray Sum Equals K
 https://leetcode.com/problems/subarray-sum-equals-k/description/

 Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

 Example 1:
 Input:nums = [1,1,1], k = 2
 Output: 2
 Note:
 The length of the array is in range [1, 20,000].
 The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].

 */
public class SubarraySumK {

    public static void main(String[] args) {
        SubarraySumK ss = new SubarraySumK();

        int [][] A = {
                {1,1,1},
                {3, 4, 7, 2, -3, 1, 4, 2}
        };

        int [] k = {
                2, 7
        };

        for (int i=0; i<A.length; i++) {

            System.out.println("input: " + Arrays.toString(A[i]));
            System.out.println("K: " + k[i]);
            System.out.println("slow: " + ss.subarraySum(A[i], k[i]));
            System.out.println("fast: " + ss.subarraySumFast(A[i], k[i]));
            System.out.println();
            System.out.println();
        }

    }


    public int subarraySum(int[] nums, int k) {

        int [] sums = new int[nums.length+1];
        sums[0] = 0;


        for (int i=1; i<=nums.length; i++) {
            sums[i] = sums[i-1] + nums[i-1];
        }

        int count = 0;

       // System.out.println("sums: " + Arrays.toString(sums));

        for (int i=0; i<nums.length; i++) {
            for (int j=i+1; j<=nums.length; j++) {

                if ((sums[j] - sums[i]) == k) {
                    ++count;
                }

            }
        }

       // System.out.println("count: " + count);

        return count;

    }

    public int subarraySumFast(int[] nums, int k) {

        Map<Integer, Integer> hmap = new HashMap<>();

        int sum = 0;
        int count = 0;
        hmap.put(0, 1);

        for (int i=0; i<nums.length; i++) {
            sum += nums[i];

            hmap.put(sum, hmap.getOrDefault(sum, 0) + 1);

            if (hmap.containsKey(sum - k)) {
                count += hmap.get(sum-k);
            }
        }
       // System.out.println("hmap: " + hmap);

        return count;
    }
}
