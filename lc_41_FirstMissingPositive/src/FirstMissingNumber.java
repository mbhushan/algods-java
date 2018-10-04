/**
 41. First Missing Positive

 Given an unsorted integer array, find the smallest missing positive integer.

 Example 1:

 Input: [1,2,0]
 Output: 3
 Example 2:

 Input: [3,4,-1,1]
 Output: 2
 Example 3:

 Input: [7,8,9,11,12]
 Output: 1

 */
public class FirstMissingNumber {

    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 1;
        }
        int len = nums.length;
        int [] A = new int[len+1];

        for (int i=0; i<len; i++) {
            if (nums[i] < 0) {
                continue;
            }
            if (nums[i] <= len && A[nums[i]] == 0) {
                A[nums[i]] = 1;
            }
        }
        for (int i=1; i<=len; i++) {
            if (A[i] == 0) {
                return i;
            }
        }

        return len+1;
    }
}
