import java.util.Arrays;

/**
 Imagine x is an operand and * is a binary operator.
 We say a string of x and * follows Reverse Polish notation if it is a postfix notation.

 For example strings xx*, x, and xx*xx** follow Reverse Polish notation.

 Given a string of x and *, how many insert, delete, and replace
 operations are needed to make the string follow the RPN.

 For example, xx* need 0 operation to follow RPN since it already follows RPN.
 x*x needs two operations to become xx* which follows RPN.
 *xx* needs one operation to become xx* which follows RPN.

 Your algorithm should work for a string of size up to 100

 ========
 Len: 4
 The min operations for **** is 3
 The min operations for x**x is 2
 The min operations for *x*x is 2
 The min operations for xx** is 1
 The min operations for x*** is 2
 The min operations for **xx is 3
 The min operations for xxxx is 2
 =========
 Len 5:

 The min operations for x**xx is 2
 The min operations for ***xx is 3
 The min operations for x*xxx is 3
 The min operations for x*x*x is 2
 The min operations for **xx* is 2
 The min operations for *x*xx is 2

 =====================
 INPUT / OUTPUT
 ====================
 Calculating Edit for len: 4
 input: [*, *, *, *]
 result: 3
 input: [x, *, *, x]
 result: 2
 input: [*, x, *, x]
 result: 2
 input: [x, x, *, *]
 result: 1
 input: [x, *, *, *]
 result: 2
 input: [*, *, x, x]
 result: 3
 input: [x, x, x, x]
 result: 2

 Calculating Edit for len: 5
 input: [x, *, *, x, x]
 result: 2
 input: [*, *, *, x, x]
 result: 3
 input: [x, *, x, x, x]
 result: 3
 input: [x, *, x, *, x]
 result: 2
 input: [*, *, x, x, *] //TODO: bug - it should be 2 edits.
 result: 3
 input: [*, x, *, x, x]
 result: 2
 */

public class MinEditRPN {

   // private int minVal = Integer.MAX_VALUE;

    public static void main(String[] args) {
        MinEditRPN me = new MinEditRPN();

        String [] S4 = {
                "****", "x**x", "*x*x", "xx**", "x***", "**xx", "xxxx"
        };

        String [] S5 = {
          "x**xx", "***xx", "x*xxx", "x*x*x", "**xx*", "*x*xx"
        };

        System.out.println("Calculating Edit for len: 4");
        for (String s: S4) {
            me.minEdit(s.toCharArray());
        }

        System.out.println();
        System.out.println("Calculating Edit for len: 5");

        for (String s: S5) {
            me.minEdit(s.toCharArray());
        }

//        String s = "**xx";
//        me.minEdit(s.toCharArray());

    }

    public void minEdit(char [] S) {
       // minVal = Integer.MAX_VALUE;
        System.out.println("input: " + Arrays.toString(S));
        int result = minEdit(S, 0, S.length-1);

        System.out.println("result: " + result);

    }

    private int minEdit(char [] S, int start, int end) {

        if (end == start) {
            if (S[start] == 'x') {
                return 0;
            } else {
                return 1;
            }
        }

        if (end - start == 1) {
            if (S[start] == 'x' && S[end] == 'x') {
                return 1;
            } else if (S[start] == 'x' && S[end] == '*') {
                return 1;
            } else if (S[start] == '*' && S[end] == 'x') {
                return 1;
            } else if (S[start] == '*' && S[end] == '*') {
                return 2;
            }
        }

        if (S[end] == '*') {
            int minVal = Integer.MAX_VALUE;
            for (int k=start; k<end-1; k++) {
                int left = minEdit(S, start, k);
                int right = minEdit(S, k+1, end-1);
                minVal = Math.min(minVal, left + right);
            }
            return minVal;
        } else { //last char is 'x'
            int minVal = Integer.MAX_VALUE;
            //replace it with '*', rest is similar to above.
            for (int k=start; k<end-1; k++) {
                int left = minEdit(S, start, k);
                int right = minEdit(S, k+1, end-1);
                minVal = Math.min(minVal, left + right+1);

            }

            //insert '*' at the end.
            for (int k=start; k<end; k++) {
                int left = minEdit(S, start, k);
                int right = minEdit(S, k+1, end);
                minVal = Math.min(minVal, left + right+1);

            }

            //delete 'x'
            int temp = minEdit(S, start, end-1)+1;
            minVal = Math.min(minVal, temp);

            return minVal ;
        }
    }
}
