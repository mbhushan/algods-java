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


 */
public class NextPermutation {

    public static void main(String [] args) {
        NextPermutation np = new NextPermutation();

        int [][] inputs = {
                {1,2,3},
                {3,2,1},
                {1,1,5}
        };

        for (int i=0; i<inputs.length; i++) {
            System.out.println("input array: " + Arrays.toString(inputs[i]));
            np.nextPermutation(inputs[i]);
            System.out.println();
        }
    }

    public void nextPermutation(int[] nums) {

    }
}
