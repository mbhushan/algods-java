import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 LeetCode – Find Leaves of Binary Tree (Java)

 Given a binary tree, collect a tree's nodes as if you were doing this:
 Collect and remove all leaves, repeat until the tree is empty.

 Example:
 Given binary tree
     1
    / \
   2   3
  / \
 4   5
 Returns [4, 5, 3], [2], [1].

                   8
               /       \
              3        10
            /  \       \
           1    6      14
               /  \   /
              4   7  13

 ================
 INPUT / OUTPUT
 ================
 inorder traversal itertive:
 1 3 4 6 7 8 10 13 14
 leaves collection:
 1 4 7 13
 6 14
 3 10
 8

 */

public class BTCollectLeaves {

    private Node root;

    BTCollectLeaves() {
        this.root = null;
    }

    public static void main(String[] args) {

        BTCollectLeaves bt = new BTCollectLeaves();
        // https://en.wikipedia.org/wiki/Binary_search_tree#/media/File:Binary_search_tree.svg
        int [] A = {8, 3, 10, 1, 6, 14, 4, 7, 13};

        bt.buildBST(A);
        bt.inorder();
        System.out.println();

        bt.collectLeaves();
    }

    public void collectLeaves() {
        List<Node> leaves = new ArrayList<>();

        System.out.println("leaves collection: ");
        while (true) {
            collectLeaves(this.root, this.root, leaves);

            for (Node node : leaves) {
                System.out.print(node.data + " ");
            }

            if (leaves.get(0).equals(this.root)) {
                break;
            }
            leaves = new ArrayList<>();
            System.out.println();
        }
    }

    private void collectLeaves(Node node, Node prev, List<Node> leaves) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            leaves.add(node);
            if (prev.left != null && prev.left.equals(node)) {
                prev.left = null;
            } else if (prev.right != null && prev.right.equals(node)) {
                prev.right = null;
            }
            return;
        }

        prev = node;

        if (node.left != null) {
            collectLeaves(node.left, prev, leaves);
        }

        if (node.right != null) {
            collectLeaves(node.right, prev, leaves);
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
