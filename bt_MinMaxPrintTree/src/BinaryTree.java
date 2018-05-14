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
        int [] A = {8, 3, 10, 1, 6, 14, 4, 7, 13 };
        System.out.println("A.len: " + A.length);
        bt.buildBST(A);
        bt.inorder();
        System.out.println();
        System.out.println("min value: " + bt.minValue());
        System.out.println("max value: " + bt.maxValue());

    }

    public Integer maxValue() {
        return maxValue(this.root);
    }

    private Integer maxValue(Node node) {

        int maxValue = Integer.MIN_VALUE;
        while (node != null) {
            if (node.data > maxValue) {
                maxValue = node.data;
            }
            node = node.right;
        }

        return maxValue;
    }

    public Integer minValue() {
        return minValue(this.root);
    }

    private Integer minValue(Node node) {

        int minValue = Integer.MAX_VALUE;
        while (node != null) {
            if (node.data < minValue) {
                minValue = node.data;
            }
            node = node.left;
        }

        return minValue;
    }

    public void inorder() {
        inorder(this.root);
    }

    private void inorder(Node node) {
        if (node == null) {
            return ;
        }
        if (node.left != null) {
            inorder(node.left);
        }
        System.out.print(node.data + " ");
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

    public Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
