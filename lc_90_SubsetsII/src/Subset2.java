import java.util.ArrayList;
import java.util.List;

/**
 90. Subsets II
 https://leetcode.com/problems/subsets-ii/description/

 Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

 Note: The solution set must not contain duplicate subsets.

 Example:

 Input: [1,2,2]
 Output:
 [
 [2],
 [1],
 [1,2,2],
 [2,2],
 [1,2],
 []
 ]

 */
public class Subset2 {

    public static void main(String[] args) {

    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> buff = new ArrayList<>();

        helper(nums, 0, buff, result);

        return result;

    }

    private void helper(int [] nums, int index, List<Integer> buff, List<List<Integer>> result) {
        result.add(new ArrayList<>(buff));

        for (int i=index; i<nums.length; i++) {
            if (i > index && nums[i] == nums[i-1]) { //skip duplicates.
                continue;
            }
            buff.add(nums[i]);
            helper(nums, i+1, buff, result);
            buff.remove(buff.size()-1);
        }
    }
}
