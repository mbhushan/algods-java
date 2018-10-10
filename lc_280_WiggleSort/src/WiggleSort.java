/**
 280. Wiggle Sort
 https://leetcode.com/problems/wiggle-sort/description/

 Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

 Example:

 Input: nums = [3,5,2,1,6,4]
 Output: One possible answer is [3,5,1,6,2,4]
 */
public class WiggleSort {

    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length < 1) {
            return;
        }
        for (int i=0; i<nums.length; i++) {
            if (i%2 == 1) {
                if (nums[i-1] > nums[i]) {
                    int tmp = nums[i-1];
                    nums[i-1] = nums[i];
                    nums[i] = tmp;
                }
            } else if (i > 0 && nums[i-1] < nums[i]) {

                int tmp = nums[i-1];
                nums[i-1] = nums[i];
                nums[i] = tmp;

            }
        }
    }
}
