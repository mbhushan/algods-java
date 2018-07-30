import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Stack;

/**
 Check for balanced parentheses in an expression
 Given an expression string exp , write a program to examine whether the pairs and the
 orders of “{“,”}”,”(“,”)”,”[“,”]” are correct in exp. For example, the program should print
 true for exp = “[()]{}{[()()]()}” and false for exp = “[(])”
 ===============
 INPUT / OUTPUT
 ===============
 input: [()]{}{[()()]()}
 is valid parenthesis: true
 input: [(])
 is valid parenthesis: false
 input: ()(){}[[{()}]]
 is valid parenthesis: true
 input: ()(){}[[()}]]
 is valid parenthesis: false

 */
public class BalancedParenthesis {

    private HashSet<Character> leftSet ;
    private HashSet<Character> rightSet ;
    private Map<Character, Character> rightMap;

    public BalancedParenthesis() {
        leftSet = new HashSet<>();
        rightSet = new HashSet<>();
        rightMap = new HashMap<>();

        init();
    }

    public void init() {
        char [] leftParens = {'(', '{', '[', '<'};
        char [] rightParens = {')', '}', ']', '>'};

        for (char ch :leftParens) {
            leftSet.add(ch);
        }

        for (char ch :rightParens) {
            rightSet.add(ch);
        }

        rightMap.put(')', '(');
        rightMap.put('}', '{');
        rightMap.put(']', '[');
        rightMap.put('>', '<');
    }

    public static void main(String[] args) {
        BalancedParenthesis bp = new BalancedParenthesis();
        String [] expressions = {
                "[()]{}{[()()]()}",
                "[(])",
                "()(){}[[{()}]]",
                "()(){}[[()}]]"
        };

        for (int i=0; i<expressions.length; i++) {
            System.out.println("input: " + expressions[i]);
            System.out.println("is valid parenthesis: " + bp.validParenthesis(expressions[i]));
        }

    }

    public boolean validParenthesis(String input) {
        if (input == null) {
            return false;
        }

        char [] A = input.toCharArray();

        Stack<Character> stack = new Stack<Character>();
        int index = 0;

        while (index < A.length) {
            char ch = A[index];
            if (leftSet.contains(ch)) {
                stack.push(ch);
            } else if (rightSet.contains(ch)) {
                if (!rightMap.containsKey(ch)) {
                    return false;
                }
                char left = getLeftParen(ch);
                if (stack.isEmpty() || stack.peek() != left) {
                    return false;
                }
                stack.pop();
            }
            ++index;
        }
        return stack.isEmpty();
    }

    public char getLeftParen(char right) {
        return rightMap.get(right);
    }


}
