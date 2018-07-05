import java.util.Stack;

/**
 LeetCode – Closest Binary Search Tree Value (Java)

 Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

 Recursively traverse down the root. When target is less than root, go left;
 when target is greater than root, go right.

 Example binary tree

              8
         /       \
        3        10
       /  \       \
      1    6      14
          /  \   /
         4   7  13

 =================
 INPUT / OUTPUT
 =================
 inorder traversal itertive:
 1 3 4 6 7 8 10 13 14
 Target value: 5
 Closest distance: 1
 Closest node value: 6

 Target value: 15
 Closest distance: 1
 Closest node value: 14

 Target value: 12
 Closest distance: 1
 Closest node value: 13

 */

public class BSLClosestValue {

    private Node root;

    BSLClosestValue() {
        this.root = null;
    }

    public static void main(String[] args) {

        BSLClosestValue bt = new BSLClosestValue();
        // https://en.wikipedia.org/wiki/Binary_search_tree#/media/File:Binary_search_tree.svg
        int [] A = {8, 3, 10, 1, 6, 14, 4, 7, 13};

        bt.buildBST(A);
        bt.inorder();
        System.out.println();
        int [] values = {5, 15, 12};

        for (int i=0; i<values.length; i++) {
            System.out.println("Target value: " + values[i]);
            bt.findClosestValue(values[i]);
            System.out.println();
        }
    }

    public void findClosestValue(int value) {
        int [] result = new int[2];
        result[0] = Integer.MAX_VALUE;
        result[0] = this.root.data;

        findClosestValues(this.root, value, result);

        System.out.println("Closest distance: " + result[0]);
        System.out.println("Closest node value: " + result[1]);
    }

    private void findClosestValues(Node node, int value, int [] result) {
        if (node == null) {
            return ;
        }

       if (Math.abs(node.data - value) < result[0]) {
            result[0] = Math.abs(node.data - value);
            result[1] = node.data;
       }

       if (value < node.data ) {
            findClosestValues(node.left, value, result);
       }

       if (value > node.data) {
            findClosestValues(node.right, value, result);
       }
    }

    public void inorder() {
        System.out.println("inorder traversal itertive: ");
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
