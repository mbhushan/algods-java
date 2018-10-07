/**
 371. Sum of Two Integers
 Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.

 Example 1:

 Input: a = 1, b = 2
 Output: 3
 Example 2:

 Input: a = -2, b = 3
 Output: 1

 */
public class SumTwoIntegers {

    public static void main(String[] args) {
        int a = 2;
        int b = 3;
        System.out.println(new SumTwoIntegers().getSum(a, b));
    }

    /**
     Let's look at an example of adding 2 + 3:

     0 0 1 0 (2)
     0 0 1 1 (3)

     First, let's look at the rightmost bits. 0 + 1 = 1 "one" and 1 "zero", so we have 1. This gives us:

     0 0 1 0 (2)
     0 0 1 1 (3)

     Now let's look at the next digit (2nd from the right. 1 + 1. Here we have to carry since we have two "ones", and
     the maximum we can have in binary is 1, just as the maximum we can have in base 10 is 10. So just like in regular
     decimal addition, we set our bit here to 0 and carry the 1 to the left. This gives us our final answer:

     0 1 0 1 (5)

     Now when we look at the final solution, all we are doing is repeating these steps over and over again until
     we have nothing left to carry.

     How do we check if only one bit is 1? Use ^.
     How do we check if both bits are 1? Use &.

     Then the final step is to work out when to terminate your addition. I would argue this is actually quite
     tricky and pushes the question above easy. But if you wouldn't work it out you could actually just use a
     loop through all of the bits.
     * @param a
     * @param b
     * @return
     */

    public int getSum(int a, int b) {
        if(a == 0) {
            return b;
        }

        if(b == 0) {
            return a;
        }

        int carry = 0;

        while(b != 0) {

            // If both bits are 1, we set the bit to the left (<<1) to 1 -- this is the carry step
            carry = (a & b) << 1;

            // If both bits are 1, this will give us 0 (we will have a carry from the step above)
            // If only 1 bit is 1, this will give us 1 (there is nothing to carry)
            a = a ^ b;

            b = carry;
        }

        return a;
    }
}
