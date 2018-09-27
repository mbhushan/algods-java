import java.util.Arrays;

/**
 338. Counting Bits
 https://leetcode.com/problems/counting-bits/description/

 Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate
 the number of 1's in their binary representation and return them as an array.

 Example 1:

 Input: 2
 Output: [0,1,1]
 Example 2:

 Input: 5
 Output: [0,1,1,2,1,2]
 Follow up:

 It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in
 linear time O(n) /possibly in a single pass?
 Space complexity should be O(n).
 Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or
 in any other language.

 */
public class CountBits {

    public static void main(String[] args) {
        int n = 5;
        //ans: [0, 1, 1, 2, 1, 2]

        CountBits cb = new CountBits();

        System.out.println("ans: " + Arrays.toString(cb.countBits(n)));
    }

    public int[] countBits(int num) {
        int [] res = new int[num+1];

        for (int i=0; i<=num; i++) {
            res[i] = bitCount(i);
        }

        return res;
    }

    public int bitCount(int x) {
        int count = 0;
        for (count = 0; x != 0; count++) {
            x = x & (x-1);
        }

        return count;
    }
}
