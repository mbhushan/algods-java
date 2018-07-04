import java.util.Arrays;
import java.util.Stack;

/**
 LeetCode – Verify Preorder Serialization of a Binary Tree (Java)

 One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node,
 we record the node's value. If it is a null node, we record using a sentinel value such as #.

       9
     /   \
    3     2
   / \   / \
  4   1  #  6
 / \ / \   / \
 # # # #   # #
 For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#",
 where # represents a null node.

 =================
 INPUT / OUTPUT
 =================
 preorder string: [9, 3, 4, #, #, 1, #, #, 2, #, 6, #, #]
 valid preorder: true

 preorder string: [9, 3, 4, #, #, 1, #, #, 2, #, 6, 7, #]
 valid preorder: false
 */

public class PreorderSerialization {

    public static void main(String[] args) {
        PreorderSerialization ps = new PreorderSerialization();

        String [] S = { "9,3,4,#,#,1,#,#,2,#,6,#,#",  "9,3,4,#,#,1,#,#,2,#,6,7,#"};

        for (int i=0; i<S.length; i++) {
            //System.out.println("preorder string: " + S[i]);
            System.out.println("valid preorder: " + ps.checkSerialization(S[i]));
            System.out.println();
        }
    }

    public boolean checkSerialization(String str) {
        if (str == null) {
            return false;
        }

        String [] sArr = str.split(",");
        char [] S = new char[sArr.length];
        for (int i=0; i<sArr.length; i++) {
            S[i] = sArr[i].charAt(0);
        }

        System.out.println("preorder string: " + Arrays.toString(S));

        Stack<Character> stack = new Stack<>();
        int len = S.length;
        int index = 0;
        while (index < len) {
            char ch = S[index];
            if (ch == '#') {
                //check if stack top is '#' too, if so shrink.
                while (!stack.isEmpty() && stack.peek() == ch) {
                    stack.pop();
                    if (stack.peek() != '#') {
                        stack.pop();
                    } else {
                        return false;
                    }
                }
            }
            if (index == len-1 && stack.isEmpty()) {
                return true;
            }
            stack.push(ch);
            ++index;
        }

        return stack.isEmpty();
    }
}
