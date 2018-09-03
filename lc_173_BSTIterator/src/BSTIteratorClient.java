/**
 173. Binary Search Tree Iterator
 https://leetcode.com/problems/binary-search-tree-iterator/description/

 Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 Calling next() will return the next smallest number in the BST.
 Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 */

public class BSTIterator {

    public static void main(String[] args) {
        BSTIterator bst = new BSTIterator();


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