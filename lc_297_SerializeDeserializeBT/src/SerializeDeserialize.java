import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 297. Serialize and Deserialize Binary Tree
 https://leetcode.com/problems/serialize-and-deserialize-binary-tree/description/

 Serialization is the process of converting a data structure or object into a sequence of bits
 so that it can be stored in a file or memory buffer, or transmitted across a network connection
 link to be reconstructed later in the same or another computer environment.

 Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your
 serialization/deserialization algorithm should work. You just need to ensure that a binary tree can
 be serialized to a string and this string can be deserialized to the original tree structure.

 Example:

 You may serialize the following tree:

      1
     / \
    2   3
       / \
      4   5

 as "[1,2,3,null,null,4,5]"

 ==============
 INPUT / OUTPUT
 ==============

 */

public class SerializeDeserialize {

    private TreeNode root;

    public static void main(String[] args) {
        SerializeDeserialize sd = new SerializeDeserialize();

        sd.builtBT();

        sd.serialize();



    }

    public void serialize() {
        serialize(this.root);
    }

    public void builtBT() {
        this.root = new TreeNode(1);
        this.root.left = new TreeNode(2);

        this.root.right = new TreeNode(3);
        this.root.right.left = new TreeNode(4);
        this.root.right.right = new TreeNode(5);
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        List<String> result = new ArrayList<>();

        //do preorder iterative.
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;

        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                System.out.print(node.val + " ");
                result.add(String.valueOf(node.val));
                if (node.right != null) {
                    stack.push(node.right);
                } else {
                    result.add("null");
                }
                node = node.left;
            }

            if (node == null) {
                result.add("null");
                if (!stack.isEmpty()) {
                    node = stack.pop();
                }
            }
        }
        System.out.println();

        System.out.println(result);

        return null;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        return null;
    }
}

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
}
