import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

/**
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

 ============================
 INPUT / OUTPUT - Solution 1
 ============================
 min edit: 1
 solutions: [()()(), (())()]
 min edit: 1
 solutions: [()v, (v)]
 min edit: 1
 solutions: [(a)()(), (a())()]
 min edit: 2
 solutions: []


 solution using BFS:
 input string: ()())()
 bfs result: [(())(), ()()()]

 input string: ()v)
 bfs result: [(v), ()v]

 input string: (a)())()
 bfs result: [(a())(), (a)()()]

 input string: )(
 bfs result: []

 */

public class RemoveInvalidParentheses {

    public static void main(String[] args) {
        RemoveInvalidParentheses rip = new RemoveInvalidParentheses();

        String [] expressions = {
                "()())()",
                "()v)",
                "(a)())()",
                ")("
        };

        //rip.parenthesis("()())()");

        for (String e: expressions) {
            rip.parenthesis(e);
        }
        System.out.println();

//        String [] tests = {"()()", "(()))", "()()()", "(()))"};
//
//        for (String s: tests) {
//            List<Character> list = s.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
//            boolean flag = rip.validParen(list);
//            System.out.println(s + ": " + flag);
//        }

//        String s = "abcd";
//        List<Character> list = s.chars().mapToObj(e -> (char)e).collect(Collectors.toList());
//        System.out.println("list test: " +  list);

        System.out.println();
        System.out.println("solution using BFS: ");
        for (String e: expressions) {
            System.out.println("input string: " + e);
            rip.rmParenthesisBFS(e);
            System.out.println();
        }

    }

    public List<String> rmParenthesisBFS(String input) {

        List<String> result = new ArrayList<String>();
        Set<String> visited = new HashSet<>();

        Queue<String> queue = new LinkedList<>();

        queue.add(input);
        visited.add(input);

        boolean flag = false;

        while (!queue.isEmpty()) {
            String candidate = queue.remove();
            List<Character> list = candidate.chars().mapToObj(e -> (char)e).collect(Collectors.toList());


            if (validParen(list)) {
                //System.out.println("list: " + list);
                result.add(candidate);
                flag = true;
            }

            if (flag) continue;

            for (int i=0; i<candidate.length(); i++) {
                if (candidate.charAt(i) != '(' && candidate.charAt(i) != ')') {
                     continue;
                }

                String str = candidate.substring(0, i) + candidate.substring(i+1);
                if (visited.contains(str)) {
                    continue;
                }
                    //System.out.println(str);
                    visited.add(str);
                    queue.add(str);
            }
        }

        System.out.println("bfs result: " + result);
       // System.out.println("visited: " + visited);

        return null;
    }

    public void parenthesis(String input) {
        if (input == null || input.length() < 1) {
            return;
        }

        char [] A = input.toCharArray();
        String [] result = new String[1];
        result[0] = "";
        Map<Integer, Set<String>> hmap = new HashMap<>();

        parenthesis(A, new Stack<>(), 0, new ArrayList<Character>(), result, hmap);

       // System.out.println("result: " + result[0]);
        int minEdit = A.length - result[0].length();
        System.out.println("min edit: " + minEdit);

        System.out.println("solutions: " + hmap.get(minEdit));


    }

    private void parenthesis(char [] A, Stack<Character> stack, int index, List<Character> list, String [] result,
                             Map<Integer, Set<String>> hmap ) {

        if (index > A.length) {
            return;
        }

        if (index <= A.length) {
            if (validParen(list) && list.size() >= result[0].length()) {
                result[0] = list.stream().map(Object::toString).collect(Collectors.joining());
                int diff = A.length - result[0].length();
                Set<String> ans = new HashSet<>();
               // System.out.println("diff: " + diff);
                if (hmap.containsKey(diff)) {
                    ans = hmap.get(diff);
                }
                ans.add(result[0]);
                hmap.put(diff, ans);
            }
            //System.out.println(list.stream().map(Object::toString).collect(Collectors.joining()));

        }

//        if (A[index] == '(') {
//            stack.push(A[index]);
//        } else if (A[index] == ')') {
//            if (!stack.isEmpty() && stack.peek() == '(') {
//                stack.pop();
//            } else {
//                return;
//            }
//        }

        for (int i=index; i<A.length; i++) {
            list.add(A[i]);
            parenthesis(A, stack, i+1, list, result, hmap);
            list.remove(list.size()-1);
        }


    }

    public boolean validParen(List<Character> list) {

        Stack<Character> stack = new Stack<Character>();

        for (char ch :  list) {
            if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
