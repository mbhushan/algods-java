import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 282. Expression Add Operators
 https://leetcode.com/problems/expression-add-operators/description/
 Given a string that contains only digits 0-9 and a target value,
 return all possibilities to add binary operators (not unary) +, -, or *
 between the digits so they evaluate to the target value.

 Example 1:

 Input: num = "123", target = 6
 Output: ["1+2+3", "1*2*3"]
 Example 2:

 Input: num = "232", target = 8
 Output: ["2*3+2", "2+3*2"]
 Example 3:

 Input: num = "105", target = 5
 Output: ["1*0+5","10-5"]
 Example 4:

 Input: num = "00", target = 0
 Output: ["0+0", "0-0", "0*0"]
 Example 5:

 Input: num = "3456237490", target = 9191
 Output: []
 */
public class ExpressionAddOperators {

    public List<String> addOperators(String num, int target) {
        Stack<Integer> stack = new Stack<>();
        char [] A = num.toCharArray();
        int [] B = new int[A.length];

        int i=0;
        for (char ch: A) {
            int n = Character.getNumericValue(ch);
            stack.push(n);
            B[i++] = n;
        }
        char [] operators = {'*', '+', '-'};
        List<String> result = new ArrayList<>();
        addOperators(B, stack, target, operators, new ArrayList<>(), result);

        return result;
    }

    public void addOperators(int [] A, Stack<Integer> stack, int target, char [] operators, List<String> buff, List<String> res) {

        if (stack.size() == 1 && stack.peek() == target) {
            StringBuffer sb = new StringBuffer();
            sb.append(A[0]);
            for (int i=1; i<A.length; i++) {
                sb.append(buff.get(i-1));
                sb.append(A[i]);
            }
            //System.out.print(sb + " ");
            res.add(sb.toString());

            return;
        }

        if (stack.size() < 2 ) {
            return;
        }

        for (int i=0; i<operators.length; i++) {
            int first = stack.pop();
            int second = stack.pop();

            int result = operate(second, first, operators[i]);

            buff.add(String.valueOf(operators[i]));

            stack.push(result);

            addOperators(A, stack, target, operators, buff, res);

            stack.pop();
            stack.push(second);
            stack.push(first);
            buff.remove(buff.size()-1);
        }
    }

    private Integer operate(int a, int b, char operand) {
        if (operand == '+') {
            return a+b;
        } else if (operand == '-') {
            return a-b;
        } else if (operand == '*') {
            return a*b;
        }

        return null;
    }
}
