import java.util.Stack;

/**


 98. Validate Binary Search Tree

 https://leetcode.com/problems/validate-binary-search-tree/description/

 Given a binary tree, determine if it is a valid binary search tree (BST).

 Assume a BST is defined as follows:

 The left subtree of a node contains only nodes with keys less than the node's key.
 The right subtree of a node contains only nodes with keys greater than the node's key.
 Both the left and right subtrees must also be binary search trees.
 Example 1:

 Input:
 2
 / \
 1   3
 Output: true
 Example 2:

     5
    / \
    1   4
        / \
        3   6
 Output: false
 Explanation: The input is: [5,1,4,null,null,3,6]. The root node's value
 is 5 but its right child's value is 4.
 */
public class BSTValidation {

    private TreeNode root1;
    private TreeNode root2;

    public static void main(String [] args) {
        BSTValidation bst = new BSTValidation();

        System.out.println("integer max: " + Integer.MAX_VALUE);

        bst.buildTree1();
        bst.buildTree2();

        bst.isBST();

    }

    public void isBST() {

        System.out.println("root1: ");
        isValidBST(this.root1);
        System.out.println();

        System.out.println("root2: ");
        isValidBST(this.root2);



    }

    public void isValidBST(TreeNode root) {
        System.out.println("is BST: " + isValidBSTMinMax(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    public boolean isValid(TreeNode root, Integer min, Integer max) {
        if(root == null) return true;
        if(min != null && root.val <= min) return false;
        if(max != null && root.val >= max) return false;

        return isValid(root.left, min, root.val) && isValid(root.right, root.val, max);
    }

    private boolean isValidBSTMinMax(TreeNode node, int min, int max) {
        if (node == null) {
            return true;
        }

        if (node.val < min || node.val > max) {
            return false;
        }

        return isValidBSTMinMax(node.left, min, node.val-1) &&
                isValidBSTMinMax(node.right, node.val+1, max);
    }

    public boolean isBSTInorder(TreeNode node) {
        if (node == null) {
            return true;
        }

        Stack<TreeNode> stack = new Stack<>();
        Integer prev = null;

        while (!stack.isEmpty() || node != null) {

            if (node == null) {
                node = stack.pop();
                if (prev != null && node.val <= prev) {
                    return false;
                }
                prev = node.val;
                node = node.right;
            }

            if (node != null) {
                node = node.left;
            }
        }
        return true;
    }

    public void buildTree1() {

        this.root1 = new TreeNode(5);

        this.root1.left = new TreeNode(10);
        this.root1.right = new TreeNode(4);

        this.root1.right.left = new TreeNode(3);
        this.root1.right.right = new TreeNode(6);

    }

    public void buildTree2() {

        this.root2 = new TreeNode(5);

        this.root2.left = new TreeNode(1);
        this.root2.right = new TreeNode(8);

        this.root2.right.left = new TreeNode(6);
        this.root2.right.right = new TreeNode(9);

    }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}
