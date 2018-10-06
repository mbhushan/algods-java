import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 102. Binary Tree Level Order Traversal
 https://leetcode.com/problems/binary-tree-level-order-traversal/description/

 Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

 For example:
 Given binary tree [3,9,20,null,null,15,7],
 3
 / \
 9  20
 /  \
 15   7
 return its level order traversal as:
 [
 [3],
 [9,20],
 [15,7]
 ]

 */
public class BTLevelOrder {

    public static void main(String[] args) {

    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result  = new ArrayList<>();
        if (root == null) {
            return result;
        }
        TreeNode node = root;
        TreeNode marker = new TreeNode(0);
        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(node);
        queue.add(marker);
        List<Integer> buff = new ArrayList<>();
        while (!queue.isEmpty()) {
            node = queue.remove();

            if (node.equals(marker)) {
                result.add(buff);
                buff = new ArrayList<>();
                if (!queue.isEmpty()) {
                    queue.add(marker);
                }
            } else {
                buff.add(node.val);
            }
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }

        return result;

    }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
