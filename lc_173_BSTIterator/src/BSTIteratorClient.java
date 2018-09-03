import java.util.Stack;

/**
 173. Binary Search Tree Iterator
 https://leetcode.com/problems/binary-search-tree-iterator/description/

 Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 Calling next() will return the next smallest number in the BST.
 Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.

 ==============
 INPUT / OUTPUT
 ==============
 inorder traversal of binary search tree:
 1 3 4 6 7 8 9 10 13 14
 */

public class BSTIteratorClient {

    private TreeNode root;

    public static void main(String[] args) {

        BSTIteratorClient bc = new BSTIteratorClient();



        int [] A = {8, 3, 10, 1, 6, 9, 14,  4, 7, 13};

        bc.buildBST(A);

        bc.bstIterDemo();




    }

    public void bstIterDemo() {
        BSTIterator bst = new BSTIterator(this.root);

        System.out.println("inorder traversal of binary search tree: ");
        while (bst.hasNext()) {
            System.out.print(bst.next() + " ");
        }
        System.out.println();
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

    TreeNode(int x) {
        this.val = x;
    }
}

class BSTIterator {

    private Stack<TreeNode> stack;
    private TreeNode node;

    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        node = root;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty() || node != null;
    }

    /** @return the next smallest number */
    public int next() {
        Integer result = Integer.MIN_VALUE;

        while (node != null) {
            stack.push(node);
            node = node.left;
        }


        if (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result = node.val;
            this.node = node.right;
        }

        return result;
    }
}