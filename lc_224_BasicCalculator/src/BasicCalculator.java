import java.util.Stack;

/**
 224. Basic Calculator

 https://leetcode.com/problems/basic-calculator/description/
 Implement a basic calculator to evaluate a simple expression string.

 The expression string may contain open ( and closing parentheses ),
 the plus + or minus sign -, non-negative integers and empty spaces .

 Example 1:

 Input: "1 + 1"
 Output: 2
 Example 2:

 Input: " 2-1 + 2 "
 Output: 3
 Example 3:

 Input: "(1+(4+5+2)-3)+(6+8)"
 Output: 23
 Note:
 You may assume that the given expression is always valid.
 Do not use the eval built-in library function.

 */

public class BasicCalculator {

    public static void main(String[] args) {
        BasicCalculator bc = new BasicCalculator();

        String s = "(1+(4+5+2)-3)";
        int ans = bc.calculate(s);
        System.out.println("ans: " + ans);

    }

    public int calculate(String s) {

        int result = 0;
        int sign = 1;
        int num = 0;

        int i = 0;
        int len = s.length();
        Stack<Integer> stack = new Stack<Integer>();

        while (i < len) {
            if (Character.isDigit(s.charAt(i))) {
                int sum = 0;
                while (i < len && Character.isDigit(s.charAt(i))) {
                    sum = 10*sum + Character.getNumericValue(s.charAt(i));
                    i++;
                }
                result += sum*sign;
            } else {
                if (s.charAt(i) == '+') {
                    sign = 1;
                } else if (s.charAt(i) == '-') {
                    sign = -1;
                } else if (s.charAt(i) == '(') {
                    stack.push(result);
                    stack.push(sign);
                    result = 0;
                    sign = 1;
                } else if (s.charAt(i) == ')') {
                    result = result*stack.pop() + stack.pop();
                }
                ++i;
            }

        }


        return result;

    }


    public int calculate1(String s) {

        char operator = '+';
        Stack<Integer> stack = new Stack<Integer>();

        int i = 0;
        int len = s.length();
        while (i < len) {


            StringBuffer sb = new StringBuffer();
            // char ch = s.charAt(i);
            while ((i < len) && Character.isDigit(s.charAt(i))) {
                sb.append(s.charAt(i));
                ++i;
                // ch = s.charAt(i);

            }

            if (sb.length() > 0) {
                int n = Integer.valueOf(sb.toString());
                stack.push(n);
                --i;
            } else if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                operator = s.charAt(i);
            }
            if (stack.size() == 2) {
                int y = stack.pop();
                int x = stack.pop();
                int ans = (operator == '+') ? (x+y) : (x - y);
                stack.push(ans);
            }
            ++i;
        }

        return stack.pop();

    }
}
