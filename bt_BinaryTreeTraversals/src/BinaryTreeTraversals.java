/**
 Preorder,
 Inorder,
 Postorder,
 Level Order,
 Level Order II,
 Vertical Order
 */
public class BinaryTreeTraversals {

    private Node root;

    BinaryTreeTraversals() {
        this.root = null;
    }

    public static void main(String [] args) {
        BinaryTreeTraversals btt = new BinaryTreeTraversals();

        // https://en.wikipedia.org/wiki/Binary_search_tree#/media/File:Binary_search_tree.svg
        int [] A = {8, 3, 10, 1, 6, 14, 4, 7, 13};
        btt.buildBST(A);
        btt.inorder();
        System.out.println();
        btt.preorder();
        System.out.println();
        btt.postorder();

    }

    public void inorder() {
        System.out.println("inorder traversal recursive: ");
        inorder(this.root);
    }

    private void inorder(Node node) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            inorder(node.left);
        }
        System.out.print(node.data + " ");
        if (node.right != null) {
            inorder(node.right);
        }
    }

    public void preorder() {
        System.out.println("preorder traversal recursive: ");
        preorder(this.root);
    }

    private void preorder(Node node) {
        if (node == null) {
            return;
        }

        System.out.print(node.data + " ");
        if (node.left != null) {
            preorder(node.left);
        }

        if (node.right != null) {
            preorder(node.right);
        }
    }

    public void postorder() {
        System.out.println("postorder traversal recursive: ");
        postorder(this.root);
    }

    private void postorder(Node node) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            postorder(node.left);
        }

        if (node.right != null) {
            postorder(node.right);
        }

        System.out.print(node.data + " ");
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
    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
