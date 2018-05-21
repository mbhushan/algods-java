import java.util.Stack;

public class BinaryTree {

    private Node root;

    BinaryTree() {
        this.root = null;
    }

    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        //int [] A = {8, 3, 10, 1, 6, 14, 4, 7, 13 };
        int [] A = {2, 1, 3};
        System.out.println("A.len: " + A.length);
        bt.buildBST(A);
        bt.inorder();
        System.out.println();
        bt.doubleTree();
        System.out.println("inorder after calling double tree: ");
        bt.inorder();
    }

    public void doubleTree() {
        doubleTree(this.root);
    }

    private void doubleTree(Node node) {
        if (node == null) {
            return;
        }
        doubleTree(node.left);
        doubleTree(node.right);
        Node oldLeft = node.left;
        node.left = new Node(node.data);
        node.left.left = oldLeft;
    }

    public void inorder() {
        if (this.root == null) {
            return;
        }
        Node node = this.root;
        Stack<Node> stack = new Stack<Node>();

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

    public void buildBST(int [] A) {
        if (A == null || A.length < 1) {
            return;
        }

        for (int i=0; i<A.length; i++) {
            this.root = insertBST(this.root, A[i]);
        }
    }

    public Node insertBST(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }
        if (value <= node.data) {
            node.left = insertBST(node.left, value);
        } else {
            node.right = insertBST(node.right, value);
        }

        return node;
    }
}

class Node {
    Integer data;
    Node left;
    Node right;

    public Node(Integer data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
