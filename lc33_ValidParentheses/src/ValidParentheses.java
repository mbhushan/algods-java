import java.util.Stack;

/**
 * Created by manib on 10/17/17.

 33 Valid Parentheses
 Given a string containing just the characters ’(’, ’)’, ’’, ’’, ’[’ and ’]’, determine if the
 input string is valid. The brackets must close in the correct order, "()" and "()[]" are all
 valid but "(]" and "([)]" are not.

 */

public class ValidParentheses {

    public static void main(String [] args) {
        String [] strings = {
                "[[]](){}", "(]", "[", "(]", ")", ")([]}{", "{}", "[]"
        };

        ValidParentheses vp = new ValidParentheses();
        vp.evalueate(strings);

    }

    public void evalueate(String [] strings) {
        for (int i=0; i<strings.length; i++) {
            boolean flag = checkValidity(strings[i]);
            System.out.println(strings[i] + ": is valid? " + flag);
        }
    }

    public boolean checkValidity(String str) {
        if (str == null || str.isEmpty()) {
            System.out.println("Null or Empty String!");
            return false;
        }

        Stack<Character> stack = new Stack<Character>();

        char [] tokens = str.toCharArray();
        int len = tokens.length;

        for (int i=0; i<len; i++) {
            char ch = tokens[i];
            if (isOpen(ch)) {
                stack.push(ch);
            } else {
                //assuming string tokens are either open or closed brackets.
                if (stack.isEmpty()) {
                    return false;
                }
                char pop = stack.pop();
                if (!isPair(pop, ch)) {
                    return false;
                }
            }
        }
        if (!stack.isEmpty()) {
            return false;
        }

        return true;
    }

    public boolean isPair(char left, char right) {
        if (left == '(' && right == ')') {
            return true;
        } else if (left == '[' && right == ']') {
            return true;
        } else if (left == '{' && right == '}') {
            return true;
        }
        return false;
    }

    public boolean isOpen(char ch) {
        String st = "[{(<";
        if (st.indexOf(ch) >= 0) {
            return true;
        }
        return false;
    }
}
