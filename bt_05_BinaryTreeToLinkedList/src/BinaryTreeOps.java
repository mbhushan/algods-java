import java.util.Stack;

/**
 Given a binary tree, flatten it to a linked list.
 For example,
 Given

     1
    / \
   2   5
  / \   \
 3   4   6

 The flattened tree should look like:

 1
 \
 2
 \
 3
 \
 4
 \
 5
 \
 6

 ================
 INPUT / OUTPUT
 ===============
 inorder traversal itertive:
 1 3 4 6 7 8 10 13 14
 flattened binary tree:
 8 3 1 6 4 7 10 14 13
 flattening of binary tree done!
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

        btt.flattenBT();
        System.out.println("flattening of binary tree done!");

    }

    public void flattenBT() {
        Node node = this.root;
        flattenBTStack(this.root);

        System.out.println("flattened binary tree: ");
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.right;
        }
        System.out.println();

    }

    private void flattenBTStack(Node node) {
        Stack<Node> stack = new Stack<>();

        while (node != null || !stack.isEmpty()) {
            if (node != null && node.right != null) {
                stack.push(node.right);
            }
            if (node != null && node.left != null) {
                node.right = node.left;
                node.left = null;
            } else if (!stack.isEmpty()) {
                Node temp = stack.pop();
                node.right = temp;
            }
            node = node.right;
        }
    }

    private void flattenBT(Node node) {
        if (node == null) {
            return;
        }

        flattenBT(node.left);
        flattenBT(node.right);
        if (node.left == null && node.right == null) {
            return;
        } else if (node.left != null && node.right == null) {
            node.left.right = node.left;
            node.left.left = node;
        } else if (node.right != null &&  node.left == null) {
            node.right.left = node;
        } else {
            node.left.right = node.right;
            node.right.left = node.left;
            node.right = node.left;
            node.left.left = node;
        }
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
