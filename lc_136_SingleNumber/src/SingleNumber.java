import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 136. Single Number
 https://leetcode.com/problems/single-number/description/

 Given a non-empty array of integers, every element appears twice except for one. Find that single one.

 Note:

 Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

 Example 1:

 Input: [2,2,1]
 Output: 1
 Example 2:

 Input: [4,1,2,1,2]
 Output: 4


 */

public class SingleNumber {

    public static void main(String[] args) {
        SingleNumber sn = new SingleNumber();

        int [] A = {4,1,2,1,2};

        System.out.println("input: " + Arrays.toString(A));
        System.out.println("ans: " + sn.singleNumber(A));

    }

    public int singleNumber(int[] nums) {
        if (nums == null || nums.length < 1) {
            return -1;
        }

        //Steps:
        //a. have a hashset, if element doesnt exist in hashset then add it.
        //b. if it exists then remove it.
        //c. ideally only the single element should be the last remaining element.
        Set<Integer> set = new HashSet<Integer>();
        for (int n: nums) {
            if (set.contains(n)) {
                set.remove(n);
            } else {
                set.add(n);
            }
        }

        return !set.isEmpty() ? set.iterator().next() : -1;
    }
}
