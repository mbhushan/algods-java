import java.util.ArrayList;
import java.util.List;

/**
 22. Generate Parentheses
 https://leetcode.com/problems/generate-parentheses/description/

 Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

 For example, given n = 3, a solution set is:

 [
 "((()))",
 "(()())",
 "(())()",
 "()(())",
 "()()()"
 ]

 */
public class GenerateParenthesis {

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();

        helper(n, 0, 0, 0, new StringBuffer(), res);

        return res;
    }

    private void helper(int n, int leftIndex, int rightIndex, int index, StringBuffer sb, List<String> res) {
        if (index == n*2) {
            res.add(sb.toString());
            return;
        }

        if (leftIndex < n) {
            sb.append('(');
            helper(n, leftIndex+1, rightIndex, index+1, sb, res);
            sb.deleteCharAt(sb.length()-1);
        }

        if (rightIndex < leftIndex) {
            sb.append(')');
            helper(n, leftIndex, rightIndex+1, index+1, sb, res);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
