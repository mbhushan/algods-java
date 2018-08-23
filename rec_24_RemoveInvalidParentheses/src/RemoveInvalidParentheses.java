import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
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

 Solution with DFS:
 input: ()())()
 DFS result: (())() ()()()

 input: ()v)
 DFS result: v

 input: (a)())()
 DFS result: (a()) (a)() a()()

 input: )(
 DFS result:


 Solution with DFS2:
 input: ()())()
 left: 0; right: 1
 DFS result: (())() ()()()

 input: ()v)
 left: 0; right: 1
 DFS result: ()v (v)

 input: (a)())()
 left: 0; right: 1
 DFS result: (a())() (a)()()

 input: )(
 left: 1; right: 1
 DFS result:

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

        //rip.rmParenthesisDFS("()())()");

        System.out.println("Solution with DFS: ");
        for (String e: expressions) {
            System.out.println("input: " + e);
            rip.rmParenthesisDFS(e);
            System.out.println();
        }
        System.out.println();


        System.out.println("Solution with DFS2: ");
        for (String e: expressions) {
            System.out.println("input: " + e);
            rip.rmParenthesisDFS2(e);
            System.out.println();
        }
        System.out.println();

    }

    public void rmParenthesisDFS2(String input) {
        char [] A = input.toCharArray();

        Set<String> result = new TreeSet<>();

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
        System.out.println("left: " + left + "; right: " + right );

        rmParenthesisDFS2(A, left, right, 0, 0, result, sb);
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

        //System.out.println(Collections.max(result));

    }

    private void rmParenthesisDFS2(char [] A, int left, int right, int index, int open, Set<String> result,
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
        int len = sb.length();

        if (ch == '(') {
            //exclude (
            rmParenthesisDFS2(A, left-1, right, index+1, open, result, sb);
            //include (
            rmParenthesisDFS2(A, left, right, index+1, open+1, result, sb.append(ch));
            //sb.deleteCharAt(sb.length()-1);
        } else if (ch == ')') {
            //exclude (
            rmParenthesisDFS2(A, left, right-1, index+1, open, result, sb);
            //include (
            rmParenthesisDFS2(A, left, right, index+1, open-1, result, sb.append(ch));
            //sb.deleteCharAt(sb.length()-1);
        } else {
            //non-bracket characters
            rmParenthesisDFS2(A, left, right, index+1, open, result, sb.append(ch));
            //sb.deleteCharAt(sb.length()-1);
        }

        sb.setLength(len);
    }

    public void rmParenthesisDFS(String input) {
        char [] A = input.toCharArray();

        Set<String> result = new TreeSet<>();

        int left = 0;
        int right = 0;
        int open = 0;
        StringBuffer sb = new StringBuffer();

        for (char ch: A) {
            if (ch == '(') {
                ++left;
            } else if (ch == ')') {
                ++right;
            }
        }

        rmParenthesisDFS(A, left, right, 0, 0, result, sb);
        //System.out.println("DFS result: " + result);
        System.out.print("DFS result: ");

        int maxLen = Collections.max(result).length();

        for (String s: result) {
            if (s.length() == maxLen) {
                System.out.print(s + " ");
            }
        }
        System.out.println();

        //System.out.println(Collections.max(result));

    }

    private void rmParenthesisDFS(char [] A, int left, int right, int index, int open, Set<String> result,
                                  StringBuffer sb) {
        if (index == A.length && left == right && open == 0) {
            result.add(sb.toString());
            return;
        }

        if (index >= A.length || left < 0 || right < 0 || open < 0) {
            return;
        }

        char ch = A[index];

        if (ch == '(') {
            //exclude (
            rmParenthesisDFS(A, --left, right, index+1, open, result, sb);
            //include (
            rmParenthesisDFS(A, left, right, index+1, open+1, result, sb.append(ch));
            sb.deleteCharAt(sb.length()-1);
        } else if (ch == ')') {
            //exclude (
            rmParenthesisDFS(A, left, --right, index+1, open, result, sb);
            //include (
            rmParenthesisDFS(A, left, right, index+1, open-1, result, sb.append(ch));
            sb.deleteCharAt(sb.length()-1);
        } else {
            //non-bracket characters
            rmParenthesisDFS(A, left, right, index+1, open, result, sb.append(ch));
            sb.deleteCharAt(sb.length()-1);
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
