/**
 7. Reverse Integer
 https://leetcode.com/problems/reverse-integer/description/
 Given a 32-bit signed integer, reverse digits of an integer.

 Example 1:

 Input: 123
 Output: 321
 Example 2:

 Input: -123
 Output: -321
 Example 3:

 Input: 120
 Output: 21
 Note:
 Assume we are dealing with an environment which could only store integers within the 32-bit signed
 integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 0
 when the reversed integer overflows.

 */
public class ReverseInteger {

    public static void main(String[] args) {
        ReverseInteger ri = new ReverseInteger();

        int x = 12345;
        System.out.println("ans: " + ri.reverse(x));
    }

    public int reverse(int x) {
        long result =0;
        while(x != 0)
        {
            result = (result*10) + (x%10);
            if(result > Integer.MAX_VALUE) return 0;
            if(result < Integer.MIN_VALUE) return 0;
            x = x/10;
        }
        return (int)result;


    }

    public int reverseValid(int x) {
        int ans = 0, temp = 0, carry = 0;
        while(x != 0){
            carry = x%10;
            temp = ans;
            ans = ans*10 + carry;
            x = x/10;
        }
        //Check one time whether there is overflow in the last round, e.g.1000 000 000 3
        if((ans-carry)/10 == temp){
            return ans;
        }else return 0;
    }
}
