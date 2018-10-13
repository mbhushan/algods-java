import java.util.Stack;

/**
 844. Backspace String Compare
 https://leetcode.com/problems/backspace-string-compare/description/

 Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.

 Example 1:

 Input: S = "ab#c", T = "ad#c"
 Output: true
 Explanation: Both S and T become "ac".
 Example 2:

 Input: S = "ab##", T = "c#d#"
 Output: true
 Explanation: Both S and T become "".
 Example 3:

 Input: S = "a##c", T = "#a#c"
 Output: true
 Explanation: Both S and T become "c".
 Example 4:

 Input: S = "a#c", T = "b"
 Output: false
 Explanation: S becomes "c" while T becomes "b".

 */

public class BackspaceStringCompare {

    public static void main(String[] args) {
        String S = "a##c", T = "#a#c";

        BackspaceStringCompare bs = new BackspaceStringCompare();
        boolean ans = bs.backspaceCompare(S, T);
        System.out.println(ans);
    }

    public boolean backspaceCompare(String S, String T) {
        int i = S.length() - 1, j = T.length() - 1;
        while (true) {
            for (int back = 0; i >= 0 && (back > 0 || S.charAt(i) == '#'); --i)
                back += S.charAt(i) == '#' ? 1 : -1;
            for (int back = 0; j >= 0 && (back > 0 || T.charAt(j) == '#'); --j)
                back += T.charAt(j) == '#' ? 1 : -1;
            if (i >= 0 && j >= 0 && S.charAt(i) == T.charAt(j)) {
                i--; j--;
            } else
                return i == -1 && j == -1;
        }
    }

    public boolean backspaceCompare1(String S, String T) {
        if (S == null && T == null) {
            return true;
        }
        if (S == null || T == null) {
            return false;
        }

        Stack<Character> stack1 = new Stack<>();
        Stack<Character> stack2 = new Stack<>();

        for (char ch: S.toCharArray()) {
            if (ch == '#') {
                if (!stack1.isEmpty()) {
                    stack1.pop();
                }
            } else {
                stack1.push(ch);
            }
        }

        for (char ch: T.toCharArray()) {
            if (ch == '#') {
                if (!stack2.isEmpty()) {
                    stack2.pop();
                }
            } else {
                stack2.push(ch);
            }
        }

        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            if (stack1.peek() != stack2.peek()) {
                return false;
            }
            stack1.pop();
            stack2.pop();
        }

        return stack1.isEmpty() && stack2.isEmpty();
    }
}
