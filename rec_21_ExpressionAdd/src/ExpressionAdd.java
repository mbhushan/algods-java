import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 Given a string that contains only digits 0-9 and a target value, return all possibilities
 to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.

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

 =================
 INPUT / OUTPUT
 =================
 Input Digits: [1, 2, 3]
 Target Value: 6
 valid expressions: 1*2*3 1+2+3

 Input Digits: [2, 3, 2]
 Target Value: 8
 valid expressions: 2*3+2

 Input Digits: [1, 0, 5]
 Target Value: 5
 valid expressions: 1+0*5

 Input Digits: [0, 0]
 Target Value: 0
 valid expressions: 0*0 0+0 0-0

 Input Digits: [3, 4, 5, 6, 2, 3, 7, 4, 9, 0]
 Target Value: 9191
 valid expressions:

 */

public class ExpressionAdd {

    public static void main(String[] args) {

        ExpressionAdd ea = new ExpressionAdd();

        int [][] digits = {
                            {1, 2, 3},
                            {2, 3, 2},
                            {1, 0, 5},
                            {0, 0},
                            {3, 4, 5, 6, 2, 3, 7, 4, 9, 0}
        };
        int [] targets = {6, 8, 5, 0, 9191};

        char [] operators = {'*', '+', '-'};

        for (int i=0; i<digits.length; i++) {
            System.out.println("Input Digits: " + Arrays.toString(digits[i]));
            System.out.println("Target Value: " + targets[i]);
            ea.expressionAdd(digits[i], targets[i], operators);
            System.out.println();
        }

        //ea.expressionAdd(digits[1], targets[1], operators);

    }

    public void expressionAdd(int [] A, int target, char [] operators) {
        Stack<Integer> stack = new Stack<>();

        for (int n: A) {
            stack.push(n);
        }

        //System.out.println("stack: " + stack);

        System.out.print("valid expressions: ");
        addOperators(A, stack, target, operators, new ArrayList<>());
        System.out.println();

    }

    public void addOperators(int [] A, Stack<Integer> stack, int target, char [] operators, List<String> buff) {

        if (stack.size() == 1 && stack.peek() == target) {
            StringBuffer sb = new StringBuffer();
            sb.append(A[0]);
            for (int i=1; i<A.length; i++) {
               sb.append(buff.get(i-1));
               sb.append(A[i]);
            }
            System.out.print(sb + " ");

            return;
        }

        if (stack.size() < 2 || stack.peek() > target) {
            return;
        }

        for (int i=0; i<operators.length; i++) {
            int first = stack.pop();
            int second = stack.pop();

            int result = operate(second, first, operators[i]);

            buff.add(String.valueOf(operators[i]));

            stack.push(result);

            addOperators(A, stack, target, operators, buff);

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
