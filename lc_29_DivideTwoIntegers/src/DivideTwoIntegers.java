/**
 29. Divide Two Integers
 https://leetcode.com/problems/divide-two-integers/description/


 Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.

 Return the quotient after dividing dividend by divisor.

 The integer division should truncate toward zero.

 Example 1:

 Input: dividend = 10, divisor = 3
 Output: 3
 Example 2:

 Input: dividend = 7, divisor = -3
 Output: -2
 Note:

 Both dividend and divisor will be 32-bit signed integers.
 The divisor will never be 0.
 Assume we are dealing with an environment which could only store integers within the 32-bit
 signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your
 function returns 231 − 1 when the division result overflows.

 ======================================
 INPUT / OUTPUT
 ======================================
 dividend: 56, divisor: 8
 rec result: 7
 lc res: 7

 dividend: 890, divisor: 10
 rec result: 89
 lc res: 89

 dividend: 155, divisor: 5
 rec result: 31
 lc res: 31

 */

public class DivideTwoIntegers {

    public static void main(String [] args) {
        DivideTwoIntegers dt = new DivideTwoIntegers();

        int [] N = {
          56, 890, 155
        };

        int [] K = {
          8, 10, 5
        };

        for (int i=0; i<N.length; i++) {
            System.out.println("dividend: " + N[i] + ", divisor: " + K[i]);
            int ans = dt.divideRec(N[i], K[i]);
            System.out.println("rec result: " + ans);
            System.out.println("lc res: " + dt.divide(N[i], K[i]));
            System.out.println();
        }

    }

    public int divideRec(int dividend, int divisor) {

        if (dividend < divisor) {
            return 0;
        }

        //  Find the largest multiple so that (divisor * multiple <= dividend),
        //  whereas we are moving with stride 1, 2, 4, 8, 16...2^n for performance reason.
        //  Think this as a binary search.
        int sum = divisor;
        int multiple = 1;

        while ((2*sum) <= dividend) {
            sum += sum;
            multiple += multiple;
        }

        return multiple + divideRec(dividend-sum, divisor);



    }

    public int divide(int dividend, int divisor) {
        int sign = 1;

        if ((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)) {
            sign = -1;
        }

        long ldividend = Math.abs((long) dividend);
        long ldivisor = Math.abs((long) divisor);

        //edge cases.
        if (ldivisor == 0) {
            return Integer.MAX_VALUE;
        }

        //return 0 cases
        if (ldividend == 0 || (ldividend < ldivisor)) {
            return 0;
        }

        long lans = ldivide(ldividend, ldivisor);

        int ans = 0;

        if (lans > Integer.MAX_VALUE) {
            ans = (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        } else {
            ans = (int)(sign * lans);
        }


        return ans;

    }

    public long ldivide(long dividend, long divisor) {

        if (dividend < divisor) {
            return 0;
        }

        //  Find the largest multiple so that (divisor * multiple <= dividend),
        //  whereas we are moving with stride 1, 2, 4, 8, 16...2^n for performance reason.
        //  Think this as a binary search.
        long sum = divisor;
        long multiple = 1;

        while ((2*sum) <= dividend) {
            sum += sum;
            multiple += multiple;
        }

        return multiple + ldivide(dividend-sum, divisor);



    }

}
