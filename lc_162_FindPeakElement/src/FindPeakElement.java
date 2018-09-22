/**
 162. Find Peak Element
 https://leetcode.com/problems/find-peak-element/description/

 A peak element is an element that is greater than its neighbors.

 Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.

 The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

 You may imagine that nums[-1] = nums[n] = -∞.

 Example 1:

 Input: nums = [1,2,3,1]
 Output: 2
 Explanation: 3 is a peak element and your function should return the index number 2.
 Example 2:

 Input: nums = [1,2,1,3,5,6,4]
 Output: 1 or 5
 Explanation: Your function can return either index number 1 where the peak element is 2,
 or index number 5 where the peak element is 6.
 Note:

 Your solution should be in logarithmic complexity.

 */
public class FindPeakElement {

    public static void main(String[] args) {
        FindPeakElement fp = new FindPeakElement();


    }

    public int findPeakElementBinSearch(int[] nums) {

        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] > nums[mid + 1])
                r = mid;
            else
                l = mid + 1;
        }
        return l;

    }

    public int findPeakElement(int[] nums) {

        for (int i=0; i<nums.length-1; i++) {
            if (nums[i] > nums[i+1]) {
                return i;
            }
        }

        return nums.length-1;

    }
}
