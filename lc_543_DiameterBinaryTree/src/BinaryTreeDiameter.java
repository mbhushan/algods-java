/**
 543. Diameter of Binary Tree
 https://leetcode.com/problems/diameter-of-binary-tree/description/

 Given a binary tree, you need to compute the length of the diameter of the tree.
 The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
 This path may or may not pass through the root.

 Example:
 Given a binary tree
 1
 / \
 2   3
 / \
 4   5
 Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

 Note: The length of path between two nodes is represented by the number of edges between them.

 ==============
 input / output
 ===============
 diameter: 7
 */

public class BinaryTreeDiameter {

    private TreeNode root;

    public static void main(String[] args) {
        BinaryTreeDiameter bt = new BinaryTreeDiameter();
        int [] A = {8, 3, 10, 1, 6, 14, 4, 7, 13, 11};

        bt.buildBST(A);

        bt.findDiamter();


    }

    public void findDiamter() {
        int x = diameterOfBinaryTree(this.root);
        System.out.println("diameter: " + x);
    }

    public int diameterOfBinaryTree(TreeNode root) {
        int [] max = new int[1];
        max[0] = Integer.MIN_VALUE;

        diameterOfBinaryTree(root, max);

        return max[0];
    }

    private int diameterOfBinaryTree(TreeNode node, int [] max) {
        if (node == null) {
            return 0;
        }

        int left = diameterOfBinaryTree(node.left, max);
        int right = diameterOfBinaryTree(node.right, max);

        max[0] = Math.max(max[0], left+right);

        return Math.max(left, right) + 1;
    }

    public void buildBST(int [] A) {
        if (A == null) {
            return;
        }

        for (int i=0; i<A.length; i++) {
            this.root = insertIntoBST(this.root, A[i]);
        }
    }

    public TreeNode insertIntoBST(TreeNode node, int data) {
        if (node == null) {
            return new TreeNode(data);
        }
        if (data <= node.val) {
            node.left = insertIntoBST(node.left, data);
        } else {
            node.right = insertIntoBST(node.right, data);
        }
        return node;
    }

}

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
}
