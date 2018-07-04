import java.util.Stack;

/**
 LeetCode – Lowest Common Ancestor of a Binary Search Tree (Java)

 Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.

 ===============
 INPUT / OUTPUT
 ==============
 inorder traversal:
 1 3 4 6 7 8 10 13 14
 finding LCA of 1 and 7
 LCA: 3

 */
public class LCA {

    private Node root;

    LCA() {
        this.root = null;
    }

    public static void main(String[] args) {
        LCA bst = new LCA();

        // https://en.wikipedia.org/wiki/Binary_search_tree#/media/File:Binary_search_tree.svg
        int [] A = {8, 3, 10, 1, 6, 14, 4, 7, 13};

        bst.buildBST(A);
        System.out.println();
        System.out.println("inorder traversal: ");
        bst.inorder();
        System.out.println();

        int first = 1;
        int second = 7;

        System.out.println("finding LCA of " + first + " and " + second);
        bst.findLCA(first, second);
    }

    //assumes first and second exist in BST
    public void findLCA(int first, int second) {
        Node node = findLCA(this.root, first, second);
        if (node != null) {
            System.out.println("LCA: " + node.data);
        } else {
            System.out.println("LCA is null");
        }
    }


    private Node findLCA(Node node, int first, int second) {
        //check if first and second are on either side, if so root is LCA
        if (node.data > first && node.data < second) {
            return node;
        }

        //traverse left if both nodes are on left
        if (node.data > first && node.data > second) {
            return findLCA(node.left, first, second);
        }

        //traverse right if both nodes are on right
        if (node.data < first && node.data < second) {
            return findLCA(node.right, first, second);
        }

        return node;
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
    Node left ;
    Node right;

    Node (Integer data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

