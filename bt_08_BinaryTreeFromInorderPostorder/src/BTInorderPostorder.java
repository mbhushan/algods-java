import java.util.Stack;

/**
 Construct Binary Tree from Inorder and Postorder Traversal

 Given inorder and postorder traversal of a tree, construct the binary tree.

 Analysis


 This problem can be illustrated by using a simple example.

 in-order:   4 2 5  (1)  6 7 3 8
 post-order: 4 5 2  6 7 8 3  (1)
 From the post-order array, we know that last element is the root. We can find the root in in-order array.
 Then we can identify the left and right sub-trees of the root from in-order array.


 Using the length of left sub-tree, we can identify left and right sub-trees in post-order array.
 Recursively, we can build up the tree.

 ===============
 INPUT / OUTPUT
 ===============
 inorder iterative:
 4 2 5 1 6 7 3 8

 */
public class BTInorderPostorder {

    private Node root;

    BTInorderPostorder() {
        this.root = null;
    }

    public static void main(String[] args) {
        BTInorderPostorder bt = new BTInorderPostorder();

        int [] inorder = {4, 2, 5, 1, 6, 7, 3, 8};
        int [] postorder = {4, 5, 2,  6, 7, 8, 3,  1};

        bt.buildBT(inorder, postorder);

        System.out.println("inorder iterative: ");
        bt.inorder();

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

    public void buildBT(int [] inorder, int [] postorder) {

        this.root = buildBT(this.root, inorder, 0, inorder.length-1, postorder, 0,
                postorder.length-1);
    }


    private Node buildBT(Node node, int [] inorder, int inStart, int inEnd, int [] postorder, int postStart, int
            postEnd) {

        //terminating condition
        if (inStart > inEnd || postStart > postEnd) {
            return null;
        }

        int nodeVal = postorder[postEnd];
        node = new Node(nodeVal);
        int k = 0;

        for (int i=0; i<inorder.length; i++) {
            if (inorder[i] == nodeVal) {
                k = i;
                break;
            }
        }

        node.left = buildBT(node.left, inorder, inStart, k-1, postorder, postStart,
                postStart+(k-1-inStart));

        node.right = buildBT(node.right, inorder, k+1, inEnd, postorder, postStart + k - inStart,
                postEnd-1);

        return node;
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
