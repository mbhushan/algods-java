/**
 Created by manib on 5/20/18.

            8
        3      10
    1     6       14
         4   7  13
 */

public class BinarySearchTree {

    private Node root;

    public static void main(String[] args) {

        BinarySearchTree bst = new BinarySearchTree();
        int [] A = {8, 3, 10, 1, 6, 14, 4, 7, 13 };
        bst.buildBST(A);
        bst.isBST1();
        bst.isBST2();
    }

    public void isBST2() {
        boolean ans = isBST2(this.root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.println("is bst2: " + ans);
    }

    private boolean isBST2(Node node, int min, int max) {
        if (node == null) {
            return true;
        }
        //check if left is ok.
        boolean leftOk = isBST2(node.left, min, node.data);
        if (!leftOk) return false;

        boolean rightOk = isBST2(node.right, node.data, max);
        return rightOk;
    }

    public void isBST1() {
        System.out.println("is BST1: " + this.isBST1(this.root));
    }

    private boolean isBST1(Node node) {
        if (node == null) {
            return true;
        }

        if (node.left != null && (node.data < this.maxValue(node.left))) {
            return false;
        }
        if (node.right != null && (node.data >= this.minValue(node.right))) {
            return false;
        }

        return (isBST1(node.left) && isBST1(node.right));
    }

    private Integer minValue(Node node) {
        if (node == null) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node.data;
    }

    private Integer maxValue(Node node) {
        if (node == null) {
            return null;
        }

        while (node.right != null) {
            node = node.right;
        }

        return node.data;
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
    int data;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
