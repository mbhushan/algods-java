import java.util.Stack;

/**
 LeetCode – Construct Binary Tree from Preorder and Inorder Traversal (Java)
 Given preorder and inorder traversal of a tree, construct the binary tree.

 Analysis

 Consider the following example:
 in-order:   4 2 5 (1) 6 7 3 8
 pre-order: (1) 2 4 5  3 7 6 8
 From the pre-order array, we know that first element is the root. We can find the root in in-order array.
 Then we can identify the left and right sub-trees of the root from in-order array.


 Using the length of left sub-tree, we can identify left and right sub-trees in pre-order array.
 Recursively, we can build up the tree.

 =================
 INPUT / OUTPUT
 =================
 inorder traversal post building tree:
 4 2 5 1 6 7 3 8
 */

public class BinaryTreeInorderPreorder {

    private Node root;

    public static void main(String[] args) {
        BinaryTreeInorderPreorder bt = new BinaryTreeInorderPreorder();

        int [] inorder = {4, 2, 5, 1, 6, 7, 3, 8};
        int [] preorder = {1, 2, 4, 5,  3, 7, 6, 8};


        bt.buildBT(inorder, preorder);
        System.out.println("inorder traversal post building tree: ");
        bt.inorder();
    }

    public void buildBT(int [] inorder, int [] preorder) {

        this.root = buildBT(this.root, inorder, 0, inorder.length-1, preorder,
                0, preorder.length-1);

    }

    private Node buildBT(Node node, int [] inorder, int inStart, int inEnd, int [] preorder, int preStart, int preEnd) {

        if (inStart > inEnd || preStart > preEnd) {
            return null;
        }

        int rootVal = preorder[preStart];
        node = new Node(rootVal);
        int k = 0;
        for (int i=0; i<inorder.length; i++) {
            if (inorder[i] == rootVal) {
                k = i;
                break;
            }
        }

        node.left = buildBT(node.left, inorder, inStart, k-1, preorder,
                preStart+1, preStart + k - inStart);

        node.right = buildBT(node.right, inorder, k+1, inEnd, preorder,
                preStart + k - inStart +1, preEnd);


        return node;
    }


    public void inorder() {
        inorderIterative(this.root);
    }

    private void inorderIterative(Node node) {
        Stack<Node> stack = new Stack<>();

        while (node != null || !stack.isEmpty()) {
            if (node == null) {
                node = stack.pop();
                System.out.print(node.data + " ");
                node = node.right;
            }
            if (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
    }

}

class Node {
    Integer data;
    Node left;
    Node right;

    Node(Integer data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
