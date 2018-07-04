import java.util.Stack;

/**
 LeetCode – Binary Search Tree Iterator (Java)

 Problem

 Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 Calling next() will return the next smallest number in the BST. Note: next() and hasNext() should run in average O(1)
 time and uses O(h) memory, where h is the height of the tree.

 */

public class BSTIterator {

    private Node root;

    BSTIterator() {
        this.root = null;
    }

    public static void main(String[] args) {
        BSTIterator bst = new BSTIterator();

        int [] A = {8, 3, 10, 1, 6, 14, 4, 7, 13};

        bst.buildBST(A);

        bst.iterateBST();
    }

    public void iterateBST() {
        BTIterator iter = new BTIterator(this.root);
        System.out.println("BST iterator example: ");
        while (iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
        System.out.println();

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

class BTIterator {

    private Stack<Node> stack;

    BTIterator(Node node) {
        stack = new Stack<>();
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public int next() {
        Node node = stack.pop();
        int result = node.data;
        node = node.right;
        while (node != null) {
            stack.push(node);
            node = node.left;
        }

        return result;
    }
}

class Node {
    Integer data;
    Node left ;
    Node right;

    Node (Integer data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
