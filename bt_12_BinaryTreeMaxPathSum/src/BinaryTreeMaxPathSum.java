import java.util.Stack;

/**
 LeetCode – Binary Tree Maximum Path Sum (Java)

 Given a binary tree, find the maximum path sum. The path may start and end at any node in the tree.
 For example, given the below binary tree

   1
  / \
 2   3

 the result is 6.

 Example 2:
       5
      / \
     4   8
    /   / \
   11  13  4
  /  \    / \
 7    2  5   1

 result is: 48

 Example 3:
       5
      / \
     10   8
    /   / \
   -21  13  4
  /  \    / \
 7    2  6   1

 result is: 36

 inorder iterative:
 7 -21 2 10 5 13 8 6 4 1
 max path sum: 36
 max path sum leaf node to leaf node: 31

 */


public class BinaryTreeMaxPathSum {

    private Node root ;

    BinaryTreeMaxPathSum() {
        this.root = null;
    }

    public static void main(String [] args) {
        BinaryTreeMaxPathSum bt = new BinaryTreeMaxPathSum();

        bt.buildBT();

        System.out.println("inorder iterative: ");
        bt.inorder();
        System.out.println();

        bt.findMaxPathSum();
        bt.findMaxPathSumLeafToLeaf();
    }

    /**
     Find the maximum path sum between two leaves of a binary tree
     Given a binary tree in which each node element contains a number. Find the maximum possible sum
     from one leaf node to another.
     The maximum sum path may or may not go through root. For example, in the following binary tree,
     the maximum sum is 27(3 + 6 + 9 + 0 – 1 + 10). Expected time complexity is O(n).
     */
    public void findMaxPathSumLeafToLeaf() {
        int [] max = new int[1];
        max[0] = Integer.MIN_VALUE;

        findMaxPathSumLeafToLeaf(this.root, max);
        System.out.println("max path sum leaf node to leaf node: " + max[0]);
    }



    private int findMaxPathSumLeafToLeaf(Node node, int [] max) {
        if (node == null) {
            return 0;
        }

        int left = findMaxPathSumLeafToLeaf(node.left, max);
        int right = findMaxPathSumLeafToLeaf(node.right, max);

        if (node.left != null && node.right != null) {
            max[0] = Math.max(node.data + left + right, max[0]);

            return Math.max(left, right) + node.data;
        }

        return node.left == null ? right + node.data : left + node.data;
    }

    public void findMaxPathSum() {
        int [] max = new int[1];
        max[0] = Integer.MIN_VALUE;
        findMaxPathSum(this.root, max);
        System.out.println("max path sum: " + max[0]);
    }

    private int findMaxPathSum(Node node, int [] max) {
        if (node == null) {
            return 0;
        }

        int left = findMaxPathSum(node.left, max);
        int right = findMaxPathSum(node.right, max);

        int curr = Math.max(node.data, Math.max(node.data + left, node.data + right));

        max[0] = Math.max(max[0], Math.max(curr, node.data + left + right));

        return curr;
    }

    public void inorder() {
        inorder(this.root);
    }

    private void inorder(Node node ){
        Stack<Node> stack = new Stack();

        while(node != null || !stack.isEmpty()) {

            if (node == null) {
                node = stack.pop();
                System.out.print(node.data + " ");
                node = node.right;
            }

            if (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
    }

    public void buildBT() {
        this.root = new Node(5);
        this.root.left = new Node(10);
        this.root.right = new Node(8);

        this.root.left.left = new Node(-21);
        this.root.right.left = new Node(13);
        this.root.right.right = new Node(4);

        this.root.left.left.left = new Node(7);
        this.root.left.left.right = new Node(2);

        this.root.right.right.left = new Node(6);
        this.root.right.right.right = new Node(1);

    }
}

class Node {
    Integer data;
    Node left;
    Node right;

    Node(Integer data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
