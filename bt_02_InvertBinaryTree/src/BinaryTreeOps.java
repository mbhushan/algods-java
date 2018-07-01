import java.util.Stack;

/**
 https://leetcode.com/problems/invert-binary-tree/description/
 Invert a binary tree.

 Example:

 Input:

       4
    /    \
   2     7
  / \   / \
 1   3 6   9


 Output:

      4
    /   \
   7     2
  / \   / \
 9   6 3   1

 inorder traversal itertive:
 1 3 4 6 7 8 10 13 14
 5 smallest element: 7
 inverting binary tree:
 inorder after inverting:
 14 13 10 8 7 6 4 3 1
 */

public class BinaryTreeOps {

    private Node root;

    BinaryTreeOps() {
        this.root = null;
    }

    public static void main(String [] args) {
        BinaryTreeOps btt = new BinaryTreeOps();

        // https://en.wikipedia.org/wiki/Binary_search_tree#/media/File:Binary_search_tree.svg
        int[] A = {8, 3, 10, 1, 6, 14, 4, 7, 13};
        btt.buildBST(A);


        System.out.println("inorder traversal itertive: ");
        btt.inorder();
        System.out.println();

        int k = 5;
        btt.kSmallest(k);

        System.out.println("inverting binary tree: ");
        btt.invert();

        System.out.println("inorder after inverting: ");
        btt.inorder();


    }

    /**
     * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
     * (1 ≤ k ≤ BST's total elements)
     * @param k
     */
    public void kSmallest(int k) {

        int ans = kSmallest(this.root, k);
        System.out.println(k + " smallest element: " + ans);
    }

    private Integer kSmallest(Node node, int k) {
        Stack<Node> stack = new Stack<>();

        while (node != null || !stack.isEmpty()) {
            if (node == null) {
                node = stack.pop();
                --k;
                if (k == 0) {
                    return node.data;
                }
                node = node.right;
            }

            if (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        return null;

    }

    public void invert() {
        invert(this.root);
    }

    private void invert(Node node) {
        if (node == null) {
            return;
        }

        if (node.left != null) {
            invert(node.left);
        }
        if (node.right != null) {
            invert(node.right);
        }

        Node temp = node.left;
        node.left = node.right;
        node.right = temp;
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
