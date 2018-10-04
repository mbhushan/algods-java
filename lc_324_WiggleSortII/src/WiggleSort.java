import java.util.Arrays;

/**
 324. Wiggle Sort II
 https://leetcode.com/problems/wiggle-sort-ii/description/

 Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

 Example 1:

 Input: nums = [1, 5, 1, 1, 6, 4]
 Output: One possible answer is [1, 4, 1, 5, 1, 6].
 Example 2:

 Input: nums = [1, 3, 2, 2, 3, 1]
 Output: One possible answer is [2, 3, 1, 3, 1, 2].
 Note:
 You may assume all input has valid answer.

 Follow Up:
 Can you do it in O(n) time and/or in-place with O(1) extra space?


 */
public class WiggleSort {

    public static void main(String[] args) {

    }

    /**
     (1) elements smaller than the 'median' are put into the last even slots

     (2) elements larger than the 'median' are put into the first odd slots

     (3) the medians are put into the remaining slots.

     Index :       0   1   2   3   4   5
     Small half:   M       S       S
     Large half:       L       L       M
     */

    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        int [] copy = Arrays.copyOf(nums, len);
        int j = (len-1)/2;
        int k = len-1;
        for (int i=0; i<len; i++) {
            nums[i] = (i%2 == 0) ? copy[j--] : copy[k--];
        }
    }

//    public void wiggleSort(int[] nums) {
//        int median = findKthLargest(nums, (nums.length + 1) / 2);
//        int n = nums.length;
//
//        int left = 0, i = 0, right = n - 1;
//
//        while (i <= right) {
//
//            if (nums[newIndex(i,n)] > median) {
//                swap(nums, newIndex(left++,n), newIndex(i++,n));
//            }
//            else if (nums[newIndex(i,n)] < median) {
//                swap(nums, newIndex(right--,n), newIndex(i,n));
//            }
//            else {
//                i++;
//            }
//        }
//
//
//    }
//
//    private int newIndex(int index, int n) {
//        return (1 + 2*index) % (n | 1);
//    }
}
