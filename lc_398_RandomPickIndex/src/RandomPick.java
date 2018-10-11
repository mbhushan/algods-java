import java.util.Random;

/**
 398. Random Pick Index
 https://leetcode.com/problems/random-pick-index/description/

 Given an array of integers with possible duplicates, randomly output the index of a
 given target number. You can assume that the given target number must exist in the array.

 Note:
 The array size can be very large. Solution that uses too much extra space will not pass the judge.

 Example:

 int[] nums = new int[] {1,2,3,3,3};
 Solution solution = new Solution(nums);

 // pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
 solution.pick(3);

 // pick(1) should return 0. Since in the array only nums[0] is equal to 1.
 solution.pick(1);

 */
public class RandomPick {

    public static void main(String[] args) {
        int [] A = {1,2,3,3,3};
        Solution sol  = new Solution(A);

        System.out.println("picking: " + sol.pick(3));
    }
}

class Solution {

    private int [] A;
    Random rand;
    public Solution(int[] nums) {
        A = nums;
        rand = new Random();
    }

    public int pick(int target) {
        int res = -1;
        int total = 0;
        for (int i=0; i<A.length; i++) {
            if (A[i] == target) {
                int r = rand.nextInt(++total);
                res = (r == 0) ? i : res;
            }
        }

        return res;
    }
}
