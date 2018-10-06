import java.util.ArrayList;
import java.util.List;

/**
 46. Permutations
 https://leetcode.com/problems/permutations/description/
 Given a collection of distinct integers, return all possible permutations.

 Example:

 Input: [1,2,3]
 Output:
 [
 [1,2,3],
 [1,3,2],
 [2,1,3],
 [2,3,1],
 [3,1,2],
 [3,2,1]
 ]

 */
public class Permutations {

    public static void main(String[] args) {
        int [] A = {1,2,3};
        System.out.println(new Permutations().permute(A));
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        permute(nums, 0, result);

        return result;

    }

    private void permute(int [] A, int index, List<List<Integer>> result) {
        if (index >= A.length) {
            List<Integer> list = new ArrayList<>();
            for (int i: A) {
                list.add(i);
            }
            result.add(list);
            return;
        }

        for (int i=index; i<A.length; i++) {
            int tmp = A[i];

            A[i] = A[index];
            A[index] = tmp;
            permute(A, index+1, result);

            tmp = A[i];
            A[i] = A[index];
            A[index] = tmp;
        }
    }
}
