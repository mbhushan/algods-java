import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**

 15. 3Sum
 https://leetcode.com/problems/3sum/description/

 Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?
 Find all unique triplets in the array which gives the sum of zero.

 Note:

 The solution set must not contain duplicate triplets.

 Example:

 Given array nums = [-1, 0, 1, 2, -1, -4],

 A solution set is:
 [
 [-1, 0, 1],
 [-1, -1, 2]
 ]

 ===============
 INPUT / OUTPUT
 ===============
 sorted array: [-4, -1, -1, 0, 1, 2]
 triplets with sum ZERO: [[-1, -1, 2], [-1, 0, 1]]

 sorted array: [-2, 0, 0, 2, 2]
 triplets with sum ZERO: [[-2, 0, 2]]

 sorted array: [-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6]
 triplets with sum ZERO: [[-4, -2, 6], [-4, 0, 4], [-4, 1, 3], [-4, 2, 2], [-2, -2, 4], [-2, 0, 2]]
 */

public class ThreeSum {

    public static void main(String[] args) {
        ThreeSum ts = new ThreeSum();

        int [][] A = {
                {-1, 0, 1, 2, -1, -4},
                {-2,0,0,2,2},
                {-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6}
        };

        for (int i=0; i<A.length; i++) {
            ts.threeSum(A[i]);
            System.out.println();
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        if (nums == null || nums.length < 3) {
            return result;
        }

        Arrays.sort(nums);
        System.out.println("sorted array: " + Arrays.toString(nums));

        for (int i=0; i<nums.length-2; i++) {

            if (i > 0 && (nums[i] == nums[i-1])) {
                continue;
            }

            int target = -1 *  nums[i];

            int j = i+1;
            int k = nums.length - 1;

            while (j < k) {
                if (nums[j] + nums[k] < target) {
                    ++j;
                } else if (nums[j] + nums[k] > target) {
                    --k;
                } else { //triplet found.
                    Integer [] A = new Integer[] {nums[i], nums[j], nums[k]};
                    List<Integer> list = Arrays.asList(A);
                    result.add(list);

                    while ((j < k) && nums[j] == nums[j+1]) {
                        ++j;
                    }

                    while ((j < k) && nums[k] == nums[k-1]) {
                        --k;
                    }
                    ++j;
                    --k;
                }

            }
        }

        System.out.println("triplets with sum ZERO: " + result);


        return result;

    }
}
