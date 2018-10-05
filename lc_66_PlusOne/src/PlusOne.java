import java.util.Arrays;
import java.util.Stack;

/**
 66. Plus One
 https://leetcode.com/problems/plus-one/description/
 Given a non-empty array of digits representing a non-negative integer, plus one to the integer.

 The digits are stored such that the most significant digit is at the head of the list,
 and each element in the array contain a single digit.

 You may assume the integer does not contain any leading zero, except the number 0 itself.

 Example 1:

 Input: [1,2,3]
 Output: [1,2,4]
 Explanation: The array represents the integer 123.
 Example 2:

 Input: [4,3,2,1]
 Output: [4,3,2,2]
 Explanation: The array represents the integer 4321.
 */
public class PlusOne {

    public static void main(String[] args) {
        int [] A = {4,3,2,1};
        PlusOne po = new PlusOne();

        System.out.println("input: " + Arrays.toString(A));
        System.out.println("output: " + Arrays.toString(po.plusOne(A)));
    }

    public int[] plusOne(int[] digits) {

        int len = digits.length;
        Stack<Integer> stack = new Stack<>();


        int carry = 1;
        int i = len-1;

        while (i >= 0) {
            int sum = digits[i] + carry;
            carry = sum / 10;
            sum = sum % 10;
            stack.add(sum);
            --i;
        }
        if (carry > 0) {
            stack.add(carry);
        }
        int [] res = new int[stack.size()];
        i = 0;
        while (!stack.isEmpty()) {
            res[i] = stack.pop();
            ++i;
        }

        return res;

    }
}
