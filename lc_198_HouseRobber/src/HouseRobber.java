import java.util.Arrays;

/**
 198. House Robber
 https://leetcode.com/problems/house-robber/description/

 You are a professional robber planning to rob houses along a street. Each house has a certain amount
 of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses
 have security system connected and it will automatically contact the police if two adjacent houses
 were broken into on the same night.

 Given a list of non-negative integers representing the amount of money of each house, determine the maximum
 amount of money you can rob tonight without alerting the police.

 Example 1:

 Input: [1,2,3,1]
 Output: 4
 Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 Total amount you can rob = 1 + 3 = 4.
 Example 2:

 Input: [2,7,9,3,1]
 Output: 12
 Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 Total amount you can rob = 2 + 9 + 1 = 12.

 */
public class HouseRobber {

    public static void main(String[] args) {

        HouseRobber hr = new HouseRobber();

        int [] A = {114,117,207,117,235,82,90,67,143,146,53,108,200,91,80,223,58,170,110,236,81,90,222,160,
                165,195,187,199,114,235,197,187,69,129,64,214,228,78,188,67,205,94,205,169,241,202,144,240};
        //max rob value: 4173

        System.out.println("input: " + Arrays.toString(A));
      //  System.out.println("max rob value: " + hr.rob(A));
        System.out.println("max rob value iterative: " + hr.robIterative(A));
        //max rob value iterative: 4173

    }

    public int robIterative(int [] nums) {

        int first = 0;
        int second = 0;
        int i = 0;
        while (i < nums.length) {
            int tmp = first;
            first = Math.max(second+nums[i], first);
            second = Math.max(tmp, second);
            ++i;
        }

        return Math.max(first, second);
    }

    public int rob(int[] nums) {
        return helper(nums, 0);
    }

    private int helper(int[] nums, int index) {

        if (index >= nums.length) {
            return 0;
        }

        int sum = 0;

        sum += Math.max(helper(nums, index+2) + nums[index], helper(nums, index+1));

        return sum;

    }
}
