import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
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

 =================
 INPUT / OUTPUT
 =================
 candidates: [2, 3, 6, 7]
 combinations summing to: 7
 [2, 2, 3]
 [7]


 candidates: [2, 3, 5]
 combinations summing to: 8
 [2, 2, 2, 2]
 [2, 3, 3]
 [3, 5]
 */

public class CombinationSum {

    public static void main(String[] args) {
        CombinationSum cs = new CombinationSum();

        int [][] candidates = {
                {2,3,6,7},
                {2,3,5}
        };

        int [] targets = {
                7, 8
        };

        for (int i=0; i<candidates.length; i++) {
            cs.combinationSum(candidates[i], targets[i]);
            System.out.println();
        }
    }

    public void combinationSum(int [] A, int target) {
        System.out.println("candidates: " + Arrays.toString(A));
        System.out.println("combinations summing to: " + target);
        combinationSum(A, target, 0, new ArrayList<>());
        System.out.println();
    }

    public void combinationSum(int [] A, int target, int index, List<Integer> buff) {
        if (target == 0) {
            System.out.println(buff);
            return;
        }

        if (target < 0 || index >= A.length) {
            return;
        }

        buff.add(A[index]);
        combinationSum(A, target - A[index], index, buff);
        buff.remove(buff.size()-1);
        combinationSum(A, target, index+1, buff);
    }
}
