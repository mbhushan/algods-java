import java.util.Stack;

/**
 LeetCode – Count Complete Tree Nodes (Java)

 Given a complete binary tree, count the number of nodes.

 Analysis

 Steps to solve this problem:
 1) get the height of left-most part
 2) get the height of right-most part
 3) when they are equal, the # of nodes = 2^h -1
 4) when they are not equal, recursively get # of nodes from left&right sub-trees

 Time complexity is O(h^2).

 ================
 INPUT / OUTPUT
 ================
 inorder traversal itertive:
 1 2 3 4 5 6 7 8 9
 number of nodes: 7

 */

public class CompleteTreeNodes {

    private Node root;

    CompleteTreeNodes() {
        this.root = null;
    }

    public static void main(String[] args) {

        CompleteTreeNodes bt = new CompleteTreeNodes();
        // https://en.wikipedia.org/wiki/Binary_search_tree#/media/File:Binary_search_tree.svg
        int [] A = {6, 2, 7, 1, 4, 9, 3, 5, 8};
        bt.buildBST(A);
        bt.inorder();
        System.out.println();

        bt.findNodes();
    }

    public void findNodes() {
        int numNodes = findNodes(this.root);
        System.out.println("number of nodes: " + numNodes);
    }

    private int findNodes(Node node) {
        if (node == null) {
            return 0;
        }

        int left = getLeftHeight(node);
        int right = getRightHeight(node);

        if (left == right) {
            return (int)(Math.pow(2, left) - 1);
        }

        return findNodes(node.left) + findNodes(node.right);
    }

    private int getRightHeight(Node node ){
        int height = 0;

        while (node != null) {
            node = node.right;
            ++height;
        }

        return height;
    }
    private int getLeftHeight(Node node ){
        int height = 0;

        while (node != null) {
            node = node.left;
            ++height;
        }

        return height;
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
