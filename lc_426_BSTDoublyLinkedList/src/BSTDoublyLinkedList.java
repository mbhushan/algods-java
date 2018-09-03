/**
 426. Convert Binary Search Tree to Sorted Doubly Linked List
 https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/description/

 Convert a BST to a sorted circular doubly-linked list in-place. Think of the left and right pointers as
 synonymous to the previous and next pointers in a doubly-linked list.

 ==============
 input / output
 ==============

 inorder traversal:
 1 3 4 6 7 8 9 10 13 14
 printing the DLL:
 1 -> 3 -> 4 -> 6 -> 7 -> 8 -> 9 -> 10 -> 13 -> 14 -> null
 */

public class BSTDoublyLinkedList {

    private Node root;

    public static void main(String[] args) {
        BSTDoublyLinkedList bst = new BSTDoublyLinkedList();

        int [] A = {8, 3, 10, 1, 6, 9, 14,  4, 7, 13};

        bst.buildBST(A);

        bst.inorder();

        bst.treeToDoublyList();

    }

    public void treeToDoublyList() {
        Node node = treeToDoublyList(this.root);

        //lets go begining of the DLL
        while (node.left != null) {
            node = node.left;
        }

        System.out.println("printing the DLL: ");
        //lets print the DLL
        while (node != null) {
            System.out.print(node.val + " -> ");
            node = node.right;
        }
        System.out.println("null");
    }


    public Node treeToDoublyList(Node node) {

        if (node == null) {
            return null;
        }

        if (node.left != null) {
            Node left = treeToDoublyList(node.left);

            //find the predecessor and connect the node.
            while (left.right != null) {
                left = left.right;
            }

            left.right = node;
            node.left = left;
        }

        if (node.right != null) {
            Node right = treeToDoublyList(node.right);

            //find the successor and connect the node.
            while (right.left != null) {
                right = right.left;
            }

            right.left = node;
            node.right = right;
        }

        return node;

    }

    public void inorder() {

        System.out.println("inorder traversal: ");
        inorder(this.root);
        System.out.println();

    }

    private void inorder(Node node) {
        if (node == null) {
            return;
        }

        if (node.left != null) {
            inorder(node.left);
        }

        System.out.print(node.val + " ");

        if (node.right != null) {
            inorder(node.right);
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
        if (data <= node.val) {
            node.left = insertIntoBST(node.left, data);
        } else {
            node.right = insertIntoBST(node.right, data);
        }
        return node;
    }
}

class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node (int data) {
        this.val = data;
        left = null;
        right = null;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
