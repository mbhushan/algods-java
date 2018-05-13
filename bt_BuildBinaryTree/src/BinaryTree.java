/**
 * Created by manib on 5/12/18.
 *
 */
public class BinaryTree {

    private Node root;

    public BinaryTree() {
        root = null;
    }

    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        //https://upload.wikimedia.org/wikipedia/commons/thumb/d/da/Binary_search_tree.svg/400px-Binary_search_tree.svg.png
        int [] A = {8, 3, 10, 1, 6, 14, 4, 7, 13 };
        bt.buildBinarySearchTree(A);
        bt.inorder();
    }

    public void inorder() {
        System.out.println("inorder: ");
        inorder(this.root);
        System.out.println("null");
    }

    public void inorder(Node node) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            inorder(node.left);
        }
        System.out.print(node.value + " -> ");
        if (node.right != null) {
            inorder(node.right);
        }
    }

    public void buildBinarySearchTree(int [] A) {
        if (A == null || A.length < 1) {
            return;
        }
        for (int i=0; i<A.length; i++) {
            root = insertIntoBST(root, A[i]);
        }
    }

    public Node insertIntoBST(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }
        if (value <= node.value) {
            node.left = insertIntoBST(node.left, value);
        } else {
            node.right = insertIntoBST(node.right, value);
        }

        return  node;
    }
}

class Node {
    int value;
    Node left;
    Node right;

    Node(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public String toString() {
        return "[" + this.value + "]";
    }
}
