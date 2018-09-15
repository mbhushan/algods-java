import java.util.ArrayList;
import java.util.List;

/**
 39. Combination Sum
 https://leetcode.com/problems/combination-sum/description/

 Given a set of candidate numbers (candidates) (without duplicates) and a target number (target),
 find all unique combinations in candidates where the candidate numbers sums to target.

 The same repeated number may be chosen from candidates unlimited number of times.

 Note:

 All numbers (including target) will be positive integers.
 The solution set must not contain duplicate combinations.
 Example 1:

 Input: candidates = [2,3,6,7], target = 7,
 A solution set is:
 [
 [7],
 [2,2,3]
 ]
 Example 2:

 Input: candidates = [2,3,5], target = 8,
 A solution set is:
 [
 [2,2,2,2],
 [2,3,3],
 [3,5]
 ]

 */
public class CombinationSum {

    class Solution {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {

            List<List<Integer>> result = new ArrayList<>();

            if (candidates == null || candidates.length < 1) {
                return result;
            }

            List<Integer> buff = new ArrayList<>();
            int index = 0;

            findCombinationSum(candidates, index, target, buff, result);

            return result;
        }

        private void findCombinationSum(int [] A, int index, int target, List<Integer> buff, List<List<Integer>> result) {
            if (target == 0) {
                List<Integer> temp = new ArrayList<Integer>();
                temp.addAll(buff);
                result.add(temp);
                return;
            }

            if (target < 0) {
                return;
            }


            if (index >= A.length) {
                return;
            }

            buff.add(A[index]);
            findCombinationSum(A, index, target - A[index], buff, result);
            buff.remove(buff.size()-1);
            findCombinationSum(A, index+1, target, buff, result);


        }
    }
}
