/**
 124. Binary Tree Maximum Path Sum
 https://leetcode.com/problems/binary-tree-maximum-path-sum/description/

 Given a non-empty binary tree, find the maximum path sum.

 For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

 Example 1:

 Input: [1,2,3]

 1
 / \
 2   3

 Output: 6
 Example 2:

 Input: [-10,9,20,null,null,15,7]

 -10
 / \
 9  20
 /  \
 15   7

 Output: 42

 ==================
 input / output
 ==================
 max sum path: 42
 */

public class BinaryTreeMaxPathSum {

    private TreeNode root;

    public static void main(String[] args) {
        BinaryTreeMaxPathSum bt = new BinaryTreeMaxPathSum();

        bt.buildBT1();

        bt.maxPathSum();

    }

    public int maxPathSum() {
        int [] maxSum = new int[1];
        maxSum[0] = 0;
        findMaxPathSumLeafToLeaf(this.root, maxSum);
        System.out.println("max sum path: " + maxSum[0]);

        return maxSum[0];

    }

    private int findMaxPathSumLeafToLeaf(TreeNode node, int [] max) {
        if (node == null) {
            return 0;
        }

        int left = findMaxPathSumLeafToLeaf(node.left, max);
        int right = findMaxPathSumLeafToLeaf(node.right, max);

        max[0] = Math.max(node.val + left + right, max[0]);
        return Math.max(left, right) + node.val;
    }

    public int maxPathSum(TreeNode node, int [] maxSum) {
        if (node == null) {
            return 0;
        }

        int left = maxPathSum(node.left, maxSum);
        int right = maxPathSum(node.right, maxSum);

        if (node.left != null && node.right != null) {
            maxSum[0] = Math.max(maxSum[0], left + right + node.val);

            return Math.max(left, right) + node.val;
        } else if (node.left != null || node.right != null) {
            return node.left == null ? right + node.val : left + node.val;
        } else {
            return node.val;
        }
    }

    public void buildBT1() {
        this.root = new TreeNode(-10);

        this.root.left = new TreeNode(9);
        this.root.right = new TreeNode(20);

        this.root.right.left = new TreeNode(15);
        this.root.right.right = new TreeNode(7);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
        val = x;
        this.left = null;
        this.right = null;
    }
}
