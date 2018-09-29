import java.util.Stack;

/**
 20. Valid Parentheses
 https://leetcode.com/problems/valid-parentheses/description/

 Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

 An input string is valid if:

 Open brackets must be closed by the same type of brackets.
 Open brackets must be closed in the correct order.
 Note that an empty string is also considered valid.

 Example 1:

 Input: "()"
 Output: true
 Example 2:

 Input: "()[]{}"
 Output: true
 Example 3:

 Input: "(]"
 Output: false
 Example 4:

 Input: "([)]"
 Output: false
 Example 5:

 Input: "{[]}"
 Output: true

 */
public class ValidParenthesis {

    public static void main(String[] args) {
        ValidParenthesis vp = new ValidParenthesis();

        String [] inputs = {
                "()[]{}",
                "{[]}",
                "([)]"
        };

        for (String s: inputs) {
            System.out.println("input: " + s);
            System.out.println("is valid: " + vp.isValid(s));
            System.out.println();
        }
    }

    public boolean isValid(String s) {
        if (s == null || s.length() < 1) {
            return true;
        }

        Stack<Character> stack = new Stack<Character>();
        int index = 0;
        int len = s.length();

        while (index < len) {
            char ch = s.charAt(index);
            if (isOpening(ch)) {
                stack.push(ch);
            } else if (isClosing(ch)) {
                //check if bracket matches after getting the corresp pair.
                char op = getPairingBracket(ch);
                if (stack.isEmpty() || op != stack.peek()) {
                    return false;
                }
                stack.pop();
            }

            ++index;
        }

        return stack.isEmpty();
    }

    private char getPairingBracket(char ch) {
        if (ch == ')') {
            return '(';
        } else if (ch == ']') {
            return '[';
        } else if (ch == '}') {
            return '{';
        } else if (ch == '>') {
            return '<';
        }
        return ' ';
    }

    private boolean isOpening(char ch) {
        if (ch == '(' || ch == '[' || ch == '{' || ch == '<') {
            return true;
        }

        return false;
    }

    private boolean isClosing(char ch) {
        if (ch == ')' || ch == ']' || ch == '}' || ch == '>') {
            return true;
        }

        return false;
    }
}
