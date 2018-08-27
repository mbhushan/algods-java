import java.util.Arrays;

/**

 31. Next Permutation

 https://leetcode.com/problems/next-permutation/description/

 Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

 If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

 The replacement must be in-place and use only constant extra memory.

 Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.

 1,2,3 → 1,3,2
 3,2,1 → 1,2,3
 1,1,5 → 1,5,1


 ===============
 INPUT / OUTPUT
 ===============

 [1, 2, 3] -> [1, 3, 2]

 [3, 2, 1] -> [1, 2, 3]

 [1, 1, 5] -> [1, 5, 1]

 [3, 1, 2] -> [3, 2, 1]

 [1, 3, 2] -> [2, 1, 3]

 [2, 3, 1] -> [3, 1, 2]

 [5, 1, 1] -> [1, 1, 5]


 */
public class NextPermutation {

    public static void main(String [] args) {
        NextPermutation np = new NextPermutation();

        int [][] inputs = {
                {1,2,3},
                {3,2,1},
                {1,1,5},
                {3,1,2},
                {1,3,2},
                {2,3,1},
                {5,1,1}
        };

        for (int i=0; i<inputs.length; i++) {
            System.out.print(Arrays.toString(inputs[i]) + " -> ");
            np.nextPermutation(inputs[i]);
            System.out.println();
        }

//        int [] A = {5,1,1};
//        System.out.print(Arrays.toString(A) + " -> ");
//        np.nextPermutation(A);
//        System.out.println();
    }

    public void nextPermutation(int[] nums) {

        int len = nums.length;
        int j = len-1;
        int i = 0;
        while (j > 0 && (nums[j-1] >= nums[j])) {
            --j;
        }

        if (j == 0) {
            //System.out.println("j1: " + nums[j]);
            reverse(nums, j, len-1);
        } else {
            --j;
            int k = len-1;
            while (k > j && (nums[k] <= nums[j])) {
                --k;
            }
            swap(nums, j, k);
            reverse(nums, j+1, len-1);

        }

        System.out.print(Arrays.toString(nums));
        System.out.println();
    }

    private void reverse(int [] A, int i, int j) {
        while (i < j) {
            swap(A, i, j);
            ++i;
            --j;
        }
    }

    private void swap(int [] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}
