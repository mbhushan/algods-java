import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 301. Remove Invalid Parentheses
 https://leetcode.com/problems/remove-invalid-parentheses/description/

 Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

 Note: The input string may contain letters other than the parentheses ( and ).

 Example 1:
 Input: "()())()"
 Output: ["()()()", "(())()"]


 Example 2:
 Input: "(a)())()"
 Output: ["(a)()()", "(a())()"]


 Example 3:
 Input: ")("
 Output: [""]

 ==================
 INPUT / OUTPUT
 ==================

 */

public class InvalidParentheses {

    public static void main(String[] args) {
        InvalidParentheses ip = new InvalidParentheses();

        String [] inputs = {"()())()", "(a)())()", ")("};

        for (String input:  inputs) {
            //ip.removeParenBFS(input);
            ip.removeParenDFS(input);
        }


    }

    public void removeParenDFS(String input) {
        char [] A = input.toCharArray();

        System.out.println("input: " + input);
        Set<String> result = new HashSet<>();

        int left = 0;
        int right = 0;
        StringBuffer sb = new StringBuffer();

        for (char ch: A) {
            if (ch == '(') {
                ++left;
            } else if (ch == ')') {
                if (left > 0) {
                    --left;
                } else {
                    ++right;
                }
            }
        }
        //System.out.println("left: " + left + "; right: " + right );

        removeParenDFS(A, left, right, 0, 0, result, sb);
        //System.out.println("DFS result: " + result);
        System.out.print("DFS result: ");

        if (result.size() == 0) {
            System.out.println("NO RESULTS!");
            return;
        }

        int maxLen = Collections.max(result).length();

        for (String s: result) {
            if (s.length() == maxLen) {
                System.out.print(s + " ");
            }
        }
        System.out.println();


    }

    private void removeParenDFS(char [] A, int left, int right, int index, int open, Set<String> result,
                                   StringBuffer sb) {
        if (index == A.length && left == 0 && right == 0 && open == 0) {
            //System.out.println("inside result: " + sb.toString());
            result.add(sb.toString());
            return;
        }

        if (index == A.length || left < 0 || right < 0 || open < 0) {
            return;
        }

        char ch = A[index];
        //int len = sb.length();

        if (ch == '(') {
            //exclude (
            removeParenDFS(A, left-1, right, index+1, open, result, sb);
            //include (
            removeParenDFS(A, left, right, index+1, open+1, result, sb.append(ch));
            sb.deleteCharAt(sb.length()-1);
        } else if (ch == ')') {
            //exclude (
            removeParenDFS(A, left, right-1, index+1, open, result, sb);
            //include (
            removeParenDFS(A, left, right, index+1, open-1, result, sb.append(ch));
            sb.deleteCharAt(sb.length()-1);
        } else {
            //non-bracket characters
            removeParenDFS(A, left, right, index+1, open, result, sb.append(ch));
            sb.deleteCharAt(sb.length()-1);
        }

        //sb.setLength(len);
    }

    public void removeParenBFS(String input) {
        if (input == null || input.length() < 1) {
            return;
        }

        Queue<String> queue = new LinkedList<String>();
        Set<String> result = new HashSet<>();
        int minEdit = Integer.MAX_VALUE;
        boolean found = false;

        queue.add(input);

        while (!queue.isEmpty()) {
            String str = queue.remove();

            if (isValid(str)) {
                int edit = input.length() - str.length();
                if (edit <= minEdit) {
                    result.add(str);
                    minEdit = edit;
                }
                found = true;
            }

            if (found) {
                continue;
            }

            //level i removal
            for (int i = 0; i < str.length(); i++) {
                String tmp = str.substring(0, i) + str.substring(i + 1);
                queue.add(tmp);
            }
        }

        System.out.println("output: " + result);
        System.out.println("min removal: " + minEdit);
        System.out.println();

    }

    private boolean isValid(String input) {

        int open = 0;
        for (int i=0; i<input.length(); i++) {
            if (input.charAt(i) == '(') {
                ++open;
            } else if (input.charAt(i) == ')') {
                --open;
            }

            if (open < 0) {
                return false;
            }
        }

        return open == 0;
    }
}
