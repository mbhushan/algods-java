import java.util.Stack;

/**
 LeetCode – Binary Tree Right Side View (Java)

 Given a binary tree, imagine yourself standing on the right side of it, return the values of the
 nodes you can see ordered from top to bottom. For example, given the following binary tree,

    1            <---
  /   \
 2     3         <---
 \
 5             <---

 You can see [1, 3, 5].
 */

public class BinaryTreeView {

    private Node root;

    BinaryTreeView() {
        this.root = null;
    }

    public static void main(String[] args) {
        BinaryTreeView bst = new BinaryTreeView();

        int [] A = {8, 3, 10, 1, 6, 14, 4, 7, 13};

        bst.buildBST(A);
        System.out.println();
        System.out.println("inorder traversal: ");
        bst.inorder();
        System.out.println();

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

}


class Node {
    Integer data;
    Node left ;
    Node right;

    Node (Integer data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
