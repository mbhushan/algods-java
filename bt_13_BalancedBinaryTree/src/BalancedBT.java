import java.util.Stack;

/**
 LeetCode – Balanced Binary Tree (Java)

 Given a binary tree, determine if it is height-balanced.

 For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of
 the two subtrees of every node never differ by more than 1.

         10
       /    \
      5      15
     / \    /  \
    2  7   12  18


       10
     /    \
    5      15
   / \    /  \
  2  7   12  18
            /  \
           17  20
              /
             19
 */

public class BalancedBT {

    private Node root;

    BalancedBT() {
        this.root = null;
    }

    public static void main(String[] args) {
        BalancedBT bt = new BalancedBT();

        int [] A = {10, 5, 15, 2, 7, 12, 18};
        int [] B = {10, 5, 15, 2, 7, 12, 18, 17, 20, 19};

        bt.buildBST(A);
        //bt.buildBST(B);
        System.out.println("inorder traversal: ");
        bt.inorder();

        System.out.println("checking if tree is height balanced: ");
        System.out.println();
        bt.checkHeightBalanced();

    }

    public void checkHeightBalanced() {

        System.out.println("is height balanced? " + isBTHeightBalanced(this.root));
    }

    private boolean isBTHeightBalanced(Node node) {
        if (node == null) {
            return true;
        }

        int left = getHeight(node.left);
        int right = getHeight(node.right);

        if (Math.abs(left - right) <= 1 && isBTHeightBalanced(node.left) && isBTHeightBalanced(node.right)) {
            return true;
        }

        return false;
    }

    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }

        return  1 + Math.max(getHeight(node.left), getHeight(node.right));
    }


    public void buildBST(int [] A) {
        if (A == null) {
            return;
        }

        for (int i=0; i<A.length; i++) {
            this.root = insertIntoBST(this.root, A[i]);
        }
    }

    public Node insertIntoBST(Node node, int data) {
        if (node == null) {
            return new Node(data);
        }
        if (data <= node.data) {
            node.left = insertIntoBST(node.left, data);
        } else {
            node.right = insertIntoBST(node.right, data);
        }
        return node;
    }

    public void inorder() {
        inorder(this.root);
    }

    private void inorder(Node node ){
        Stack<Node> stack = new Stack();

        while(node != null || !stack.isEmpty()) {

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

    Node (Integer data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
