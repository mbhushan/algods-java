import java.util.Arrays;

/**
 238. Product of Array Except Self
 https://leetcode.com/problems/product-of-array-except-self/description/

 Given an array nums of n integers where n > 1,  return an array output such that output[i]
 is equal to the product of all the elements of nums except nums[i].

 Example:

 Input:  [1,2,3,4]
 Output: [24,12,8,6]
 Note: Please solve it without division and in O(n).

 Follow up:
 Could you solve it with constant space complexity? (The output array does not count as extra space
 for the purpose of space complexity analysis.)

 */
public class ProductArrayExceptSelf {

    public static void main(String[] args) {
        int [] A = {1,2,3,4};

        ProductArrayExceptSelf pr = new ProductArrayExceptSelf();

        System.out.println("solution: " + Arrays.toString(pr.productExceptSelf(A)));
    }

    public int[] productExceptSelf(int[] nums) {
        int [] res = new int[nums.length];

        Arrays.fill(res, 1);

        int left = 1;
        for (int i=1; i<nums.length; i++) {
            res[i] = res[i-1] * nums[i-1];
        }

        int right = 1;
        for (int i=nums.length-1; i>=0; i--) {
            res[i] = res[i]*right;
            right = right*nums[i];
        }

        return res;
    }
}
