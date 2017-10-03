import java.util.Stack;

/**
 * Created by manib on 10/3/17.
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression. For example:

 ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 */
public class ReversePolishEval {

    public static void main(String [] args) {
        String[] expression = new String[] { "2", "11", "+", "3", "*" };

        System.out.println("result: " + evalExpression(expression));
    }

    public static Integer evalExpression(String [] expression) {
        if (expression == null || expression.length == 0) {
            return null;
        }

        int exprLen = expression.length;
        String operators = "+-/*";
        Stack<Integer> stack = new Stack<Integer>();

        for (int i=0; i<exprLen; i++) {
            String tok = expression[i];

            if (!operators.contains(tok)) { //its number
                int num = Integer.parseInt(tok);
                stack.push(num);
            } else {
                int y = stack.pop();
                int x = stack.pop();

                switch (tok) {
                    case "+":
                        stack.push( x + y);
                        break;
                    case "-":
                        stack.push(x - y);
                        break;
                    case "*":
                        stack.push(x * y);
                        break;
                    case "/":
                        if (y == 0) {
                            System.out.println("Divide by zero!");
                            return null;
                        }
                        stack.push(x / y);
                        break;
                }
            }

        }
        return stack.pop();
    }
}
