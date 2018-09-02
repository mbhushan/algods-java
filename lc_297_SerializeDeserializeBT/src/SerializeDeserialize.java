import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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

 Example binary tree

             8
         /       \
        3        10
       / \      /  \
      1   6     9   14
         /  \      /
        4   7     13
 ==============
 INPUT / OUTPUT
 ==============
 serialized binary tree:
 8,3,1,#,#,6,4,#,#,7,#,#,10,9,#,#,14,13,#,#,#

 post deserialization - inorder traversal:
 1 3 4 6 7 8 9 10 13 14

 */

public class SerializeDeserialize {

    private TreeNode root;

    public static void main(String[] args) {
        SerializeDeserialize sd = new SerializeDeserialize();

        int [] A = {8, 3, 10, 1, 6, 9, 14,  4, 7, 13};

        sd.buildBST(A);
        String data = sd.serialize();
        sd.deserializeTree(data);



    }

    public String serialize() {
        String tree = serialize(this.root);
        System.out.println("serialized binary tree: ");
        System.out.println(tree);

        return tree;
    }

    public void deserializeTree(String data) {
        TreeNode node = deserialize(data);

        System.out.println();
        System.out.println("post deserialization - inorder traversal: ");
        inorder(node);
    }

    public void builtBT() {
        this.root = new TreeNode(1);
        this.root.left = new TreeNode(2);

        this.root.right = new TreeNode(3);
        this.root.right.left = new TreeNode(4);
        this.root.right.right = new TreeNode(5);
    }

    public String serialize(TreeNode root) {

        if (root == null) {
            return null;
        }

        List<String> result = new ArrayList<>();

        serializePreorder(root, result);

        //System.out.println(result);

        return String.join(",", result);
    }

    private void serializePreorder(TreeNode node,  List<String> result) {
        if (node == null) {
            return;
        }

        result.add(String.valueOf(node.val));

        if (node.left != null) {
            serializePreorder(node.left, result);
        } else {
            result.add("#");
        }


        if (node.right != null) {
            serializePreorder(node.right, result);
        } else {
            result.add("#");
        }

    }

    // Encodes a tree to a single string.
    public String serializeOld(TreeNode root) {

        //DOES NOT serialize in proper preorder way.

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
                    result.add("#");
                }
                node = node.left;
            }

            if (node == null) {
                result.add("#");
                if (!stack.isEmpty()) {
                    node = stack.pop();
                }
            }
        }
        System.out.println();
        System.out.println("preorder serialization: ");
        System.out.println(result);

        return String.join(",", result);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {

        if (data == null || data.length() < 1) {
            return null;
        }

        Queue<String> queue = new LinkedList<>();

        List<String> list = Arrays.asList(data.split(","));
        ((LinkedList<String>) queue).addAll(list);

        TreeNode node = deserialize(queue);

        return node;
    }

    private TreeNode deserialize(Queue<String> queue) {
        if (queue.isEmpty()) {
            return null;
        }
        String s = queue.poll();

        if (s.equals("#")) {
            return null;
        } else {
            TreeNode node = new TreeNode(Integer.valueOf(s));
            node.left = deserialize(queue);
            node.right = deserialize(queue);

            return node;
        }
    }

    public void inorder(TreeNode node) {

        Stack<TreeNode> stack = new Stack<>();

        while (!stack.isEmpty() || node != null) {

            if (node == null) {
                node = stack.pop();
                System.out.print(node.val + " ");
                node = node.right;
            }

            if (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
    }

    public void buildBST(int [] A) {
        if (A == null) {
            return;
        }

        for (int i=0; i<A.length; i++) {
            this.root = insertIntoBST(this.root, A[i]);
        }
    }

    public TreeNode insertIntoBST(TreeNode node, int data) {
        if (node == null) {
            return new TreeNode(data);
        }
        if (data <= node.val) {
            node.left = insertIntoBST(node.left, data);
        } else {
            node.right = insertIntoBST(node.right, data);
        }
        return node;
    }
}

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
}
