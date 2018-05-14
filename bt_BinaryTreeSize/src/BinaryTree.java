/**
 * Created by manib on 5/13/18.
 */
public class BinaryTree {

    private Node root;

    public BinaryTree() {
        this.root = null;
    }

    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();

        //https://upload.wikimedia.org/wikipedia/commons/thumb/d/da/Binary_search_tree.svg/400px-Binary_search_tree.svg.png
        int [] A = {8, 3, 10, 1, 6, 14, 4, 7, 13 };
        System.out.println("A.len: " + A.length);
        bt.buildBST(A);
        bt.inorder();
        System.out.println("Size of binary tree: " + bt.btSize());
        System.out.println("Max depth of the binary tree: " + bt.maxDepth());

    }

    public int maxDepth() {
        return maxDepth(this.root);
    }

    private int maxDepth(Node node) {
        if (node == null) {
            return 0;
        }
        int leftDepth = maxDepth(node.left);
        int rightDepth = maxDepth(node.right);

        return Math.max(leftDepth, rightDepth) + 1;
    }

    public int btSize() {
        return btSize(this.root);
    }

    private int btSize(Node node) {
        if (node == null) {
            return 0;
        }

        return btSize(node.left) + 1 + btSize(node.right);
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
        System.out.print(node.data + " -> ");
        if (node.right != null) {
            inorder(node.right);
        }
    }

    public void buildBST(int [] A) {
        if (A == null || A.length < 1) {
            return;
        }
        for (int i=0; i<A.length; i++) {
            root = insertIntoBST(root, A[i]);
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
