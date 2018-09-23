import java.util.Stack;

/**
 227. Basic Calculator II
 https://leetcode.com/problems/basic-calculator-ii/description/

 Implement a basic calculator to evaluate a simple expression string.

 The expression string contains only non-negative integers, +, -, *, / operators and empty spaces .
 The integer division should truncate toward zero.

 Example 1:

 Input: "3+2*2"
 Output: 7
 Example 2:

 Input: " 3/2 "
 Output: 1
 Example 3:

 Input: " 3+5 / 2 "
 Output: 5
 Note:

 You may assume that the given expression is always valid.
 Do not use the eval built-in library function.

 ============input / output =========
 input: 3+2*2
 ans: 7
 input:  3/2
 ans: 1
 input:  3+5 / 2
 ans: 5
 */
public class BasicCalc2 {
    public static void main(String[] args) {

        BasicCalc2 bc = new BasicCalc2();

        String [] exp = {
                "3+2*2",
                " 3/2 ",
                " 3+5 / 2 "
        };

        for (String e: exp) {
            System.out.println("input: " + e);
            System.out.println("ans: " + bc.calculate(e));
        }

    }

    public int calculate(String s) {
        int result = 0;

        if (s == null || s.length() < 1) {
            return result;
        }

        //Steps;
        //a. go through each char in the input
        //b. accumuluate number.
        //c. if + or - add the sign with the number and put it in the stack.
        //d. if / or * then perform operation with top of the stack value.
        //e. in the end do summation of everything in stack as only + or -values would be there with the sign attached.
        //f. return the result.
        Stack<Integer> stack = new Stack<>();
        int len = s.length();
        int num = 0;
        int sign = '+';

        for (int i=0; i<len; i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                num = num*10 + Character.getNumericValue(ch);
            }
            if (((!Character.isDigit(ch)) && ch != ' ') || i == len-1) {
                if (sign == '+') {
                    stack.push(num);
                } else if (sign == '-') {
                    stack.push((-num));
                } else if (sign == '/') {
                    stack.push(stack.pop() / num);
                } else if (sign == '*') {
                    stack.push(stack.pop() * num);
                }
                sign = ch;
                num = 0;
            }
        }


        for (int x: stack) {
            result += x;
        }

        return result;
    }
}
