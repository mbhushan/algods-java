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

 result is: 43
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
    }

    public void findMaxPathSum() {
        int maxSum = findMaxPathSum(this.root, new int[1]);
        System.out.println("max path sum: " + maxSum);
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
        this.root.left = new Node(4);
        this.root.right = new Node(8);

        this.root.left.left = new Node(11);
        this.root.right.left = new Node(13);
        this.root.right.right = new Node(4);

        this.root.left.left.left = new Node(7);
        this.root.left.left.right = new Node(2);

        this.root.right.right.left = new Node(5);
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
