import java.util.ArrayList;
import java.util.List;

/**
 129. Sum Root to Leaf Numbers
 https://leetcode.com/problems/sum-root-to-leaf-numbers/description/
 Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

 An example is the root-to-leaf path 1->2->3 which represents the number 123.

 Find the total sum of all root-to-leaf numbers.

 Note: A leaf is a node with no children.

 Example:

 Input: [1,2,3]
 1
 / \
 2   3
 Output: 25
 Explanation:
 The root-to-leaf path 1->2 represents the number 12.
 The root-to-leaf path 1->3 represents the number 13.
 Therefore, sum = 12 + 13 = 25.
 Example 2:

 Input: [4,9,0,5,1]
 4
 / \
 9   0
 / \
 5   1
 Output: 1026
 Explanation:
 The root-to-leaf path 4->9->5 represents the number 495.
 The root-to-leaf path 4->9->1 represents the number 491.
 The root-to-leaf path 4->0 represents the number 40.
 Therefore, sum = 495 + 491 + 40 = 1026.

 */
public class RootToLeafNumbers {

    int [] res = new int[1];
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        helper(root, new ArrayList<>());

        return res[0];
    }

    private void helper(TreeNode node, List<Integer> buff) {

        buff.add(node.val);

        if (node.left == null && node.right == null) {
            //System.out.println(buff);
            int sum = 0;
            for (int i: buff) {
                sum = sum*10 + i;
            }
            res[0] += sum;
        }

        if (node.left != null) {
            helper(node.left, buff);
        }
        if (node.right != null) {
            helper(node.right, buff);
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
