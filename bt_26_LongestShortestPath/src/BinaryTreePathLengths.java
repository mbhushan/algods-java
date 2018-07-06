import java.util.Stack;

/**
 LeetCode – Maximum Depth of Binary Tree (Java)

 Given a binary tree, find its maximum depth.

 The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

 Example binary tree

              8
          /       \
         3        10
       /  \       \
      1    6      14
         /  \   /
        4   7  13
              /
             11

 =====================
 INPUT / OUTPUT
 ====================
 inorder traversal itertive:
 1 3 4 6 7 8 10 11 13 14
 min depth: 3
 max depth: 5

 */
public class BinaryTreePathLengths {

    private Node root;

    BinaryTreePathLengths() {
        this.root = null;
    }

    public static void main(String[] args) {
        BinaryTreePathLengths bt = new BinaryTreePathLengths();
        // https://en.wikipedia.org/wiki/Binary_search_tree#/media/File:Binary_search_tree.svg
        //int [] A = {8, 3, 10, 1, 6, 14, 2, 4, 7, 13};
        int [] A = {8, 3, 10, 1, 6, 14, 4, 7, 13, 11};

        bt.buildBST(A);
        bt.inorder();
        System.out.println();
        bt.minDepth();
        bt.maxDepth();
    }

    public void maxDepth() {
        int depth = maxDepth(this.root);
        System.out.println("max depth: " + depth);
    }

    private int maxDepth(Node node) {
        if (node == null) {
            return 0;
        }

        if (node.left == null && node.right == null) {
            return 1;
        }

        if (node.left == null) {
            return minDepth(node.right)+1;
        }

        if (node.right == null) {
            return minDepth(node.left)+1;
        }


        return Math.max(maxDepth(node.left), maxDepth(node.right))+1 ;
    }

    public void minDepth() {
        int depth = minDepth(this.root);
        System.out.println("min depth: " + depth);
    }

    private int minDepth(Node node) {
        if (node == null) {
            return 0;
        }

        if (node.left == null && node.right == null) {
            return 1;
        }

        if (node.left == null) {
            return minDepth(node.right)+1;
        }

        if (node.right == null) {
            return minDepth(node.left)+1;
        }


        return Math.min(minDepth(node.left), minDepth(node.right))+1 ;
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
