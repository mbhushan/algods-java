import java.util.ArrayList;
import java.util.List;

/**
 113. Path Sum II
 https://leetcode.com/problems/path-sum-ii/description/
 Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

 Note: A leaf is a node with no children.

 Example:

 Given the below binary tree and sum = 22,

 5
 / \
 4   8
 /   / \
 11  13  4
 /  \    / \
 7    2  5   1
 Return:

 [
 [5,4,11,2],
 [5,8,4,5]
 ]

 */
public class BinaryTreePathSum2 {

    public static void main(String[] args) {

    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }
        List<Integer> buff = new ArrayList<>();

        pathSumHelper(root, sum, buff, result);

        return result;

    }

    private void pathSumHelper(TreeNode node, int sum, List<Integer> buff,  List<List<Integer>> result) {

        buff.add(node.val);

        if (sum == node.val && node.left == null && node.right == null) {
            result.add(new ArrayList<>(buff));
        }

        if (node.left != null) {
            pathSumHelper(node.left, sum - node.val, buff, result);
        }

        if (node.right != null) {
            pathSumHelper(node.right, sum - node.val, buff, result);
        }

        buff.remove(buff.size()-1);

    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
