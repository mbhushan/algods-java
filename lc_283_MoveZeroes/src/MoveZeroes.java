import java.util.Arrays;

/**

 283. Move Zeroes
 https://leetcode.com/problems/move-zeroes/description/

 Given an array nums, write a function to move all 0's to the end of it while maintaining the
 relative order of the non-zero elements.
 Example:
 Input: [0,1,0,3,12]
 Output: [1,3,12,0,0]
 Note:
 You must do this in-place without making a copy of the array.
 Minimize the total number of operations.

 =======================
 INPUT / OUTPUT
 =======================

 input: [0, 1, 0, 3, 12]
 final array: [1, 3, 12, 0, 0]


 */
public class MoveZeroes {

    public static void main(String[] args) {
        MoveZeroes mz = new MoveZeroes();

        int [][] A = {
                {0,1,0,3,12},
        };

        for (int i=0; i<A.length; i++) {
            System.out.println("input: " + Arrays.toString(A[i]));
            mz.moveZeroes(A[i]);
            //mz.moveZeroesNonRelative(A[i]);
            System.out.println();
        }

    }

    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length < 1) {
            return;
        }

        int nonZeroIndex = 0;

        for (int i=0; i<nums.length; i++) {
            if (nums[i] != 0) {
                nums[nonZeroIndex++] = nums[i];
            }
        }

        for (int i=nonZeroIndex; i<nums.length; i++) {
            nums[i] = 0;
        }

        System.out.println("final array: " + Arrays.toString(nums));

    }

    public void moveZeroesOld(int[] nums) {

        if (nums == null || nums.length < 1) {
            return;
        }

        int left = 0, right = 0;

        while (right < nums.length) {

            while (left < nums.length && nums[left] != 0) {
                ++left;
            }

            while (right < nums.length && nums[right] == 0) {
                ++right;
            }

            if (left < right && right < nums.length) {
                nums[left] = nums[right];
                nums[right] = 0;
            }
        }

        while (left < nums.length) {
            nums[left] = 0;
            ++left;
        }

        System.out.println("final array: " + Arrays.toString(nums));
    }

    //relative order not maintained for non-zero elements.
    public void moveZeroesNonRelative(int[] nums) {

        if (nums == null || nums.length < 1) {
            return;
        }

        int left = 0;
        int right = nums.length-1;

        while (left <= right) {
            while (nums[left] != 0) {
                ++left;
            }

            while (nums[right] == 0) {
                --right;
            }

            if (left < right) {
                swap(nums, left, right);
            }

        }

        System.out.println("final array: " + Arrays.toString(nums));

    }

    private void swap(int [] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}
