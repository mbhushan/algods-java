/**
 250. Count Univalue Subtrees
 https://leetcode.com/problems/count-univalue-subtrees/description/

 */
public class CountUnivalueSubtrees {

    int count;
    public int countUnivalSubtrees(TreeNode root) {
        count = 0;
        helper(root);
        return count;
    }

    private boolean helper(TreeNode node) {
        if (node == null) {
            return true;
        }

        boolean left = helper(node.left);
        boolean right = helper(node.right);

        if (left && right) {
            if (node.left != null && node.val != node.left.val) {
                return false;
            }
            if (node.right != null && node.val != node.right.val) {
                return false;
            }
            ++count;
            return true;
        }

        return false;
    }
}

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int v) {
        val = v;
        left = right = null;
    }
}
