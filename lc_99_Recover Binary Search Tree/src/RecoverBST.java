import java.util.Stack;

/**
 99. Recover Binary Search Tree
 https://leetcode.com/problems/recover-binary-search-tree/description/
 Two elements of a binary search tree (BST) are swapped by mistake.

 Recover the tree without changing its structure.

 Example 1:

 Input: [1,3,null,null,2]

 1
 /
 3
 \
 2

 Output: [3,1,null,null,2]

 3
 /
 1
 \
 2
 Example 2:

 Input: [3,1,4,null,null,2]

 3
 / \
 1   4
 /
 2

 Output: [2,1,4,null,null,3]

 2
 / \
 1   4
 /
 3

 */
public class RecoverBST {

    public void recoverTreeOld(TreeNode root) {
        if (root == null) { return; }
        TreeNode pre = null, first = null, second = null;
        Stack<TreeNode> stk = new Stack<>();
        while (!stk.isEmpty() || root != null) {
            while (root != null) {
                stk.push(root);
                root = root.left;
            }
            TreeNode cur = stk.pop();
            if (pre != null && pre.val >= cur.val) {
                if (first == null) {
                    first = pre;
                }
                if (first != null) {
                    second = cur;
                }
            }
            root = cur.right;
            pre = cur;
        }
        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;
        return;
    }

    public void recoverTree(TreeNode root) {
        inorderIterative(root);
    }

    private void inorderIterative(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        //TreeNode oldNode = node;
        TreeNode first = null;
        TreeNode second = null;
        TreeNode prev = null;

        while (node != null || !stack.isEmpty()) {

            if (node != null) {

                stack.push(node);
                node = node.left;
            }

            if (node == null) {
                node = stack.pop();
                //System.out.print(node.data + " ");
                if (prev != null && prev.val >= node.val) {
                    if (first == null) {
                        first = prev;
                        second = node;
                    } else {
                        second = node;
                    }
                }

                prev = node;
                node = node.right;
            }
        }


        if (first != null) {
            int buff = first.val;
            first.val = second.val;
            second.val = buff;
        }
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
