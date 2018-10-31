import java.util.Arrays;

/**
 75. Sort Colors
 Given an array with n objects colored red, white or blue, sort them in-place so that
 objects of the same color are adjacent, with the colors in the order red, white and blue.

 Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

 Note: You are not suppose to use the library's sort function for this problem.

 Example:

 Input: [2,0,2,1,1,0]
 Output: [0,0,1,1,2,2]

 */
public class SortColors {


    public static void main(String[] args) {
        SortColors sc = new SortColors();

        int [] A = {1, 2, 0};
       // int [] A = {2,0,2,1,1,0};
        sc.sortColors(A);
        System.out.println(Arrays.toString(A));
    }
    public void sortColors(int[] nums) {
        int zero  = 0;
        int two = nums.length-1;
        int i = 0;
        while (i <= two) {
            if (nums[i] == 0 && i > zero) {
                //swap i and zero
                swap(nums, zero, i);
                ++zero;
            } else if (nums[i] == 2 && i < two) {
                swap(nums, i, two);
                --two;
            } else {
                ++i;
            }


        }


    }

    private void swap(int [] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
}
