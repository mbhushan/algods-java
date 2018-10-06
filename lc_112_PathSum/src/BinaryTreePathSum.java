/**
 112. Path Sum
 https://leetcode.com/problems/path-sum/description/

 Given a binary tree and a sum, determine if the tree has a root-to-leaf path such
 that adding up all the values along the path equals the given sum.

 Note: A leaf is a node with no children.

 Example:

 Given the below binary tree and sum = 22,

 5
 / \
 4   8
 /   / \
 11  13  4
 /  \      \
 7    2      1
 return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.


 */
public class BinaryTreePathSum {

    public static void main(String[] args) {

    }

    public boolean hasPathSum(TreeNode root, int sum) {
        return helper(root, sum);
    }

    private boolean helper(TreeNode node, int sum) {
        if (node == null) {
            return false;
        }

        if (sum == node.val && node.left == null && node.right == null) {
            return true;
        }

        return helper(node.left, sum-node.val) || helper(node.right, sum - node.val);
    }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
