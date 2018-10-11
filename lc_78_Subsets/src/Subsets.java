import java.util.ArrayList;
import java.util.List;

/**
 78. Subsets
 https://leetcode.com/problems/subsets/description/
 Given a set of distinct integers, nums, return all possible subsets (the power set).

 Note: The solution set must not contain duplicate subsets.

 Example:

 Input: nums = [1,2,3]
 Output:
 [
 [3],
 [1],
 [2],
 [1,2,3],
 [1,3],
 [2,3],
 [1,2],
 []
 ]

 */
public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        helper(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private void helper(int [] nums, int index, List<Integer> buff, List<List<Integer>> result) {
        result.add(new ArrayList<>(buff));
        for (int i=index; i<nums.length; i++) {
            buff.add(nums[i]);
            helper(nums, i+1, buff, result);
            buff.remove(buff.size()-1);
        }
    }
}
