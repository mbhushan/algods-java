import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 LeetCode – Path Sum

 Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the
 values along the path equals the given sum.

 For example:
 Given the below binary tree and sum = 22,


        5
       / \
      4   8
    /   / \
   11  13  4
  /  \      \
 7    2      1
 return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.

 =================
 INPUT / OUTPUT
 =================
 inorder traversal itertive:
 1 3 4 6 7 8 10 13 14
 path sum of 24: true
 path sum of 37: false
 path sum of 45: true
 path sum of 12: true
 path sum of 15: false

 path sum: iterative solution
 path sum of 24: true
 path sum of 37: false
 path sum of 45: true
 path sum of 12: true
 path sum of 15: false

 */

public class BinaryTreePathSum {

    private Node root;

    BinaryTreePathSum() {
        this.root = null;
    }

    public static void main(String[] args) {
        BinaryTreePathSum btt = new BinaryTreePathSum();
        // https://en.wikipedia.org/wiki/Binary_search_tree#/media/File:Binary_search_tree.svg
        int[] A = {8, 3, 10, 1, 6, 14, 4, 7, 13};
        btt.buildBST(A);

        int [] sum = {24, 37, 45, 12, 15};


        System.out.println("inorder traversal itertive: ");
        btt.inorder();
        System.out.println();

        btt.pathSum(sum);

        System.out.println();
        System.out.println("path sum: iterative solution");
        btt.pathSumIterative(sum);

    }

    public void pathSumIterative(int [] sum) {
        for (int i=0; i<sum.length; i++) {
            boolean ans =pathSumIterative(this.root, sum[i]);
            System.out.println("path sum of " + sum[i] + ": " + ans);
        }

    }

    private boolean pathSumIterative(Node node, int sum) {

        Queue<Node> queue = new LinkedList<>();
        Queue<Integer> values = new LinkedList<>();
        queue.add(node);
        values.add(node.data);

        while (!queue.isEmpty()) {
            node = queue.remove();
            int sumVal = values.remove();

            if (node.left == null && node.right == null && sumVal == sum) {
                return true;
            }

            if (node.left != null) {
                queue.add(node.left);
                values.add(node.left.data + sumVal);
            }

            if (node.right != null) {
                queue.add(node.right);
                values.add(node.right.data + sumVal);
            }
        }

        return false;
    }

    public void pathSum(int [] sum) {
        for (int i=0; i<sum.length; i++) {
            boolean ans = pathSum(this.root, sum[i]);
            System.out.println("path sum of " + sum[i] + ": " + ans);
        }
    }

    private boolean pathSum(Node node, int sum) {
        if (node == null) {
            return false;
        }

        //if leaf node, check if we reached the total sum.
        if (node.left == null && node.right == null && node.data == sum) {
            return true;
        }

        return pathSum(node.left, sum - node.data) || pathSum(node.right, sum - node.data);


    }

    public void inorder() {
        inorderIterative(this.root);
    }

    private void inorderIterative(Node node) {
        Stack<Node> stack = new Stack<>();

        while (node != null || !stack.isEmpty()) {
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

    public void buildBST(int [] A) {
        if (A == null) {
            return;
        }

        for (int i=0; i<A.length; i++) {
            this.root = insertIntoBST(this.root, A[i]);
        }
    }

    public Node insertIntoBST(Node node, int data) {
        if (node == null) {
            return new Node(data);
        }
        if (data <= node.data) {
            node.left = insertIntoBST(node.left, data);
        } else {
            node.right = insertIntoBST(node.right, data);
        }
        return node;
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
