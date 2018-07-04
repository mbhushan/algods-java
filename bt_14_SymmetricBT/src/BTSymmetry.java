/**
 LeetCode – Symmetric Tree (Java)

 Problem
 Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

 Example:
        1
     /    \
    2     2
   / \   / \
  3  3  3  3
 */

public class BTSymmetry {

    private Node root;

    BTSymmetry() {
        this.root = null;
    }

    public static void main(String[] args) {
        BTSymmetry bt = new BTSymmetry();

        bt.buildBT();
        bt.isSymmetric();
    }

    public void isSymmetric() {
        boolean ans = isSymmetric(this.root);

        System.out.println("is symmetric: " + ans);
    }

    public boolean isSymmetric(Node node) {
        if (node == null) {
            return true;
        }

        return isSymmetric(node.left, node.right);
    }

    private boolean isSymmetric(Node left, Node right) {
        if (left == null && right == null) {
            return true;
        }

        if (left == null || right == null) {
            return false;
        }

        if (left.data != right.data) {
            return false;
        }

        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }

    public void buildBT() {
        this.root = new Node(1);
        this.root.left = new Node(2);
        this.root.right = new Node(2);

        this.root.left.left = new Node(3);
        this.root.left.right = new Node(4);

        this.root.right.left = new Node(4);
        this.root.right.right = new Node(3);
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
