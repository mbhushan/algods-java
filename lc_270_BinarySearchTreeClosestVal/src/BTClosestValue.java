
/**
 270. Closest Binary Search Tree Value
 https://leetcode.com/problems/closest-binary-search-tree-value/description/

 Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

 Note:

 Given target value is a floating point.
 You are guaranteed to have only one unique value in the BST that is closest to the target.
 Example:

 Input: root = [4,2,5,1,3], target = 3.714286

 4
 / \
 2   5
 / \
 1   3

 Output: 4

 */
public class BTClosestValue {

    public static void main(String[] args) {
        BTClosestValue bt = new BTClosestValue();

    }

    public int closestValue(TreeNode root, double target) {
        TreeNode curr = root;
        int res = root.val;

        while (curr != null) {
            if (Math.abs(target - curr.val) < Math.abs(target - res)) {
                res = curr.val;
            }
            curr = (target < curr.val) ? curr.left : curr.right;
        }

        return res;

    }
}

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
